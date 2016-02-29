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
<link href="<%= request.getContextPath() %>/css/comunity.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">

<title>Login</title>
<style></style>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<script src="http://connect.facebook.net/en_US/all.js" language="JavaScript" type="text/javascript" ></script>
  
<!-- JQuery Mobile을 사용하기 위해 반드시 필요한 태그
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
-->

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
		
		if(frm.memberType.value=='01'){
			frm.sbPhoneNumber.value=frm.businessId.value;
			frm.sbPw.value=frm.businessPw.value;
		}

		var sbPhoneNumber = frm.sbPhoneNumber.value;
		var sbPw =  frm.sbPw.value;
	
		if(sbPhoneNumber==''){
			alert('핸드폰 번호를 입력하시기 바랍니다.');
			return;
		}
		
		if(sbPw==''){
			alert('Password를 입력하시기 바랍니다.');
			return;
		}

		setCookie("salesb_sbPhoneNumber", $('#sbPhoneNumber').val());
		setCookie("salesb_groupkey", $('#groupId').val());

		frm.groupName.value='물류정상';

		try {
			document.loginForm.action="<%= request.getContextPath() %>/customer/login";
			document.loginForm.submit();
		} catch(e) {}
		
	}
	
	function goBusiness(){

		var frm = document.loginForm;

		if(frm.businessId==''){
			alert('사업자 회원 아이디를 입력하시기 바랍니다.');
			return;
		}
		
		if(frm.businessPw==''){
			alert('사업자 회원 Password를 입력하시기 바랍니다.');
			return;
		}

		try {
			document.loginForm.action="<%= request.getContextPath() %>/business/login";
			document.loginForm.submit();
		} catch(e) {}
	}

	function goPrivateForm(){
		
		setCookie("salesb_sbPhoneNumber", $('#sbPhoneNumber').val());
		setCookie("salesb_groupkey", $('#groupId').val());

		location.href="<%= request.getContextPath() %>/customerregistform";
		//location.href="<%= request.getContextPath() %>/customerprivateinfo";
		
	}
	
	function goPwSearch(){
		
		setCookie("salesb_sbPhoneNumber", $('#sbPhoneNumber').val());
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
	  
	  function memeberLoginFrom(type){
	
		  var frm = document.loginForm;
		  frm.memberType.value=type;
			
		  if(type=='02'){
		      document.all('memberView').style.display="inline";
		      document.all('businessView').style.display="none";
		     
		  }else{
			  document.all('memberView').style.display="none";
			  document.all('businessView').style.display="inline";
		  }

	  }
</script>
</head>

<body oncontextmenu="return false;" ondragstart="return false;" onselectstart="return false;">
     <div id="wrap" class="wrap" >
		 <!-- 헤더 -->
		  <header>
		   <div class="mb_top">
				<h1 class="head_logo"></h1>
			</div>
		  </header>
		  <!--//헤더 -->  
		  <!-- container -->
		  <form  id="loginForm" name="loginForm"  method="post" role="form" action="<%= request.getContextPath() %>/customer/login">
			  <input type="hidden" name="loginType" value="comunity" >
			  <input type="hidden" name="groupName" value="" >
			  <input type="hidden" id="groupId" name="groupId" value="BD008">
			  <input type="hidden" name="memberType" value="02" >   
		    <div id="container">
		      <div class="m_content form" >
				<!-- 타이틀 -->
		        <div class="clm_acdo_tit">
		          <h1>로그인</h1>
		          <div class="clm_acdo_tit_left">
		 			 <a href="<%= request.getContextPath() %>/intro" class="btn b_prev"><span class="sp_prev">취소</span></a>
		          </div>
		        </div>
		        <!--// 타이틀 --> 
		        <div class="m_resbx">
		        <form id="login_form" method="post">
		            <div class="m_result id">
	<!-- 
						    <select id="flip-1" name="flip-1" data-role="slider">
						        <option value="off">사업자 인증</option>
						        <option value="on">회원인증</option>
						    </select>
	 -->
		              	<h4 class="m_sch_tp">	
		              		<label class="radio-inline">
							  <input type="radio" name="memberTypeRadio" id="memberTypeRadio" value="02" checked onChange="memeberLoginFrom('02')"> 일반회원
							</label>
				            <label class="radio-inline">
							   <input type="radio" name="memberTypeRadio" id="memberTypeRadio" value="01" onChange="memeberLoginFrom('01')"> 사업자 회원
							</label>
						</h4>
		            </div>
		          <div id="memberView" >
		           <div class="m_result id">
		              <ul class="schinp_list">
		                <div class="bnbox">
			              <button type="button" class="bn_facebook" onclick="fbLogin()">FACEBOOK 으로 연결</button>
			            </div>
			            <div class="bnbox_kakao">
			              <button type="button" class="bn_kakao" onclick="kakaoLogin()">KAKAO 로 연결</button>
			            </div>
                        <div class="bnbox">
			              <button type="button" class="bn_naver" onclick="naverLogin()">NAVER 로 연결</button>
			            </div>
		              </ul>
		            </div>
		          	<h6  class="m_sch_tp">-또는-</h6>
		            <!-- 핸드폰번호 등록 -->
		            <div class="m_result id">
 						<h6  class="m_sch_tp"></h6>
		              	<ul class="schinp_list">
		                 <li>
		                  <label for="inp_id" class="blind">핸드폰번호</label>
		                  <span class="inpbx">
		                  <input type="text" id="sbPhoneNumber" name="sbPhoneNumber" placeholder="핸드폰번호">
		                  <span class="sp_login ico_id">&nbsp;</span></span></li>
		                 <li>
		                  <label for="inp_pw" class="blind">비밀번호</label>
		                  <span class="inpbx">
		                  <input type="password" id="sbPw" name="sbPw" placeholder="비밀번호">
		                  <span class="sp_login ico_pw">&nbsp;</span></span></li>
		                  
			             <!-- //로그인 -->
			             <div class="bnbox">
			              <button type="button" class="bn_salesb" onclick="goLogin()">Login</button>
			             </div>
			            </ul>
		            </div>
		           </div>
		           
		           <div id="businessView" style="display:none" >
		            <!-- 핸드폰번호 등록 -->
		            <div class="m_result id">
		              <h4 class="m_sch_tp">사업자 회원 아이디를 입력하세요</h4>
		              	<ul class="schinp_list">
		                 <li>
		                  <label for="inp_id" class="blind">회원아이디</label>
		                  <span class="inpbx">
		                  <input type="text" id="businessId" name="businessId" placeholder="회원아이디">
		                  <span class="sp_login ico_id">&nbsp;</span></span></li>
		                <li>
		                  <label for="inp_pw" class="blind">비밀번호</label>
		                  <span class="inpbx">
		                  <input type="password" id="businessPw" name="businessPw" placeholder="비밀번호">
		                  <span class="sp_login ico_pw">&nbsp;</span></span></li>
		                  
			            <!-- //로그인 -->
			            <div class="bnbox">
			              <button type="button" class="bn_salesb" onclick="goBusiness()">Login</button>
			            </div>
		              </ul>
		            </div>
		           </div>
		            
		            <!-- 가입,비번찾기 -->
			        <div class="login_join">
			          <h3 class="line_tit"></h3>
			          <ul class="log_joinlist">
			            <li> <span class="tit">비밀번호를 잊으셨나요?</span> <span class="txt"> <a href="javascript:goPwSearch()" class="bn_pw">비밀번호 찾기</a></span> </li>
			          </ul>
			        </div>
			        <!--//가입,비번찾기 -->
				</form>
		        </div>
		      </div>
		  </div>

          </form>
          
		  <div id="footer" class="footer">
		    <span class="Copyright">Copyright 2015 ⓒ salesb Corp. All rights reserved. v1.0.0</span>
		  </div>
	  	  <!--//container -->
      </div>

</body>
</html>
<script>

var cust_frm = document.loginForm;
var cust_key = getCookie("salesb_sbPhoneNumber");
var group_key = getCookie("salesb_groupkey");

if( cust_key != null && trim(cust_key) != '' && cust_key != 'null' ){
	cust_frm.sbPhoneNumber.value = cust_key;
}

if( group_key != null && trim(group_key) != '' && group_key != 'null' ){

}

</script>