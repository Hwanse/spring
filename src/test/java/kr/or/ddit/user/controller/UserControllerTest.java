package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends ControllerTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	
	/**
	* Method : userListTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : userList 페이지 요청 테스트
	*/
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertNotNull(mav.getModel().get("userList"));
		assertEquals(110, ((List<UserVO>)(mav.getModelMap().get("userList"))).size() );
	}

	/**
	* Method : userPagingListWithParameterTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파라미터(page, pageSize)를 주고 사용자 페이징처리 페이지 요청 테스트
	*/
	@Test
	public void userPagingListWithParameterTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userPagingList")
				.param("page", "2").param("pageSzie", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModel().get("userList");
		PageVO pageVO = (PageVO) mav.getModel().get("pageVO");
		int paginationSize = (int) mav.getModel().get("paginationSize");
		
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(2, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파라미터(page, pageSize) 없이 사용자 페이징처리 페이지 요청 테스트
	*/
	@Test
	public void userPagingListWithOutParameterTest() throws Exception {
		/***Given***/
		

		/***When***/
		mockMvc.perform(get("/userPagingList"))
				.andExpect(view().name("user/userPagingList"))
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVO"))
				.andExpect(model().attribute("paginationSize", 11))
				.andReturn();
		
		/***Then***/

	}
	
	/**
	* Method : user
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 상세조회 페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void user() throws Exception {
		/***Given***/
		String userId = "brown";

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user").param("userId", userId)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) mav.getModel().get("SELECT_USER_INFO");
		logger.debug("userVO : {}",userVO);
		/***Then***/
		assertEquals("user/user", viewName);
		assertNotNull(userVO);
		assertEquals("브라운", userVO.getName());
	}
	
}
