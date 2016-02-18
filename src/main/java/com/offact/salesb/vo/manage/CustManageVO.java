package com.offact.salesb.vo.manage;

import com.offact.salesb.vo.AbstractVO;

public class CustManageVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    private String customerKey;
	private String sbPhoneNumber;
	private String sbPw;
	private String socialId1;
	private String socialId2;
	private String socialId3;
	private String socialId4;
	private String socialId5;
	private String socialId6;
	private String socialId7;
	private String socialId8;
	private String socialId9;
	private String socialId10;
	private String createDateTime;
	private String token;
	private String tokenDateTime;
	private String registDateTime;
	private String updateDateTime;
	private String usedYn;
	private String lastloginType;
	private String lastLat;
	private String lastlon;
	
	private String con_useYn;
    private String searchGubun;
    private String searchValue;

	private String groupId;
    
    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
    
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
	public String getSbPhoneNumber() {
		return sbPhoneNumber;
	}
	public void setSbPhoneNumber(String sbPhoneNumber) {
		this.sbPhoneNumber = sbPhoneNumber;
	}
	public String getSbPw() {
		return sbPw;
	}
	public void setSbPw(String sbPw) {
		this.sbPw = sbPw;
	}
	public String getSocialId1() {
		return socialId1;
	}
	public void setSocialId1(String socialId1) {
		this.socialId1 = socialId1;
	}
	public String getSocialId2() {
		return socialId2;
	}
	public void setSocialId2(String socialId2) {
		this.socialId2 = socialId2;
	}
	public String getSocialId3() {
		return socialId3;
	}
	public void setSocialId3(String socialId3) {
		this.socialId3 = socialId3;
	}
	public String getSocialId4() {
		return socialId4;
	}
	public void setSocialId4(String socialId4) {
		this.socialId4 = socialId4;
	}
	public String getSocialId5() {
		return socialId5;
	}
	public void setSocialId5(String socialId5) {
		this.socialId5 = socialId5;
	}
	public String getSocialId6() {
		return socialId6;
	}
	public void setSocialId6(String socialId6) {
		this.socialId6 = socialId6;
	}
	public String getSocialId7() {
		return socialId7;
	}
	public void setSocialId7(String socialId7) {
		this.socialId7 = socialId7;
	}
	public String getSocialId8() {
		return socialId8;
	}
	public void setSocialId8(String socialId8) {
		this.socialId8 = socialId8;
	}
	public String getSocialId9() {
		return socialId9;
	}
	public void setSocialId9(String socialId9) {
		this.socialId9 = socialId9;
	}
	public String getSocialId10() {
		return socialId10;
	}
	public void setSocialId10(String socialId10) {
		this.socialId10 = socialId10;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenDateTime() {
		return tokenDateTime;
	}
	public void setTokenDateTime(String tokenDateTime) {
		this.tokenDateTime = tokenDateTime;
	}
	public String getRegistDateTime() {
		return registDateTime;
	}
	public void setRegistDateTime(String registDateTime) {
		this.registDateTime = registDateTime;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getUsedYn() {
		return usedYn;
	}
	public void setUsedYn(String usedYn) {
		this.usedYn = usedYn;
	}
	public String getLastloginType() {
		return lastloginType;
	}
	public void setLastloginType(String lastloginType) {
		this.lastloginType = lastloginType;
	}
	public String getLastLat() {
		return lastLat;
	}
	public void setLastLat(String lastLat) {
		this.lastLat = lastLat;
	}
	public String getLastlon() {
		return lastlon;
	}
	public void setLastlon(String lastlon) {
		this.lastlon = lastlon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
