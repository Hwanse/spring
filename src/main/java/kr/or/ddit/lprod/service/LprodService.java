package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Service
public class LprodService implements ILprodService{

	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;
	
	/**
	* Method : getAllLprodList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 리스트 조회
	*/
	@Override
	public List<LprodVO> getAllLprodList() {
		List<LprodVO> allList = lprodDao.getAllLprodList(); 
		return allList;
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
	public Map<String, Object> lprodPagingList(PageVO pageVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		List<LprodVO> pagingList = lprodDao.lprodPagingList(pageVO);
		int lprodsCnt = lprodDao.lprodsCnt();
		
		resultMap.put("lprodPagingList", pagingList);
		resultMap.put("lprodsCnt", lprodsCnt);
		
		int paginationSize = 0;
		if( lprodsCnt % pageVO.getPageSize() == 0){
			paginationSize = lprodsCnt / pageVO.getPageSize();
		} else{
			paginationSize = lprodsCnt / pageVO.getPageSize() + 1;
		}
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	/**
	* Method : getLprodInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param lprod_id
	* @return
	* Method 설명 : lprod 상세정보 조회
	*/
	@Override
	public LprodVO getLprodInfo(int lprod_id) {
		LprodVO lprodVO = lprodDao.getLprodInfo(lprod_id);
		return lprodVO;
	}
	
	
}
