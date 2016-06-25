<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>

function optionDetail(optionId){

	 $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/optiondetaillist?optionId="+optionId,
               success: function(result) {
            
                   $("#addOptionDetailList_"+optionId).html(result);

               },
               error:function() {
                 
               }
        });		
	
}

</SCRIPT>
<form  id="addValueForm" name="addValueForm" method="post" action="">
<input type="hidden" id="optionId" name="optionId" value="${optionKey}">
<input type="hidden" id="addValue" name="addValue" value="">
</form>                 
	 <div class="ibox-content">
          <div class="table-responsive">
              <table class="table table-striped" id="contentId">
                  <thead>
                  <tr>
                      <th>옵션명 </th>
                      <th>상세</th>
                      <th>삭제 </th>
                  </tr>
                  </thead>
                   	<tbody>
                         <c:if test="${!empty optionList}">
                         <c:forEach items="${optionList}" var="OptionVO" varStatus="status">
                          	<tr id="select_tr_${OptionVO.optionId}" >
				              <td>${OptionVO.optionName}</td>
				              <td><button type="button" class="btn btn-primary btn-xs"  onClick="optionDetail('${OptionVO.optionId}')" >상세옵션열기</button></td>
	                          <td><a href="javascript:optionDel('${OptionVO.optionId}')"><i class="fa fa-minus-square"></i></a></td>
	                        </tr>
	                        <tr>
								<td colspan="3">
								  <div id="addOptionDetailList_${OptionVO.optionId}"></div>
								</td>
							</tr>
	                     </c:forEach>
				         </c:if>
				         <c:if test="${empty optionList}">
	                         <tr>
	                           <td colspan='3' class='text-center'>추가 옵션 정보가 없습니다.</td>
						     </tr>
					    </c:if>
                 </tbody>
              </table>
          </div>
      </div>
	                
   <script>
   var frm=document.productRegistForm;

   if(frm==undefined){
	  
	   frm=document.productModifyForm;
	   
   }
   
   frm.optionKey.value='${optionKey}';

   </script>

