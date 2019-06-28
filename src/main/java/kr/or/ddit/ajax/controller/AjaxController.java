package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {

	@Resource(name = "userService")
	private IuserService userService;
	
	/**
	* Method : view
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : ajax호출용 View
	*/
	@RequestMapping("/view")
	public String view() {
		
		return "tiles.ajaxView";
	}
	
	
	/**
	* Method : requestData
	* 작성자 : PC14
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 요청을 받아 json형태로 데이터를 반환해줌
	*/
	@RequestMapping("/requestData")
	public String requestData(Model model) {
		
		model.addAttribute("pageVO", new PageVO(5, 10));
		model.addAttribute("pageVO2", new PageVO(5, 10));
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
		model.addAttribute("rangers", rangers);
		
		// 모델의 속성으로 셋팅된 값들을 아래와 같이 json형식 데이터로 변환 시켜 반환해줌
		/*
		 * { pageVO : {page: 5, pageSize: 10} }
		 * { pageVO2 : {page: 5, pageSize: 10} }
		 * { rangers : ["brown" , "sally" , "james" , "moon"]  }
		 */
		
		return "jsonView";
	}

	
	/**
	* Method : user
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : user정보를 json형태로 반환
	*/
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "jsonView";
	}

	
	/**
	* Method : userHtml
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : user정보를 html 형태로 반환
	*/
	@RequestMapping("/userHtml")
	public String userHtml(String userId, Model model) {
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "user/userHtml";
	}
	
	
	
}
