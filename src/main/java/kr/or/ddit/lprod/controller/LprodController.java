package kr.or.ddit.lprod.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVO;

@RequestMapping("/lprod")
@Controller
public class LprodController {

	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	
	@RequestMapping("/lprod")
	public String lprod(@RequestParam(name = "lprodId") int lprodId, Model model) {
		
		LprodVO lprodVO = lprodService.getLprodInfo(lprodId);
		model.addAttribute("SELECT_LPROD_INFO", lprodVO);
		
		return "lprod/lprod";
	}
	
	@RequestMapping("/list")
	public String lprodList(Model model) {
		model.addAttribute("allLprodList",lprodService.getAllLprodList());
		
		return "lprod/lprodList";
	}
	
	
	@RequestMapping("/pagingList")
	public String lprodPagingList(@RequestParam(name = "page", defaultValue = "1") int page,
								  @RequestParam(name = "pageSize", defaultValue = "3") int pageSize,
								  Model model) {
		
		PageVO pageVO = new PageVO(page, pageSize);
		
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVO);
		
		model.addAttribute("lprodPagingList", resultMap.get("lprodPagingList") );
		model.addAttribute("lprodsCnt", resultMap.get("lprodsCnt") );
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("paginationSize", resultMap.get("paginationSize"));
		
		return "lprod/lprodPagingList";
	}
	
	
}
