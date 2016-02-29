<%@ include file="/WEB-INF/views/salesb/top.jsp" %>
<script>

	function fcGoods_regist(){
		
		alert('상품을 등록하시겠습니까?');
	}

</script>
	<div class="container">
	<!-- container -->
	  <form:form class="form-inline" role="form" commandName="customerVO" id="customerModifyForm" name="customerModifyForm" method="post" action="">
		 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;상품페이지 제작
		 </p>
		 <!--// 타이틀 --> 
         <div class="well">
           	<h5> <strong><em class="num">2. </em></strong>상품 이미지</h5>
				<img src="<%= request.getContextPath() %>/images/salesbaron.jpg" alt="상품이미지" width="200" height="200">
	            <br>
	            <input type="file"  id="cfiles" name="cfiles" onChange="c_uploadImg_Change( this.value )" >
	            <br><br>
	            <img src="<%= request.getContextPath() %>/images/salesbaron.jpg" alt="상품이미지" width="200" height="200">
	            <br>
	            <input type="file"  id="cfiles" name="cfiles" onChange="c_uploadImg_Change( this.value )" >
	            <br><br>
	            <img src="<%= request.getContextPath() %>/images/salesbaron.jpg" alt="상품이미지" width="200" height="200">
	            <br>
	            <input type="file"  id="cfiles" name="cfiles" onChange="c_uploadImg_Change( this.value )" >
         </div> 
         <div class="well">
           	<h5> <strong><em class="num">3. </em></strong>상품 설명</h5>
				<textarea style='ime-mode:active;' row="10" class="form-control" id="counselResult" maxlength="200" name="counselResult"  value="" placeholder="상품설명" ></textarea>
         </div> 
     	 <div class="clm_detail_btn">
	         <div class="clm_btn">
	           <a href="javascript:fcGoods_regist();" class="btn_ty2">등록하기</a>
	         </div>
         </div>
  	</form:form>
  </div>


