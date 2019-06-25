package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IuserDao {
	
	
	/**
	* Method : userList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	List<UserVO> userList();


	/**
	* Method : insertUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVO userVO);
	
	
	/**
	* Method : deleteUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(String userId);
	
	
	/**
	* Method : getUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	UserVO getUser(String userId);
	
	
	/**
	* 
	* Method : userPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	public List<UserVO> userPagingList(PageVO pageVO);

	
	/**
	* 
	* Method : userCnt
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 수 조회
	*/
	int usersCnt();

	
	/**
	* Method : updateUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	int updateUser(UserVO userVO);
}
