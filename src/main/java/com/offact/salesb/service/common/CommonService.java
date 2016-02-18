/**
 *
 */
package com.offact.salesb.service.common;

import java.util.List;
import java.util.Map;

import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.common.CodeVO;
import com.offact.salesb.vo.common.CompanyVO;
import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.common.GroupVO;

/**
 * @author 4530
 */
public interface CommonService {
    
	/**
     * 코드 목록
     *
     * @return
     * @throws BizException
     */
    public List<CodeVO> getCodeComboList(CodeVO code) throws BizException;
    
    /**
     * 코드 목록
     *
     * @return
     * @throws BizException
     */
    public List<CodeVO> getWorkCodeList(CodeVO code) throws BizException;

    /**
     * 그룹 목록
     *
     * @return
     * @throws BizException
     */
    public List<GroupVO> getGroupComboList(GroupVO group) throws BizException;
    
    
    /**
     * 업체정보
     *
     * @return
     * @throws BizException
     */
    public CompanyVO getCompanyDetail(CompanyVO company) throws BizException;
    
    /**
     * 코드 목록
     *
     * @return
     * @throws BizException
     */
    public List<CompanyVO> getCompanyList(CompanyVO company) throws BizException;

    /**
     * 업무이력저장
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiHistoryInsert(WorkVO work)
    	    throws BizException;
    /**
     * 업무이력저장
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public UserVO getEncPassword(UserVO user)
    	    throws BizException;

}
