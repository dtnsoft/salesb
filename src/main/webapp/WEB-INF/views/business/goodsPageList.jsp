<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageGoodsPageList(page) {
        document.goodsManageConForm.curPage.value = page;
        var dataParam = $("#goodsManageConForm").serialize();
        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/goodspagelist",
               	data:dataParam,
               success: function(result) {
                   //commonDim(false);
                   $("#goodsPageList").html(result);
               },
               error:function() {
                  // commonDim(false);
               }
        });

    }
    
    // 페이지 이동
    function fcGoods_View(idx) {

    	var curPage='${productConVO.curPage}';
    	var url="<%= request.getContextPath() %>/business/productdetail?idx="+idx+"&curPage="+curPage;

    	fcMenu(url);
    	
    }

    $(document).ready(function() {
    	$('[data-toggle="tooltip"]').tooltip({
    		// container: "body",
    		delay: { "show": 0, "hide": 111000 }
    	});
    	$('.tooltip-right').tooltip({
    		placement: 'right',
    		viewport: {selector: 'body', padding: 2}
    	});
    	$('.tooltip-bottom').tooltip({
    		placement: 'bottom',
    		viewport: {selector: 'body', padding: 2}
    	});
    	$('.tooltip-viewport-right').tooltip({
    		placement: 'right',
    		viewport: {selector: '.container-viewport', padding: 2}
    	});
    	$('.tooltip-viewport-bottom').tooltip({
    		placement: 'bottom',
    		viewport: {selector: '.container-viewport', padding: 2}
    	});
       });
</SCRIPT>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row">
                   <div class="col-lg-12">
                      <h4>전체 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" />개 검색</h4>
                   </div>
                </div>
            </div>
        </div>
    </div>
</div>
	                
<div class="row">
   <div class="col-lg-12">
       <div class="ibox float-e-margins">
          <div class="ibox-content">
              <div class="table-responsive">
                 <table class="table table-striped">
                    <thead>
                       <tr>
                           <th>상품 </th>
                           <th>수정확인</th>
                       </tr>
                    </thead>
                    <tbody>
                    	<c:if test="${!empty productList}">
                   		<c:forEach items="${productList}" var="ProductVO" varStatus="status">
                   			<tr onClick="fcGoods_View('${ProductVO.idx}');">
		                       <td><c:out value="${ProductVO.productName}"></c:out></td>
                               <td><c:out value="${ProductVO.productCode}"></c:out></td>
                               <td>
								<button class="btn btn-info  dim" type="button" ><i class="fa fa-paste"></i> </button>
							   </td>
                            </tr>
                        </c:forEach>
						</c:if>
						<c:if test="${empty productList}">
                          <tr>
                            <td colspan='2' class='text-center'>조회된 데이터가 없습니다.</td>
						  </tr>
					   </c:if>
                   </tbody>
               </table>
           </div>
		   <!-- 페이징 -->
		    <taglib:paging cbFnc="goPageGoodsPageList" totalCount="${totalCount}" curPage="${productConVO.curPage}" rowCount="${productConVO.rowCount}" /> 
		    <!-- //페이징 --> 
        </div>
     </div>
   </div>
</div>

