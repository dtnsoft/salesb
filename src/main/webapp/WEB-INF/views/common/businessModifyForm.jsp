<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<script>

	//사용자 수정
	function fcBusiness_modify(){

		var frm=document.userModifyForm;
		
		//if(frm.change_password.value==''){
		if(false){
			
			if (confirm('사업자정보를 변경 하시겠습니까?')){ 

				document.userModifyForm.pw_modifyYn.value='N';
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/common/businessmodify",
			           data:$("#userModifyForm").serialize(),
			           success: function(result) {

							if(result=='1'){
								 alert('사업자정보 변경을 성공했습니다.');
							} else{
								 alert('사업자정보 변경을 실패했습니다.');
							}

			           },
			           error:function(){
			        	   
			        	    alert('사업자정보 변경을 실패했습니다.');

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
		           url:  "<%= request.getContextPath() %>/common/userpwcheck",
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

				document.userModifyForm.pw_modifyYn.value='Y';

			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/common/businessmodify",
			           data:$("#userModifyForm").serialize(),
			           success: function(result) {
	
							if(result=='1'){
								 alert('사업자 정보 변경을 성공했습니다.');
							} else{
								 alert('사업자 정보 변경을 실패했습니다.');
							}
							
			           },
			           error:function(){
			        	   
			        	    alert('사업자 정보 변경을 실패했습니다.');
			        	    
			           }
			    });
		    
	    	}
		}
	}


</script>
<div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>사업자 정보설정</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a>사업자 정보설정</a>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
 		 </div>
	  <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
               <div class="col-lg-12">
                       <div class="ibox float-e-margins">
                    <div class="ibox-title">
                           <h5>토큰요청<small>3</small></h5>
                       </div>
		                <div class="ibox-content">

                               <div class="form-group has-success"><label class="col-sm-2 control-label">사용자 정보</label>
                                   <div class="col-sm-10"> 
                                   [업체명] : ${strGroupName}  [사용자명] : ${strUserName}</div>
                               </div>

	                    </div>
                    </div>
                </div>
            </div> 
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>비밀번호 설정</h5>
                        </div>
                        <div class="ibox-content">
                            <form:form class="form-horizontal" role="form" commandName="UserManageVO" id="userModifyForm" name="userModifyForm" method="post" action="">
						        <input type="hidden" id="pw_modifyYn" name="pw_modifyYn" value="Y" >
						   	    <input type="hidden" id="userId" name="userId" value="${business.userId}" > 
                                <div class="form-group"><label class="col-sm-2 control-label">현재 비밀번호</label>
				 					<input type="hidden" id="regPassword" name="regPassword" value="${business.password}" > 
						            <input type="hidden" id="encpassword" name="encpassword" value="" > 
						            <input type="hidden" id="password" name="password" value="" > 
                                    <div class="col-sm-10"><input type="password" class="form-control"  id="cur_password" name="cur_password"  maxlength="50"  placeholder="현재 비밀번호"></div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-2 control-label">변경비밀번호</label>
                                    <div class="col-sm-10"><input type="password" class="form-control" id="change_password" name="change_password"  maxlength="50"  value=""  placeholder="변경 비밀번호"></div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-2 control-label">재입력</label>
                                    <div class="col-sm-10"><input type="password" class="form-control" id="re_password" name="re_password"  maxlength="50"  value=""  placeholder="재입력"></div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
		        <div class="col-lg-12">
		           <div class="ibox float-e-margins">
		               <div class="ibox-content">
		                  <button type="button" class="btn btn-primary btn-lg" onClick="fcBusiness_modify()">수정하기</button>
		               </div>
		           </div>
		        </div>
     		</div>      
        </div>
	