package kr.or.ddit.batch.dao;

public interface IBatchDao {
	
	/**
	* Method : deleteDaily
	* 작성자 : PC14
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 삭제
	*/
	public int deleteDaily(String ym);
	
	
	/**
	* Method : createDaily
	* 작성자 : PC14
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 생성
	*/
	public int createDaily(String ym);
	
}
