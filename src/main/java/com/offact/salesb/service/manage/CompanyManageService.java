/**
 *
 */
package com.offact.salesb.service.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.manage.CompanyManageVO;

/**
 * @author
 */
public interface CompanyManageService {
    /**
     * 업체 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<CompanyManageVO> getCompanyPageList(CompanyManageVO user) throws BizException;

    /**
     * 업체 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCompanyCnt(CompanyManageVO user) throws BizException;

    /**
     * 업체정보 일괄업로드
     * 
     * @param UserManageVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<CompanyManageVO> paramList)
    	    throws BizException;

}
