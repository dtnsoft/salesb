<%@ include file="/WEB-INF/views/salesb/topBusiness.jsp" %>
<script>

	function keyTest(){
		
		 
		 var saleurl=document.all('saleurl').innerText;
	
		 if(saleurl!=''){	 location.href=saleurl; }
	
		 
	}
	
	function orderKeyCreate(){
		
		var tokenemail=goodsTokenConForm.tokenEmail.value;
		var tokenphone=goodsTokenConForm.tokenPhone.value;
		var productkey=goodsTokenConForm.productSelectKey.value;
		var productname=goodsTokenConForm.productSelectName.value;
	
	    if(tokenemail==''){
	    	alert('발급하실 이메일 정보가 없습니다.');
	    	return;
	    }
	    
	    if(tokenphone==''){
	    	alert('발급하실 휴대폰 정보가 없습니다.');
	    	return;
	    }
	    
	    if(productkey==''){
	    	alert('선택된 상품정보가 없습니다.');
	    	return;
	    }
	    
		if (confirm('선택하신 상품정보와 발급대상 정보로 토큰을 요청 하시습니까?')){ 
		
			$.ajax({
			       type: "POST",
			       async:true,
			          url:  '<%= request.getContextPath() %>/order/orderkeycreate?tokenemail='+tokenemail+'&tokenphone='+tokenphone+'&productkey='+productkey+'&productname='+productname,
			         // data:paramString,
			          success: function(data) {
				
			        	 document.all('saleurl').innerText=data;
			        	 alert('요청하신 토큰값이 생성되었습니다.\n생성된 토큰은 발급대상자 정보로 로그인하신후 활성화 가능합니다.');
			        
			          },
			          error:function(){
			        	  
			        	  alert('error');	          
		
			          }
			 });
			
		}
	} 
	
    // 리스트 조회
    function fcGoods_listSearch(curPage){
    
        curPage = (curPage==null) ? 1:curPage;

        goodsTokenConForm.curPage.value = curPage;

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/goodstokenlist",
                    data:$("#goodsTokenConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#goodsTokenList").html(result);
      			   
                   if(goodsTokenConForm.prodId.length!=undefined){
                	   if(goodsTokenConForm.prodId.length>1){
                		   goodsTokenConForm.prodIdRadio[0].checked=true;
                           fcGoods_View(0);
                       }else{
                    	   goodsTokenConForm.prodIdRadio.checked=true;
                           fcGoods_View(0);
                       } 
                   }
                  
               },
               error:function() {
                   commonDim(false);
               }
        });

    }
    
    // 페이지 이동
    function fcGoods_View(index) {

    	var curPage='${productConVO.curPage}';
    	var idx='';
    	
    	 if(goodsTokenConForm.prodId.length!=undefined){
      	   if(goodsTokenConForm.prodId.length>1){ 
      		   goodsTokenConForm.prodIdRadio[index].checked=true;
      		   idx=goodsTokenConForm.prodId[index].value;
             }else{
          	   goodsTokenConForm.prodIdRadio.checked=true;
          	   idx=goodsTokenConForm.prodId.value;
             } 
         }

    	 $.ajax({
             type: "POST",
             url:  "<%= request.getContextPath() %>/business/producttokendetail?idx="+idx+"&curPage="+curPage,
             success: function(result) {
              
                    $("#goodsTokenDetail").html(result);
             },
             error:function(){

             }
         });
    }

</script>
<div class="container">
	<!-- container -->
	<form:form class="form-inline" role="form" commandName="productConVO" id="goodsTokenConForm" name="goodsTokenConForm" method="post" action="" >
		 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;토큰발급(온라인 판매를 위한 토큰발급)
		 </p>
		 <hr>
		 <!--// 타이틀 --> 
		 <div class="well">
           	<h5> <strong><em class="num">1. </em></strong>상품정보</h5>

	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="5"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
			<div class="input-group">
                 <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품명으로 검색하세요">
                 <span class="input-group-btn">
                     <button class="btn btn-default" type="button" onClick="fcGoods_listSearch()">
                         <span class="glyphicon glyphicon-search" ></span>
                 </button>
                 </span>
             </div>
            <p></p>
		    <div id=goodsTokenList></div>
		    <script>
				fcGoods_listSearch();
			</script>
			<div id=goodsTokenDetail></div>
         </div> 
          <div class="well">
           	<h5> <strong><em class="num">2. </em></strong>상품토큰 발급대상</h5>
			<table  class="table table-bordered">
			  <thead></thead>
			  <tbody>
			    <tr>
		          <td  class='text-center' style="background-color:#F5F5F5"><img src="<%= request.getContextPath() %>/images/sp_v2.png" ></td>
		          <td><input type="text" class="form-control" id="tokenEmail" name="tokenEmail"  maxlength="50"  value="" placeholder="123@naver.com"></td>
			    </tr>
			    <tr>
		          <td  class='text-center' style="background-color:#F5F5F5"><img src="<%= request.getContextPath() %>/images/sp_v3.png" ></td>
		          <td><input type="text" class="form-control" id="tokenPhone" name="tokenPhone"  maxlength="50"  value="" placeholder="01012345678"></td>
			    </tr>
			  </tbody>
		    </table>
         </div> 
         <div class="well">
           	<h5> <strong><em class="num">3. </em></strong>토큰요청</h5>
				<p class="tx2">토큰값 : <a href="javascript:keyTest()"><span class="nav_mnu" id="saleurl" style="color:blue"></span></a></p>
         </div> 
		 <div class="clm_detail_btn">
	        <div class="clm_btn">
	          <a href="javascript:orderKeyCreate();" class="btn_ty2">상품토큰 발급</a>
	        </div>
	    </div>
	</form:form>
</div>