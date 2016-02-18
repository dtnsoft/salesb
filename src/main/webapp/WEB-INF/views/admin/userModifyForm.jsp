<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 수정
		function fcUserManage_modify(){

			var frm=document.userModifyForm;
			
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
				frm.password.value='';
				frm.cur_password.focus(1);
				return;
			}
			
			if(frm.cur_password.value==frm.change_password.value){
				alert('변경 비밀번호가 현재 비밀번호와 동일합니다.');
				frm.encpassword.value='';
				frm.cur_password.value='';
				frm.change_password.value='';
				frm.re_password.value='';
				frm.password.value='';
				frm.cur_password.focus(1);
				return;
			}
			
			if(frm.re_password.value!=frm.change_password.value){
				alert('변경 비밀번호가 재입력 비밀번호와 같지 않습니다.');
				frm.encpassword.value='';
				frm.cur_password.value='';
				frm.change_password.value='';
				frm.re_password.value='';
				frm.password.value='';
				frm.cur_password.focus(1);
				return;
			}

	    	if (confirm('비밀번호를 변경 하시겠습니까?')){ 
	    		
	    		frm.password.value=frm.change_password.value;

		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/manage/usermodify",
		           data:$("#userModifyForm").serialize()+"&workCode=CM003",
		           success: function(result) {

						if(result=='1'){
							 alert('비밀번호 변경을 성공했습니다.');
						} else{
							 alert('비밀번호 변경을 실패했습니다.');
						}
						
						$('#passwordModify').dialog('close');
						
						
		           },
		           error:function(){
		        	   
		        	   alert('비밀번호 변경을 실패했습니다.');
		        	   $('#passwordModify').dialog('close');
		           }
		    });
		    
	    	}
		}
		function fcMofify_close(){
			$('#passwordModify').dialog('close');
		}
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" role="form" commandName="userVO" id="userModifyForm" name="userModifyForm" method="post" action="">
      <input type="hidden" id="updateUserId" name="updateUserId" value="${userVO.updateUserId}" >
      <input type="hidden" id="pw_modifyYn" name="pw_modifyYn" value="Y" >
	    <div class="form-group">
	        <br>
	        <h4><strong><font style="color:#FF9900">※ 비밀번호는 한달주기로 변경 하시기 바랍니다.</font></strong></h4>
	        <br>
		    <table class="table table-bordered" >
		        <tr>
		          <th class='text-center' width="150px" style="background-color:#33b9ae" >비밀번호 변경일</th>
		          <th class='text-left'  width="250px"  >${userVO.pwdChangeDateTime}</th>
		      	</tr>
			 	<tr>
		          <th class='text-center' width="150px" style="background-color:#33b9ae" >사용자ID</th>
		          <th class='text-left'  width="250px"  ><c:out value="${userVO.userId}"></c:out></th>
		          <input type="hidden" id="userId" name="userId" value="${userVO.userId}" >
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#33b9ae" >사용자명</th>
		          <th class='text-left'><c:out value="${userVO.userName}"></c:out></th>
		          <input type="hidden" id="userName" name="userName" value="${userVO.userName}" >
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#33b9ae" >현재 비밀번호</th>
		          <th class='text-left'><input type="password" class="form-control" id="cur_password" name="cur_password" maxlength="50"  tabindex="2" value="" ></th>
		          <input type="hidden" id="regPassword" name="regPassword" value="${userVO.password}" > 
		          <input type="hidden" id="encpassword" name="encpassword" value="" > 
		          <input type="hidden" id="password" name="password" value="" > 
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#33b9ae" >변경 비밀번호</th>
		          <th class='text-left'><input type="password" class="form-control" id="change_password" name="change_password" maxlength="50"  tabindex="2" value="" ></th>
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#33b9ae" >비밀번호 재입력</th>
		          <th class='text-left'><input type="password" class="form-control" id="re_password" name="re_password" maxlength="50"  tabindex="2" value="" ></th>
		      	</tr>
		      	<input type="hidden" id="groupId" name="groupId" value="${userVO.groupId}" ></th>
		      	<input type="hidden" id="authId" name="authId" value="${userVO.authId}" ></th>
		      	<input type="hidden" id="auth" name="auth" value="${userVO.auth}" ></th>
		      	<input type="hidden" id="email" name="email" value="${userVO.email}" ></th>
		      	<input type="hidden" id="officePhone" name="officePhone" value="${userVO.officePhone}" ></th>
		      	<input type="hidden" id="mobliePhone" name="mobliePhone" value="${userVO.mobliePhone}" ></th>
		      	<input type="hidden" id="smsYn" name="smsYn" value="${userVO.smsYn}" ></th>
			  </table>
        	<button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_modify()">수정</button>
        	<button type="button" class="btn btn-danger" onClick="fcMofify_close()">닫기</button>
	    </div>
	  </form:form>
	</div>
  </body>
</html>

 

