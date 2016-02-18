package com.offact.framework.constants;


public class CodeConstant {
	/**
	 * JSON RPC
	 */
	public static final String RPC_PROTOCOL_TEST = "HTTP";
	public static final String RPC_HOST_TEST = "servicerpc-int.tmon.co.kr";
	public static final String RPC_PORT_TEST = "80";
	public static final String RPC_URL_TEST = "/rpc";
	public static final String RPC_CLASS_01 = "Member";
	public static final String RPC_METHOD_01 = "getInfo";

	public static final String RPC_CLASS_02 = "Member";
	public static final String RPC_METHOD_02 = "searchByName";

	public static final String YES_STR	= "Y";
	public static final String NO_STR	= "N";

	// BOARD CATEGORY
	public static final String BOARD_CAT_CS_KNOW		= "1";	//CS_지식관리
	public static final String BOARD_CAT_CS_DEAL		= "2";	//CS_딜공지사항
	public static final String BOARD_CAT_CS_MPCC		= "3";	//CS_MPC커뮤니티
	public static final String BOARD_CAT_CS_META		= "4";	//CS_메타넷커뮤니티
	public static final String BOARD_CAT_IS_COMM		= "5";	//IS_커뮤니티
	public static final String BOARD_CAT_TM_PLUS		= "6";	//티몬플러스_커뮤니티
	public static final String BOARD_CAT_PS_COMM		= "7";	//PS_커뮤니티
	public static final String BOARD_CAT_MB_COMM	= "8";	//멀티비즈_커뮤니티
	public static final String BOARD_CAT_ST_COMM		= "9";	//스토어운영_커뮤니티

	// Session
	public static final String SESSION_USER_INFO		= "userInfo";

	// taglib
	public static final String TAGLIB_SELECT_OPTION		= "--선택--";
	public static final int PAGE_GENERAL_MAX_ROW_COUNT = 15;
	public static final int PAGE_PHOTO_MAX_ROW_COUNT = 12;
	public static final int THUMBNAIL_WIDTH	= 134;

	/* **********************************************
	 * SSO 인증
	 * **********************************************/
	public static final String SSO_SITEPIN = "C613DD0B993D344151604D2B50F61B3F";
	public static final String SSO_REDIRECT_URL = "http%3a%2f%2fcc.tmoncorp.com:8080/cs/ssotest";
	public static final String SSO_VALIDUSER_URL = "https://svc.tmoncorp.com/ssosvc/ValidUser";
	public static final String SSO_TOKEN = "6DE16478192F3CC0115E61DAF5EE7C09";
	public static final String SSO_JSON_TAG_TITLE = "ValidUserResult";
	public static final String SSO_JSONKEY_COMPLETED = "Completed";
	public static final String SSO_JSONKEY_ERROR = "Error";
	public static final String SSO_JSONKEY_MESSAGE = "Message";
	public static final String SSO_JSONKEY_RETURNVALUE = "ReturnValue";
	public static final String SSO_JSONKEY_COMPANYCODE = "CompanyCode";
	public static final String SSO_JSONKEY_COMPANYNAME = "CompanyName";
	public static final String SSO_JSONKEY_DEPTCODE = "DeptCode";
	public static final String SSO_JSONKEY_DEPTNAME = "DeptName";
	public static final String SSO_JSONKEY_EMPID = "EmpID";
	public static final String SSO_JSONKEY_EMPMAIL = "EmpMail";
	public static final String SSO_JSONKEY_EMPMOBILENO = "EmpMobileNo";
	public static final String SSO_JSONKEY_EMPNAME_EN = "EmpNameEn";
	public static final String SSO_JSONKEY_EMPNAME_KO = "EmpNameKo";
	public static final String SSO_JSONKEY_EMPNO = "EmpNo";
	public static final String SSO_JSONKEY_EMPOFFICE_TELNO = "EmpOfficeTelNo";

	// 상위메뉴코드
	public static final String CS_UPPER_MENU_CODE = "IG30";
	public static final String TMONP_UPPER_MENU_CODE = "IG31";
	public static final String IS_UPPER_MENU_CODE = "IG32";
	public static final String PS_UPPER_MENU_CODE = "IG33";
	public static final String STORE_UPPER_MENU_CODE = "IG34";
	public static final String MULTIBIZ_UPPER_MENU_CODE = "IG35";


	/* **********************************************
	 * 첨부 파일
	 * **********************************************/
	public static final String BOARD_ATTCH_FILE_PATH="D:/springsource/workspace/cs/upload/";
//	public static final String BOARD_ATTCH_FILE_PATH="D:/DEV/01.projects/pj_tmon_cs/cs/upload/";

	/* **********************************************
	 * 암호화/복호화 키값
	 * **********************************************/
	public static final String ENCRIPT_DECRIPT_KEY = "We are tmonian and livingsocials";

	/* **********************************************
	 * 사용자부서코드
	 * **********************************************/
	public static final String USER_DEPT_CODE_CS = "CS";

}
