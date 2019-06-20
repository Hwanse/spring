package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringPlaceHolderTest {
	
	@Resource(name="dbInfo")
	private DbInfo dbInfo;
	
	/**
	* Method : test
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : DbInfo 클래스에 db.properties 값 읽어오기 테스팅 및
	* 				spring context:property-placeholder 실습하기
	*/
	@Test
	public void springPlaceHolderTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertNotNull(dbInfo);
		assertEquals("oracle.jdbc.driver.OracleDriver", dbInfo.getDriver());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbInfo.getUrl());
		assertEquals("pc14", dbInfo.getUsername());
		assertEquals("java", dbInfo.getPassword());
	}

}
