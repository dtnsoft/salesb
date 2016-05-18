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
		 
	    var url=window.location.href;
		//alert(url);

	    if (url.indexOf("code=") > 0) {
			queryString = url.substr(url.indexOf('?') + 1);
			//alert('queryString:'+queryString);
			obj = parseQueryString(queryString);

			var code = obj['code'];
			var URL='https://kauth.kakao.com/oauth/token';

		    var client_id='${kakaoclient_id}';
		    var redirectURL='${redirectUrl}/salesb/kakaologin';
	
			var restfullurl=URL + '?grant_type=authorization_code&client_id=' + client_id + '&redirect_uri=' + redirectURL +'&code='+code;
	
			$.ajax({
			    type: "POST",
			    async:true,
			      url:  '<%= request.getContextPath() %>/common/restfullinfo?restfullurl='+encodeURIComponent(restfullurl),
			       success: function(data) {
					
			        //alert('access_token : '+data.access_token);		
					var access_token=data.access_token;
		
					if(access_token!=null){
						
						var URL2='https://kapi.kakao.com/v1/api/story/post/note?content=This cafe is really awesome!&android_exec_param=cafe_id=1234&ios_exec_param=cafe_id=1234';
						var restfullurl3=URL + '?Bearer='+access_token;
						var authorizationToken =access_token;
				
						$.ajax({
						    type: "POST",
						    async:true,
						      url:  '<%= request.getContextPath() %>/common/restfullinfo3?restfullurl3='+encodeURIComponent(URL2)+'&access_token=Bearer '+access_token,
						       success: function(data) {

						    	alert('kakao story id : '+data.id);
								//alert('kakao nickname : '+data.nickname);
								//alert('kakao custom_field1 : '+data.custom_field1);
								
						
						       },
						       error:function(){
						     	  
						     	  alert('error');	          
						
						       }
						 });

					}

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
  <form  id="loginForm" name="loginForm"  method="post" role="form" action="${domainUrl}">
  <input type="hidden" name="loginType" value="sns" >

  <input type="hidden" name="id" value="" >
  <input type="hidden" name="name" value="" >
  <input type="hidden" name="restfulltype" value="kakao" >
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
