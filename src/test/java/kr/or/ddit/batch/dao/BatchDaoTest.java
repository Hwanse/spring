package kr.or.ddit.batch.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.batch.model.BatchVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class BatchDaoTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(BatchDaoTest.class);
	
	@Resource(name = "batchDao")
	private IBatchDao batchDao;
	
	
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
		int createCnt = batchDao.createDaily(ym);
		
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
	@Test
	public void deleteDailyTest() {
		/***Given***/
		String ym = "201907";
		batchDao.createDaily(ym);
		
		/***When***/
		int deleteCnt = batchDao.deleteDaily(ym);
		
		/***Then***/
		assertEquals(69, deleteCnt);
		
	}
	
	/**
	* Method : insertBatch
	* 작성자 : PC14
	* 변경이력 :
	* @param batchVO
	* @return
	* Method 설명 : 배치정보 입력 테스트
	*/
	@Test
	public void insertBatchTest() {
		/***Given***/
		BatchVO batchVO = new BatchVO();
		batchVO.setBcd("01");	// 일실적 배치 : 01
		batchVO.setSt("01");	// 배치 실행상태 : 01 -실행중

		/***When***/
		logger.debug("before insertBatch batchVO.getBid() ; {}", batchVO.getBid());
		int insertCnt = batchDao.insertBatch(batchVO);
		logger.debug("after insertBatch batchVO.getBid() ; {}", batchVO.getBid());
		
		
		/***Then***/
		assertEquals(1, insertCnt);
		
	}

	
	/**
	* Method : updateBatchTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 배치 데이터 업데이트 테스트
	*/
	@Test
	public void updateBatchTest() {
		/***Given***/
		BatchVO batchVO = new BatchVO();
		batchVO.setBcd("01");	// 일실적 배치 : 01
		batchVO.setSt("01");	// 배치 실행상태 : 01 -실행중
		batchDao.insertBatch(batchVO); // bid가 들어가 있는 상태
		
		logger.debug("bid : {}" , batchVO.getBid());
		
		batchVO.setSt("02");	// 배치 실행상태 : 01 -실행중
		
		/***When***/
		int updateCnt = batchDao.updateBatch(batchVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
		
	}
	
}
