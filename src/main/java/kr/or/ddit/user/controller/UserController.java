package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private IuserService userService;

	/**
	 * Method : userList 작성자 : PC14 변경이력 :
	 * 
	 * @param model
	 * @return Method 설명 : 사용자 전체 리스트 페이지
	 */
	@RequestMapping("/userList")
	public String userList(Model model) {
		model.addAttribute("userList", userService.userList());

		return "user/userList";
	}

	@RequestMapping("/userPagingList")
//	방법1
//	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int page,
//								 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
//								 Model model) {
//		logger.debug("page : {}", pageVO.getPage());
//		logger.debug("pageSize: {}", pageVO.getPageSize());
//		PageVO pageVO = new PageVO(page, pageSize);
	// 방법2
	public String userPagingList(PageVO pageVO, Model model) {
		logger.debug("pageVO : {}", pageVO);
		
		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		List<UserVO> userList = (List<UserVO>)resultMap.get("userList");
		int paginationSize = (int)resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "user/userPagingList";
	}

	@RequestMapping("/user")
	public String user(@RequestParam("userId") String userId, Model model) {
		UserVO userVO = userService.getUser(userId);
		
		model.addAttribute("SELECT_USER_INFO",userVO);
		return "user/user";
	}

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * // request 객체로부터 사용자 아이디 파라미터 획득 String userId =
	 * request.getParameter("userId");
	 * 
	 * // 사용자 아이디로 사용자 정보를 조회 UserVO userVO = userService.getUser(userId);
	 * 
	 * // 조회 결과를 request객체에 속성으로 저장 request.setAttribute("SELECT_USER_INFO",
	 * userVO);
	 * 
	 * // 화면을 담당하는 /user/user.jsp로 forward
	 * request.getRequestDispatcher("/user/user.jsp").forward(request, response);
	 * 
	 * }
	 */

}
