<%@ include file="/WEB-INF/views/admin/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcWorkManage_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        workManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/history/workhistorypagelist",
                    data:$("#workManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#workHistoryPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcWorkManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    function codSetting(){
    
    	var cfrm=document.workManageConForm.con_workCategory;
    	var frm=document.workManageConForm.con_workCode;
    	
    	//clearSelectOptions(document.workManageConForm.con_workCode);	
    	//addSelectOption(document.workManageConForm.con_workCode, "", "전체");	
    	
    	$.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/history/workcodeoption?workCategory="+cfrm.value,
               success: function(result) {
                 
                   $("#workCodeOption").html(result);
               },
               error:function() {
                  
               }
        });
    	
    	
    }
	function clearSelectOptions(object)
	{
		if(object.tagName != "SELECT") return;
		
		for(var i = object.options.length - 1 ; i >= 0 ; i--) 
		{
			object.options.remove(i);
		}
	}
	function addSelectOption(object, value, text)
	{
		option = document.createElement("OPTION");
		option.text = text;
		option.value = value;
		object.options.add(option);
	}
	
    //셀렉트 박스 삭제
    function clearComboBox(form)
    {
    	
    	clearSelectOptions(document.workManageConForm.con_workCode);	
		addSelectOption(document.workManageConForm.con_workCode, "", "전체");
    }
    $(function() {
        // 기간 설정 타입 1 
        // start Date 설정시 end Date의 min Date 지정
        $( "#start_workDate" ).datepicker({
            dateFormat: "yy-mm-dd",
            dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
            monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            maxDate : "+0D",
            onClose: function( selectedDate ) {
                $( "#end_workDate" ).datepicker( "option", "minDate", selectedDate );
            }
        }); 
         // end Date 설정시 start Date max Date 지정
        $( "#end_workDate" ).datepicker({
            dateFormat: "yy-mm-dd",
            dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
            monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            maxDate : "+0D",
            onClose: function( selectedDate ) {
                $( "#start_workDate" ).datepicker( "option", "maxDate", selectedDate );
            }
        });
 
        // 기간 설정 타입 2 
        // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
        $("#start_workDate").datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($( "#start_workDate" ).val() < selectedDate)
                {
                    $( "#end_workDate" ).val(selectedDate);
                }
            }
        }); 
        // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
        $( "#end_workDate" ).datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($("#start_workDate" ).val() > selectedDate)
                {
                    $("#start_workDate" ).val(selectedDate);
                }
            }
        });
        
        //날짜
        $( "#date" ).datepicker({
            changeMonth: true ,
            changeYear: true ,
            showMonthAfterYear: true ,
            dateFormat: "yy-mm-dd",
            dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
            monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            defaultDate: "+1w",
            numberOfMonths: 1
        }); 


    });
    function showCalendar(div){

 	   if(div == "1"){
 	   	   $('#start_workDate').datepicker("show");
 	   } else if(div == "2"){
 		   $('#end_workDate').datepicker("show");
 	   }  
 	}
</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">업무 처리이력</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="workConVO" id="workManageConForm" name="workManageConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        	<label for="start_workDate end_workDate">작업일자 :</label>
				<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_workDate" id="start_workDate" value="${workConVO.start_workDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_workDate" id="end_workDate" value="${workConVO.end_workDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
			    <label for="searchGubun">작업구분 :</label>
				<select class="form-control" title="작업구분" id="con_workCategory" name="con_workCategory" value="" onChange="codSetting()">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
           		<div class="form-group" id=workCodeOption></div>
	            <br><br>
        	    <label for="con_groupId">지점선택 :</label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${workConVO.groupId}">
                    <option value="">전체</option>
                    <c:forEach var="groupVO" items="${group_comboList}" >
                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                    </c:forEach>
                </select>
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >사용자명</option>
                    <option value="02" >사용자Id</option>
                    <option value="03" >작업코드</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${workConVO.searchKey1Value}" onkeypress="javascript:return checkKey(event);"/>
				
				<label for="searchGubun">업체 :</label>
				<select class="form-control" title="검색조건" id="searchKey1Gubun" name="searchKey1Gubun" value="">
                	<option value="01" >업체명</option>
                    <option value="02" >업체코드</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchKey1Value" name="searchKey1Value"  value="${workConVO.searchKey1Value}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcWorkManage_listSearch()">조회</button>
	            <!--  >button type="button" class="btn" onClick="">excel</button-->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=workHistoryPageList></div>

  <div id="workDetail"  title="업무상세내역"></div>
  
    <div id="deferReasonList"  title="보류사유"></div>
    <div id="memoManage"  title="메모관리"></div>
    <!-- //보류 상세화면 -->
    <div id="etcManage"  title="비고"></div>
</div>
<br>
<%@ include file="/WEB-INF/views/admin/footer.jsp" %>
<script>
fcWorkManage_listSearch();
codSetting();
</script>