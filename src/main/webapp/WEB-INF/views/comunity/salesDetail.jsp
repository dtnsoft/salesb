<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
	<script>
      
	    // 페이지 이동
	    function goPageSalesPageList() {

	        var dataParam = $("#asConForm").serialize();
	        //commonDim(true);
	        $.ajax({
	            type: "POST",
	            url:  "<%= request.getContextPath() %>/comunity/saleslist",
	              data:dataParam,
	            success: function(result) {
	                   commonDim(false);
	                   $("#saleslist").html(result);
	            },
	            error:function(){
	                commonDim(false);
	            }
	        });
	    }
	    
	    function fcAs_reTranspath(){
			
			var url=document.asConForm.reTransurl_Modify.value;
			var transno=document.asConForm.reTransportNo_Modify.value;
			
			var theURL=url+transno;
			
		//	var h=700;
		//	var s=800;

		 //   tmt_winLaunch(theURL, 'transObj', 'transObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
		
		}
	   
		  /*
		   * 화면 POPUP
		   */
		  function tmt_winLaunch(theURL,winName,targetName,features) {
		  	
		  	var targetRandom=Math.random();
		  	eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

		  }

	</script>
<form:form class="form-inline" role="form" commandName="asConVO" id="asConForm" name="asConForm" method="post" action="" >
 <input type="hidden" name="curPage"             id="curPage"            value="${curPage}" />
 <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
 <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
 </form:form>
<!-- container -->
  <div id="container" class="comunity" >
    <div id="m_content" >
      <div class="clm_order_detail">
        <!-- 타이틀 -->
        <div class="clm_acdo_tit">
          <h1>판매상세</h1>
          <div class="clm_acdo_tit_left">
            <a href="javascript:goPageSalesPageList();" class="btn b_prev"><span class="sp_prev">이전</span></a>
          </div>
        </div>
        <!--// 타이틀 --> 
        <!-- 1.판매정보 -->
        <div class="clm_acdo_tit2">
          <h2 class="h2_txo"> <strong><em class="num">1. </em></strong>판매정보</h2>
          <a href="javascript:void(0)" class="tit_desc"> <span class="sp_odr ic_arr">&nbsp;</span> </a>
        </div>
        <div class="clm_acdo_sec">
          <dl class="clm_ip2">
            <dt><span class="tit">판매일</span></dt>
            <dd>
              <p class="tx1">${asVO.asStartDateTime}</p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">모델명</span></dt>
            <dd>
              <p class="tx2">${asVO.productName}</p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">상품사진</span></dt>
            <dd> <span class="img_area"> <span><a href="javascript:AutoResize('${asVO.asImage}')"><img src="${asVO.asImage}" alt="상품이미지"></a></span></dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">진행상황</span></dt>
            <dd><p class="tx1">
          	  <c:choose>
					<c:when test="${asVO.asState=='05' || asVO.asState=='06' || asVO.asState=='07' || asVO.asState=='08' || asVO.asState=='09'}">
						  <c:choose>
								<c:when test="${asVO.receiveType=='02'}">
									<c:if test="${asVO.asState=='05'}">
									  <strong><font style="color:red">A/S대행요청</font></strong>▶센터판매완료▶택배수령요청▶A/S완료
									</c:if>
							        <c:if test="${asVO.asState=='06'}">
									     A/S대행요청▶<strong><font style="color:red">센터판매의뢰</font></strong>▶택배수령요청▶A/S완료
									</c:if>
									<c:if test="${asVO.asState=='08'}">
									     A/S대행요청▶센터판매의뢰▶<strong><font style="color:red">택배수령요청</font></strong>▶A/S완료
									</c:if>
									<c:if test="${asVO.asState=='09'}">
									     A/S대행요청▶센터판매의뢰▶택배수령요청▶<strong><font style="color:red">A/S완료</font></strong>
									</c:if> 
			            	 	</c:when>
							 <c:otherwise>
									<c:if test="${asVO.asState=='05'}">
									     <strong><font style="color:red">A/S대행요청</font></strong>▶센터판매의뢰▶매장수령요청▶A/S완료
									</c:if>
									<c:if test="${asVO.asState=='06'}">
									     A/S대행요청▶<strong><font style="color:red">센터판매의뢰</font></strong>▶매장수령요청▶A/S완료
									</c:if>
									<c:if test="${asVO.asState=='07'}">
									     A/S대행요청▶센터판매의뢰▶<strong><font style="color:red">매장수령요청</font></strong>▶A/S완료
									</c:if>
									<c:if test="${asVO.asState=='09'}">
									     A/S대행요청▶센터판매의뢰▶매장수령요청▶<strong><font style="color:red">A/S완료</font></strong>
									</c:if>
							 </c:otherwise>
			 			  </c:choose> 
            	 	</c:when>
				 <c:otherwise>
				    <p class="tx4"> ${asVO.asStateTrans} </p>
				 </c:otherwise>
 			  </c:choose></p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">판매결과</span></dt>
            <dd>
              <p class="tx1">${asVO.asResult}
              <c:if test="${asVO.centerAsNo!=null && asVO.centerAsNo!=''}">
              <br>[센터 판매번호] : ${asVO.centerAsNo}
              </c:if>
              <c:if test="${asVO.centerImage!=null && asVO.centerImage!=''}">
              <br><span class="img_area"> <span><a href="javascript:AutoResize('${asVO.centerImage}')"><img src="${asVO.centerImage}" alt="상품이미지"></a></span>
              </c:if>
              </p>
            </dd>
          </dl>
        </div>
        <!--// 1. 판매정보 --> 
        <!-- 2.배송정보 -->
        <div class="clm_acdo_tit2">
          <h2 class="h2_txo"> <strong><em class="date">2.</em></strong>&nbsp;배송정보</h2>
          <a href="javascript:void(0)" class="tit_desc"> <span class="sp_odr ic_arr">&nbsp;</span> </a>
        </div>
        <div class="clm_acdo_sec">
          <dl class="clm_ip2">
            <dt><span class="tit">받는분</span></dt>
            <dd>
              <p class="tx1">${asVO.receiveName}&nbsp; </p>
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">연락처</span></dt>
            <dd>
              <p class="tx1">${asVO.receiveTelNo}
            </dd>
          </dl>
          <c:if test="${asVO.receiveType=='02'}">
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">주소</span></dt>
            <dd>
              <p class="tx1">
                ${asVO.receiveAddress} <br>
                ${asVO.receiveAddressDetail}
            </dd>
          </dl>
          <hr class="odr_line_ty1">
          <dl class="clm_ip2">
            <dt><span class="tit">운송업체</span></dt>
            <dd>
              <p class="tx1">
                ${asVO.reTransport}
                 <c:choose>
   						 <c:when test="${asVO.reTransurl!='N'}">
   						    <input type="hidden" name="reTransurl_Modify" id="reTransurl_Modify" value="${asVO.reTransurl}" >
   						    <input type="hidden" name="reTransportNo_Modify" id="reTransportNo_Modify" value="${asVO.reTransportNo}" >
   						  	<br>[운송장 번호] :<a href="javascript:fcAs_reTranspath();"><span id="reTransNoId">${asVO.reTransportNo}</span></a>
	             	 	 </c:when>
						 <c:otherwise>
						    <input type="hidden" name="reTransurl_Modify" id="reTransurl_Modify" value="${asVO.reTransurl}" >
   						    <input type="hidden" name="reTransportNo_Modify" id="reTransportNo_Modify" value="${asVO.reTransportNo}" >
						  	<br>[운송장 번호] : ${asVO.reTransportNo}
						 </c:otherwise>
	 			  </c:choose>
            </dd>
          </dl>
          </c:if>
        </div>
        <!--// 2.배송정보 -->
     	
      </div>
    </div>
  </div>
  <!-- //container -->