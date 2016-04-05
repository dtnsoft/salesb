<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
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
<!-- 조회조건 -->
  <form:form class="form-inline" role="form" commandName="salesConVO" id="salesManageConForm" name="salesManageConForm" method="post" action="" >
       <input type="hidden" name="curPage"             id="curPage"            value="1" />
       <input type="hidden" name="rowCount"            id="rowCount"           value="5"/>
       <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
       <input type="hidden" name="orderStartDate"          id="orderStartDate"         value="${strDeliveryDay}"  />
       <input type="hidden" name="orderEndDate"          id="orderEndDate"         value="${strToday}"  /> 
  </form:form>
 <!-- //조회 -->
 <div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>판매관리</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>판매관리</a>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">
    </div>
 </div>
        
 <!-- 조회결과리스트 -->
 <div id=salesPageList></div>
	    

<script>
fcSales_listSearch();
</script>

 