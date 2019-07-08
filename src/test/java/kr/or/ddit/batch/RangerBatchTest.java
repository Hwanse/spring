package kr.or.ddit.batch;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	*/
	@Test
	public void rangerBatchTest() {
		/***Given***/
		

		/***When***/
		JobExecution jobExecution= jobLauncher.run(rangerJob, new JobParameters());
		
		/***Then***/
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		
	}

}
