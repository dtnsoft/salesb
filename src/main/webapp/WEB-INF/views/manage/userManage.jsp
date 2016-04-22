<%@ include file="/WEB-INF/views/admin/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcUserManage_listSearch(curPage){

        userManageConForm.con_userId.value = "";
        curPage = (curPage==null) ? 1:curPage;
        userManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/userpagelist",
                    data:$("#userManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#userManagePageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcUserManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    //레이어팝업 : 사용자등록 Layer 팝업
    function fcUserManage_regForm(){

    	$('#userManageRegist').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 400,
            height : 580,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/userregform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userManageRegist").dialog('close');

                    });
            }
            ,close:function(){
                $('#userManageRegist').empty();
            }
        });
    };
  //레이어팝업 : 사용자수정 Layer 팝업
    function fcUserManage_detailSearch(userId){

    	$('#userManageModify').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 400,
            height : 730,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/usermodifyform',{
    				'userId' : userId
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userManageModify").dialog('close');

                    });
            }
            ,close:function(){
                $('#userManageModify').empty();
            }
        });
    };
    
    //체크박스 전체선택
    function fcUserManage_checkAll(){
    	$("input:checkbox[id='userCheck']").prop("checked", $("#userCheckAll").is(":checked"));
    }
    
    //사용자 삭제
    function fcUserManage_delete(){
    	
    	var checkedCnt = $('input:checkbox[ name="userCheck"]:checked').length;

    	if(checkedCnt <= 0){
        	alert("삭제 대상을 선택해 주세요!");
        	return;
        }
        
        var arrDelUserId = "";
        $('input:checkbox[name="userCheck"]').each(function() {
            if ($(this).is(":checked")) {
            	arrDelUserId += $(this).val() + "^";
            }   
        });
        
        var paramString = $("#userManagePageListForm").serialize() + "&arrDelUserId="+arrDelUserId;

        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/userdeletes",
            data:paramString,
            cache : false,
            success: function(result) {
                commonDim(false);
                if(result=='1'){
					 alert('사용자 삭제를 성공했습니다.');
				} else{
					 alert('사용자 삭제를 실패했습니다.');
				}
				
				fcUserManage_listSearch();
				
            },
            error:function(error){
                commonDim(false);
                alert('사용자 삭제를 실패했습니다.');
            }
        });

    }
    //레이어팝업 : 사용자등록 Layer 팝업
    function fcUserManage_excelForm(){

    	$('#userExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 300,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/userexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#userExcelForm').empty();
            }
        });
    };

</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">사업자 회원관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="userConVO" id="userManageConForm" name="userManageConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <input type="hidden" name="con_userId"          id="con_userId"         value=""  />
        <input type="hidden" name="userId"              id="userId"         value="${userConVO.userId}"  />
        <fieldset>
        	<div class="form-group">
        	    <label for="con_groupId">지점선택 :</label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${userConVO.groupId}">
                    <option value="">전체</option>
                    <c:forEach var="groupVO" items="${group_comboList}" >
                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                    </c:forEach>
                </select>
                <label for="con_useYn">사용여부 :</label>
				<select class="form-control" title="사용유무" id="con_useYn" name="con_useYn" >
                    <option value="" >전체</option>
                    <option value="Y" >사용</option>
                    <option value="N" >미사용</option>
                </select>
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >사용자명</option>
                    <option value="02" >사용자ID</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${userConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_listSearch()">조회</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=userManagePageList></div>
  
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="fcUserManage_regForm()">등록</button>
  <button type="button" class="btn btn-danger" onClick="fcUserManage_delete()">삭제</button>
  <button type="button" class="btn btn-primary" onClick="fcUserManage_excelForm()">사용자 일괄등록</button>
  
  <!-- 사용자 일괄등록-->
  <div id="userExcelForm"  title="사용자 일괄등록"></div>
  
  <!-- 사용자 등록-->
  <div id="userManageRegist"  title="사용자 등록"></div>

  <!-- 사용자 수정-->
  <div id="userManageModify"  title="사용자 수정"></div>

</div>
<br>
<%@ include file="/WEB-INF/views/admin/footer.jsp" %>
<script>
fcUserManage_listSearch();
</script>