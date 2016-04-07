<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
//

</SCRIPT>
	 <div class="ibox-content">
          <div class="table-responsive">
              <table class="table table-striped" id="contentId">
                  <thead>
                  <tr>
                      <th>상세옵션</th>
                  </tr>
                  </thead>
                   	<tbody>
                         <c:if test="${!empty optionDetailList}">
                         <c:forEach items="${optionDetailList}" var="OptionVO" varStatus="status">
                          	<tr id="select_tr_${OptionVO.optionValueKey}" >
							  <input type="hidden" id="optionValueKey" name="optionValueKey" value="${OptionVO.optionValueKey}">
				              <td>${OptionVO.optionValue} &nbsp;<button type="button" class="btn btn-primary btn-xs"  onClick="optionDetailSelect('${OptionVO.optionId}','${OptionVO.optionValueKey}','${OptionVO.optionValue}')" >선택하기</button></td>
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
	                

