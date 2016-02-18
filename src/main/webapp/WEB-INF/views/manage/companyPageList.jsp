<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageCompanyManagePageList(page) {
        document.companyManageConForm.curPage.value = page;
        var dataParam = $("#companyManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/companypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#companyManagePageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="companyManageVO" name="companyManagePageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#33b9ae">
	        <th class='text-center'>업체코드</th>
            <th class='text-center'>업체명</th>
            <th class='text-center'>담당자명</th>
            <th class='text-center'>핸드폰번호</th>
            <th class='text-center'>전화번호</th>
            <th class='text-center'>FAX</th>
            <th class='text-center'>발주e-mail</th>
            <th class='text-center'>참조e-mail</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty companyList}">
             <c:forEach items="${companyList}" var="companyManageVO" varStatus="status">
             <tr id="select_tr_${companyManageVO.companyCode}">
                 <td class='text-center'><c:out value="${companyManageVO.companyCode}"></c:out></td>
                 <td><c:out value="${companyManageVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${companyManageVO.chargeName}"></c:out></td>
                 <td class='text-center'><c:out value="${companyManageVO.mobilePhone}"></c:out></td>
                 <td class='text-center'><c:out value="${companyManageVO.companyPhone}"></c:out></td>
                 <td class='text-center'><c:out value="${companyManageVO.faxNumber}"></c:out></td>
                 <td class='text-center'><c:out value="${companyManageVO.email}"></c:out></td>
                 <td class='text-center'><c:out value="${companyManageVO.email_cc}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty companyList}">
           <tr>
           	<td colspan='8' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageCompanyManagePageList" totalCount="${totalCount}" curPage="${companyConVO.curPage}" rowCount="${companyConVO.rowCount}" />
     <!-- //페이징 -->

    