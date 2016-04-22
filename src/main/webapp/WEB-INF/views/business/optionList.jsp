<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
//

</SCRIPT>
	 <div class="ibox-content">
          <div class="table-responsive">
              <table class="table table-striped" id="contentId">
                  <thead>
                  <tr>
                      <th>옵션명 </th>
                      <th>상세옵션</th>
                      <th>삭제 </th>
                  </tr>
                  </thead>
                   	<tbody>
                         <c:if test="${!empty optionList}">
                         <c:forEach items="${optionList}" var="OptionVO" varStatus="status">
                          	<tr id="select_tr_${OptionVO.optionId}" >
				              <td>${OptionVO.optionName}</td>
				              <td><button type="button" class="btn btn-primary btn-xs"  onClick="optionDetail('${OptionVO.optionId}')" data-toggle="modal" data-target="#myModal1">상세</button></td>
	                          <td><a href="javascript:optionDel('${OptionVO.optionId}')"><i class="fa fa-minus-square"></i></a></td>
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
	 

