<%@ include file="/WEB-INF/views/salesb/topBusiness.jsp" %>
<script>

	function fcGoods_modify(){
		
		var frm=document.productModifyForm;
		
		if(frm.productName.value==''){
			alert('상품명을 입력하세요');
			return;
		}
		
		if(frm.salesPrice.value==''){
			alert('소비자 가격을 입력하세요');
			return;
		}
		
		if(frm.setteleRate.value==''){
			alert('1차 구매자 판매 수수료율을 입력하세요');
			return;
		}

		
		if (confirm('개별 상품정보를 수정 하시겠습니까?')){ 
		
		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/business/productmodify",
		           data:$("#productModifyForm").serialize(),
		           success: function(result) {

						if(result>0){
							 alert('개별 상품정보를 수정을 성공했습니다.');
							 fcMenu('<%= request.getContextPath() %>/business/goodsmanage');
						} else{
							 alert('개별 상품정보를 수정에 실패했습니다.');
						}
						
		           },
		           error:function(){
		        	   
		        	   alert('개별 상품정보를 수정에 실패했습니다.');
		        	   
		           }
		    });
		}
	}

</script>
	<div class="container">
	<!-- container -->
	  <form:form class="form-inline" role="form" commandName="productMasterVO" id="productModifyForm" name="productModifyForm" method="post" action="">
	     <input type="hidden" id="idx" name="idx" value="${productVO.idx}" >
		 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;개별 상품 수정
		 </p>
		 <hr>
		 <!--// 타이틀 --> 
          <div class="well">
           	<h5> <strong><em class="num">1. </em></strong>상품 기본정보</h5>
				<table  class="table table-bordered">
			  <thead></thead>
			  <tbody>
			  	<tr>
		          <td style="background-color:#F5F5F5">상품분류</td>
		          <td> 
                        <select class="form-control" id="productCategory" name="productCategory" >
                            <option value="01" >카테고리1</option>
                            <option value="02" >카테고리2</option>
                            <option value="03" >카테고리3</option>
                            <option value="04" >카테고리4</option>
                            <option value="05" >카테고리5</option>
                        </select>

                  </td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">상품명</td>
		          <td><input type="text" class="form-control" id="productName" name="productName"  maxlength="50"  value="${productVO.productName}" placeholder="상품명"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">상품코드</td>
		          <td><input type="text" class="form-control" id="productCode" name="productCode"  maxlength="10"  value="${productVO.productCode}" placeholder="상품코드(미입력시자동생성)"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">모델명</td>
		          <td><input type="text" class="form-control" id="modelName" name="modelName"  maxlength="50"  value="${productVO.modelName}" placeholder="모델명"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">제조사</td>
		          <td><input type="text" class="form-control" id="makeCompany" name="makeCompany"  maxlength="50"  value="${productVO.makeCompany}" placeholder="제조사"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">공급처</td>
		          <td><input type="text" class="form-control" id="suplycompany" name="suplycompany"  maxlength="50"  value="${productVO.suplycompany}" placeholder="공급처"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">원가</td>
		          <td><input type="text" class="form-control" id="productPrice" name="productPrice"  maxlength="50"  value="${productVO.productPrice}" placeholder="원가"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">소비자가</td>
		          <td><input type="text" class="form-control" id="salesPrice" name="salesPrice"  maxlength="50"  value="${productVO.salesPrice}" placeholder="소비자가격"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">1차 구매자 판매 수수료율</td>
		          <td><input type="text" class="form-control" id="setteleRate" name="setteleRate"  maxlength="50"  value="${productVO.setteleRate}" placeholder="소비자가격"></td>
			    </tr>
			    <tr>
		          <td style="background-color:#F5F5F5">재고수량</td>
		          <td><input type="text" class="form-control" id="stockCnt" name="stockCnt"  maxlength="5"  value="${productVO.stockCnt}" placeholder="재고수량"></td>
			    </tr>
			  </tbody>
		    </table>
         </div> 
         <div class="well">
           	<h5> <strong><em class="num">2. </em></strong>배송정보 설정</h5>
				<select class="form-control" id="deliveryOption" name="deliveryOption">
                            <option value="01" >1km 이내 무료</option>
                            <option value="02" >3개이상 무료</option>
                            <option value="03" >1000원 이상 무료</option>
                        </select>
         </div> 
         <div class="well">
           	<h5> <strong><em class="num">3. </em></strong>옵션정보 설정</h5>
				<table  class="table table-bordered">
			  <thead>
			    <tr>
			      <th>구분</th>
			      <th>옵션명</th>
			      <th>옵션값</th>
			      <th>추가/삭제</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<tr>
		          <td>1</td>
		          <td>색상</td>
		          <td>1.1빨강/1.2노랑/1.3파랑</td>
		          <td>+ -</td>
			    </tr>
			    <tr>
		          <td>2</td>
		          <td>사이즈</td>
		          <td>2.90/1.2노랑/1.3파랑</td>
		          <td>+ -</td>
			    </tr>
			  </tbody>
		    </table>
         </div> 
     	 <div class="clm_detail_btn">
	         <div class="clm_btn">
	           <a href="javascript:fcGoods_modify();" class="btn_ty2">수정하기</a>
	         </div>
         </div>
  	</form:form>
  </div>


