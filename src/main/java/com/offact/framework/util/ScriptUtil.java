package com.offact.framework.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptUtil {
	
	private final  String _jsStartTag = "<script language='javascript'>\n<!--\n";
	private final  String _jsEngTag =  "//--></script>"; 
	private final  String _contentType = "text/html; charset=euc-kr" ; 	

	
	/**
	 * alert 을 출력 한후 이전 페이지 history.back 을 실행 한다 . 
	 * @param response
	 * @param message
	 */ 
	public void doAlertToHistoryGo(HttpServletResponse response,String message){

		try {

			StringBuffer sb = new StringBuffer(1024) ; 

			sb.append("alert('"+message+"');") ;
			sb.append("history.back()") ;
			//sb.append("history.back();return false;") ;
			pDoPrintWriteFlush(response,sb.toString()) ; 

		} catch (Exception e) {

			e.printStackTrace() ; 

		}

	}
	
	/**
	 * 사용자 Javascript 를 출력한다 . 스크립트 여는 테그 <script></script> 는 필요 없이 alert('테스트'); 이런식으로 처리 할수 있다. ; 는 꼭첨부
	 * @param response
	 * @param sb
	 * @throws Exception
	 */
	public  void userScript(HttpServletResponse response,StringBuffer sb) {
		try {
			pDoPrintWriteFlush(response,sb.toString()); 
		} catch (Exception e) {
			e.printStackTrace() ; 
		}
	}
		
	/**
	 * 사용자 Javascript 를 출력한다 . 스크립트 여는 테그 <script></script> 는 필요 없이 alert('테스트'); 이런식으로 처리 할수 있다. ; 는 꼭첨부
	 * @param response
	 * @param sb
	 * @throws Exception
	 */
	public  void userScript(HttpServletResponse response,String str) {
		try {
			pDoPrintWriteFlush(response,str) ; 
		} catch (Exception e) {
			e.printStackTrace() ; 
		}
	}


	/**
	 * 자신의 창을 닫는다 
	 * @param response
	 * @param openerReloadCheck 부모창을 리로드 할지 여부 true 리로드 
	 */

	public void doSelfCloseAndOpenerReload(HttpServletResponse response ,boolean openerReloadCheck){
		try {
			StringBuffer sb = new StringBuffer(1024) ; 
			String script = doSelfCloseAndOpenerReloadMessage(sb,openerReloadCheck) ; 
			pDoPrintWriteFlush(response, script) ; 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	
	
	/**

	 * 메시지 출력 후 자신의 창을 닫는다 .
	 * @param response
	 * @param message	출력 메시지 alert 를 출력 한다 
	 * @param openerReloadCheck  부모창을 리로드 할지 여부 true 리로드 
	 */
	public void doSelfCloseAndOpenerReload(HttpServletResponse response,String message,boolean openerReloadCheck){

		try {
			StringBuffer sb = new StringBuffer(1024) ; 
			//sb.append("alert('"+strToAlert(message)+"');");
			sb.append("alert('"+message+"');");
			String script = doSelfCloseAndOpenerReloadMessage(sb,openerReloadCheck) ; 
			pDoPrintWriteFlush(response, script) ; 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	
	
	private String doSelfCloseAndOpenerReloadMessage(StringBuffer sb,boolean reloadCheck){
		if(reloadCheck){
			sb.append("opener.location.reload();"); 
		}
		sb.append("if(/MSIE/.test(navigator.userAgent)){"); 
		sb.append("if(navigator.appVersion.indexOf('MSIE 7.0')>=0){");
		sb.append("window.open('about:blank','_self').close();");
		sb.append("}");
		sb.append("else{");
		sb.append("window.opener=self;");
		sb.append("self.close();");
		sb.append("}");
		sb.append("}") ;
		return sb.toString() ; 

	}

	
	
	private void pDoPrintWriteFlush(HttpServletResponse response,String jsMessage)throws Exception{

		response.setContentType(_contentType) ; 
		PrintWriter out = response.getWriter() ;
			out.println(_jsStartTag) ; 
			out.println(jsMessage) ; 
			out.println(_jsEngTag) ; 
		out.flush() ; 
		out.close() ; 

	}	
	
}
