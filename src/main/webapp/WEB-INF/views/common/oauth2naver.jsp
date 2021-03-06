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
<script type="text/javascript" src="<%= request.getContextPath() %>/js/json2.js"></script>
<script src="http://connect.facebook.net/en_US/all.js" language="JavaScript" type="text/javascript" ></script>

	<script>

    function parseQueryString(queryString) {
        var qs = decodeURIComponent(queryString),
            obj = {},
            params = qs.split('&');
        params.forEach(function (param) {
            var splitter = param.split('=');
            obj[splitter[0]] = splitter[1];
        });
        return obj;
    }
	
	function goLogin(){
	
		commonDim(true);
		
		var frm = document.loginForm;
		var cfrm = document.Check_form;
		 
	    var url=window.location.href;
	    //alert('${state}');
	    //alert(url);
	    
	    var s_state='${state}';

	    if (url.indexOf("code=") > 0) {
			queryString = url.substr(url.indexOf('?') + 1);
			//alert('queryString:'+queryString);
			obj = parseQueryString(queryString);

			var code = obj['code'];
			var state = obj['state'];
			
			if(state==s_state){	
				//alert('session code ok!');

				var URL='https://nid.naver.com/oauth2.0/token';

			    var client_id='${naverclient_id}';
			    var client_secret='${naverclient_secret}';
		
				var restfullurl=URL + '?client_id='+client_id+'&client_secret='+client_secret+'&grant_type=authorization_code&state='+state+'&code='+code;
		
				$.ajax({
				    type: "POST",
				    async:true,
				      url:  '<%= request.getContextPath() %>/common/restfullinfo?restfullurl='+encodeURIComponent(restfullurl),
				       success: function(data) {
	
						//alert('access_token : '+data.access_token);
						
						var access_token=data.access_token;
						var refresh_token=data.refresh_token;
						
						if(access_token!=null){
							
							var URL2='https://openapi.naver.com/v1/nid/getUserProfile.xml';
							var restfullurl3=URL + '?Bearer='+access_token;
							var authorizationToken =access_token;
					
							$.ajax({
							    type: "POST",
							    async:true,
							      url:  '<%= request.getContextPath() %>/common/naverlogin?restfullurl3='+encodeURIComponent(URL2)+'&access_token=Bearer '+encodeURIComponent(access_token),
							       success: function(data) {
	
									frm.id.value=data.id;
									frm.name.value=data.nickname;
									frm.photo.value=data.profile_image;
									frm.access_token.value=encodeURIComponent(access_token);
								
									cfrm.socialId2.value=data.id;
									
									if(data.customerKey=='N'){
										alert('Naver와 연동된 회원정보가 없습니다.\n기 등록 회원 이신경우 이메일 과 핸드폰 번호를 입력 하신후 연동가능합니다.');
										commonDim(false);
										document.all('container').style.display="inline";
									}else{
										
										frm.customerKey.value=data.customerKey;
										frm.customerPw.value=1;
										
										try {
											 document.loginForm.submit();
										} catch(e) {}
									}
							
							       },
							       error:function(){
							     	  
							     	  alert('error');	          
							
							       }
							 });
	
						}
	
				       },
				       error:function(){
				     	  
				     	  alert('error');	          
				
				       }
				 });
			
			
			}else{
				
				alert('session code error!!');
				
			}

		} 
	    
	}
	

	function goPrivateForm(){

		location.href="<%= request.getContextPath() %>/customerregistform";
		
	}
	
	function naverLink(){

		var frm = document.loginForm;
		var cfrm = document.Check_form;
    /*
		if(cfrm.sbEmail.value==''){
			alert('연동하실 이메일 번호가 없습니다.');
			return;
		}
		*/
		if(cfrm.sbPhoneNumber.value==''){
			alert('연동하실 핸드폰 번호가 없습니다.');
			return;
		}
		
		if(cfrm.sbPw.value==''){
			alert('연동하실 패스워드가 없습니다.');
			return;
		}

    	$.ajax({
	        type: "POST",
	        async:false,
	           url:  "<%= request.getContextPath() %>/common/naverlink",
	           data:$("#Check_form").serialize(),
	           success: function(result) {

				if(result=='00'){
					alert('등록된 고객정보가 없습니다.\n휴대폰 정보를 다시 확인 부탁드립니다.\n가입이 안되신 경우 회원가입 기능을 이용해 주세요');
				}else if (result=='01'){
					alert('등록하신 고객정보가 비활성화 상태입니다.\n가입하신 이메일 과 핸드폰으로 인증 부탁드립니다.');
				}else if (result=='02'){
					alert('등록하신 고객정보의  패스워드 오류입니다.');
				}else if (result=='03'){
					alert('페이스북 정보 연동에 실패했습니다.');
				}else {
					
					frm.customerKey.value=result;
					frm.customerPw.value=1;
					
					try {
						 document.loginForm.submit();
					} catch(e) {}
				}

	           },
	           error:function(){
	        	   
	        	   alert('[error]연동을 실패했습니다.');

	           }
	    });
		
	}
	
	</script>
</head>
<body oncontextmenu="return false;" ondragstart="return false;" onselectstart="return false;" onload="goLogin()">
     <div id="wrap" class="wrap" >
		  <!-- container -->
		    <form  id="loginForm" name="loginForm"  method="post" role="form" action="${domainUrl}">
			  <input type="hidden" name="loginType" value="sns" >
			
			  <input type="hidden" name="id" value="" >
			  <input type="hidden" name="name" value="" >
			  <input type="hidden" name="restfulltype" value="naver" >
			  <input type="hidden" name="photo" value="" >
			  <input type="hidden" name="access_token" value="" >
			  
			  <input type="hidden" id="groupId" name="groupId" value="BD008">
			  <input type="hidden" name="groupName" value="" >
			  <input type="hidden" id="customerKey" name="customerKey" value="" >
			  <input type="hidden" id="customerPw" name="customerPw" value="" >
			</form>
		    <div id="container" style="display:none">
		      <div class="m_content form" >
				<!-- 타이틀 -->
		        <div class="clm_acdo_tit">
		          <h1>NAVER 연동</h1>
		          <div class="clm_acdo_tit_left">
		 			 <a href="<%= request.getContextPath() %>/intro" class="btn b_prev"><span class="sp_prev">취소</span></a>
		          </div>
		        </div>
		        <!--// 타이틀 --> 
		        <div class="m_resbx">
		        <form commandName="customerVo"   id="Check_form" name="Check_form"  method="post" role="form"  method="post">
		        <input type="hidden" name="socialId2" id="socialId2" >
		            <!-- 핸드폰번호 등록 -->
		            <div class="m_result id">
 						<h6  class="m_sch_tp"></h6>
		              	<ul class="schinp_list">
		                 <li>
		                  <label for="inp_id" class="blind">이메일</label>
		                  <span class="inpbx">
		                  <input type="text" id="sbPhoneNumber" name="sbPhoneNumber" placeholder="핸드폰">
		                  <input type="hidden" name="sbEmail" id="sbEmail" >
		                  <input type="hidden" name="searchType" id="searchType" value="01">
		                  <span class="sp_login ico_id">&nbsp;</span></span></li>
		                 <li>
		                  <label for="inp_pw" class="blind">패스워드</label>
		                  <span class="inpbx">
		                  <input type="text" id="sbPw" name="sbPw" placeholder="패스워드">
		                  <span class="sp_login ico_pw">&nbsp;</span></span></li>
			            </ul>
		            </div>
		         </form>
		           <div class="m_result id">
		              <ul class="schinp_list">
		                   <div class="bnbox">
			              <button type="button" class="bn_naver" onclick="naverLink()">NAVER 연동</button>
			            </div>
			            <div class="bnbox">
				         <button type="button" class="bn_salesb2" onclick="goPrivateForm()">일반회원가입</button>
				       	</div>
		              </ul>
		            </div>
		            
		        </div>
		      </div>
		  </div>
		  <div id="footer" class="footer">
		    <span class="Copyright">Copyright 2015 ⓒ salesb Corp. All rights reserved. v1.0.0</span>
		  </div>
	  	  <!--//container -->
      </div>
</body>
</html>
