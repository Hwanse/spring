package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IuserService{
	
	@Resource(name="userDao")
	private IuserDao userDao;
	
	/**
	* Method : userList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}

	/**
	* Method : insertUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVO) {
		return userDao.insertUser(userVO);
	}

	/**
	* Method : deleteUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	/**
	* Method : getUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
	}

	/**
	* 
	* Method : userPagingListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 페이징 처리 리스트 조회 테스트
	*/
	public Map<String, Object> userPagingList(PageVO pageVO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<UserVO> userList = userDao.userPagingList(pageVO);
		int usersCnt = userDao.usersCnt();
		resultMap.put("userList", userList);
		resultMap.put("usersCnt", usersCnt);
		
		int paginationSize = 0;
		if(usersCnt % pageVO.getPageSize() == 0){
			paginationSize = usersCnt / pageVO.getPageSize();
		} else{
			paginationSize = usersCnt / pageVO.getPageSize() + 1;
		}
		
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}


	/**
	* Method : updateUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 수정
	*/
	@Override
	public int updateUser(UserVO userVO) {
		return userDao.updateUser(userVO);
	}
	
}
