package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {

	@Resource(name="formattingVo")
	private FormattingVo formatVo;
	
	/**
	* Method : applicationTypeFormatTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : Formatting Conversion Service 테스팅
	*/
	@Test
	public void applicationTypeFormatTest() {
		/***Given***/
		

		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String rdt = sdf.format(formatVo.getReg_dt());
		String mdt = sdf.format(formatVo.getMod_dt());
		
		/***Then***/
		assertNotNull(formatVo);
		assertEquals(formatVo.getReg_dt(), formatVo.getMod_dt());
		assertEquals("2019-06-21", rdt);
		assertEquals("2019-06-21", mdt);
		assertEquals(6000, formatVo.getNumber());	// "6,000" => 6000
	}

}
