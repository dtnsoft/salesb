<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">

<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://googledrive.com/host/0B-QKv6rUoIcGeHd6VV9JczlHUjg"></script><!-- holder.js link -->

<script src="http://googledrive.com/host/0B-QKv6rUoIcGREtrRTljTlQ3OTg"></script><!-- ie10-viewport-bug-workaround.js -->

<script>
    $(document).ready(function() {
       // $('#myCarousel').carousel('cycle');
        $('#myCarousel2').carousel('cycle');
    });
    
	function goPrivateForm(){

		location.href="<%= request.getContextPath() %>/customerregistform";
		//location.href="<%= request.getContextPath() %>/customerprivateinfo";
		
	}
	
	function goGuest(){
		

	}
	
	function goLogin(){
		
		location.href="<%= request.getContextPath() %>/customerloginform";
		
	}
</script>
</head>
<body>
<div class="container-fuild">
	<div id="myCarousel2" class="carousel slide vertical">
	  <ol class="carousel-indicators">
	    <li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
	    <li data-target="#myCarousel2" data-slide-to="1" class=""></li>
	    <li data-target="#myCarousel2" data-slide-to="2" class=""></li>
	  </ol>
	  <div class="carousel-inner" role="listbox">
	    <div class="item active">
	     <img src="<%= request.getContextPath() %>/images/intro/salesbaron6.jpg" >
	     <!--  <div align=center><embed src=https://www.youtube.com/embed/Fj19L0lL1gM?version=2&autoplay=1&loop=1  width="100%" height="480"></div>  --> 
	      	<div class="carousel-caption">
				<h3>환영합니다.</h3>
				<p>복잡한 쇼핑몰 구축이나 오픈 마켓 입점 없이누구나 무료로 온라인 쇼핑몰의 사장님이 되실 수 있습니다.</p>
				<div class="bnbox">
		         <button type="button" class="bn_salesb" onclick="goLogin()">로그인</button>
		       	</div>
		        <div class="bnbox">
		         <button type="button" class="bn_salesb2" onclick="goPrivateForm()">일반회원가입</button>
		       	</div>
	      </div>      
	    </div>
	    <div class="item">
	      <img src="<%= request.getContextPath() %>/images/intro/salesbaron5.jpg" >
	     <!--   <div align=center><embed src=https://www.youtube.com/embed/zWxkWjXgSN0?version=2&autoplay=1&loop=1  width="100%" height="480"></div> -->   
		    <div class="carousel-caption">
				<h3>환영합니다.</h3>
				<p>복잡한 쇼핑몰 구축이나 오픈 마켓 입점 없이누구나 무료로 온라인 쇼핑몰의 사장님이 되실 수 있습니다.</p>
				<div class="bnbox">
		         <button type="button" class="bn_salesb" onclick="goLogin()">로그인</button>
		       	</div>
		        <div class="bnbox">
		         <button type="button" class="bn_salesb2" onclick="goPrivateForm()">일반회원가입</button>
		       	</div>
		    </div>      
		</div>
	    <div class="item">
	      <img src="<%= request.getContextPath() %>/images/intro/salesbaron4.jpg" >
	      <!-- <img data-src="holder.js/1200x500/text:Third slide">
	      <div align=center><embed src=https://www.youtube.com/embed/QhH1Mdu1_zs?version=2&autoplay=1&loop=1 ></div> --> 
		     <div class="carousel-caption">
				<h3>동영상 사용자메뉴얼</h3>
				<p>사용자 메뉴얼을 재생하시기 바랍니다.</p>
				<div class="bnbox">
		         <button type="button" class="bn_salesb" onclick="goLogin()">로그인</button>
		       	</div>
		        <div class="bnbox">
		         <button type="button" class="bn_salesb2" onclick="goPrivateForm()">일반회원가입</button>
		       	</div>
		    </div>
	  </div>
	</div>
	  <a class="left carousel-control" href="#myCarousel2" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#myCarousel2" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
     <!-- 
		<div style="width:100%; min-height:100px;height:50%; margin-bottom:15px;">
			<video id="video" style="width:100%; min-height:200px;" autobuffer controls>
				<source src="http://m.sanghacheese.co.kr/jm/movie/menu.mp4">
			</video>
			<script>
					var video1 = document.getElementById('video');
					video1.play();
					/*
					video1.addEventListener('click',function(){
					  video1.play();
					},false);
					*/
			</script>
		</div>  
	-->  
</div>
</body>
</html>
<script>


var msg='${ordermessage}';

if(msg!=''){
	
	alert('${ordermessage}');
	
}

</script>