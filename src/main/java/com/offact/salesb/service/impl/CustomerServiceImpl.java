package com.offact.salesb.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonAdminDao;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.CustomerService;
import com.offact.salesb.vo.CustomerVO;

/**
 * @author 4530
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SqlSessionCommonDao commonDao;

	@Autowired
	private SqlSessionCommonAdminDao commonSubDao;		//multi datasource test

	@Override
	public CustomerVO getCustomer(CustomerVO customer) throws BizException {
		return commonDao.selectOne("Customer.getCustomer", customer);
	}
	@Override
	   public CustomerVO getEncPassword(CustomerVO customer)
	   	    throws BizException
		{

		CustomerVO customerVo=new CustomerVO();
		   
		    try{
		
		    	customerVo=this.commonDao.selectOne("Customer.getEncPassword", customer);
		
		    }catch(Exception e){
		    	
		    	e.printStackTrace();
		    	e.printStackTrace();
		    	throw new BizException(e.getMessage());

		    }
		
		    return customerVo;
		    
	  }
	@Override
    public int customerUpdateProc(CustomerVO customer) throws BizException {
        // 고객 상세정보 수정
    	
    	if(customer.getPw_modifyYn().equals("Y")){
    		return commonDao.update("Customer.customerUpdatePwProc", customer);
    	}else{
    		return commonDao.update("Customer.customerUpdateProc", customer);
    	}

    }
	
	@Override
    public int socialId1UpdateProc(CustomerVO customer) throws BizException {
        // 고객 상세정보 수정
    	
		return commonDao.update("Customer.socialId1UpdateProc", customer);

    }
	
	@Override
    public int socialId2UpdateProc(CustomerVO customer) throws BizException {
        // 고객 상세정보 수정
    	
		return commonDao.update("Customer.socialId2UpdateProc", customer);

    }
	
	@Override
    public int socialId3UpdateProc(CustomerVO customer) throws BizException {
        // 고객 상세정보 수정
    	
		return commonDao.update("Customer.socialId3UpdateProc", customer);

    }
	
	@Override
    public int customerRegist(CustomerVO customer) throws BizException {
        // 고객등록
		
		return commonDao.insert("Customer.customerRegist", customer);
    }
	
	@Override
    public int customerRegistUpdate(CustomerVO customer) throws BizException {
        // 고객등록
		
		return commonDao.insert("Customer.customerRegistUpdate", customer);
    }
	
	@Override
    public int customerUpdateToken(CustomerVO customer) throws BizException {
		
		return commonDao.insert("Customer.getToken", customer);

    }
	@Override
    public int tokenInsert(CustomerVO customer) throws BizException {
		
		return commonDao.insert("Customer.getTokenInsert", customer);

    }
	
	@Override
    public int tokenUpdate(CustomerVO customer) throws BizException {
		
		return commonDao.insert("Customer.getTokenUpdate", customer);

    }
	@Override
	public CustomerVO getTokenInfo(CustomerVO customer) throws BizException {
		return commonDao.selectOne("Customer.getTokenInfo", customer);
	}
	
	@Override
	public CustomerVO getCustKeyInfo(CustomerVO customer) throws BizException {
		return commonDao.selectOne("Customer.getCustKeyInfo", customer);
	}
	
	@Override
    public int customerInsert(CustomerVO customer) throws BizException {
        // 고객등록
		
		return commonDao.insert("Customer.customerInsert", customer);
    }
}
