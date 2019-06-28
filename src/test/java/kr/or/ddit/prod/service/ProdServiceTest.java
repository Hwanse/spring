package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceTest extends LogicTestEnv{

	@Resource(name = "prodService")
	private IProdService prodService;
	
	@Test
	public void getProdListTest() {
		/***Given***/
		

		/***When***/
		List<ProdVO> prodList = prodService.getProdList("P201");
		
		/***Then***/
		assertNotNull(prodList);
		assertEquals(21, prodList.size());
		
	}

}
