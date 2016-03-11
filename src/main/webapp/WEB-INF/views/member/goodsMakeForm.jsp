<%@ include file="/WEB-INF/views/salesb/top.jsp" %>
<link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>

	function keyTest(){
		
		 
		 var saleurl=document.all('saleurl').innerText;
	
		 if(saleurl!=''){	 location.href=saleurl; }
	
		 
	}

	function kakaoStory(){
		
		//alert('${access_token_kakao}');
		
		var access_token='${access_token_kakao}';

		if(access_token==''){
			
			alert('kakao login 후 포스팅 가능합니다.');
			
		    var loginURL='https://kauth.kakao.com/oauth/authorize';
		    var client_id='${kakaoclient_id}';
		    var redirectURL='${redirectUrl}/salesb/kakaologin';
		    
		    top.location.href=loginURL + '?client_id=' + client_id + '&redirect_uri=' + redirectURL +'&response_type=code';
			
		}else{
			
			var posturl='https://kapi.kakao.com/v1/api/story/post/photo';

		    var image1=$("#image1").val();
		    var image2=$("#image2").val();
		    var image3=$("#image3").val();
		    var image4=$("#image4").val();
		    var image5=$("#image5").val();
		    var content='구매하러 가기=> ${token.token} 상품설명 : '+$("#productEtc").val();
		    
		    var regcnt=0;
		    
		    if(image1!=''){

		    	regcnt++;
		    }
		    
		    if(image2!=''){

		    	regcnt++;
		    }
		    
		    if(image3!=''){

		    	regcnt++;
		    }
		    
		    if(image4!=''){

		    	regcnt++;
		    }
		    
		    if(image5!=''){

		    	regcnt++;
		    }
		    
		    if(content!=''){

		    	//regcnt++;
		    }
		    
		    if(regcnt==0){
		    	
		    	alert('포스팅 내용이 없습니다.\n등록하신 후 포스팅 가능합니다.');
		    	
		    	return;
		    }

	    	$.ajax({
			    type: "POST",
			    async:true,
			     url:  '<%= request.getContextPath() %>/common/restkakaostory?kaostoryurl='+encodeURIComponent(posturl)+'&content='+encodeURIComponent(content)+'&image1='+encodeURIComponent(image1)+'&image2='+encodeURIComponent(image2)+'&image3='+encodeURIComponent(image3)+'&image4='+encodeURIComponent(image4)+'&image5='+encodeURIComponent(image5)+'&access_token='+access_token+'&android_exec_param=cafe_id=1234&ios_exec_param=cafe_id=1234',
				      success: function(data) {
				    	alert('카카오 스토리 포스팅에 성공했습니다.'); 
				      /* 
				      alert('kakao story id : '+data.id);

				      if(data.id!=undefined){
				    	 alert('카카오 스토리 포스팅에 성공했습니다.'); 
				      }else{
				    	 alert('카카오 스토리  포스팅에 실패했습니다.');  
				      }
				      */

			       },
			       error:function(){
			     	  
			     	  alert('error');	          
			
			       }
			 });
			
		}

	    
	}
	
	function facebookpost(){

		var access_token='${access_token_facebook}';
	//	var id='${id}';
	
	//access_token='CAACEdEose0cBABYAy9WykktZAHvCGEi0zDDlHVFrBXTWxpvPE7erZCFtOKcstImA4yKKoXWwqXUpZAK4CvWxT7A7fLSyZBZADCrXusoHc5vxdzYb4fd2QbgLSLW43QDAqUF57VHsQf4ZBDjDBLijrtOF8zfA10yuUD4Ngz8lVIsQsb77ZA0BLldr5rZAWffv080AWmZAhUpDGbbSm0FD4IESn';

	//	id='940756895979731';
	//	access_token='CAACEdEose0cBAPahrtusMz59JfNPxfmKzA2MQ0AkAmZCed0nzQUBkEyDjtrP5X7oLTZBPR6nRVBgGeFloi4Ipc5BiKlGZBj0ux5oZCAZC4TsZCoRBvMwh2NafBSy6viTGt0culGPkVjxOh6VokauulVWZB0xLNrwwWUvh2m4jCP7RJG6BMay2gGkAUrOdWHLaa1YRZAaaF2zbQZDZD';
	
	//	alert(id);
		//alert(access_token);
		
	
		if(access_token==''){
			
			alert('facebook login 후 포스팅 가능합니다.');
			
		    var loginURL='https://www.facebook.com/dialog/oauth';
		    var fbAppId='${facebookfbAppId}';
		    var scope='public_profile';
		    var redirectURL='${redirectUrl}/salesb/facebooklogin';
	
		    top.location.href=loginURL + '?client_id=' + fbAppId + '&redirect_uri=' + redirectURL +'&response_type=token&scope=' + scope;
			
		}else{
			
		    var image1=$("#image1").val();
		    var image2=$("#image2").val();
		    var image3=$("#image3").val();
		    var image4=$("#image4").val();
		    var image5=$("#image5").val();
		    var content='구매하기=> ${token.token} 상품설명 : '+$("#productEtc").val();

			var facebookurl='https://graph.facebook.com/me/feed';
			var message='더 나은 세상을 꿈꾸고 그것을 현실로 만드는 이를 위하여 salsb 플랫폼 서비스를 시작합니다.';
			var picture='http://dev.salesb.net:19003/salesb/images/salesbaron.jpg';
			var link='http://salesb.net';
			var caption='짧은설명,상품이름2';
			var discription='자세한설명 상품요약정도';
			var source='http://dev.salesb.net:19003/salesb/images/salesbaron.jpg';
			
		    var regcnt=0;
		    
		    if(image1!=''){
		    	source=image1;
		    	regcnt++;
		    }
		    
		    if(image2!=''){
		    	source=image2;
		    	regcnt++;
		    }
		    
		    if(image3!=''){
		    	source=image3;
		    	regcnt++;
		    }
		    
		    if(image4!=''){
		    	source=image4;
		    	regcnt++;
		    }
		    
		    if(image5!=''){
		    	source=image5;
		    	regcnt++;
		    }
		    
		    if(content!=''){
		    	message=content;
		    	regcnt++;
		    }
		    
		    if(regcnt==0){
		    	
		    	alert('포스팅 내용이 없습니다.\n등록하신 후 포스팅 가능합니다.');
		    	
		    	return;
		    }
			
	    	$.ajax({
			    type: "POST",
			    async:true,
			     url:  '<%= request.getContextPath() %>/common/facebookpost?facebookurl='+encodeURIComponent(facebookurl)+'&picture='+encodeURIComponent(picture)+'&link='+encodeURIComponent(link)+'&caption='+encodeURIComponent(caption)+'&caption='+encodeURIComponent(caption)+'&source='+encodeURIComponent(source)+'&message='+encodeURIComponent(message)+'&access_token='+access_token,
				      success: function(data) {

				      //alert('facebook id : '+data.id);

				      if(data.id!=undefined){
				    	 alert('페이스북 포스팅에 성공했습니다.'); 
				      }else{
				    	 alert('페이스북 포스팅에 실패했습니다.');  
				      }

			       },
			       error:function(){
			     	  
			     	  alert('error');	          
			
			       }
			 });
			
		}

	    
	}

	function fcGoods_regist(){
	
		var url;
        var frm=tokenFileForm;
	    var fileName = '';
	    var pos = '';
	    var ln = '';
	    var gap = '';
	    var gap1 = '';

	    var tokenkey=$("#tokenkey").val();
	    var image1=$("#image1").val();
	    var image2=$("#image2").val();
	    var image3=$("#image3").val();
	    var image4=$("#image4").val();
	    var image5=$("#image5").val();
	    var productEtc=$("#productEtc").val();

	    var paramString = $("#tokenManageForm").serialize();
        //var paramString = $("#tokenManageForm").serialize()+'&'+$("#tokenFileForm").serialize();
     
	    fileName = '목공.jpg';
		   
        pos = fileName.lastIndexOf("\\");
        ln = fileName.lastIndexOf("\.");
        gap = fileName.substring(pos + 1, ln);
        gap1 = fileName.substring(ln+1);
        
        if(gap1=="jpg" || gap1=="JPG" || gap1=="gif" || gap1=="GIF" || gap1=="png" || gap1=="PNG"){//
        	
            url= "<%= request.getContextPath() %>/member/goodsmake?fileName="+gap+"&extension="+gap1+"&tokenkey="+tokenkey+"&image1="+encodeURIComponent(image1)+"&image2="+encodeURIComponent(image2)+"&image3="+encodeURIComponent(image3)+"&image4="+encodeURIComponent(image4)+"&image5="+encodeURIComponent(image5)+"&productEtc="+encodeURIComponent(productEtc);

            /*
        	$("#tokenFileForm").ajaxSubmit({
        		  url: url,
                  type : "post",
                  data : paramString,
                  dataType : 'text',
                  success: function(data) {
                	 	
   		        	alert(data);
   						
   		          },
   		          error:function(){
   		          
   		          alert('오류!');
   		         
   		          }
             });
            
            */
        	
			/*
            $.ajax({
 		       type: "POST",
 		       async:false,
 		          url:  "<%= request.getContextPath() %>/member/goodsmake?fileName="+gap+"&extension="+gap1,
 		          data:paramString,
 		          success: function(data) {
 	
 		        	alert(data);
 						
 		          },
 		          error:function(){
 		          
 		          alert('오류!');
 		         
 		          }
 		    });
  			*/          
            
        }else {
        	alert("이미지 파일만 등록 부탁드립니다.");
            return;
        }
        
    	if (confirm('상품정보를 등록하시겠습니까?\n등록하시면 토큰값 사용이 가능합니다.')){ 

	        frm.action = url;
	        frm.target="file_result";
	
	        frm.submit();    
    	}   
    
	}
	
	function fileResult(image1,image2,image3,image4,image5){
		
		alert('등록 되었습니다.');
		
		var frm=tokenManageForm;
		frm.image1.value=image1;
		frm.image2.value=image2;
		frm.image3.value=image3;
		frm.image4.value=image4;
		frm.image5.value=image5;
		
		document.all('image1Id').src=image1;
		document.all('image2Id').src=image2;
		document.all('image3Id').src=image3;
		document.all('image4Id').src=image4;
		document.all('image5Id').src=image5;

	}
	
	function getC_FileExtension( filePath ){ 
		   
		var lastIndex = -1; 
	    lastIndex = filePath.lastIndexOf('.'); 
	    var extension = ""; 
	
		if ( lastIndex != -1 ){ 
		    extension = filePath.substring( lastIndex+1, filePath.len ); 
		} else { 
		    extension = ""; 
		} 
		
		return extension; 
	} 

	
	function c_uploadImg_Change( value ){ 
	
	    var src = getC_FileExtension(value); 
	    if (src == "") { 
	       // alert('올바른 파일을 입력하세요'); 
	       // return; 
	    } else if ( !((src.toLowerCase() == "gif") || (src.toLowerCase() == "jpg") || (src.toLowerCase() == "jpeg")) ) { 
	        alert('gif 와 jpg 파일만 지원합니다.'); 
	        return; 
	    } 
	
		//$('#fid').attr("class","");
		//document.all('cfid').innerText='첨부완료';
	
	} 
	
	function copyUrl(url) {
		var IE = (document.all)?true:false;
		var curPage = url;
		 
		if(IE){ //익스면
		if(confirm("이 글의 트랙백 주소를 클립보드에 복사하시겠습니까?"))
		window.clipboardData.setData("Text", curPage);
		}else{ //그 외 브라우저면
		temp = prompt("이 글의 트랙백 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", curPage);
		}

	}
</script>
<iframe id="file_result" name="file_result" style="display: none" ></iframe>
	<div class="container">
	<!-- container -->
		 <!--// 타이틀 --> 
		 <p class="lead">
		    <span class="glyphicon glyphicon-tags"></span>&nbsp;상품페이지 제작
		 </p>
		 <hr>
		 <!--// 타이틀 --> 
		 <div class="well">
           	<h5> <strong><em class="num">1. </em></strong>상품 판매 토근값</h5>
           	    구매하러 가기=> <a href="javascript:keyTest()"><span class="nav_mnu" id="saleurl" style="color:blue">${token.token}</span></a>
           	  <br>
           	  <a href="javascript:copyUrl('${token.token}')">복사하기</a>
         </div> 
		 <form:form commandName="MultipartFileVO"  id="tokenFileForm" name="tokenFileForm" method="post" action="/member/goodsmake" target="file_result"  enctype="multipart/form-data" >
         <div class="well">
           	<h5> <strong><em class="num">2. </em></strong>상품 이미지</h5>
           	    <c:choose>
	                <c:when test="${token.image1!=null && token.image1!=''}">
						 <br>
						 <a href="javascript:AutoResize('${token.image1}')"><img id="image1Id" src="${token.image1}"  width="200" height="200" alt="이미지"></a>
						 <br>
					</c:when>
					<c:otherwise>
						 <br>
						 <img id="image1Id" src=""  width="200" height="200" alt="이미지">
						 <br>
					</c:otherwise>
				</c:choose>
	            1.<input type="file"  id="files" name="files" value="" onChange="c_uploadImg_Change( this.value )" >
                <c:choose>
	                <c:when test="${token.image2!=null && token.image2!=''}">
	                      <br>
						 <a href="javascript:AutoResize('${token.image2}')"><img id="image2Id" src="${token.image2}"  width="200" height="200" alt="이미지"></a>
						 <br>
					</c:when>
					<c:otherwise>
						  <br>
						  <img id="image2Id" src=""  width="200" height="200" alt="이미지">
						  <br>
					</c:otherwise>
				</c:choose>
	            2.<input type="file"  id="files" name="files" value="" onChange="c_uploadImg_Change( this.value )" >
           	    <c:choose>
	                <c:when test="${token.image3!=null && token.image3!=''}">
						 <br>
						 <a href="javascript:AutoResize('${token.image3}')"><img id="image3Id" src="${token.image3}"  width="200" height="200" alt="이미지"></a>
						 <br>
					</c:when>
					<c:otherwise>
						 <br>
						 <img id="image3Id" src=""  width="200" height="200" alt="이미지">
						 <br>
					</c:otherwise>
				</c:choose>
				3.<input type="file"  id="files" name="files" value="" onChange="c_uploadImg_Change( this.value )" >
	            <c:choose>
	                <c:when test="${token.image4!=null && token.image4!=''}">
						 <br>
						 <a href="javascript:AutoResize('${token.image4}')"><img id="image1Id" src="${token.image4}"  width="200" height="200" alt="이미지"></a>
						 <br>
					</c:when>
					<c:otherwise>
						 <br>
						 <img id="image4Id" src=""  width="200" height="200" alt="이미지">
						 <br>
					</c:otherwise>
				</c:choose>
			    4.<input type="file"  id="files" name="files" value="" onChange="c_uploadImg_Change( this.value )" >
           	    <c:choose>
	                <c:when test="${token.image5!=null && token.image5!=''}">
						 <br>
						 <a href="javascript:AutoResize('${token.image5}')"><img id="image5Id" src="${token.image5}"  width="200" height="200" alt="이미지"></a>
						 <br>
					</c:when>
					<c:otherwise>
						 <br>
						 <img id="image5Id" src=""  width="200" height="200" alt="이미지">
						 <br>
					</c:otherwise>
				</c:choose>
	            5.<input type="file"  id="files" name="files" value="" onChange="c_uploadImg_Change( this.value )" >
         </div> 
         </form:form>
         <form:form commandName="tokenVO" id="tokenManageForm"  name="tokenManageForm" method="post" action="" >
         <input type="hidden" name=tokenkey          id="tokenkey"         value="${token.tokenkey}"  />
	     <input type="hidden" name=image1            id="image1"           value="${token.image1}"  />
	     <input type="hidden" name=image2            id="image2"           value="${token.image2}"  />
	     <input type="hidden" name=image3            id="image3"           value="${token.image3}"  />
	     <input type="hidden" name=image4            id="image4"           value="${token.image4}"  />
	     <input type="hidden" name=image5            id="image5"           value="${token.image5}"  />
         <div class="well">
           	<h5> <strong><em class="num">3. </em></strong>상품 설명</h5>
				<textarea style='ime-mode:active;' row="10" class="form-control" id="productEtc" maxlength="200" name="productEtc"  value="" placeholder="상품설명" >${token.productEtc}</textarea>
         </div> 
         </form:form>
         <div class="well">
           	<h5> <strong><em class="num">4. </em></strong>포스팅하기</h5>
           	 <div class="bnbox_kakao">
              <button type="button" class="bn_kakao" onclick="kakaoStory()">kakao story post</button>
            </div>
            <div class="bnbox">
              <button type="button" class="bn_facebook" onclick="facebookpost()">facebook post</button>
            </div>
            <div class="bnbox">
              <button type="button" class="bn_naver" onclick="alert('서비스 준비중입니다.')">Never blog post</button>
            </div>
         </div> 
     	 <div class="clm_detail_btn">
	         <div class="clm_btn">
	           <a href="javascript:fcGoods_regist();" class="btn_ty2">등록하기</a>
	         </div>
         </div>
  </div>


