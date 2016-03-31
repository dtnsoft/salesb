<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageTokenPageList(page) {
        document.tokenManageConForm.curPage.value = page;
        var dataParam = $("#tokenManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/member/mytokenlist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#myTokenList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
    // 페이지 이동
    function fcShow_Token(tokenKey) {

    	fcMenu('<%= request.getContextPath() %>/member/goodsmakeform?tokenKey='+tokenKey);
    }

</SCRIPT>
 <div class="wrapper wrapper-content animated fadeInRight">
	   <div class="row">
	         <c:if test="${!empty tokenList}">
	          <c:forEach items="${tokenList}" var="TokenVO" varStatus="status">
	          <!-- List -->
	           <div class="col-md-3">
	                 <div class="ibox">
	                     <div class="ibox-content product-box">
	
	                         <div class="product-imitation">
	                            <img src="${TokenVO.image1}"  width="200" alt="상품이미지" > 
	                         </div>
	                         <div class="product-desc">
	                             <span class="product-price">
	                             <c:choose>
			                <c:when test="${TokenVO.activeYn=='Y'  }">
	                                                             사용가능
	                              </c:when>
							<c:otherwise>
								 미등록
							</c:otherwise>
					    </c:choose>
	                             </span>
	                             <small class="text-muted">${TokenVO.createDateTime}</small>
	                             <a href="#" class="product-name"> ${TokenVO.productName}</a>
	                             <div class="small m-t-xs">
	                                ${TokenVO.productEtc}=>${TokenVO.shortUrl}
	                             </div>
	                             <div class="m-t text-righ">
	
	                                 <a href="javascript:fcShow_Token('${TokenVO.tokenkey}')" class="btn btn-xs btn-outline btn-primary">Info <i class="fa fa-long-arrow-right"></i> </a>
	                             </div>
	                         </div>
	                     </div>
	                 </div>
	             </div>
	          	<!--// List -->
	          </c:forEach>
	          </c:if>
	          <c:if test="${empty tokenList}">
	          <!-- List -->
	           <div class="col-md-3">
	                 <div class="ibox">
	                     <div class="ibox-content product-box">
	
	                         <div class="product-imitation">
	                             [ INFO ]
	                         </div>
	                         <div class="product-desc">
	                             <span class="product-price">
	                                  
	                             </span>
	                             <small class="text-muted"></small>
	                             <a href="#" class="product-name"> </a>
	
	
	
	                             <div class="small m-t-xs">
	                              	   등록된 상품정보 가 없습니다.
	                             </div>
	                             <div class="m-t text-righ">
	
	                                 <a href="#" class="btn btn-xs btn-outline btn-primary">Info <i class="fa fa-long-arrow-right"></i> </a>
	                             </div>
	                         </div>
	                     </div>
	                 </div>
	             </div>
	          <!--// List -->
	          </c:if>
	         <!-- 페이징 -->
		     <taglib:paging cbFnc="goPageTokenPageList" totalCount="${totalCount}" curPage="${tokenConVO.curPage}" rowCount="${tokenConVO.rowCount}" />
		    <!-- //페이징 -->   
    </div> 
 </div>          

