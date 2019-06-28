package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodDao implements ILprodDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getAllLprodList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 리스트 조회
	*/
	@Override
	public List<LprodVO> getAllLprodList() {
		return sqlSession.selectList("lprod.getAllLprodList");
	}

	/**
	* Method : lprodsCnt
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : lprod 데이터 개수 조회
	*/
	@Override
	public int lprodsCnt() {
		return sqlSession.selectOne("lprodsCnt");
	}

	/**
	* Method : lprodPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : lprod 페이징처리 리스트 조회
	*/
	@Override
	public List<LprodVO> lprodPagingList(PageVO pageVO) {
		return sqlSession.selectList("lprod.lprodPagingList", pageVO);
	}

	/**
	* Method : getLprodInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param lprod_id
	* @return
	* Method 설명 : lprod 상세 조회
	*/
	@Override
	public LprodVO getLprodInfo(int lprod_id) {
		return sqlSession.selectOne("lprod.getLprodInfo", lprod_id);
	}

}
