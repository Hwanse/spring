package kr.or.ddit.file.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class FileControllerTest extends ControllerTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(FileControllerTest.class);
	
	/**
	* Method : uploadViewTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : /uploadView 페이지 요청 테스트
	*/
	@Test
	public void uploadViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/file/uploadView")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("upload/upload", mav.getViewName());
		
	}

	
	/**
	* Method : uploadTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파일 업로드 테스트
	*/
	@Test
	public void uploadTest() throws Exception {
		/***Given***/
//		File file = new File("src/test/resources/kr/ddit/testenv/moon.png");
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/moon.png").getFile());
		
		logger.debug("file.getAbsolutePath() : {}", file.getAbsolutePath());
		logger.debug("file.getName() : {}", file.getName());

		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("img",file.getName(), "",fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform( fileUpload("/file/upload")
										.file(mockMultipartFile)
										.file(mockMultipartFile) )
										.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("upload/upload", viewName);
		assertEquals("SUCCESS", msg);
	}

	@Ignore
	@Test
	public void test() {
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/moon.png").getFile());
		
		logger.debug("file.getName() : {}", file.getName());
		logger.debug("file.getAbsolutePath() : {}", file.getAbsolutePath());
	}
	
}
