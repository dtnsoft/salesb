/**
 * 
 */
package com.offact.salesb.vo;

import com.offact.salesb.vo.AbstractVO;

/**
 * @author 4530
 *
 */
public class UserMenuVO extends AbstractVO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5170148625029306297L;
	
	private String USER_ID;
	private String USER_NM;
	private String USER_DEPT_CODE;
	private String DEPT_NAME;
	private String FUNC_AUTH_CODE;
	private String FUNC_AUTH_NM;
	private String MENU_ID;
	private String MENU_NM;
	private String MENU_URL;
	private String MENU_YN;
	private String MENU_DISPLAY_ORDER;
	private String DESCRIPTION;
	private String UPPER_MENU_CODE;
	private String UPPER_MENU_NAME;
	
	
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String USER_ID) {
		this.USER_ID = USER_ID;
	}
	public String getUSER_NM() {
		return USER_NM;
	}
	public void setUSER_NM(String USER_NM) {
		this.USER_NM = USER_NM;
	}
	public String getUSER_DEPT_CODE() {
		return USER_DEPT_CODE;
	}
	public void setUSER_DEPT_CODE(String USER_DEPT_CODE) {
		this.USER_DEPT_CODE = USER_DEPT_CODE;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String DEPT_NAME) {
		this.DEPT_NAME = DEPT_NAME;
	}
	public String getFUNC_AUTH_CODE() {
		return FUNC_AUTH_CODE;
	}
	public void setFUNC_AUTH_CODE(String FUNC_AUTH_CODE) {
		this.FUNC_AUTH_CODE = FUNC_AUTH_CODE;
	}
	public String getFUNC_AUTH_NM() {
		return FUNC_AUTH_NM;
	}
	public void setFUNC_AUTH_NM(String FUNC_AUTH_NM) {
		this.FUNC_AUTH_NM = FUNC_AUTH_NM;
	}
	public String getMENU_ID() {
		return MENU_ID;
	}
	public void setMENU_ID(String MENU_ID) {
		this.MENU_ID = MENU_ID;
	}
	public String getMENU_NM() {
		return MENU_NM;
	}
	public void setMENU_NM(String MENU_NM) {
		this.MENU_NM = MENU_NM;
	}
	public String getMENU_URL() {
		return MENU_URL;
	}
	public void setMENU_URL(String MENU_URL) {
		this.MENU_URL = MENU_URL;
	}
	public String getMENU_DISPLAY_ORDER() {
		return MENU_DISPLAY_ORDER;
	}
	public void setMENU_DISPLAY_ORDER(String MENU_DISPLAY_ORDER) {
		this.MENU_DISPLAY_ORDER = MENU_DISPLAY_ORDER;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String DESCRIPTION) {
		this.DESCRIPTION = DESCRIPTION;
	}
	public String getUPPER_MENU_CODE() {
		return UPPER_MENU_CODE;
	}
	public void setUPPER_MENU_CODE(String UPPER_MENU_CODE) {
		this.UPPER_MENU_CODE = UPPER_MENU_CODE;
	}
	public String getUPPER_MENU_NAME() {
		return UPPER_MENU_NAME;
	}
	public void setUPPER_MENU_NAME(String UPPER_MENU_NAME) {
		this.UPPER_MENU_NAME = UPPER_MENU_NAME;
	}
	public String getMENU_YN() {
		return MENU_YN;
	}
	public void setMENU_YN(String mENU_YN) {
		MENU_YN = mENU_YN;
	}
	
}
