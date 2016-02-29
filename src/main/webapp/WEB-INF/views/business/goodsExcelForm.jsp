<%@ include file="/WEB-INF/views/salesb/topBusiness.jsp" %>
<html>
<head>
<script language="javascript">
//초기세팅

function fcProductMaster_excelimport(){

    if($("#files").val() == ''){
    	
        alert('등록 할 파일이 없습니다.');
        return;
    }
    
    var url;
    var frm = document.excel_form;
    var fileName = document.all.files.value;
    var pos = fileName.lastIndexOf("\\");
    var ln = fileName.lastIndexOf("\.");
    var gap = fileName.substring(pos + 1, ln);
    var gap1 = fileName.substring(ln+1);

    if(gap1=="xlsx"){
       url="<%= request.getContextPath() %>/master/productexcelimport?fileName="+gap+"&extension="+gap1;
    }else{
    	alert("Excel 통합문서(xlsx) 파일만 등록 부탁드립니다.");
        return;
    }
    commonDim(true);
    
    frm.action = url;
    frm.target="excel_import_result";

    frm.submit();        
}

function uploadClose(msg,obj){
	
	 commonDim(false);

	 alert(msg);
	 
	 if(obj.length>0){
		 if(obj[0] !=''){
			 var eMsg='오류 품목코드 정보\n';
			 for (i=0;i<obj.length;i++){
				 eMsg=eMsg+obj[i]+'\n';
			 }
			 
			 alert(eMsg);
		 }
	 }
	 
	 $('#productExcelForm').dialog('close');
	 fcProductMaster_listSearch();
}

</script>
</head>
<iframe id="excel_import_result" name="excel_import_result" style="display: none" ></iframe>
	<div class="container">
	<!-- container -->
	 <!-- form_area -->
	 <form:form class="form-inline" role="form" commandName="fileVO"  id="excel_form" method="post" target="excel_import_result"  name="excel_form"  enctype="multipart/form-data" >
	 	 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;일괄 상품 등록
		 </p>
		 <!--// 타이틀 --> 
          <div class="well">
           	<h5> <strong><em class="num">1. </em></strong>업로드 파일선택</h5>
			<h6><strong><font style="color:#FF9900">업로드 할 <em class="bold"> excel파일</em></font></strong></h6>
	            <input type="file"  id="files" name="files" /> 
	        <br>
	        <h5><strong><em class="num">2. </em><font style="color:#428bca">파일 양식 다운로드</font></strong></h5>
	        <br>
	        <h5><strong><em class="num">3. </em></strong>업로드 안내</h5>
			  <h6><strong><font style="color:#FF9900">기존에 이미 사용하고 계신 </font></strong></h6>
			  <h6><strong><font style="color:#FF9900">재고 관리 시스템이나 ERP가</font></strong></h6>
			  <h6><strong><font style="color:#FF9900">있으시면 해당 양식을 다운로드</font></strong></h6>
			  <h6><strong><font style="color:#FF9900">한 뒤 양식에 맞게 작성 후</font></strong></h6>
			  <h6><strong><font style="color:#FF9900">업로드 하시면 일괄 등록 됩니다.</font></strong></h6>
         </div> 
         <div class="clm_detail_btn">
	         <div class="clm_btn">
	           <a href="javascript:fcGoods_regist();" class="btn_ty2">업로드</a>
	         </div>
         </div>
	</form:form>
	 <!-- //form_area --> 
	</div>
    <!-- //container -->