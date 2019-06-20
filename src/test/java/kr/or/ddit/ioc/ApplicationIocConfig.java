package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardServiceImpl;

@Configuration
public class ApplicationIocConfig {
	
	
	//<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDaoImpl"/>
	@Bean
	public IBoardDao boardDao() {
		// 리턴문에 new 생성자를 호출했기때문에 매번 새로운 객체를 생성할 것 같으나
		// 스프링에서 자동으로 제어하여 객체가 여러번 생성되지 않는다.
		return new BoardDaoImpl();
	}
	
	/*
	 * <bean id="boardService" class="kr.or.ddit.board.service.BoardServiceImpl">
	 * <property name="boardDao" ref="boardDao"></property> </bean>
	 */
	
	@Bean
	public BoardServiceImpl boardService() {
		BoardServiceImpl boardService = new BoardServiceImpl();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao());
		return boardService;
	}
	
}
