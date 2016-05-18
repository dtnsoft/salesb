<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>

<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%
    /* ============================================================================== */
    /* =   PAGE : 결제 요청 PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   이 페이지는 Payplus Plug-in을 통해서 결제자가 결제 요청을 하는 페이지    = */
    /* =   입니다. 아래의 ※ 필수, ※ 옵션 부분과 매뉴얼을 참조하셔서 연동을        = */
    /* =   진행하여 주시기 바랍니다.                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
    /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do			        = */
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
	<%@ include file="/kcp/cfg/site_conf_inc.jsp" %>
<%
	request.setCharacterEncoding ( "utf-8" ) ;
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
<!DOCTYPE html>
<html>

<head>

    <title>Salesb | E-commerce product detail</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/favicon.ico" type='image/ico'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <meta property="og:type" content="website">
    <meta property="og:title" content="Sale Baron">
    <meta property="og:description" content="나도 판매왕 되기">
    <meta property="og:image" content="<%= request.getContextPath() %>/images/salesbaron.jpg">
    

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/plugins/slick/slick.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/plugins/slick/slick-theme.css" rel="stylesheet">

    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/animate.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/style.css" rel="stylesheet">
    
    <%
    /* ============================================================================== */
    /* =   Javascript source Include                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ※ 필수                                                                  = */
    /* =   테스트 및 실결제 연동시 site_conf_inc.jsp파일을 수정하시기 바랍니다.     = */
    /* = -------------------------------------------------------------------------- = */
	%>
	    <script type="text/javascript" src="<%= g_conf_js_url %>"></script>
	<%
	    /* = -------------------------------------------------------------------------- = */
	    /* =   Javascript source Include END                                            = */
	    /* ============================================================================== */
	%>
	
	<!--	<script src="http://lite.payapp.kr/public/api/payapp-lite.js"></script> -->
 	
  <script type="text/javascript">

	  var controlCss = "<%= request.getContextPath() %>/kcp/mobile_sample/css/style.css";
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

  
        /* 플러그인 설치(확인) */
        StartSmartUpdate();
        
        /*  해당 스크립트는 타브라우져에서 적용이 되지 않습니다.
        if( document.Payplus.object == null )
        {
            openwin = window.open( "chk_plugin.html", "chk_plugin", "width=420, height=100, top=300, left=300" );
        }
        */

        /* Payplus Plug-in 실행 */
        function  jsf__pay( form )
        {
            var RetVal = false;

            /* Payplus Plugin 실행 */
            if ( MakePayMessage( form ) == true )
            {
                openwin = window.open( "<%= request.getContextPath() %>/kcp/sample/proc_win.html", "proc_win", "width=449, height=209, top=300, left=300" );
                RetVal = true ;
            }
            
            else
            {
                /*  res_cd와 res_msg변수에 해당 오류코드와 오류메시지가 설정됩니다.
                    ex) 고객이 Payplus Plugin에서 취소 버튼 클릭시 res_cd=3001, res_msg=사용자 취소
                    값이 설정됩니다.
                */
                res_cd  = document.order_info.res_cd.value ;
                res_msg = document.order_info.res_msg.value ;

            }

            return RetVal ;
        }

        // Payplus Plug-in 설치 안내 
        function init_pay_button()
        {
            if ((navigator.userAgent.indexOf('MSIE') > 0) || (navigator.userAgent.indexOf('Trident/7.0') > 0))
            {
                try
                {
                    if( document.Payplus.object == null )
                    {
                        document.getElementById("display_setup_message").style.display = "block" ;
                    }
                    else{
                        document.getElementById("display_pay_button").style.display = "block" ;
                    }
                }
                catch (e)
                {
                    document.getElementById("display_setup_message").style.display = "block" ;
                }
            }
            else
            {
                try
                {
                    if( Payplus == null )
                    {
                        document.getElementById("display_setup_message").style.display = "block" ;
                    }
                    else{
                        document.getElementById("display_pay_button").style.display = "block" ;
                    }
                }
                catch (e)
                {
                    document.getElementById("display_setup_message").style.display = "block" ;
                }
            }
        }

        /* 주문번호 생성 예제 */
        function init_orderid()
        {
            var today = new Date();
            var year  = today.getFullYear();
            var month = today.getMonth() + 1;
            var date  = today.getDate();
            var time  = today.getTime();

            if(parseInt(month) < 10) {
                month = "0" + month;
            }

            if(parseInt(date) < 10) {
                date = "0" + date;
            }

            var order_idxx = "S" + year + "" + month + "" + date + "" + time;

            document.order_info.ordr_idxx.value = order_idxx;
            
            document.OrderProcessForm.orderkey.value = order_idxx;
            
            //주문정보 입력 (주문키생성)
           	$.ajax({
                type: "POST",
                async:false,
                   url:  "<%= request.getContextPath() %>/order/orderkey",
                   data:$("#OrderProcessForm").serialize(),
                   success: function(result) {

        				if(result > 0){
        					
        					//주문키 입력성공
                                 
        				} else{
        			    	
        					alert('주문키 생성을 실패했습니다.');
        					return;
        					 
        				}

                   },
                   error:function(){
                	   
                	   alert('[error]주문키 생성을 실패했습니다.');
                	   return;
                   }
            });

            /*
             * 인터넷 익스플로러와 파이어폭스(사파리, 크롬.. 등등)는 javascript 파싱법이 틀리기 때문에 object 가 인식 전에 실행 되는 문제
             * 기존에는 onload 부분에 추가를 했지만 setTimeout 부분에 추가
             * setTimeout 300의 의미는 플러그인 인식속도에 따른 여유시간 설정
             * - 20101018 -
             */
            setTimeout("init_pay_button();",300);
        }

        /* onLoad 이벤트 시 Payplus Plug-in이 실행되도록 구성하시려면 다음의 구문을 onLoad 이벤트에 넣어주시기 바랍니다. */
        function onload_pay()
        {
             if( jsf__pay(document.order_info) )
                document.order_info.submit();
        }

    </script>

    <script>
    
      //google anlaystic적용
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');


	  ga('create', 'UA-73697440-1', 'auto');

	  ga('set', 'userId', '116577077'); // 로그인한 User-ID를 사용하여 User-ID를 설정합니다.

	  ga('send', 'pageview');
    
	function optionDetail(optionId){
		
		 $.ajax({
	            type: "POST",
	               url:  "<%= request.getContextPath() %>/order/optiondetaillist?optionId="+optionId,
	               success: function(result) {
	                   //commonDim(false);
	                   $("#addOptionDetailList").html(result);
	 
	               },
	               error:function() {
	                 
	               }
	        });		
		
	}
	var optionObj=''; 
	
	function optionDetailSelect(optionId,optionValueKey,optionValue){

		document.all('select_tr_'+optionId).innerText=optionValue;
		
		optionObj=optionObj+'|'+optionId+'^'+optionValueKey;
		
		 document.order_info.buyr_tel2.value = optionObj;

		$("#modalColse1").click();
	}
    
    </script>

</head>

<body onload="init_orderid();">

<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
	            <div class="sidebar-collapse">
	                <ul class="nav metismenu" id="side-menu">
	                    <li class="nav-header">
	                        <div class="dropdown profile-element"> <span>
	                            <img alt="image" class="img-circle" src="<%= request.getContextPath() %>/images/intro/icon.png" />
	                             </span>
	                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                            <span class="clear"> <span class="block m-t-xs"> 
	                             <strong class="font-bold">SALESB</strong>
	                             </span> <span class="text-muted text-xs block">Welcome to salesb!<b class="caret"></b></span> </span> </a>
	                        </div>
	                        <div class="logo-element">
	                           <img alt="image" class="img-circle" src="<%= request.getContextPath() %>/images/intro/icon.png" />
	                        </div>
	                    </li>
	                </ul>
	            </div>
	        </nav>
	         
	    <div id="page-wrapper" class="gray-bg dashbard-1">
	        <div class="row border-bottom">
	        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
	        <div class="navbar-header">
	            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
	        </div>
	            <ul class="nav navbar-top-links navbar-right">
	                <li>
	                    <span class="m-r-sm text-muted welcome-message">Welcome to Salesb PC version.</span>
	                </li>
	                <li>
	                </li>
	            </ul>

        	</nav>
        </div>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>E-commerce product detail</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a>E-commerce</a>
                    </li>
                    <li class="active">
                        <strong>Product detail</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-lg-12">

                    <div class="ibox product-detail">
                        <div class="ibox-content">

                            <div class="row">
                                <div class="col-md-5">


                                    <div class="product-images">
                                    
                                      <c:if test="${token.image1!=null  && token.image1!=''}">
                                        <div>
                                            <div class="image-imitation">
                                                 <img id="image1" src="${token.image1}"  alt="이미지">
                                            </div>
                                        </div>
								      </c:if>
								      <c:if test="${token.image2!=null && token.image2!=''}">
                                        <div>
                                            <div class="image-imitation">
                                                 <img id="image2" src="${token.image2}"   alt="이미지">
                                            </div>
                                        </div>
								      </c:if>
								      <c:if test="${token.image3!=null && token.image3!=''}">
                                        <div>
                                            <div class="image-imitation">
                                                 <img id="image3" src="${token.image3}"   alt="이미지">
                                            </div>
                                        </div>
								      </c:if>
								      <c:if test="${token.image4!=null && token.image4!=''}">
                                        <div>
                                            <div class="image-imitation">
                                                 <img id="image4" src="${token.image4}"   alt="이미지">
                                            </div>
                                        </div>
								      </c:if>
								      <c:if test="${token.image5!=null && token.image5!=''}">
                                        <div>
                                            <div class="image-imitation">
                                                 <img id="image5" src="${token.image5}"   alt="이미지">
                                            </div>
                                        </div>
								      </c:if>
                                    </div>

                                </div>
                                <div class="col-md-7">

                                    <h2 class="font-bold m-b-xs">
                                        ${goods.productName}
                                    </h2>
                                    <small>Product</small>
                                    <div class="m-t-md">
                                        <h2 class="product-main-price">${goods.salesPrice} 원 <small class="text-muted">Price</small> </h2>
                                    </div>
                                    <hr>

                                    <h4>Product description</h4>

                                    <div class="small text-muted">
                                        ${token.productEtc}
                                    </div>
                                    <dl class="small m-t-md">
                                        <dt>Product Option</dt>
                                        <dd>
											<div class="ibox-content">
									          <div class="table-responsive">
									              <table class="table table-striped" id="contentId">
									             	  <div class="modal inmodal" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true">
										                    <div class="modal-dialog">
										                        <div class="modal-content animated flipInY">
										                            <div class="modal-header">
										                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										                                <h4 class="modal-title">옵션 상세선택</h4>
										                                <small class="font-bold">* 선택된 옵션으로 주문합니다.</small>
										                            </div>
										                            <div class="modal-body">
														              <div id=addOptionDetailList></div>
										                            </div>
										                            <div class="modal-footer">
										                                <button type="button" class="btn btn-white" id="modalColse1" data-dismiss="modal">Close</button>
										                            </div>
										                        </div>
										                    </div>
										                </div>
									                  <thead>
										             	 <tr>
									                      <th>구분</th>
									                      <th>선택된옵션</th>
									                      <th>선택하기</th>
									                  	</tr>
									                  </thead>
									                   	<tbody>
									                      <c:if test="${!empty optionList}">
										                       <c:forEach items="${optionList}" var="OptionVO" varStatus="status">
										                        	<tr>
												                        <td>${OptionVO.optionName}</td>
												                        <td id="select_tr_${OptionVO.optionId}"></td>
												                        <td><button type="button" class="btn btn-primary btn-xs"  onClick="optionDetail('${OptionVO.optionId}')" data-toggle="modal" data-target="#myModal1">상세옵션선택</button></td>
										                            </tr>
										                       </c:forEach>
												          </c:if>
											              <c:if test="${empty optionList}">
										                      <tr>
										                         <td colspan='3' class='text-center'>추가 옵션 정보가 없습니다.</td>
													          </tr>
													      </c:if>
									                 </tbody>
									              </table>
									          </div>
								   			</div>
										</dd>
                                        <dt>판매자 핸드폰</dt>
                                        <dd>${customer.sbPhoneNumber}</dd>
                                        <dt>판매자 이메일</dt>
                                        <dd>${customer.sbEmail}</dd>
                                    </dl>
                                      <hr>

                                    <h4>PayPal 결재</h4>

                                    <div class="small text-muted">
                                       <script src="https://www.paypalobjects.com/js/external/paypal-button.min.js?merchant=Z75Q9DK6L5VRC" async="async" 
									    data-name="offact" 
									    data-button="buynow" 
									    data-quantity="1" 
									    data-callback="http://dev.addys.co.kr" 
									    data-env="sandbox"
									    data-charset="UTF-8"
									></script>
                                    </div>

                                    
									<!-- 3.결재 -->
							        <form commandName="orderVo"   id="OrderProcessForm" name="OrderProcessForm"  method="post" role="form" >
							           <input type="hidden" name="orderkey" value="" />
							           <input type="hidden" name="tokenkey" value="${token.tokenkey}" />
							           <input type="hidden" name="customerKey" value="${customer.customerKey}" />
							           <input type="hidden" name="productCode" value="${goods.idx}" />
							           <input type="hidden" name="salesPrice" value="${goods.salesPrice}" />
							           <input type="hidden" name="groupId" value="${token.groupId}" />
							           <input type="hidden" name="orderState" value="01" />
							        </form>
							        <!-- 주문정보 입력 form : order_info -->
									<!--NT	<form name="order_info" method="post" action="<%= request.getContextPath() %>/kcp/sample/pp_cli_hub.jsp" > -->
									<!--linux-->
									<form name="order_info" method="post" action="<%= request.getContextPath() %>/kcp/sample/<%=hub_page_info%>" >
									<%
									    /* ============================================================================== */
									    /* =   1. 주문 정보 입력                                                        = */
									    /* = -------------------------------------------------------------------------- = */
									    /* =   결제에 필요한 주문 정보를 입력 및 설정합니다.                            = */
									    /* = -------------------------------------------------------------------------- = */
									%>
									
									<%
							            /* ============================================================================== */
							            /* =   1-1. 결제 수단 정보 설정                                                 = */
							            /* = -------------------------------------------------------------------------- = */
							            /* =   결제에 필요한 결제 수단 정보를 설정합니다.                               = */
							            /* =                                                                            = */
							            /* =  신용카드 : 100000000000, 계좌이체 : 010000000000, 가상계좌 : 001000000000 = */
							            /* =  포인트   : 000100000000, 휴대폰   : 000010000000, 상품권   : 000000001000 = */
							            /* =  ARS      : 000000000010                                                   = */
							            /* =                                                                            = */
							            /* =  위와 같이 설정한 경우 PayPlus Plugin에서 설정한 결제수단이 표시됩니다.    = */
							            /* =  Payplug Plugin에서 여러 결제수단을 표시하고 싶으신 경우 설정하시려는 결제 = */
							            /* =  수단에 해당하는 위치에 해당하는 값을 1로 변경하여 주십시오.               = */
							            /* =                                                                            = */
							            /* =  예) 신용카드, 계좌이체, 가상계좌를 동시에 표시하고자 하는 경우            = */
							            /* =  pay_method = "111000000000"                                               = */
							            /* =  신용카드(100000000000), 계좌이체(010000000000), 가상계좌(001000000000)에  = */
							            /* =  해당하는 값을 모두 더해주면 됩니다.                                       = */
							            /* =                                                                            = */
							            /* = ※ 필수                                                                    = */
							            /* =  KCP에 신청된 결제수단으로만 결제가 가능합니다.                            = */
							            /* = -------------------------------------------------------------------------- = */
									%>
									   <!-- 신용카드 -->
							
									   <!-- 주문번호(ordr_idxx) -->
									   <input type="hidden" name="ordr_idxx" value="" />
									   <!-- 상품명(good_name) -->
									   <input type="hidden" name="good_name" value="${goods.productName}" />
									    <!-- 결제금액(good_mny) - ※ 필수 : 값 설정시 ,(콤마)를 제외한 숫자만 입력하여 주십시오. -->
									   <input type="hidden" name="good_mny" value="${goods.salesPrice}" />
									    <!-- 주문자명(buyr_name) -->
									   <input type="hidden" name="buyr_name" value="" />
									   <!-- 주문자 E-mail(buyr_mail) -->
									   <input type="hidden" name="buyr_mail" value="${customer.sbEmail}" />
									   <!-- 주문자 연락처1(buyr_tel1) -->
									   <input type="hidden" name="buyr_tel1" value="${customer.sbPhoneNumber}" />
									   <!-- 휴대폰번호(buyr_tel2) -->
									   <input type="hidden" name="buyr_tel2" value="" />
									   
									<!-- 결제 요청/처음으로 이미지 -->
									<!-- 
                                    <div>
		                               <div class="form-group"><label class="col-sm-2 control-label">KCP 지불 방법</label>
		                                    <div class="col-sm-10">
		                                    <select class="form-control m-b" name="pay_method">
		                                        <option value="100000000000">신용카드</option>
				                                <option value="010000000000">계좌이체</option>
				                                <option value="001000000000">가상계좌</option>
				                                <option value="000100000000">포인트</option>
				                                <option value="000010000000">휴대폰</option>
				                                <option value="000000001000">상품권</option>
				                                <option value="000000000010">ARS</option>
				                                <<option value="111000000000">신용카드/계좌이체/가상계좌</option>  
		                                    </select>
                                            <button class="btn btn-primary btn-sm"  type="submit" onclick="return jsf__pay(this.form);"><i class="fa fa-cart-plus"></i> 결제요청</button>
                                       		</div>
		                                </div>
                                    </div>
                                     -->
									<%
									    /* = -------------------------------------------------------------------------- = */
									    /* =   1. 주문 정보 입력 END                                                    = */
									    /* ============================================================================== */
									%>
									
									<%
									    /* ============================================================================== */
									    /* =   2. 가맹점 필수 정보 설정                                                 = */
									    /* = -------------------------------------------------------------------------- = */
									    /* =   ※ 필수 - 결제에 반드시 필요한 정보입니다.                               = */
									    /* =   site_conf_inc.jsp 파일을 참고하셔서 수정하시기 바랍니다.                 = */
									    /* = -------------------------------------------------------------------------- = */
									    // 요청종류 : 승인(pay)/취소,매입(mod) 요청시 사용
									%>
									    <input type="hidden" name="req_tx"          value="pay" />
									    <input type="hidden" name="site_cd"         value="<%= g_conf_site_cd   %>" />
									    <input type="hidden" name="site_name"       value="<%= g_conf_site_name %>" />
									<%
									    /*
									       할부옵션 : Payplus Plug-in에서 카드결제시 최대로 표시할 할부개월 수를 설정합니다.(0 ~ 18 까지 설정 가능)
									       ※ 주의  - 할부 선택은 결제금액이 50,000원 이상일 경우에만 가능, 50000원 미만의 금액은 일시불로만 표기됩니다
									                  예) value 값을 "5" 로 설정했을 경우 => 카드결제시 결제창에 일시불부터 5개월까지 선택가능
									    */
									%>
									    <input type="hidden" name="quotaopt"        value="12"/>
									    <!-- 필수 항목 : 결제 금액/화폐단위 -->
									    <input type="hidden" name="currency"        value="WON"/>
									<%
									    /* = -------------------------------------------------------------------------- = */
									    /* =   2. 가맹점 필수 정보 설정 END                                             = */
									    /* ============================================================================== */
									%>
									
									<%
									    /* ============================================================================== */
									    /* =   3. Payplus Plugin 필수 정보(변경 불가)                                   = */
									    /* = -------------------------------------------------------------------------- = */
									    /* =   결제에 필요한 주문 정보를 입력 및 설정합니다.                            = */
									    /* = -------------------------------------------------------------------------- = */
									%>
									    <!-- PLUGIN 설정 정보입니다(변경 불가) -->
									    <input type="hidden" name="module_type"     value="<%= module_type %>"/>
									<!--
									      ※ 필 수
									          필수 항목 : Payplus Plugin에서 값을 설정하는 부분으로 반드시 포함되어야 합니다
									          값을 설정하지 마십시오
									-->
									    <input type="hidden" name="res_cd"          value=""/>
									    <input type="hidden" name="res_msg"         value=""/>
									    <input type="hidden" name="tno"             value=""/>
									    <input type="hidden" name="trace_no"        value=""/>
									    <input type="hidden" name="enc_info"        value=""/>
									    <input type="hidden" name="enc_data"        value=""/>
									    <input type="hidden" name="ret_pay_method"  value=""/>
									    <input type="hidden" name="tran_cd"         value=""/>
									    <input type="hidden" name="bank_name"       value=""/>
									    <input type="hidden" name="bank_issu"       value=""/>
									    <input type="hidden" name="use_pay_method"  value=""/>
									
									    <!--  현금영수증 관련 정보 : Payplus Plugin 에서 설정하는 정보입니다 -->
									    <input type="hidden" name="cash_tsdtime"    value=""/>
									    <input type="hidden" name="cash_yn"         value=""/>
									    <input type="hidden" name="cash_authno"     value=""/>
									    <input type="hidden" name="cash_tr_code"    value=""/>
									    <input type="hidden" name="cash_id_info"    value=""/>
									
										<!-- 2012년 8월 18일 전자상거래법 개정 관련 설정 부분 -->
										<!-- 제공 기간 설정 0:일회성 1:기간설정(ex 1:2012010120120131)  -->
										<input type="hidden" name="good_expr" value="0">
									
										<!-- 가맹점에서 관리하는 고객 아이디 설정을 해야 합니다.(필수 설정) -->
										<input type="hidden" name="shop_user_id"    value=""/>
										<!-- 복지포인트 결제시 가맹점에 할당되어진 코드 값을 입력해야합니다.(필수 설정) -->
									    <input type="hidden" name="pt_memcorp_cd"   value=""/>
									
									<%
									    /* = -------------------------------------------------------------------------- = */
									    /* =   3. Payplus Plugin 필수 정보 END                                          = */
									    /* ============================================================================== */
									%>
									
									<%
									    /* ============================================================================== */
									    /* =   4. 옵션 정보                                                             = */
									    /* = -------------------------------------------------------------------------- = */
									    /* =   ※ 옵션 - 결제에 필요한 추가 옵션 정보를 입력 및 설정합니다.             = */
									    /* = -------------------------------------------------------------------------- = */
									
										/* 사용카드 설정 여부 파라미터 입니다.(통합결제창 노출 유무)
										<input type="hidden" name="used_card_YN"        value="Y"/> */
										/* 사용카드 설정 파라미터 입니다. (해당 카드만 결제창에 보이게 설정하는 파라미터입니다. used_card_YN 값이 Y일때 적용됩니다.
										/<input type="hidden" name="used_card"        value="CCBC:CCKM:CCSS"/> */
									
									    /* 신용카드 결제시 OK캐쉬백 적립 여부를 묻는 창을 설정하는 파라미터 입니다
									         포인트 가맹점의 경우에만 창이 보여집니다
									        <input type="hidden" name="save_ocb"        value="Y"/> */
									
										/* 고정 할부 개월 수 선택
										       value값을 "7" 로 설정했을 경우 => 카드결제시 결제창에 할부 7개월만 선택가능
									    <input type="hidden" name="fix_inst"        value="07"/> */
									
									    /*  무이자 옵션
									            ※ 설정할부    (가맹점 관리자 페이지에 설정 된 무이자 설정을 따른다)                             - "" 로 설정
									            ※ 일반할부    (KCP 이벤트 이외에 설정 된 모든 무이자 설정을 무시한다)                           - "N" 로 설정
									            ※ 무이자 할부 (가맹점 관리자 페이지에 설정 된 무이자 이벤트 중 원하는 무이자 설정을 세팅한다)   - "Y" 로 설정
									    <input type="hidden" name="kcp_noint"       value=""/> */
									
									    /*  무이자 설정
									            ※ 주의 1 : 할부는 결제금액이 50,000 원 이상일 경우에만 가능
									            ※ 주의 2 : 무이자 설정값은 무이자 옵션이 Y일 경우에만 결제 창에 적용
									            예) 전 카드 2,3,6개월 무이자(국민,비씨,엘지,삼성,신한,현대,롯데,외환) : ALL-02:03:04
									            BC 2,3,6개월, 국민 3,6개월, 삼성 6,9개월 무이자 : CCBC-02:03:06,CCKM-03:06,CCSS-03:06:04
									    <input type="hidden" name="kcp_noint_quota" value="CCBC-02:03:06,CCKM-03:06,CCSS-03:06:09"/> */
									
									
										/* 해외카드 구분하는 파라미터 입니다.(해외비자, 해외마스터, 해외JCB로 구분하여 표시)
										<input type="hidden" name="used_card_CCXX"        value="Y"/> */
									
									    /*  가상계좌 은행 선택 파라미터
									         ※ 해당 은행을 결제창에서 보이게 합니다.(은행코드는 매뉴얼을 참조)
									    <input type="hidden" name="wish_vbank_list" value="05:03:04:07:11:23:26:32:34:81:71"/> */
									
									    /*  가상계좌 입금 기한 설정하는 파라미터 - 발급일 + 3일
									    <input type="hidden" name="vcnt_expire_term" value="3"/> */
									
									    /*  가상계좌 입금 시간 설정하는 파라미터
									         HHMMSS형식으로 입력하시기 바랍니다
									         설정을 안하시는경우 기본적으로 23시59분59초가 세팅이 됩니다
									         <input type="hidden" name="vcnt_expire_term_time" value="120000"/> */
									
									    /* 포인트 결제시 복합 결제(신용카드+포인트) 여부를 결정할 수 있습니다.- N 일경우 복합결제 사용안함
									        <input type="hidden" name="complex_pnt_yn" value="N"/>    */
									
									    /* 현금영수증 등록 창을 출력 여부를 설정하는 파라미터 입니다
									         ※ Y : 현금영수증 등록 창 출력
									         ※ N : 현금영수증 등록 창 출력 안함 
										※ 주의 : 현금영수증 사용 시 KCP 상점관리자 페이지에서 현금영수증 사용 동의를 하셔야 합니다
									        <input type="hidden" name="disp_tax_yn"     value="Y"/> */
									
									    /* 결제창에 가맹점 사이트의 로고를 플러그인 좌측 상단에 출력하는 파라미터 입니다
									       업체의 로고가 있는 URL을 정확히 입력하셔야 하며, 최대 150 X 50  미만 크기 지원
									
										※ 주의 : 로고 용량이 150 X 50 이상일 경우 site_name 값이 표시됩니다.
									        <input type="hidden" name="site_logo"       value="" /> */
									
										/* 결제창 영문 표시 파라미터 입니다. 영문을 기본으로 사용하시려면 Y로 세팅하시기 바랍니다
											2010-06월 현재 신용카드와 가상계좌만 지원됩니다
										    <input type='hidden' name='eng_flag'      value='Y'> */
									
										/* KCP는 과세상품과 비과세상품을 동시에 판매하는 업체들의 결제관리에 대한 편의성을 제공해드리고자, 
										   복합과세 전용 사이트코드를 지원해 드리며 총 금액에 대해 복합과세 처리가 가능하도록 제공하고 있습니다
										   복합과세 전용 사이트 코드로 계약하신 가맹점에만 해당이 됩니다
									       상품별이 아니라 금액으로 구분하여 요청하셔야 합니다
										   총결제 금액은 과세금액 + 부과세 + 비과세금액의 합과 같아야 합니다. 
										   (good_mny = comm_tax_mny + comm_vat_mny + comm_free_mny)
										
										    <input type="hidden" name="tax_flag"       value="TG03">  <!-- 변경불가	   -->
										    <input type="hidden" name="comm_tax_mny"   value=""    >  <!-- 과세금액	   --> 
									        <input type="hidden" name="comm_vat_mny"   value=""    >  <!-- 부가세	   -->
										    <input type="hidden" name="comm_free_mny"  value=""    >  <!-- 비과세 금액 --> */
									
										/* skin_indx 값은 스킨을 변경할 수 있는 파라미터이며 총 7가지가 지원됩니다. 
										   변경을 원하시면 1부터 7까지 값을 넣어주시기 바랍니다. 
									
											<input type='hidden' name='skin_indx'      value='1'> */
									
										/* 상품코드 설정 파라미터 입니다.(상품권을 따로 구분하여 처리할 수 있는 옵션기능입니다.)
										    <input type='hidden' name='good_cd'      value=''> */
									
									    /* = -------------------------------------------------------------------------- = */
									    /* =   4. 옵션 정보 END                                                         = */
									    /* ============================================================================== */
									%>
									</form>

                                </div>
                            </div>

                        </div>
                        <div class="ibox-footer">
                            <span class="pull-right">
                                Full stock - <i class="fa fa-clock-o"></i> 14.04.2016 10:04 pm
                            </span>
                            The generated Lorem Ipsum is therefore always free
                        </div>
                    </div>

                </div>
            </div>
        

        </div>
        <div class="footer">
	           <div class="pull-right">
	               welcome to <strong>Salesb</strong> !!
	           </div>
	           <div>
	               <strong>Copyright</strong> Salesb Corp &copy; All rights reserved v1.0.0
	           </div>
		</div> 

    </div>
</div>



<!-- Mainly scripts -->
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/jquery-2.1.1.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/inspinia.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/pace/pace.min.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/wow/wow.min.js"></script>

<!-- slick carousel-->
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/slick/slick.min.js"></script>

<script>
    $(document).ready(function(){


        $('.product-images').slick({
            dots: true
        });

    });

</script>

</body>

</html>
