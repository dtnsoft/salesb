/**
 *
 */
package com.offact.salesb.service.impl.member;

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
import com.offact.salesb.service.member.MemberSalesService;
import com.offact.salesb.vo.member.MemberSalesVO;

/**
 * @author 4530
 */
@Service
public class MemberSalesServiceImpl implements MemberSalesService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<MemberSalesVO> getMemberSalesPageList(MemberSalesVO token) throws BizException {
    	
        List<MemberSalesVO> tokenlist = commonDao.selectList("MemberSales.getMemberSalesPageList", token);

        return tokenlist;
    }

    @Override
    public int getMemberSalesCnt(MemberSalesVO token) throws BizException {
        return commonDao.selectOne("MemberSales.getMemberSalesCnt", token);
    }

    @Override
    public MemberSalesVO getTokenState(MemberSalesVO token) throws BizException {
    	
    	MemberSalesVO tokenDetailVO = commonDao.selectOne("MemberSales.getTokenState", token);

        return tokenDetailVO;
    }
    
    @Override
    public MemberSalesVO getOrderState(MemberSalesVO token) throws BizException {
    	
    	MemberSalesVO tokenDetailVO = commonDao.selectOne("MemberSales.getOrderState", token);

        return tokenDetailVO;
    }


}
