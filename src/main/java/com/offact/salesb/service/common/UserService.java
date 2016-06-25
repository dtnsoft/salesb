/**
 * 
 */
package com.offact.salesb.service.common;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.common.UserVO;

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
     * 사용자 상태저장
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiLoginYnUpdate(UserVO user)
    	    throws BizException;
    
	/**
     * 사용자 사용여부저장
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiUseYnUpdate(UserVO user)
    	    throws BizException;
    
	
}
