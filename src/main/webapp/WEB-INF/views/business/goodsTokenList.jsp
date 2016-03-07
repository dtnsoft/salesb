<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageGoodsPageList(page) {
        document.goodsTokenConForm.curPage.value = page;
        var dataParam = $("#goodsTokenConForm").serialize();
        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/goodstokenlist",
               	data:dataParam,
               success: function(result) {
                   //commonDim(false);
                   $("#goodsTokenList").html(result);
                   
                   if(goodsTokenConForm.prodId.length!=undefined){
                	   if(goodsTokenConForm.prodId.length>1){
                		   goodsTokenConForm.prodIdRadio[0].checked=true;
                           fcGoods_View(0);
                       }else{
                    	   goodsTokenConForm.prodIdRadio.checked=true;
                           fcGoods_View(0);
                       } 
                   }
                   
               },
               error:function() {
                  // commonDim(false);
               }
        });

    }

</SCRIPT>
    <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> 전체 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" />개 검색 </span></p>  
	<table  class="table table-bordered table-hover table-striped">
	  <thead>
	  </thead>
	  <tbody>
	  	<c:if test="${!empty productList}">
	          <c:forEach items="${productList}" var="ProductVO" varStatus="status">
	          <input type="hidden" id="prodId" name="prodId" value="${ProductVO.idx}">
	          <tr id="select_tr_${ProductVO.idx}" onClick="fcGoods_View('${status.count-1}');">
	              <td style="width:30px"><input type="radio" name="prodIdRadio" id="prodIdRadio"></td>
	              <td><c:out value="${ProductVO.productName}"></c:out></td>
	           </tr>
	          </c:forEach>
	         </c:if>
	        <c:if test="${empty productList}">
	        <tr>
	            <td colspan='1' class='text-center'>조회된 상품이 없습니다.</td>
	        </tr>
	       </c:if>
	  </tbody>
	</table>
	<!-- 페이징 -->
	<taglib:paging cbFnc="goPageGoodsPageList" totalCount="${totalCount}" curPage="${productConVO.curPage}" rowCount="${productConVO.rowCount}" />
	<!-- //페이징 -->  
	 

