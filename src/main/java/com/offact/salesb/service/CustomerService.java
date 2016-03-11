/**
 * 
 */
package com.offact.salesb.service;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.CustomerVO;

/**
 * @author 4530
 *
 */
public interface CustomerService {
	/**
	 * 고객 조회
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public CustomerVO getCustomer(CustomerVO customer) throws BizException;
    /**
     * 고객패스워드 조회
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public CustomerVO getEncPassword(CustomerVO customer)
    	    throws BizException;
    /**
     * 고객 상세정보 수정
     * 
     * @return
     * @throws BizException
     */
    public int customerUpdateProc(CustomerVO customer) throws BizException;
    
    /**
     * 고객 등록
     * 
     * @return
     * @throws BizException
     */
    public int customerRegist(CustomerVO customer) throws BizException;
    
    /**
     * 인증번호 업데이트
     * 
     * @return
     * @throws BizException
     */
    public int customerUpdateToken(CustomerVO customer) throws BizException;
    
    /**
	 * Token 조회
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public CustomerVO getTokenInfo(CustomerVO customer) throws BizException;
	
	/**
	 * 코객키값 조회
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public CustomerVO getCustKeyInfo(CustomerVO customer) throws BizException;
    
	/**
	 * 고객등록
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public int customerInsert(CustomerVO customer) throws BizException;    
}
