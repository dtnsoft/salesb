/**
 *
 */
package com.offact.salesb.service.impl.business;

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
import com.offact.salesb.service.business.BusinessSalesService;
import com.offact.salesb.vo.business.BusinessSalesVO;

/**
 * @author 4530
 */
@Service
public class BusinessSalesServiceImpl implements BusinessSalesService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<BusinessSalesVO> getBusinessSalesPageList(BusinessSalesVO sales) throws BizException {
    	
        List<BusinessSalesVO> saleslist = commonDao.selectList("BusinessSales.getBusinessSalesPageList", sales);

        return saleslist;
    }

    @Override
    public int getBusinessSalesCnt(BusinessSalesVO sales) throws BizException {
        return commonDao.selectOne("BusinessSales.getBusinessSalesCnt", sales);
    }

    @Override
    public BusinessSalesVO getTokenState(BusinessSalesVO sales) throws BizException {
    	
    	BusinessSalesVO salesDetailVO = commonDao.selectOne("BusinessSales.getTokenState", sales);

        return salesDetailVO;
    }
    
    @Override
    public BusinessSalesVO getOrderState(BusinessSalesVO sales) throws BizException {
    	
    	BusinessSalesVO salesDetailVO = commonDao.selectOne("BusinessSales.getOrderState", sales);

        return salesDetailVO;
    }


}
