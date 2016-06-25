<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<script>

	function fcGoods_regist(){
		
		var frm=document.productRegistForm;
		
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

		
		if (confirm('개별 상품정보를 등록 하시겠습니까?')){ 
		
		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/business/productregist",
		           data:$("#productRegistForm").serialize(),
		           success: function(result) {

						if(result>0){
							 alert('개별 상품정보를 등록을 성공했습니다.');
							 fcMenu('<%= request.getContextPath() %>/business/goodsmanage');
						} else{
							 alert('개별 상품정보를 등록에 실패했습니다.');
						}
						
		           },
		           error:function(){
		        	   
		        	   alert('개별 상품정보를 등록에 실패했습니다.');
		        	   
		           }
		    });
		}
	}
	
	function addSet(){
		
		var ofrm=document.optionForm;
		ofrm.addName.value='';
		
	}

	function addOption(){
		var ofrm=document.optionForm;
		var frm=document.productRegistForm;
		
		var addName=$("#addName").val();
		//alert(addName);
		if(addName==''){
			alert('추가 옵션명을 입력하세요');
			return;
		}
		
		var rowCnt = contentId.rows.length;
		var newRow = contentId.insertRow( rowCnt++ );
		newRow.onmouseover=function(){contentId.clickedRowIndex=this.rowIndex};
		var newCell = newRow.insertCell();
		newCell.innerHTML ='<tr><input type="hidden" id="selectOption" name="selectOption" value="'+addName+'"><td>'+addName+'  &nbsp;  &nbsp;<button type="button" class="btn btn-xs btn-info" onClick="delFile(this)" >삭제</button></td> </tr>';
		
	}
	
	function delFile(obj){ 
	    var tr = obj // A 
	             .parentNode // TD 
	             .parentNode; // TR 
	    var table = tr.parentNode; 
	    var index = tr.rowIndex; 
	    table.deleteRow(index-1); 

	}
	
	function addOptionList(){

		var ofrm=document.optionForm;
		var frm=document.productRegistForm;

		if(ofrm.addName.value==''){
			alert('추가 옵션명을 입력하세요');
			return;
		}
		 $.ajax({
	            type: "POST",
	               url:  "<%= request.getContextPath() %>/business/optionlist?optionKey="+frm.optionKey.value+"&optionName="+encodeURIComponent(ofrm.addName.value),
	               success: function(result) {
	                   //commonDim(false);
	                   $("#addOptionList").html(result);
	                   
	                   ofrm.addName.value='';
	                   //$("#modalColse").click();
            
	               },
	               error:function() {
	                 
	               }
	        });		
		 
	}
	

	
	function optionDel(optionId){
		
		var ofrm=document.addValueForm;
		var frm=document.productRegistForm;
		
		 $.ajax({
	            type: "POST",
	               url:  "<%= request.getContextPath() %>/business/optiondel?optionKey="+frm.optionKey.value+"&optionId="+optionId,
	               success: function(result) {
	                   //commonDim(false);
	                   $("#addOptionList").html(result);
	 
	               },
	               error:function() {

	               }
	     });		
	
	}

</script>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>개별 상품 등록</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>상품 등록/변경</a>
            </li>
            <li class="active">
                <strong>개별 상품 등록</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">
    </div>
</div>
        	
<div class="wrapper wrapper-content animated fadeInRight">
  <form:form class="form-horizontal" role="form" commandName="productMasterVO" id="productRegistForm" name="productRegistForm" method="post" action="">  
    <input type="hidden" id="optionKey" name="optionKey" value="N" >             
	<div class="row">
        <div class="col-lg-12">
           <div class="ibox float-e-margins">
              <div class="ibox-title">
                 <h5><em class="num">1. </em></strong>상품 기본정보</h5>
              </div>
              <div class="ibox-content">
                <div class="form-group"><label class="col-sm-2 control-label">상품분류</label>
		            <div class="col-sm-10"><select class="form-control m-b" id="productCategory" name="productCategory" >
                       <option value="01" >카테고리1</option>
                           <option value="02" >카테고리2</option>
                           <option value="03" >카테고리3</option>
                           <option value="04" >카테고리4</option>
                           <option value="05" >카테고리5</option>
                       </select>
                    </div>
                 </div>
                            
                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label">상품명</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="productName" name="productName"  maxlength="50"  value="" placeholder="상품명"></div>
                 </div>
                 
                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >상품코드</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="productCode" name="productCode"  maxlength="10"  value="" placeholder="상품코드(미입력시자동생성)"></div>
                 </div>

                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >모델명</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="modelName" name="modelName"  maxlength="50"  value="" placeholder="모델명"></div>
                 </div>
                 
                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >제조사</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="makeCompany" name="makeCompany"  maxlength="50"  value="" placeholder="제조사"></div>
                 </div>  
                 
                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >공급처</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="suplycompany" name="suplycompany"  maxlength="50"  value="" placeholder="공급처"></div>
                 </div>

                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >원가</label>
                     <div class="col-sm-10"><input type="text" class="form-control"  id="productPrice" name="productPrice"  maxlength="50"  value="0" placeholder="원가"></div>
                 </div>
                 
                <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >소비자가</label>
                     <div class="col-sm-10"><input type="text" class="form-control"  id="salesPrice" name="salesPrice"  maxlength="50"  value="0" placeholder="소비자가격"></div>
                 </div>
                 
                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >1차 구매자 판매 수수료율</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="setteleRate" name="setteleRate"  maxlength="50"  value="0.00" placeholder="1차 구매자 판매 수수료율"></div>
                 </div>                               

                 <div class="hr-line-dashed"></div>
                 <div class="form-group has-success"><label class="col-sm-2 control-label" >재고수량</label>
                     <div class="col-sm-10"><input type="text" class="form-control" id="stockCnt" name="stockCnt"  maxlength="5"  value="0" placeholder="재고수량"></div>
                 </div> 

              </div>
           </div>
         </div>
     </div> 
         
     <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
             <div class="ibox-title">
                    <h5><em class="num">2. </em></strong>배송정보 설정</h5>
                </div>
           <div class="ibox-content"  class="form-horizontal">
                 <form method="get" class="form-horizontal">
              	<div class="form-group"><label class="col-sm-2 control-label">배송옵션</label>

                            <div class="col-sm-10"><select class="form-control m-b" id="deliveryOption" name="deliveryOption" >
                                 <option value="01" >1km 이내 무료</option>
                     <option value="02" >3개이상 무료</option>
                     <option value="03" >1000원 이상 무료</option>
                            </select>
                            </div>
                        </div>                                                        
                 </form>
              </div>
             </div>
         </div>
     </div>
</form:form>

 <div class="row">
     <div class="col-lg-12">
         <div class="ibox float-e-margins">
           <div class="ibox-title">
               <h5><em class="num">3. </em></strong>옵션정보 설정</h5>
           </div>
           <div class="ibox-content"  class="form-horizontal">
                <form method="get" class="form-horizontal"  id="optionForm" name="optionForm" >
           			<div class="form-group"><label class="col-sm-2 control-label">옵션추가</label>
                         <div class="col-sm-7">
                         <input type="text" class="form-control"  id="addName" name="addName"  maxlength="50"  value="" placeholder="옵션명">
                         </div> 
                         <div class="col-sm-3">
                         <button type="button" class="btn btn-primary btn-ms"  onClick="addOptionList()">옵션추가</button>
                         </div>
                     </div>                                                        
                 </form>
            </div>
            <div id=addOptionList></div>
          </div>
       </div>
  </div>

     <div class="row">
        <div class="col-lg-12">
           <div class="ibox float-e-margins">
               <div class="ibox-content">
                  <button type="button" class="btn btn-primary btn-lg" onClick="fcGoods_regist()">등록하기</button>
               </div>
           </div>
        </div>
     </div>                          
</div>

	