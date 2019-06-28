package kr.or.ddit.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.user.dao.IuserDao;


// application나 session에 셋팅하여 자주 사용되는 특정 데이터정보를 공유시키는데
// servlet에서 이와같은 처리를 spring에서는 intercepter로 처리하면 공통적으로 적용되는 logic을
// 효율적으로 관리할 수 있다.
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	@Resource(name = "userDao")
	private IuserDao userDao;
	
	/**
	* Method : preHandle
	* 작성자 : PC14
	* 변경이력 :
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	* Method 설명 : 로그인 한 사용자만 Controller에 접근이 가능하도록 체크
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		HttpSession session = request.getSession();
		
		// 사용자 로그인 상태
		if(session.getAttribute("USER_INFO") != null) {
			
			return true;
		} 
		// 사용자 비로그인 상태 : 로그인 페이지로 이동
		else {
			response.sendRedirect("/login");
			return false;
		}	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.debug("userDao : {}", userDao);
		request.setAttribute("userList", userDao.userList());
		
	}
	
	
	
}
