package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdDaoTest extends LogicTestEnv{

	@Resource(name = "prodDao")
	private ProdDao prodDao;
	
	@Test
	public void getProdTest() {
		/***Given***/
		

		/***When***/
		List<ProdVO> prodList = prodDao.getProdList("P201");
		
		/***Then***/
		assertNotNull(prodList);
		assertEquals( 21, prodList.size());
		
	}

	@Test
	public void getAllProdListTest() {
		/***Given***/
		

		/***When***/
		List<ProdVO> allList = prodDao.getAllProdList();
		
		/***Then***/
		assertNotNull(allList);
		assertEquals(74, allList.size());
			
	}
	
	@Test
	public void prodPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 10); 

		/***When***/
		List<ProdVO> pagingList = prodDao.prodPagingList(pageVO);
		
		/***Then***/
		assertNotNull(pagingList);
		assertEquals(10, pagingList.size());
		
	}
	
}
