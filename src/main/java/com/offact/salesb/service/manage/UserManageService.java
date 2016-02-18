/**
 *
 */
package com.offact.salesb.service.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.manage.UserManageVO;

/**
 * @author
 */
public interface UserManageService {
    /**
     * 사용자 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<UserManageVO> getUserList(UserManageVO user) throws BizException;

    /**
     * 사용자 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getUserCnt(UserManageVO user) throws BizException;

    /**
     * 사용자 상세정보 수정
     * 
     * @return
     * @throws BizException
     */
    public int userUpdateProc(UserManageVO userDetail) throws BizException;

    /**
     * 사용자 상세정보 저장
     * 
     * @return
     * @throws BizException
     */
    public int userInsertProc(UserManageVO userDetail) throws BizException;

    /**
     * 사용자상세조회
     * 
     * @param userId
     * @return
     * @throws BizException
     */
    public UserManageVO getUserDetail(String userId) throws BizException;
    
    /**
     * 사용자 삭제
     * 
     * @param arrDelUserId
     * @return
     * @throws BizException
     */
    public int userDeleteProc(String updateUserId ,String arrDelUserId) throws BizException;
   
    /**
     * 사용자 업로드
     * 
     * @param UserManageVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<UserManageVO> paramList)
    	    throws BizException;
    
    /**
     * 중복 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCheckCnt(UserManageVO user) throws BizException;


}
