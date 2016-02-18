/**
 * 
 */
package com.offact.salesb.service.manage;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.manage.CustManageVO;

/**
 * @author 4530
 *
 */
public interface CustManageService {
	 /**
     * 고객 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<CustManageVO> getCustList(CustManageVO cust) throws BizException;

    /**
     * 고객 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCustCnt(CustManageVO cust) throws BizException;
    
    /**
     * 고객상세조회
     * 
     * @param custId
     * @return
     * @throws BizException
     */
    public CustManageVO getCustDetail(String custId) throws BizException;
    
    /**
     * 고객 삭제
     * 
     * @param arrDelCustId
     * @return
     * @throws BizException
     */
    public int custDeleteProc(String updateCustId ,String arrDelCustId) throws BizException;
    
}
