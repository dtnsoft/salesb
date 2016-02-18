<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <!-- Latest compiled and minified CSS -->
	<style type="text/css">
	.map_style {
	    padding: 0px;
	    height: 100%;
	    width: 100%;
	}
	</style>
	<link href="<%= request.getContextPath() %>/css/reset.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/css/comunity.css" rel="stylesheet">
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	
	<!-- JQuery Mobile을 사용하기 위해 반드시 필요한 태그-->
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0rc2/jquery.mobile-1.0rc2.min.css" />
    <script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.0rc2/jquery.mobile-1.0rc2.min.js"></script>
	<!-- JQuery-UI-Map을 사용하기 위해 반드시 필요한 태그-->
    <script src="http://maps.google.com/maps/api/js?sensor=true" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/web/jquery.fn.gmap.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/ui/jquery.ui.map.full.min.js" type="text/javascript"></script>   
    <script src="<%= request.getContextPath() %>/ui/jquery.ui.map.extensions.js" type="text/javascript"></script>
    
    <!-- naver -->
    <script type="text/javascript" src="http://openapi.map.naver.com/openapi/v2/maps.js?clientId=4THYarC7qz77DVXElPNk"></script>
    <!-- daum -->
    <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=cf7f8d48261d5da8c3c317b89b95736e"></script>

	<script>
	
	var setFlag='01';
	

    /*
	 *interval에따른 시간차 함수
	 */
	 function interval(method,secval){
	
	 	setTimeout(method,secval);
	 	
	 }


    function setTab(flag){

    	setFlag=flag;
    	
    	if(flag=='01'){

    		$('#orderlist').attr("style","display:block");
        	$('#saleslist').attr("style","display:none");
        	$('#map').attr("style","display:none");
        	$('#orderkeydtail').attr("style","display:none");
        	$('#customerModify').attr("style","display:none");

        	$('#tab1').attr("class","on");
        	$('#tab2').attr("class","");
        	$('#tab3').attr("class","");
        	$('#tab4').attr("class","");
        	
    	}else if(flag=='02'){
    		
    		$('#orderlist').attr("style","display:none");
        	$('#saleslist').attr("style","display:block");
        	$('#map').attr("style","display:none");
        	$('#orderkeydtail').attr("style","display:none");
        	$('#customerModify').attr("style","display:none");

        	$('#tab1').attr("class","");
        	$('#tab2').attr("class","on");
        	$('#tab3').attr("class","");
        	$('#tab4').attr("class","");
        	
    	}else if(flag=='03'){

    		$('#orderlist').attr("style","display:none");
        	$('#saleslist').attr("style","display:none");
        	$('#map').attr("style","display:block");
        	$('#orderkeydtail').attr("style","display:none");
        	$('#customerModify').attr("style","display:none");
 
        	$('#tab1').attr("class","");
        	$('#tab2').attr("class","");
        	$('#tab3').attr("class","on");
        	$('#tab4').attr("class","");
        	
    	}else if(flag=='04'){
    		
    		$('#orderlist').attr("style","display:none");
        	$('#saleslist').attr("style","display:none");
        	$('#map').attr("style","display:none");
        	$('#orderkeydtail').attr("style","display:block");
        	$('#customerModify').attr("style","display:none");

        	$('#tab1').attr("class","");
        	$('#tab2').attr("class","");
        	$('#tab3').attr("class","");
        	$('#tab4').attr("class","on");
        	
    	}

    }
    
	function fcConfig_modifyView() {

    	$.ajax({
            type: "POST",
            url:  '<%= request.getContextPath() %>/common/customermodifyform',
            success: function(result) {
             
            	$("#customerModify").html(result);
            	
            	$('#orderlist').attr("style","display:none");
            	$('#saleslist').attr("style","display:none");
            	$('#map').attr("style","display:none");
            	$('#orderkeydtail').attr("style","display:none");
            	$('#customerModify').attr("style","display:block");
      	
            },
            error:function(){

            }
        });
    };

 // 리스트 조회
    function fcOrder_list(){
		
    	//var customerKey='${customerKey}';
    	//var groupId='${groupId}';
    	
    	var customerKey='01067471995';
    	var groupId='BD008';
    
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/comunity/orderlist",
               success: function(result) {
                  
                   $("#orderlist").html(result);
               },
               error:function() {
                  
               }
        });
    }
 
    // 판매 리스트 조회
    function fcSales_list(){
	
    	//var customerKey='${customerKey}';
    	//var groupId='${groupId}';
    	
    	var customerKey='01067471995';
    	var groupId='BD008';
	
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/comunity/saleslist",
               success: function(result) {
               
                   $("#saleslist").html(result);
               },
               error:function() {
                  
               }
        });
    }
 
 	var mapType='daum';
 
    function setMap(type){
    	mapType=type;
    	alert('map type :'+type);
    }
 
    function fcMap(){//위치조회
    	
    	//alert(mapType);
    	
    	if(mapType=='daum'){
    		dMap();
    	}else{
    		gMap();
    	}
    	
    }

    function gMap(){//google
    	
    	//서울산업진흥원
        var lat = 37.5804622;
        var lon = 126.8873503;

    	if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function nowLocation(position) {
                    lat = position.coords.latitude;
                    lon = position.coords.longitude;
                  
                    //alert('current latitude:'+lat);
                    //alert('current longitude:'+lon);
                    	
                  	var StartLatLng = new google.maps.LatLng(lat, lon);    
                    //현위치 마크추가  37.5804622,126.8873503
                    var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude)
                   	var latlng2 = new google.maps.LatLng(37.5804622,126.8873503)
                    var markerRed = "http://www.google.com/intl/en_us/mapfiles/ms/icons/red-dot.png";
                   	var markerBlue = "http://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png"; 
                    /*
                    $("#map_canvas").gmap({"center": StartLatLng, "zoom":16});
 
                    $("#map_canvas").gmap("get", "map").panTo(latlng); 
                    $("#map_canvas").gmap("addMarker", {"position": latlng, "icon": markerRed}); 
                       
                    $("#map_canvas").gmap("addMarker", {"position": latlng2, "icon": markerBlue});   
                    */                  
 
                    var map = new google.maps.Map(document.getElementById('map_canvas'), {
                        zoom: 14,
                        center: {lat: position.coords.latitude, lng: position.coords.longitude}
                      });
					  
					var marker = new google.maps.Marker({
					    position: {lat: position.coords.latitude, lng: position.coords.longitude},
					    map: map,
					    icon: markerBlue,
					    title: 'Sales Baron!'
					  });
					
					var marker2 = new google.maps.Marker({
					    position: latlng2,
					    map: map,
					    icon: markerRed,
					    title: 'Sales Baron2!'
					  });
                    
                    var cityCircle = new google.maps.Circle({
                        strokeColor: '#FF0000',
                        strokeOpacity: 0.8,
                        strokeWeight: 2,
                        fillColor: '#FF0000',
                        fillOpacity: 0.35,
                        map: map,
                        center: StartLatLng,
                        radius: Math.sqrt(50) * 100
                      });
 
                    var groupmap = {
                		  seoul1: {
                		    center: {lat: 37.5814622, lng: 126.8813503}
                		  },
                		  seoul2: {
                		    center: {lat: 37.5824622, lng: 126.8823503}
                		  },
                		  seoul3: {
                		    center: {lat: 37.5834622, lng: 126.8833503}
                		  },
                		  seoul4: {
                  		    center: {lat: 37.5844622, lng: 126.8843503}
                  		  },
                		  seoul5: {
                		    center: {lat: 37.5854622, lng: 126.8853503}
                		  }
                	};
                    
                    for (var group in groupmap) {
                        // Add the marker for this group to the map.       
                        var marker = new google.maps.Marker({
						    position: groupmap[group].center,
						    map: map,
						    icon: markerRed,
						    title: 'Sales Baron!'+group
						  });
                      }

                },
                function(error) {
                	
                    alert("브라우저의 위치추적을 허용하지 않으셨습니다. 기본좌표로 이동합니다.");
     
					var StartLatLng = new google.maps.LatLng(lat, lon);    
					$("#map_canvas").gmap({"center": StartLatLng, "zoom":16});
  
                }
	        );
	    } else {
            
        	alert("Your Browser don't support for Geolocation");

        	 var StartLatLng = new google.maps.LatLng(lat, lon);   
             $("#map_canvas").gmap({"center": StartLatLng, "zoom":16});
        }

   	}

    function dMap(){
    	
    	//서울산업진흥원
        var lat = 37.5804622;
        var lon = 126.8873503;
        
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function nowLocation(position) {
                    lat = position.coords.latitude;
                    lon = position.coords.longitude;
        
			    	var container = document.getElementById('map_canvas'); //지도를 담을 영역의 DOM 레퍼런스
			    	var options = { //지도를 생성할 때 필요한 기본 옵션
			    		center: new daum.maps.LatLng(lat, lon), //지도의 중심좌표.
			    		level: 6 //지도의 레벨(확대, 축소 정도)
			    	};
			
			    	var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
			    	
			    	// 마커가 표시될 위치입니다 
			    	var markerPosition  = new daum.maps.LatLng(lat, lon); 
			    	
			    	// 고정마커가 표시될 위치입니다 
			    	var markerPosition2  = new daum.maps.LatLng(37.5804622, 126.8873503); 
			    	
			    	// 마커를 생성합니다
			    	var marker = new daum.maps.Marker({
			    	    position: markerPosition
			    	});
			    	
			    	// 고정마커를 생성합니다
			    	var marker2 = new daum.maps.Marker({
			    	    position: markerPosition2
			    	});
			    	
			    	// 마커가 지도 위에 표시되도록 설정합니다
			    	marker2.setMap(map);
			    	
			    	// 마커가 지도 위에 표시되도록 설정합니다
			    	marker.setMap(map);

			    	var groupmap = {
                		  seoul1: {
                		    center: {lat: 37.5814622, lng: 126.8813503}
                		  },
                		  seoul2: {
                		    center: {lat: 37.5824622, lng: 126.8823503}
                		  },
                		  seoul3: {
                		    center: {lat: 37.5834622, lng: 126.8833503}
                		  },
                		  seoul4: {
                  		    center: {lat: 37.5844622, lng: 126.8843503}
                  		  },
                		  seoul5: {
                		    center: {lat: 37.5854622, lng: 126.8853503}
                		  }
                	};
	                    
                    for (var group in groupmap) {
                        // Add the marker for this group to the map.
                    	// 마커를 지도위에 표시합니다 
                    	var markerPosition  = new daum.maps.LatLng(groupmap[group].center.lat,groupmap[group].center.lng); 
                        
                    	// 마커를 생성합니다
    			    	var marker = new daum.maps.Marker({
    			    	    position: markerPosition
    			    	});
                    	
    			       	// 마커가 지도 위에 표시되도록 설정합니다
    			    	marker.setMap(map);

                     }

			        // 원의 반경을 표시할 선 객체를 생성합니다
			        var polyline = new daum.maps.Polyline({
			            path: [ new daum.maps.LatLng(lat, lon),  new daum.maps.LatLng(lat, lon+0.01132)], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다 ->0.0018 (약 158m)
			            strokeWeight: 3, // 선의 두께 입니다
			            strokeColor: '#00a0e9', // 선의 색깔입니다
			            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
			            strokeStyle: 'solid' // 선의 스타일입니다
			        });
			    	
			    	// 원 객체를 생성합니다
	    	        var circle = new daum.maps.Circle({ 
	    	            center :  new daum.maps.LatLng(lat, lon), // 원의 중심좌표입니다
	    	            radius: polyline.getLength(),  // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
	    	            strokeWeight: 1, // 선의 두께입니다
	    	            strokeColor: '#00a0e9', // 선의 색깔입니다
	    	            strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	    	            strokeStyle: 'solid', // 선의 스타일입니다
	    	            fillColor: '#00a0e9', // 채우기 색깔입니다
	    	            fillOpacity: 0.2  // 채우기 불투명도입니다 
	    	        });
			    	
	    	        circle.setMap(map); 
 
	    	        // 선을 지도에 표시합니다
	    	        polyline.setMap(map);
  
	    	        var radius = Math.round(circle.getRadius()), // 원의 반경 정보를 얻어옵니다
	                content = getTimeHTML(radius); // 커스텀 오버레이에 표시할 반경 정보입니다
	              
	                // 반경정보를 표시할 커스텀 오버레이를 생성합니다
	                var radiusOverlay = new daum.maps.CustomOverlay({
	                    content: content, // 표시할 내용입니다
	                    position: new daum.maps.LatLng(lat, lon+0.01132), // 표시할 위치입니다. 클릭한 위치로 설정합니다
	                    xAnchor: 0,
	                    yAnchor: 0,
	                    zIndex: 1 
	                });  

	                // 반경 정보 커스텀 오버레이를 지도에 표시합니다
	                radiusOverlay.setMap(map);
 
		    	     // 그려진 원의 반경 정보와 반경에 대한 도보, 자전거 시간을 계산하여
		    	     // HTML Content를 만들어 리턴하는 함수입니다
		    	     function getTimeHTML(distance) {
	
		    	         // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
		    	         var walkkTime = distance / 67 | 0;
		    	         var walkHour = '', walkMin = '';
	
		    	         // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
		    	         if (walkkTime > 60) {
		    	             walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
		    	         }
		    	         walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'
	
		    	         // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
		    	         var bycicleTime = distance / 227 | 0;
		    	         var bycicleHour = '', bycicleMin = '';
	
		    	         // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
		    	         if (bycicleTime > 60) {
		    	             bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
		    	         }
		    	         bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'
	
		    	         // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
		    	         var content = '<ul class="info">';
		    	         content += '    <li>';
		    	         content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
		    	         content += '    </li>';
		    	         content += '    <li>';
		    	         content += '        <span class="label">도보</span>' + walkHour + walkMin;
		    	         content += '    </li>';
		    	         content += '    <li>';
		    	         content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
		    	         content += '    </li>';
		    	         content += '</ul>'
	
		    	         return content;
		    	     }
		
                },
                function(error) {
                	
                    alert("브라우저의 위치추적을 허용하지 않으셨습니다. 기본좌표로 이동합니다.");
     
			    	var container = document.getElementById('map_canvas'); //지도를 담을 영역의 DOM 레퍼런스
			    	var options = { //지도를 생성할 때 필요한 기본 옵션
			    		center: new daum.maps.LatLng(lat, lon), //지도의 중심좌표.
			    		level: 3 //지도의 레벨(확대, 축소 정도)
			    	};
			
			    	var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
  
                }
	        );
	    } else {
            
        	alert("Your Browser don't support for Geolocation");

	    	var container = document.getElementById('map_canvas'); //지도를 담을 영역의 DOM 레퍼런스
	    	var options = { //지도를 생성할 때 필요한 기본 옵션
	    		center: new daum.maps.LatLng(lat, lon), //지도의 중심좌표.
	    		level: 3 //지도의 레벨(확대, 축소 정도)
	    	};
	
	    	var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
        }
    	
    }
    
    function nMap(){
    	
    	//서울산업진흥원
        var lat = 37.5804622;
        var lon = 126.8873503;
    	
        var oSeoulCityPoint = new nhn.api.map.LatLng(lat, lon);
        var defaultLevel = 11;
        var oMap = new nhn.api.map.Map(document.getElementById('map_canvas'), { 
                                        point : oSeoulCityPoint,
                                        zoom : defaultLevel,
                                        enableWheelZoom : true,
                                        enableDragPan : true,
                                        enableDblClickZoom : false,
                                        mapMode : 0,
                                        activateTrafficMap : false,
                                        activateBicycleMap : false,
                                        minMaxLevel : [ 1, 14 ],
                                        size : new nhn.api.map.Size(800, 480)           });
        var oSlider = new nhn.api.map.ZoomControl();
        oMap.addControl(oSlider);
        oSlider.setPosition({
                top : 10,
                left : 10
        });

        var oMapTypeBtn = new nhn.api.map.MapTypeBtn();
        oMap.addControl(oMapTypeBtn);
        oMapTypeBtn.setPosition({
                bottom : 10,
                right : 80
        });
        
        var oThemeMapBtn = new nhn.api.map.ThemeMapBtn();
        oThemeMapBtn.setPosition({
                bottom : 10,
                right : 10
        });
        oMap.addControl(oThemeMapBtn);

        var oBicycleGuide = new nhn.api.map.BicycleGuide(); // - 자전거 범례 선언
        oBicycleGuide.setPosition({
                top : 10,
                right : 10
        }); // - 자전거 범례 위치 지정
        oMap.addControl(oBicycleGuide);// - 자전거 범례를 지도에 추가.

        var oTrafficGuide = new nhn.api.map.TrafficGuide(); // - 교통 범례 선언
        oTrafficGuide.setPosition({
                bottom : 30,
                left : 10
        });  // - 교통 범례 위치 지정.
        oMap.addControl(oTrafficGuide); // - 교통 범례를 지도에 추가.

        var trafficButton = new nhn.api.map.TrafficMapBtn(); // - 실시간 교통 지도 버튼 선언
        trafficButton.setPosition({
                bottom:10, 
                right:150
        }); // - 실시간 교통 지도 버튼 위치 지정
        oMap.addControl(trafficButton);

        var oSize = new nhn.api.map.Size(28, 37);
        var oOffset = new nhn.api.map.Size(14, 37);
        var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

        var oInfoWnd = new nhn.api.map.InfoWindow();
        oInfoWnd.setVisible(false);
        oMap.addOverlay(oInfoWnd);

        oInfoWnd.setPosition({
                top : 20,
                left :20
        });

        var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
        oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

        oInfoWnd.attach('changeVisible', function(oCustomEvent) {
                if (oCustomEvent.visible) {
                        oLabel.setVisible(false);
                }
        });

        var oPolyline = new nhn.api.map.Polyline([], {
                strokeColor : '#f00', // - 선의 색깔
                strokeWidth : 5, // - 선의 두께
                strokeOpacity : 0.5 // - 선의 투명도
        }); // - polyline 선언, 첫번째 인자는 선이 그려질 점의 위치. 현재는 없음.
        oMap.addOverlay(oPolyline); // - 지도에 선을 추가함.

        oMap.attach('mouseenter', function(oCustomEvent) {

                var oTarget = oCustomEvent.target;
                // 마커위에 마우스 올라간거면
                if (oTarget instanceof nhn.api.map.Marker) {
                        var oMarker = oTarget;
                        oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
                }
        });
    
        oMap.attach('mouseleave', function(oCustomEvent) {

                var oTarget = oCustomEvent.target;
                // 마커위에서 마우스 나간거면
                if (oTarget instanceof nhn.api.map.Marker) {
                        oLabel.setVisible(false);
                }
        });

        oMap.attach('click', function(oCustomEvent) {
                var oPoint = oCustomEvent.point;
                var oTarget = oCustomEvent.target;
                alert('30');
                oInfoWnd.setVisible(false);
                alert('31');
                // 마커 클릭하면
                if (oTarget instanceof nhn.api.map.Marker) {
                	 alert('32');
                        // 겹침 마커 클릭한거면
                        if (oCustomEvent.clickCoveredMarker) {
                        	
                        	 alert('33');
                                return;
                        }
                        // - InfoWindow에 들어갈 내용은 setContent로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며, 
                        // - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
                        // - 단, DIV의 position style은 absolute가 되면 안되며, 
                        // - absolute의 경우 autoPosition이 동작하지 않습니다. 
                        oInfoWnd.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
                                '<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">' + 
                                'Hello World <br /> ' + oTarget.getPoint()
                                +'<span></div>');
                        oInfoWnd.setPoint(oTarget.getPoint());
                        oInfoWnd.setPosition({right : 15, top : 30});
                        oInfoWnd.setVisible(true);
                        oInfoWnd.autoPosition();
                        alert('34');
                        return;
                }
                alert('4');
                var oMarker = new nhn.api.map.Marker(oIcon, { title : '마커 : ' + oPoint.toString() });
                oMarker.setPoint(oPoint);
                oMap.addOverlay(oMarker);

                var aPoints = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
                aPoints.push(oPoint); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
                oPolyline.setPoints(aPoints); // - 해당 폴리라인에 배열에 저장된 점을 추가함
        });

    }
 
	//logout 처리
	var goLogout =  function() {

		//alert('logout');
		
		$('#logoutForm').attr({action:"<%= request.getContextPath() %>/common/logout"});
		
		try {
			logoutForm.submit();
		} catch(e) {}
	};


    function fcOrderKey(){
    	  	
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/comunity/orderkeydetail",
            success: function(result) {
             
                   $("#orderkeydtail").html(result);
            },
            error:function(){

            }
        });
        
    }
    
    function AutoResize(img){

    	   foto1= new Image();
    	   foto1.src=(img);
    	   Controlla(img);
    }
    
  	 function Controlla(img){

  	   if((foto1.width!=0)&&(foto1.height!=0)){
  	     viewFoto(img);
  	   }
  	   else{
  	     funzione="Controlla('"+img+"')";
  	     intervallo=setTimeout(funzione,20);
  	   }
  	   
  	 }
  	 
   	 function viewFoto(img){
   		 
   	   largh=foto1.width-20;
   	   altez=foto1.height-20;
   	   stringa="width="+largh+",height="+altez;
   	  // finestra=window.open(img,"",stringa);
   	  
	   	var h=screen.height-(screen.height*(8.5/100));
		var s=screen.width;
		
		if(h<s){
			s=h;
		}
		
		if(s<largh){
			largh=s;
		}

   	  	var url='<%= request.getContextPath() %>/comunity/imageview';
   
	   	$('#imageView').dialog({
	        resizable : false, //사이즈 변경 불가능
	        draggable : true, //드래그 불가능
	        closeOnEscape : false, //ESC 버튼 눌렀을때 종료
	        ////position : 'center',
	        width : largh,
	        modal : true, //주위를 어둡게
	        istitle : false,
	
	        open:function(){
	            //팝업 가져올 url
	        	 $(this).load(url+'?imageurl='+img);
	
	        }
	        ,close:function(){
	            $('#imageView').empty();
	        }
	    });
   	   
   	 }
   	 
     function imageView(imageurl) {

     	var url='<%= request.getContextPath() %>/comunity/imageview';
     	
     	$('#imageView').dialog({
             resizable : false, //사이즈 변경 불가능
             draggable : true, //드래그 불가능
             closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            // width : 100,
            // height : 100,
             modal : true, //주위를 어둡게

             open:function(){
                 //팝업 가져올 url
             	 $(this).load(url+'?imageurl='+imageurl);

             }
             ,close:function(){
                 $('#imageView').empty();
             }
         });
     };
     
     function imageClose(){
    	 
    	 $("#imageView").dialog('close');
    	 $('#imageView').empty();
     }
     
     function fcComunity_multiRegist(){
    
   	    var url;
   	    var frm = document.comunityForm;
   	    var fileName = '';
   	    var pos = '';
   	    var ln = '';
   	    var gap = '';
   	    var gap1 = '';
   	    
   		var comment=frm.comment.value;
   		
   	    url="<%= request.getContextPath() %>/comunity/comunityregist?comment="+comment;
   		
   	    if($("#files").val() != ''){
   	    	
   	        fileName = document.all.files.value;
   	        pos = fileName.lastIndexOf("\\");
   	        ln = fileName.lastIndexOf("\.");
   	        gap = fileName.substring(pos + 1, ln);
   	        gap1 = fileName.substring(ln+1);
   	        
   	        if(gap1=="jpg" || gap1=="JPG" || gap1=="gif" || gap1=="GIF" || gap1=="png" || gap1=="PNG"){//
   	            url="<%= request.getContextPath() %>/comunity/comunityregist?fileName="+gap+"&extension="+gap1+"&comment="+comment;
   	        }else {
   	        	alert("이미지 파일만 등록 부탁드립니다.");
   	            return;
   	        }
   	        
   	    }else{
   	    
   			if(frm.comment.value==''){
   				alert('남길 톡 내용이 없습니다.');
   				return;
   			}
   	    }

   	    commonDim(true);
   	    frm.action = url;
   	    frm.target="file_result";

   	    frm.submit();        
    }
     
    function fcComunity_close(){
    		
   		commonDim(false);
   		
   		var frm = document.comunityForm;
   		frm.comment.value='';
   		frm.files.value='';
   		document.all('fid').innerText='파일첨부'; 
   		//	$("#commentRegistForm").dialog('close');
    }
     
    function getFileExtension( filePath ){ 
   	   
    	var lastIndex = -1; 
   	    lastIndex = filePath.lastIndexOf('.'); 
   	    var extension = ""; 

	   	if ( lastIndex != -1 ){ 
	   	    extension = filePath.substring( lastIndex+1, filePath.len ); 
	   	} else { 
	   	    extension = ""; 
	   	} 
	   	
   	    return extension; 
   	} 

   	function uploadImg_Change( value ){ 

   	    var src = getFileExtension(value); 
   	    if (src == "") { 
   	       // alert('올바른 파일을 입력하세요'); 
   	       // return; 
   	    } else if ( !((src.toLowerCase() == "gif") || (src.toLowerCase() == "jpg") || (src.toLowerCase() == "jpeg")) ) { 
   	        alert('gif 와 jpg 파일만 지원합니다.'); 
   	        return; 
   	    } 

   	    //LoadImg( value); 
   	    
   		//$('#fid').attr("class","");
   		document.all('fid').innerText='첨부완료';

   	} 

   	function LoadImg(value){ 
   	    var imgInfo = new Image(); 
   	    imgInfo.onload = img_Load; 
   	    imgInfo.src = value; 
   	} 

   	function img_Load() { 
   	    var imgSrc, imgWidth, imgHeight, imgFileSize; 
   	    var maxFileSize; 
   	    maxFileSize = 10240;  //byte 
   	    imgSrc = this.src; 
   	    imgWidth = this.width; 
   	    imgHeight = this.height; 
   	    imgFileSize = this.fileSize; 

   	    if (imgSrc == "" || imgWidth <= 0 || imgHeight <= 0) { 
   	        alert('그림파일을 가져올 수 없습니다.'); 
   	        return; 
   	    } 

   	    if (imgFileSize > maxFileSize) { 
   	        alert('선택하신 그림 파일은 허용 최대크기인 ' + maxFileSize/1024 + ' KB 를 초과하였습니다.'); 
   	        return; 
   	    } 

   	    document.all.imgWidth.value = imgWidth; 
   	    document.all.imgHeight.value = imgHeight; 

   	} 

  </script>
  </head>
   <body>
  <form method="post" id="logoutForm" name="logoutForm"  role="form" >
  <input type="hidden" name="saleurlvalue" id="saleurlvalue" >
  </form>
	 <div id="wrap" class="wrap" >
	 <!-- 헤더 -->
	  <header id="header">
	    <div id="m_gnb" class="mcom_gnbwrap mb_top">
	      <div class="mcom_gnb ">
	        <h1 class="head_logo"></h1>
	        <div class="mcom_gnb_rgt">
	          <div class="b_toggle">
	            <ul>
	               <!--<li> <a href="#" class="b_name"> <strong class="">${restfulltype}</strong> </a></li>-->
	              <li> <a href="#" class="b_name"> <strong class="">${name}</strong> </a></li>
	              <li> <a href="javascript:fcConfig_modifyView()" class="b_cog"> <strong class="ico_cog"><span>설정</span></strong> </a></li>
	              <li><a href="javascript:goLogout()" class="b_logout"> 
	                <strong>로그아웃</strong></a>
	              </li>
	            </ul>
	          </div>
	        </div>
	      </div>
	    </div>
	    <div class="mcom_navwrap" style="overflow: hidden; z-index: 2000; -webkit-tap-highlight-color: transparent; height: 47px;">
	      <div class="mcom_nav" style="position: absolute; z-index: 1; left: 0px; top: 0px; transition-property: -webkit-transform; transform: translate3d(10px, 0px, 0px); transition-timing-function: cubic-bezier(0.33, 0.66, 0.66, 1); height: 100%; width: 2583px; transition-duration: 0ms;">
	        <ul style="width: 861px; height: 100%; position: absolute; left: 0px; top: 0px;">
	          <li id="tab1" class="on" index="0" style="float: left; width: 70px;"><a href="javascript:setTab('01');fcOrder_list()"><span class="nav_mnu">주문조회</span></a></li>
	          <li id="tab2" index="1" style="float: left; width: 70px;"><a href="javascript:setTab('02');fcSales_list();"><span class="nav_mnu">판매조회</span></a></li>
	          <li id="tab3" index="2" style="float: left; width: 70px;"><a href="javascript:setTab('03');fcMap();"><span class="nav_mnu">위치조회</span></a></li>
	          <li id="tab4" index="3" style="float: left; width: 70px;"><a href="javascript:setTab('04');fcOrderKey()"><span class="nav_mnu" style="color:red">주문키발급</span></a></li>
	        </ul>
	      </div>
	    </div>
	  </header>
	  <!--//헤더 --> 

      <div id=orderlist style="display:none"></div>
      <div id=saleslist style="display:none"></div>
  
      <div id=map style="display:none">
	     <div data-role="page" id="page" data-fullscreen="true" class="map_style">
 			<div id="map_canvas" class="map_style"></div>
		 </div> 
	  </div> 
	  
	  <div id=orderkeydtail title="주문키상세"></div>
	  
      <div id=orderDetail title="주문상세"></div>
      <div id=salesDetail title="판매상세"></div>
      
      <div id="customerModify"  style="display:none"></div>
      
  	  <div id="imageView"  onClick="imageClose()"></div>

      <div id="footer" class="footer">
	    <span class="Copyright">Copyright 2015 ⓒsalesb Corp. All rights reserved. v1.0.0</span>
	  </div>
       
    </div>
  </body>
</html>
<script>

if('${customerKey}'==null || '${customerKey}'=='null' ){
	goLogout();
}

setTab('01');
fcOrder_list();

</script>