<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSalesPageList(page) {
        document.SalesManageConForm.curPage.value = page;
        var dataParam = $("#salesManageConForm").serialize();
        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/member/salespagelist",
               	data:dataParam,
               success: function(result) {
                   //commonDim(false);
                   $("#salesPageList").html(result);
               },
               error:function() {
                  // commonDim(false);
               }
        });

    }
   
</SCRIPT>
	<div class="well">
         <div class="input-group">
           <h5>상품 토큰 발급현황    ${tokenStateVo.tokenCnt}건</h5>
           <h5>신규주문    ${orderStateVo.newOrerCnt}건</h5>
           <h5>판매    ${orderStateVo.salesCnt}건</h5>
           <h5>배송완료    ${orderStateVo.deliveryColseCnt}건</h5>
           <h5>정산예정    ${orderStateVo.setteleTagetAmt}원</h5>
           <h5>정산완료    ${orderStateVo.setteleColseAmt}원</h5>
    	</div> 
    </div> 
    <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span>판매현황</p>   
	<table  class="table table-bordered table-hover table-striped">
	  <thead>
	    <tr>
	      <th>No</th>
	      <th>상품명</th>
	      <th>공급처</th>
	      <th>판매(건)</th>
	      <th>금액(원)</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:if test="${!empty bestList}">
	          <c:forEach items="${bestList}" var="salesVO" varStatus="status">
	          <tr id="select_tr_${status}">
	              <td><c:out value=""></c:out></td>
	              <td><c:out value="${salesVO.productName}"></c:out></td>
	              <td><c:out value="${salesVO.suplyCompany}"></c:out></td>
	              <td><c:out value="${salesVO.salesCnt}"></c:out></td>
	              <td><c:out value="${salesVO.salesAmt}"></c:out></td>
	           </tr>
	          </c:forEach>
	         </c:if>
	        <c:if test="${empty bestList}">
	        <tr>
	            <td colspan='5' class='text-center'>조회된 데이터가 없습니다.</td>
	        </tr>
	       </c:if>
	  </tbody>
	</table>
	<!-- 페이징 -->
	<taglib:paging cbFnc="goPageSalesPageList" totalCount="${totalCount}" curPage="${salesConVO.curPage}" rowCount="${salesConVO.rowCount}" />
	<!-- //페이징 -->  
	 

