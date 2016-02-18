/**
 *
 */
package com.offact.salesb.service.history;

import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.common.CodeVO;
import com.offact.salesb.vo.common.GroupVO;
import com.offact.salesb.vo.common.CompanyVO;
import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.history.WorkHistoryVO;
import com.offact.salesb.vo.manage.CompanyManageVO;
/**
 * @author 4530
 */
public interface WorkHistoryService {
    
    /**
     * 업무이력 조회
     * 
     * @return
     * @throws BizException
     */
    public List<WorkHistoryVO> getWorkHistoryPageList(WorkHistoryVO work) throws BizException;

    /**
     * 업무이력 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getWorkHistoryCnt(WorkHistoryVO work) throws BizException;

}
