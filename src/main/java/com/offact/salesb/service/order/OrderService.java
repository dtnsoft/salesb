/**
 *
 */
package com.offact.salesb.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.order.OrderVO;

/**
 * @author
 */
public interface OrderService {
    /**
     * Order 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OrderVO> getOrderPageList(OrderVO order) throws BizException;

    /**
     * Order 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getOrderCnt(OrderVO order) throws BizException;

    /**
     * Order 상세조회
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public OrderVO getOrderDetail(OrderVO order) throws BizException;
    
    /**
     * Order 저장
     * 
     * @return
     * @throws BizException
     */
    public int orderInsertProc(OrderVO order) throws BizException;
    
    /**
     * Order 업데이트
     * 
     * @return
     * @throws BizException
     */
    public int orderUpdateProc(OrderVO order) throws BizException;
    
    /**
     * Orderkey
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public OrderVO getOrderKey() throws BizException;

}
