package com.offact.salesb.service.impl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.common.CodeVO;
import com.offact.salesb.vo.common.CompanyVO;
import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.common.CommonService;
import com.offact.salesb.vo.common.GroupVO;

/**
 * @author 4530
 */
@Service
public class CommonServiceImpl implements CommonService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    
    @Override
    public List<CodeVO> getCodeComboList(CodeVO code) throws BizException {
        List<CodeVO> codeList = commonDao.selectList("Code.getCodeComboList", code);

        return codeList;
    }
   
   @Override
   public List<CodeVO> getWorkCodeList(CodeVO code) throws BizException {
       List<CodeVO> codeList = commonDao.selectList("Code.getWorkCodeList", code);

       return codeList;
   }
  

   @Override
    public List<GroupVO> getGroupComboList(GroupVO group) throws BizException {
        List<GroupVO> grolupList = commonDao.selectList("Group.getGroupComboList", group);

        return grolupList;
    }

   @Override
   public CompanyVO getCompanyDetail(CompanyVO company) throws BizException {
	   CompanyVO companyDetail = commonDao.selectOne("Company.getCompany", company);

       return companyDetail;
   }
   
   @Override
   public List<CompanyVO> getCompanyList(CompanyVO company) throws BizException {
       List<CompanyVO> companyList = commonDao.selectList("Company.getCompanyList", company);

       return companyList;
   }
   
   @Override
   public int regiHistoryInsert(WorkVO work)
   	    throws BizException
	{
	    int retVal=-1;
	    
	    try{
	
	    	retVal=this.commonDao.insert("Work.WorkInsert", work);
	
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
  }
   
   @Override
   public UserVO getEncPassword(UserVO user)
   	    throws BizException
	{

	   UserVO userVo=new UserVO();
	   
	    try{
	
	    	userVo=this.commonDao.selectOne("User.getEncPassword", user);
	
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return userVo;
	    
  }

}
