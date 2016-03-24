package com.offact.salesb.vo.business;

import com.offact.salesb.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class BusinessSalesVO extends AbstractVO {

	
	private String customerKey;
	private String userId;
	private String groupId;

	private String tokenCnt;
	private String newOrerCnt;
	private String deliveryTagetCnt;
	private String deliveryIngCnt;
	private String deliveryColseCnt;
	private String salesAmt;
	
	private String sbPhoneNuber;
	private String accrueSalesCnt;
	private String accrueSalesAmt;
	
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

	public String getDeliveryTagetCnt() {
		return deliveryTagetCnt;
	}

	public void setDeliveryTagetCnt(String deliveryTagetCnt) {
		this.deliveryTagetCnt = deliveryTagetCnt;
	}

	public String getDeliveryIngCnt() {
		return deliveryIngCnt;
	}

	public void setDeliveryIngCnt(String deliveryIngCnt) {
		this.deliveryIngCnt = deliveryIngCnt;
	}

	public String getDeliveryColseCnt() {
		return deliveryColseCnt;
	}

	public void setDeliveryColseCnt(String deliveryColseCnt) {
		this.deliveryColseCnt = deliveryColseCnt;
	}

	public String getSalesAmt() {
		return salesAmt;
	}

	public void setSalesAmt(String salesAmt) {
		this.salesAmt = salesAmt;
	}

	public String getSbPhoneNuber() {
		return sbPhoneNuber;
	}

	public void setSbPhoneNuber(String sbPhoneNuber) {
		this.sbPhoneNuber = sbPhoneNuber;
	}

	public String getAccrueSalesCnt() {
		return accrueSalesCnt;
	}

	public void setAccrueSalesCnt(String accrueSalesCnt) {
		this.accrueSalesCnt = accrueSalesCnt;
	}

	public String getAccrueSalesAmt() {
		return accrueSalesAmt;
	}

	public void setAccrueSalesAmt(String accrueSalesAmt) {
		this.accrueSalesAmt = accrueSalesAmt;
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
