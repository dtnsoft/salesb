<%@ include file="/WEB-INF/views/salesb/top.jsp" %>
<script>

	//사용자 수정
	function fcCustomer_modify(){

		var frm=document.customerModifyForm;
		
		if(frm.change_password.value==''){
			
			if (confirm('고객정보를 변경 하시겠습니까?')){ 

				document.customerModifyForm.pw_modifyYn.value='N';
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/common/customermodify",
			           data:$("#customerModifyForm").serialize(),
			           success: function(result) {

							if(result=='1'){
								 alert('고객 정보 변경을 성공했습니다.');
							} else{
								 alert('고객 정보 변경을 실패했습니다.');
							}

			           },
			           error:function(){
			        	   
			        	    alert('고객 정보 변경을 실패했습니다.');

			           }

			    });
				
			}

		}else{
			

			if(frm.cur_password.value==''){
				alert('현재 비밀번호를 입력 하시기 바랍니다.');
				return;
			}
			
			if(frm.change_password.value==''){
				alert('변경 비밀번호를  입력 하시기 바랍니다.');
				return;
			}
			
			if(frm.re_password.value==''){
				alert('변경 비밀번호를 재입력 하시기 바랍니다.');
				return;
			}

			$.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/common/passwordcheck",
		           data: "curPwd=" + frm.cur_password.value,
		           success: function(result) {

		        	   frm.encpassword.value=result;
						
		           },
		           error:function(){
		        	   alert('Error: ' + e);
		           }
		    });

			if(frm.encpassword.value!=frm.regPassword.value){
				alert('현재 비밀번호가 맞지 않습니다.');
				frm.encpassword.value='';
				frm.cur_password.value='';
				frm.change_password.value='';
				frm.re_password.value='';
				frm.sbPw.value='';
				frm.cur_password.focus(1);
				return;
			}
			
			if(frm.cur_password.value==frm.change_password.value){
				alert('변경 비밀번호가 현재 비밀번호와 동일합니다.');
				frm.encpassword.value='';
				frm.cur_password.value='';
				frm.change_password.value='';
				frm.re_password.value='';
				frm.sbPw.value='';
				frm.cur_password.focus(1);
				return;
			}
			
			if(frm.re_password.value!=frm.change_password.value){
				alert('변경 비밀번호가 재입력 비밀번호와 같지 않습니다.');
				frm.encpassword.value='';
				frm.cur_password.value='';
				frm.change_password.value='';
				frm.re_password.value='';
				frm.sbPw.value='';
				frm.cur_password.focus(1);
				return;
			}

	    	if (confirm('비밀번호를 변경 하시겠습니까?')){ 
	    		
	    		frm.sbPw.value=frm.change_password.value;

				document.customerModifyForm.pw_modifyYn.value='Y';

			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/common/customermodify",
			           data:$("#customerModifyForm").serialize(),
			           success: function(result) {
	
							if(result=='1'){
								 alert('고객 정보 변경을 성공했습니다.');
							} else{
								 alert('고객 정보 변경을 실패했습니다.');
							}
							
			           },
			           error:function(){
			        	   
			        	    alert('고객 정보 변경을 실패했습니다.');
			        	    
			           }
			    });
		    
	    	}
		}
	}
	
	var chatstate='N';
	
	function fcChat() {
		
		if(chatstate=='N'){
			$('#chatid').attr("style","display:block");
			chatstate='Y';
		}else{
			$('#chatid').attr("style","display:none");
			chatstate='N';
		}
		
    };

</script>
<div id="wrap" class="wrap" >
	<div class="container">
	<!-- container -->
	  <form:form class="form-inline" role="form" commandName="customerVO" id="customerModifyForm" name="customerModifyForm" method="post" action="">
	     <input type="hidden" id="pw_modifyYn" name="pw_modifyYn" value="Y" >
	   	 <input type="hidden" id="sbPhoneNumber" name="sbPhoneNumber" value="${customer.sbPhoneNumber}" > 
		 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;사용자 정보설정
		 </p>
		 <!--// 타이틀 -->
		 <div class="well">
	           	<h5> <strong><em class="num">1.</em></strong>지도옵션설정</h5>
	             	<h5>
	             		<label class="radio-inline">
					  <input type="radio" name="mapradioId" id="mapradioId"  onChange="setMap('google')"> Google
					</label>
		            <label class="radio-inline">
					   <input type="radio" name="mapradioId" id="mapradioId" checked onChange="setMap('daum')"> Daum
					</label>
				</h5>
				<h5> <a href="javascript:fcMap();" >위치조회</a></h5>
	         </div>
		  <div class="well">
	           	<h5> <strong><em class="num">2. </em></strong>실시간 문의하기</h5>
				<h5> <a href="javascript:fcChat();" >문의하기</a></h5>
				 <div id="chatid" style="display:none">
		         	 <p class="tx2">
						<iframe id="chatframe" src="http://dev.salesb.net:19002/" width="100%" height="300"></iframe>
		             </p>
	       		 </div>
	         </div>
	      <div class="well">
	           	<h5> <strong><em class="num">3. </em></strong>사용자 정보</h5>
					<p class="tx2">
						[sns info] : ${restfulltype} 
						<br>
						[sns name] : ${name}
						
						<c:if test="${photo!=''}">
							<br>
				        	<img src="${photo}"/>
				       	</c:if>
	             </p>
	      </div> 
          <div class="well">
           	<h5> <strong><em class="num">4. </em></strong>비밀번호 설정</h5>
				<table  class="table table-bordered">
			  <thead></thead>
			  <tbody>
			  	<tr>
		          <td style="background-color:#F5F5F5">현재 비밀번호</td>
		          <input type="hidden" id="regPassword" name="regPassword" value="${customer.sbPw}" > 
		          <input type="hidden" id="encpassword" name="encpassword" value="" > 
		          <input type="hidden" id="sbPw" name="sbPw" value="" > 
		          <td><input type="password" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="현재 비밀번호"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">변경비밀번호</td>
		          <td><input type="password" class="form-control" id="change_password" name="change_password"  maxlength="50"  value=""  placeholder="변경 비밀번호"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">재입력</td>
		          <td><input type="password" class="form-control" id="re_password" name="re_password"  maxlength="50"  value=""  placeholder="재입력"></td>
			    </tr>
			  </tbody>
		    </table>
         </div> 
     	 <div class="clm_detail_btn">
	         <div class="clm_btn">
	           <a href="javascript:fcCustomer_modify();" class="btn_ty2">수정</a>
	         </div>
         </div>
  	</form:form>
  </div>
</div>

