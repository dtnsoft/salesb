<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
	<link href="<%= request.getContextPath() %>/css/issue_style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	
	<link href="<%= request.getContextPath() %>/css/reset.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">

	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	<script>
	
	  
	</script>
  </head>
  <body>
  <div id="wrap" class="wrap" >
  <!-- 헤더 -->
  <header>
    <div class="mb_top">
      <h1 class="head_logo"><img src="<%= request.getContextPath() %>/images/logo_addys.png" alt="addys"  /></h1>
    </div>
  </header>
  <!--//헤더 -->

<!-- container -->
		  <div id="container">
		    <div class="talk_board" >
		      <table class="tbl_base" >
				<img vertical-align="bottom" height="100%" width="100%" src="<%= request.getContextPath() %>/images/event/event.jpg">
		      </table>
		    </div>
		  </div>
	<!-- //container -->
</div>
</body>
</html>
<script type="text/javascript">

  fcBoard_list();

</script>