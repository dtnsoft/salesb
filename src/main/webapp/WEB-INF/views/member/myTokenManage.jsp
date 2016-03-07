<%@ include file="/WEB-INF/views/salesb/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcToken_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        tokenManageConForm.curPage.value = curPage;

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/member/mytokenlist",
                    data:$("#tokenManageConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#myTokenList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }

</SCRIPT>
	<div class="container">
		<!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;상품토큰현황(온라인 판매를 위한 토큰 발급현황)
		 </p>
		 <hr>
		 <!--// 타이틀 --> 
		  <!-- 조회조건 -->
		  <form:form class="form-inline" role="form" commandName="tokenConVO" id="tokenManageConForm" name="tokenManageConForm" method="post" action="" >
	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
	        <!-- Blog Search Well -->
	         <div class="well">
	             <div class="input-group">
	                 <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="조회일자">
	                 <span class="input-group-btn">
	                     <button class="btn btn-default" type="button" onClick="javascript:fcToken_listSearch()">
	                         <span class="glyphicon glyphicon-search" ></span>
	                 </button>
	                 </span>
	             </div>
	             <!-- /.input-group -->
	         </div>
		  </form:form>
		  <!-- //조회 -->
	
		  <!-- 조회결과리스트 -->
		<div id=myTokenList></div>
	</div>

<script>
fcToken_listSearch();
</script>