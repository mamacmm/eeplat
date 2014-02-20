package com.exedosoft.plat.report.jasper;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 使用报表模板及数据等来生成JapserPrint
 *  * 平台中用的是com.exedosoft.plat.ui.jquery.grid.report.* 
 * 这个包基本没用

 */
public class JasperPrintWithCon {
	/** 传入的参数 */
	private Map params;
	/** 模板文件的地址 */
	private String reportFilePath;
	/** JDBC connection */
	private Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	public String getReportFilePath() {
		return reportFilePath;
	}

	public void setReportFilePath(String reportFilePath)
			throws JasperReportException {
		if (reportFilePath == null || !reportFilePath.endsWith(".jasper"))
			throw new JasperReportException("您传入的模板文件格式不对，请传入以.jasper为后缀的文件!");
		this.reportFilePath = reportFilePath;
	}

	public JasperPrintWithCon() {
		super();
	}

	public JasperPrintWithCon(String reportFilePath, Map params, Connection con)
			throws JasperReportException {
		if (reportFilePath == null || !reportFilePath.endsWith(".jasper"))
			throw new JasperReportException("模板文件格式不对，请传入以.jasper为后缀的文件!");
		if (con == null)
			throw new JasperReportException("Conncetion不应当为null!");
		this.setReportFilePath(reportFilePath);
		this.setParams(params);
		this.setCon(con);
	}

	/**
	 * 取得JasperPrint
	 * 
	 * @return
	 * @throws JasperReportException
	 */
	public JasperPrint getJasperPrint() throws JasperReportException {
		File reportFile = new File(this.reportFilePath);
		if (!reportFile.exists())
			throw new JasperReportException("传入的模板文件不存在!");

		try {
			// Load编译好的模板
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportFile.getPath());
			// 进行数据填充
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, this.params, this.con);
			return jasperPrint;

		} catch (JRException jre) {
			jre.printStackTrace();
			throw new JasperReportException(
					"在进行数据填充时发生了错误中，请检查是否是数据库连接错误或者是用来填充的参数map有误!");
		}

	}
}