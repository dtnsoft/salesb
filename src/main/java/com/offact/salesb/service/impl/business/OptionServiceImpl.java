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
import com.offact.salesb.service.business.OptionService;
import com.offact.salesb.vo.business.OptionVO;
import com.offact.salesb.vo.order.OrderVO;

/**
 * @author 4530
 */
@Service
public class OptionServiceImpl implements OptionService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<OptionVO> getOptionList(OptionVO optioncondition) throws BizException {
    	
        List<OptionVO> optionList = commonDao.selectList("Option.getOptionList", optioncondition);

        return optionList;
    }


    @Override
    public int optionInsert(OptionVO option) throws BizException {
        // 사용자 추가

    	return commonDao.update("Option.optionInsert", option);

    }
    
    @Override
    public OptionVO getOptionKey() throws BizException {
    	
    	OptionVO option = commonDao.selectOne("Option.getOptionKey");

        return option;
    }
    
    @Override
    public List<OptionVO> getOptionDetailList(OptionVO optioncondition) throws BizException {
    	
        List<OptionVO> optionList = commonDao.selectList("Option.getOptionDetailList", optioncondition);

        return optionList;
    }


    @Override
    public int optionDetailInsert(OptionVO option) throws BizException {
        // 사용자 추가

    	return commonDao.update("Option.optionDetailInsert", option);

    }
    
    @Override
    public int optionDelete(OptionVO option) throws BizException {
        // 사용자 추가

    	return commonDao.update("Option.optionDelete", option);

    }
    
    @Override
    public int optionDetailDelete(OptionVO option) throws BizException {
        // 사용자 추가

    	return commonDao.update("Option.optionDetailDelete", option);

    }

}
