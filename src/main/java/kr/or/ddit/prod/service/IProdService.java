package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

public interface IProdService {

	/**
	* Method : getProdList
	* 작성자 : PC14
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : 상품분류 코드값에 해당되는 prod리스트 조회
	*/
	public List<ProdVO> getProdList(String prod_lgu);

	
	
	/**
	* Method : getAllProdList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : prod리스트 전체 조회
	*/
	public List<ProdVO> getAllProdList();
	
	
	/**
	* Method : prodPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : prod 페이징처리 리스트 조회
	*/
	public Map<String, Object> prodPagingList(PageVO pageVO);
}
