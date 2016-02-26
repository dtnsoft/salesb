<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageCustManagePageList(page) {
        document.custManageConForm.curPage.value = page;
        var dataParam = $("#custManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/custpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#custManagePageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="custlistVO" name="custManagePageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#33b9ae">
	        <th><input type="checkbox"  id="custCheckAll"  name="custCheckAll" onchange="fcCustManage_checkAll();" title="전체선택" /></th>
	        <th>고객키</th>
            <th>폰번호</th>
            <th>kakao</th>
            <th>naver</th>
            <th>facebook</th>
            <th>google</th>
            <th>pinterest</th>
            <th>고객 가입일시</th>
            <th>최종로그인매체</th>
            <th>최종접속좌표</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty custList}">
             <c:forEach items="${custList}" var="custListVO" varStatus="status">
             <tr id="select_tr_${custListVO.customerKey}">
                 <td><input type="checkbox" id="custCheck" name="custCheck" value="${custListVO.customerKey}" title="선택" /></td>
                 <td><a href="javascript:fcCustManage_detailSearch('${custListVO.customerKey}')"><c:out value="${custListVO.customerKey}"></c:out></a></td>
                 <td><c:out value="${custListVO.sbPhoneNumber}"></c:out></td>
                 <td><c:out value="${custListVO.socialId1}"></c:out></td>
                 <td><c:out value="${custListVO.socialId2}"></c:out></td>
                 <td><c:out value="${custListVO.socialId3}"></c:out></td>
                 <td><c:out value="${custListVO.socialId4}"></c:out></td>
                 <td><c:out value="${custListVO.socialId5}"></c:out></td>
                 <td><c:out value="${custListVO.registDateTime}"></c:out></td>
                 <td><c:out value="${custListVO.lastloginType}"></c:out></td>
                 <td><c:out value="(${custListVO.lastLat},${custListVO.lastlon})"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty custList}">
           <tr>
               <td colspan='11' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageCustManagePageList" totalCount="${totalCount}" curPage="${custCon.curPage}" rowCount="${custCon.rowCount}" />
     <!-- //페이징 -->

    