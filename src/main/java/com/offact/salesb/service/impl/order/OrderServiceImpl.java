/**
 *
 */
package com.offact.salesb.service.impl.order;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.order.OrderService;
import com.offact.salesb.vo.order.OrderVO;

/**
 * @author 4530
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<OrderVO> getOrderPageList(OrderVO order) throws BizException {
    	
        List<OrderVO> orderlist = commonDao.selectList("Order.getOrderPageList", order);

        return orderlist;
    }

    @Override
    public int getOrderCnt(OrderVO order) throws BizException {
        return commonDao.selectOne("Order.getOrderCnt", order);
    }

    @Override
    public OrderVO getOrderDetail(OrderVO order) throws BizException {
    	
    	OrderVO orderDetailVO = commonDao.selectOne("Order.getOrderDetail", order);

        return orderDetailVO;
    }

    
    @Override
    public int orderInsertProc(OrderVO order) throws BizException {

    	return commonDao.update("Order.orderInsertProc", order);

    }
    
    @Override
    public int orderUpdateProc(OrderVO order) throws BizException {

    	return commonDao.update("Order.orderUpdateProc", order);

    }
    
    @Override
    public OrderVO getOrderKey() throws BizException {
    	
    	OrderVO orderVO = commonDao.selectOne("Order.getOrderKey");

        return orderVO;
    }
}
