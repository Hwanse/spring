package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVO;

@Service
public class ProdService implements IProdService {

	@Resource(name = "prodDao")
	private IProdDao prodDao;
	
	
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
		List<ProdVO> prodList = prodDao.getProdList(prod_lgu); 
		return prodList;
	}


	/**
	* Method : getAllProdList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : prod리스트 전체 조회
	*/
	@Override
	public List<ProdVO> getAllProdList() {
		return prodDao.getAllProdList();
	}


	@Override
	public Map<String, Object> prodPagingList(PageVO pageVO) {
		List<ProdVO> pagingList = prodDao.prodPagingList(pageVO);
		int prodCnt = prodDao.prodCnt();
			
		if(prodCnt % pageVO.getPageSize() == 0) {
		}
			
		return null;
	}

}
