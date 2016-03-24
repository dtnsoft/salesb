<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
		
        <script type="text/javascript">

            // 결제창 종료후 인증데이터 리턴 함수
            function auth_data( frm )
            {
                var auth_form     = document.form_auth;
                var nField        = frm.elements.length;
                var response_data = "";

                // up_hash 검증 
                if( frm.up_hash.value != auth_form.veri_up_hash.value )
                {
                    alert("up_hash 변조 위험있음");
                    
                }              
                
               /* 리턴 값 모두 찍어보기 (테스트 시에만 사용) */
                var form_value = "";

                for ( i = 0 ; i < frm.length ; i++ )
                {
                    form_value += "["+frm.elements[i].name + "] = [" + frm.elements[i].value + "]\n";
                }
                alert(form_value);
            }
            
            // 인증창 호출 함수
            function auth_type_check()
            {
                var auth_form = document.form_auth;
    
                if( auth_form.ordr_idxx.value == "" )
                {
                    alert( "주문번호는 필수 입니다." );
    
                    return false;
                }
                else
                {
                    if( ( navigator.userAgent.indexOf("Android") > - 1 || navigator.userAgent.indexOf("iPhone") > - 1 ) == false ) // 스마트폰이 아닌경우
                    {
	                    var return_gubun;
	                    var width  = 410;
	                    var height = 500;
	
	                    var leftpos = screen.width  / 2 - ( width  / 2 );
	                    var toppos  = screen.height / 2 - ( height / 2 );
	
	                    var winopts  = "width=" + width   + ", height=" + height + ", toolbar=no,status=no,statusbar=no,menubar=no,scrollbars=no,resizable=no";
	                    var position = ",left=" + leftpos + ", top="    + toppos;
	                    var AUTH_POP = window.open('','auth_popup', winopts + position);
                    }
                    
                    auth_form.target = "auth_popup"; // !!주의 고정값 ( 리턴받을때 사용되는 타겟명입니다.)
                    auth_form.action = "<%= request.getContextPath() %>/kcp/kcpcert_proc_req.jsp"; // 인증창 호출 및 결과값 리턴 페이지 주소
                    
                    return true;
                }
            }
    
            /* 예제 */
            window.onload=function()
            {
                var today            = new Date();
                var year             = today.getFullYear();
                var month            = today.getMonth() + 1;
                var date             = today.getDate();
                var time             = today.getTime();
                var year_select_box  = "<option value=''>선택 (년)</option>";
                var month_select_box = "<option value=''>선택 (월)</option>";
                var day_select_box   = "<option value=''>선택 (일)</option>";
                
                if(parseInt(month) < 10) {
                    month = "0" + month;
                }
    
                if(parseInt(date) < 10) {
                    date = "0" + date;
                }
    
                year_select_box = "<select name='year' class='frmselect' id='year_select'>";
                year_select_box += "<option value=''>선택 (년)</option>";
       
                for(i=year;i>(year-100);i--)
                {
                    year_select_box += "<option value='" + i + "'>" + i + " 년</option>";
                }
                
                year_select_box  += "</select>";
                month_select_box  = "<select name=\"month\" class=\"frmselect\" id=\"month_select\">";
                month_select_box += "<option value=''>선택 (월)</option>";
                
                for(i=1;i<13;i++)
                {
                    if(i < 10)
                    {
                        month_select_box += "<option value='0" + i + "'>" + i + " 월</option>";
                    }
                    else
                    {
                        month_select_box += "<option value='" + i + "'>" + i + " 월</option>";
                    }
                }
                
                month_select_box += "</select>";
                day_select_box    = "<select name=\"day\"   class=\"frmselect\" id=\"day_select\"  >";
                day_select_box   += "<option value=''>선택 (일)</option>";
                for(i=1;i<32;i++)
                {
                    if(i < 10)
                    {
                        day_select_box += "<option value='0" + i + "'>" + i + " 일</option>";
                    }
                    else
                    {
                        day_select_box += "<option value='" + i + "'>" + i + " 일</option>";
                    }
                }
                
                day_select_box += "</select>";
                
                document.getElementById( "year_month_day"  ).innerHTML = year_select_box + month_select_box + day_select_box;
            }

            function goRigist(){
            	
            	var frm=document.RegistForm;
            	
            	if(frm.sbEmail.value==''){
    				alert('인증하실 이메일 정보가 없습니다.');
    				return;
    			}
            	
            	if(frm.sbPhoneNumber.value==''){
    				alert('인증된 핸드폰 번호가 없습니다.');
    				return;
    			}
            	
           		if(frm.tokenstate.value!='1'){
       				alert('인증확인을 하시기 바랍니다.');
       				return;
       			}
            	
            	if(frm.sbPw.value==''){
    				alert('등록하실 비밀번호를  입력 하시기 바랍니다.');
    				return;
    			}
    			
    			if(frm.customerRePw.value==''){
    				alert('등록하실 비밀번호를 재입력 하시기 바랍니다.');
    				return;
    			}

    			if(frm.sbPw.value!=frm.customerRePw.value){
    				alert('등록하실 비밀번호가 재입력 비밀번호와 같지 않습니다.');
    				frm.sbPw.value='';
    				frm.customerRePw.value='';
    				
    				frm.sbPw.focus(1);
    				return;
    			}

    			if(frm.useCheck.checked==false){
    				alert('이용약관 위치기반 서비스 동의를 하시기 바랍니다.');
    				return;
    			}
    			
      			if(frm.privateCheck.checked==false){
    				alert('개인정보 수집 및 이용에 대해 동의를 하시기 바랍니다.');
    				return;
    			}

    			//if (confirm('회원 가입을 하시겠습니까?')){ 
    				
    				setCookie("addys_customerkey", $('#sbPhoneNumber').val());
    	    		
	    		    $.ajax({
	    		        type: "POST",
	    		        async:false,
	    		           url:  "<%= request.getContextPath() %>/common/customerregist",
	    		           data:$("#RegistForm").serialize(),
	    		           success: function(result) {
	
	    						if(result=='1'){
	    							 alert('등록메일이 발송되었습니다.\n등록하신 메일을 통해 인증후 로그인 하시기 바랍니다.');

	    							 if('${type}'==='survey'){
	    								 location.href="<%= request.getContextPath() %>/surveyloginform"; 
	    							 }else{
	    								 location.href="<%= request.getContextPath() %>/customerloginform"; 
	    							 }
	    							 
	    						} else if(result=='2'){
	    							 alert('인증요청 된 정보가 없습니다.');
	    						} else if(result=='3'){
	    							 alert('인증번호 오류\n재인증 하시기 바랍니다.');
	    						} else {
	    							 alert('등록을 실패했습니다.');
	    						}
	
	    		           },
	    		           error:function(){
	    		        	   
	    		        	   alert('등록을 실패했습니다.');
	    		           }
	    		    });
    		    
    	    	//}
            }	
            function getToken(){
            	
            	var frm=document.RegistForm;
            	
            	if(frm.sbEmailView.value==''){
    				alert('인증하실 이메일 번호가 없습니다.');
    				return;
    			}
            	
            	if(frm.sbPhoneNumberView.value==''){
    				alert('인증하실 핸드폰 번호가 없습니다.');
    				return;
    			}
            	
            	frm.sbPhoneNumber.value=frm.sbPhoneNumberView.value;
            	frm.sbEmail.value=frm.sbEmailView.value;
            	
            	$.ajax({
    		        type: "POST",
    		        async:false,
    		           url:  "<%= request.getContextPath() %>/common/gettoken",
    		           data:$("#RegistForm").serialize(),
    		           success: function(result) {

    						if(result=='0'){
    							
    						    alert('인증요청을 성공했습니다.\n문자로 발송된 인증번호를 입력하시기 바랍니다.');
    						    frm.sbEmailView.disabled=true;
    							frm.sbPhoneNumberView.disabled=true;
    							frm.tokenView.disabled=false;
    							//document.all('completebtn').style.display="inline";
    							document.all('completebtn').disabled=false;

    						} else if(result=='1'){
    							
	   							 alert('이미 등록된 핸드폰 번호입니다.');
						    } else{
						    	
    							 alert('인증요청을 실패했습니다.');
    						}
    						
    						 frm.tokenstate.value=result;

    		           },
    		           error:function(){
    		        	   
    		        	   alert('[error]인증요청을 실패했습니다.');
    		        	   frm.tokenstate.value='-1';
    		           }
    		    });
            }	 
            function getTokenConfirm(){
            	
            	var frm=document.RegistForm;
            	
           		if(frm.tokenstate.value!='0'){
           			
           			if(frm.tokenstate.value=='1'){
           				alert('이미 인증확인이 된 번호입니다.');
           				return;
           			}
       				alert('인증요청을 하시기 바랍니다.');
       				return;
       			}
           		
            	if(frm.sbPhoneNumber.value==''){
    				alert('인증하실 핸드폰 번호가 없습니다.');
    				return;
    			}
            	
            	if(frm.tokenView.value==''){
    				alert('인증번호가 없습니다.');
    				return;
    			}
            	
            	frm.token.value=frm.tokenView.value;
            	
            	$.ajax({
    		        type: "POST",
    		        async:false,
    		           url:  "<%= request.getContextPath() %>/common/gettokenconfirm",
    		           data:$("#RegistForm").serialize(),
    		           success: function(result) {
    		        	  
    						if(result=='1'){
    							
    							 //alert('인증요청을 성공했습니다.\n발송된 인증번호를 입력하시기 바랍니다.');
    							 document.all('pwform').style.display="inline";
    							 //document.all('agreeform').style.display="inline";
    							 document.all('reqbtn').style.display="none";
    							 //document.all('completebtn').style.display="none";
    							 document.all('completebtn').disabled=true;
    							 frm.tokenView.disabled=true;
                                
    						} else if(result=='3'){
    							
	   							 alert('인증정보 오류');
	   							 
						    } else{
						    	
    							 alert('인증확인을 실패했습니다.');
    							 
    						}
    						
    						 frm.tokenstate.value=result;

    		           },
    		           error:function(){
    		        	   
    		        	   alert('[error]인증확인을 실패했습니다.');
    		        	   frm.tokenstate.value='-1';
    		           }
    		    });
            }
            
         function fbRegist(){
        	 
        	 alert('서비스 준비중입니다.');
        	 return;
      		  
    		    var loginURL='https://www.facebook.com/dialog/oauth';
    		    var fbAppId='${facebookfbAppId}';
    		    var scope='public_profile';
    		    var redirectURL='${redirectUrl}/salesb/facebooklogin';
    	
    		    top.location.href=loginURL + '?client_id=' + fbAppId + '&redirect_uri=' + redirectURL +'&response_type=token&scope=' + scope;
    	
    	  }
            
            
          function kakaoRegist(){
           	 
           	 alert('서비스 준비중입니다.');
           	 return;
           	 
    		    var loginURL='https://kauth.kakao.com/oauth/authorize';
    		    var client_id='${kakaoclient_id}';
    		    var redirectURL='${redirectUrl}/salesb/kakaologin';
    		    
    		    top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=code';
    		    
    	  }
            
          function naverRegist(){
           	 
           	 alert('서비스 준비중입니다.');
           	 return;
           	 
    		    var loginURL='https://nid.naver.com/oauth2.0/authorize';
    		  	var client_id='${naverclient_id}';
    			//alert('${state}');
    		    var redirectURL='${redirectUrl}/salesb/naverlogin';
    		  	
    		    top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=code&state=${state}';
    		    
    	  }
          
          function useShow(){
        	  
        		 document.all('useShow').style.display="inline";
        		 document.all('useCloseBtn').style.display="inline";
        		 document.all('useBtn').style.display="none";
        		 
        		 document.all('privateBtn').style.display="inline";
        		 document.all('privateCloseBtn').style.display="none";
        		 document.all('privateShow').style.display="none";
          }
          
          function privateShow(){
        		
        		 document.all('useShow').style.display="none";
        		 document.all('useCloseBtn').style.display="none";
        		 document.all('useBtn').style.display="inline";
        		 
        		 document.all('privateBtn').style.display="none";
        		 document.all('privateCloseBtn').style.display="inline";
        		 document.all('privateShow').style.display="inline";
        	  
          }
          
          function useClose(){
        	  
     		 document.all('useShow').style.display="none";
     		 document.all('useCloseBtn').style.display="none";
     		 document.all('useBtn').style.display="inline";
     		 
     		 document.all('privateBtn').style.display="inline";
     		 document.all('privateCloseBtn').style.display="none";
     		 document.all('privateShow').style.display="none";
	       }
	       
	       function privateClose(){
	     		
	     		 document.all('useShow').style.display="none";
	     		 document.all('useCloseBtn').style.display="none";
	     		 document.all('useBtn').style.display="inline";
	     		 
	     		 document.all('privateBtn').style.display="inline";
	     		 document.all('privateCloseBtn').style.display="none";
	     		 document.all('privateShow').style.display="none";
	     	  
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
		  <form commandName="customerVo"   id="RegistForm" name="RegistForm"  method="post" role="form" action="<%= request.getContextPath() %>/customer/regist">
	        <input type="hidden" name="tokenstate"          id="tokenstate"         value="-1"  />
		    <div id="container">
		      <div class="m_content form" >
		      <!-- 타이틀 -->
        <div class="clm_acdo_tit">
          <h1>계정생성</h1>
          <div class="clm_acdo_tit_left">
          <c:choose>
               <c:when test="${key=='N'}">
				 <a href="<%= request.getContextPath() %>/intro" class="btn b_prev"><span class="sp_prev">취소</span></a>
			</c:when>
			<c:otherwise>
				 <a href="<%= request.getContextPath() %>/orderintro?key=${key}" class="btn b_prev"><span class="sp_prev">취소</span></a>
			</c:otherwise>
		  </c:choose>
          </div>
        </div>
        <!--// 타이틀 --> 
		        <div class="m_resbx">
		          <!-- 핸드폰번호 등록 -->
		            <div class="m_result id">
		              <h6  class="m_sch_tp"></h6>
		              <ul class="schinp_list">
		                <li>
		                  <label for="" class="blind">이메일</label>
		                  <span class="inpbx certi">
		                  <input type="text" id=sbEmailView name="sbEmailView" placeholder="name@example.com">
		                  <input type="hidden" id=sbEmail name="sbEmail"  value=""/> 
		                  </span><a href="#"  class="bn_certi" >메일인증</a></li>
		                   <li>
		                  <label for="" class="blind">핸드폰번호</label>
		                  <span class="inpbx certi">
		                  <input type="text" id=sbPhoneNumberView name="sbPhoneNumberView" placeholder="핸드폰번호 등록">
		                  <input type="hidden" id=sbPhoneNumber name="sbPhoneNumber"  value=""/> 
		                  </span><a href="javascript:getToken()" id="reqbtn" class="bn_certi" >인증요청</a></li>
		                <li>
		                  <label for="" class="blind">인증번호</label>
		                  <span class="inpbx certi">
		                  <input type="password" id="tokenView" name="tokenView" disabled placeholder="인증번호 입력">
		                  <input type="hidden" id=token name="token"  value=""/> 
		                  </span><a href="javascript:getTokenConfirm()" id="completebtn" disabled class="bn_certi" >인증확인</a></li>
		              </ul>
		            </div>
		          <!--//핸드폰번호 등록 --> 
		          <div id="agreeform" name="agreeform" >
		          <!-- 비밀번호 등록 -->
		          <div  id="pwform" name="pwform" style="display:none" >
		            <div class="m_result pw" >
		              <h4 class="m_sch_tp"> 비밀번호를 설정해주세요.</h4>
		              <ul class="schinp_list">
		                <li>
		                  <label for="inp_pw" class="blind">비밀번호</label>
		                  <span class="inpbx">
		                  <input type="password" name="sbPw" id="sbPw" placeholder="비밀번호 등록 (6~20 영문 숫자의 조합)">
		                  </span></li>
		                <li>
		                  <label for="inp_pw2" class="blind">비밀번호 재입력</label>
		                  <span class="inpbx">
		                  <input type="password" name="customerRePw" id="customerRePw" placeholder="비밀번호 재확인">
		                  </span></li>
		              </ul>
		            </div>

		          </div>
		          <!--//비밀번호 등록 --> 
		          <!-- 개인정보수집 동의 -->
		            <div class="m_result privacy" >
		              <h4 class="m_sch_tp">고객님의 이메일과 휴대폰 번호로 상품 판매를 위한<br>e-브로슈어 보안 토큰을 보내 드립니다.</h4>
		              <ul class="schinp_list">
		                <li>
		                  <label for="" class="">
		                                이용약관 및 위치기반 서비스 <input type="checkbox" id="useCheck" name="useCheck" value="" title="선택" /> <a href="javascript:useShow()" id="useBtn">보기</a> <a href="javascript:useClose()" id="useCloseBtn"  style="display:none" >닫기</a>
		                  <br><br><br>
		                                개인 정보 취급 방침 <input type="checkbox" id="privateCheck" name="privateCheck" value="" title="선택" /> <a href="javascript:privateShow()" id="privateBtn">보기</a> <a href="javascript:privateClose()" id="privateCloseBtn"  style="display:none" >닫기</a>
		                  <br><br>
		                  </label>
						</li>
						<li>
						<div id="useShow" style="display:none" >

						제1장 총칙


						제1조 (목적)
						
						본 약관은 '(주)오펙트'(이하 '회사'라 함)가 운영하는 인터넷 사이트 및 모바일 애플리케이션(이하 'salesb'이라 함)에서 제공하는 전자상거래 관련 서비스 및 기타 서비스(이하 '서비스'라 함)를 이용하는 자 간의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.
						
						제2조 (정의)
						
						① 'salesb'이란 '회사'가 컴퓨터 등 정보통신설비를 이용하여 '서비스'를 제공할 수 있도록 설정한 가상의 영업장을 말합니다.
						
						② '회원'이라 함은 '회사'에 개인정보를 제공하여 회원등록을 한 자로서, 'salesb'의 정보를 지속적으로 제공받으며, '회사'가 제공하는 '서비스'를 계속적으로 이용할 수 있는 자를 말합니다.
						
						③ '비회원'이라 함은 '회원'에 가입하지 않고 '회사'가 제공하는 '서비스'를 이용하는 자를 말합니다.
						
						④ '이용자'란 'salesb'에 접속하여 본 약관에 따라 '회사'가 제공하는 '서비스'를 이용하는 '회원' 및 '비회원'을 말합니다.
						
						⑤ '판매자'라 함은 'salesb'에 본인의 재화 및 용역(이하 '아이템'이라 함)을 등록하여 판매하거나 또는 제공할 의사로 '서비스'를 이용하는 자를 말합니다. 
						
						⑥ '구매자'라 함은 'salesb'에 등록된 '판매자'의 '아이템'을 구매하거나 또는 제공받을 의사로 '서비스'를 이용하는 자를 말합니다. 
						
						제3조 (약관 등의 명시와 설명 및 개정)
						
						① '회사'는 본 약관의 내용을 '이용자'가 쉽게 알 수 있도록 인터넷 사이트 및 모바일 어플리케이션에 공지합니다. 다만, 약관의 내용은 '이용자'가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.
						
						② '회사'는 전자상거래등에서의소비자보호에관한법률, 약관의규제에관한법률, 전자거래기본법, 전자서명법, 정보통신망이용촉진등에관한법률, 방문판매등에관한법률, 소비자보호법 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.
						
						③ '회사'가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 '이용자'가 알기 쉽도록 표시하여 공지합니다.
						
						④ '회사'가 약관을 개정할 경우에는 변경된 약관은 공지된 시점부터 그 효력이 발생하며, '이용자'는 약관이 변경된 후에도 본 '서비스'를 계속 이용함으로써 변경 후의 약관에 대해 동의를 한 것으로 간주됩니다.
						
						⑤ 본 약관에서 정하지 아니한 사항과 본 약관의 해석에 관하여는 전자상거래등에서의소비자보호에관한법률, 약관의규제등에관한법률, 공정거래위원회가 정하는 전자상거래등에서의소비자보호지침 및 관계법령 또는 상관례에 따릅니다.
						
						</div>
						<div id="privateShow" style="display:none" >

						개인정보의 수집 이용 목적 및 수집하는 개인정보 항목


						회사는 Salesb 서비스 제공을 위해 마스터 아이디(Master ID), 비밀번호 등 인증정보를 수집하고 Salesb 서비스 이용 기간 동안 옥션 및 G마켓 사이에 아이디(ID), 닉네임, 성명, 주민번호, 대표자명, 사업자등록번호, 사업자연락처, 주소, 연락처, 이메일(e-mail), 등록 상품 정보, 판매 상품 정산 정보, 제3자 제공 동의 정보 등을 공유하거나 연동하여 활용할 수 있습니다. 동 사항에 동의할 경우 해당 이용자의 사업자등록번호에 연동된 모든 아이디(ID)에 상기의 정보 공유 및 연동 활용 효력이 적용됩니다.
						
						
						개인정보의 보유 이용 기간
						
						
						회사는 이용자의 개인정보를 원칙적으로 고지 및 약정한 기간 동안 보유 및 이용하며 개인정보의 수집 및 이용목적이 달성되면 지체 없이 파기합니다. 또한, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.
						
						
						관련법령 및 회사 방침에 의한 정보보유 사유
						
						
						상법 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 법령에서 규정한 일정한 기간 동안 이용자 개인정보를 보관합니다. 이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.
						1.① 계약 또는 청약철회 등에 관한 기록◦보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
						◦보존 기간 : 5년
						
						2.② 대금결제 및 재화 등의 공급에 관한 기록◦보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
						◦보존 기간 : 5년
						
						3.③ 소비자의 불만 또는 분쟁처리에 관한 기록◦보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
						◦보존 기간 : 3년
						
						4.④ 접속에 관한 기록◦보존 이유 : 통신비밀보호법 제15조의2 및 시행령 제41조
						◦보존기간 : 3개월
						
						5.⑤ 부정거래기록◦부정거래의 배제 등 회사 방침에 의한 보존
						◦보존 기간 : 1년
						

						
						</div>
						</li>
		              </ul>
		            <div class="bnbox">
		              <a href="javascript:goRigist()" id="btn_change" class="bn_gray">등록하기</a>
		            </div>
		            </div>
		            </div>
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

var cust_frm = document.RegistForm;
var cust_key = getCookie("addys_customerkey");

if( cust_key != null && trim(cust_key) != '' && cust_key != 'null' ){
	cust_frm.sbPhoneNumberView.value = cust_key;
	cust_frm.sbPhoneNumber.value = cust_key;
}

</script>