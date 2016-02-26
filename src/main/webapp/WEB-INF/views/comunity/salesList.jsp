<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSalesPageList(page) {
        document.asConForm.curPage.value = page;
        var dataParam = $("#asConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/comunity/saleslist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#view").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
    // 페이지 이동
    function fcSales_View(asNo) {

    	var curPage='${asConVO.curPage}';
    	
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/comunity/salesdetail?asNo="+asNo+"&curPage="+curPage,
            success: function(result) {
             
                   $("#view").html(result);
            },
            error:function(){

            }
        });
    }

</SCRIPT>
 <!-- container -->
<form:form class="form-inline" role="form" commandName="asConVO" id="asConForm" name="asConForm" method="post" action="" >
 <input type="hidden" name="curPage"             id="curPage"            value="1" />
 <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
 <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
 
      <div class="clm_acdo_cont">
        <!-- 타이틀 -->
        <div class="clm_acdo_tit">
          <h1>판매내역조회</h1>         
          <div class="clm_acdo_tit_left">
          </div>
        </div>
        <!--// 타이틀 --> 
        <div class="clm_acdo_sec">
          <ul class="odr_goods_lst">
          	<c:if test="${!empty asList}">
            <c:forEach items="${asList}" var="AsVO" varStatus="status">
            <!-- List -->
            <li>
              <div class="clm_acont ">
                <div class="order_acont">
                  <div class="display_store">
                    <span class="place"> <span class="date">${AsVO.asStartDateTime}</span> </span> <span class="type"><span class="type_in">${AsVO.asStateTrans}</span></span>
                  </div>
                  <span class="img_area"> <span> <img src="${AsVO.asImage}" alt="상품이미지" > </span> </span>
                  <div class="summary">
                    <div>
                      <a href="javascript:fcSales_View('${AsVO.asNo}')">${AsVO.productName}</a>
                    </div>
                  </div>
                </div>
              </div>
            </li>
            <!--// List -->
            </c:forEach>
            </c:if>
            <c:if test="${empty asList}">
            <!-- List -->
            <li>
              <div class="clm_acont ">
                <div class="order_acont">
                  <div class="summary">
                    <div>
                      	등록된 주문내역이 없습니다.
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
     <taglib:paging cbFnc="goPageSalesPageList" totalCount="${totalCount}" curPage="${asConVO.curPage}" rowCount="${asConVO.rowCount}" />
     <!-- //페이징 --> 

  </form:form>
  <!-- //container -->



   