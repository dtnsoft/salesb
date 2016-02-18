<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 등록
		function fcUserManage_regist(){
			
			var frm=document.userRegistForm;
			
			if(frm.d_userId.value==''){
				alert('사용자 아이디를 입력하세요');
				return;
			}
			
			if(frm.password.value==''){
				alert('사용자 패스워드를 입력하세요');
				return;
			}
			
			if(frm.userName.value==''){
				alert('사용자명을 입력하세요');
				return;
			}

			if(frm.d_userId.disabled==false){
				alert('아이디 중복체크가 필요합니다.');
				return;
			}
			
			frm.userId.value=frm.d_userId.value;
			
			if(frm.authCode.value=='01' || frm.authCode.value=='02' || frm.authCode.value=='03'  ){//기본권한 (슈퍼관리자,관리자,일반)
				
				frm.auth.value=frm.authCode.value;
				frm.authId.value='';
			
			}else{//일반 상세권한
				
				if(frm.authCode.value=='04'){//일반(본사)
					
					frm.auth.value='03';
				    frm.authId.value='AD001';
					
				}else if(frm.authCode.value=='05'){//일반(staff)
					
					frm.auth.value='03';
				    frm.authId.value='STAFF';
				}
			}
			
			if (confirm('사용자 정보를 등록 하시겠습니까?')){ 
			
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/manage/userreg",
			           data:$("#userRegistForm").serialize(),
			           success: function(result) {
	
							if(result>0){
								 alert('사용자 등록을 성공했습니다.');
							} else{
								 alert('사용자 등록에 실패했습니다.');
							}
							
							$('#userManageRegist').dialog('close');
							fcUserManage_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('사용자 등록에 실패했습니다.');
			        	   $('#userManageRegist').dialog('close');
			           }
			    });
			}
		}
		function fcCheck(){
			
			var frm=document.userRegistForm;
			
			if(frm.d_userId.value==''){
				alert('사용자 아이디를 입력하세요');
				return;
			}
			
			 $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/manage/usercheck",
			           data:$("#userRegistForm").serialize(),
			           success: function(result) {

							if(result=='1'){
								 alert('이미 등록된 아이디 입니다.');
								 frm.d_userId.value='';
								 frm.userId.value='';
								 frm.d_userId.focus(1);
							} else{
								 
								 if (confirm('사용가능한 아이디 입니다.\n해당 아이디를 선택 하시겠습니까?')){ 
								 	frm.d_userId.disabled=true;
								 	frm.userId.value=frm.d_userId.value;
								 }else{
									frm.d_userId.disabled=false;
									frm.userId.value='';
								 }
							}
							
			           },
			           error:function(){
			        	   
			        	   alert('중복체크 호출에 실패했습니다.');
			        	 
			           }
			    });
		}
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" commandName="userVO" id="userRegistForm" name="userRegistForm" method="post" action="">
      <input type="hidden" id="createUserId" name="createUserId" value="${userVO.createUserId}" >
      <input type="hidden" id="userId" name="userId" value="" >
	    <div class="form-group">
		    <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>사용자id</th>
	          <th class='text-left'  width="250px"  >
	          <div class="form-inline">
	          <input type="text" class="form-control" id="d_userId" name="d_userId" maxlength="10"  tabindex="1" value="">
	          <button id="checkbtn" type="button" class="btn btn-info" onClick="fcCheck()" >중복체크</button>
    	      </div> 
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>password</th>
	          <th class='text-left'><input type="password" class="form-control" id="password" name="password" maxlength="50"  tabindex="2" value="" ></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>사용자명</th>
	          <th class='text-left'><input  type="text" class="form-control" id="userName"  name="userName" maxlength="25" tabindex="3" value=""></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>조직</th>
	          <th class='text-left'>	    	
	          	<select class="form-control" title="지점정보" id="groupId" name="groupId" value="" tabindex="4">
	                <c:forEach var="groupVO" items="${group_comboList}" >
	                	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
	                </c:forEach>
	            </select>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>권한</th>
	          <th class='text-left'>
	          	<select class="form-control" title="관리권한" id="authCode" name="authCode" value="" tabindex="5">
	                <option value="03">일반</option>
	                <option value="05">일반(staff)</option>
	                <option value="04">일반(본사)</option>
	                <option value="02">관리자</option>
	                <c:if test="${strAuth=='01'}"><option value="01">슈퍼관리자</option></c:if>
	       		</select></th>
	       		<input type="hidden" name="auth" id="auth" >
	       		<input type="hidden" name="authId" id="authId" >
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >email</th>
	          <th class='text-left'><input type="text" class="form-control" id="email" name="email" maxlength="25"  tabindex="6" value=""></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >officePhone</th>
	          <th class='text-left'><input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="20" tabindex="7" value=""> </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >mobilePhone</th>
	          <th class='text-left'><input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="20" tabindex="8" value="">   </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >sms수신여부</th>
	          <th class='text-left'>
	          	<select class="form-control" title="smsYn" id="smsYn" name="smsYn" value="N" tabindex="9">
	                <option value="N">미수신</option>
	                <option value="Y">수신</option>
	       		</select>    </th>
	      	</tr>
		  </table>
          <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_regist()">저장</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>

//$('#userId').focus(1); 
document.userRegistForm.userId.focus();
</script>
 

