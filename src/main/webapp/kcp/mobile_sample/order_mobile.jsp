<%@ page language="java" contentType="text/html;charset=euc-kr"%>
<%
    /* ============================================================================== */
    /* =   PAGE : ���� ��û PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �������� Payplus Plug-in�� ���ؼ� �����ڰ� ���� ��û�� �ϴ� ������    = */
    /* =   �Դϴ�. �Ʒ��� �� �ʼ�, �� �ɼ� �κа� �Ŵ����� �����ϼż� ������        = */
    /* =   �����Ͽ� �ֽñ� �ٶ��ϴ�.                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ������ ������ �߻��ϴ� ��� �Ʒ��� �ּҷ� �����ϼż� Ȯ���Ͻñ� �ٶ��ϴ�.= */
    /* =   ���� �ּ� : http://kcp.co.kr/technique.requestcode.do                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
%>
<%
    /* ============================================================================== */
    /* =   ȯ�� ���� ���� Include                                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �ʼ�                                                                  = */
    /* =   �׽�Ʈ �� �ǰ��� ������ site_conf_inc.jsp ������ �����Ͻñ� �ٶ��ϴ�.    = */
    /* = -------------------------------------------------------------------------- = */
%>
    <%@ include file="../cfg/site_conf_inc.jsp"%>
<%!
    /* = -------------------------------------------------------------------------- = */
    /* =   ȯ�� ���� ���� Include END                                               = */
    /* ============================================================================== */
%>
<%!
    /* ============================================================================== */
    /* =   null ���� ó���ϴ� �޼ҵ�                                                = */
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

    /* kcp�� ����� kcp �������� ���۵Ǵ� ���� ��û ���� */
    String req_tx          = f_get_parm( request.getParameter( "req_tx"         ) ); // ��û ����         
    String res_cd          = f_get_parm( request.getParameter( "res_cd"         ) ); // ���� �ڵ�         
    String tran_cd         = f_get_parm( request.getParameter( "tran_cd"        ) ); // Ʈ����� �ڵ�     
    String ordr_idxx       = f_get_parm( request.getParameter( "ordr_idxx"      ) ); // ���θ� �ֹ���ȣ   
    String good_name       = f_get_parm( request.getParameter( "good_name"      ) ); // ��ǰ��            
    String good_mny        = f_get_parm( request.getParameter( "good_mny"       ) ); // ���� �ѱݾ�       
    String buyr_name       = f_get_parm( request.getParameter( "buyr_name"      ) ); // �ֹ��ڸ�          
    String buyr_tel1       = f_get_parm( request.getParameter( "buyr_tel1"      ) ); // �ֹ��� ��ȭ��ȣ   
    String buyr_tel2       = f_get_parm( request.getParameter( "buyr_tel2"      ) ); // �ֹ��� �ڵ��� ��ȣ
    String buyr_mail       = f_get_parm( request.getParameter( "buyr_mail"      ) ); // �ֹ��� E-mail �ּ�
    String use_pay_method  = f_get_parm( request.getParameter( "use_pay_method" ) ); // ���� ���         
    String ipgm_date       = f_get_parm( request.getParameter( "ipgm_date"      ) ); // ������� �����ð� 
	String enc_info        = f_get_parm( request.getParameter( "enc_info"       ) ); // ��ȣȭ ����       
    String enc_data        = f_get_parm( request.getParameter( "enc_data"       ) ); // ��ȣȭ ������     
    String van_code        = f_get_parm( request.getParameter( "van_code"       ) );
    String cash_yn         = f_get_parm( request.getParameter( "cash_yn"        ) );
    String cash_tr_code    = f_get_parm( request.getParameter( "cash_tr_code"   ) );
    /* ��Ÿ �Ķ���� �߰� �κ� - Start - */
    String param_opt_1    = f_get_parm( request.getParameter( "param_opt_1"     ) ); // ��Ÿ �Ķ���� �߰� �κ�
    String param_opt_2    = f_get_parm( request.getParameter( "param_opt_2"     ) ); // ��Ÿ �Ķ���� �߰� �κ�
    String param_opt_3    = f_get_parm( request.getParameter( "param_opt_3"     ) ); // ��Ÿ �Ķ���� �߰� �κ�
    /* ��Ÿ �Ķ���� �߰� �κ� - End -   */

  String tablet_size     = "1.0"; // ȭ�� ������ ����
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

<!-- �ŷ���� �ϴ� kcp ������ ����� ���� ��ũ��Ʈ-->
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
   /* �ֹ���ȣ ���� ���� */
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

   /* kcp web ����â ȣ�� (����Ұ�) */
  function call_pay_form()
  {
    var v_frm = document.order_info;

    document.getElementById("sample_wrap").style.display = "none";
    document.getElementById("layer_all").style.display  = "block";

    v_frm.target = "frm_all";
    v_frm.action = PayUrl;

    if (v_frm.Ret_URL.value == "")
    {
	  /* Ret_URL���� �� �������� URL �Դϴ�. */
	  alert("������ Ret_URL�� �ݵ�� �����ϼž� �˴ϴ�.");
      return false;
    }
    else
    {
      v_frm.submit();
    }
  }

   /* kcp ����� ���� ���� ��ȣȭ ���� üũ �� ���� ��û (����Ұ�) */
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
    <input type="hidden" name="req_tx"         value="<%=req_tx%>">               <!-- ��û ����          -->
    <input type="hidden" name="res_cd"         value="<%=res_cd%>">               <!-- ��� �ڵ�          -->
    <input type="hidden" name="tran_cd"        value="<%=tran_cd%>">              <!-- Ʈ����� �ڵ�      -->
    <input type="hidden" name="ordr_idxx"      value="<%=ordr_idxx%>">            <!-- �ֹ���ȣ           -->
    <input type="hidden" name="good_mny"       value="<%=good_mny%>">             <!-- �޴��� �����ݾ�    -->
    <input type="hidden" name="good_name"      value="<%=good_name%>">            <!-- ��ǰ��             -->
    <input type="hidden" name="buyr_name"      value="<%=buyr_name%>">            <!-- �ֹ��ڸ�           -->
    <input type="hidden" name="buyr_tel1"      value="<%=buyr_tel1%>">            <!-- �ֹ��� ��ȭ��ȣ    -->
    <input type="hidden" name="buyr_tel2"      value="<%=buyr_tel2%>">            <!-- �ֹ��� �޴�����ȣ  -->
    <input type="hidden" name="buyr_mail"      value="<%=buyr_mail%>">            <!-- �ֹ��� E-mail      -->
    <input type="hidden" name="cash_yn"		   value="<%=cash_yn%>">              <!-- ���ݿ����� ��Ͽ���-->
    <input type="hidden" name="enc_info"       value="<%=enc_info%>">
    <input type="hidden" name="enc_data"       value="<%=enc_data%>">
    <input type="hidden" name="use_pay_method" value="<%=use_pay_method%>">
    <input type="hidden" name="cash_tr_code"   value="<%=cash_tr_code%>">
    <!-- �߰� �Ķ���� -->
    <input type="hidden" name="param_opt_1"	   value="<%=param_opt_1%>">
    <input type="hidden" name="param_opt_2"	   value="<%=param_opt_2%>">
    <input type="hidden" name="param_opt_3"	   value="<%=param_opt_3%>">
</form>
</body>
</html>
