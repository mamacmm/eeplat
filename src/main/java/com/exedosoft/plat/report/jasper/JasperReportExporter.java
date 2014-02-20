package com.exedosoft.plat.report.jasper;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.util.DOGlobals;

public class JasperReportExporter
{
	private static Log	log	= LogFactory.getLog(JasperReportExporter.class);

	private static String getReportTemplatePath()
	{
		URL url = DOGlobals.class.getResource("/globals.xml");
		String aPath = url.getPath().toLowerCase();
		aPath = aPath.replaceAll("/[.]/", "/");
		String OUT_TEMPLATE = url.getPath().substring(0, aPath.indexOf("web-inf")) + "/web/report/";
		return OUT_TEMPLATE;
	}

	private static JasperPrint getJasperPrint(DOGridModel gm, HttpServletRequest request) throws Exception
	{
		try
		{

			String jasperPath = getReportTemplatePath() + gm.getExeDoc();

			if (jasperPath != null)
			{
				File jasperFile = new File(jasperPath);

				if (!jasperFile.exists())
				{
					throw new Exception("报表文件不存在 :" + jasperPath);
				}

				Map<String, Object> paras = new HashMap<String, Object>();

				Connection con = null;

				if ("true".equals(DOGlobals.getValue("multi.tenancy")))
				{
					con = DOGlobals.getInstance().getSessoinContext().getTenancyValues().getDataDDS().getConnection();
				}
				else
				{
					if (gm.getService() != null)
					{
						con = gm.getService().getBo().getDataBase().getConnection();
					}
				}

				if (con == null)
				{
					throw new Exception("报表无数据库连接......");
				}

				// 传递在EEPlat中定义的SQL语句
				String eeplat_sql = gm.getService().getTempSql();
				paras.put("eeplat_sql", eeplat_sql);
				log.info("EEPlat SQL::" + eeplat_sql);

				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paras, con);

				request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);

				return jasperPrint;
			}
		}
		catch (Exception e)
		{
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static void export(DOGridModel gm, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			JasperPrint jasperPrint = getJasperPrint(gm, request);

			if (jasperPrint != null)
			{
				String type = request.getParameter("type");

				if ("html".equalsIgnoreCase(type))
				{
					exportHtml(jasperPrint, request, response);
				}
				else if ("pdf".equalsIgnoreCase(type))
				{
					exportPdf(jasperPrint, request, response);
				}
				else if ("xls".equalsIgnoreCase(type))
				{
					exportExcel(jasperPrint, request, response);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 导出为html文件
	 * 
	 * @param jasperPrint
	 * @param response
	 */
	private static void exportHtml(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response)
	{

		try
		{
			response.setCharacterEncoding("UTF-8");
			JRHtmlExporter exporter = new JRHtmlExporter();

			exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER, response.getWriter());
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
			exporter.exportReport();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 导出为excel文件
	 * 
	 * @param jasperPrint
	 * @param response
	 */
	private static void exportExcel(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String filename = request.getParameter("filename");
			if (filename == null)
			{
				filename = "tzx";
			}

			response.setCharacterEncoding("UTF-8");
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

			response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
			response.setContentType("application/vnd_ms-excel");
			exporter.exportReport();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 导出为pdf文件
	 * 
	 * @param jasperPrint
	 * @param response
	 */
	private static void exportPdf(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String filename = request.getParameter("filename");
			if (filename == null)
			{
				filename = "tzx";
			}

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

			response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".pdf");
			response.setContentType("application/pdf");
			response.setCharacterEncoding("UTF-8");
			exporter.exportReport();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
