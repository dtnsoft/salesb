<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
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
<form:form class="form-inline" role="form" commandName="asConVO" id="asConForm" name="asConForm" method="post" action="" >
 </form:form>
<!-- container -->
  <div id="container" class="comunity" >
    <div id="m_content" >
      <div class="clm_order_detail">
        <!-- 타이틀 -->
        <div class="clm_acdo_tit">
          <h1>주문키 생성</h1>
        </div>
        <!--// 타이틀 --> 
        <!-- 1.주문정보 -->
        <div class="clm_acdo_tit2">
          <h2 class="h2_txo"> <strong><em class="num">1. </em></strong>주문키정보</h2>
          <a href="javascript:void(0)" class="tit_desc"> <span class="sp_odr ic_arr">&nbsp;</span> </a>
        </div>
        <div class="clm_acdo_sec">
          <dl class="clm_ip2">
            <dt><span class="tit">주문자</span></dt>
            <dd>
              <p class="tx1">${customerKey}</p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">상품정보</span></dt>
            <dd>
              <p class="tx2">아이피타임 무선 랜카드 N150UA</p>
            </dd>
          </dl>	         
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">주문URL</span></dt>
            <dd>
              <p class="tx2"><a href="javascript:keyTest()"><span class="nav_mnu" id="saleurl" style="color:blue"></span></a></p>
            </dd>
          </dl>
        </div>
        <!--// 1. 주문정보 --> 
        <!-- 2.주문키요청 -->
        <div class="clm_acdo_tit2">
          <h2 class="h2_txo"> <strong><em class="date">2.</em></strong>&nbsp;주문키 요청</h2>
          <a href="javascript:void(0)" class="tit_desc"> <span class="sp_odr ic_arr">&nbsp;</span> </a>
        </div>
        <!--// 2.주문키정보 -->
      	<div class="clm_detail_btn">
	        <div class="clm_btn">
	          <a href="javascript:orderKeyCreate();" class="btn_ty2">주문키 요청</a>
	        </div>
	    </div>
    </div>
  </div>
  <!-- //container -->