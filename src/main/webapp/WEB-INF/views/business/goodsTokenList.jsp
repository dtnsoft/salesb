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
	
	 <div class="row">
         <div class="col-lg-12">
             <div class="ibox float-e-margins">
                 <div class="ibox-content">
                     <div class="row">
                         <div class="col-xs-4">
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
                                <tbody>
                                <c:if test="${!empty productList}">
                                <c:forEach items="${productList}" var="ProductVO" varStatus="status">
                                 <tr id="select_tr_${ProductVO.idx}" onClick="fcGoods_View('${status.count-1}');">
    								<input type="hidden" id="prodId" name="prodId" value="${ProductVO.idx}">
                                     <td style="width:30px">
                                     
                                        <div class="radio radio-success">
                                            <input type="radio" value="" name="prodIdRadio" id="prodIdRadio" checked="" aria-label="Single radio Two">
                                            <label></label>
                                        </div>
                                     
                                     </td>
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
                        </div>
						<!-- 페이징 -->
						<taglib:paging cbFnc="goPageGoodsPageList" totalCount="${totalCount}" curPage="${productConVO.curPage}" rowCount="${productConVO.rowCount}" />
						<!-- //페이징 --> 
                    </div>
                </div>
            </div>
        </div> 

	<!-- iCheck
       <script>
            $(document).ready(function () {
                $('.i-checks').iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green',
                });
            });
        </script>
  -->
