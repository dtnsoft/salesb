/**
 *
 */
package com.offact.salesb.service.impl.manage;

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
import com.offact.salesb.service.manage.CustManageService;
import com.offact.salesb.vo.manage.CustManageVO;

/**
 * @author 4530
 */
@Service
public class CustManageServiceImpl implements CustManageService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<CustManageVO> getCustList(CustManageVO custcondition) throws BizException {
    	
        List<CustManageVO> custListManage = commonDao.selectList("CustManage.getCustPageList", custcondition);

        return custListManage;
    }

    @Override
    public int getCustCnt(CustManageVO custcondition) throws BizException {
        return commonDao.selectOne("CustManage.getCustCnt", custcondition);
    }

  
    @Override
    public CustManageVO getCustDetail(String custId) throws BizException {
    	
    	CustManageVO custDetailVO = commonDao.selectOne("CustManage.getCustDetail", custId);

        return custDetailVO;
    }
    
    
    public int custDeleteProc(String updateCustId , String arrDelCustId) throws BizException
    {
    	
      arrDelCustId = arrDelCustId.substring(0, arrDelCustId.lastIndexOf("^"));

      String[] arrCustId = arrDelCustId.split("\\^");
      
      int retVal=0;

      for (int i = 0; i < arrCustId.length; i++) {
        Map deleteMap = new HashMap();

        deleteMap.put("updateCustId", updateCustId);
        deleteMap.put("custId", arrCustId[i]);
        
        retVal=this.commonDao.delete("CustManage.custDeleteProc", deleteMap);

      }
      return retVal;
    }
   
}
