package com.offact.salesb.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.UserMenuService;
import com.offact.salesb.vo.UserMenuVO;

@Service
public class UserMenuServiceImpl implements UserMenuService
{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SqlSessionCommonDao commonDao;
	
	/* =================================
	 * #. 2013-12-12 hgjang 
	 * #. 사용자 체크 (old)
	 * =================================*/
	@Override
	public List<UserMenuVO> getCheckUserValidate(UserMenuVO userMenuVo)
			throws BizException 
	{
		logger.info(">>>> Start getCheckUserValidate a_userId ::: " + userMenuVo.getUSER_ID());
		
		List<UserMenuVO> userCheck = commonDao.selectList("UserMenu.getCheckUserValidate", userMenuVo);
		
		logger.info(">>>> userCheck ::: " + userCheck);
		logger.info(">>>> userCheck ::: " + userCheck.size());
		
		return userCheck;
	}
	
	/* =================================
	 * #. 2013-11-26 hgjang 
	 * #. 사용자 메뉴 리스트 (old)
	 * =================================*/
	@Override
	public List<UserMenuVO> getUserMenuList(UserMenuVO userMenuVo)
			throws BizException 
	{
		logger.info(">>>> Start getUserMenuList a_userId ::: " + userMenuVo.getUSER_ID());
		
		List<UserMenuVO> userMenuList = commonDao.selectList("UserMenu.getUserMenuList", userMenuVo);
		
		logger.info(">>>> userMenuList ::: " + userMenuList);
		logger.info(">>>> userMenuList ::: " + userMenuList.size());
		
		return userMenuList;
	}
	
	/* =================================
	 * #. 2013-12-12 jsjeon
	 * #. 메뉴 리스트
	 * =================================*/
	@Override
	public List<UserMenuVO> getAuthPerMenu(UserMenuVO userMenuVo)
			throws BizException 
	{
		logger.info(">>>> Start getAuthPerMenu a_userId ::: " + userMenuVo.getUSER_ID());
		
		List<UserMenuVO> authList = commonDao.selectList("UserMenu.getAuthPerMenu", userMenuVo);
		
		logger.info(">>>> authList ::: " + authList);
		logger.info(">>>> authList ::: " + authList.size());
		
		return authList;
	}
	
	/* =================================
	 * #. 2013-12-12 jsjeon
	 * #. 메뉴별 기능 리스트
	 * =================================*/
	@Override
	public List<UserMenuVO> getAuthPerFunction(UserMenuVO userMenuVo)
			throws BizException 
	{
		logger.info(">>>> Start getAuthPerFunction a_userId ::: " + userMenuVo.getUSER_ID());
		
		List<UserMenuVO> funcList = commonDao.selectList("UserMenu.getAuthPerFunction", userMenuVo);
		
		logger.info(">>>> funcList ::: " + funcList);
		logger.info(">>>> funcList ::: " + funcList.size());
		
		return funcList;
	}
	
	/* =================================
	 * #. 2013-12-12 jsjeon
	 * #. 메뉴별 기능 리스트
	 * =================================*/
	@Override
	public List<UserMenuVO> getAuthPerSuper(UserMenuVO userMenuVo)
			throws BizException 
	{
		logger.info(">>>> Start getAuthPerSuper a_userId ::: " + userMenuVo.getUSER_ID());
		
		List<UserMenuVO> funcList = commonDao.selectList("UserMenu.getAuthPerSuper", userMenuVo);
		
		logger.info(">>>> superList ::: " + funcList);
		logger.info(">>>> superList ::: " + funcList.size());
		
		return funcList;
	}
}
