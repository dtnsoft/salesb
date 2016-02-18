<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.net.*" %>
<%@ page import="com.offact.framework.util.StringUtil"%>
<%
	String ipaddress = InetAddress.getLocalHost().getHostAddress();
	if (ipaddress == null) ipaddress = InetAddress.getLocalHost().getHostAddress();
	
	String sClientIP = request.getRemoteAddr();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>salesb</title>
<link href="<%= request.getContextPath() %>/css/issue_style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}

img {margin:0; padding:0; vertical-align:top; line-height:0; border:0;}
-->
</style>
<script>

//login 처리
function goLogin() {
 
	var id = $('#id').val();
	var pwd = $('#pwd').val();
	
	if(id==''){
		alert('ID를 입력하세요');
		$('#id').focus();
		return;
	}
	
	
	if(pwd==''){
		alert('Password 를 입력하세요');
		$('#pwd').focus();
		return;
	}
	
    //alert('id:'+id);
	//alert('pw:'+pwd);
	
	//alert( $("input[name=saveId]:checkbox:checked").val());
	var chkCnt = $("input[name=saveId]:checkbox:checked").length;

	if( $("input[name=saveId]:checkbox:checked").val()=='on'){
		//alert($('#id').val());
		setCookie("offact_UserId", $('#id').val());
	} else {
		setCookie("offact_UserId", "");
	}
	
	//alert(chkCnt);
	
	//$('#loginForm').attr({action:"<%= request.getContextPath() %>/admin/login"});

	try {
		loginForm.submit();
	} catch(e) {}

}

document.onkeypress = keyPress ;

function keyPress(){
	var ieKey = window.event.keyCode ;
	if( ieKey == 13 ){
		goLogin();
	}
}



function loginInit(){

	var frm=document.loginForm;

	var cUserId = getCookie("offact_UserId");

	if( cUserId != null && trim(cUserId) != '' && cUserId != 'null' ){
		frm.id.value = getCookie("offact_UserId");
		frm.saveId.checked = true;
	}

	if(frm.id.value == "" ) {
		frm.id.focus();
	} else {
		frm.pwd.focus();
	}

}
</script>
</head>
 <body onload='loginInit()'>
    <div class="container">
      <br><br><br><br><br>
      <h2>SalesBaron Admin Management</h2>
      <form method="post"  id="loginForm" name="loginForm"  role="form" action="<%= request.getContextPath() %>/admin/login">
        <div class="form-group">
          <label for="email">id:</label>
          <input type="id" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password">
        </div>
       <button type="submit" class="btn btn-default" onclick="goLogin()">Submit</button>
      </form>
      <div class="checkbox">
      <fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>
	  <div id="status">
	  </div>
      </div>
      <br>
      <div
	  class="fb-like"
	  data-share="true"
	  data-width="450"
	  data-show-faces="true">
	</div>
    </div>
  </body>
</html>
