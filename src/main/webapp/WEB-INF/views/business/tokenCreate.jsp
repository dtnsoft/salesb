<%@ include file="/WEB-INF/views/salesb/topBusiness.jsp" %>
<script>

	function keyTest(){
		
		 
		 var saleurl=document.all('saleurl').innerText;
	
		 if(saleurl!=''){	 location.href=saleurl; }
	
		 
	}
	
	function orderKeyCreate(){
	
	
		$.ajax({
		       type: "POST",
		       async:true,
		          url:  '<%= request.getContextPath() %>/order/orderkeycreate?customerKey=&productCode=',
		         // data:paramString,
		          success: function(data) {
			
		        	 document.all('saleurl').innerText=data;
		        
		          },
		          error:function(){
		        	  
		        	  alert('error');	          
	
		          }
		 });
	
	} 

</script>
	<div class="container">
	<!-- container -->
	  <form:form class="form-inline" role="form" commandName="customerVO" id="customerModifyForm" name="customerModifyForm" method="post" action="">
		 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;토큰발급
		 </p>
		 <!--// 타이틀 --> 
          <div class="well">
           	<h5> <strong><em class="num">1. </em></strong>토큰정보</h5>
				<table  class="table table-bordered">
			  <thead></thead>
			  <tbody>
			  	<tr>
		          <td style="background-color:#F5F5F5">주문자</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="주문자"></td>
			    </tr>
			    <tr>
		          <td colspan="2" style="background-color:#F5F5F5" class='text-center'>상품정보</td>
		        </tr>
		        <tr>
		          <td colspan="2">
	             	<div class="input-group">
		                 <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품명으로 검색하세요">
		                 <span class="input-group-btn">
		                     <button class="btn btn-default" type="button" onClick="">
		                         <span class="glyphicon glyphicon-search" ></span>
		                 </button>
		                 </span>
		             </div>
				  </td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">상품명</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="상품명"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">상품설명</td>
		          <td><input type="text" class="form-control" id="cur_password" name="cur_password"  maxlength="50"  value="" placeholder="상품설명"></td>
			    </tr>
			  </tbody>
		    </table>
         </div> 
         <div class="well">
           	<h5> <strong><em class="num">2. </em></strong>토큰요청</h5>
				<p class="tx2">토큰값 : <a href="javascript:keyTest()"><span class="nav_mnu" id="saleurl" style="color:blue"></span></a></p>
         </div> 
		 <div class="clm_detail_btn">
	        <div class="clm_btn">
	          <a href="javascript:orderKeyCreate();" class="btn_ty2">토큰 발급</a>
	        </div>
	    </div>
  	</form:form>
  </div>