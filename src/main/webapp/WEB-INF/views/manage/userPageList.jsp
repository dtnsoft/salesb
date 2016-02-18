<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageUserManagePageList(page) {
        document.userManageConForm.curPage.value = page;
        var dataParam = $("#userManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/userpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#userManagePageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="userlistVO" name="userManagePageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#33b9ae">
	        <th><input type="checkbox"  id="userCheckAll"  name="userCheckAll" onchange="fcUserManage_checkAll();" title="전체선택" /></th>
	        <th>아이디</th>
            <th>이름</th>
            <th>지점</th>
            <th>권한</th>
            <th>SMS수신여부</th>
            <th>사용유무</th>
            <th>수정자</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty userList}">
             <c:forEach items="${userList}" var="userListVO" varStatus="status">
             <tr id="select_tr_${userListVO.userId}">
                 <td><input type="checkbox" id="userCheck" name="userCheck" value="${userListVO.userId}" title="선택" /></td>
                 <td><a href="javascript:fcUserManage_detailSearch('${userListVO.userId}')"><c:out value="${userListVO.userId}"></c:out></a></td>
                 <td><c:out value="${userListVO.userName}"></c:out></td>
                 <td><c:out value="${userListVO.groupName}"></c:out></td>
                 <td><c:out value="${userListVO.authName}"></c:out></td>
                 <td><c:out value="${userListVO.smsYn}"></c:out></td>
                 <td><c:out value="${userListVO.useYn}"></c:out></td>
                 <td><c:out value="${userListVO.updateUserName}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty userList}">
           <tr>
               <td colspan='8' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageUserManagePageList" totalCount="${totalCount}" curPage="${userCon.curPage}" rowCount="${userCon.rowCount}" />
     <!-- //페이징 -->

    