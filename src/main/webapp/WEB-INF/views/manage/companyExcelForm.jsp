<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<html>
<head>
<script language="javascript">
//초기세팅
<%-- function init() {

	closeWaiting(); //처리중 메세지 비활성화

	if('<%=importResult%>'!=''){
		alert('<%=importResult%>');
	}

} --%>
function fcCompanyManage_excelimport(){

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
       url="<%= request.getContextPath() %>/manage/companyexcelimport?fileName="+gap+"&extension="+gap1;
    }else{
    	alert("Excel 통합문서(xlsx) 파일만 등록 부탁드립니다.");
        return;
    }
    commonDim(true);
    
    frm.action = url;
    frm.target="excel_import_result";

    frm.submit();        
}

function uploadClose(msg){
	
	 commonDim(false);
	  
	 alert(msg);
	 
	 $('#companyExcelForm').dialog('close');
	 fcCompanyManage_listSearch();
}

</script>
</head>
<body>
<iframe id="excel_import_result" name="excel_import_result" style="display: none" ></iframe>
 <!-- content -->
<div class="container-fluid">
 <!-- form_area -->
 <form:form class="form-inline" role="form" commandName="fileVO"  id="excel_form" method="post" target="excel_import_result"  name="excel_form"  enctype="multipart/form-data" >
  <fieldset>
  <div class="form-group" >
  <h4><strong><font style="color:#428bca">업로드 파일 선택</font></strong></h4>
  <h5><strong><font style="color:#FF9900">업로드 할 <em class="bold"> excel파일</em></font></strong></h5>
  <input type="file"  id="files" name="files" />
  <br><br> 
  <h4><strong><font style="color:#428bca">업로드 시 주의사항</font></strong></h4>
  <h6><strong><font style="color:#FF9900">엑셀파일 양식에 맞지 않으면 업로드가 불가능 합니다.</font></strong></h6>
  <!-- >h6><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 엑셀파일 업로드 양식을 다운로드 합니다. 
  <a href="#"><strong><font style="color:#428bca">[양식다운로드]</font></strong></a></font></strong></h6 -->
  <h6><strong><font style="color:#FF9900">파일 업로드 결과는 서버의 log 경로에서 확인이 가능합니다.</font></strong></h6>
  </div>
  </fieldset>
</form:form>
 <!-- //form_area --> 
 <br>
 <!-- button -->
 <div >
  <button type="button" class="btn btn-primary" onClick="javascript:fcCompanyManage_excelimport()">업로드</button>
 </div>
  <!-- //button -->
</div>
 </div>
 <!-- //content -->
</body>
</html>
