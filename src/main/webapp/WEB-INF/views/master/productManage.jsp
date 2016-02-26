<%@ include file="/WEB-INF/views/admin/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcProductMaster_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        productMasterConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/productpagelist",
                    data:$("#productMasterConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#productMasterPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcProductMaster_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
  //레이어팝업 : 사용자수정 Layer 팝업
    function fcProductMaster_detailSearch(productCode){

    	$('#productMasterDetail').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/productdetailform',{
    				'productCode' : productCode
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#productMasterDetail").dialog('close');

                    });
            }
            ,close:function(){
                $('#productMasterDetail').empty();
            }
        });
    };
    
    
    //레이어팝업 : 품목등록 Layer 팝업
    function fcProduct_excelForm(){

    	$('#productExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 300,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/productexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#productExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#productExcelForm').empty();
            }
        });
    };
    //레이어팝업 : 안전재고 Layer 팝업
    function fcSafeStock_excelForm(){

    	$('#safeStockExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 300,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/safestockexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#safeStockExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#safeStockExcelForm').empty();
            }
        });
    };
    //레이어팝업 : 보유재고 Layer 팝업
    function fcHoldStock_excelForm(){

    	$('#holdStockExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 300,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/holdstockexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#holdStockExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#holdStockExcelForm').empty();
            }
        });
    };
</SCRIPT>
<div class="container-fluid">

    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">품목관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="productConVO" id="productMasterConForm" name="productMasterConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
				<label for="searchGubun"><h6><strong>검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="03" >품목명</option>
                    <option value="01" >품목코드</option>
                	<option value="02" >바코드</option>
                    <option value="04" >구매처명</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${productConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcProductMaster_listSearch()">조회</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=productMasterPageList></div>

  <!-- 품목 안전재고 보유재고 일괄등록 -->
  <button type="button" class="btn btn-primary" onClick="fcProduct_excelForm()">품목 일괄등록</button>
  
  <!-- 품목 일괄등록-->
  <div id="productExcelForm"  title="품목 일괄등록"></div>

  <!-- 안전재고 일괄등록-->
  <div id="safeStockExcelForm"  title="안전재고 일괄등록"></div>

  <!-- 보유재고 일괄등록-->
  <div id="holdStockExcelForm"  title="보유재고 일괄등록"></div>

  <!-- 품목 상세페이지-->
  <div id="productDetail"  title="품목 상세현황"></div>
  
</div>
<br>
<%@ include file="/WEB-INF/views/admin/footer.jsp" %>
<script>
fcProductMaster_listSearch();
</script>