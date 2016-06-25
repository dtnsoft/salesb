<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Error</title>

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/animate.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>Error</h1>
        <h3 class="font-bold">Internal Server Error</h3>

        <div class="error-desc">
            The server encountered something unexpected that didn't allow it to complete the request. We apologize.<br/>
            You can go back to main page: <br/><a href="<%= request.getContextPath() %>/intro" class="btn btn-primary m-t">Intro</a>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/jquery-2.1.1.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/bootstrap.min.js"></script>

</body>

</html>
