<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcSales_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        salesManageConForm.curPage.value = curPage;
        
    	if(salesManageConForm.orderStartDate.value == ''){
    		alert('조회 시작일을 입력하세요');
    		return;	
    	}
    	
    	if(salesManageConForm.orderEndDate.value == ''){
    		alert('조회 종료일을 입력하세요');
    		return;	
    	}

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/member/salespagelist",
                    data:$("#salesManageConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#salesPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }

    function setToDay(){

    	var strToday='${strToday}';
    	
    	salesManageConForm.orderStartDate.value = strToday;
    	salesManageConForm.orderEndDate.value = strToday;
    	
    	fcSales_listSearch();
    }
    
    function setMonth(){

    	var strToday='${strToday}';
    	var strDeliveryDay='${strDeliveryDay}';
    	
    	salesManageConForm.orderStartDate.value = strDeliveryDay;
    	salesManageConForm.orderEndDate.value = strToday;
    	
    	fcSales_listSearch();
    }
    
    function setDate(){

    	salesManageConForm.orderStartDate.value = '';
    	salesManageConForm.orderEndDate.value = '';

    }

</SCRIPT>
 <!-- 조회조건 -->
		  <form:form class="form-inline" role="form" commandName="salesConVO" id="salesManageConForm" name="salesManageConForm" method="post" action="" >
	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="5"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
	        <input type="hidden" name="orderStartDate"          id="orderStartDate"         value="${strDeliveryDay}"  />
	        <input type="hidden" name="orderEndDate"          id="orderEndDate"         value="${strToday}"  /> 
		  </form:form>
		  <!-- //조회 -->
		 <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>판매현황</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a>판매현황</a>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
 		 </div>
        
       <!-- 조회결과리스트 -->
	   <div id=salesPageList></div>
	    
	   <div class="footer">
            <div class="pull-right">
                welcome to <strong>Salesb</strong> !!.
            </div>
            <div>
                <strong>Copyright</strong> Salesb Corp &copy; All rights reserved v1.0.0
            </div>
       </div>
	    

	<script>
	fcSales_listSearch();
	</script>

   <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>

    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>

    <!-- ChartJS-->
    <script src="js/plugins/chartJs/Chart.min.js"></script>

    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <!-- Peity demo -->
    <script src="js/demo/peity-demo.js"></script>


    <script>
        $(document).ready(function() {


            var d1 = [[1262304000000, 6], [1264982400000, 3057], [1267401600000, 20434], [1270080000000, 31982], [1272672000000, 26602], [1275350400000, 27826], [1277942400000, 24302], [1280620800000, 24237], [1283299200000, 21004], [1285891200000, 12144], [1288569600000, 10577], [1291161600000, 10295]];
            var d2 = [[1262304000000, 5], [1264982400000, 200], [1267401600000, 1605], [1270080000000, 6129], [1272672000000, 11643], [1275350400000, 19055], [1277942400000, 30062], [1280620800000, 39197], [1283299200000, 37000], [1285891200000, 27000], [1288569600000, 21000], [1291161600000, 17000]];

            var data1 = [
                { label: "Data 1", data: d1, color: '#17a084'},
                { label: "Data 2", data: d2, color: '#127e68' }
            ];
            $.plot($("#flot-chart1"), data1, {
                xaxis: {
                    tickDecimals: 0
                },
                series: {
                    lines: {
                        show: true,
                        fill: true,
                        fillColor: {
                            colors: [{
                                opacity: 1
                            }, {
                                opacity: 1
                            }]
                        },
                    },
                    points: {
                        width: 0.1,
                        show: false
                    },
                },
                grid: {
                    show: false,
                    borderWidth: 0
                },
                legend: {
                    show: false,
                }
            });

            var lineData = {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [
                    {
                        label: "Example dataset",
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [65, 59, 40, 51, 36, 25, 40]
                    },
                    {
                        label: "Example dataset",
                        fillColor: "rgba(26,179,148,0.5)",
                        strokeColor: "rgba(26,179,148,0.7)",
                        pointColor: "rgba(26,179,148,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(26,179,148,1)",
                        data: [48, 48, 60, 39, 56, 37, 30]
                    }
                ]
            };

            var lineOptions = {
                scaleShowGridLines: true,
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleGridLineWidth: 1,
                bezierCurve: true,
                bezierCurveTension: 0.4,
                pointDot: true,
                pointDotRadius: 4,
                pointDotStrokeWidth: 1,
                pointHitDetectionRadius: 20,
                datasetStroke: true,
                datasetStrokeWidth: 2,
                datasetFill: true,
                responsive: true,
            };


            var ctx = document.getElementById("lineChart").getContext("2d");
            var myNewChart = new Chart(ctx).Line(lineData, lineOptions);

        });
    </script>
