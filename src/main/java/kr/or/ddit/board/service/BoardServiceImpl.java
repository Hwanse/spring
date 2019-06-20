package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IBoardDao;

public class BoardServiceImpl implements IBoardService{
	
	// property or field
	private IBoardDao boardDao;

	
	public BoardServiceImpl(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public BoardServiceImpl() {

	}
	
	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}

	public IBoardDao getBoardDao() {
		return boardDao;
	}

	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	
}
