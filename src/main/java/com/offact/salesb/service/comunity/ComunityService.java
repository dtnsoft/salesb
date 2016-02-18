/**
 * 
 */
package com.offact.salesb.service.comunity;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.comunity.AsVO;
import com.offact.salesb.vo.comunity.ComunityVO;
import com.offact.salesb.vo.comunity.CounselVO;

/**
 * @author 4530
 *
 */
public interface ComunityService {
	/**
	 * 글올리기
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public int commentInsert(ComunityVO comunity) throws BizException;
	
	/**
     * 커뮤니티 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ComunityVO> getComunity(ComunityVO comunity) throws BizException;
    
    /**
     * 커뮤니티 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ComunityVO> getComunityList(ComunityVO comunity) throws BizException;
    
    /**
     * 커뮤니티 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ComunityVO> getComunityListTalk(ComunityVO comunity) throws BizException;
    
    /**
	 * 1:1상담
	 * @param 
	 * @return
	 * @throws BizException
	 */
	public int counselInsert(CounselVO counsel) throws BizException;
	
	 /**
     * 1:1상담 조회
     * 
     * @return
     * @throws BizException
     */
    public List<CounselVO> getCounselList(CounselVO counsel) throws BizException;
	
    /**
     * cs 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCounselCnt(CounselVO counsel) throws BizException;

    
    /**
     * 커뮤니티상세조회
     * 
     * @return
     * @throws BizException
     */
    public List<ComunityVO> getComunityReply(ComunityVO comunity) throws BizException;
    
}
