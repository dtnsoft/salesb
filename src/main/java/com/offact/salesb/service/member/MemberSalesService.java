/**
 *
 */
package com.offact.salesb.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.member.MemberSalesVO;

/**
 * @author
 */
public interface MemberSalesService {
    /**
     * 판매 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<MemberSalesVO> getMemberSalesPageList(MemberSalesVO token) throws BizException;

    /**
     * 판매 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getMemberSalesCnt(MemberSalesVO token) throws BizException;

    /**
     * 토큰발급현황
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public MemberSalesVO getTokenState(MemberSalesVO token) throws BizException;
    
    /**
     * 주문/판매 현황
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public MemberSalesVO getOrderState(MemberSalesVO token) throws BizException;
    
   
}
