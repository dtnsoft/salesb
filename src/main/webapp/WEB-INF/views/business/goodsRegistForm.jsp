<%@ include file="/WEB-INF/views/salesb/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcGoods_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        goodsManageConForm.curPage.value = curPage;

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/business/goodspagelist",
                    data:$("#goodsManageConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#goodsPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }

  //레이어팝업 : 고객수정 Layer 팝업
    function fcCustManage_detailSearch(customerKey){

    	$('#custManageModify').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 400,
            height : 580,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/custmodifyform',{
    				'customerKey' : customerKey
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#custManageModify").dialog('close');

                    });
            }
            ,close:function(){
                $('#custManageModify').empty();
            }
        });
    };
    
    //체크박스 전체선택
    function fcCustManage_checkAll(){
    	$("input:checkbox[id='custCheck']").prop("checked", $("#custCheckAll").is(":checked"));
    }
    
    //고객 삭제
    function fcCustManage_delete(){
    	
    	var checkedCnt = $('input:checkbox[ name="custCheck"]:checked').length;

    	if(checkedCnt <= 0){
        	alert("삭제 대상을 선택해 주세요!");
        	return;
        }
        
        var arrDelCustId = "";
        $('input:checkbox[name="custCheck"]').each(function() {
            if ($(this).is(":checked")) {
            	arrDelCustId += $(this).val() + "^";
            }   
        });
        
        var paramString = $("#custManagePageListForm").serialize() + "&arrDelCustId="+arrDelCustId;

        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/custdeletes",
            data:paramString,
            cache : false,
            success: function(result) {
                commonDim(false);
                if(result=='1'){
					 alert('고객 삭제를 성공했습니다.');
				} else{
					 alert('고객 삭제를 실패했습니다.');
				}
				
				fcCustManage_listSearch();
				
            },
            error:function(error){
                commonDim(false);
                alert('고객 삭제를 실패했습니다.');
            }
        });

    }

</SCRIPT>
<div id="wrap" class="wrap" >
	<div class="container">
	     <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;개별 상품 등록
		 </p>
		 <hr>
	     <!--// 타이틀 --> 
		 <table  class="table table-bordered">
		  <thead>
	
		  </thead>
		  <tbody>
		  	<tr>
	          <td style="background-color:#F5F5F5;width:100">상품명</td>
	          <td><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품명"></td>
		    </tr>
		  	<tr>
	          <td style="background-color:#F5F5F5;width:100">상품코드</td>
	          <td><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="상품코드(미입력시 자동생성)"></td>
		    </tr>
		   	<tr>
	          <td style="background-color:#F5F5F5;width:100">모델명</td>
	          <td><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="모델명"></td>
		    </tr>
		    <tr>
	          <td style="background-color:#F5F5F5;width:100">소비자 가격</td>
	          <td><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="소비자 가격"></td>
		    </tr>
		    <tr>
	          <td style="background-color:#F5F5F5;width:100">원가</td>
	          <td><input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="원가"></td>
		    </tr>
		    <tr>
	          <td style="background-color:#F5F5F5;width:100">상품 이미지</td>
	          <td>
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
	            <br><br>
	          </td>
		    </tr>
		    <tr>
	          <td style="background-color:#F5F5F5;width:100">상품<br>상세설명</td>
	          <td>
	          <textarea style='width:200;ime-mode:active;' row="10" class="form-control" id="counselResult" maxlength="200" name="counselResult"  value="" ></textarea>
	          </td>
		    </tr> 
		  </tbody>
		</table>
		<div class="clm_detail_btn">
	        <div class="clm_btn">
	          <a href="javascript:orderKeyCreate();" class="btn_ty2">등록하기</a>
	        </div>
		 </div>
	</div>
</div>