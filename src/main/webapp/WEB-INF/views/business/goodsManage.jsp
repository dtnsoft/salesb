<%@ include file="/WEB-INF/views/salesb/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcGoods_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        goodsManageConForm.curPage.value = curPage;

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/goodspagelist",
                    data:$("#goodsManageConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#goodsPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }

  //레이어팝업 : 고객수정 Layer 팝업
    function fcCustManage_detailSearch(customerKey){

    	$('#custManageModify').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 400,
            height : 580,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/custmodifyform',{
    				'customerKey' : customerKey
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#custManageModify").dialog('close');

                    });
            }
            ,close:function(){
                $('#custManageModify').empty();
            }
        });
    };
    
    //체크박스 전체선택
    function fcCustManage_checkAll(){
    	$("input:checkbox[id='custCheck']").prop("checked", $("#custCheckAll").is(":checked"));
    }
    
    //고객 삭제
    function fcCustManage_delete(){
    	
    	var checkedCnt = $('input:checkbox[ name="custCheck"]:checked').length;

    	if(checkedCnt <= 0){
        	alert("삭제 대상을 선택해 주세요!");
        	return;
        }
        
        var arrDelCustId = "";
        $('input:checkbox[name="custCheck"]').each(function() {
            if ($(this).is(":checked")) {
            	arrDelCustId += $(this).val() + "^";
            }   
        });
        
        var paramString = $("#custManagePageListForm").serialize() + "&arrDelCustId="+arrDelCustId;

        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/custdeletes",
            data:paramString,
            cache : false,
            success: function(result) {
                commonDim(false);
                if(result=='1'){
					 alert('고객 삭제를 성공했습니다.');
				} else{
					 alert('고객 삭제를 실패했습니다.');
				}
				
				fcCustManage_listSearch();
				
            },
            error:function(error){
                commonDim(false);
                alert('고객 삭제를 실패했습니다.');
            }
        });

    }

</SCRIPT>
<div id="wrap" class="wrap" >
	<div class="container">
	     <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;상품 등록목록 (수정)
		 </p>
		 <hr>
	      <!--// 타이틀 --> 
		  <!-- 조회조건 -->
		  <form:form class="form-inline" role="form" commandName="goodsConVO" id="goodsManageConForm" name="goodsManageConForm" method="post" action="" >
	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
	        <input type="hidden" name="con_customerKey"          id="con_customerKey"         value=""  />
	        <input type="hidden" name="customerKey"              id="customerKey"         value=""  />
	        <!-- Blog Search Well -->
	         <div class="well">
	             <div class="input-group">
	                 <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품명으로 검색하세요">
	                 <span class="input-group-btn">
	                     <button class="btn btn-default" type="button" onClick="javascript:fcGoods_listSearch()">
	                         <span class="glyphicon glyphicon-search" ></span>
	                 </button>
	                 </span>
	             </div>
	             <!-- /.input-group -->
	         </div>
		  </form:form>
		  <!-- //조회 -->
	
		  <!-- 조회결과리스트 -->
		<div id=goodsPageList></div>
	</div>
</div>
<script>
fcGoods_listSearch();
</script>