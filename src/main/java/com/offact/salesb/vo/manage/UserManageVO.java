package com.offact.salesb.vo.manage;

import com.offact.salesb.vo.AbstractVO;

public class UserManageVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String groupId;
	private String groupName;
	private String authId;
	private String authName;
	private String excelAuth;
	private String password;
	private String officePhone;
	private String officePhoneFormat;
	private String mobliePhone;
	private String mobliePhoneFormat;
	private String email;
	private String ip;
	private String createUserId;
	private String createUserName;
	private String createDateTime;
	private String updateUserId;
	private String updateUserName;
	private String updateDateTime;
	private String useYn;
	private String deletedYn;
	private String auth;
	
    private String con_groupId;
    private String con_useYn;
    private String searchGubun;
    private String searchValue;
    
    private String pw_modifyYn;
    private String smsYn;
    
    private String pwdChangeDateTime;

	private String companyId;
	private String image1;
	
    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
    
    private String userResult;
    private String errMsg;
    
	public String getUserResult() {
		return userResult;
	}
	public void setUserResult(String userResult) {
		this.userResult = userResult;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getExcelAuth() {
		return excelAuth;
	}
	public void setExcelAuth(String excelAuth) {
		this.excelAuth = excelAuth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getOfficePhoneFormat() {
		return officePhoneFormat;
	}
	public void setOfficePhoneFormat(String officePhoneFormat) {
		this.officePhoneFormat = officePhoneFormat;
	}
	public String getMobliePhone() {
		return mobliePhone;
	}
	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}
	public String getMobliePhoneFormat() {
		return mobliePhoneFormat;
	}
	public void setMobliePhoneFormat(String mobliePhoneFormat) {
		this.mobliePhoneFormat = mobliePhoneFormat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDeletedYn() {
		return deletedYn;
	}
	public void setDeletedYn(String deletedYn) {
		this.deletedYn = deletedYn;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_useYn() {
		return con_useYn;
	}
	public void setCon_useYn(String con_useYn) {
		this.con_useYn = con_useYn;
	}
	public String getSearchGubun() {
		return searchGubun;
	}
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getRowCount() {
		return rowCount;
	}
	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}
	public String getPage_limit_val1() {
		return page_limit_val1;
	}
	public void setPage_limit_val1(String page_limit_val1) {
		this.page_limit_val1 = page_limit_val1;
	}
	public String getPage_limit_val2() {
		return page_limit_val2;
	}
	public void setPage_limit_val2(String page_limit_val2) {
		this.page_limit_val2 = page_limit_val2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPw_modifyYn() {
		return pw_modifyYn;
	}
	public void setPw_modifyYn(String pw_modifyYn) {
		this.pw_modifyYn = pw_modifyYn;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getPwdChangeDateTime() {
		return pwdChangeDateTime;
	}
	public void setPwdChangeDateTime(String pwdChangeDateTime) {
		this.pwdChangeDateTime = pwdChangeDateTime;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
   
}
