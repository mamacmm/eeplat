package com.exedosoft.plat.action;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class DOCSVImport extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798265805984034747L;
	private static Log log = LogFactory.getLog(DOCSVImport.class);

	public DOCSVImport() {
	}

	public String excute() {

		if (this.service == null || this.service.getTempSql() == null) {
			this.setEchoValue(I18n.instance().get("未配置SQL 语句"));
			return NO_FORWARD;
		}

		Transaction t = this.service.currentTransaction();
		try {
			t.begin();

			String fileName = this.actionForm.getValue("csvfile");

			if (fileName == null || fileName.trim().equals("")) {
				this.setEchoValue(I18n.instance().get("你还没有选择文件！"));

				return NO_FORWARD;
			}

			fileName = DOGlobals.UPLOAD_TEMP.trim() + "/" + fileName.trim();
			System.out.println("FileName::" + fileName);

			int lines = this.getNumberLines(fileName);

			if (lines == 0) {
				setEchoValue(I18n.instance().get("没有数据"));
				return NO_FORWARD;
			}

			if (lines > 20010) {
				this.setEchoValue(I18n.instance().get(
						"CSV data can not be more than 20,000 lines."));
				return NO_FORWARD;
			}
			try {
				CSVReader reader = new CSVReader(new FileReader(fileName));
				this.service.beginBatch();

				String[] nextLine;
				List paras = this.service.retrieveParaServiceLinks();
				while ((nextLine = reader.readNext()) != null) {// /一行数据
					Map aMap = new HashMap();
					for (int i = 0; i < nextLine.length; i++) {
						int j = -1;
						for (Iterator it = paras.iterator(); it.hasNext();) {
							DOParameterService dops = (DOParameterService) it
									.next();
							if (i == j) {
								DOParameter dop = dops.getDop();

								aMap.put(dop.getName(), nextLine[i]);
							}
							j++;
							if(j > i){
								break;
							}
						}
					}
					this.service.addBatch(aMap);
				}

				this.service.endBatch();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			t.end();
		}

		this.setEchoValue(I18n.instance().get("导入成功!"));

		return DEFAULT_FORWARD;
	}

	private int getNumberLines(String fileName) {

		File test = new File(fileName);
		long fileLength = test.length();
		LineNumberReader rf = null;
		int lines = 0;
		try {
			rf = new LineNumberReader(new FileReader(test));
			if (rf != null) {

				rf.skip(fileLength);
				lines = rf.getLineNumber();
				rf.close();
			}
		} catch (IOException e) {
			if (rf != null) {
				try {
					rf.close();
				} catch (IOException ee) {
				}
			}
		}
		return lines;

	}

}
