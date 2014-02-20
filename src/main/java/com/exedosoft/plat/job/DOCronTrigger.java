package com.exedosoft.plat.job;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exedosoft.plat.bo.DOActionConfig;
import com.exedosoft.plat.bo.job.DOJobConfig;

public class DOCronTrigger {

	// First we must get a reference to a scheduler
	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static Scheduler sched = null;

	static {

		try {
			sched = sf.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Scheduler getScheduler() {
		return sched;
	}

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(DOCronTrigger.class);

		log.info("------- Initializing -------------------");

		log.info("------- Initialization Complete --------");

		log.info("------- Scheduling Jobs ----------------");
		


		// jobs can be scheduled before sched.start() has been called

		// job 1 will run every 20 seconds
		JobDetail job = newJob(DOActionJob.class)
				.withIdentity("job1", "group1").build();

		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(cronSchedule("0/20 * * * * ?")).build();

		// pass initialization parameters into the job
		job.getJobDataMap().put(DOActionJob.ACTION_NAME, "SubmitWf");

		Date ft = sched.scheduleJob(job, trigger);

		log.info(job.getKey() + " has been scheduled to run at: " + ft
				+ " and repeat based on expression: "
				+ trigger.getCronExpression());

		// ////////////////////////////////////////////////////////////////////////the
		// second Aciton Class
		List<DOJobConfig> jobConfigs = DOJobConfig.getJobConfigs();
		
		System.out.println("JobConfigs:::::::::;" + jobConfigs);

		for (DOJobConfig jobConfig : jobConfigs) {

			DOActionConfig dac = jobConfig.getActionConfig();
			String cronScript = jobConfig.getCronScript();
			job = newJob(DOActionJob.class).withIdentity(
					"job_" + jobConfig.getJobName(), "group1").build();
			trigger = newTrigger()
					.withIdentity("trigger_" + jobConfig.getJobName(), "group1")
					.withSchedule(cronSchedule(cronScript)).build();
			// pass initialization parameters into the job
			job.getJobDataMap().put(DOActionJob.ACTION_NAME, dac.getName());
			ft = sched.scheduleJob(job, trigger);

			log.info(job.getKey() + " has been scheduled to run at: " + ft
					+ " and repeat based on expression: "
					+ trigger.getCronExpression());

		}

		sched.start();

	}

	public static void main(String[] args) throws Exception {

		DOCronTrigger docron = new DOCronTrigger();
		docron.run();
	}

}
