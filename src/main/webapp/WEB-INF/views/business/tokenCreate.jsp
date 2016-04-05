<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
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
		   <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>토큰발급</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a>토큰발급</a>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        	</div>
        	
        	<div class="wrapper wrapper-content animated fadeInRight">
           		
	            <form:form class="form-horizontal" role="form" commandName="productConVO" id="goodsTokenConForm" name="goodsTokenConForm" method="post" action="" >
		        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	            <input type="hidden" name="rowCount"            id="rowCount"           value="5"/>
	            <input type="hidden" name="totalCount"          id="totalCount"         value=""  />

	                
	            <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
	                
			                <div class="ibox-content">
		                    		<div class="form-group"><label class="col-sm-2 control-label">상품검색</label>
		                               <div class="col-sm-10">
		                                   <div class="input-group"><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품명으로 검색하세요"> <span class="input-group-btn"> 
		                                   <button type="button" class="btn btn-primary" onClick="javascript:fcGoods_listSearch()" >Go!
		                                   </button> </span></div>
		                               </div>
		                    		 </div>
		                    </div>
	                    </div>
	                </div>
	            </div>         
             
	            <div id=goodsTokenList></div>
			    <script>
					fcGoods_listSearch();
				</script>
				<div id=goodsTokenDetail></div>
				
	
	            <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
	                    <div class="ibox-title">
                            <h5>상품 토큰발급 대상<small>2</small></h5>
                        </div>
			                <div class="ibox-content">

                                <div class="form-group has-success"><label class="col-sm-2 control-label">이메일</label>
                                    <div class="col-sm-10"><input type="text" class="form-control" id="tokenEmail" name="tokenEmail" maxlength="50"  value="" placeholder="123@naver.com"></div>
                                </div>
                                
                                <div class="hr-line-dashed"></div>
                                <div class="form-group has-success"><label class="col-sm-2 control-label">휴대폰</label>
                                    <div class="col-sm-10"><input type="text" class="form-control" id="tokenPhone" name="tokenPhone"  maxlength="50"  value="" placeholder="01012345678"></div>
                                </div>

		                    </div>
	                    </div>
	                </div>
	            </div> 
	            
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
	                    <div class="ibox-title">
                            <h5>토큰요청<small>3</small></h5>
                        </div>
			                <div class="ibox-content">

                                <div class="form-group has-success"><label class="col-sm-2 control-label">토큰값</label>
                                    <div class="col-sm-10"> <a href="javascript:keyTest()"><span class="nav_mnu" id="saleurl" style="color:blue"></span></a></div>
                                </div>

		                    </div>
	                    </div>
	                </div>
	            </div> 
	            
	            <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
			                <div class="ibox-content">
		                         <button type="button" class="btn btn-primary btn-lg" onClick="orderKeyCreate()">상품 토큰발급</button>
		                    </div>
	                    </div>
	                </div>
	            </div>       
			   </form:form>
	       </div>

