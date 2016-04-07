<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<html>
<head>
<script language="javascript">
//초기세팅

function fcProductMaster_excelimport(){

    if($("#inputImage").val() == ''){
    	
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
       url="<%= request.getContextPath() %>/business/productexcelimport?fileName="+gap+"&extension="+gap1;
    }else{
    	alert("Excel 통합문서(xlsx) 파일만 등록 부탁드립니다.");
        return;
    }

    frm.action = url;
    frm.target="excel_import_result";

    frm.submit();        
}

function uploadClose(msg,obj){
	
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

function c_uploadImg_Change( value ){ 
	
    var src = getC_FileExtension(value); 
    if (src == "") { 
       // alert('올바른 파일을 입력하세요'); 
       // return; 
    } else if ( !((src.toLowerCase() == "xlsx"))) { 
        alert('xlsx 파일만 지원합니다.'); 
        return; 
    } 
  
	document.all('excel').innerText='첨부완료';

} 
</script>
</head>
			
			<div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>일괄 상품 등록</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a>상품 등록/변경</a>
                    </li>
                    <li class="active">
                        <strong>일괄 상품 등록</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        	</div>
			
			<iframe id="excel_import_result" name="excel_import_result" style="display: none" ></iframe>

			<div class="wrapper wrapper-content animated fadeIn">
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>업로드 파일선택</h5>
                    </div>
                    <div class="ibox-content">
                     <form:form class="form-inline" role="form" commandName="fileVO"  id="excel_form" method="post" target="excel_import_result"  name="excel_form"  enctype="multipart/form-data" >
                        <h5><strong><em class="num">1. </em>업로드 파일 선택</strong></h5>
				         <div class="btn-group">
                             <label title="Upload image file" for="inputImage" class="btn btn-primary">
                                 <input type="file" accept="xlsx/*" name="files" id="inputImage" class="hide" onChange="c_uploadImg_Change( this.value )">
                                 Upload File 선택
                             </label>
                             <label title="엑셀첨부" id="excel" class="btn btn-primary">excel첨부</label>
                         </div>
				        <br>
				        <h5><strong><em class="num">2. </em>파일 양식 다운로드</strong></h5>
				        <div class="btn-group">
                             <label title="Donload image" id="download" class="btn btn-primary">파일양식 Download</label>
                        </div> 
				        <br>
				        <h5><strong><em class="num">3. </em></strong>업로드 안내</h5>
						  <h6><strong><font style="color:#FF9900">기존에 이미 사용하고 계신 </font></strong></h6>
						  <h6><strong><font style="color:#FF9900">재고 관리 시스템이나 ERP가</font></strong></h6>
						  <h6><strong><font style="color:#FF9900">있으시면 해당 양식을 다운로드</font></strong></h6>
						  <h6><strong><font style="color:#FF9900">한 뒤 양식에 맞게 작성 후</font></strong></h6>
						  <h6><strong><font style="color:#FF9900">업로드 하시면 일괄 등록 됩니다.</font></strong></h6>
					 </form:form>
                    </div>
                </div>
            </div>
            </div>
            
                            
            <div class="row">
                  <div class="col-lg-12">
                      <div class="ibox float-e-margins">
	                <div class="ibox-content">
                         <button type="button" class="btn btn-primary btn-lg" onClick="fcProductMaster_excelimport()">업로드</button>
                    </div>
                   </div>
               </div>
           </div>   

       </div>
