package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml")
public class SpringIocStTest {
	
	@Resource(name="bDao")
	private IBoardDao boardDao;
	
	@Resource(name="bService")
	private IBoardService boardService;
	
	/**
	* Method : springIocStTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : boardService객체의 boardDao 속성과 spring컨테이너로부터 받은 boardDao가
	* 				같은 객체인지 확인
	*/
	@Test
	public void springIocStTest() {
		/***Given***/
		

		/***When***/
		
		
		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
		
	}

}
