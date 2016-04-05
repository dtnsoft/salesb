<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
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
		  <!-- 조회조건 -->
		  <form:form class="form-inline" role="form" commandName="tokenConVO" id="tokenManageConForm" name="tokenManageConForm" method="post" action="" >
	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
	        <!-- Blog Search Well -->
	         <!-- 
	         <div class="well">
	             <div class="input-group">
	                 <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="조회일자">
	                 <span class="input-group-btn">
	                     <button class="btn btn-default" type="button" onClick="javascript:fcToken_listSearch()">
	                         <span class="glyphicon glyphicon-search" ></span>
	                 </button>
	                 </span>
	             </div>
	         </div>-->
		  </form:form>
		  <!-- //조회 -->
		  
 <div class="row wrapper border-bottom white-bg page-heading">
     <div class="col-lg-10">
        <h2>상품 토큰현황</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>나의상품</a>
            </li>
            <li class="active">
                <strong>상품토큰현황</strong>
            </li>
        </ol>
     </div>
     <div class="col-lg-2">
     </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
  <!-- 조회결과리스트 -->
  <div id=myTokenList></div>
  
</div>

<script>
fcToken_listSearch();
</script>
