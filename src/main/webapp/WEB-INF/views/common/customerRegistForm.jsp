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

    			if(frm.agreeradio[0].checked==false){
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
	    							 alert('등록이 완료 되었습니다.');

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
            	
            	if(frm.sbPhoneNumberView.value==''){
    				alert('인증하실 핸드폰 번호가 없습니다.');
    				return;
    			}
            	
            	frm.sbPhoneNumber.value=frm.sbPhoneNumberView.value;
            	
            	$.ajax({
    		        type: "POST",
    		        async:false,
    		           url:  "<%= request.getContextPath() %>/common/gettoken",
    		           data:$("#RegistForm").serialize(),
    		           success: function(result) {

    						if(result=='0'){
    							
    						    alert('인증요청을 성공했습니다.\n문자로 발송된 인증번호를 입력하시기 바랍니다.');
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
    						 //alert(frm.tokenstate.value);

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
 			 <a href="<%= request.getContextPath() %>/intro" class="btn b_prev"><span class="sp_prev">취소</span></a>
          </div>
        </div>
        <!--// 타이틀 --> 
		        <div class="m_resbx">
		          
		           <div class="m_result id">
		              <ul class="schinp_list">
		                <div class="bnbox">
			              <button type="button" class="bn_facebook" onclick="fbRegist()">FACEBOOK 으로 연결</button>
			            </div>
			            <div class="bnbox_kakao">
			              <button type="button" class="bn_kakao" onclick="kakaoRegist()">KAKAO 로 연결</button>
			            </div>
                        <div class="bnbox">
			              <button type="button" class="bn_naver" onclick="naverRegist()">NAVER 로 연결</button>
			            </div>
		              </ul>
		            </div>
		          
		          <h6  class="m_sch_tp">-또는-</h6>
		          <!-- 핸드폰번호 등록 -->
		            <div class="m_result id">
		              <h6  class="m_sch_tp"></h6>
		              <ul class="schinp_list">
		                <li>
		                  <label for="" class="blind">이메일</label>
		                  <span class="inpbx certi">
		                  <input type="text" id=sbEamilView name="sbEamilView" placeholder="name@example.com">
		                  <input type="hidden" id=sbEamil name="sbEamil"  value=""/> 
		                  </span><a href="javascript:getTokenEmail()" id="reqbtn" class="bn_certi" >인증요청</a></li>
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
		              <h4 class="m_sch_tp">개인정보 수집 및 이용에 대한 안내</h4>
		              <ul class="schinp_list">
		                <li>
		                  <label for="" class="">[필수] 수집 · 이용 항목</label>
		                  <table class="tbl-lease tbl-02 tbl-privacy" summary="개인정보 수집 및 이용에 대한 안내를 항목, 목적, 보유기간으로 나타낸 표.">		
								<colgroup>
									<col width="25%">
									<col>
									<col width="25%">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">항목</th>
										<th scope="col">목적</th>
										<th scope="col">보유기간</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>핸드폰번호/이메일</td>
										<td>고객 문의, 칭찬, 제안 등 민원 처리 결과 , A/S 회신 , 마케팅활용</td>
										<td>문의 처리 후 3년</td>
									</tr>
									<tr>
										<td>위치정보</td>
										<td>위치기반 서비스</td>
										<td>문의 처리 후 3년</td>
									</tr>
								</tbody>
							</table>
							<div class="tbl-refer">※ 최소한의 개인정보이므로 동의를 해주셔야 가입이 가능합니다.</div>
							<div class="agree-box">		               
               						 <dl class="rdo-agree">
									<dt>수집 · 이용 항목에 동의하십니까?</dt>
									<dd>
										<input type="radio" name="agreeradio" id="agreeradio" class="rdo-piu-y" checked><label for="rdo-piu-y">예</label>
										<input type="radio" name="agreeradio" id="agreeradio" class="rdo-piu-n"><label for="rdo-piu-n">아니오</label>
									</dd>
								</dl> 
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