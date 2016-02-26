<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageOrderPageList(page) {
        document.asConForm.curPage.value = page;
        var dataParam = $("#asConForm").serialize();
        //commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/comunity/orderlist",
              data:dataParam,
            success: function(result) {
                   //commonDim(false);
                   $("#view").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
    // 페이지 이동
    function fcOrder_View(asNo) {

    	var curPage='${asConVO.curPage}';
    	
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/comunity/orderdetail?asNo="+asNo+"&curPage="+curPage,
            success: function(result) {
             
                   $("#view").html(result);
            },
            error:function(){

            }
        });
    }

</SCRIPT>
	<table  class="table table-bordered table-hover table-striped">
	  <thead>
	    <tr>
	      <th>상품명</th>
	         <th>상품코드</th>
	         <th>수정확인</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:if test="${!empty asList}">
	          <c:forEach items="${asList}" var="AsVO" varStatus="status">
	          <tr id="select_tr_${AsVO.asNo}">
	              <td><c:out value="${AsVO.productName}"></c:out></td>
	              <td><c:out value="${AsVO.asNo}"></c:out></td>
	              <td><button type="button" id="receivebtn" class="btn btn-xs btn-success" onClick="fcRecovery_receive('');">수정</button></td>
	           </tr>
	          </c:forEach>
	         </c:if>
	        <c:if test="${empty asList}">
	        <tr>
	            <td colspan='4' class='text-center'>조회된 데이터가 없습니다.</td>
	        </tr>
	       </c:if>
	  </tbody>
	</table>
	<!-- 페이징 -->
	<taglib:paging cbFnc="goPageOrderPageList" totalCount="${totalCount}" curPage="${asConVO.curPage}" rowCount="${asConVO.rowCount}" />
	<!-- //페이징 -->  
	 

