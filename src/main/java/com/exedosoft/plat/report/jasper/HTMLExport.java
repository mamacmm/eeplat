package com.exedosoft.plat.report.jasper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

/**
 * 利用报表生成HTML格式报表
 * 平台中用的是com.exedosoft.plat.ui.jquery.grid.report.* 
 * 这个包基本没用
 */
public class HTMLExport {

	/**
	 * 导出报表
	 * 
	 * @param request
	 * @param response
	 * @param reportFilePath
	 * @param params
	 * @param con
	 * @throws JasperReportException
	 */
	public void export(HttpServletRequest request,
			HttpServletResponse response, String reportFilePath, Map params,
			Connection con) throws JasperReportException {
		try {
			PrintWriter out = response.getWriter();
			try {
				response.setContentType("text/html;charset=UTF-8");
				
				JasperPrint jasperPrint = new JasperPrintWithCon(
						reportFilePath, params, con).getJasperPrint();
				
				
				// 使用JRHtmlExproter导出Html格式
				JRHtmlExporter exporter = new JRHtmlExporter();
				// request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
				// jasperPrint);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
//				exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
//						"./servlets/image?image=");
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
						"UTF-8");
				// 导出
				exporter.exportReport();
				

				
			} catch (Exception e) {
				e.printStackTrace();
				throw new JasperReportException("在导出Html格式报表时发生错误!");
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (Exception e) {
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new JasperReportException("从Response中取得PrintWriter时发生错误!");
		}
	}

}