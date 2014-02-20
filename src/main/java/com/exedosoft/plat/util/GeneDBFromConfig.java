package com.exedosoft.plat.util;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.SQLTypes;
import com.exedosoft.plat.bo.DODataSource;

/**
 * 类型就是收敛到一定的程度，比如就是现在的4个类型。 但是还要支持扩展的类型。
 * 
 * @author anolesoft
 * 
 */

public class GeneDBFromConfig {

	private static Log log = LogFactory.getLog(GeneDBFromConfig.class);

	private static List<String> defaultTables = new ArrayList<String>();

	private static void addTableName(String aTableName) {
		defaultTables.add(aTableName);
	}

	static {

		addTableName("do_org_dept");
		addTableName("do_org_role");
		addTableName("do_org_user");
		addTableName("do_org_user_role");
		addTableName("do_org_user_delegate");
		addTableName("do_auth_owner");
		addTableName("do_auth_suite");
		addTableName("do_authorization");
		addTableName("do_log");
		addTableName("do_log_data");
		addTableName("do_org_role_conflict");
		addTableName("do_org_timespan");

		addTableName("do_wfi_ni_dependency");
		addTableName("do_wfi_nodeinstance");
		addTableName("do_wfi_processinstance");
		addTableName("do_wfi_varinstance");
		addTableName("wf_person");

		addTableName("do_wfi_his_ni_dependency");
		addTableName("do_wfi_his_nodeinstance");
		addTableName("do_wfi_his_processinstance");
		addTableName("do_wfi_his_varinstance");

		// /工作流测试的例子
		addTableName("t_expense");

		defaultTables = Collections.unmodifiableList(defaultTables);

	}

	public static void main(String[] args) {

		List<DOBO> bos = DOBO.getDOBOsofTable();
		StringBuilder sb = new StringBuilder();
		for (DOBO aBO : bos) {
			sb.append(getCreateOneTable(aBO));
		}

		System.out.println("Create DB SQL::" + sb);
	}

	public static String getCreateOneTable(DOBO aBO) {

		StringBuilder sb = new StringBuilder();
		if (aBO.getSqlStr() != null) {
			if (!defaultTables.contains(aBO.getSqlStr().toLowerCase())) {

				sb.append("create table ").append(aBO.getSqlStr())
						.append(" ( \n");
				List<DOBOProperty> dops = aBO.retrieveProperties();
				int i = 0;
				for (DOBOProperty aPro : dops) {
					i++;
					String colType = getColType(aPro);
					sb.append(aPro.getOldColName()).append(" ").append(colType);

					if (i == dops.size()) {
						sb.append("); \n\n\n");
					} else {
						sb.append(",\n");
					}

				}

			}
		}

		return sb.toString();
	}

	public static String getColType(DOBOProperty aPro) {
		String colType = "varchar(50)";

		int iDBSize = 255;
		if (aPro.getDbSize() != null) {
			iDBSize = aPro.getDbSize().intValue();
		}
		if (Types.VARCHAR == aPro.getDbType()) {
			colType = "varchar(" + iDBSize + ")";
		} else if (Types.CHAR == aPro.getDbType()) {
			colType = "char(" + iDBSize + ")";
		}

		else if (aPro.isClobType()) {
			colType = "text";
		}

		else if (aPro.isDateType()) {
			colType = "datetime";
		}

		else if (aPro.isTimestampType()) {
			colType = "timestamp";
		}

		else if (aPro.isDecimal()) {
			colType = "decimal(10,2)";
		}

		else if (aPro.isInt() || aPro.isLong()) {
			colType = "integer";
		}

		else if (aPro.isNumberType()) {
			colType = "decimal(10,2)";
		}
		return colType;
	}

}
