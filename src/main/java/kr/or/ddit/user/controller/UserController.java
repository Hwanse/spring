package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encryt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private IuserService userService;

	/**
	 * Method : userList 작성자 : PC14 변경이력 :
	 * 
	 * @param model
	 * @return Method 설명 : 사용자 전체 리스트 페이지 요청
	 */
	@RequestMapping("/list")
	public String userList(Model model) {
		model.addAttribute("userList", userService.userList());

		return "user/userList";
	}

	/**
	* Method : userPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @param model
	* @return
	* Method 설명 : 사용자 페이징처리 페이지 요청
	*/
	@RequestMapping("/pagingList")
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

	
	/**
	* Method : user
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세조회 페이지 요청
	*/
	@RequestMapping("/user")
//	public String user(@RequestParam("userId") String userId, Model model) {
	public String user(String userId, Model model) {
		UserVO userVO = userService.getUser(userId);
	
		model.addAttribute("SELECT_USER_INFO",userVO);
		return "user/user";

	}
	
	/**
	* Method : userForm
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 페이지 요청
	*/
	@RequestMapping(path = "/form", method = RequestMethod.GET )
	public String userForm() {
		
		return "user/userForm";
	}
	

	// 사용자 등록
	/**
	* Method : userForm
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	*/
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVO userVO, String userId, /* @RequestPart("profile") */MultipartFile profile
							, Model model) {
		String viewName = "";
		UserVO dbUser = userService.getUser(userId);
		
		if(dbUser == null) {
			
			if(profile.getSize() > 0) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString()+ ext;
				userVO.setPath(filePath);
				userVO.setFilename(fileName);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
			}
			
			userVO.setPass( KISA_SHA256.encrypt(userVO.getPass()) );
			int insertCnt = userService.insertUser(userVO);

			// 정상등록 된 경우
			if(insertCnt == 1) viewName = "redirect:/user/pagingList";
			
		} else {
			model.addAttribute("msg", "이미 존재하는 사용자입니다.");
			
			// 등록이 안될경우 등록페이지 재요청
			viewName = userForm();
		}
		
		return viewName;
	}

	
	
	/**
	* Method : profile
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @param response
	* @param request
	* @throws IOException
	* Method 설명 : 사용자 프로필 사진 응답 생성
	*/
	@RequestMapping("/profile")
	public void profile(String userId
			, HttpServletResponse response, HttpServletRequest request) throws IOException{
		
		// 사용자 정보(path)를 조회
		UserVO userVO = userService.getUser(userId);
		
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = null;
		String filePath = null;
		
		if(userVO.getPath() != null){
			filePath = userVO.getPath();
		} else{
			filePath = request.getServletContext().getRealPath("/img/no_image.gif");
		}

		File file = new File(filePath);
		fis = new FileInputStream(file);

		byte[] buffer = new byte[512];
		// response객체에 스트림으로 써준다
		while( fis.read(buffer, 0, buffer.length) != -1){
			sos.write(buffer);
		}
		fis.close();
		sos.close();
	
	}
	
	
	/**
	* Method : userModify
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정화면 요청
	*/
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
	
		return "user/userModify";
	}
	
	
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVO userVO, MultipartFile profile, HttpSession session
							, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		logger.debug("userVO: {}", userVO);
		// 추후 ajax요청으로 분리 계획
//		userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
		
		if(profile.getSize() > 0) {
			String fileName = profile.getOriginalFilename();
			String ext = PartUtil.getExt(fileName);
			
			String uploadPath = PartUtil.getUploadPath();
			
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString()+ ext;
			
			userVO.setPath(filePath);
			userVO.setFilename(fileName);
			
			profile.transferTo(new File(filePath));
		}
		int updateCnt = userService.updateUser(userVO);
		
		if(updateCnt == 1) {
			//session.setAttribute("msg", "등록되었습니다");
			
			// addFlashAttribute 최초 한번의 요청에 대해서 속성을 추가해주고 삭제
			// 이후 재요청이 들어오면 속성을 셋팅이 안되어있음
			redirectAttributes.addFlashAttribute("msg", "등록되었습니다");
			// redirect시 url에 파라미터를 자동으로 추가
			redirectAttributes.addAttribute("userId", userVO.getUserId()); 
			return "redirect:/user/user?userId="+userVO.getUserId();
		} else {
			return userModify(userVO.getUserId(), model);
		}
		
	}
	
}
