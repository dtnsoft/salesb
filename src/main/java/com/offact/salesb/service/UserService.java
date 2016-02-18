/**
 * 
 */
package com.offact.salesb.service;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.UserConditionVO;
import com.offact.salesb.vo.UserMenuVO;
import com.offact.salesb.vo.UserVO;

/**
 * @author 4530
 *
 */
public interface UserService {
	/**
	 * 사용자 조회
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public UserVO getUser(UserVO user) throws BizException;
	
	/**
	 * 사용자 수 조회
	 * @return
	 * @throws BizException
	 */
	public int getUserCnt() throws BizException;

	/**
	 * 사용자 리스트 조회
	 * @param userConVo
	 * @return
	 * @throws BizException
	 */
	public List<UserVO> getUserList(UserConditionVO userConVO) throws BizException;
	
	/**
	 * 사용자 등록처리
	 * @param userConVO
	 * @return
	 * @throws BizException
	 */
	public void inserUserProc(UserVO userVO) throws BizException;
	
}
