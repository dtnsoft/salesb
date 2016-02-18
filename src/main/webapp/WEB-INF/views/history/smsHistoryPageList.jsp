<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSmsManagePageList(page) {
        document.smsManageConForm.curPage.value = page;
        var dataParam = $("#smsManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/history/smshistorypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#smsHistoryPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="smsHistoryVO" name="smsHistoryPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#33b9ae">
	        <th class='text-center'>전송일시</th>
	        <th class='text-center'>전송자</th>
            <th class='text-center'>발신번호</th>
	        <th class='text-center'>수신번호</th>
            <th class='text-center'>메세지</th>
            <th class='text-center'>결과코드</th>
            <th class='text-center'>결과메세지</th>
            <th class='text-center'>잔여포인트</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty smsList}">
             <c:forEach items="${smsList}" var="smsHistoryVO" varStatus="status">
             <tr id="select_tr_${smsHistoryVO.idx}">
               <td class='text-center'><c:out value="${smsHistoryVO.smsDateTime}"></c:out></td> 
               <td class='text-center'><c:out value="${smsHistoryVO.smsUserName}(${smsHistoryVO.smsUserId})"></c:out></td>
               <td class='text-center'><c:out value="${smsHistoryVO.smsFrom}"></c:out></td>
               <td class='text-center'><c:out value="${smsHistoryVO.smsTo}"></c:out></td>
               <td class='text-center'><c:out value="${smsHistoryVO.smsMsg}"></c:out></td>
               <td class='text-center'><c:out value="${smsHistoryVO.resultCode}"></c:out></td>
               <td class='text-center'><c:out value="${smsHistoryVO.resultMessage}"></c:out></td>
               <td class='text-center'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${smsHistoryVO.resultLastPoint}" /></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty smsList}">
           <tr>
           	<td colspan='8' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageSmsManagePageList" totalCount="${totalCount}" curPage="${smsConVO.curPage}" rowCount="${smsConVO.rowCount}" />
     <!-- //페이징 -->

    