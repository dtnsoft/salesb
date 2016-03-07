<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%!
    /* ============================================================================== */
    /* =   null 값을 처리하는 메소드                                                   = */
    /* = -------------------------------------------------------------------------- = */
      public String f_get_parm( String val )
        {
        if ( val == null ) val = "";
            return  val;
        }
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   login 처리 결과값 receive                                                 = */
    /* = -------------------------------------------------------------------------- = */
	  
	    request.setCharacterEncoding( "utf-8" );

		String loginType          = f_get_parm( request.getParameter( "loginType"         ) ); // loginType   
		
		String id          = f_get_parm( request.getParameter( "id"         ) ); // id         
		String name         = f_get_parm( request.getParameter( "name"        ) ); // name
		String photo         = f_get_parm( request.getParameter( "photo"        ) ); // name
		String restfulltype         = f_get_parm( request.getParameter( "restfulltype"        ) ); // restfulltype
		String access_token         = f_get_parm( request.getParameter( "access_token"        ) ); // access_token

		String customerKey         = f_get_parm( request.getParameter( "customerKey"        ) ); // customerKey
		String customerPw         = f_get_parm( request.getParameter( "customerPw"        ) ); // customerPw
		String groupId         = f_get_parm( request.getParameter( "groupId"        ) ); // groupId
		String groupName         = f_get_parm( request.getParameter( "groupName"        ) ); // groupName

		String url             = request.getRequestURL().toString();

	/* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   request 구분값에 따른 분기처리로직                                           = */
    /* = -------------------------------------------------------------------------- = */

		String serverName = request.getServerName();
		String serverQueryString = request.getQueryString();

		String[] querygb=null;
		String port="19003";
		String context="sales/";
		String suburl="";
		String key="";
		String login="";

		String ogTitle="";
		String ogDescription="";

		if(!"sns".equals(loginType)){ //sales url 처리( root or shrt url)

			if (serverQueryString == null || serverQueryString.equals("")){ //root context
				
				suburl="";
				
				ogTitle="Sales Baron";
		        ogDescription="Go to Login!";

			}else{ //단축제공 url
				
				querygb=serverQueryString.split("=");

				if(querygb.length==2){
					
					if("key".equals(querygb[0])){
						key=querygb[1];
						suburl="orderintro?key="+key;

						ogTitle="Sales Baron";
		                ogDescription="Go to Sales!";
					}

				}

			}

		}
	/* ============================================================================== */				
%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<%
	if("salesb.net".equals(serverName) || "www.salesb.net".equals(serverName)) {
%>
    <meta property="og:type" content="website">
    <meta property="og:title" content="<%=ogTitle%>">
    <meta property="og:description" content="<%=ogDescription%>">
    <meta property="og:image" content="http://dev.salesb.net:<%=port%>/salesb/images/salesbaron.jpg">
	<meta property="og:image:width" content="450">
    <meta property="og:image:height" content="900">
	
    <TITLE>sales baron</TITLE>
    <link rel="shortcut icon" href="http://dev.salesb.net:<%=port%>/salesb/images/favicon.ico" type='image/ico'>
	<script>

	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

	  ga('create', 'UA-73697440-1', 'auto');
	  ga('send', 'pageview');

	</script>
<%
	}else{
%>
	<TITLE>offact</TITLE>
    <link rel="shortcut icon" href="http://dev.salesb.net:<%=port%>/offact/images/favicon.ico" type='image/ico'>
<%
	}
%>
	<script>

      //alert('<%=serverQueryString%>');
	  //alert('http://dev.salesb.net:<%=port%>/<%=context%><%=suburl%>');

	  var domArr=document.domain.split(".");
	  var chkdomain=domArr[0];
	  var salsebchk=domArr[0]+domArr[1];

	  if('wwwoffact'==salsebchk ){//www.offact.com 처리
		location.href='http://www.offact.com/offact/';
	  }

	  </script>

<%
	if("salesb.net".equals(serverName) || "www.salesb.net".equals(serverName)) {

		if(!"sns".equals(loginType)){ //sales 일반 url 처리
%>
</HEAD>
	 <frameset rows="*,0" frameborder="no" border="0" framespacing="0">
		<frame src="http://dev.salesb.net:<%=port%>/salesb/<%=suburl%>" name="mainFrame" id="mainFrame" />
	</frameset>
</HTML> 
<%

		}else{ //sns 로그인 처리후 데이타 전달 (POST방식으로 변경)

			suburl="customer/snslogin?groupId="+groupId+"&groupName="+groupName+"&memberType=02&sbPhoneNumber=01067471995&sbPw=1&customerKey="+customerKey+"&customerPw="+customerPw+"&id="+id+"&name="+name+"&photo="+photo+"&restfulltype="+restfulltype+"&access_token="+access_token; //sns url

%>

</HEAD>
	 <frameset rows="*,0" frameborder="no" border="0" framespacing="0">
		<frame src="http://dev.salesb.net:<%=port%>/salesb/<%=suburl%>" name="mainFrame" id="mainFrame" />
	</frameset>
</HTML>

<%
		}
	}else{//pms 처리
%>
</HEAD>
	 <frameset rows="*,0" frameborder="no" border="0" framespacing="0">
		<frame src="/pms" name="mainFrame" id="mainFrame" />
	</frameset>
</HTML> 
<%
	}
%>