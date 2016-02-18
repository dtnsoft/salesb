<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageWorkManagePageList(page) {
        document.workManageConForm.curPage.value = page;
        var dataParam = $("#workManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/history/workhistorypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#workHistoryPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

    
    // 보류 상세 페이지 리스트 Layup
    function fcWork_Detail(workKey1,workKey2,workKey3,deiscription) {
    	//alert(workKey1);
    	//alert(deiscription);

    	if(workKey1=='N'){
    		
    		alert('작업 상세이력이 없습니다.');
    		return;
    	}
    	
    	var detailinfos=deiscription.split('|');
    	
    	var url='<%= request.getContextPath() %>'+detailinfos[0];
    	
    	url=url.replace('$1',workKey1);
    	
		if(workKey2!='N'){
			url=url.replace('$2',encodeURIComponent(workKey2));
    	}
		
		if(workKey3!='N'){
			url=url.replace('$3',encodeURIComponent(workKey3));
    	}

    	var w=detailinfos[1];
    	var h=detailinfos[2];
    	
    	//alert(url);
    	//alert(w);
    	//alert(h);

    	$('#workDetail').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : w,
            height : h,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load(url);
 
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#workDetail").dialog('close');

                    });
            }
            ,close:function(){
                $('#workDetail').empty();
            }
        });
    };

</SCRIPT>
     <form:form commandName="workHistoryVO" name="workHistoryPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <colgroup>
	      <col width="5%" >
	      <col width="10%" >
	      <col width="10%" >
	      <col width="10%">
	      <col width="10%">
	      <col width="15%">
	      <col width="10%">
	      <col width="10%">
	      <col width="10%">
	      <col width="10%">
	     </colgroup>
	      <tr style="background-color:#33b9ae">
	        <th rowspan="2" class='text-center'>no</th>
	        <th rowspan="2" class='text-center'>작업일시</th>
	        <th rowspan="2" class='text-center'>지점명</th>
	        <th rowspan="2" class='text-center'>작업자</th>
            <th rowspan="2" class='text-center'>작업구분</th>
            <th rowspan="2" class='text-center'>작업명</th>
            <th colspan="4" class='text-center'>작업상세</th>
	      </tr>
	      <tr style="background-color:#33b9ae">
	          <th class='text-center'>코드</th>
		      <th class='text-center'>상세내역</th>
		      <th class='text-center'>발주서</th>
		      <th class='text-center'>업체</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty workList}">
             <c:forEach items="${workList}" var="workHistoryVO" varStatus="status">
             <tr id="select_tr_${workHistoryVO.idx}">
               <td class='text-center'><c:out value="${workList.size()-(status.count-1)}"></c:out></td>
               <td class='text-center'><c:out value="${workHistoryVO.workDateTime}"></c:out></td> 
               <td class='text-center'><c:out value="${workHistoryVO.workGroupName}"></c:out></td>
               <td class='text-center'><c:out value="${workHistoryVO.workUserName}"></c:out></td>
               <td class='text-center'><c:out value="${workHistoryVO.workCategoryName}"></c:out></td>
               <td class='text-center'><c:out value="${workHistoryVO.workCodeName}"></c:out></td>
               <td class='text-center'><c:out value="${workHistoryVO.workKey1}"></c:out></td>
               <td class='text-center'>
               <c:if test="${workHistoryVO.description!='N' && workHistoryVO.workKey1!='N'}">
               		<button type="button" class="btn btn-xs btn-success" onClick="fcWork_Detail('${workHistoryVO.workKey1}','${workHistoryVO.workKey2}','${workHistoryVO.workKey3}','${workHistoryVO.description}')">작업상세내역</button>
               </c:if>
               </td>
               <td class='text-center'>
                <c:if test="${(workHistoryVO.workKey1!='N' && workHistoryVO.workCategory=='CH') || (workHistoryVO.workKey1!='N' && workHistoryVO.workCode=='OD004') }">
               		<a href="<%= request.getContextPath() %>/order/orderdownload?orderCode=${workHistoryVO.workKey1}">
						[발주서 다운로드]
					</a>
               </c:if>
               </td>
               <td class='text-center'>
               <c:out value="${workHistoryVO.companyName}"></c:out>
               </td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty workList}">
           <tr>
           	<td colspan='10' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageWorkManagePageList" totalCount="${totalCount}" curPage="${workConVO.curPage}" rowCount="${workConVO.rowCount}" />
     <!-- //페이징 -->

    