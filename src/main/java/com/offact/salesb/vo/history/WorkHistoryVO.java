package com.offact.salesb.vo.history;

import java.io.Serializable;

import com.offact.salesb.vo.AbstractVO;

public class WorkHistoryVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String groupId;
	
	private String idx;
	private String workDateTime;
	private String workIp;
	private String workUserId;
	private String workUserName;
	private String workGroupId;
	private String workGroupName;
	private String workCategory;
	private String workCategoryName;
	private String workCode;
	private String workCodeName;
	
    private String description;
    private String companyName;
	
	private String workKey1;
	private String workKey2;
	private String workKey3;
	
	private String searchKey1;
	private String searchKey2;
	private String searchKey3;
	
	private String con_groupId;
	private String con_userId;
	private String con_workCategory;
	private String con_workCode;
	private String searchGubun;
	private String searchValue;
	
	private String searchKey1Gubun;
	private String searchKey1Value;
	private String searchKey2Gubun;
	private String searchKey2Value;
	private String searchKey3Gubun;
	private String searchKey3Value;
	
	private String start_workDate;
	private String end_workDate;

    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
    
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getWorkDateTime() {
		return workDateTime;
	}
	public void setWorkDateTime(String workDateTime) {
		this.workDateTime = workDateTime;
	}
	public String getWorkIp() {
		return workIp;
	}
	public void setWorkIp(String workIp) {
		this.workIp = workIp;
	}
	public String getWorkUserId() {
		return workUserId;
	}
	public void setWorkUserId(String workUserId) {
		this.workUserId = workUserId;
	}
	public String getWorkCategory() {
		return workCategory;
	}
	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_workCategory() {
		return con_workCategory;
	}
	public void setCon_workCategory(String con_workCategory) {
		this.con_workCategory = con_workCategory;
	}
	public String getCon_workCode() {
		return con_workCode;
	}
	public void setCon_workCode(String con_workCode) {
		this.con_workCode = con_workCode;
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
	public String getWorkUserName() {
		return workUserName;
	}
	public void setWorkUserName(String workUserName) {
		this.workUserName = workUserName;
	}
	public String getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}
	public String getWorkGroupName() {
		return workGroupName;
	}
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}
	public String getCon_userId() {
		return con_userId;
	}
	public void setCon_userId(String con_userId) {
		this.con_userId = con_userId;
	}
	public String getWorkCategoryName() {
		return workCategoryName;
	}
	public void setWorkCategoryName(String workCategoryName) {
		this.workCategoryName = workCategoryName;
	}
	public String getWorkCodeName() {
		return workCodeName;
	}
	public void setWorkCodeName(String workCodeName) {
		this.workCodeName = workCodeName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getStart_workDate() {
		return start_workDate;
	}
	public void setStart_workDate(String start_workDate) {
		this.start_workDate = start_workDate;
	}
	public String getEnd_workDate() {
		return end_workDate;
	}
	public void setEnd_workDate(String end_workDate) {
		this.end_workDate = end_workDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorkKey1() {
		return workKey1;
	}
	public void setWorkKey1(String workKey1) {
		this.workKey1 = workKey1;
	}
	public String getWorkKey2() {
		return workKey2;
	}
	public void setWorkKey2(String workKey2) {
		this.workKey2 = workKey2;
	}
	public String getWorkKey3() {
		return workKey3;
	}
	public void setWorkKey3(String workKey3) {
		this.workKey3 = workKey3;
	}
	public String getSearchKey1() {
		return searchKey1;
	}
	public void setSearchKey1(String searchKey1) {
		this.searchKey1 = searchKey1;
	}
	public String getSearchKey2() {
		return searchKey2;
	}
	public void setSearchKey2(String searchKey2) {
		this.searchKey2 = searchKey2;
	}
	public String getSearchKey3() {
		return searchKey3;
	}
	public void setSearchKey3(String searchKey3) {
		this.searchKey3 = searchKey3;
	}
	public String getSearchKey1Gubun() {
		return searchKey1Gubun;
	}
	public void setSearchKey1Gubun(String searchKey1Gubun) {
		this.searchKey1Gubun = searchKey1Gubun;
	}
	public String getSearchKey1Value() {
		return searchKey1Value;
	}
	public void setSearchKey1Value(String searchKey1Value) {
		this.searchKey1Value = searchKey1Value;
	}
	public String getSearchKey2Gubun() {
		return searchKey2Gubun;
	}
	public void setSearchKey2Gubun(String searchKey2Gubun) {
		this.searchKey2Gubun = searchKey2Gubun;
	}
	public String getSearchKey2Value() {
		return searchKey2Value;
	}
	public void setSearchKey2Value(String searchKey2Value) {
		this.searchKey2Value = searchKey2Value;
	}
	public String getSearchKey3Gubun() {
		return searchKey3Gubun;
	}
	public void setSearchKey3Gubun(String searchKey3Gubun) {
		this.searchKey3Gubun = searchKey3Gubun;
	}
	public String getSearchKey3Value() {
		return searchKey3Value;
	}
	public void setSearchKey3Value(String searchKey3Value) {
		this.searchKey3Value = searchKey3Value;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
