package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

// **실무에서 자주 사용하는 형태
// spring 컨테이너와 junit테스트를 동시에 처리하기위한 방식
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocTest {
	
	// field기준 boardService, boardService2  : spring boardSErvice bean (scope=singleton)
	//			 boardServiceConstructor : spring boardServiceConstructor bean(scope=singleton)
	
	//1. boardService, boardService2 : 같아야 한다
	//2. boardService, boardSErviceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton
	//	  개념에 따라 다른객체
	//3. boardService2, boardServiceConstructor : 같은 크래래스에서 만들어진 객체이지만 spring singleton
	//    개념에 따라 다른 객체
	
	// bean을 주입처리하는 어노테이션 : @Resource
	// boardDao : spring boardDao(scope=singleton)
	// boardDaoPrototype : spring boardDaoPrototype(scope=prototype)
	// boardDaoPrototype2 : spring boardDaoPrototype(scope=prototype)
	// 1. boardDao boardDaoPrototype : spring bean id가 다르므르로 다른객체
	// 2. boardDaoPrototype, boardPrototype2 : 두 객체는 같은 스프링 bean이지만
	//										 : scope가 prototype이므로 다른 객체이어야한다
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardDaoPrototype")
	private IBoardDao boardDaoPrototype;
	
	@Resource(name="boardDaoPrototype")
	private IBoardDao boardDaoPrototype2;

	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Resource(name="boardService")
	private IBoardService boardService2;
	
	@Resource(name="boardServiceConstructor")
	private IBoardService boardServiceConstructor;
	
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
	
	/**
	* Method : springSingletonScopeTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너의 singleton scope 테스트
	*/
	@Test
	public void springSingletonScopeTest() {
		/***Given***/
		//1. boardService, boardService2 : 같아야 한다
		//2. boardService, boardSErviceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton
		//	  개념에 따라 다른객체
		//3. boardService2, boardServiceConstructor : 같은 크래래스에서 만들어진 객체이지만 spring singleton
		//    개념에 따라 다른 객체

		/***When***/

		
		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		assertEquals(boardService, boardService2);
		assertNotEquals(boardService, boardServiceConstructor);
		assertNotEquals(boardService2, boardServiceConstructor);
	}

	
	@Test
	public void springPrototypeScopeTest() {
		/***Given***/
		

		/***When***/

		
		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
	}
	
}
