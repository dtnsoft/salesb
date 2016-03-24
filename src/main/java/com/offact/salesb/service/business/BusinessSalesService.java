/**
 *
 */
package com.offact.salesb.service.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.business.BusinessSalesVO;

/**
 * @author
 */
public interface BusinessSalesService {
    /**
     * 판매 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<BusinessSalesVO> getBusinessSalesPageList(BusinessSalesVO sales) throws BizException;

    /**
     * 판매 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getBusinessSalesCnt(BusinessSalesVO sales) throws BizException;

    /**
     * 토큰발급현황
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public BusinessSalesVO getTokenState(BusinessSalesVO sales) throws BizException;
    
    /**
     * 주문/판매 현황
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public BusinessSalesVO getOrderState(BusinessSalesVO sales) throws BizException;
    
   
}
