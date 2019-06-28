package kr.or.ddit.main.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;



public class MainControllerTest extends ControllerTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);
	
	@Autowired
	private WebApplicationContext ctx; //webApplicationContext == spring container
	
	private MockMvc mockMvc; // mockMvc  == dispatcher servlet 

	@Before
	public void setup() {
		// 컨테이너를 받아서 mockMvc객체 생성 
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/**
	* Method : mainViewTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		// 테스팅 방법1
		// ModelAndView를 얻어와 처리하는 방식
		MvcResult mvcResult =  mockMvc.perform(get("/main")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String userId = (String) mav.getModel().get("mainUserId");
		
		/***Then***/
		assertEquals("tiles.main", viewName);
		assertEquals("brown", userId);
		assertNotNull(mav.getModel().get("rangers"));
		assertNotNull(mav.getModel().get("userVO"));
	}

	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		// 테스팅 방법2
		// MockMvcResultMatchers를 이용한 방식
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk())
				.andExpect(view().name("tiles.main"))
				.andExpect(model().attribute("mainUserId", "brown"))
				.andExpect(model().attributeExists("rangers"))
				.andExpect(model().attributeExists("userVO"));
				
				
	}
	
	
	/**
	* Method : mainViewMavTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : ModelAndView 객체를 이용한 main페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewMavTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/mainMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		// viewName이 기대하는 문자열로 리턴되는지
		assertEquals("main", mav.getViewName());
		
		logger.debug("mav:getModel()", mav.getModel());
		
		// model객체에 controller에서 설정한 속성이 있는지
		assertEquals("brown", mav.getModel().get("mainUserId"));
		assertNotNull(mav.getModel().get("rangers"));
	}
	
	
	/**
	* Method : pathvariableTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : @Pathvariable 테스트
	 * @throws Exception 
	*/
	@Test
	public void pathvariableTest() throws Exception {
		/***Given***/
		String userId = "sally";
		
		/***When***/
		mockMvc.perform(get("/main/pathvariable/"+userId))
					.andExpect(status().isOk())
					.andExpect(view().name("main"));
		
		/***Then***/
		
	}

	/**
	* Method : requestHeaderTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : @RequestHeader 테스트
	*/
	@Test
	public void requestHeaderTest() throws Exception {
		mockMvc.perform(get("/main/header").accept("text/html"))
					.andExpect(status().isOk())
					.andExpect(view().name("main"));
		
	}
	
}
