package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{

	@Resource(name="userService")
	private IuserService userService;
	
	@Test
	public void userListTest() {
		/***Given***/
		

		/***When***/
		List<UserVO> userList = userService.userList();
		
		/***Then***/
		assertNotNull(userService);
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
	}

	
	/**
	* Method : insertUserTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	*/
	@Test
	public void insertUserTest(){
		/***Given***/
		// 사용자 정보를 담고있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO=null;
		try {
			userVO = new UserVO("애옹이", "testing1", "중앙로", "test1234", "대전광역시 중구 중앙로 76"
					, "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/***When***/
		// userDao.insertUser();
		int insertRes = userService.insertUser(userVO);
		/***Then***/
		// insertCnt(1)
		assertEquals(1, insertRes);
		// data 삭제
		userService.deleteUser(userVO.getUserId());
		
	}
}
