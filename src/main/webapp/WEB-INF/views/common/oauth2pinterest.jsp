<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
      
<link href="<%= request.getContextPath() %>/css/reset.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/login.css" rel="stylesheet">

<title>Login</title>
<style></style>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/json2.js"></script>
<script src="http://connect.facebook.net/en_US/all.js" language="JavaScript" type="text/javascript" ></script>

	<script>
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	goLogin();
            return false;
        } else{
            return true;
        }
    }
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
	
	function goLogin(){
		
		commonDim(true);
		
		var frm = document.loginForm;
		
		var url='${totalurl}';

	    if (url.indexOf("access_token=") > 0) {
			queryString = url.substr(url.indexOf('?') + 1);
			//alert('queryString:'+queryString);
			obj = parseQueryString(queryString);

			var access_token = obj['access_token'];
			//alert(access_token);
	
			var restfullurl='https://api.pinterest.com/v1/me?access_token='+access_token;
	
			$.ajax({
			    type: "POST",
			    async:true,
			      url:  '<%= request.getContextPath() %>/common/restfullinfo2?restfullurl='+encodeURIComponent(restfullurl),
			       success: function(data) {

					//alert('pinterest id : '+data.id);
					//alert('pinterest name : '+data.first_name);
					
					frm.customerKey.value='01067471995';
					frm.customerPw.value=1;
					
					frm.id.value=data.id;
					frm.name.value=data.first_name;
					frm.access_token.value=access_token;
				
					try {
						document.loginForm.submit();
					} catch(e) {}
			
			       },
			       error:function(){
			     	  
			     	  alert('error');	          
			
			       }
			 });

		} 
	    
	}
	
	</script>
</head>
<body onload="goLogin();">
<div id="wrap" class="wrap"  >
  <!-- 헤더 -->
  <header>
     <div class="mb_top"  id="header">
      <h1 class="head_logo"></h1>
    </div>
  </header>
  <!--//헤더 --> 
  <form  id="loginForm" name="loginForm"  method="post" role="form" action="${domainUrl}">
  <input type="hidden" name="loginType" value="sns" >

  <input type="hidden" name="id" value="" >
  <input type="hidden" name="name" value="" >
  <input type="hidden" name="restfulltype" value="pinterest" >
  <input type="hidden" name="photo" value="" >
  <input type="hidden" name="access_token" value="" >
  
  <input type="hidden" id="groupId" name="groupId" value="BD008">
  <input type="hidden" name="groupName" value="" >
  <input type="hidden" id="customerKey" name="customerKey" value="" >
  <input type="hidden" id="customerPw" name="customerPw" value="" >
  </form>
  
  <div id="footer" class="footer">
    <span class="Copyright">Copyright 2015 ⓒ salesb Corp. All rights reserved. v1.0.0</span>
  </div>
</div>
</body>
</html>
