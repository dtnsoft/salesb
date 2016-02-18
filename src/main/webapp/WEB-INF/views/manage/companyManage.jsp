<%@ include file="/WEB-INF/views/admin/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcCompanyManage_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        companyManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/companypagelist",
                    data:$("#companyManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#companyManagePageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcCompanyManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    //레이어팝업 : 업체등록 Layer 팝업
    function fcCompany_excelForm(){

    	$('#companyExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 300,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/companyexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#companyExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#companyExcelForm').empty();
            }
        });
    };
   
</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">업체관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="companyConVO" id="companyManageConForm" name="companyManageConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >업체명</option>
                    <option value="02" >업체코드</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${companyConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcCompanyManage_listSearch()">조회</button>
	            <!--  >button type="button" class="btn" onClick="">excel</button-->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=companyManagePageList></div>

  <!-- 업체정보 일괄등록 -->
  <button type="button" class="btn btn-primary" onClick="fcCompany_excelForm()">업체 일괄등록</button>
  
  <!-- 업체 일괄등록-->
  <div id="companyExcelForm"  title="업체 일괄등록"></div>


</div>
<br>
<%@ include file="/WEB-INF/views/admin/footer.jsp" %>
<script>
fcCompanyManage_listSearch();
MM_nbGroup('down','group4','menu_04','<%= request.getContextPath() %>/images/top/addys-menu_04_on.jpg',1);
</script>