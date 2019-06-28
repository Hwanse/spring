package kr.or.ddit.lprod.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv{

	
	/**
	* Method : lprodTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : lprod 상세조회 페이지 요청 테스트
	*/
	@Test
	public void lprodTest() throws Exception {
		/***Given***/
		LprodVO lprodVO = new LprodVO(1, "P101", "컴퓨터제품");

		/***When***/
		mockMvc.perform(get("/lprod/lprod").param("lprodId", "1"))
							.andExpect(view().name("lprod/lprod"))
							.andExpect(model().attributeExists("SELECT_LPROD_INFO"))
							.andExpect(model().attribute("SELECT_LPROD_INFO", lprodVO));
		
		/***Then***/

		
	}
	
	
	/**
	* Method : lprodListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : lprod 전체 조회 페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void lprodListTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<LprodVO> allLprodList = (List<LprodVO>) mav.getModel().get("allLprodList");
		
		/***Then***/
		assertEquals("lprod/lprodList", viewName);
		assertNotNull(allLprodList);
		assertEquals(19, allLprodList.size());
		
	}

	
	@Test
	public void lprodPagingListTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<LprodVO> pagingList = (List<LprodVO>) mav.getModel().get("lprodPagingList");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		
		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertNotNull(pagingList);
		assertEquals(paginationSize, paginationSize);
		
	}
}
