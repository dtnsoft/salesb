/**
 *
 */
package com.offact.salesb.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.member.TokenVO;

/**
 * @author
 */
public interface TokenService {
    /**
     * token 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<TokenVO> getTokenPageList(TokenVO token) throws BizException;

    /**
     * Token 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getTokenCnt(TokenVO token) throws BizException;

    /**
     * Token 상세조회
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public TokenVO getTokenDetail(TokenVO token) throws BizException;
    
    /**
     * Token 저장
     * 
     * @return
     * @throws BizException
     */
    public int tokenInsertProc(TokenVO token) throws BizException;
    
    /**
     * Token 업덷이트
     * 
     * @return
     * @throws BizException
     */
    public int tokenUpdateProc(TokenVO token) throws BizException;

}
