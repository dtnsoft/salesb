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
	 <!-- container -->
	      <div class="clm_acdo_cont">
	        <div class="clm_acdo_sec">
	          <ul class="odr_goods_lst">
	          	<c:if test="${!empty tokenList}">
	            <c:forEach items="${tokenList}" var="TokenVO" varStatus="status">
	            <!-- List -->
	            <li onClick="fcShow_Token('${TokenVO.tokenkey}')" >
	              <div class="clm_acont ">
	                <div class="order_acont">
	                  <div class="display_store">
	                    <span class="place"> <span class="date" style="color:blue">${TokenVO.shortUrl}</span> </span> 
	                    <span class="type">
	                     	<c:choose>
				                <c:when test="${TokenVO.activeYn=='Y'  }">
									 <span class="type_in"><span class="type_in">사용가능</span></span>
								</c:when>
								<c:otherwise>
									 <span class="type_out" ><span class="type_out" >미등록</span></span>
								</c:otherwise>
							</c:choose>
	                    
	                    </span>
	                  </div>
	                  <span class="img_area"> <span> <img src="${TokenVO.image1}" alt="상품이미지" > </span> </span>
	                  <div class="summary">
	                    <div>
	                      ${TokenVO.productName}
	                      <br>${TokenVO.createDateTime}
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </li>
	            <!--// List -->
	            </c:forEach>
	            </c:if>
	            <c:if test="${empty tokenList}">
	            <!-- List -->
	            <li>
	              <div class="clm_acont ">
	                <div class="order_acont">
	                  <div class="summary">
	                    <div>
	                      	등록된 토큰내역이 없습니다.
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </li>
	            <!--// List -->
	          </c:if>
	          </ul>
	        </div>
	      </div>
	     <!-- 페이징 -->
	     <taglib:paging cbFnc="goPageTokenPageList" totalCount="${totalCount}" curPage="${tokenConVO.curPage}" rowCount="${tokenConVO.rowCount}" />
	     <!-- //페이징 -->   
	  <!-- //container -->

   