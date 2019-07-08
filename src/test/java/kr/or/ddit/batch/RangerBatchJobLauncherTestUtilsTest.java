package kr.or.ddit.batch;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/application-batch-dev.xml",
								   "classpath:kr/or/ddit/config/spring/application-datasource-dev.xml"})
public class RangerBatchJobLauncherTestUtilsTest {

	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void rangerBatchTest() throws Exception{
		/***Given***/
		

		/***When***/
		// spring의 JobExecution
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		
		
		/***Then***/
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	
		
	}

}
