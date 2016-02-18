<%@ include file="/WEB-INF/views/admin/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:430px; .height:420px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>
    // 리스트 조회
    function fcStockDetail_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        stockDetailConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/stockdetailpagelist",
                    data:$("#stockDetailConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#stockDetailPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcStockDetail_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    function fcUpdate_stockCnt(index){

    	var frm=document.stockMasterPageListForm;
    	var dfrm=document.productDetailForm;
     	var amtCnt = frm.groupId.length;
     	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
    	
    	var groupId='';
    	var safeStock=0;
    	var holdStock=0;
     	
     	if(amtCnt > 1){
     		
     		groupId=frm.groupId[index-1].value;
     		safeStock=isnullStr(frm.safeStock[index-1].value);
     		holdStock=isnullStr(frm.holdStock[index-1].value);

     	}else{
     		
     		groupId=frm.groupId.value;
     		safeStock=isnullStr(frm.safeStock.value);
     		holdStock=isnullStr(frm.holdStock.value);

     	}
     	
     	dfrm.groupId.value=groupId;
     	dfrm.safeStock.value=safeStock;
     	dfrm.holdStock.value=holdStock;
     	
     	if (confirm('선택 지점의 안전재고 와 보유재고 수량을 변경 하시겠습니까?')){    
	
		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/master/stockcntmodify",
		           data:$("#productDetailForm").serialize(),
		           success: function(result) {
	
						if(result=='1'){
							 alert('재고수량 변경을 성공했습니다.');
						} else{
							 alert('재고수량 변경을 실패했습니다.');
						}
		           },
		           error:function(){
		        	   
		        	   alert('재고수량 변경을 실패했습니다.');
		           }
		    });
	    
     	}
    	
    }
    
</SCRIPT>
<!-- 사용자관리 -->
<div class="container-fluid">     
     <div class="form-group" >
	 <form:form commandName="stockVO" name="productDetailForm" id="productDetailForm" method="post" action="" >
	  <input type="hidden" name="productCode"  id="productCode" value="${productMasterVO.productCode}">
	  <input type="hidden" name="groupId"  id="groupId" value="">
	  <input type="hidden" name="safeStock"  id="safeStock" value="">
	  <input type="hidden" name="holdStock"  id="holdStock" value="">
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#33b9ae" >품목코드</th>
          <th class='text-center'><c:out value="${productMasterVO.productCode}"/></th>
          <th class='text-center' style="background-color:#33b9ae">바코드</th>
          <th class='text-center'><c:out value="${productMasterVO.barCode}"/></th>
          <th class='text-center' style="background-color:#33b9ae">입고단가</th>
          <th class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${productMasterVO.productPrice}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#33b9ae" >품목명</th>
          <th colspan='5' class='text-center'><c:out value="${productMasterVO.productName}"/></th>
        </tr>
      	<tr>
          <th class='text-center' style="background-color:#33b9ae" >구매처코드</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.companyCode}"/></th>
          <th class='text-center' style="background-color:#33b9ae">구매처명</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.companyName}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#33b9ae" >그룹1</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.group1}"/></th>
          <th class='text-center' style="background-color:#33b9ae">그룹1명</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.group1Name}"/></th>
      	<tr>
          <th class='text-center' style="background-color:#33b9ae" >그룹2</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.group2}"/></th>
          <th class='text-center' style="background-color:#33b9ae">그룹2명</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.group2Name}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#33b9ae" >그룹3</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.group3}"/></th>
          <th class='text-center' style="background-color:#33b9ae">그룹3명</th>
          <th colspan='2' class='text-center'><c:out value="${productMasterVO.group3Name}"/></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
</div >
