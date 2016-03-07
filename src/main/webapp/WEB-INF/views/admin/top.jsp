<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<%
    //session 정보
	String strUserId= (String)session.getAttribute("strUserId");
	String strUserName= (String)session.getAttribute("strUserName");
	String strGroupId= (String)session.getAttribute("strGroupId");	
	String strGroupName= (String)session.getAttribute("strGroupName");
	String strAuthId= (String)session.getAttribute("strAuthId");
	String strAuthName= (String)session.getAttribute("strAuthName");	
	String strExcelAuth= (String)session.getAttribute("strExcelAuth");
	String strOfficePhone= (String)session.getAttribute("strOfficePhone");
	String strOfficePhoneFormat= (String)session.getAttribute("strOfficePhoneFormat");
	String strMobliePhone= (String)session.getAttribute("strMobliePhone");
	String strMobliePhoneFormat= (String)session.getAttribute("strMobliePhoneFormat");
	String strEmail= (String)session.getAttribute("strEmail");
	String strIp= (String)session.getAttribute("strIp");
	String strAuth= (String)session.getAttribute("strAuth");
	String pwdChangeDateTime= ((String)session.getAttribute("pwdChangeDateTime")).replaceAll("-", "");
	String pwCycleDate= ((String)session.getAttribute("pwCycleDate")).replaceAll("-", "");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>salesb</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">
	
	<!-- Latest compiled JavaScript--> 
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

	<!-- lastest ver -->
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>-->
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/locales/bootstrap-datetimepicker.kr.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/errorMsg.js"></script>

	<style>
	/* paging */
	.paging {width:100%;margin:10px 0 0;text-align:center;}
	.paging a, .paging strong {padding:4px 4px 1px;vertical-align:bottom;}
	.paging strong {color:#f15a22;}
	.paging .btn {display:inline-block;padding:0;width:21px;height:20px;background:url(../images/common/btn.gif) no-repeat;}
	.paging .btn_first {background:url(../images/main/btn_pre02.gif)}
	.paging .btn_prev {background:url(../images/main/btn_pre01.gif)}
	.paging .btn_next {background:url(../images/main/btn_next01.gif)}
	.paging .btn_last {background:url(../images/main/btn_next02.gif)}

	/* 서브타이틀 영역 (.sub_title) */
	.sub_title { margin-bottom:20px; width:100%; height:43px; border-bottom:1px solid #ccc; }
	.sub_title .titleP { position:relative; left:0; float:left; margin-left:-7px; padding-left:31px; _padding-top:3px; height:28px; _height:25px; background:url(../images/sub/icon_title.gif) no-repeat; font-family:Malgun Gothic, "맑은 고딕", "돋움", dotum, verdana, Tahoma, sans-serif; font-size:16px; color:#2c3546; font-weight:bold; letter-spacing:-1px; }
	.seachNm { float:left; margin:1px 0 0 20px; padding:4px 0 0 20px; padding-top:5px\9; height:16px; height:15px\9; border:1px solid #cbcbcb; background:#eef0f4; }
	.sub_title .seachNm li { display:inline; padding:0 15px 0 8px; background:url(../images/sub/icon_01.gif) no-repeat 0 2px; }

	/* 공통부분  */

	body { margin:0; padding:0; } 
		
	div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,form,fieldset,p,button{margin:0;padding:0;}
	body,h1,h2,h3,h4,th,td,input, select, textarea {color:#666; font-family:"돋움", dotum, verdana, Tahoma, sans-serif; font-size:11px; font-weight:normal; padding:0px; margin:0px}
	html { scrollbar-arrow-color: #999; scrollbar-3dlight-color: #e6e6e6; scrollbar-darkshadow-color: #e9e9e9; scrollbar-face-color: #f4f4f0; scrollbar-highlight-color:#FFFFFF; scrollbar-shadow-color: #d0d0d0; scrollbar-track-color: #F2F2F2; }
	hr{display:none;}
	img,fieldset,iframe {border:0;}
	ul,ol,li{list-style:none; margin:0; padding:0;}
	img,input,select,textarea{
		vertical-align:text-top;
	}
	input, label { vertical-align:middle;}
	caption, legend { display: none; }
	em, address { margin:0; padding:0; font-style:normal }
	.vm{vertical-align:middle !important;}
	.bn{border:none !important}
	.chk,.rdo{widows:13px;height:13px;margin:0 !important;padding:0 !important;vertical-align:middle}
	.chk_label{position:relative;top:1px;*top:2px;left:0px}
	.blind{overflow:hidden;position:absolute;visibility:hidden;width:0;height:0;font-size:0;line-height:0}

	/* 2차 버전 - 20130801 */
	/* gnb 속성 */
	#gnb_wrap { width:100%; min-width:1000px; height:174px; background:url(../images/top/bg_top.gif) repeat-x; }
	#gnb_wrapL { position:relative; width:100%; min-width:1000px; height:174px; background:url(../images/top/bg_topleft.gif) no-repeat;  z-index:200;}
	#gnb_logo {
		margin-left:20px;
		width:352px;
		height:119px;
		background:url(../images/top/bg_logo.jpg) no-repeat left bottom;
		text-align:left;
	}
	#gnb_logo h1 { margin:0 auto; padding-top:20px; width:212px; height:68px; }
	#top_set { position:absolute; right:18px; top:0; padding:2px 3px 0 0; height:28px; line-height:28px; color:#ffffff; letter-spacing:-0.5px; background:; }
	#top_set a { color:#3b3b3b; }
	#top_set ul { float:right; }
	#top_set li { float:left; padding-left:7px; }
	#top_set .bar { padding-right:9px; background:url(../images/top/bar_top_set.gif) no-repeat right top; }
	#top_set .addr { padding-left:15px; background:url(../images/top/icon_topaddr.gif) no-repeat 1px 0; }
	#top_set li strong { color:#fff000; font-weight:normal; letter-spacing:0; }
	#top_set li alarm { color:red; font-weight:bold; letter-spacing:0; }
	#gnb_btn { position:absolute; right:18px; top:30px; }
	#gnb_btn li { float:left; padding-right:2px; }
	#gnb_inform { margin-left:70px; width:252px; height:24px; text-align:center; background: url(../images/top/inform.gif) no-repeat; color:#fff; }
	#gnb_inform li { display:inline; line-height:26px; }
	#gnb_inform li.bar { padding-right:7px; background: url(../images/top/bar_gnb_inform.gif) no-repeat right top; }
	#gnb_menu_wrap { position:absolute; right:20px; top:67px; *top:71px; _top:72px; z-index:100; }
	#gnb_menu_wrap .dep1 { position:relative; float:left; width:100px; }
	#gnb_menu_wrap .sub_menu { *margin-top:1px; _margin-top:0; padding:18px 0 10px; width:118px; border-left:1px solid #fff; border-bottom:1px solid #fff; border-right:1px solid #fff; background:#4988bd url(../images/top/bg_sub_menu.gif) no-repeat 50% 3px; text-align:left; z-index:1000; }
	#gnb_menu_wrap .sub_menu li { display:block; margin:1px 0; }
	#gnb_menu_wrap .sub_menu a { display:block; padding:3px 10px 1px 17px; line-height:16px; border-top:1px solid #4988bd; border-bottom:1px solid #4988bd; background:url(../images/top/icon_sub_menu.gif) no-repeat 10px 8px; color:#fff !important; }
	#gnb_menu_wrap .sub_menu a:hover, #gnb_menu_wrap .sub_menu a:active { border-top:1px solid #007eb5; border-bottom:1px solid #007eb5; background-color:#00a8f2; color:#fff !important; }

	
	</style>

	<script type="text/javascript">
	
	//logout 처리
		var goLogout =  function() {
	
			//alert('logout');
			
			$('#logoutForm').attr({action:"<%= request.getContextPath() %>/admin/logout"});
			
			try {
				logoutForm.submit();
			} catch(e) {}
		};
		
		//레이어팝업 : 사용자수정 Layer 팝업
	    function goMyInfo(userId){

	    	$('#passwordModify').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

	            width : 400,
	            height : 415,
	            modal : true, //주위를 어둡게

	            open:function(){
	                //팝업 가져올 url
	                $(this).load('<%= request.getContextPath() %>/admin/usermodifyform',{
	    				'userId' : userId
	    			});
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#passwordModify").dialog('close');

	                    });
	            }
	            ,close:function(){
	                $('#passwordModify').empty();
	            }
	        });

	    };
	
	</script>
    <c:set var="strUserId" value="<%=strUserId %>" />
	<c:set var="strUserName" value="<%=strUserName %>" />
	<c:set var="strGroupId" value="<%=strGroupId %>" />
	<c:set var="strGroupName" value="<%=strGroupName %>" />
	<c:set var="strAuthId" value="<%=strAuthId %>" />
	<c:set var="strAuthName" value="<%=strAuthName %>" />
	<c:set var="strExcelAuth" value="<%=strExcelAuth %>" />
	<c:set var="strAuth" value="<%=strAuth %>" />
</head>
<body id="gnb" name = "CommTop" onunload="browser_Event()" onload="MM_preloadImages('<%= request.getContextPath() %>/images/top/menu_01_on.jpg','<%= request.getContextPath() %>/images/top/menu_01_on.jpg')">
  <form method="post" id="logoutForm" name="logoutForm"  role="form" >
  </form>
<!-- 레이아웃 시작 -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header" >
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Sales Baron</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">관리<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%= request.getContextPath() %>/manage/usermanage">사업자 회원관리</a></li>
            <li><a href="<%= request.getContextPath() %>/manage/custmanage">일반 회원관리</a></li>
           <!-- <li><a href="<%= request.getContextPath() %>/manage/companymanage">업체 관리</a></li>
            <li><a href="<%= request.getContextPath() %>/master/productmanage">품목 관리</a></li> -->
          </ul>
        </li>
      </ul>
      <!-- 
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">이력<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%= request.getContextPath() %>/history/smshistorymanage">SMS 전송이력</a></li>
            <li><a href="<%= request.getContextPath() %>/history/workhistorymanage">업무 처리이력</a></li>
          </ul>
        </li>
      </ul> -->
	  <ul class="nav navbar-nav navbar-right">
        <li><a href="#">사용자 : <strong> <%=strUserName %></strong></a></li>
        <li><a href="javascript:goMyInfo('<%=strUserId %>');">비밀번호 변경</a></li>
		<li><a href="javascript:goLogout();">Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<!-- 레이아웃 끝 -->
  <!-- 사용자 수정-->
  <div id="passwordModify"  title="비밀번호 수정"></div>
</body>
</html>
<script>
//alert('${strUserId}');
if('${strUserId}'==null || '${strUserId}'=='null' ){
	goLogout();
}

if('<%=pwCycleDate %>'>'<%=pwdChangeDateTime %>'){
	goMyInfo('<%=strUserId %>');
}


</script>