<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Salesb | Register</title>

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/animate.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/style.css" rel="stylesheet">
	
	<link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">

	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>

 	<script type="text/javascript">

	 	//사용자 등록
		function fcUserManage_regist(){
			
			var frm=document.userRegistForm;
			
			if(frm.d_userId.value==''){
				alert('사업자 회원 아이디를 입력하세요');
				return;
			}
			
			if(frm.password.value==''){
				alert('사업자 회원 패스워드를 입력하세요');
				return;
			}
			
			if(frm.userName.value==''){
				alert('사업자명을 입력하세요');
				return;
			}
	
			if(frm.d_userId.disabled==false){
				alert('사업자 회원 아이디 중복체크가 필요합니다.');
				return;
			}
			
			frm.userId.value=frm.d_userId.value;
			frm.createUserId=frm.d_userId.value;
			frm.groupId.value=frm.d_userId.value;
			
			if (confirm('사업자 회원 정보를 등록 하시겠습니까?')){ 
			
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/common/companyregist",
			           data:$("#userRegistForm").serialize(),
			           success: function(result) {
	
							if(result>0){
								 alert('사업자 회원 등록요청을 성공했습니다.\n법인심사가 완료되면 사용 가능하십니다.');

								 location.href="<%= request.getContextPath() %>/customerloginform"; 
							} else{
								 alert('사업자 회원 정보 등록에 실패했습니다.');
							}

			           },
			           error:function(){
			        	   
			        	   alert('사업자 회원 정보 등록에 실패했습니다.');
			           }
			    });
			}
		}
	 	
		function fcCompany_regist(){
			
			var ufrm=document.userRegistForm;
			
			if(ufrm.d_userId.value==''){
				alert('사업자 회원 아이디를 입력하세요');
				return;
			}
			
			if(ufrm.password.value==''){
				alert('사업자 회원 패스워드를 입력하세요');
				return;
			}
			
			if(ufrm.userName.value==''){
				alert('사업자명을 입력하세요');
				return;
			}
	
			if(ufrm.d_userId.disabled==false){
				alert('사업자 회원 아이디 중복체크가 필요합니다.');
				return;
			}
			
			
			if(ufrm.email.value==false){
				alert('email 정보를 입력하세요');
				return;
			}
			
			if(ufrm.officePhone.value==false){
				alert('사무실 전화번호를 입력하세요');
				return;
			}
			
			if(ufrm.companyId.value==false){
				alert('사업자 번호를 입력하세요');
				return;
			}
			
			var url;
	        var frm=userRegistFileForm;
		    var fileName = '세일즈비.jpg';
		    var pos = '';
		    var ln = '';
		    var gap = '';
		    var gap1 = '';

		    var userId=$("#d_userId").val();
		    var userName=$("#userName").val();
		    var password=$("#password").val();
		    var email=$("#email").val();
		    var officePhone=$("#officePhone").val();
		    var companyId=$("#companyId").val();

		    if($("#files").val() != ''){

		        fileName = document.all.files.value;
  
		    }else{
		    	
		    	alert('사업자 등록증을 첨부하셔야 가입요청이 가능합니다.');
		    	return;
		    }
			   
	        pos = fileName.lastIndexOf("\\");
	        ln = fileName.lastIndexOf("\.");
	        gap = fileName.substring(pos + 1, ln);
	        gap1 = fileName.substring(ln+1);
	        
	        if(gap1=="jpg" || gap1=="JPG" || gap1=="gif" || gap1=="GIF" || gap1=="png" || gap1=="PNG"){//
	        	
	            url= "<%= request.getContextPath() %>/common/companyregist?fileName="+gap+"&extension="+gap1+"&userId="+userId+"&userName="+encodeURIComponent(userName)+"&password="+encodeURIComponent(password)+"&email="+encodeURIComponent(email)+"&officePhone="+officePhone+"&companyId="+companyId;
	            
	        }else {
	        	alert("이미지 파일만 등록 부탁드립니다.");
	            return;
	        }
	        
	    	if (confirm('사업자 회원정보를 등록하시겠습니까?\n등록후 법인심사가 완료되면 사용 가능하십니다.')){ 

		        frm.action = url;
		        frm.target="file_result"
		
		        frm.submit();    
	    	}   
	    
		}
		
		function fileResult(result){


			if(result==1){
				 alert('사업자 회원 등록요청을 성공했습니다.\n법인심사가 완료되면 사용 가능하십니다.');
			
				 location.href="<%= request.getContextPath() %>/customerloginform"; 
				
			}else{
				
				 alert('사업자 회원 정보 등록에 실패했습니다.');
				
			}
			

		}
		
		function fcCheck(){
			
			var frm=document.userRegistForm;
			
			if(frm.d_userId.value==''){
				alert('사업자 회원 아이디를 입력하세요');
				return;
			}
			
			frm.userId.value=frm.d_userId.value;
			
			 $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/manage/usercheck",
			           data:$("#userRegistForm").serialize(),
			           success: function(result) {
	
							if(result=='1'){
								 alert('이미 등록된 사업자 회원 아이디 입니다.');
								 frm.d_userId.value='';
								 frm.userId.value='';
								 frm.d_userId.focus(1);
							} else{
								 
								 if (confirm('사용가능한 사업자 회원 아이디 입니다.\n해당 아이디를 선택 하시겠습니까?')){ 
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
	       
	   	function c_uploadImg_Change( value ){ 
			
		    var src = getC_FileExtension(value); 
		    if (src == "") { 
		       // alert('올바른 파일을 입력하세요'); 
		       // return; 
		    } else if ( !((src.toLowerCase() == "gif") || (src.toLowerCase() == "jpg") || (src.toLowerCase() == "jpeg")) ) { 
		        alert('gif 와 jpg 파일만 지원합니다.'); 
		        return; 
		    } 
		  
			//document.all('afid')[0].innerText='첨부완료';
		
		} 
	   	
	   	function getC_FileExtension( filePath ){ 
			   
			var lastIndex = -1; 
		    lastIndex = filePath.lastIndexOf('.'); 
		    var extension = ""; 
		
			if ( lastIndex != -1 ){ 
			    extension = filePath.substring( lastIndex+1, filePath.len ); 
			} else { 
			    extension = ""; 
			} 
			
			return extension; 
		} 

            
        </script>		
</head>

<body class="gray-bg">
<iframe id="file_result" name="file_result" style="display: none" ></iframe>
             
    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>
<!-- 
                <h1 class="logo-name">IN+</h1>
 -->
            </div>
            <h3>Register to Company</h3>
            <p>Create account to see it in action.</p>

            <form class="m-t"  id="userRegistForm" name="userRegistForm" role="form" action="login.html">
                <input type="hidden" id="userId" name="userId" value="" >
	            <input type="hidden" id="groupId" name="groupId" value="" >
	            <input type="hidden" id="createUserId" name="createUserId" value="" >
	            
                <div class="form-group">
                    <input type="text" class="form-control" id=userName name="userName" placeholder="회사명" required="">
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" id=d_userId name="d_userId" placeholder="관리자ID" required="">
                </div>
                
                <button type="button" class="btn btn-primary block full-width m-b" id="reqbtn" onClick="fcCheck()">중복체크</button>
                 
                <div class="form-group">
                    <input type="password" class="form-control" id=password name="password" placeholder="비밀번호 등록 (6~20 영문 숫자의 조합)" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="rePassword" name="rePassword" placeholder="비밀번호 재입력" required="">
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" id=email name="email" placeholder="email" required="">
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" id=officePhone name="officePhone" placeholder="officePhone" required="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id=companyId name="companyId" placeholder="사업자 번호" required="">
                </div> 
                
               </form>   
                    <p>
                                         사업자 등록증을 첨부 하시면<br>심사후 가입이 가능합니다.
                    </p> 
                    <form:form commandName="MultipartFileVO"  id="userRegistFileForm" name="userRegistFileForm" method="post" action="/member/goodsmake" target="file_result"  enctype="multipart/form-data" >               
					<input type="file"  id="files" name="files" value="" onChange="c_uploadImg_Change( this.value )" >
					</form:form>
                    <div class="modal inmodal" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true">
                       <div class="modal-dialog">
                           <div class="modal-content animated flipInY">
                               <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                   <h4 class="modal-title"></h4>
                                   <small class="font-bold">이용약관 및 위치기반 서비스</small>
                               </div>
                               <div class="modal-body">
                                   <p>
                                    
									제1장 총칙
			                        <br>
			                        <br>
									제1조 (목적)
						            <br>
									본 약관은 '(주)오펙트'(이하 '회사'라 함)가 운영하는 인터넷 사이트 및 모바일 애플리케이션(이하 'salesb'이라 함)에서 제공하는 전자상거래 관련 서비스 및 기타 서비스(이하 '서비스'라 함)를 이용하는 자 간의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.
					                <br> 
									제2조 (정의)
									<br>
									① 'salesb'이란 '회사'가 컴퓨터 등 정보통신설비를 이용하여 '서비스'를 제공할 수 있도록 설정한 가상의 영업장을 말합니다.
									<br>
									② '회원'이라 함은 '회사'에 개인정보를 제공하여 회원등록을 한 자로서, 'salesb'의 정보를 지속적으로 제공받으며, '회사'가 제공하는 '서비스'를 계속적으로 이용할 수 있는 자를 말합니다.
									<br>
									③ '비회원'이라 함은 '회원'에 가입하지 않고 '회사'가 제공하는 '서비스'를 이용하는 자를 말합니다.
									<br>
									④ '이용자'란 'salesb'에 접속하여 본 약관에 따라 '회사'가 제공하는 '서비스'를 이용하는 '회원' 및 '비회원'을 말합니다.
									<br>
									⑤ '판매자'라 함은 'salesb'에 본인의 재화 및 용역(이하 '아이템'이라 함)을 등록하여 판매하거나 또는 제공할 의사로 '서비스'를 이용하는 자를 말합니다. 
									<br>
									⑥ '구매자'라 함은 'salesb'에 등록된 '판매자'의 '아이템'을 구매하거나 또는 제공받을 의사로 '서비스'를 이용하는 자를 말합니다. 
									<br>
									제3조 (약관 등의 명시와 설명 및 개정)
									<br>
									① '회사'는 본 약관의 내용을 '이용자'가 쉽게 알 수 있도록 인터넷 사이트 및 모바일 어플리케이션에 공지합니다. 다만, 약관의 내용은 '이용자'가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.
									<br>
									② '회사'는 전자상거래등에서의소비자보호에관한법률, 약관의규제에관한법률, 전자거래기본법, 전자서명법, 정보통신망이용촉진등에관한법률, 방문판매등에관한법률, 소비자보호법 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.
									<br>
									③ '회사'가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 '이용자'가 알기 쉽도록 표시하여 공지합니다.
									<br>
									④ '회사'가 약관을 개정할 경우에는 변경된 약관은 공지된 시점부터 그 효력이 발생하며, '이용자'는 약관이 변경된 후에도 본 '서비스'를 계속 이용함으로써 변경 후의 약관에 대해 동의를 한 것으로 간주됩니다.
									<br>
									⑤ 본 약관에서 정하지 아니한 사항과 본 약관의 해석에 관하여는 전자상거래등에서의소비자보호에관한법률, 약관의규제등에관한법률, 공정거래위원회가 정하는 전자상거래등에서의소비자보호지침 및 관계법령 또는 상관례에 따릅니다.

                                   </p>
                               </div>
                               <div class="modal-footer">
                                   <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                               </div>
                           </div>
                       </div>
                   </div>
                    <div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true">
                       <div class="modal-dialog">
                           <div class="modal-content animated flipInY">
                               <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                   <h4 class="modal-title"></h4>
                                   <small class="font-bold">개인정보의 수집 이용 목적 및 수집하는 개인정보 항목</small>
                               </div>
                               <div class="modal-body">
                                   <p>
                                    
									회사는 Salesb 서비스 제공을 위해 마스터 아이디(Master ID), 비밀번호 등 인증정보를 수집하고 Salesb 서비스 이용 기간 동안 옥션 및 G마켓 사이에 아이디(ID), 닉네임, 성명, 주민번호, 대표자명, 사업자등록번호, 사업자연락처, 주소, 연락처, 이메일(e-mail), 등록 상품 정보, 판매 상품 정산 정보, 제3자 제공 동의 정보 등을 공유하거나 연동하여 활용할 수 있습니다. 동 사항에 동의할 경우 해당 이용자의 사업자등록번호에 연동된 모든 아이디(ID)에 상기의 정보 공유 및 연동 활용 효력이 적용됩니다.
						            <br>
						            <br>
									개인정보의 보유 이용 기간
									<br>
									<br>
									회사는 이용자의 개인정보를 원칙적으로 고지 및 약정한 기간 동안 보유 및 이용하며 개인정보의 수집 및 이용목적이 달성되면 지체 없이 파기합니다. 또한, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.
									<br>
									<br>
									관련법령 및 회사 방침에 의한 정보보유 사유
									<br>
									<br>
									상법 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 법령에서 규정한 일정한 기간 동안 이용자 개인정보를 보관합니다. 이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.
									1.① 계약 또는 청약철회 등에 관한 기록◦보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
									<br>◦보존 기간 : 5년
									<br>
									2.② 대금결제 및 재화 등의 공급에 관한 기록◦보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
									<br>◦보존 기간 : 5년
									<br>
									3.③ 소비자의 불만 또는 분쟁처리에 관한 기록◦보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
									<br>◦보존 기간 : 3년
									<br>
									4.④ 접속에 관한 기록◦보존 이유 : 통신비밀보호법 제15조의2 및 시행령 제41조
									<br>◦보존기간 : 3개월
									<br>
									5.⑤ 부정거래기록◦부정거래의 배제 등 회사 방침에 의한 보존
									<br>◦보존 기간 : 1년
                                   </p>
                               </div>
                               <div class="modal-footer">
                                   <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                               </div>
                           </div>
                       </div>
                   </div>
                <div class="form-group">
                        <div class="checkbox i-checks"><label> <input type="checkbox"  id="useCheck" name="useCheck" checked value=""><i></i> 이용약관 및 위치기반 서비스 <a class="btn btn-info btn-rounded" data-toggle="modal" data-target="#myModal1">보기</a></label></div>
                </div>
                <div class="form-group">
                        <div class="checkbox i-checks"><label> <input type="checkbox"  id="privateCheck" name="privateCheck" checked value=""><i></i> 개인 정보 취급 방침&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-info btn-rounded" data-toggle="modal" data-target="#myModal2">보기</a></label></div>
                </div>
                
                <button type="button" class="btn btn-primary block full-width m-b" id="btn_change" onClick="fcCompany_regist()">등록하기</button>

            <p class="m-t"> <small>Copyright 2015 ⓒ salesb Corp.All rights reserved. v1.0.0</small> </p>
        </div>
    </div>


    <!-- Mainly scripts -->
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/jquery-2.1.1.js"></script>
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/bootstrap.min.js"></script>
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	
	<!-- Custom and plugin javascript -->
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/inspinia.js"></script>
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/pace/pace.min.js"></script>
	<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/wow/wow.min.js"></script>
    
    
    
    <!-- iCheck -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>
    
    <script>

	var cust_frm = document.RegistForm;
	var cust_key = getCookie("addys_customerkey");
	
	if( cust_key != null && trim(cust_key) != '' && cust_key != 'null' ){
		cust_frm.sbPhoneNumberView.value = cust_key;
		cust_frm.sbPhoneNumber.value = cust_key;
	}
	
	</script>
</form>
</body>

</html>
