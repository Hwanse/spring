package kr.or.ddit.batch.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.batch.model.BatchVO;

@Repository
public class BatchDao implements IBatchDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	/**
	* Method : deleteDaily
	* 작성자 : PC14
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 삭제
	*/
	@Override
	public int deleteDaily(String ym) {
		return sqlSession.delete("batch.deleteDaily", ym);
	}

	/**
	* Method : createDaily
	* 작성자 : PC14
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 생성
	*/
	@Override
	public int createDaily(String ym) {
		return sqlSession.insert("batch.createDaily", ym);
	}

	/**
	* Method : insertBatch
	* 작성자 : PC14
	* 변경이력 :
	* @param batchVO
	* @return
	* Method 설명 : 배치정보 입력
	*/
	@Override
	public int insertBatch(BatchVO batchVO) {
		return sqlSession.insert("batch.insertBatch", batchVO);
	}

	@Override
	public int updateBatch(BatchVO batchVO) {
		return sqlSession.update("batch.updateBatch", batchVO);
	}
	
	
}
