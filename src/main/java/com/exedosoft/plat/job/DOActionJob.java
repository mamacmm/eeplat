package com.exedosoft.plat.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exedosoft.plat.ActionFactory;
import com.exedosoft.plat.bo.DOActionConfig;

public class DOActionJob implements Job {

	private static Logger _log = LoggerFactory.getLogger(DOActionJob.class);

	public static final String ACTION_NAME = "ACTION_NAME";

	public static final String TENANT_VALUES = "TENANT_VALUES";

	public DOActionJob() {
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		// This job simply prints out its job name and the
		// date and time that it is running
		JobKey jobKey = context.getJobDetail().getKey();

		// Grab and print passed parameters
		JobDataMap data = context.getJobDetail().getJobDataMap();
		String actionName = data.getString(ACTION_NAME);

		try {
			DOActionConfig dac = DOActionConfig.getActionConfig(actionName);
			_log.info("ActionConfig::::::" + dac);
			String result = "";
			if (dac != null) {
				result = ActionFactory.getActionValue(dac);
			}

			_log.info("DOActionJob: " + jobKey + " executing at " + new Date()
					+ "\n" + "  ActionClass is " + actionName + "\n Result is "
					+ result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		DOActionConfig dac = DOActionConfig.getActionConfig("testcron_ation");

		System.out.println("DAC:::" + dac);
		System.out.println("Get Action Value:::"
				+ ActionFactory.getActionValue(dac));
	}

}
