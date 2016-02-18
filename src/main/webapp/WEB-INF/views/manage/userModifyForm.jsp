<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 수정
		function fcUserManage_modify(){

			var frm=document.userModifyForm;

			if(frm.password.value!=frm.regPassword.value){
				frm.pw_modifyYn.value='Y';
			}else{
				frm.pw_modifyYn.value='N';
			}
			
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

			if (confirm('사용자 정보를 수정 하시겠습니까?')){ 
				
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/manage/usermodify",
			           data:$("#userModifyForm").serialize(),
			           success: function(result) {
	
							if(result=='1'){
								 alert('사용자정보 수정을 성공했습니다.');
							} else{
								 alert('사용자정보 수정에 실패했습니다.');
							}
							
							$('#userManageModify').dialog('close');
							fcUserManage_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('사용자 등록에 실패했습니다.');
			        	   $('#userManageModify').dialog('close');
			           }
			    });
			}
		}
		
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" role="form" commandName="userVO" id="userModifyForm" name="userModifyForm" method="post" action="">
      <input type="hidden" id="updateUserId" name="updateUserId" value="${userVO.updateUserId}" >
      <input type="hidden" id="pw_modifyYn" name="pw_modifyYn" value="N" >
	    <div class="form-group">
		    <table class="table table-bordered" >
			 	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>사용자id</th>
		          <th class='text-left'  width="250px"  ><c:out value="${userVO.userId}"></c:out></th>
		          <input type="hidden" id="userId" name="userId" value="${userVO.userId}" >
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>password</th>
		          <th class='text-left'><input type="password" class="form-control" id="password" name="password" maxlength="50"  tabindex="2" value="${userVO.password}" ></th>
		          <input type="hidden" id="regPassword" name="regPassword" value="${userVO.password}" > 
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>사용자명</th>
		          <th class='text-left'><input  type="text" class="form-control" id="userName"  name="userName" maxlength="25" tabindex="3" value="${userVO.userName}"></th>
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>조직</th>
		          <th class='text-left'>	    	
		          	<select class="form-control" title="지점정보" id="groupId" name="groupId" value="${userVO.groupId}" tabindex="4">
		                <c:forEach var="groupVO" items="${group_comboList}" >
		                	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                </c:forEach>
		            </select>
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>권한</th>
		          <th class='text-left'>
		          	<select class="form-control" title="관리권한" id="authCode" name="authCode"  tabindex="5">
		                 <option value="03">일반</option>
		                 <option value="05">일반(staff)</option>
		                 <option value="04">일반(본사)</option>
		                 <option value="02">관리자</option>
		                 <c:if test="${strAuth=='01'}"><option value="01">슈퍼관리자</option></c:if>
		       		</select></th>
		       		<input type="hidden" name="auth" id="auth" value="${userVO.auth}">
	       		    <input type="hidden" name="authId" id="authId" value="${userVO.authId}">
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >email</th>
		          <th class='text-left'><input type="text" class="form-control" id="email" name="email" maxlength="25"  tabindex="6" value="${userVO.email}"></th>
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >officePhone</th>
		          <th class='text-left'><input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="20" tabindex="7" value="${userVO.officePhone}"> </th>
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >mobilePhone</th>
		          <th class='text-left'><input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="20" tabindex="8" value="${userVO.mobliePhone}">   </th>
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >sms수신여부</th>
		          <th class='text-left'>
		          	<select class="form-control" title="smsYn" id="smsYn" name="smsYn" value="${userVO.smsYn}" tabindex="9">
		                <option value="N">미수신</option>
		                <option value="Y">수신</option>
		       		</select>    </th>
		      	</tr>
			  </table>
        	<td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_modify()">수정</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>

document.userModifyForm.groupId.value='${userVO.groupId}';
document.userModifyForm.smsYn.value='${userVO.smsYn}';

if('${userVO.auth}'=='03'){//일반인경우
	
	if('${userVO.authId}'=='AD001'){//일반본사
		
		document.userModifyForm.authCode.value='04';
		
	}else if('${userVO.authId}'=='STAFF'){//일반스태프
		
		document.userModifyForm.authCode.value='05';
		
	}else{//일반
		
		document.userModifyForm.authCode.value='03';//일반
	}
	
}else{
	
	document.userModifyForm.authCode.value='${userVO.auth}';
	
}

</script>

 

