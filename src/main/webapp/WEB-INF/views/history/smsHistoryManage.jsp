<%@ include file="/WEB-INF/views/admin/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcSmsManage_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        smsManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/history/smshistorypagelist",
                    data:$("#smsManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#smsHistoryPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcSmsManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    $(function() {
        // 기간 설정 타입 1 
        // start Date 설정시 end Date의 min Date 지정
        $( "#start_smsDate" ).datepicker({
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
                $( "#end_smsDate" ).datepicker( "option", "minDate", selectedDate );
            }
        }); 
         // end Date 설정시 start Date max Date 지정
        $( "#end_smsDate" ).datepicker({
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
                $( "#start_smsDate" ).datepicker( "option", "maxDate", selectedDate );
            }
        });
 
        // 기간 설정 타입 2 
        // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
        $("#start_smsDate").datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($( "#start_smsDate" ).val() < selectedDate)
                {
                    $( "#end_smsDate" ).val(selectedDate);
                }
            }
        }); 
        // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
        $( "#end_smsDate" ).datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($("#start_smsDate" ).val() > selectedDate)
                {
                    $("#start_smsDate" ).val(selectedDate);
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
 	   	   $('#start_smsDate').datepicker("show");
 	   } else if(div == "2"){
 		   $('#end_smsDate').datepicker("show");
 	   }  
 	}
</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">SMS 전송이력</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="smsConVO" id="smsManageConForm" name="smsManageConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        	<label for="start_smsDate end_smsDate">SMS발송일자 :</label>
				<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_smsDate" id="start_smsDate" value="${smsConVO.start_smsDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_smsDate" id="end_smsDate" value="${smsConVO.end_smsDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            <br><br>
        	    <label for="con_groupId">지점선택 :</label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${smsConVO.groupId}">
                    <option value="">전체</option>
                    <c:forEach var="groupVO" items="${group_comboList}" >
                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                    </c:forEach>
                </select>
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >수신번호</option>
                    <option value="02" >발신번호</option>
                    <option value="03" >전송자명</option>
                    <option value="04" >전송자ID</option>
                    <option value="05" >결과코드</option>
                    <option value="06" >결과메세지</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${smsConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcSmsManage_listSearch()">조회</button>
	            <!--  >button type="button" class="btn" onClick="">excel</button-->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=smsHistoryPageList></div>

</div>
<br>
<%@ include file="/WEB-INF/views/admin/footer.jsp" %>
<script>
fcSmsManage_listSearch();
</script>