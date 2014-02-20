package com.exedosoft.plat.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.util.id.UUIDHex;

public class WriteTxtFile {

	public WriteTxtFile() {
		// TODO Auto-generated constructor stub
	}

	
	public static void  writeContent(File aFile,String content){
		

		 try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
			 new FileOutputStream(aFile), "utf-8"));
			 out.append(content);
			 out.flush();
			 out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.jdbc.Driver");

		String driverUrl = "jdbc:mysql://58.215.163.177:3306/mvnforum?useUnicode=true&characterEncoding=utf-8";

		Connection con = DriverManager
				.getConnection(driverUrl, "wkx", "123asd");

		// con.setAutoCommit(false);
		// PreparedStatement pstmt = con
		// .prepareStatement();



		File indexFile = new File("d:\\eeplatqq5.txt");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(indexFile), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String bakDay = "";
		String oneDay = null;

		StringBuffer dayBuffer = new StringBuffer();
		String title = "";
		boolean isEndFile = false;
		while (true) {
			String aLine = in.readLine();
			
			System.out.println(aLine);
			if (aLine == null) {
				break;
			}

			if (aLine.replace("\\s", "").equals("")) {
				continue;
			}

			if (aLine.startsWith("2013-") || aLine.startsWith("2012-")
					|| aLine.startsWith("2011-") || aLine.startsWith("2010-")) {
				oneDay = aLine.substring(0, aLine.indexOf(" "));

			}

			if (oneDay != null && oneDay.equals(bakDay)) {

				if (!(aLine.startsWith("2013-") || aLine.startsWith("2012-")
						|| aLine.startsWith("2011-") || aLine
							.startsWith("2010-"))) {

					if (!isEndFile && aLine.length() > 0 && aLine.length() < 50) {
						title = aLine;
						if (aLine.indexOf("?") != -1
								|| aLine.indexOf("？") != -1
								|| aLine.indexOf("请问") != -1) {
							isEndFile = true;
						}
					}

				}

				dayBuffer.append(aLine).append("\n");
			}

			if (oneDay != null && !oneDay.equals(bakDay)) {
				System.out.println("Title:::" + title);
				System.out.println("dayBuffer:::" + dayBuffer);
				insertData(con, dayBuffer, title);
				dayBuffer.setLength(0);
				isEndFile = false;

				
			}

			bakDay = oneDay;

			// sb.append(aLine).append(";").append("\n");// \n\r
		}
		insertData(con, dayBuffer, title);
		in.close();

		con.close();

		// BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
		// new FileOutputStream(indexFile+"2"), "utf-8"));
		// out.append(sb.toString());
		// out.flush();
		// out.close();

		// URL url = ReadTxtFile.class.getResource("/AccessUtil.java");
		//
		//
		// File file = new File(url.getFile());
		//
		//
		// FileReader fr = new FileReader(file);
		// BufferedReader in = new BufferedReader( fr);
		//
		// // List allChs = new ArrayList();
		// StringBuffer sb = new StringBuffer();
		// while (true) {
		//
		// String aLine = in.readLine();
		//
		//
		// if(aLine==null){
		// break;
		// }
		//
		// char[] chars = aLine.toCharArray();
		//
		// StringBuilder zuci = new StringBuilder();
		//
		// List lineChs = new ArrayList();
		// for(int i = 0; i < chars.length ; i ++){
		//
		// char aChar = chars[i];
		// int p = (int) chars[i];
		// ////大于160是汉字
		// if (p > 160) {
		// zuci.append(aChar);
		// //System.out.println(aChar);
		// }else{
		// if(zuci.length()>0){
		// //System.out.println(zuci);
		// //allChs.add(zuci.toString());
		// lineChs.add(zuci.toString());
		// zuci.delete(0, zuci.length());
		// }
		// }
		// }
		// for(Iterator it = lineChs.iterator(); it.hasNext();){
		// String aChi = (String)it.next();
		// ////这个地方获取拼音后应该加上随机数
		// String theRep = "${" + PinYin.getPingyin(aChi) + "?if_exists}";
		// aLine = aLine.replace(aChi, theRep);
		// StringBuilder theOutStr = new StringBuilder("<label name=\"");
		// theOutStr.append(PinYin.getPingyin(aChi))
		// .append("\">")
		// .append(aChi)
		// .append("</label>");
		//
		// System.out.println(theOutStr);
		//
		// }
		//
		//
		//
		//
		// sb.append(aLine).append("\n");
		//
		// }
		//

		// for(Iterator it = allChs.iterator();it.hasNext();){
		//
		// String aCh = (String)it.next();
		//
		// String theRep = "${" + PinYin.getPingyin(aCh) + "?if_exists}";
		//
		// //${dt_apply_usefullife_start?if_exists}
		// theAllString = theAllString.replace(aCh, theRep);
		//
		// StringBuilder theOutStr = new StringBuilder("<label name=\"");
		// theOutStr.append(PinYin.getPingyin(aCh))
		// .append("\">")
		// .append(aCh)
		// .append("</label>");
		//
		// // <label name="tbsj">填 表 时 间</label>
		// System.out.println(theOutStr);
		//
		// }

		// File file2 = new File("c:\\first2.ftl");
		//
		// FileWriter fw = new FileWriter(file2);
		//
		// BufferedWriter out = new BufferedWriter(fw);
		// out.write(sb.toString());
		// out.flush();
		// out.close();
		// System.out.println(allChs);

		// 写文件

		// 读文件同时写文件java 不运行，可以一遍读一遍创建新的文件。独到内存中再处理 o了。

		// String content = jTextField1.getText() ;
		// FileWriter fw = null;
		// try {
		// fw = new FileWriter("src\\home\\bb.properties");
		// fw.write(content);
		// fw.close();

	}

	private static void insertData(Connection  con, StringBuffer dayBuffer,
			String title) throws SQLException {
		if (title != null && !title.trim().equals("")) {
			String sql = "insert into mvnforumthread(forumid,membername,lastpostmembername,threadtopic,threadbody,threadcreationdate,threadlastpostdate) values('1','admin','admin',?,?,?,?)";
			
			PreparedStatement	pstmt = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, title);
			pstmt.setString(2, dayBuffer.toString());
			pstmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			 rs.next();
			String uid = String.valueOf(rs.getInt(1)); // 得到第一个键值
			rs.close();
			pstmt.close();
			
			
			
			sql = "insert into mvnforumpost(parentPostID,forumid,threadid,memberid,membername,posttopic,postbody,postcreationdate,postlasteditdate,postcreationip) values('0','1',?,'1','admin',?,?,?,?,'127.0.0.1')";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uid);
			pstmt.setString(2, title);
			pstmt.setString(3, dayBuffer.toString());
			pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
		
			
			
			
			
		}
	}

}
