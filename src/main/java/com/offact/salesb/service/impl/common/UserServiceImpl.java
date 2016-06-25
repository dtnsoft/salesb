package com.offact.salesb.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonAdminDao;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.common.UserService;
import com.offact.salesb.vo.common.UserVO;

/**
 * @author 4530
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SqlSessionCommonDao commonDao;

	@Autowired
	private SqlSessionCommonAdminDao commonSubDao;		//multi datasource test

	@Override
	public UserVO getUser(UserVO user) throws BizException {
		return commonDao.selectOne("User.getUser", user);
	}
	
	@Override
	   public int regiLoginYnUpdate(UserVO user)
	   	    throws BizException
		{
		    int retVal=-1;
		    
		    try{
		
		    	retVal=this.commonDao.insert("User.userLoginYnUpdate", user);
		
		    }catch(Exception e){
		    	
		    	e.printStackTrace();
		    	e.printStackTrace();
		    	throw new BizException(e.getMessage());

		    }
		
		    return retVal;
		    
	  }
	   
	@Override
	   public int regiUseYnUpdate(UserVO user)
	   	    throws BizException
		{
		    int retVal=-1;
		    
		    try{
		
		    	retVal=this.commonDao.insert("User.userUseYnUpdate", user);
		
		    }catch(Exception e){
		    	
		    	e.printStackTrace();
		    	e.printStackTrace();
		    	throw new BizException(e.getMessage());

		    }
		
		    return retVal;
		    
	  }
	   
}
