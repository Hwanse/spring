package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IBoardDao;

public interface IBoardService {
	
	String sayHello();

	String getName();
	
	IBoardDao getBoardDao();
}
