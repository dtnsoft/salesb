<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
      
<link href="<%= request.getContextPath() %>/css/reset.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">

<title>Login</title>
<style></style>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<script src="http://connect.facebook.net/en_US/all.js" language="JavaScript" type="text/javascript" ></script>

<script>

    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	goLogin();
            return false;
        } else{
            return true;
        }
    }
	
	function goLogin(){
		
		var frm = document.loginForm;
		var customerKey = frm.customerKey.value;
		var customerPw =  frm.customerPw.value;
	
		if(customerKey==''){
			alert('핸드폰 번호를 입력하시기 바랍니다.');
			return;
		}
		
		if(customerPw==''){
			alert('Password를 입력하시기 바랍니다.');
			return;
		}

		setCookie("salesb_customerKey", $('#customerKey').val());
		setCookie("salesb_groupkey", $('#groupId').val());

		frm.groupName.value='물류정상';

		try {
			document.loginForm.submit();
		} catch(e) {}
		
	}

	function goPrivateForm(){
		
		setCookie("salesb_customerKey", $('#customerKey').val());
		setCookie("salesb_groupkey", $('#groupId').val());

		location.href="<%= request.getContextPath() %>/customerregistform";
		//location.href="<%= request.getContextPath() %>/customerprivateinfo";
		
	}
	
	function goPwSearch(){
		
		setCookie("salesb_customerKey", $('#customerKey').val());
		setCookie("salesb_groupkey", $('#groupId').val());

		location.href="<%= request.getContextPath() %>/customerpwform";
	}


	  function fbLogin(){
		  
		    var loginURL='https://www.facebook.com/dialog/oauth';
		    var fbAppId='${facebookfbAppId}';
		    var scope='public_profile';
		    var redirectURL='${redirectUrl}/salesb/facebooklogin';
	
		    top.location.href=loginURL + '?client_id=' + fbAppId + '&redirect_uri=' + redirectURL +'&response_type=token&scope=' + scope;
	
	  }
	  
	  function gbLogin(){
		  
		    var loginURL='https://accounts.google.com/o/oauth2/auth';
		    var client_id='${googleclient_id}';
		    var scope='https://www.googleapis.com/auth/userinfo.email';
		    var redirectURL='${redirectUrl}/salesb/googlelogin';
	
		    top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=token&scope=' + scope;
		  
	  }
	  
	  function pinLogin(){
		  
		    var loginURL='https://api.pinterest.com/oauth';
		    var client_id='${pinterestclient_id}';
		    var scope='read_public';
		    var redirectURL='${redirectUrl2}/pinterest';
		    
		  	top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=token&scope=' + scope;
		  
	  }
	  
	  function kakaoLogin(){
		  
		    var loginURL='https://kauth.kakao.com/oauth/authorize';
		    var client_id='${kakaoclient_id}';
		    var redirectURL='${redirectUrl}/salesb/kakaologin';
		    
		    top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=code';
		    
	  }
	  
	  function naverLogin(){
		  
		    var loginURL='https://nid.naver.com/oauth2.0/authorize';
		  	var client_id='${naverclient_id}';
			//alert('${state}');
		    var redirectURL='${redirectUrl}/salesb/naverlogin';
		  	
		    top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=code&state=${state}';
		    
	  }
</script>
</head>
<body>
<div id="wrap" class="wrap"  >
  <!-- 헤더 -->
  <header>
     <div class="mb_top"  id="header">
      <h1 class="head_logo"></h1>
    </div>
  </header>
  <!--//헤더 --> 
  <form  id="loginForm" name="loginForm"  method="post" role="form" action="<%= request.getContextPath() %>/customer/login">
  <input type="hidden" name="loginType" value="comunity" >
  <input type="hidden" name="groupName" value="" >
  <!-- container -->
  <div id="container">
    <div class="m_content" >
      <!-- 로그인영역 -->
      <div class="m_combx">
      <input type="hidden" id="groupId" name="groupId" value="BD008">
      <br></br>
        <fieldset>
          <legend>로그인 영역</legend>
          <form id="login_form" method="post">
            <!-- 로그인 -->
            <div class="m_inplogin">
              <ul class="m_inp_data">
                <li>
                  <label for="inp_id" class="blind">핸드폰번호</label>
                  <span class="inpbx">
                  <input type="text" id="customerKey" name="customerKey" placeholder="핸드폰번호">
                  <span class="sp_login ico_id">&nbsp;</span></span></li>
                <li>
                  <label for="inp_pw" class="blind">비밀번호</label>
                  <span class="inpbx">
                  <input type="password" id="customerPw" name="customerPw" placeholder="비밀번호">
                  <span class="sp_login ico_pw">&nbsp;</span></span></li>
              </ul>
            </div>
            <!-- //로그인 -->
            <!-- 로그인버튼 -->
            <div class="bnbox">
              <button type="button" class="bn_salesb" onclick="goLogin()">Login</button>
            </div>
            <div class="bnbox_kakao">
              <button type="button" class="bn_kakao" onclick="kakaoLogin()">Login with KaKao</button>
            </div>
             <div class="bnbox">
              <button type="button" class="bn_naver" onclick="naverLogin()">Login with NAVER</button>
            </div>
            <div class="bnbox">
              <button type="button" class="bn_facebook" onclick="fbLogin()">Login with Facebook</button>
            </div>
            <div class="bnbox">
              <button type="button" class="bn_google" onclick="gbLogin()">Login with Google</button>
            </div>
            <div class="bnbox_pin">
              <button type="button" class="bn_pinterest" onclick="pinLogin()">Login with Pinterest</button>
            </div>
            <!-- 
            <div class="bnbox">
              <fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>
            </div>
             -->
            <div id="status">
			</div>
            <!--//로그인버튼 -->
            <!-- 가입,비번찾기 -->
	        <div class="login_join">
	          <h3 class="line_tit"></h3>
	          <ul class="log_joinlist">
	            <li> <span class="tit">아직 회원이 아니세요?</span> <span class="txt"><a href="javascript:goPrivateForm()" class="bn_join">간편가입</a> </li>
	            <li> <span class="tit">정보를 잊어 버리셨나요?</span> <span class="txt"> <a href="javascript:goPwSearch()" class="bn_pw">비밀번호 찾기</a></span> </li>
	          </ul>
	        </div>
	        <!--//가입,비번찾기 -->
            <!-- 이용안내 -->
             <h3 class="line_tit"></h3>
            <div class="m_tip">
              <span class="ico_tip"></span>등록을 하시면 다음과 같은 서비스를 이용 가능하십니다.
              <ul class="m_tip_list">
                <li>1. 주문내역 조회</li>
                <li>2. 판매내역 조회</li>
              </ul>
            </div>
            <!--//이용안내 -->
          </form>
        </fieldset>
      </div>
      <!--//로그인영역 -->
    </div>
  </div>
  <!--//container -->
  </form>
  
  <div id=facebooklogin style="display:none"></div>
  
  <div id="footer" class="footer">
    <span class="Copyright">Copyright 2015 ⓒ salseb Corp. All rights reserved. v1.0.0</span>
  </div>
</div>
</body>
</html>
<script>

var cust_frm = document.loginForm;
var cust_key = getCookie("salesb_customerKey");
var group_key = getCookie("salesb_groupkey");

if( cust_key != null && trim(cust_key) != '' && cust_key != 'null' ){
	cust_frm.customerKey.value = cust_key;
}

if( group_key != null && trim(group_key) != '' && group_key != 'null' ){

}

</script>