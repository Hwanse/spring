package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdDao implements IProdDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getProdList
	* 작성자 : PC14
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : 상품분류 코드값에 해당되는 prod리스트 조회
	*/
	@Override
	public List<ProdVO> getProdList(String prod_lgu) {
		return sqlSession.selectList("prod.getProdList", prod_lgu);
	}
	
	
	/**
	* Method : getAllProdList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : prod리스트 전체 조회
	*/
	public List<ProdVO> getAllProdList(){
		return sqlSession.selectList("prod.getAllProdList");
	}

	
	public List<ProdVO> prodPagingList(PageVO pageVO){
		return sqlSession.selectList("prod.prodPagingList", pageVO);
	}


	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}
}
