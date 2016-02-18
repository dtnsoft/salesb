/**
 *
 */
package com.offact.salesb.service.history;

import java.util.List;

import org.json.simple.JSONArray;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.history.SmsHistoryVO;
/**
 * @author 4530
 *
 */
public interface SmsHistoryService {

    /**
     * SMS이력 조회
     * 
     * @return
     * @throws BizException
     */
    public List<SmsHistoryVO> getSmsHistoryPageList(SmsHistoryVO work) throws BizException;

    /**
     * SMS이력 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getSmsHistoryCnt(SmsHistoryVO work) throws BizException;


}
