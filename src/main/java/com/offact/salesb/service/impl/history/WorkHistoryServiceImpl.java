package com.offact.salesb.service.impl.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.history.WorkHistoryService;
import com.offact.salesb.vo.common.CodeVO;
import com.offact.salesb.vo.common.GroupVO;
import com.offact.salesb.vo.common.CompanyVO;
import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.history.WorkHistoryVO;

/**
 * @author 4530
 */
@Service
public class WorkHistoryServiceImpl implements WorkHistoryService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

   @Override
   public List<WorkHistoryVO> getWorkHistoryPageList(WorkHistoryVO workcondition) throws BizException {
   	
       List<WorkHistoryVO> workList = commonDao.selectList("WorkHistory.getWorkHistoryPageList", workcondition);

       return workList;
   }

   @Override
   public int getWorkHistoryCnt(WorkHistoryVO workcondition) throws BizException {
       return commonDao.selectOne("WorkHistory.getWorkHistoryCnt", workcondition);
   }
}
