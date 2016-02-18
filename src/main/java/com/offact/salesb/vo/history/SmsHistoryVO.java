package com.offact.salesb.vo.history;

import java.io.Serializable;
import java.io.File;
import java.util.List;

import com.offact.salesb.vo.AbstractVO;

public class SmsHistoryVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String groupId;
	
	private String idx;
	private String smsTo;
	private String smsFrom;
	private String smsMsg;
	private String smsDateTime;
	private String smsUserId;
	private String smsUserName;
	private String resultCode;
	private String resultMessage;
	private String resultLastPoint;
	 
	private String con_groupId;
	private String con_userId;
	private String searchGubun;
	private String searchValue;
	
	private String start_smsDate;
	private String end_smsDate;

    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
    
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
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getSmsTo() {
		return smsTo;
	}
	public void setSmsTo(String smsTo) {
		this.smsTo = smsTo;
	}
	public String getSmsFrom() {
		return smsFrom;
	}
	public void setSmsFrom(String smsFrom) {
		this.smsFrom = smsFrom;
	}
	public String getSmsMsg() {
		return smsMsg;
	}
	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}
	public String getSmsDateTime() {
		return smsDateTime;
	}
	public void setSmsDateTime(String smsDateTime) {
		this.smsDateTime = smsDateTime;
	}
	public String getSmsUserId() {
		return smsUserId;
	}
	public void setSmsUserId(String smsUserId) {
		this.smsUserId = smsUserId;
	}
	public String getSmsUserName() {
		return smsUserName;
	}
	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getResultLastPoint() {
		return resultLastPoint;
	}
	public void setResultLastPoint(String resultLastPoint) {
		this.resultLastPoint = resultLastPoint;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_userId() {
		return con_userId;
	}
	public void setCon_userId(String con_userId) {
		this.con_userId = con_userId;
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
	public String getStart_smsDate() {
		return start_smsDate;
	}
	public void setStart_smsDate(String start_smsDate) {
		this.start_smsDate = start_smsDate;
	}
	public String getEnd_smsDate() {
		return end_smsDate;
	}
	public void setEnd_smsDate(String end_smsDate) {
		this.end_smsDate = end_smsDate;
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

}
