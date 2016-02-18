<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
    <!-- Latest compiled JavaScript--> 
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
  </head>

  <body>
  <div class="container">
      <h3><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-plus-sign"></span> SalesBaron Admin Management</font></strong></h3>
      <h1><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-exclamation-sign"></span> Login Fail</font></strong></h1>
      <h5><font style="color:#FF9900">(등록된 사용자가 아닙니다.)</font></h5>   
      <h5><strong><a href="<%= request.getContextPath() %>/admin/loginform" ><font style="color:#428bca"> <span class="glyphicon glyphicon-arrow-left"></span> 뒤로가기</font></a></strong></h5>
  </div>
  </body>
</html>
