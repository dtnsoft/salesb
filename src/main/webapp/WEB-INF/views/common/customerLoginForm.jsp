<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Login</title>

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/animate.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/style.css" rel="stylesheet">
    
    <link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">
    
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
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
			    var scope='email,public_profile,user_friends,publish_actions';
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
		  
		  var type='01'

		  function memeberLoginFrom2(){
		
			  var frm = document.loginForm;
			  frm.memberType.value=type;
				
			  if(type=='02'){
			      document.all('memberView').style.display="inline";
			      document.all('businessView').style.display="none";
			      type='01';
			     
			  }else{
				  document.all('memberView').style.display="none";
				  document.all('businessView').style.display="inline";
				  type='02';
			  }
	
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

<body class="gray-bg">
	<form  id="loginForm" name="loginForm"  method="post" role="form" action="<%= request.getContextPath() %>/customer/login">
				  <input type="hidden" name="loginType" value="comunity" >
				  <input type="hidden" name="groupName" value="" >
				  <input type="hidden" id="groupId" name="groupId" value="BD008">
				  <input type="hidden" name="memberType" value="02" >  
		   
    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">
                <c:if test="${key=='N'}">	
		            <div class="m_result id">
	<!-- 
						    <select id="flip-1" name="flip-1" data-role="slider">
						        <option value="off">사업자 인증</option>
						        <option value="on">회원인증</option>
						    </select>
						
								              	<h4 class="m_sch_tp">	
		              		<label class="radio-inline">
							  <input type="radio" name="memberTypeRadio" id="memberTypeRadio" value="02" checked onChange="memeberLoginFrom('02')"> 일반회원
							</label>
				            <label class="radio-inline">
							   <input type="radio" name="memberTypeRadio" id="memberTypeRadio" value="01" onChange="memeberLoginFrom('01')"> 사업자 회원
							</label>
						</h4>
	 -->
		              	<h4 class="m_sch_tp">	
		              		<label class="radio-inline">
							   <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example1" onClick="memeberLoginFrom2()">
                                    <label class="onoffswitch-label" for="example1">
                                        <span class="onoffswitch-inner" ></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
							</label>
						</h4>
		            </div>

                            
		            </c:if>
                </h1>

            </div>
            <h3>Welcome to Salesb</h3>
            <p><!--Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
            </p>
            <p>Login in. To see it in action.</p>
            <form class="m-t" role="form" id="login_form" method="post">
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
                
                <div class="form-group">
                    <input type="text" class="form-control" id="sbPhoneNumber" name="sbPhoneNumber" placeholder="핸드폰 번호" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="sbPw" name="sbPw" placeholder="비밀번호" required="">
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="goLogin()">Login</button>

                <a href="javascript:goPwSearch()"><small>Forgot password?</small></a>

            </div>
             <div id="businessView" style="display:none" >
             	<div class="form-group">
                    <input type="text" class="form-control" id="businessId" name="businessId" placeholder="회원아이디" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="businessPw" name="businessPw" placeholder="비밀번호" required="">
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="goBusiness()">Login</button>
             </div>
            </form>
            <p class="m-t"> <small>Copyright 2015 ⓒ salesb Corp. All rights reserved. v1.0.0</small> </p>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/jquery-2.1.1.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/bootstrap.min.js"></script>
</form>	
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