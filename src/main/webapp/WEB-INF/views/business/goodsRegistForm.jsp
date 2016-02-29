<%@ include file="/WEB-INF/views/salesb/topBusiness.jsp" %>
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
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;개별 상품 등록
		 </p>
		 <!--// 타이틀 --> 
          <div class="well">
           	<h5> <strong><em class="num">1. </em></strong>상품 기본정보</h5>
				<table  class="table table-bordered">
			  <thead></thead>
			  <tbody>
			  	<tr>
		          <td style="background-color:#F5F5F5">상품명</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="상품명"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">상품코드</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="상품코드(미입력시자동생성)"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">모데명</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="모델명"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">소비자가격</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="소비자가격"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">원가</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="원가"></td>
			    </tr>
			  </tbody>
		    </table>
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


