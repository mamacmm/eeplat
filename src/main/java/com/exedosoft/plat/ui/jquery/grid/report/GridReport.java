package com.exedosoft.plat.ui.jquery.grid.report;

import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;


import com.exedosoft.plat.report.jasper.JasperPrintWithCon;
import com.exedosoft.plat.report.jasper.JasperReportException;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class GridReport extends DOViewTemplate {

	
	private static Log log = LogFactory.getLog(GridReport.class);

	public GridReport() {
		dealTemplatePath("/grid/report/GridReport.ftl");
	}

	public static void report(DOGridModel gm, HttpServletRequest request, Writer out) throws Exception {

		String jasporFile = getReportTemplatePath() + gm.getExeDoc();

		if (jasporFile == null) {
			return;
		}

		Map paras = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getMap();
		paras.putAll(DOGlobals.getInstance().getSessoinContext().getUser()
				.getMap());

		Connection con = null;

		if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {
			con = DOGlobals.getInstance().getSessoinContext()
					.getTenancyValues().getDataDDS().getConnection();
		} else {
			if (gm.getService() != null) {
				con = gm.getService().getBo().getDataBase().getConnection();
			}
		}

		if (con == null) {
			return;
		}

		//////////////////////传递在EEPlat中定义的SQL语句
		String eeplat_sql = "";
		try{
			eeplat_sql =	gm.getService().getTempSql();
			paras.put("eeplat_sql", eeplat_sql);
			if(gm.getSecondService()!=null){
				paras.put("eeplat_sql2", gm.getSecondService().getTempSql());	
			}
			log.info("EEPlat SQL::" + eeplat_sql);

		}catch(Exception e){
			
		}

		try {
			JasperPrint jasperPrint = new JasperPrintWithCon(jasporFile, paras,
					con).getJasperPrint();

			// 使用JRHtmlExproter导出Html格式
			JRHtmlExporter exporter = new JRHtmlExporter();
			 request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
			 jasperPrint);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			 exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
			"/"  + DOGlobals.URL + "/servlets/image?image=");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
					"UTF-8");
			// 导出
			exporter.exportReport();

		} catch (JasperReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getReportTemplatePath() {
		URL url = DOGlobals.class.getResource("/globals.xml");
		String aPath = url.getPath().toLowerCase();
		aPath = aPath.replaceAll("/[.]/", "/");
		String OUT_TEMPLATE = url.getPath().substring(0,
				aPath.indexOf("web-inf"))
				+ "/web/report/";
		return OUT_TEMPLATE;
	}

	// Jasper Report Back
	// Map<String, Object> data = super.putData(doimodel);
	//
	// DOGridModel gm = (DOGridModel) doimodel;
	//
	//
	// String jasporFile = getReportTemplatePath() + gm.getExeDoc();
	//
	// if(jasporFile == null){
	// return data;
	// }
	//
	//
	// Map paras =
	// DOGlobals.getInstance().getSessoinContext().getFormInstance().getMap();
	// paras.putAll(DOGlobals.getInstance().getSessoinContext().getUser().getMap());
	//
	// Connection con = null;
	//
	// if("true".equals(DOGlobals.getValue("multi.tenancy"))){
	// con =
	// DOGlobals.getInstance().getSessoinContext().getTenancyValues().getDataDDS().getConnection();
	// }else{
	// if(gm.getService()!=null){
	// con = gm.getService().getBo().getDataBase().getConnection();
	// }
	// }
	//
	// if(con==null){
	// return data;
	// }
	//
	// try {
	// JasperPrint jasperPrint = new JasperPrintWithCon(
	// jasporFile, paras,
	// con
	// ).getJasperPrint();
	//
	//
	// // 使用JRHtmlExproter导出Html格式
	// JRHtmlExporter exporter = new JRHtmlExporter();
	// //
	// request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
	// // jasperPrint);
	// StringWriter sw = new StringWriter();
	//
	// exporter.setParameter(JRExporterParameter.JASPER_PRINT,
	// jasperPrint);
	// exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, sw);
	// // exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
	// // "./servlets/image?image=");
	// exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
	// "UTF-8");
	// // 导出
	// exporter.exportReport();
	//
	//
	// data.put("htmlreport", sw.getBuffer() );
	//
	// } catch (JasperReportException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (JRException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

}
