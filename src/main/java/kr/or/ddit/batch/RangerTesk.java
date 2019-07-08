package kr.or.ddit.batch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RangerTesk {
	
	private static final Logger logger = LoggerFactory.getLogger(RangerTesk.class);
	
	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name = "rangerJob")
	private Job rangerJob;
	
	public void rangerTask() throws JobExecutionAlready {
		logger.debug("==========================rangerTask==========================");
		
	}
}
