package kr.or.ddit.prod.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdController {

	@Resource(name = "prodService")
	private IProdService prodService;
	
	
	@RequestMapping("/pagingList")
	public String pagingList(PageVO pageVO, Model model) {
		
		List<ProdVO> prodList = prodService.getAllProdList();
		
		model.addAttribute("prodList",prodList);
		model.addAttribute("pageVO", pageVO);
		
		
		return "prod/prodPagingList";
	}
	
}
