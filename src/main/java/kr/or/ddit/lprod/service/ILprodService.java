package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodService {

	
	/**
	 * 
	* Method : getAllLprodList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 모든 상품분류 정보를 받아오는 메서드
	 */
	public List<LprodVO> getAllLprodList();
	
	/**
	* Method : getLprodInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param lprod_id
	* @return
	* Method 설명 : 해당 lprod의 정보를 조회하기 위한 메서드
	*/
	public LprodVO getLprodInfo(int lprod_id);
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : lprod 페이징리스트 조회
	 */
	public Map<String, Object> lprodPagingList(PageVO pageVO);
	
}
