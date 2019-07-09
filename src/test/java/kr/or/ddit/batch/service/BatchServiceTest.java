package kr.or.ddit.batch.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;

public class BatchServiceTest extends LogicTestEnv{

	
	@Resource(name = "batchService")
	private IBatchService batchService;
	
	/**
	* Method : createDaily
	* 작성자 : PC14
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 생성 테스트
	*/
	@Test
	public void createDailyTest() {
		/***Given***/
		String ym = "201907";

		/***When***/
		int createCnt = batchService.createDaily(ym);
		
		/***Then***/
		assertEquals(69, createCnt);
		
	}

	/**
	* Method : deleteDaily
	* 작성자 : PC14
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 테스트
	*/
//	@Test
//	public void deleteDailyTest() {
//		/***Given***/
//		String ym = "201907";
//		batchDao.createDaily(ym);
//		
//		/***When***/
//		int deleteCnt = batchDao.deleteDaily(ym);
//		
//		/***Then***/
//		assertEquals(69, deleteCnt);
//		
//	}
	
}
