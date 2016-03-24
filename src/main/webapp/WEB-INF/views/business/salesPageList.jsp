<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSalesPageList(page) {
        document.SalesManageConForm.curPage.value = page;
        var dataParam = $("#salesManageConForm").serialize();
        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/salespagelist",
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
           <h5>배송대상    ${orderStateVo.deliveryTagetCnt}건</h5>
           <h5>배송중    ${orderStateVo.deliveryIngCnt}건</h5>
           <h5>배송완료    ${orderStateVo.deliveryColseCnt}건</h5>
           <h5>매출현황    ${orderStateVo.salesAmt}원</h5>
    	</div> 
    </div> 
    <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span>판매현황</p>   
	<table  class="table table-bordered table-hover table-striped">
	  <thead>
	    <tr>
	      <th>No</th>
	      <th>Name</th>
	      <th>누적판매(건)</th>
	      <th>누적금액(원)</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:if test="${!empty bestList}">
	          <c:forEach items="${bestList}" var="salesVO" varStatus="status">
	          <tr id="select_tr_${status}">
	              <td><c:out value=""></c:out></td>
	              <td><c:out value="${salesVO.customerKey}"></c:out></td>
	              <td><c:out value="${salesVO.accrueSalesCnt}"></c:out></td>
	              <td><c:out value="${salesVO.accrueSalesAmt}"></c:out></td>
	           </tr>
	          </c:forEach>
	         </c:if>
	        <c:if test="${empty bestList}">
	        <tr>
	            <td colspan='4' class='text-center'>조회된 데이터가 없습니다.</td>
	        </tr>
	       </c:if>
	  </tbody>
	</table>
	<!-- 페이징 -->
	<taglib:paging cbFnc="goPageSalesPageList" totalCount="${totalCount}" curPage="${salesConVO.curPage}" rowCount="${salesConVO.rowCount}" />
	<!-- //페이징 -->  
	 

