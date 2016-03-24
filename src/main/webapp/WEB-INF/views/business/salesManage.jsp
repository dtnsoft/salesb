<%@ include file="/WEB-INF/views/salesb/topBusiness.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcSales_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        salesManageConForm.curPage.value = curPage;
        
    	if(salesManageConForm.orderStartDate.value == ''){
    		alert('조회 시작일을 입력하세요');
    		return;	
    	}
    	
    	if(salesManageConForm.orderEndDate.value == ''){
    		alert('조회 종료일을 입력하세요');
    		return;	
    	}

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/salespagelist",
                    data:$("#salesManageConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#salesPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }

    function setToDay(){

    	var strToday='${strToday}';
    	
    	salesManageConForm.orderStartDate.value = strToday;
    	salesManageConForm.orderEndDate.value = strToday;
    	
    	fcSales_listSearch();
    }
    
    function setMonth(){

    	var strToday='${strToday}';
    	var strDeliveryDay='${strDeliveryDay}';
    	
    	salesManageConForm.orderStartDate.value = strDeliveryDay;
    	salesManageConForm.orderEndDate.value = strToday;
    	
    	fcSales_listSearch();
    }
    
    function setDate(){

    	salesManageConForm.orderStartDate.value = '';
    	salesManageConForm.orderEndDate.value = '';

    }

</SCRIPT>
	<div class="container">
	     <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;판매관리
		 </p>
		 <hr>
	      <!--// 타이틀 --> 
		  <!-- 조회조건 -->
		  <form:form class="form-inline" role="form" commandName="salesConVO" id="salesManageConForm" name="salesManageConForm" method="post" action="" >
	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="5"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
	        <!-- Blog Search Well -->
	         <div class="well">
	             <div class="input-group">
	                  	<h5>기간설정</h5>
	                  	<h5>
	             		<label class="radio-inline">
						  <input type="radio" name="periodId" id="periodId"  onChange="setToDay()"> 오늘
						</label>
			            <label class="radio-inline">
						   <input type="radio" name="periodId" id="periodId" checked onChange="setMonth()"> 1개월
						</label>
						<label class="radio-inline">
						   <input type="radio" name="periodId" id="periodId" checked onChange="setDate()"> 기간선택
						</label>
				</h5>
	             </div>
	             <!-- /.input-group -->
	         </div>
	         <div class="well">
	             <div class="input-group">
	                 <input type="text" style="width:100px" class="form-control" id="orderStartDate" name="orderStartDate"  value="${strDeliveryDay}" placeholder="주문시작일">
	                 <input type="text" style="width:100px" class="form-control" id="orderEndDate" name="orderEndDate"  value="${strToday}" placeholder="주문종료일">
	                 <span class="input-group-btn">
	                     <button class="btn btn-default" type="button" onClick="javascript:fcSales_listSearch()">
	                         <span class="glyphicon glyphicon-search" ></span>
	                 </button>
	                 </span>
	             </div>
	             <!-- /.input-group -->
	         </div>
		  </form:form>
		  <!-- //조회 -->
	
		  <!-- 조회결과리스트 -->
		<div id=salesPageList></div>
	</div>

<script>
fcSales_listSearch();
</script>