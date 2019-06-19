package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.service.IBoardService;

// **실무에서 자주 사용하는 형태
// spring 컨테이너와 junit테스트를 동시에 처리하기위한 방식
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocTest {
	
	// bean을 주입처리하는 어노테이션
	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	* Method : springIocTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		/***Given***/
		
		/***When***/
		String msg =  boardService.sayHello();

		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
		
	}

}
