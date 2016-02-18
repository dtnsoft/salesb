<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageProductMasterPageList(page) {
        document.productMasterConForm.curPage.value = page;
        var dataParam = $("#productMasterConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/productpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#productMasterPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
    // 품목 상세 페이지 Layup
    function fcProduct_detailSearch(productCode) {

    	$('#productDetail').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 650,
            height : 280,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/productmasterdetail?productCode='+productCode);
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#productDetail").dialog('close');

                    });
            }
            ,close:function(){
                $('#productDetail').empty();
            }
        });
    };


</SCRIPT>

     <form:form commandName="productMasterVO" name="productMasterPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	  	<colgroup>
	     <col width="10%" />
         <col width="10%" />
         <col width="*" />
         <col width="10%" />
         <col width="20%" />
         <col width="10%" />
         <col width="15%" />
        </colgroup>
	    <thead>
	      <tr style="background-color:#33b9ae">
	        <th class='text-center'>품목코드</th>
            <th class='text-center'>바코드</th>
            <th class='text-center'>품목명</th>
            <th class='text-center'>진행여부</th>
            <th class='text-center'>구매처</th>
            <th class='text-center'>업데이트 User</th>
            <th class='text-center'>업데이트 일시</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty productList}">
             <c:forEach items="${productList}" var="productMasterVO" varStatus="status">
             <tr id="select_tr_${productMasterVO.productCode}">
                 <td class='text-center'><a href="javascript:fcProduct_detailSearch('${productMasterVO.productCode}')"><c:out value="${productMasterVO.productCode}"></c:out></a></td>
                 <td class='text-center'><c:out value="${productMasterVO.barCode}"></c:out></td>
                 <td><c:out value="${productMasterVO.productName}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.recoveryYnView}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.updateUserName}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.updateDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty productList}">
           <tr>
           	<td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageProductMasterPageList" totalCount="${totalCount}" curPage="${productCon.curPage}" rowCount="${productCon.rowCount}" />
     <!-- //페이징 -->

