package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodDaoTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(LprodDaoTest.class);
	
	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;
	
	
	/**
	* Method : getAllLprodList
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : lprod 전체 리스트 조회 테스트
	*/
	@Test
	public void getAllLprodListTest() {
		/***Given***/
		

		/***When***/ 
		List<LprodVO> lprodAllList = lprodDao.getAllLprodList();
		
		/***Then***/
		assertNotNull(lprodAllList);
		assertEquals(19, lprodAllList.size());
	}

	
	/**
	* Method : lprodsCntTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : lprod 데이터 갯수 조회 테스트
	*/
	@Test
	public void lprodsCntTest() {
		/***Given***/
		

		/***When***/
		int lprodCnt = lprodDao.lprodsCnt();
		
		/***Then***/
		assertEquals(19, lprodCnt);
		
	}
	
	
	/**
	* Method : lprodPagingListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : lprod 페이징 처리 리스트 조회 테스트
	*/
	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 3);

		/***When***/
		List<LprodVO> pagingList = lprodDao.lprodPagingList(pageVO);
		
		/***Then***/
		assertNotNull(pagingList);
		assertEquals(3, pagingList.size());
		assertEquals(1, pagingList.get(0).getLprod_id());
	}
	
	
	/**
	* Method : getLprodInfoTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 :  lprod 상세 조회 테스트
	*/
	@Test
	public void getLprodInfoTest() {
		/***Given***/
		int lprod_id = 3;

		/***When***/
		LprodVO lprodVO = lprodDao.getLprodInfo(lprod_id);
		
		/***Then***/
		assertNotNull(lprodVO);
		assertEquals(3, lprodVO.getLprod_id());
		assertEquals("P201", lprodVO.getLprod_gu());
		assertEquals("여성캐주얼", lprodVO.getLprod_nm());
		
	}
	
}
