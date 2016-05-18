<%@ page language="java" contentType="text/html;charset=euc-kr"%>
<%
    /* ============================================================================== */
    /* =   PAGE : 결제 요청 PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   이 페이지는 Payplus Plug-in을 통해서 결제자가 결제 요청을 하는 페이지    = */
    /* =   입니다. 아래의 ※ 필수, ※ 옵션 부분과 매뉴얼을 참조하셔서 연동을        = */
    /* =   진행하여 주시기 바랍니다.                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
    /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
%>
<%
    /* ============================================================================== */
    /* =   환경 설정 파일 Include                                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ※ 필수                                                                  = */
    /* =   테스트 및 실결제 연동시 site_conf_inc.jsp 파일을 수정하시기 바랍니다.    = */
    /* = -------------------------------------------------------------------------- = */
%>
    <%@ include file="../cfg/site_conf_inc.jsp"%>
<%!
    /* = -------------------------------------------------------------------------- = */
    /* =   환경 설정 파일 Include END                                               = */
    /* ============================================================================== */
%>
<%!
    /* ============================================================================== */
    /* =   null 값을 처리하는 메소드                                                = */
    /* = -------------------------------------------------------------------------- = */
      public String f_get_parm( String val )
        {
        if ( val == null ) val = "";
            return  val;
        }
    /* ============================================================================== */
%>
<%
  request.setCharacterEncoding( "euc-kr" );

    /* kcp와 통신후 kcp 서버에서 전송되는 결제 요청 정보 */
    String req_tx          = f_get_parm( request.getParameter( "req_tx"         ) ); // 요청 종류         
    String res_cd          = f_get_parm( request.getParameter( "res_cd"         ) ); // 응답 코드         
    String tran_cd         = f_get_parm( request.getParameter( "tran_cd"        ) ); // 트랜잭션 코드     
    String ordr_idxx       = f_get_parm( request.getParameter( "ordr_idxx"      ) ); // 쇼핑몰 주문번호   
    String good_name       = f_get_parm( request.getParameter( "good_name"      ) ); // 상품명            
    String good_mny        = f_get_parm( request.getParameter( "good_mny"       ) ); // 결제 총금액       
    String buyr_name       = f_get_parm( request.getParameter( "buyr_name"      ) ); // 주문자명          
    String buyr_tel1       = f_get_parm( request.getParameter( "buyr_tel1"      ) ); // 주문자 전화번호   
    String buyr_tel2       = f_get_parm( request.getParameter( "buyr_tel2"      ) ); // 주문자 핸드폰 번호
    String buyr_mail       = f_get_parm( request.getParameter( "buyr_mail"      ) ); // 주문자 E-mail 주소
    String use_pay_method  = f_get_parm( request.getParameter( "use_pay_method" ) ); // 결제 방법         
    String ipgm_date       = f_get_parm( request.getParameter( "ipgm_date"      ) ); // 가상계좌 마감시간 
	String enc_info        = f_get_parm( request.getParameter( "enc_info"       ) ); // 암호화 정보       
    String enc_data        = f_get_parm( request.getParameter( "enc_data"       ) ); // 암호화 데이터     
    String van_code        = f_get_parm( request.getParameter( "van_code"       ) );
    String cash_yn         = f_get_parm( request.getParameter( "cash_yn"        ) );
    String cash_tr_code    = f_get_parm( request.getParameter( "cash_tr_code"   ) );
    /* 기타 파라메터 추가 부분 - Start - */
    String param_opt_1    = f_get_parm( request.getParameter( "param_opt_1"     ) ); // 기타 파라메터 추가 부분
    String param_opt_2    = f_get_parm( request.getParameter( "param_opt_2"     ) ); // 기타 파라메터 추가 부분
    String param_opt_3    = f_get_parm( request.getParameter( "param_opt_3"     ) ); // 기타 파라메터 추가 부분
    /* 기타 파라메터 추가 부분 - End -   */

  String tablet_size     = "1.0"; // 화면 사이즈 고정
  String url             = request.getRequestURL().toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>*** KCP ***</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<meta http-equiv="Cache-Control" content="No-Cache">
<meta http-equiv="Pragma" content="No-Cache">

<meta name="viewport" content="width=device-width, user-scalable=<%=tablet_size%>, initial-scale=<%=tablet_size%>, maximum-scale=<%=tablet_size%>, minimum-scale=<%=tablet_size%>">

<link href="css/style.css" rel="stylesheet" type="text/css" id="cssLink"/>

<!-- 거래등록 하는 kcp 서버와 통신을 위한 스크립트-->
<script type="text/javascript" src="js/approval_key.js"></script>

<link href="<%= s_host_url %>/salesb/css/reset.css" rel="stylesheet">
<link href="<%= s_host_url %>/salesb/css/common.css" rel="stylesheet">
<link href="<%= s_host_url %>/salesb/css/style.css" rel="stylesheet">
<link href="<%= s_host_url %>/salesb/css/login.css" rel="stylesheet">

<script type="text/javascript">

  var controlCss = "css/style_mobile.css";
  var isMobile = {
    Android: function() {
      return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function() {
      return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function() {
      return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function() {
      return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function() {
      return navigator.userAgent.match(/IEMobile/i);
    },
    any: function() {
      return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
    }
  };

  if( isMobile.any() )
    document.getElementById("cssLink").setAttribute("href", controlCss);
</script>
<script type="text/javascript">
   /* 주문번호 생성 예제 */
  function init_orderid()
  {
    var today = new Date();
    var year  = today.getFullYear();
    var month = today.getMonth() + 1;
    var date  = today.getDate();
    var time  = today.getTime();

    if (parseInt(month) < 10)
      month = "0" + month;

    if (parseInt(date) < 10)
      date  = "0" + date;

    var order_idxx = "TEST" + year + "" + month + "" + date + "" + time;
    var ipgm_date  = year + "" + month + "" + date;

    document.order_info.ordr_idxx.value = order_idxx;
    document.order_info.ipgm_date.value = ipgm_date;
  }

   /* kcp web 결제창 호츨 (변경불가) */
  function call_pay_form()
  {
    var v_frm = document.order_info;

    document.getElementById("sample_wrap").style.display = "none";
    document.getElementById("layer_all").style.display  = "block";

    v_frm.target = "frm_all";
    v_frm.action = PayUrl;

    if (v_frm.Ret_URL.value == "")
    {
	  /* Ret_URL값은 현 페이지의 URL 입니다. */
	  alert("연동시 Ret_URL을 반드시 설정하셔야 됩니다.");
      return false;
    }
    else
    {
      v_frm.submit();
    }
  }

   /* kcp 통신을 통해 받은 암호화 정보 체크 후 결제 요청 (변경불가) */
  function chk_pay()
  {

    self.name = "tar_opener";
    var pay_form = document.pay_form;

    if (pay_form.res_cd.value == "3001" )
    {
      alert("User Cancel!");
      pay_form.res_cd.value = "";
      
      location.href='<%= s_domain_url %>';
      
    }
    else if (pay_form.res_cd.value == "3000" )
    {
      alert("Don't Pay over 300,000 !");
      pay_form.res_cd.value = "";
      
      location.href='<%= s_domain_url %>';
    }

    //document.getElementById("sample_wrap").style.display = "block";
    //document.getElementById("layer_all").style.display  = "none";

    if (pay_form.enc_info.value)
      pay_form.submit();
  }

  function jsf__chk_type()
  {
    if ( document.order_info.ActionResult.value == "card" )
    {
      document.order_info.pay_method.value = "CARD";
    }
    else if ( document.order_info.ActionResult.value == "acnt" )
    {
      document.order_info.pay_method.value = "BANK";
    }
    else if ( document.order_info.ActionResult.value == "vcnt" )
    {
      document.order_info.pay_method.value = "VCNT";
    }
    else if ( document.order_info.ActionResult.value == "mobx" )
    {
      document.order_info.pay_method.value = "MOBX";
    }
    else if ( document.order_info.ActionResult.value == "ocb" )
    {
      document.order_info.pay_method.value = "TPNT";
      document.order_info.van_code.value = "SCSK";
    }
    else if ( document.order_info.ActionResult.value == "tpnt" )
    {
      document.order_info.pay_method.value = "TPNT";
      document.order_info.van_code.value = "SCWB";
    }
    else if ( document.order_info.ActionResult.value == "scbl" )
    {
      document.order_info.pay_method.value = "GIFT";
      document.order_info.van_code.value = "SCBL";
    }
    else if ( document.order_info.ActionResult.value == "sccl" )
    {
      document.order_info.pay_method.value = "GIFT";
      document.order_info.van_code.value = "SCCL";
    }
    else if ( document.order_info.ActionResult.value == "schm" )
    {
      document.order_info.pay_method.value = "GIFT";
      document.order_info.van_code.value = "SCHM";
    }
  }
</script>
</head>

<body onload="chk_pay();">
<div id="wrap" class="wrap"  >
  <!-- container -->
  <div id="container">
 	<div class="dimbackground-curtain commonDimedClass" id="dimedLoadingDiv"></div>
 	<image src="/salesb/images/common/loading.gif" class="commonDimedClass" id="dimedLoadingImg"/>
  </div>
  <div id="footer" class="footer">
    <span class="Copyright">Copyright 2015 salesb Corp. All rights reserved. v1.0.0</span>
  </div>
</div>
<form name="pay_form" method="post" action="<%= request.getContextPath() %>/kcp/mobile_sample/pp_cli_hub.jsp">
    <input type="hidden" name="req_tx"         value="<%=req_tx%>">               <!-- 요청 구분          -->
    <input type="hidden" name="res_cd"         value="<%=res_cd%>">               <!-- 결과 코드          -->
    <input type="hidden" name="tran_cd"        value="<%=tran_cd%>">              <!-- 트랜잭션 코드      -->
    <input type="hidden" name="ordr_idxx"      value="<%=ordr_idxx%>">            <!-- 주문번호           -->
    <input type="hidden" name="good_mny"       value="<%=good_mny%>">             <!-- 휴대폰 결제금액    -->
    <input type="hidden" name="good_name"      value="<%=good_name%>">            <!-- 상품명             -->
    <input type="hidden" name="buyr_name"      value="<%=buyr_name%>">            <!-- 주문자명           -->
    <input type="hidden" name="buyr_tel1"      value="<%=buyr_tel1%>">            <!-- 주문자 전화번호    -->
    <input type="hidden" name="buyr_tel2"      value="<%=buyr_tel2%>">            <!-- 주문자 휴대폰번호  -->
    <input type="hidden" name="buyr_mail"      value="<%=buyr_mail%>">            <!-- 주문자 E-mail      -->
    <input type="hidden" name="cash_yn"		   value="<%=cash_yn%>">              <!-- 현금영수증 등록여부-->
    <input type="hidden" name="enc_info"       value="<%=enc_info%>">
    <input type="hidden" name="enc_data"       value="<%=enc_data%>">
    <input type="hidden" name="use_pay_method" value="<%=use_pay_method%>">
    <input type="hidden" name="cash_tr_code"   value="<%=cash_tr_code%>">
    <!-- 추가 파라미터 -->
    <input type="hidden" name="param_opt_1"	   value="<%=param_opt_1%>">
    <input type="hidden" name="param_opt_2"	   value="<%=param_opt_2%>">
    <input type="hidden" name="param_opt_3"	   value="<%=param_opt_3%>">
</form>
</body>
</html>
