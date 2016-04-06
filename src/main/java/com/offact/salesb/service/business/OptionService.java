/**
 *
 */
package com.offact.salesb.service.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.business.OptionVO;

/**
 * @author
 */
public interface OptionService {
    /**
     * 옵션 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OptionVO> getOptionList(OptionVO option) throws BizException;
   
  
    /**
     * 옵션 저장
     * 
     * @return
     * @throws BizException
     */
    public int optionInsert(OptionVO option) throws BizException;

    /**
     * 옵션키 조회
     * 
     * @return
     * @throws BizException
     */
    public OptionVO getOptionKey() throws BizException;
    
    /**
     * 옵션 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OptionVO> getOptionDetailList(OptionVO option) throws BizException;
   
  
    /**
     * 옵션 저장
     * 
     * @return
     * @throws BizException
     */
    public int optionDetailInsert(OptionVO option) throws BizException;
    
    /**
     * 옵션 삭제
     * 
     * @return
     * @throws BizException
     */
    public int optionDelete(OptionVO option) throws BizException;
    
    /**
     * 옵션 삭제
     * 
     * @return
     * @throws BizException
     */
    public int optionDetailDelete(OptionVO option) throws BizException;


}
