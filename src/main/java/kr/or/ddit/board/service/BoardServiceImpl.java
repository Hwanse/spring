package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;

@Service
public class BoardServiceImpl implements IBoardService{
	
	// property or field
	
	@Resource(name="boardDao")
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
