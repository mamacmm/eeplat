package com.exedosoft.plat.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerMetaData;

import com.exedosoft.plat.job.DOCronTrigger;




public class DOJobRestart extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798265805984034747L;
	private static Log log = LogFactory.getLog(DOJobRestart.class);

	public DOJobRestart() {
	}

	public String excute()  {
		
		try {
			DOCronTrigger.getScheduler().shutdown(true);

			SchedulerMetaData metaData = DOCronTrigger.getScheduler()
					.getMetaData();

			log.info("Executed " + metaData.getNumberOfJobsExecuted()
					+ " jobs.");
			
			DOCronTrigger docron = new DOCronTrigger();
			docron.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DEFAULT_FORWARD;
	}

}
