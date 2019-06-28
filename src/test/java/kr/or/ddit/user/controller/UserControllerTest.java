package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
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
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertNotNull(mav.getModel().get("userList"));
		assertEquals(109, ((List<UserVO>)(mav.getModelMap().get("userList"))).size() );
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
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")
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
//		mockMvc.perform(get("/userPagingList"))
//				.andExpect(view().name("user/userPagingList"))
//				.andExpect(model().attributeExists("userList"))
//				.andExpect(model().attributeExists("pageVO"))
//				.andExpect(model().attribute("paginationSize", 11))
//				.andReturn();
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModel().get("userList");
		PageVO pageVO = (PageVO) mav.getModel().get("pageVO");
		int paginationSize = (int) mav.getModel().get("paginationSize");
		
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(new PageVO(1, 10), pageVO);
		assertEquals(11, paginationSize);
		
	}
	
	/**
	* Method : user
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 상세조회 페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void userTest() throws Exception {
		/***Given***/
		String userId = "brown";

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", userId)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) mav.getModel().get("SELECT_USER_INFO");
		logger.debug("userVO : {}",userVO);
		/***Then***/
		assertEquals("user/user", viewName);
		assertNotNull(userVO);
		assertEquals("브라운", userVO.getName());
	}
	
	
	/**
	* Method : userForm
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 유저 상세조회 페이지 요청 테스트
	*/
	@Test
	public void userFormGetTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/form")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("user/userForm", viewName);
	}
	
	
	/**
	* Method : userForm
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 유저등록 페이지 요청 테스트
	*/
	@Test
	public void userForm() throws Exception {
		/***Given***/
		

		/***When***/
		mockMvc.perform(get("/user/form"))
				.andExpect(view().name("user/userForm")).andReturn();
		
		/***Then***/

	}
	
	
	/**
	* Method : userFormPostSuccessTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 성공 테스트
	*/
	@Test
	public void userFormPostSuccessTest() throws Exception {
		/***Given***/
		String userId = "userTest";
		String name = "유저테스트";
		String alias = "대덕인";
		String addr1 = "대전광역시 중구 중앙로 76";
		String addr2 = "영민빌딩 2층 204호";
		String zipcd = "34940";
		String birth = "2019-06-26";
		String pass = "userTest1234";
		
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/moon.png").getFile());
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("profile",file.getName(), "",fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform( fileUpload("/user/form")
										.file(mockMultipartFile)
										.param("userId",userId)
										.param("name",name)
										.param("alias",alias)
										.param("addr1",addr1)
										.param("addr2",addr2)
										.param("zipcd",zipcd)
										.param("birth",birth)
										.param("pass",pass)
										).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("redirect:/user/pagingList", viewName);
		
	}
	
	
	/**
	* Method : userFormFailTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 실패처리 테스트
	*/
	@Test
	public void userFormFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String name = "실패테스트";
		String alias = "대덕인";
		String addr1 = "대전광역시 중구 중앙로 76";
		String addr2 = "영민빌딩 2층 204호";
		String zipcd = "34940";
		String birth = "2019-06-26";
		String pass = "userTest1234";
		
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/moon.png").getFile());
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("profile",file.getName(), "",fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform( fileUpload("/user/form")
										.file(mockMultipartFile)
										.param("userId",userId)
										.param("name",name)
										.param("alias",alias)
										.param("addr1",addr1)
										.param("addr2",addr2)
										.param("zipcd",zipcd)
										.param("birth",birth)
										.param("pass",pass)
										).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("user/userForm", viewName);
		assertEquals("이미 존재하는 사용자입니다.", msg);
		
	}
	
	
	/**
	* Method : profileTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 프로필 사진 응답 테스트
	 * @throws Exception 
	*/
	@Test
	public void profileTest() throws Exception {
		
		// 요청 파라미터도 반환하는 모델or뷰 객체가 없기 때문에
		// 응답 상태코드로 테스팅
		mockMvc.perform(get("/user/profile").param("userId", "brown"))
							.andExpect(status().isOk());
		
	}
	
	
	/**
	* Method : userModifyGetTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 수정화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void userModifyGetTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO)mav.getModel().get("userVO");
		
		/***Then***/
		assertEquals("user/userModify", viewName);
		assertNotNull(userVO);
		
	}
	
	
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		String userId = "userTest2";
		String name = "유저테스트";
		String alias = "대덕인";
		String addr1 = "대전광역시 중구 중앙로 76";
		String addr2 = "영민빌딩 2층 204호";
		String zipcd = "34940";
		String birth = "2019-06-26";
//		String pass = "userTest1234";
		
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/moon.png").getFile());
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("profile",file.getName(), "",fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform( fileUpload("/user/modify")
										.file(mockMultipartFile)
										.param("userId",userId)
										.param("name",name)
										.param("alias",alias)
										.param("addr1",addr1)
										.param("addr2",addr2)
										.param("zipcd",zipcd)
										.param("birth",birth)
										).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		

		/***Then***/
		assertEquals("redirect:/user/user?userId=userTest2", viewName);
		
	}
	
}
