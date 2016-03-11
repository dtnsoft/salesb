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
import com.offact.salesb.service.member.TokenService;
import com.offact.salesb.vo.member.TokenVO;

/**
 * @author 4530
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<TokenVO> getTokenPageList(TokenVO token) throws BizException {
    	
        List<TokenVO> tokenlist = commonDao.selectList("Token.getTokenPageList", token);

        return tokenlist;
    }

    @Override
    public int getTokenCnt(TokenVO token) throws BizException {
        return commonDao.selectOne("Token.getTokenCnt", token);
    }

    @Override
    public TokenVO getTokenDetail(TokenVO token) throws BizException {
    	
    	TokenVO tokenDetailVO = commonDao.selectOne("Token.getTokenDetail", token);

        return tokenDetailVO;
    }
    
    @Override
    public int tokenInsertProc(TokenVO token) throws BizException {

    	return commonDao.update("Token.tokenInsertProc", token);

    }
    
    @Override
    public int tokenUpdateProc(TokenVO token) throws BizException {
        // 사용자 추가

    	return commonDao.update("Token.tokenUpdateProc", token);

    }

}
