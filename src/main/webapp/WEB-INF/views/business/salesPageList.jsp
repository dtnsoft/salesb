<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSalesPageList(page) {
        document.SalesManageConForm.curPage.value = page;
        var dataParam = $("#salesManageConForm").serialize();
        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/salespagelist",
               	data:dataParam,
               success: function(result) {
                   //commonDim(false);
                   $("#salesPageList").html(result);
               },
               error:function() {
                  // commonDim(false);
               }
        });

    }
   
</SCRIPT>
    <div class="row">
        <div class="col-lg-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-warning pull-right">Data has changed</span>
                    <h5>Sales stastic</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-xs-4">
                            <small class="stats-label">상품 토큰 발급현황</small>
                            <h4>${tokenStateVo.tokenCnt}건</h4>
                        </div>

                        <div class="col-xs-4">
                            <small class="stats-label">신규주문</small>
                            <h4>${orderStateVo.newOrerCnt}건</h4>
                        </div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-xs-4">
                            <small class="stats-label">배송대상</small>
                            <h4>${orderStateVo.deliveryTagetCnt}건</h4>
                        </div>

                        <div class="col-xs-4">
                            <small class="stats-label">배송중</small>
                            <h4>${orderStateVo.deliveryIngCnt}건</h4>
                        </div>
                        
                        <div class="col-xs-4">
                            <small class="stats-label">배송완료</small>
                            <h4>${orderStateVo.deliveryColseCnt}원</h4>
                        </div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                      

                        <div class="col-xs-4">
                            <small class="stats-label">매출현황</small>
                            <h4>${orderStateVo.salesAmt}원</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
           <div class="ibox float-e-margins">
               <div class="ibox-title">
                   <h5>판매관리</h5>
               </div>
               <div class="ibox-content">
                   <div class="row">
                       <div class="col-sm-9 m-b-xs">
                           <div data-toggle="buttons" class="btn-group">
                               <label class="btn btn-sm btn-white"> <input type="radio" id="option1" name="options" onChange="setToDay()"> 오늘 </label>
                               <label class="btn btn-sm btn-white "> <input type="radio" id="option2" name="options"  onChange="setMonth()"> 1개월 </label>
                               <label class="btn btn-sm btn-white"> <input type="radio" id="option3" name="options"  onChange="setDate()"> 기간선택 </label>
                           </div>
                       </div>
                       <div class="col-sm-3">
                          <div class="input-group" id="data_5">
	                      <div class="input-daterange input-group" id="datepicker">
	                          <input type="text" class="input-sm form-control" name="start" value="05/14/2014"/>
	                          <span class="input-group-addon">to</span>
	                          <input type="text" class="input-sm form-control" name="end" value="05/22/2014" />
	                          <span class="input-group-btn">
	                                   <button type="button" class="btn btn-sm btn-primary" onClick="javascript:fcSales_listSearch()"> Go!</button> </span>
	                      </div>
                  		</div>
                       </div>
                   </div>
                   <div class="table-responsive">
                       <table class="table table-striped">
                           <thead>
                           <tr>
                             <th>No</th>
                             <th>Name </th>
                             <th>누적판매(건)</th>
                             <th>누적금액(원)</th>
                           </tr>
                           </thead>
                           <tbody>
                           <c:if test="${!empty bestList}">
                           <c:forEach items="${bestList}" var="salesVO" varStatus="status">
							 <tr>
                               <td><c:out value=""></c:out></td>
                               <td><c:out value="${salesVO.customerKey}"></c:out></td>
                               <td><c:out value="${salesVO.accrueSalesCnt}"></c:out></td>
                               <td><c:out value="${salesVO.accrueSalesAmt}"></c:out></td>
                          	 </tr>
                           </c:forEach>
						   </c:if>
						   <c:if test="${empty bestList}">
                           <tr>
                             <td colspan='4' class='text-center'>조회된 데이터가 없습니다.</td>
						   </tr>
						   </c:if>
                           </tbody>
                       </table>
                   </div>
               </div>
           </div>
       </div>
    </div>
	<div class="row">
       <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-content">
             <!-- 페이징 -->
		     <taglib:paging cbFnc="goPageTokenPageList" totalCount="${totalCount}" curPage="${salesConVO.curPage}" rowCount="${salesConVO.rowCount}" />
		    <!-- //페이징 -->   
       </div>
          </div>
      </div>
