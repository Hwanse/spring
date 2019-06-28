package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;

public class ProfileView implements View{

	private static final Logger logger = LoggerFactory.getLogger(ProfileView.class);
	
	@Override
	public String getContentType() {

		return "img";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.debug("profileView.render()");
		
		UserVO userVO = (UserVO) model.get("userVO");
		
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
	
	
}
