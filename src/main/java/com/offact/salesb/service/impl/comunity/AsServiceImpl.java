/**
 *
 */
package com.offact.salesb.service.impl.comunity;

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
import com.offact.salesb.service.comunity.AsService;
import com.offact.salesb.vo.comunity.AsVO;

/**
 * @author 4530
 */
@Service
public class AsServiceImpl implements AsService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<AsVO> getAsList(AsVO as) throws BizException {
    	
        List<AsVO> aslList = commonDao.selectList("As.getAsList", as);

        return aslList;
    }

    @Override
    public int getAsCnt(AsVO as) throws BizException {
        return commonDao.selectOne("As.getAsCnt", as);
    }

    @Override
    public AsVO getAsDetail(AsVO as) throws BizException {
    	        
    	AsVO asDetailVO = commonDao.selectOne("As.getAsDetail", as);

        return asDetailVO;
    }

    @Override
    public int asProc(AsVO as) throws BizException {
        //상담처리
    	
    	int retval=0;
    	
    	retval=commonDao.update("As.asStateUpdate", as);
    	
    	retval=commonDao.insert("As.asResultInsert", as);
    	
    	return retval;

    }
    
    @Override
    public int asStateUpdate(AsVO as) throws BizException {
        //상담처리
    	
    	int retval=0;
    	
    	retval=commonDao.update("As.asStateUpdate", as);
    	
    	return retval;

    }
    
    @Override
    public List<AsVO> getAsReply(AsVO as) throws BizException {
    	
        List<AsVO> asList = commonDao.selectList("As.getAsReply", as);

        return asList;
    }

    @Override
    public int regiReplyInsert(AsVO as) throws BizException {
        //상담처리
    	
    	int retval=0;

    	retval=commonDao.insert("As.regiReplyInsert", as);
    	
    	return retval;

    }
    
    @Override
    public List<AsVO> getAsHistory(AsVO as) throws BizException {
    	
        List<AsVO> asList = commonDao.selectList("As.getAsHistory", as);

        return asList;
    }
    
    @Override
    public int asStateProc(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.asStateProc", as);
    	
    	retVal=commonDao.insert("As.asTransHistoryInsert", as);
    	
    	return retVal;


    }

}
