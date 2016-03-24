package com.offact.salesb.vo.member;

import com.offact.salesb.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class MemberSalesVO extends AbstractVO {
	
	private String customerKey;
	private String groupId;

	private String tokenCnt;
	private String newOrerCnt;
	private String totalSalesCnt;
	private String deliveryColseCnt;
	private String setteleTagetAmt;
	private String setteleColseAmt;
	
	private String productName;
	private String suplyCompany;
	private String salesCnt;
	private String salesAmt;
	
    private String orderStartDate;
    private String orderEndDate;

    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;

    private String errMsg;

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTokenCnt() {
		return tokenCnt;
	}

	public void setTokenCnt(String tokenCnt) {
		this.tokenCnt = tokenCnt;
	}

	public String getNewOrerCnt() {
		return newOrerCnt;
	}

	public void setNewOrerCnt(String newOrerCnt) {
		this.newOrerCnt = newOrerCnt;
	}

	public String getTotalSalesCnt() {
		return totalSalesCnt;
	}

	public void setTotalSalesCnt(String totalSalesCnt) {
		this.totalSalesCnt = totalSalesCnt;
	}

	public String getDeliveryColseCnt() {
		return deliveryColseCnt;
	}

	public void setDeliveryColseCnt(String deliveryColseCnt) {
		this.deliveryColseCnt = deliveryColseCnt;
	}

	public String getSetteleTagetAmt() {
		return setteleTagetAmt;
	}

	public void setSetteleTagetAmt(String setteleTagetAmt) {
		this.setteleTagetAmt = setteleTagetAmt;
	}

	public String getSetteleColseAmt() {
		return setteleColseAmt;
	}

	public void setSetteleColseAmt(String setteleColseAmt) {
		this.setteleColseAmt = setteleColseAmt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSuplyCompany() {
		return suplyCompany;
	}

	public void setSuplyCompany(String suplyCompany) {
		this.suplyCompany = suplyCompany;
	}

	public String getSalesCnt() {
		return salesCnt;
	}

	public void setSalesCnt(String salesCnt) {
		this.salesCnt = salesCnt;
	}

	public String getSalesAmt() {
		return salesAmt;
	}

	public void setSalesAmt(String salesAmt) {
		this.salesAmt = salesAmt;
	}

	public String getOrderStartDate() {
		return orderStartDate;
	}

	public void setOrderStartDate(String orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	public String getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
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

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
