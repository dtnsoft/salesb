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
import com.offact.salesb.service.manage.UserManageService;
import com.offact.salesb.vo.manage.UserManageVO;

/**
 * @author 4530
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<UserManageVO> getUserList(UserManageVO usercondition) throws BizException {
    	
        List<UserManageVO> userListManage = commonDao.selectList("UserManage.getUserPageList", usercondition);

        return userListManage;
    }

    @Override
    public int getUserCnt(UserManageVO usercondition) throws BizException {
        return commonDao.selectOne("UserManage.getUserCnt", usercondition);
    }

    @Override
    public int userUpdateProc(UserManageVO userDetail) throws BizException {
        // 사용자 상세정보 수정
    	
    	if(userDetail.getPw_modifyYn().equals("Y")){
    		return commonDao.update("UserManage.userUpdatePwProc", userDetail);
    	}else{
    		return commonDao.update("UserManage.userUpdateProc", userDetail);
    	}

    }

    @Override
    public int userInsertProc(UserManageVO userDetail) throws BizException {
        // 사용자 추가

    	return commonDao.update("UserManage.userInsertProc", userDetail);

    }

    @Override
    public UserManageVO getUserDetail(String userId) throws BizException {
    	
    	UserManageVO userDetailVO = commonDao.selectOne("UserManage.getUserDetail", userId);

        return userDetailVO;
    }
    
    public int userDeleteProc(String updateUserId , String arrDelUserId) throws BizException
    {
    	
      arrDelUserId = arrDelUserId.substring(0, arrDelUserId.lastIndexOf("^"));

      String[] arrUserId = arrDelUserId.split("\\^");
      
      int retVal=0;

      for (int i = 0; i < arrUserId.length; i++) {
        Map deleteMap = new HashMap();

        deleteMap.put("updateUserId", updateUserId);
        deleteMap.put("userId", arrUserId[i]);
        
        retVal=this.commonDao.delete("UserManage.userDeleteProc", deleteMap);

      }
      return retVal;
    }
    
    public Map<Object, Object> regiExcelUpload(List<UserManageVO> userUploadList)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();

    	    List rtnErrorUserVOList = new ArrayList();
    	    List rtnSuccessUserVOList = new ArrayList();

    	    Boolean errFg = Boolean.valueOf(false);

    	    int idx = 0;

    	    for (int i = 0; i < userUploadList.size(); i++) {
    	      errFg = Boolean.valueOf(false);
    	      try {
    	        idx = i + 2;
    	        UserManageVO userManageVO = (UserManageVO)userUploadList.get(i);
    	        userManageVO.setErrMsg("");

    	        UserManageVO validationUserVO = (UserManageVO)this.commonDao.selectOne("UserManage.validationUploadFile", userManageVO);

    	        String lineStr = "";
    	        if (!validationUserVO.getUserResult().equals("SUCCESS")) {
    	          errFg = Boolean.valueOf(true);
    	          if (!validationUserVO.getErrMsg().equals(""))
    	            lineStr = "\n\r";
    	          else {
    	            lineStr = "";
    	          }
    	          validationUserVO.setErrMsg(validationUserVO.getErrMsg() + lineStr + "(" + idx + ")사용자 정보가 유효 하지 않습니다.");
    	          System.out.println("UserManageVO.getErrMsg() : " + validationUserVO.getErrMsg());
    	        }

    	        if (!errFg.booleanValue()) {
    	          rtnSuccessUserVOList.add(userManageVO);
    	          this.commonDao.insert("UserManage.insertExcelUser", userManageVO);
    	        } else {
    	          rtnErrorUserVOList.add(userManageVO);
    	        }
    	      }
    	      catch (Exception e) {
    	        e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((UserManageVO)userUploadList.get(i)).setErrMsg(((UserManageVO)userUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorUserVOList.add((UserManageVO)userUploadList.get(i));
    	      }
    	    }

    	    rtnMap.put("rtnErrorList", rtnErrorUserVOList);
    	    rtnMap.put("rtnSuccessList", rtnSuccessUserVOList);

    	    return rtnMap;
    	  }

    @Override
    public int getCheckCnt(UserManageVO usercondition) throws BizException {
        return commonDao.selectOne("UserManage.getCheckCnt", usercondition);
    }
}
