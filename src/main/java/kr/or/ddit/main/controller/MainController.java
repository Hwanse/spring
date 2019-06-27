package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.main.model.MainVO;
import kr.or.ddit.user.model.UserVO;

/*
 기존의 서블릿 방식
 servlet 
  - extends HttpServlet
  - servlet-mapping (web.xml, @WebServlet)
  - service -> doXXX (doGet/doPost)
  
 스프링에서의 방식
 spring : 
  - pojo(Plain old Java Object), @Controller
  - @RequestMapping(class, method)
  - @RequestMapping에 설정된 url method 호출
 */
@Controller
@SessionAttributes("rangers")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 메소드에 적용된 @ModelAttribute
	// @RequestMapping이 붙은 메서드가 실행될때(요청이 처리될 때)
	// @ModelAttribute가 적용된 메서드가 리턴하는 값을 Model객체에 자동적으로 넣어준다
	// localhost/main ==> @RequestMapping("/main"): mainView ==> Model에는 rangers 속성이 들어가 있다
	// localhost/mainView ==> @RequestMapping("/mainMav"): mainViewMav ==> Model에는 rangers속성이 들어가 있다
	@ModelAttribute("rangers") // --> rangers라는 이름으로 attribute가 셋팅됨
	public List<String> rangers(){
		logger.debug("{}", "rangers()");
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("sally");
		rangers.add("moon");
		
		return rangers;
	}
	
	/**
	* Method : mainView
	* 작성자 : PC14
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : main 페이지 요청( viewName )
	*/
	@RequestMapping("/main")
	public String mainView(Model model, @ModelAttribute("userVO") UserVO userVO) {
		//prefix : /WEB-INF/views/ 
		//surffix : .jsp
		
		// prefix + viewName + surffix
		// ==> /WEB-INF/views/main.jsp
		logger.debug("mainView");
		logger.debug("model.asMap().get(\"rangers\") : {}", model.asMap().get("rangers"));
		
		// @ModelAttribute 메서드 방식의 2번째 방법 : 1번방식과 달리 매개변수에서 생성후 
		// 새로운 attribute를 셋팅해주는방식. 아래와 같이 풀어서 사용할수도 있음
//		UserVO userVo = new UserVO();
//		userVO.setName("brown");
//		model.addAttribute("userVO", userVo);
		logger.debug("userVO : {}", userVO);
		

		// 아래 문장은 다음과 동일하다
		// request.setAttribute("mainUserId","brown");
		model.addAttribute("mainUserId","brown");
		
		// viewName
		return "main";
	}
	
	/**
	* Method : mainViewMav
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : main 페이지 요청( ModelAndView객체 사용 )
	*/
	@RequestMapping("/mainMav")
	public ModelAndView mainViewMav(@ModelAttribute("rangers") List<String> rangers) {
		logger.debug("mainViewMav: {}", rangers);
		
		// viewName을 기반으로 ModelAndView 객체를 생성
		ModelAndView mav = new ModelAndView("main");
		
		// 위 문장은 아래 두 문장과 같다
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("main");
		
//		model.addAttribute("mainUserId","brown");
		// model의 addAttribute와 같은 의미
		mav.addObject("mainUserId", "brown");
		
		// viewName
		return mav;
	}
	
	// localhost/main/pathvariable/brown
	// localhost/main/pathvariable/sally
	/**
	* Method : pathvarivable
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : pathvariable로 부터 사용자 아이디 가져오기 (ex: 도서관 등록번호, 기업 법인코드..)
	*/
	@RequestMapping("/main/pathvariable/{userId}")
	public String pathvarivable(@PathVariable("userId")String userId) {
		logger.debug("userId : {}", userId);
		
		return "main";
	}
	
	/**
	* Method : header
	* 작성자 : PC14
	* 변경이력 :
	* @param accept
	* @return
	* Method 설명 : Accept 헤더 정보 가져오기
	*/
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name = "Accept"/* , required = false */)String accept) {
		logger.debug("Accept:{}",accept);
		return "main";
	}
	
	
	@RequestMapping("/main/view")
	public String view() {
		
		return "view";
	}
	
	
	// List<>타입의 경우 @RequestParam을 적용해야한다.
	@RequestMapping("/main/process")
	public String process(HttpServletRequest request, String[] userId, 
						@RequestParam("userId")List<String> userIdList,
						String[] name, /* @RequestParam("name")List<String> name, */
						MainVO mainVO ) {
		
		String[] userIdArr = request.getParameterValues("userId");
		
		// 1.기존의 Servlet에서 같은 name끼리의 복수 파라미터를 받아오는 법
		logger.debug("request.getParameterValues(\"userId\")");
		for(String u : userIdArr) {
			logger.debug("uesrId : {}", u);
		}

		logger.debug("String[] userId");
		// 2.spring에서 받아오는 방법: 위 adapter method에 String[]타입의 변수로 받음)
		for(String u : userId) {
			logger.debug("uesrId : {}", u);
		}
		
		logger.debug("List<String> userIdList");
		// 3.List로 받기
		for(String u : userIdList) {
			logger.debug("userId: {} ", u);
		}
		
		logger.debug("mainVO");
		for(String u : mainVO.getUserId()) {
			logger.debug("userId : {}", u);
		}
		
//		logger.debug("List<String> name");
		logger.debug("String[] name");
		for(String u: name) {
			logger.debug("name : {}", u);
		}
		
		logger.debug("mainVO : {}", mainVO);
		
		return "main";
	}
	
}
