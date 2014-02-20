package com.exedosoft.plat.action.customize.tools.appshare;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

public class DOImportSQL extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;
	private static Log log = LogFactory.getLog(DOImportSQL.class);

	@Override
	public String excute() throws ExedoException {

		String fileName = this.actionForm.getValue("fileName");

		if (fileName == null || fileName.trim().equals("")) {
			this.setEchoValue(I18n.instance().get("你还没有选择文件！"));

			return NO_FORWARD;
		}

		// if (datasourceuid == null || datasourceuid.trim().equals("")) {
		// this.setEchoValue("你没有选择数据源！ ");
		// return NO_FORWARD;
		// }

		fileName = DOGlobals.UPLOAD_TEMP.trim() + "/" + fileName.trim();
		System.out.println("FileName::" + fileName);
		boolean isImport = importSQL(fileName);
		if (!isImport) {
			return NO_FORWARD;
		}

		this.setEchoValue(I18n.instance().get("导入成功!"));

		return DEFAULT_FORWARD;

	}

	public static boolean importSQL(String fileName) {

		String driverClass = null;
		try {
			if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {

				if (DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues() != null
						&& DOGlobals.getInstance().getSessoinContext()
								.getTenancyValues().getDds() != null) {
					driverClass = DOGlobals.getInstance().getSessoinContext()
							.getTenancyValues().getDds().getDriverClass();
				}
			} else {

				DOBO bo = DOBO.getDOBOByName("do_datasource");
				if (bo != null && bo.getDataBase() != null) {
					driverClass = bo.getDataBase().getDriverClass();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection con = DOBO.getDOBOByName("do_bo").getDataBase()
				.getConnection();

		try {
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			List[] sqlLists = readSqlFile(fileName);

			List<String> insertSqls = sqlLists[0];
			List<String> globalSqls = sqlLists[1];
			List<String> createSqls = sqlLists[2];
			List<String> boNameSqls = sqlLists[3];

			for (String sql : insertSqls) {
				if ("com.mysql.jdbc.Driver".equals(driverClass)) {

					sql = sql.replace(",condition=?,", ",`condition`=?,")
							.replace(",condition,", ",`condition`,");

				}
				if (sql != null && !sql.trim().equals("")) {
					log.info("正在执行SQL语句:::" + sql);
					// //////////////当配置库为mysql，处理sql中含有conditon 的情况

					// stmt.execute(sql);
					stmt.addBatch(sql);
				}
			}
			stmt.executeBatch();

			con.commit();
			stmt.clearBatch();

			for (String sql : globalSqls) {
				try {

					log.info("正在执行的SQL语句:::" + sql);
					stmt.execute(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			con.commit();

			// //////////更新业务对象的数据源。
			for (String aTable : boNameSqls) {

				String sql = "update do_bo set dataSourceUid='"
						+ DOBO.getDOBOByName("do_authorization").getDataBase()
								.getObjUid() + "' where name='" + aTable + "'";

				stmt.execute(sql);

			}

			stmt.close();
			// ////////////////////create sql 在另外一个库

			con = DOBO.getDOBOByName("do_authorization").getDataBase()
					.getConnection();
			log.info("DO_Authorization::::" + DOBO.getDOBOByName("do_authorization").getDataBase());
			con.setAutoCommit(false);
			stmt = con.createStatement();
			for (String sql : createSqls) {
				try {

					log.info("正在执行的SQL语句:::" + sql);
					stmt.execute(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			con.commit();

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {

			}
			e.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {

			}
		}
		return true;
	}

	// 从文件读放内容到按分号放到sqlFileList
	public static List[] readSqlFile(String fileName) {

		List[] sqlList = new List[4];

		try {

			SAXReader saxReader = new SAXReader();

			Document document = saxReader.read(fileName);

			Element rootElement = document.getRootElement();

			List<String> insertSqls = new ArrayList<String>();
			List<String> createSqls = new ArrayList<String>();
			List<String> globalSqls = new ArrayList<String>();
			List<String> boNameSqls = new ArrayList<String>();

			for (Iterator it = rootElement.iterator(); it.hasNext();) {
				Node aNode = (Node) it.next();
				if (aNode instanceof Element) {
					Element elem = (Element) aNode;

					if (elem.getName().equals("insert")) {
						insertSqls.add(elem.getTextTrim());
					} else if (elem.getName().equals("global")) {
						globalSqls.add(elem.getTextTrim());
					} else if (elem.getName().equals("create_table_sql")) {
						createSqls.add(elem.getTextTrim());
					} else if (elem.getName().equals("create_bo_name")) {
						boNameSqls.add(elem.getTextTrim());
					}
				}
			}

			System.out.println("insertSqls:::" + insertSqls);
			System.out.println("globalSqls:::" + globalSqls);
			System.out.println("createSqls:::" + createSqls);
			System.out.println("boNameSqls:::" + boNameSqls);

			sqlList[0] = insertSqls;
			sqlList[1] = globalSqls;
			sqlList[2] = createSqls;
			sqlList[3] = boNameSqls;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sqlList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// String fileName = null;
		//
		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf"));
		// fileName = prefix + "/exedo/initsql/mysql.sql";
		String fileName = prefix + "/appshare/Recruiting/Recruiting.sql.xml";

		System.out.println("FileName" + fileName);
		DOImportSQL.readSqlFile(fileName);

		// String s2 = "this is a test";
		// String sarray[] = s2.split("\\s");
		// System.out.println("sarray.length=" + sarray.length);

		//
		// 然后一句句的执行
		// for (String sql : sqlList) {
		// pstmt = con.prepareStatement(sql,
		// ResultSet.TYPE_SCROLL_INSENSITIVE,
		// ResultSet.CONCUR_READ_ONLY);
		// pstmt.execute();
		//
		// }

	}

	// BufferedReader in = new BufferedReader(new InputStreamReader(
	// new FileInputStream(fileName), "utf-8"));
	// StringBuffer sb = new StringBuffer();
	// while (true) {
	// String aLine = in.readLine();
	// if (aLine == null) {
	// break;
	// }
	// if (aLine.startsWith("/***")) {
	// continue;
	// }
	// sb.append(aLine);
	// if (aLine.endsWith(");") || aLine.endsWith("utf8;")) {
	// sb.append("\t\r\n");
	// }
	// }
	// in.close();
	// String theSqls = sb.toString();
	// String sqls[] = theSqls.split(";\t\r\n");
	// for (String sql : sqls) {
	// if (!sql.contains("insert into")
	// && !sql.toLowerCase().startsWith("create table")) {
	//
	// System.out.println("Rubbish SQL:::" + sql);
	// continue;
	// }
	// sqlList.add(sql);
	// }
	// } catch (Exception e) {
	// e.getStackTrace();
	// }

}
