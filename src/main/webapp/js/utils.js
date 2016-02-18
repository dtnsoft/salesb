// 전화번호 포맷으로 변경.
function setPhoneFormat(strValue){
	if(strValue == '') return "";

	var strTemp = strValue.replace(/\-/g,"");
	if(strValue.substr(0,1) == "0"){
		var oRegExp = /(\d{2,3})(\d{3,4})(\d{4})/;
		if(strTemp.length >= 2){
			if(strTemp.substring(0,2) == "02")
				oRegExp = /(\d{2})(\d{3,4})(\d{4})/;
		}
		return(strTemp.replace(oRegExp, "$1-$2-$3"));
	}else{
		var oRegExp = /(\d{3,4})(\d{4})/;
		return(strTemp.replace(oRegExp, "$1-$2"));
	}
}


/**
	 * 기능 : 텍스트 전체선택
	 * 파라미터 : strObj (객체명)
	 */
	function selectText(obj){
//		var obj = document.getElementById(strObj);
		obj.focus();

		if (document.all) {	//IE
			obj.select();
		} else {	//기타
			obj.selectionStart = 0;
			obj.selectionEnd = obj.value.length;
		}
	}

	/**
	 * 기능 : 숫자만 입력되었는지 체크
	 * 파라미터 :
	 * 반환값 : true/false
	 */
	function onlyNumber() {
		if( (event.keyCode<48)||(event.keyCode>57))
			event.returnValue=false;
	}

	/**
	 * 기능 : YYYYMMDD형식의 날짜를 YYYY-MM-DD로 변경
	 *        날짜입력시 '-'넣기와 숫자를 제외한 문자빼기 함수()
	 * 파라미터 : obj
	 * 반환값 : N/A
	 *
	 * 사용예 :
	 * 1. <input name="stDd" type="text" class="input" id="stDd" maxLength="10" size="10" onkeyUp="dateFormat(this);">
	 */
	function dateFormat( obj )
	{
		with( obj ){                              // 입력된값중 포함된 문자있으면 backspace
			var a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~`!@#$%^&*()_+|}{\":?><=-\\][';/.,";
			for( i = 0; i < a.length; i++){
				if( obj.value.substr( obj.value.length - 1 , obj.value.length ) == a.charAt(i) ){
					obj.value = obj.value.substr( 0, obj.value.length - 1 );
				}
			}
		}
		var change,cnt;
		change = obj.value;
		cnt = change.length;
		var returnValue = false;

		if(cnt == 4 ){                             //자릿수에 맞추어 '-' 넣기
			obj.value = obj.value + "-";
		}
		if(cnt == 7 ){                             //자릿수에 맞추어 '-' 넣기
			obj.value = obj.value + "-";
		}

		if(cnt == 10){                             //입력한 날짜가 유효한지 검사
			if (!ValidateDate(obj)) {
				obj.value = "";
				obj.focus();
			}
		    return returnValue;
		}

		if( event.keyCode == 8 && cnt == 9  ){		// 일자를 지우고 '-'넣어줌
			obj.value = obj.value.substr( 0, obj.value.length - 2 )+"-";
		}else if( event.keyCode == 8 && cnt == 7  ){// 월을지움
			obj.value = obj.value.substr( 0, obj.value.length - 3 );
		}else if( event.keyCode == 8 ){				//년도지움
			obj.value = "";
		}
	}

	/*
	 * 숫자를 제외한 문자 제거
	 */
	function extractNumber(str) {

		var strDate = "";
		var ch = "";
		for (var i = 0; i < str.length; i++) {
			ch = str.charAt(i);
			if (ch >= "0" && ch <= "9") {
				strDate = strDate + ch;
			}
		}
		return strDate;
	}

	/**
	 * 유효날짜 체크
	 * 입력값 form.argument(예 :20110101 또는 2011-01-01)
	 * 사용예: ValidateDate(form.argument);
	 * 반환값 : true, flase
	 */
	function ValidateDate(obj) {
		var Date = obj.value;
		if (Date != null){
		Date = replaceChar(Date,'-','')
		}
		var daysInMonth = new Array("31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31");

		var lvYear = "";
		var lvMonth = "";
		var lvDay = "";

		lvYear = Date.substring(0,4);
		lvMonth = Date.substring(4,6);
		lvDay = Date.substring(6,8);

		if(eval(lvYear)%4 == 0) daysInMonth[1] = "29";
		else daysInMonth[1] = "28";

		if(eval(lvDay) > 0 && eval(lvDay) <= eval(daysInMonth[eval(lvMonth)-1])&&Date.length == 8) {
			return true;
		} else {
			alert("[" + obj.value + '] 은  유효한 일자가 아닙니다.');
			obj.focus();
			return false;
		}
	}

	/**
	 * 기능 : 문자열의 특정 문자를 원하는 문자로 대치.
	 * 파라미터 : str - 대상 문자열
	 *           src 문자가 trgt 문자로 대치됨.
	 * 반환값 : 변경된 문자열
	 * ------------------------------------------------------------------------------------------
	 * 사용예 : replaceChar("2011-01-01",'-',''); 반환값은 20110101
	 * ------------------------------------------------------------------------------------------
	 */
	function replaceChar(str,src,trgt) {

		var value = "";
		var ch;
		for ( var i = 0; i < str.length; i++ ) {

			var ch = str.charAt(i);
			if ( ch == src ) {
				value = value + trgt;
			}
			else {
				value = value + ch;
			}
		}
		return value;
	}


	function getPopupSetting(w,h,scroll,pos) {

		if(pos=="random") {
			LeftPosition=(screen.width)?Math.floor(Math.random()*(screen.width-w)):100;
			TopPosition=(screen.height)?Math.floor(Math.random()*((screen.height-h)-75)):100;
		}
		if(pos=="center") {
			LeftPosition=(screen.width)?(screen.width-w)/2:100;
			TopPosition=(screen.height)?(screen.height-h)/2:100;
		}
		else if((pos!="center" && pos!="random")) {
			LeftPosition=0;
			TopPosition=20;
		}

		if(scroll==null) scroll = 'no';

		return 'width='+w+',height='+h+
			 ',top='+TopPosition+
			 ',left='+LeftPosition+
			 ',scrollbars='+scroll+
			 ',location=no,directories=no,status=yes,menubar=no,toolbar=no,resizable=no';
	}

	function now() {

		var now = new Date();
		var year = now.getFullYear();
		var month = to2Char(now.getMonth()+1);
		var date = to2Char(now.getDate());
		var hour = to2Char(now.getHours());
		var min = to2Char(now.getMinutes());
		var sec = to2Char(now.getSeconds());

		return ""+year+month+date+"_"+hour+min+sec;
	}

	function to2Char(ch) {
		if (ch.length < 2) {
			return "0" + nMonth ;
		}
		return ch;
	}



	/**
	 * 영문과 숫자만으로 되어있는지 체크
	 */
	function checkValue(strValue)
	{
		var reValue =true;
		var strReg = /^[A-Za-z0-9]+$/;
		if (!strReg.test(strValue) ){
			//alert('영문과 숫자만 입력가능합니다.');
			//return false;
			reValue = false;
		}
		return reValue;
	}

	/**
	 * 숫자만으로 되어있는지 체크
	 */
	function checkNum(strValue){
		var flag=true;
		for(var i=0;i<strValue.length;i++){
			c = strValue.charAt(i);
			if(!(c>='0' && c<='9')){
				flag=false;
				//break;
			}
		}
		return flag;
	}


	/**
	 * 리얼타임으로 숫자만 입력할때
	 */
	function onlyNumberInput(){
		var code ="";
		if(window.event){// IE코드
			code = window.event.keyCode;
		}else{
			code = Ev.which;
		}
		if((code > 34 && code <41) || (code > 47 && code <58) || (code > 95 && code <106)|| code ==8||code ==9 || code==13|| code==46)
		{
		window.event.returnValue = true;
		return;
		}
		window.event.returnValue=false;
	}

	/**
	 * 입력값 제한
	 * 숫자만 허용
	 */
	function fn_reNumberCheckValue( obj ){
		var input = obj.val();

		if(isNaN(input) || input == ""){
			var index = input.length;
		    $(obj).val(  input.substring(0,index-1)  );
		    return 0;
	  	}
	}

	/**
	 * 입력값 제한
	 * 숫자 + '-' 허용
	 */
	function fn_reNumberPhoneCheckValue( obj ){
		var input = obj.val();
		if(isNaN(input) || input == ""){
			if( input.indexOf("-") == -1  ){
				var index = input.length;
				$(obj).val(  input.substring(0,index-1)  );
				return 0;
			}
		}
	}

    /**
     * 두 날짜의 차이를 일자로 구한다.(조회 종료일 - 조회 시작일)
     * HSH 20140411 적용
     * @param val1 - 조회 시작일(날짜 ex.2002-01-01)
     * @param val2 - 조회 종료일(날짜 ex.2002-01-01)
     * @return 기간에 해당하는 일자
     */
    function calDateRange(val1, val2)
    {
        var FORMAT = "-";

        // FORMAT을 포함한 길이 체크
        if (val1.length != 10 || val2.length != 10)
            return null;

        // FORMAT이 있는지 체크
        if (val1.indexOf(FORMAT) < 0 || val2.indexOf(FORMAT) < 0)
            return null;

        // 년도, 월, 일로 분리
        var start_dt = val1.split(FORMAT);
        var end_dt = val2.split(FORMAT);

        // 월 - 1(자바스크립트는 월이 0부터 시작하기 때문에...)
        // Number()를 이용하여 08, 09월을 10진수로 인식하게 함.
        start_dt[1] = (Number(start_dt[1]) - 1) + "";
        end_dt[1] = (Number(end_dt[1]) - 1) + "";

        var from_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
        var to_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);

        return (to_dt.getTime() - from_dt.getTime()) / 1000 / 60 / 60 / 24;
    }

    // AD 공통 엑셀다운시 업무시간 체크
    function adWorkTimeChekExelDown(){
        var workTimeYn = "";
        /* 요건 변경으로 시간 체크부분을 풀고 엑셀 다운받는걸로
        $.ajax({
            type: "POST",
            url:  "/cs/adCounselHistoryCheckWorkTime",
            async: false,
            dataType:   'json',
            success: function(result) {
                workTimeYn = result.workTimeChkYn;
            },
            error: function(error){
                workTimeYn = "E";
            }
        });
        */
        // 테스트용으로 무조건 실행 가능하도록
        workTimeYn = 'N';
        switch(workTimeYn){
            case "Y":
                return true;
            break;
            case "N":
                return false;
            break;
            case "E":
                return true;
            break;
        }
    }
