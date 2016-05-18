<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Salesb | Login Fail</title>

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/animate.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>Login Fail!</h1>
        <h3 class="font-bold">죄송합니다. 로그인에 실패했습니다.</h3>

        <div class="error-desc">
            등록된 아이디 가 아닌지<br/>
            또는 패스워드를 확인 하시기 바랍니다 <br/><a href="<%= request.getContextPath() %>/customerloginform" class="btn btn-primary m-t">뒤로가기</a>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="<%= request.getContextPath() %>/Static_Full_Versionjs/jquery-2.1.1.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Versionjs/bootstrap.min.js"></script>

</body>

</html>
