<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>

function optionClose(optionId){
	
    $("#addOptionDetailList_"+optionId).html("");
	
}


function addOptionDetailList(optionId){
	
	var ofrm=document.addValueForm;
	var frm=document.productForm;

	if($('#addValueInput_'+optionId).val()==''){
		alert('추가 상세 옵션명을 입력하세요');
		return;
	}
	
	var optionValue=$('#addValueInput_'+optionId).val();
	
	 $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/optioninsertlist?optionKey="+frm.optionKey.value+"&optionId="+optionId+"&optionValue="+encodeURIComponent(optionValue),
               success: function(result) {
                   //commonDim(false);
                   $("#addOptionDetailList_"+optionId).html(result);
               },
               error:function() {
                 
               }
        });		
	 
}


function optionDetailDel(optionValueKey,optionId){
	
	var ofrm=document.addValueForm;
	var frm=document.productForm;
	
	 $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/optiondetaildel?optionId="+optionId+"&optionValueKey="+optionValueKey,
               success: function(result) {
                   //commonDim(false);
                   $("#addOptionDetailList_"+optionId).html(result);
 
               },
               error:function() {
                 
               }
     });		

}

</SCRIPT>

 <div class="modal-header">
 	<div class="form-group has-success">
	 	<label class="col-sm-2 control-label" >상세옵션</label>
		<div class="col-sm-10"><input type="text" class="form-control" id="addValueInput_${optionId}" name="addValueInput_${optionId}"  maxlength="50"  value="" placeholder="상세옵션내용"></div>
	</div> 
 </div>

<div class="modal-body">
	 <div class="ibox-content">
    	<div class="table-responsive">
              <table class="table table-striped" id="contentId">
                  <thead>
                  <tr>
                      <th>상세옵션</th>
                      <th>삭제 </th>
                  </tr>
                  </thead>
                   	<tbody>
                         <c:if test="${!empty optionDetailList}">
                         <c:forEach items="${optionDetailList}" var="OptionVO" varStatus="status">
                          	<tr id="select_tr_${OptionVO.optionValueKey}" >
							  <input type="hidden" id="optionValueKey" name="optionValueKey" value="${OptionVO.optionValueKey}">
				              <td>${OptionVO.optionValue}</td>
				              <td><a href="javascript:optionDetailDel('${OptionVO.optionValueKey}','${optionId}')"><i class="fa fa-minus-square"></i></a></td>
	                        </tr>
	                     </c:forEach>
				         </c:if>
				         <c:if test="${empty optionDetailList}">
	                         <tr>
	                           <td colspan='2' class='text-center'>상세 옵션 정보가 없습니다.</td>
						     </tr>
					    </c:if>
                 </tbody>
              </table>
          </div>
      </div>
 </div>  
 
 <div class="modal-footer">
     <button type="button" class="btn btn-white" onClick="optionClose('${optionId}')" >상세옵션 닫기</button>
     <button type="button" class="btn btn-primary" onClick="addOptionDetailList('${optionId}')" >상세옵션 추가</button>
 </div>

