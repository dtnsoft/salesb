<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <!-- Latest compiled and minified CSS -->

	<link href="<%= request.getContextPath() %>/css/reset.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">

	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	<script>
	
	  function init(){

	  	opener.selfClose();

	  }
	  
	// 리스트 조회
	 function fcBoard_list(){
			
	        $.ajax({
	            type: "POST",
	               url:  "<%= request.getContextPath() %>/board/comunitylist?groupId=${groupId}",
	                    data:$("#comunityForm").serialize(),
	               success: function(result) {

	                   $("#boardList").html(result);
	                   
	                   $('#fset').focus(1); 
	                   
	               },
	               error:function() {

	               }
	        });

	   	 var secval='3000';//3초단위
		 
		 interval('fcBoard_list()',secval);
	    }
		
	 /*
	 *interval에따른 시간차 함수
	 */
	 function interval(method,secval){

	 	setTimeout(method,secval);
	 	
	 }
	</script>
  </head>
  <body onLoad = "init();">
  <div id="wrap" class="wrap" >
  <!-- 헤더 -->
  <header>
    <div class="mb_top"  id="header">
      <h1 class="head_logo2"><img src="<%= request.getContextPath() %>/images/logo_addys2.png" alt="addys"  /></h1>
    </div>
  </header>
  <!--//헤더 -->
  
  <!-- 대화창 -->
  <div id=boardList></div> 
  <!-- //대화창 --> 
  

</div>
</body>
</html>
<script type="text/javascript">

  fcBoard_list();

</script>