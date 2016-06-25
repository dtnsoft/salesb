<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
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

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-12">
        <h2>상품 등록목록 (수정)</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>상품 등록/변경</a>
            </li>
            <li class="active">
                <strong>상품 등록목록(수정)</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-12">
    </div>
</div>
   	
<div class="wrapper wrapper-content animated fadeInRight">
      		
  <form:form class="form-inline" role="form" commandName="productConVO" id="goodsManageConForm" name="goodsManageConForm" method="post" action="" >
  <input type="hidden" name="curPage"             id="curPage"            value="1" />
  <input type="hidden" name="rowCount"            id="rowCount"           value="5"/>
  <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
  </form:form>
            
  <div class="row">
    <div class="col-lg-12">
       <div class="ibox float-e-margins">
          <div class="ibox-content">
             <form method="get" class="form-horizontal">
          		<div class="form-group"><label class="col-sm-2 control-label">상품검색</label>
                     <div class="col-sm-10">
                         <div class="input-group"><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품명으로 검색하세요"> <span class="input-group-btn"> 
                         <button type="button" class="btn btn-primary" onClick="javascript:fcGoods_listSearch()" >Go!
                         </button> </span></div>
                     </div>
                 </div>
             </form>
          </div>
       </div>
    </div>
  </div>         
      
 <!-- 조회결과리스트 -->
 <div id=goodsPageList></div>

</div>

<script>
	fcGoods_listSearch();
</script>