//탑메뉴 열기(Hover) : 주메뉴 내 상담내역, 상담 관리, 커뮤니티, 시스템관리 등등
$(function(){
    //li에 마우스 오버 되었을 때
    $('.lnb_area li').hover(
        function(){
            //마우스 오버
            //해당 li에 hidden_obj 삭제 - 해당 css 삭제시 보여지게된다.
            $('ul',this).removeClass("hidden_obj");
            $('a',this).addClass('on');
        },
        function(){
            //마우스 아웃
            //해당 li에 hidden_obj 추가 - 해당 css 추가시 보이지 않는다.
            $('ul',this).addClass("hidden_obj");
            $('a',this).removeClass("on");
        })
});


//클릭 시 리스트 : 소프트폰 업무,이석,보류 등등
function showList(id){

    //클릭시 해당 ID 값 가져와서 현재 hidden_obj의 class가 있는지 체크
    var check = $('#'+id).hasClass("hidden_obj");

    //현재 안보여지고 있을때 hidden_obj 삭제
    if(check ==  true){
        $('#'+id).removeClass("hidden_obj");

    //현재 보여지고 있을때 hidden_obj 추가
    }else{
        $('#'+id).addClass("hidden_obj");
    }

};


//탭1 : 화면에서 사용하는 탭
function changeTab(id,tabName){

    var cnt = $('[name='+tabName+'] h3').length;

    for(var i=0; i<cnt; i++){
        //해당 위치의 tabName h3에 모두 on을 빼주고 off를 넣어준다.
        $('[name='+tabName+'] h3:eq('+i+')').removeClass('on').addClass('off');
        $('[name='+tabName+'] .tab_con_area01:eq('+i+')').addClass('hidden_obj');

    }

    //마지막으로 선택한 위치의 h3만 on을 넣어준다.
    $(id).removeClass('off').addClass('on');
    $(id).next('.tab_con_area01').removeClass('hidden_obj');

}


//구매영역 : 딜번호 클릭 시 딜티켓 상세 탭 열리도록
function openTicketInfo(){

    //딜티켓 상세로 자동이동
    changeTab($('#ticketinfo'),'tab01');
}


//탭2 : 레이어팝업에서 사용하는 탭
function changeTabLy(id,tabName){

    var cnt = $('[name='+tabName+'] h2').length;

    for(var i=0; i<cnt; i++){
        //해당 위치의 tabName h3에 모두 on을 빼주고 off를 넣어준다.
        $('[name='+tabName+'] h2:eq('+i+')').removeClass('on').addClass('off');
        $('[name='+tabName+'] .tab_con_area01:eq('+i+')').addClass('hidden_obj');

    }

    //마지막으로 선택한 위치의 h2만 on을 넣어준다.
    $(id).removeClass('off').addClass('on');
    $(id).next('.tab_con_area01').removeClass('hidden_obj');

}


//아코디언(리스트 클릭시 바로 밑으로 내용 열림) : 딜상담이력>이관이력, 상담이력>이관이력, 정산정보
//    //페이지 열렸을 때 자동으로 모든 class:con01을 안보이게
//    $(function(){
//        //모든 .con01의 class가 있는 tr 을 닫는다.
//        $('[name='+acordian+'] .con01').hide();
//    });

//클릭했을 때 해당 위치의 번호의 tr을 보이게한다.
function openConn(index, acordian){
    //현재 안보이면 보이게 열어준다.
    if($('[name='+acordian+'] [id='+index+']').css("display") == "none"){
        //클릭 했을 때 우선 모든 걸 닫게한다. 그래야 열린것이 아닌 다른 걸 클릭했을 때 열려있던게 닫힘
        $('[name='+acordian+']  .con01').hide();

        //해당 위치를 보이게 한다.
        $('[name='+acordian+'] [id='+index+']').show();
    }else{
        //현재 열려있으면 닫아준다. 모든 위치 숨킨다.
        $('[name='+acordian+'] [id='+index+']').hide();
    }

};


//달력1(이미지버튼 클릭 달력) : CS_구매리스트영역
$(document).ready(function() {
    $( "#hisCalendarData1, #hisCalendarData2" ).datepicker({
      showOn: "button",
      buttonImage: " /pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력2(이미지버튼 클릭 달력) : CS/PT/TS/TSP/IS_상담결과영역
$(document).ready(function() {
    $( "#hisCalendarData3" ).datepicker({
      showOn: "button",
      buttonImage: "/pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력2-1(이미지버튼 클릭 달력) : TSP_상담결과영역(해지/설치예정일)/IS_상담결과영역(미팅일시)
$(document).ready(function() {
    $( "#hisCalendarData3_1" ).datepicker({
      showOn: "button",
      buttonImage: "/pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력3(이미지버튼 클릭 달력) : PT_판매리스트영역
$(document).ready(function() {
    $( "#hisCalendarData4" ).datepicker({
      showOn: "button",
      buttonImage: " ../../images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력4(이미지버튼 클릭 달력) : AD_클레임관리(내방)
$(document).ready(function() {
    $( "#hisCalendarData5" ).datepicker({
      showOn: "button",
      buttonImage: " /pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력5(이미지버튼 클릭 달력) : AD_클레임관리(접수일)
$(document).ready(function() {
    $( "#hisCalendarData6" ).datepicker({
      showOn: "button",
      buttonImage: " ../../images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력6(이미지버튼 클릭 달력) : AD_클레임관리(회신일)
$(document).ready(function() {
    $( "#hisCalendarData7" ).datepicker({
      showOn: "button",
      buttonImage: " ../../images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력7(이미지버튼 클릭 달력) : AD_클레임관리>상담결과조회(상담정보)
$(document).ready(function() {
    $( "#hisCalendarData8" ).datepicker({
      showOn: "button",
      buttonImage: " ../../images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력8(이미지버튼 클릭 달력) : CS/PT/TS/TSP/IS_상담결과영역_대외기관접수(접수일)
$(document).ready(function() {
    $( "#hisCalendarData9" ).datepicker({
      showOn: "button",
      buttonImage: "/pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력9(이미지버튼 클릭 달력) : CS/PT/TS/TSP/IS_상담결과영역_대외기관접수(내방일)
$(document).ready(function() {
    $( "#hisCalendarData10" ).datepicker({
      showOn: "button",
      buttonImage: "/pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력10(이미지버튼 클릭 달력) : CS/PT/TS/TSP/IS_상담결과영역_대외기관접수(회신일)
$(document).ready(function() {
    $( "#hisCalendarData11" ).datepicker({
      showOn: "button",
      buttonImage: "/pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//달력11(이미지버튼 클릭 달력) : AD_클레임관리(등록일자)
$(document).ready(function() {
    $( "#hisCalendarData20, #hisCalendarData21" ).datepicker({
      showOn: "button",
      buttonImage: " /pms/images/common/ico_calendar.gif",
      buttonImageOnly: true,
      //maxDate:0, //오늘 날짜까지만 선택하도록
        prevText: "이전",
        nextText: "다음",
        dateFormat: "yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeMonth: true,
        changeYear: true
    });
  });


//레이어팝업0 : CS_추가정보
function goLayoutPCS00(){
    $('#LayoutPCS00').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 563,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('addInfo');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS00").dialog('close');

                });



        }
    });
};


//레이어팝업1 : CS_고객목록조회
function goLayoutPCS10(){
    $('#LayoutPCS10').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 514,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSCustomerListSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS10").dialog('close');

                });

        }
    });
};


//레이어팝업1-1 : CS_고객상세정보조회
function goLayoutPCS11(){
    $('#LayoutPCS11').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 183,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSCustomerDetailInfoSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS11").dialog('close');

                });

        }
    });
};


//레이어팝업1-2 : CS_SMS발송내역조회
function goLayoutPCS12(tel){
    $('#LayoutPCS12').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 505,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url

            $(this).load('/pms/csSmsSendSearch', {tel:tel});


            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS12").dialog('close');

                });

        }
    });
};


//레이어팝업1-3 : CS_멤버십등급이력조회
function goLayoutPCS13(accountSrl){
    $('#LayoutPCS13').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 299,
        modal : true, //주위를 어둡게

        open:function(){
            //alert(13);
            //alert(accountSrl);
            //팝업 가져올 url
            $(this).load('/pms/csMembershipGradeHistorySearch',{accountSrl:accountSrl});

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS13").dialog('close');

                });

        }
    });
};


//레이어팝업1-4 : CS_적립금입출금내역조회
function goLayoutPCS14(accountSrl){
    $('#LayoutPCS14').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 299,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            //alert(14);
            //alert(accountSrl);
            $(this).load('/pms/csReserveInOutSearch', {accountSrl:accountSrl});


            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS14").dialog('close');

                });

        }
    });
};


//레이어팝업1-5 : CS_할인쿠폰조회
function goLayoutPCS15(){
    $('#LayoutPCS15').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 469,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSDcCouponSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS15").dialog('close');

                });

        }
    });
};


//레이어팝업1-6 : CS_대외기관접수조회
function goLayoutPCS16(){
    $('#LayoutPCS16').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1000,
        height : 348,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSTendClaimSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS16").dialog('close');

                });

        }
    });
};


//레이어팝업2 : CS_정산정보조회(티켓)
function goLayoutPCS20(){
    $('#LayoutPCS20').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 312,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSExactInfoSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS20").dialog('close');

                });

        }
    });
};


//레이어팝업2-1 : CS_공지&frasl;주의사항조회(티켓)
function goLayoutPCS21(){
    $('#LayoutPCS21').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 518,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('/pms/csAnnounceSuggestionsSearch', {deal_srl:$("#csDealTicketDetailDealSrl", ".buy_deal").text()});

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS21").dialog('close');

                });

        }
    });
};


//레이어팝업2-2 : CS_판매자연락처(티켓)
function goLayoutPCS22(){
    $('#LayoutPCS22').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 199,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('/pms/csSellerContactList', {main_deal_srl:$("#csDealTicketDetailDealSrl", ".buy_deal").text()});

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS22").dialog('close');
            });
        }
    });
};


//레이어팝업3 : CS_정산정보조회(배송)
function goLayoutPCS30(){
    $('#LayoutPCS30').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 312,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSExactInfoSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS30").dialog('close');

                });

        }
    });
};


//레이어팝업3-1 : CS_공지&frasl;주의사항조회(배송)
function goLayoutPCS31(){
    $('#LayoutPCS31').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 518,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSAnnounceSuggestionsSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS31").dialog('close');

                });

        }
    });
};


//레이어팝업3-2 : CS_판매자연락처(배송)
function goLayoutPCS32(){
    $('#LayoutPCS32').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 199,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSSellerContactLlist.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS32").dialog('close');

                });

        }
    });
};


//레이어팝업4 : CS_딜상담이력상세조회
function goLayoutPCS40(){
    $('#LayoutPCS40').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 639,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSCallCounselHistoryDetailSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS40").dialog('close');

                });

        }
    });
};


//레이어팝업5 : CS_상담이력상세조회
function goLayoutPCS50(strDeptCd, strCrtNo){
    $('#LayoutPCS50').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 639,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('/pms/mainCounselView', {deptCode:strDeptCd, crtNo:strCrtNo});
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS50").dialog('close');

                });

        }
    });
};


//레이어팝업6 : CS_상담유형조회
function goLayoutPCS60(callbackFunc){
    $('#LayoutPCS60').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 531,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('/pms/counselResultInsertConsultExile',{"callbackFunc":callbackFunc});

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS60").dialog('close');

                });

        }
    });
};


//레이어팝업6-1 : CS_클레임유형선택
function goLayoutPCS61(){
    $('#LayoutPCS61').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1001,
        height : 219,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('/pms/counselResultInsertClaimType');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS61").dialog('close');

                });

        }
    });
};


//레이어팝업6-2 : CS_게시판동시답변
function goLayoutPCS62(){
    $('#LayoutPCS62').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1200,
        height : 738,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('/pms/counselResultInsertCSBoardReply');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS6").dialog('close');

                });

        }
    });
};


//레이어팝업6-3 : CS_이관담당자선택
function goLayoutPCS63(){
    $('#LayoutPCS63').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 700,
        height : 434,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSTransferPersonSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS63").dialog('close');

                });

        }
    });
};


//레이어팝업6-4 : CS_대외기관접수
function goLayoutPCS64(){
    $('#LayoutPCS64').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1000,
        height : 593,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSForeignOrgan.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS64").dialog('close');

                });

        }
    });
};


//레이어팝업8 : CS_호전환/3자통화(스킬/상담원선택)
function goLayoutPCS80(){
    $('#LayoutPCS80').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1000,
        height : 567,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./CSTransferConference.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCS80").dialog('close');

                });

        }
    });
};


//레이어팝업4 : BD_딜상담이력상세조회
function goLayoutPBD40(){
    $('#LayoutPBD40').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 654,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./BDBoardCounselHistoryDetailSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPBD40").dialog('close');

                });

        }
    });
};


//레이어팝업5 : BD_상담이력상세조회
function goLayoutPBD50(){
    $('#LayoutPBD50').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 654,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./BDBoardCounselHistoryDetailSearch_02.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPBD50").dialog('close');

                });

        }
    });
};


//레이어팝업7 : BD_게시판>분배요청
function goLayoutPBD70(){
    $('#LayoutPBD70').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 350,
        height : 175,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./BDBoardDistribution.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPBD70").dialog('close');

                });

        }
    });
};


//레이어팝업0 : PT_추가정보
function goLayoutPPT00(){
    $('#LayoutPPT00').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 563,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTMain_01.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT00").dialog('close');

                });

        }
    });
};


//레이어팝업1 : PT_파트너목록조회
function goLayoutPPT10(){
    $('#LayoutPPT10').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 514,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTPartnerListSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT10").dialog('close');

                });

        }
    });
};


//레이어팝업2 : PT_클레임정보
function goLayoutPPT20(){
    $('#LayoutPPT20').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 700,
        height : 335,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTClaimSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT20").dialog('close');

                });

        }
    });
};


//레이어팝업2-1 : PT_배송정책정보
function goLayoutPPT21(){
    $('#LayoutPPT21').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1200,
        height : 165,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTDeliveryPolicyInfoSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT21").dialog('close');

                });

        }
    });
};


//레이어팝업2-2 : PT_정산정보조회
function goLayoutPPT22(){
    $('#LayoutPPT22').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 700,
        height : 312,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTExactSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT22").dialog('close');

                });

        }
    });
};


//레이어팝업2-3 : PT_공지&frasl;주의사항조회
function goLayoutPPT23(){
    $('#LayoutPPT23').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 518,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTAnnounceSuggestionsSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT23").dialog('close');

                });

        }
    });
};


//레이어팝업4 : PT_딜상담이력상세조회
function goLayoutPPT40(){
    $('#LayoutPPT40').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 605,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTCallCounselHistoryDetailSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT40").dialog('close');

                });

        }
    });
};


//레이어팝업5 : PT_상담이력상세조회
function goLayoutPPT50(){
    $('#LayoutPPT50').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 605,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTCallCounselHistoryDetailSearch_02.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT50").dialog('close');

                });

        }
    });
};


//레이어팝업6 : PT_상담유형조회
function goLayoutPPT60(){
    $('#LayoutPPT60').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 531,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTCounselExileSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT60").dialog('close');

                });

        }
    });
};


//레이어팝업6-1 : PT_클레임유형선택
function goLayoutPPT61(){
    $('#LayoutPPT61').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1001,
        height : 219,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTClaimType.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT61").dialog('close');

                });

        }
    });
};


//레이어팝업6-3 : PT_이관담당자선택
function goLayoutPPT63(){
    $('#LayoutPPT63').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 700,
        height : 434,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./PTTransferPersonSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPPT63").dialog('close');

                });

        }
    });
};


//레이어팝업0 : IS_추가정보
function goLayoutPIS00(){
    $('#LayoutPIS00').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 563,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./ISMain_01.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPIS00").dialog('close');

                });

        }
    });
};


//레이어팝업5 : IS_상담이력상세조회
function goLayoutPIS50(){
    $('#LayoutPIS50').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 590,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./ISCallCounselHistoryDetailSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPIS50").dialog('close');

                });

        }
    });
};


//레이어팝업6 : IS_상담유형조회
function goLayoutPIS60(){
    $('#LayoutPIS60').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 531,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./ISCounselExileSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPIS60").dialog('close');

                });

        }
    });
};


//레이어팝업6-3 : IS_이관담당자선택
function goLayoutPIS63(){
    $('#LayoutPIS63').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 700,
        height : 434,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./ISTransferPersonSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPIS63").dialog('close');

                });

        }
    });
};


//레이어팝업0 : TS_추가정보
function goLayoutPTS00(){
    $('#LayoutPTS00').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 500,
        height : 563,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSMain_01.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTS00").dialog('close');

                });

        }
    });
};


//레이어팝업1 : TS_고객목록조회
function goLayoutPTS10(){
    $('#LayoutPTS10').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1000,
        height : 514,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSCustomerListSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTS10").dialog('close');

                });

        }
    });
};


//레이어팝업5 : TS_상담이력상세조회
function goLayoutPTS50(){
    $('#LayoutPTS50').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 571,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSCallCounselHistoryDetailSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTS50").dialog('close');

                });

        }
    });
};


//레이어팝업6 : TS_상담유형조회
function goLayoutPTS60(){
    $('#LayoutPTS60').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1230,
        height : 531,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSCounselExileSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTS60").dialog('close');

                });

        }
    });
};


//레이어팝업6-1 : TS_클레임유형선택
function goLayoutPTS61(){
    $('#LayoutPTS61').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1001,
        height : 219,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSClaimType.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTS61").dialog('close');

                });

        }
    });
};


//레이어팝업6-3 : TS_이관담당자선택
function goLayoutPTS63(){
    $('#LayoutPTS63').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 700,
        height : 434,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSTransferPersonSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTS63").dialog('close');

                });

        }
    });
};


//레이어팝업1 : TSP_매장목록조회
function goLayoutPTSP10(){
    $('#LayoutPTSP10').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1000,
        height : 514,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSPListSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTSP10").dialog('close');

                });

        }
    });
};


//레이어팝업5 : TSP_상담이력상세조회
function goLayoutPTSP50(){
    $('#LayoutPTSP50').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 605,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('./TSPCallCounselHistoryDetailSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPTSP50").dialog('close');

                });
        }
        ,close:function(){
            $('#LayoutPAD02').empty();
        }
    });
};

//레이어팝업6 : AD_공통상담유형조회
function goLayoutPADConsultExileSearch(option){
    var paramdata = {
            "con_callBackFunction":""
           ,"con_userDeptCode":""
           ,"con_adAdminYn":""
    };
    if(option.callBackFunction  ){paramdata.con_callBackFunction    = option.callBackFunction;}else{alert('콜백함수가 정의되지 않았습니다.');return;}
    if(option.userDeptCode      ){paramdata.con_userDeptCode        = option.userDeptCode;}
    if(option.adAdminYn         ){paramdata.con_adAdminYn           = option.adAdminYn;}else{paramdata.adAdminYn = "N";}

    $('#LayoutAdConsultExileSearch').load('/pms/adConsultExileSearch', paramdata, function(){

        $('#LayoutAdConsultExileSearch').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1230,
            height : 531,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutAdConsultExileSearch").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 트리조회로드
        fnAdConsultExileSearch_treeSearch();
    });
};

//레이어팝업 : AD_코드관리
function goLayoutPAD01(){
    //팝업 가져올 url
    $('#LayoutPAD01').load('/pms/codeList',function(){
        $('#LayoutPAD01').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 728,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD01").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_사용자관리
function goLayoutPAD02(){
    $('#LayoutPAD02').load('/pms/adUserListManage', function(){

        // 공통 권한 셋팅
        fnFuncApply();

        $('#LayoutPAD02').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 792,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD02").dialog('close');
                });
            },
            close:function(){
                $('#LayoutPAD02').empty();
            }
        });
        // 리스트조회로드
       // fcAdUserListManage_listSearch('1');
    });
};


//레이어팝업 : AD_사용자관리>권한보기(사용자권한목록)
function goLayoutPAD04(){
    $('#LayoutPAD04').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1200,
        height : 473,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('../ad/ADUserAuthList.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPAD04").dialog('close');
             });
        }
        ,close:function(){
            $('#LayoutPAD02').empty();
        }
    });
};

//레이어팝업 : AD_사용자관리>상담원조회
function goLayoutPAD05(callbackFunc){
    $('#LayoutPAD05').load('/pms/adUserSearch',{"callbackFunc":callbackFunc}, function(){

        $('#LayoutPAD05').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1000,
            height : 490,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD05").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdUserSearch_listSearch();
    });
};

//레이어팝업 : AD_사용자관리>상담원조회 _ Default 부서지정
function goLayoutPAD05_userDeptCode(callbackFunc,userDeptCode,fixFg){
    $('#LayoutPAD05').load('/pms/adUserSearch',{'callbackFunc':callbackFunc,'con_userDeptCode':userDeptCode,'fixFg':fixFg }, function(){

        $('#LayoutPAD05').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1000,
            height : 490,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD05").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdUserSearch_listSearch();
    });
};

//레이어팝업 : AD_사용자관리>상담원조회(자동배정사용시)
function goLayoutPAD05_AUTO(callbackFunc,dealId){
    $('#LayoutPAD05').load('/pms/adUserSearch',{"callbackFunc":callbackFunc,"autoSetDealId":dealId}, function(){
        $('#LayoutPAD05').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1000,
            height : 622,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD05").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdUserSearch_listSearch();
    });
};


//레이어팝업 : 공통코드(멀티 선택)
function goLayoutPBD71(callbackFunc,codeId){
    $('#LayoutPBD71').load('/pms/adCodePopup?codeId='+codeId,{"callbackFunc":callbackFunc}, function(){
        $('#LayoutPBD71').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 600,
            height : 490,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPBD71").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};

//레이어팝업 : AD_기능별그룹권한관리
function goLayoutPAD06(){
    $('#LayoutPAD06').load('/pms/ADGroupAuthManage', function(){
        $('#LayoutPAD06').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1800,
            height : 572,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD06").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_양식관리
function goLayoutPAD07(){
    //팝업 가져올 url
    $('#LayoutPAD07').load('/pms/commonFormMng',function(){
        $('#LayoutPAD07').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 906,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD07").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_딜별이관담당자관리
function goLayoutPAD08(){
    //팝업 가져올 url
    $('#LayoutPAD08').load('/pms/dealByUserMng',function(){
        $('#LayoutPAD08').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1503,
            height : 568,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD08").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_지역별이관담당자관리
function goLayoutPAD09(){
    //팝업 가져올 url
    $('#LayoutPAD09').load('/pms/categoryByUserMng',function(){
        $('#LayoutPAD09').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1503,
            height : 568,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD09").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_콜백리스트/분배
function goLayoutPAD10(){
    $('#LayoutPAD10').load('/pms/ADOBDistManageCallBack', function(){
        //달력 셋팅
        $( "#regFromDate, #regToDate" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD10').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 550,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD10").dialog('close');
                });
            },close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_고객성향관리
function goLayoutPAD11(){
    $('#LayoutPAD11').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 854,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('../ad/ADTendListSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPAD11").dialog('close');
                });
        }
        ,close:function(){
            $('#LayoutPAD11').empty();
        }
    });
};


//레이어팝업 : AD_클레임관리
function goLayoutPAD12(){
    $('#LayoutPAD12').load('/pms/adClaimListSearch', function(){
        // 권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_startYmd, #con_endYmd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });

        $('#LayoutPAD12').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 789,
            modal : true, //주위를 어둡게
            title: '중대 클레임 관리',
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD12").dialog('close');
                });
            },
            close:function(){
                $('#LayoutPAD12').empty();
            }
        });
        // 리스트조회로드
        fnAdClaimListSearch_listSearch('1');
    });
};


//레이어팝업 : AD_클레임관리>상담결과조회
function goLayoutPAD13(){
    $('#LayoutPAD13').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 518,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('../html/ad/ADClaimListSearch.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPAD13").dialog('close');

                });
        }
        ,close:function(){
            $('#LayoutPAD13').empty();
        }
    });
};


//레이어팝업 : AD_스킬배정(계시판)
function goLayoutPAD14(){
    $('#LayoutPAD14').load('/pms/ADCounselorSkillBoard', function(){
        $('#LayoutPAD14').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 950,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD14").dialog('close');
                });
            },
            close:function(){
                $('#LayoutPAD14').empty();
            }
        });
    });
};


//레이어팝업 : 스킬 배정(콜배정)
function goLayoutPAD15(){
    $('#LayoutPAD15').load('/pms/ADCounselorSkillUser', function(){
        $('#LayoutPAD15').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 950,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD15").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : AD_사용자관리>권한보기(사용자권한목록)>권한관리
function goLayoutPAD16(){
    $('#LayoutPAD16').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 600,
        height : 437,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('../ad/ADUserAuthList_01.html');

            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPAD16").dialog('close');
                });
        }
        ,close:function(){
            $('#LayoutPAD16').empty();
        }
    });
};



//레이어팝업 : AD_아웃콜리스트/분배
function goLayoutPAD17(){
    $('#LayoutPAD17').load('/pms/ADOBDistManageOutCall', function(){
        //달력 셋팅
        $( "#regFromDate, #regToDate" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD17').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1000,
            height : 550,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD17").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
    });
};




//레이어팝업 : AD상담관리_상담이력
function goLayoutPAD19(){
    $('#LayoutPAD19').load('/pms/adCounselHistory', function(){
        // 권한셋팅
        fnFuncApply();
        // 달력 셋팅
        $( "#adCounselHistory_con_interactionStartTime, #adCounselHistory_con_interactionEndTime" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD19').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1700,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD19").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 최초로드 리스트 조회
        fnAdCounselHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_아웃콜이력
function goLayoutPAD20(){
    $('#LayoutPAD20').load('/pms/adOutCallHistory', function(){
        // 권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_regTimeStart, #con_regTimeEnd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD20').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD20").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdOutCallHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_예약이력관리
function goLayoutPAD21(){
    $('#LayoutPAD21').load('/pms/adReserveHistory', function(){
        // 권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_reserveTimeStart, #con_reserveTimeEnd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD21').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD21").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdReserveHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_이관이력관리
function goLayoutPAD22(){
    $('#LayoutPAD22').load('/pms/adTransferHistory', function(){
        // 공통권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_searchPeriodStart, #con_searchPeriodEnd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD22').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1700,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD22").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdTransferHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_상담이력(관리자)
function goLayoutPAD23(){
    $('#LayoutPAD23').load('/pms/adCounselHistory',{"ad_adConYn":"Y"}, function(){
        // 권한셋팅
        fnFuncApply();
        // 달력 셋팅
        $( "#adCounselHistory_con_interactionStartTime, #adCounselHistory_con_interactionEndTime" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD23').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1700,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD23").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 최초로드 리스트 조회
        fnAdCounselHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_아웃콜이력(관리자)
function goLayoutPAD24(){
    $('#LayoutPAD24').load('/pms/adOutCallHistory',{"ad_adConYn":"Y"}, function(){
        // 권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_regTimeStart, #con_regTimeEnd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD24').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD24").dialog('close');
                });
            },close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdOutCallHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_예약 이력 관리(관리자)
function goLayoutPAD25(){
    $('#LayoutPAD25').load('/pms/adReserveHistory',{"ad_adConYn":"Y"}, function(){
        // 권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_reserveTimeStart, #con_reserveTimeEnd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD25').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD25").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdReserveHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_이관이력관리(관리자)
function goLayoutPAD26(){
    $('#LayoutPAD26').load('/pms/adTransferHistory',{"ad_adConYn":"Y"}, function(){
        // 공통권한적용
        fnFuncApply();
        // 달력 셋팅
        $( "#con_searchPeriodStart, #con_searchPeriodEnd" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            //maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD26').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1700,
            height : 700,
            modal : true, //주위를 어둡게
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD26").dialog('close');
                });
            },
            close:function(){
                $(this).empty();
            }
        });
        // 리스트조회로드
        fnAdTransferHistory_listSearch('1');
    });
};


//레이어팝업 : AD상담관리_파트너등록관리
function goLayoutPAD27(){
    //팝업 가져올 url
    $('#LayoutPAD27').load('/pms/viewPartner', function(){
        //달력 셋팅
        $( "#frstRegFromDate, #frstRegToDate" ).datepicker({
            showOn: "button",
            buttonImage: " /pms/images/common/ico_calendar.gif",
            buttonImageOnly: true,
            maxDate:0, //오늘 날짜까지만 선택하도록
            prevText: "이전",
            nextText: "다음",
            dateFormat: "yy-mm-dd",
            dayNamesMin:["일","월","화","수","목","금","토"],
            monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeMonth: true,
            changeYear: true
        });
        $('#LayoutPAD27').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 876,
            modal : true, //주위를 어둡게
            close: function(ev, ui) { $(this).remove(); },
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD27").dialog('close');
                });
            },close:function(){
                $('#LayoutPAD27').empty();
            }
        });
    });
};


//레이어팝업 : AD상담관리_업종별/유사지역 성공사례
function goLayoutPAD28(){
    //팝업 가져올 url
    $('#LayoutPAD28').load('/pms/viewSuccessExam',function(){
        $('#LayoutPAD28').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
            width : 1500,
            height : 900,
            modal : true, //주위를 어둡게
            close: function(ev, ui) { $(this).remove(); },
            open:function(){
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#LayoutPAD28").dialog('close');
                });
            },close:function(){
                $(this).empty();
            }
        });
    });
};


//레이어팝업 : 지식관리_게시판
function goLayoutPCT01(){
    $('#LayoutPCT01').load('board/ctBoardKnowledgeSearch', function(data){
    	fnKnowSearchList('1');
    $('#LayoutPCT01').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 720,
        modal : true, //주위를 어둡게

        open:function(){
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCT01").dialog('close');

            });
        },close:function(){
        $('#LayoutPCT01').empty();
        }
        });
        });
        };


//레이어팝업 : CT_딜별공지사항조회
function goLayoutPCT02(){
    $('#LayoutPCT02').load('board/ctBoardDealSearch', function(){
    $('#LayoutPCT02').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 750,
        modal : true, //주위를 어둡게

        open:function(){
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCT02").dialog('close');

            });
        },close:function(){
        $('#LayoutPCT02').empty();
        }
        });
        });
        };


//레이어팝업 : 부서별 게시판
function goLayoutPCT03(){
    $('#LayoutPCT03').load('board/ctBoardListSearch', function(){
    	/*fnBoardSearchList("1");*/
    $('#LayoutPCT03').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 1500,
        height : 893,
        modal : true, //주위를 어둡게

        open:function(){
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#LayoutPCT03").dialog('close');

            });
        },close:function(){
        $('#LayoutPCT03').empty();
        }
        });
        });
        };

//레이어팝업5 : CS_상담이력상세조회
function goLayoutPCS50(strDeptCd, strCrtNo){
$('#LayoutPCS50').dialog({
resizable : false, //사이즈 변경 불가능
draggable : true, //드래그 불가능
closeOnEscape : true, //ESC 버튼 눌렀을때 종료

width : 1500,
height : 639,
modal : true, //주위를 어둡게

open:function(){
//팝업 가져올 url
$(this).load('/pms/mainCounselView', {deptCode:strDeptCd, crtNo:strCrtNo});
$(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
$("#LayoutPCS50").dialog('close');

});

}
});
};





//레이어팝업 : AD상담관리_파트너등록관리
function goLayoutBATCHADMIN(){
  //팝업 가져올 url
  $('#LayoutBATCHADMIN').load('/pms/batchAdmin', function(){
      //달력 셋팅
      $( "#fromDate, #toDate" ).datepicker({
          showOn: "button",
          buttonImage: " /pms/images/common/ico_calendar.gif",
          buttonImageOnly: true,
          maxDate:0, //오늘 날짜까지만 선택하도록
          prevText: "이전",
          nextText: "다음",
          dateFormat: "yy-mm-dd",
          dayNamesMin:["일","월","화","수","목","금","토"],
          monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
          monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          changeMonth: true,
          changeYear: true
      });
      $('#LayoutBATCHADMIN').dialog({
          resizable : false, //사이즈 변경 불가능
          draggable : true, //드래그 불가능
          closeOnEscape : true, //ESC 버튼 눌렀을때 종료
          width : 1500,
          height : 876,
          modal : true, //주위를 어둡게
          close: function(ev, ui) { $(this).remove(); },
          open:function(){
              $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                  $("#LayoutBATCHADMIN").dialog('close');
              });
          },close:function(){
              $('#LayoutBATCHADMIN').empty();
          }
      });
  });
};



// HSH : 녹취파일 재생 (AD 관리자쪽 공통사용)
function fnAdRecordKeyPlay(key){
    //alert(key);
    if(key==undefined || key==''){
        return;
    }
    $.ajax({
        type: "POST",
        url:  "/pms/adGetRecordStream",
        data:{"URID":key},
        dataType:'json',
        success: function(result) {
            //alert(result.recordUrl);
            try {
                 var objWSH = new ActiveXObject("WScript.Shell");
                 var retval = objWSH.Run("wmplayer " + result.recordUrl, 1, false);
                 if (retval != 0) {
                     alert('녹취재생에 실패했습니다.');
                 }
            } catch (e) {
                  alert("도구 > 인터넷 옵션 > 보안 > 사용자 지정 수준 클릭 > 스크립팅하기 안전하지 않은 것으로 표시된 ActiveX컨트롤 초기화 및 스크립팅을 확인으로 변경하세요.\n");
            }
        },
        error: function(){
            alert('녹취재생에 실패하였습니다.');
        }
    });
};

var commonDimedTimer;
// HSH : 공통 딤처리용 START
function commonDim(action,checktime){
    checktime = checktime || 60000;
    if (action){
        $("#CommonDimDiv").eq(0).dimBackground();
        commonDimTimeout(true,checktime);
    }else{
        commonDimTimeout(false);
        $('.commonDimedClass').remove();
        //$("#CommonDimDiv").eq(0).undim();
    }
}

function commonDimTimeout(action,checktime){
    checktime = checktime || 60000;
    if(action){
        clearTimeout(commonDimedTimer);
        commonDimedTimer = setTimeout(function(){
            if(confirm('작업이 지연되고 있습니다.\n\n대기상태를 해제하시겠습니까?')){
                commonDimTimeout(false);
                $('.commonDimedClass').remove();
                //$('#dimedLoadingDiv').remove();
            }else{
                if($('.commonDimedClass').length > 0){
                    commonDimTimeout(true,checktime);
                }else{
                    clearTimeout(commonDimedTimer);
                }
            }
        },checktime);
    }else{
        clearTimeout(commonDimedTimer);
    }
}

/**
 *  Usage: $("<selector>").dimBackground([options] [, callback]);
 *
 *  @author Andy Wermke <andy@dev.next-step-software.com>
 *
 */
(function( $ ) {
    var dimmedNodes = [];     /// [ {$curtain: ?, $nodes: jQueryArray} ]

    /**
     *  Dim the whole page except for the selected elements.
     *  @param options
     *      Optional. See `$.fn.dimBackground.defaults`.
     *  @param callback
     *      Optional. Called when dimming animation has completed.
     */
    $.fn.dimBackground = function (options, callback) {
        var params = parseParams(options, callback);
        options  = params.options;
        callback = params.callback;

        options = $.extend({}, $.fn.dimBackground.defaults, options);

        // Initialize curtain
        var $curtain = $('<div class="dimbackground-curtain commonDimedClass" id="dimedLoadingDiv"></div>');
        var $loding  = $('<image src="/pms/images/common/loading.gif" class="commonDimedClass" id="dimedLoadingImg"/>');

        $curtain.css({
            position:   'fixed',
            left:       0,
            top:        0,
            width:      '100%',
            height:     '100%',
            background: 'black',
            opacity:    0,
            zIndex:     options.curtainZIndex
        });

        // 중앙처리
        var iLeft = ( $(window).scrollLeft() + ($(window).width()  - $loding.width())  / 2 );
        var iTop  = ( $(window).scrollTop()  + ($(window).height() - $loding.height()) / 2 );
        $loding.css({
            position:   'absolute',
            left:       iLeft,
            top:        iTop,
            zIndex:     9999
        });

        $('body').eq(0).append($curtain);
        $('body').eq(0).append($loding);

        // Top elements z-indexing
        this.each(function(){
            var $this = $(this);
            var opts = $.meta ? $.extend( {}, options, $this.data() ) : options;

            this._$curtain = $curtain;
            this._originalPosition = $this.css('position').toLowerCase();
            if(this._originalPosition != "absolute" && this._originalPosition != "fixed") {
                $this.css('position', 'relative');
            }

            this._originalZIndex = $this.css('z-index');
            if(this._originalZIndex == "auto" || this._originalZIndex <= opts.curtainZIndex) {
                $this.css('z-index', opts.curtainZIndex+1);
            }
        });

        $curtain.stop().animate({opacity: options.darkness}, options.fadeInDuration, callback);
        dimmedNodes.push( {$curtain: $curtain, $nodes: this} );
        return this;
    };

    // Plugin default options
    $.fn.dimBackground.defaults = {
        darkness        : 0,     // 0 means no dimming, 1 means completely dark
        fadeInDuration  : 0,      // in ms
        fadeOutDuration : 0,      // in ms
        curtainZIndex   : 9998
    };

    /**
     *  Undo the dimming.
     *  @param options
     *      Optional. See `$.fn.dimBackground.defaults`.
     *  @param callback
     *      Optional. Called when "undimming" animation has completed.
     */
    $.fn.undim = function (options, callback) {
        var params = parseParams(options, callback);
        options  = params.options;
        callback = params.callback;
        options = $.extend({}, $.fn.dimBackground.defaults, options);

        var $curtain;
        var nodeZIndexMap = [];     /// [ [node, originalPosition, originalZIndex], ... ]
        this.each(function() {
            var $this = $(this);
            var opts = $.meta ? $.extend( {}, options, $this.data() ) : options;

            if(this._$curtain) {
                if(!$curtain) {
                    $curtain = this._$curtain;
                }
                if(typeof this._originalPosition != "undefined") {
                    nodeZIndexMap.push( [this, this._originalPosition, this._originalZIndex] );
                }
                this._$curtain = undefined;
                this._originalPosition = undefined;
                this._originalZIndex = undefined;
            }
        });

        if($curtain) {
            $curtain.animate({opacity: 0}, options.fadeOutDuration, function() {
                for(var i=0; i<nodeZIndexMap.length; i++) {
                    var node = nodeZIndexMap[i][0],
                        position = nodeZIndexMap[i][1],
                        zIndex = nodeZIndexMap[i][2];
                    $(node).css({
                        position: position,
                        zIndex: zIndex
                    });
                }
                $('.commonDimedClass').remove();
                /*
                $curtain.remove();
                $('#dimedLoadingImg').remove();
                $('#dimedLoadingDiv').remove();
                callback();
                */
            });

            var match;
            for(var i=0; i<dimmedNodes.length; i++) {
                var entry = dimmedNodes[i];
                if(entry.$curtain == $curtain) {
                    match = i;
                    break;
                }
            }
            if(match) {
                dimmedNodes = dimmedNodes.slice(0, i).concat( dimmedNodes.slice(i+1) );
            }
        }
        return this;
    };

    /**
     *  Undim all dimmed elements.
     *  @param callback
     *      Optional. Called when all animations have completed.
     */
    $.undim = function (callback) {
        callback = typeof callback == "function" ? callback : function() {};
        _dimmedNodes = dimmedNodes.slice();

        var completed = 0, total = _dimmedNodes.length;
        for(var i=0; i<dimmedNodes.length; i++) {
            _dimmedNodes[i].$nodes.undim(done);
        }

        if(total == 0) {
            callback();
        }

        function done () {
            completed++;
            if(completed == total) {
                callback();
            }
        }
    };

    /// @return {options:object, callback:function}
    function parseParams (options, callback) {
        if(typeof options == "function") {
            callback = options;
            options = {};
        }
        if(typeof options != "object") {
            options = {};
        }
        if(typeof callback != "function") {
            callback = function() {};
        }

        return {
            options     : options,
            callback    : callback
        };
    }
}( jQuery ));
//HSH : 공통 딤처리용 END


//클릭 시 리스트 : 소프트폰 업무,이석,보류 전용(동시에 표시되지 못하도록 처리하기 위해 따로 분리함. By dsKwak)
// id: this, id2: 내가 보여지면 숨겨질 녀석
function showListMain(id, id2){
    //클릭시 해당 ID 값 가져와서 현재 hidden_obj의 class가 있는지 체크
    var check = $('#'+id).hasClass("hidden_obj");
    var check2 = $('#'+id2).hasClass("hidden_obj");

    //현재 안보여지고 있을때 hidden_obj 삭제
    if(check ==  true){
        $('#'+id).removeClass("hidden_obj");
        if(!check2) $('#'+id2).addClass("hidden_obj");

    //현재 보여지고 있을때 hidden_obj 추가
    }else{
        $('#'+id).addClass("hidden_obj");
    }

};




//JJS : 메뉴권한 적용 (공통사용)
function fnMenuApply(){
     $.ajax({
            type: "POST",
            url:  "/pms/authMenuList",
            dataType:'json',
            success: function(result) {

                var authCode;
                var authArr = result.authList.split('|');
                for (i=0;i<authArr.length;i++){
                 authCode =  document.getElementById(''+authArr[i]);
                 if(authCode!=undefined){
                	 authCode.style.display="block";
                 }
                }
            },
            error: function(){
                alert('메뉴권한 조회 실패');
            }
        });
    };



    // smjung 20140401: 디자인 적용후 삭제
    var strInline = "S0501^S0502^S0503^S0601^S0602^S0603^S1001^S1002^S1101^S1102^S0801^S0802^S0803^S0804^S0301^S0303^S0302^S0701^S0702^S0703^S0901^S0902^S1301^S1302^S0206";

    //JJS : 기능권한 적용 (공통사용)
    function fnFuncApply(){
    	var strBlock = "block";

         $.ajax({
                type: "POST",
                url:  "/pms/authFuncList",
                dataType:'json',
                success: function(result) {

                    var funcCode;
                    var funcArr = result.funcList.split('|');

                    for (i=0;i<funcArr.length;i++){
                        //funcCode =  document.getElementById(''+funcArr[i]);
                        funcCode = $("[id^='"+funcArr[i]+"']");
                        if(funcCode.length>0){
                            for(r=0;r<funcCode.length;r++){
                            	//alert(funcCode[r].id);

                            	// smjung 20140401: 디자인 적용후 삭제
                                if( strInline.indexOf(funcCode[r].id) > -1 ) {  strBlock ="inline"; }else { strBlock = "block" }

                                funcCode[r].style.display=strBlock;
//                                funcCode[r].style.display="inline";
                            }
                        }
                     //if(funcCode!=undefined){ funcCode.style.display='block';}
                    }

                },
                error: function(){
                    alert('기능권한 조회 실패');
                }
            });
        };
	    /** 
	     * input 숫자와 콤마만 입력되게 하기.
	     * include js : jquery.number.js
	     * input 속성에 numberOnly 추가
	     * jsp : <input type="text" id="amount" name="amount" numberOnly placeholder="0" />
	     * $(this).number(true);
	     * $.number( 5020.2364 );				// Outputs 5,020
	     * $.number( 5020.2364, 2 );			// Outputs: 5,020.24
	     * $.number( 135.8729, 3, ',' );		// Outputs: 135,873
	     * $.number( 5020.2364, 1, ',', ' ' );	// Outputs: 5 020,2 
	     */
	    $(document).on("keyup", "input:text[numberOnly]", function() {
	    	$(this).number(true);
	    });
	    /**
	     * 숫자에서 comma를 없앤다.
	     *
	     * @param   obj
	     */
	    function deleteComma(obj) {
	        obj.value = deleteCommaStr(obj.value);
	    }
	    /**
	     * 숫자에서 comma를 없앤다.
	     *
	     * @param   str
	     */
	    function deleteCommaStr(str) {
	        var temp = '';

	        for (var i = 0; i < str.length; i++) {
	            if (str.charAt(i) == ',') {
	                continue;
	            } else {
	                temp += str.charAt(i);
	            }
	        }

	        return  temp;
	    }
		  /**
	     * 숫자에 comma를 붙인다.
	     *
	     * @param   str
	     */
	    function addCommaStr(str) {
	        var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
	        var arrNumber = str.split('.');
	        arrNumber[0] += '.';
	        do {
	            arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
	        } while (rxSplit.test(arrNumber[0]));

	        if (arrNumber.length > 1) {
	            replaceStr = arrNumber.join("");
	        } else {
	            replaceStr = arrNumber[0].split(".")[0];
	        }
	        return replaceStr;
	    }
	    /**
	     * 입력값의 null check
	     *
	     * @param   str
	     */
		  function isnullStr(val){

			  if(val=='NaN' || val=='' || val==null){
				  
				  return 0;
			  }
			  return val
			  
		  }
		  function setCookie( name, value ){
				var todayDate = new Date();
				todayDate.setDate( todayDate.getDate() + (60*60*24*365));
				document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
			}
			
			function setCookieOne( name, value ){
				var todayDate = new Date();
				todayDate.setDate( todayDate.getDate() + (60*60*24));
				document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
			}
			
			function getCookie(name){
				var coki;
				var idx = document.cookie.indexOf(name+'=');
				
				if (idx != -1) {
					idx += name.length + 1;
					to_idx = document.cookie.indexOf(';', idx);
					
					if (to_idx == -1) {
						to_idx = document.cookie.length;
					}		
					coki = document.cookie.substring(idx, to_idx);
				} else {
				 coki = "";
				}
				return coki;
			}
		    /**
		     * trim
		     *
		     * @param   text
		     * @return  string
		     */
		    function trim(text) {
		    	if (text == "") {
		            return  text;
		        }

		        var len = text.length;
		        var st = 0;

		        while ((st < len) && (text.charAt(st) <= ' ')) {
		            st++;
		        }

		        while ((st < len) && (text.charAt(len - 1) <= ' ')) {
		            len--;
		        }

		        return  ((st > 0) || (len < text.length)) ? text.substring(st, len) : text;
		    }
		    
		    /**
		     *  파일 확장자명 체크
		     *
		     **/
		    function isImageFile( obj ) {
		    	var strIdx = obj.lastIndexOf( '.' ) + 1;
		    	if ( strIdx == 0 ) {
		    		return false;
		    	} else {
		    		var ext = obj.substr( strIdx ).toLowerCase();
		    		if ( ext == "xls") {
		    			return true;
		    		} else {
		    			alert(ext+'파일은 전송이 불가능합니다.');
		    			return false;
		    		}
		    	}
		    }
		  //textarea maxlength 지정하기
		    function textLimit(obj){
		        var maxLength = parseInt(obj.getAttribute("maxlength"));
		        if(obj.value.length > maxLength){
		            alert(maxLength + "자 이하로 입력하세요");
		            obj.value = obj.value.substring(0,maxLength);
		        }
		    }
		    
		    function fillSpace(val){

		    	if(typeof val =="undefined" || val == null){
		    		return ' ';
		    	}
		    	if(val.length==0){
		    		return ' ';
		    	}

		        return val;
		    }
		    
		    
		    
          ///////////////////////////////////////////////////////////////////////////////
		  //�Լ��� :MM_swapImgRestore(),MM_preloadImages,MM_findObj,MM_swapImage
		  //��  �� : �̹��� ȿ���Լ�.
		  ///////////////////////////////////////////////////////////////////////////////
		  function MM_swapImgRestore() { //v3.0
		    var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		  }
		  function MM_preloadImages() { //v3.0
		    var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		      var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		      if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		  }

		  function MM_findObj(n, d) { //v4.01
		    var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
		      d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		    if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		    for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		    if(!x && d.getElementById) x=d.getElementById(n); return x;
		  }

		  function MM_swapImage() { //v3.0
		    var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
		     if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		  }
		  function MM_nbGroup(event, grpName) { //v6.0
		  	  var i,img,nbArr,args=MM_nbGroup.arguments;
		  	  if (event == "init" && args.length > 2) {
		  	    if ((img = MM_findObj(args[2])) != null && !img.MM_init) {
		  	      img.MM_init = true; img.MM_up = args[3]; img.MM_dn = img.src;
		  	      if ((nbArr = document[grpName]) == null) nbArr = document[grpName] = new Array();
		  	      nbArr[nbArr.length] = img;
		  	      for (i=4; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {
		  	        if (!img.MM_up) img.MM_up = img.src;
		  	        img.src = img.MM_dn = args[i+1];
		  	        nbArr[nbArr.length] = img;
		  	    } }
		  	  } else if (event == "over") {
		  	    document.MM_nbOver = nbArr = new Array();
		  	    for (i=1; i < args.length-1; i+=3) if ((img = MM_findObj(args[i])) != null) {
		  	      if (!img.MM_up) img.MM_up = img.src;
		  	      img.src = (img.MM_dn && args[i+2]) ? args[i+2] : ((args[i+1])? args[i+1] : img.MM_up);
		  	      nbArr[nbArr.length] = img;
		  	    }
		  	  } else if (event == "out" ) {
		  	    for (i=0; i < document.MM_nbOver.length; i++) {
		  	      img = document.MM_nbOver[i]; img.src = (img.MM_dn) ? img.MM_dn : img.MM_up; }
		  	  } else if (event == "down") {
		  	    nbArr = document[grpName];
		  	    if (nbArr)
		  	      for (i=0; i < nbArr.length; i++) { img=nbArr[i]; img.src = img.MM_up; img.MM_dn = 0; }
		  	    document[grpName] = nbArr = new Array();
		  	    for (i=2; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {
		  	      if (!img.MM_up) img.MM_up = img.src;
		  	      img.src = img.MM_dn = (args[i+1])? args[i+1] : img.MM_up;
		  	      nbArr[nbArr.length] = img;
		  	  } }
		  	}
		  function MM_showHideLayers() { //v9.0
		  	  var i,p,v,obj,args=MM_showHideLayers.arguments;
		  	  for (i=0; i<(args.length-2); i+=3) 
		  	  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
		  	    if (obj.style) { obj=obj.style; v=(v=='show')?'block':(v=='hide')?'none':v; }
		  	    obj.display=v; }
		  	}
		  
		  function addDateFormat(obj) {
	
		        var value = obj.value;
		       
		        if (trim(value) == "") {
		            return;
		        }

		        value = deleteDateFormatStr(value);
	
		        if (!isDate(value)) {
		            dispName = obj.getAttribute("dispName");

		            if (dispName == null) {
		                dispName = "";
		            }

		            alert(dispName + " 형식이 올바르지 않습니다.");
		            obj.value='';
		            obj.focus();

		            return;
		        }

		        obj.value = addDateFormatStr(value);
	    }
	    function addDateFormatStr(str) {
	
	        return  str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
	    }
	    function deleteDateFormatStr(str) {
	 
	        var temp = '';

	        for (var i = 0; i < str.length; i++) {
	            if (str.charAt(i) == '-') {
	                continue;
	            } else {
	                temp += str.charAt(i);
	            }
	        }

	        return  temp;
	    }
		    
	  function onlyNum(val)
	  {
	
	   var num = val;
	   var tmp = "";

	   for (var i = 0; i < num.length; i ++)
	   {
	    if (num.charAt(i) >= 0 && num.charAt(i) <= 9)
	     tmp = tmp + num.charAt(i);
	    else
	     continue;
	   }
	   return tmp;
	  }
	  
	  function onlyNumber(obj){

	    	var num = obj.value;

	    	var pattern = /\D/gi;

	    	if( pattern.test(num)==true){

	    	obj.value = num.replace(/\D/gi, "");

	    	obj.focus();

	    	}
	  }
	  /**
	     * trim
	     *
	     * @param   text
	     * @return  string
	     */
	    function trim(text) {
	    	if (text == "") {
	            return  text;
	        }

	        var len = text.length;
	        var st = 0;

	        while ((st < len) && (text.charAt(st) <= ' ')) {
	            st++;
	        }

	        while ((st < len) && (text.charAt(len - 1) <= ' ')) {
	            len--;
	        }

	        return  ((st > 0) || (len < text.length)) ? text.substring(st, len) : text;
	    }
      ///////////////////////////////////////////////////////////////////////////////
	  //함수명 :dateCheck()
	  //내  용 : 날짜체크함수
	  ///////////////////////////////////////////////////////////////////////////////
	  function dateCheck(sObj,eObj,due){
	  	
	  	var sdate=deleteDateFormatStr(sObj.value);
	  	var edate=deleteDateFormatStr(eObj.value);
	  	
	  	if(!isDate(sdate)){
	  		alert('검색 시작일자의 날짜형식이 올바르지 않습니다.');
	  		return false;
	  	}
	  	if(!isDate(edate)){
	  		alert('검색 종료일자의 날짜형식이 올바르지 않습니다.');
	  		return false;
	  	}
	  	
	  	if(sdate>edate){
	  		alert('시작일이 종료일보다 큽니다.');
	  		return false;
	  	}
	   
	  	var rdue=daysBetween(sdate,edate);
	  	
//	  	if(due!= '' && 365<rdue){
//	  		alert('검색일수는 12개월로 제한합니다.');
//	  		return false;
//	  	} else if(due!='' && rdue>due){
	  	if(due!='' && rdue>due){
	  		if( due == '1' )
	  			alert( '검색일수는 당일로 제한됩니다.' );
	  		else
	  			alert('검색일수는 '+(due-1)+'일로 제한합니다.');
	      	return false;
	  	}	
	  	return true;
	  }
	  /**
	     * 날짜 체크
	     *
	     * @param   date
	     * @return  boolean
	     */
	    function isDate(date) {
	        if (date == null || date.length != 8) {
	            return  false;
	        }

	        if (!isNumber(date)) {
	            return  false;
	        }

	        var year = eval(date.substring(0, 4));
	        var month = eval(date.substring(4, 6));
	        var day = eval(date.substring(6, 8));

			if(year == "0000") {
				return false;
			}

	        if (month > 12 || month == "00") {
	            return  false;
	        }

	        var totalDays;

	        switch (eval(month)){

	            case 1 :
	                totalDays = 31;
	                break;
	            case 2 :
	                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
	                    totalDays = 29;
	                else
	                    totalDays = 28;
	                break;
	            case 3 :
	                totalDays = 31;
	                break;
	            case 4 :
	                totalDays = 30;
	                break;
	            case 5 :
	                totalDays = 31;
	                break;
	            case 6 :
	                totalDays = 30;
	                break;
	            case 7 :
	                totalDays = 31;
	                break;
	            case 8 :
	                totalDays = 31;
	                break;
	            case 9 :
	                totalDays = 30;
	                break;
	            case 10 :
	                totalDays = 31;
	                break;
	            case 11 :
	                totalDays = 30;
	                break;
	            case 12 :
	                totalDays = 31;
	                break;
	        }

	        if (day > totalDays) {
	            return  false;
	        }

	        if (day == "00") {
	            return  false;
	        }

	        return  true;
	    }
	    /**
	     * 오직 숫자로만 이루어져 있는지 체크 한다.
	     *
	     * @param   num
	     * @return  boolean
	     */
	    function isNumber(num) {
	        re = /[0-9]*[0-9]$/;

	        if (re.test(num)) {
	            return  true;
	        }

	        return  false;
	    }
	    /**
	     * 두 날짜간의 일자를 리턴
	     *
	     * parameter date: JavaScript Date Object
	     */
	    function daysBetween(fromDt, toDt) {
	    
	        var date1 = toTimeObject(fromDt);
	        var date2 = toTimeObject(toDt);

	        var DSTAdjust = 0;
	        // constants used for our calculations below
	        oneMinute = 1000 * 60;
	        var oneDay = oneMinute * 60 * 24;
	        // equalize times in case date objects have them
	        date1.setHours(0);
	        date1.setMinutes(0);
	        date1.setSeconds(0);
	        date2.setHours(0);
	        date2.setMinutes(0);
	        date2.setSeconds(0);
	        // take care of spans across Daylight Saving Time changes
	        if (date2 > date1) {
	            DSTAdjust = 
	                (date2.getTimezoneOffset( ) - date1.getTimezoneOffset( )) * oneMinute;
	        } else {
	            DSTAdjust = 
	                (date1.getTimezoneOffset( ) - date2.getTimezoneOffset( )) * oneMinute;    
	        }
	        var diff = Math.abs(date2.getTime( ) - date1.getTime( )) - DSTAdjust;
	        //alert(Math.floor(diff/oneDay)+1);
	        return Math.floor(diff/oneDay)+1;
	    }
	    /**
	     * Time 스트링을 자바스크립트 Date 객체로 변환
	     *
	     * parameter time: Time 형식의 String
	     */
	    function toTimeObject(time)
	    { //parseTime(time)
	        var year  = time.substr(0,4);
	        var month = time.substr(4,2) - 1; // 1월=0,12월=11
	        var day   = time.substr(6,2);
	        var hour  = time.substr(8,2);
	        var min   = time.substr(10,2);

	        return new Date(year,month,day,hour,min);
	    }

