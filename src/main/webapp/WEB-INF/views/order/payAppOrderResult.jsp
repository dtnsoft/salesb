<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<!DOCTYPE html>
<html>
  <head> 
    <title>payapp</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/favicon.ico" type='image/ico'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS-->
	<link href="<%= request.getContextPath() %>/css/reset.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/comunity.css" rel="stylesheet">

	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
	<script>

	    function parseQueryString(queryString) {
	        var qs = decodeURIComponent(queryString),
	            obj = {},
	            params = qs.split('&');
	        params.forEach(function (param) {
	            var splitter = param.split('=');
	            obj[splitter[0]] = splitter[1];
	        });
	        return obj;
	    }
	
	</script>
<body>
<form:form class="form-inline" role="form"  id="orderResultForm" name="orderResultForm" method="post" action="" >
 		<!-- 헤더 -->
		  <header>
		   <div class="mb_top">
				<h1 class="head_logo"></h1>
			</div>
		  </header>
		<!--//헤더 -->  
		<!-- container -->
		<div id="container" class="comunity clm_order_detail" >
	    <div id="m_content form" >
	      <div class="clm_order_detail">
	      <!-- 타이틀 -->
        <div class="clm_acdo_tit">
          <h1>Paypal 주문결과</h1>   
        </div>
        <!--// 타이틀 --> 
        <!-- 1.접수정보 -->
        <div class="clm_acdo_tit2">
          <h2 class="h2_txo"> <strong><em class="num">1. </em></strong>주문결과</h2>
        </div>
        <div class="clm_acdo_sec">
          <dl class="clm_ip2">
            <dt><span class="tit">결과</span></dt>
            <dd>
              <p class="tx1" id="result"></p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">주문금액</span></dt>
            <dd>
              <p class="tx1" id="amt"></p>
            </dd>
          </dl>
        </div>
        <!--// 1. 접수정보 --> 
        <!-- 2.배송정보 -->
        <div class="clm_acdo_tit2">
          <h2 class="h2_txo"> <strong><em class="date">2.</em></strong>&nbsp;주문자 정보</h2>
        </div>
        <div class="clm_acdo_sec">
          <dl class="clm_ip2">
            <dt><span class="tit">주문자명</span></dt>
            <dd>
              <p class="tx1" id="first_name"></p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">주소코드</span></dt>
            <dd>
              <p class="tx1" id="address_country_code"></p>
            </dd>
          </dl>
        </div>
       </div>
      </div>
  </div>
  <!-- //container -->
  </form:form>
</body>
</html>
<script>

 

</script>