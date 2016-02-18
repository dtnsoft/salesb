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
import com.offact.salesb.service.manage.CompanyManageService;
import com.offact.salesb.vo.manage.CompanyManageVO;
import com.offact.salesb.vo.master.ProductMasterVO;

/**
 * @author 4530
 */
@Service
public class CompanyManageServiceImpl implements CompanyManageService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<CompanyManageVO> getCompanyPageList(CompanyManageVO companycondition) throws BizException {
    	
        List<CompanyManageVO> companyListManage = commonDao.selectList("CompanyManage.getCompanyPageList", companycondition);

        return companyListManage;
    }

    @Override
    public int getCompanyCnt(CompanyManageVO companycondition) throws BizException {
        return commonDao.selectOne("CompanyManage.getCompanyCnt", companycondition);
    }

  
    public Map<Object, Object> regiExcelUpload(List<CompanyManageVO> excelUploadList)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();
    	    Map deleteMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    
    	    deleteMap.put("updateUserId", "system");  //기존 데이타 삭제필드 업데이트
    	    this.commonDao.update("CompanyManage.companyDeleteAll", deleteMap);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	CompanyManageVO companyMasterVO = (CompanyManageVO)excelUploadList.get(i);
    	    	companyMasterVO.setErrMsg("");
                this.commonDao.insert("CompanyManage.insertExcelCompany", companyMasterVO);
                rtnSuccessList.add(companyMasterVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((CompanyManageVO)excelUploadList.get(i)).setErrMsg(((CompanyManageVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((CompanyManageVO)excelUploadList.get(i));
    	        
    	        this.logger.debug("[key]:"+ ((CompanyManageVO)excelUploadList.get(i)).getCompanyCode()+" [msg] : " + ((CompanyManageVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);

    	    return rtnMap;
    	  }
}
