package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
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
//		model.addAttribute("pageVO2", new PageVO(5, 10));
//		
//		List<String> rangers = new ArrayList<String>();
//		rangers.add("brown");
//		rangers.add("sally");
//		rangers.add("james");
//		rangers.add("moon");
//		model.addAttribute("rangers", rangers);
		
		// 모델의 속성으로 셋팅된 값들을 아래와 같이 json형식 데이터로 변환 시켜 반환해줌
		/*
		 * { pageVO : {page: 5, pageSize: 10} }
		 * { pageVO2 : {page: 5, pageSize: 10} }
		 * { rangers : ["brown" , "sally" , "james" , "moon"]  }
		 */
		
		return "jsonView";
	}

	
	@RequestMapping("/requestDataResponseBody")
	@ResponseBody	// 응답 내용을 responseBody에다가 작성
	public PageVO requestDataResponseBody() {
		return new PageVO(5, 10);
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
	
	
	// requestMapping에서 옵션
	// consumes : contentType 이 아래 기술한 형식인 것만 받는다는 의미
	// produce : Accept 헤더 제한, 메서드가 생성 가능한 type (Accept 헤더를 보고 판단한다)
	@RequestMapping(path ="/requestBody", consumes = {"application/json"}
					, produces = {"application/json", "application/xml"} )
	@ResponseBody
	public UserVO requestBody(@RequestBody UserVO userVO) {
		logger.debug("userVO : {}", userVO);
		userVO.setUserId(userVO.getUserId() + "_MODIFY");
		userVO.setPass(userVO.getPass() + "_MODIFY");
		
		return userVO;
	}
	
}
