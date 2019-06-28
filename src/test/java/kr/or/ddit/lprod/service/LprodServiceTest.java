package kr.or.ddit.lprod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodServiceTest extends LogicTestEnv{

	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
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
		List<LprodVO> lprodAllList = lprodService.getAllLprodList();
		
		/***Then***/
		assertNotNull(lprodAllList);
		assertEquals(19, lprodAllList.size());
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
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVO);
		List<LprodVO> lprodPagingList = (List<LprodVO>) resultMap.get("lprodPagingList");
		int lprodCnt = (int) resultMap.get("lprodsCnt");
		int paginationSize = (int) resultMap.get("paginationSize");
		/***Then***/
		assertNotNull(lprodPagingList);
		assertEquals(3, lprodPagingList.size());
		assertEquals(19, lprodCnt);
		assertEquals(7, paginationSize);
		
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
		LprodVO lprodVO = lprodService.getLprodInfo(lprod_id);
		
		/***Then***/
		assertNotNull(lprodVO);
		assertEquals(3, lprodVO.getLprod_id());
		assertEquals("P201", lprodVO.getLprod_gu());
		assertEquals("여성캐주얼", lprodVO.getLprod_nm());
		
	}
}
