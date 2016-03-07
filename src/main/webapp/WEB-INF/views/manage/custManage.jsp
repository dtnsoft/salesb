<%@ include file="/WEB-INF/views/admin/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcCustManage_listSearch(curPage){

        custManageConForm.con_customerKey.value = "";
        curPage = (curPage==null) ? 1:curPage;
        custManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/custpagelist",
                    data:$("#custManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#custManagePageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcCustManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    //레이어팝업 : 고객등록 Layer 팝업
    function fcCustManage_regForm(){

    	$('#custManageRegist').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 400,
            height : 580,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/custregform');
                //$("#custRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#custManageRegist").dialog('close');

                    });
            }
            ,close:function(){
                $('#custManageRegist').empty();
            }
        });
    };
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
    //레이어팝업 : 고객등록 Layer 팝업
    function fcCustManage_excelForm(){

    	$('#custExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 300,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/custexcelform');
                //$("#custRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#custExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#custExcelForm').empty();
            }
        });
    };

</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">일반 회원관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="custConVO" id="custManageConForm" name="custManageConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <input type="hidden" name="con_customerKey"          id="con_customerKey"         value=""  />
        <input type="hidden" name="customerKey"              id="customerKey"         value="${custConVO.customerKey}"  />
        <fieldset>
        	<div class="form-group">
                <label for="con_useYn">사용여부 :</label>
				<select class="form-control" title="사용유무" id="con_useYn" name="con_useYn" >
                    <option value="" >전체</option>
                    <option value="Y" >사용</option>
                    <option value="N" >미사용</option>
                </select>
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >고객명</option>
                    <option value="02" >고객ID</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${custConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcCustManage_listSearch()">조회</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=custManagePageList></div>
  
  <!-- //고객 등록/삭제 -->
  <button type="button" class="btn btn-danger" onClick="fcCustManage_delete()">삭제</button>
  
  <!-- 고객 일괄등록-->
  <div id="custExcelForm"  title="고객 일괄등록"></div>
  
  <!-- 고객 등록-->
  <div id="custManageRegist"  title="고객 등록"></div>

  <!-- 고객 수정-->
  <div id="custManageModify"  title="고객 수정"></div>

</div>
<br>
<%@ include file="/WEB-INF/views/admin/footer.jsp" %>
<script>
fcCustManage_listSearch();
</script>