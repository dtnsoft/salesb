<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Payapp Sample</title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

	   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
</head>


<body>

	
<div class="container">

<div class="page-header">
  <h1>Payapp 결제 요청 결과</h1>
</div>

<c:choose>
	<c:when test="${errorMsg eq ''}">
	
	<div class="alert alert-success" role="alert"><p><strong>요청 성공</strong> 페이앱에 정상적으로 요청되었습니다.</p></div>
	
	<a href = "${result.payUrl }" class = "btn btn-primary btn-lg">페이앱 결제창 열기</a>
	
	</c:when>
	<c:otherwise>
		
	<div class="alert alert-danger">
		<p><strong>에러</strong> ${errorMsg }</p>
	</div>
	</c:otherwise>
</c:choose>

</div>

</body>