package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IBoardDao;

public class BoardServiceImpl implements IBoardService{
	
	// property or field
	private IBoardDao boardDao;
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
