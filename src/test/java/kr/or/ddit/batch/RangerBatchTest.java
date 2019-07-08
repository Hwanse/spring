package kr.or.ddit.batch;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:"})
public class RangerBatchTest {

	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name = "rangerJob")
	private Job rangerJob;
	
	/**
	* Method : rangerBatchTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 레이전스 이름 수정 배치 테스트
	 * @throws JobParametersInvalidException 
	 * @throws JobInstanceAlreadyCompleteException 
	 * @throws JobRestartException 
	 * @throws JobExecutionAlreadyRunningException 
	*/
	@Test
	public void rangerBatchTest() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		/***Given***/
		

		/***When***/
		JobExecution jobExecution= jobLauncher.run(rangerJob, new JobParameters());
		
		/***Then***/
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		
	}

}
