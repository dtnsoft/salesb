<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcToken_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        tokenManageConForm.curPage.value = curPage;

        //commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/member/mytokenlist",
                    data:$("#tokenManageConForm").serialize(),
               success: function(result) {
                   //commonDim(false);
                   $("#myTokenList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }

</SCRIPT>
		  <!-- 조회조건 -->
		  <form:form class="form-inline" role="form" commandName="tokenConVO" id="tokenManageConForm" name="tokenManageConForm" method="post" action="" >
	        <input type="hidden" name="curPage"             id="curPage"            value="1" />
	        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
	        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
	        <!-- Blog Search Well -->
	         <!-- 
	         <div class="well">
	             <div class="input-group">
	                 <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" placeholder="조회일자">
	                 <span class="input-group-btn">
	                     <button class="btn btn-default" type="button" onClick="javascript:fcToken_listSearch()">
	                         <span class="glyphicon glyphicon-search" ></span>
	                 </button>
	                 </span>
	             </div>
	         </div>-->
		  </form:form>
		  <!-- //조회 -->
		  
		   <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>상품 토큰현황</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a>나의상품</a>
                    </li>
                    <li class="active">
                        <strong>상품토큰현황</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        	</div>
	
		  <!-- 조회결과리스트 -->
		<div id=myTokenList></div>
		
	   <div class="footer">
            <div class="pull-right">
                welcome to <strong>Salesb</strong> !!
            </div>
            <div>
                <strong>Copyright</strong> Salesb Corp &copy; All rights reserved v1.0.0
            </div>
       </div>


	<script>
	fcToken_listSearch();
	</script>

    <!-- Flot -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/flot/jquery.flot.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/demo/peity-demo.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/inspinia.js"></script>
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/pace/pace.min.js"></script>

    <!-- jQuery UI -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- Sparkline -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/demo/sparkline-demo.js"></script>

    <!-- ChartJS-->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/chartJs/Chart.min.js"></script>

    <!-- Toastr -->
    <script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/toastr/toastr.min.js"></script>


    <script>
        $(document).ready(function() {
            setTimeout(function() {
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 4000
                };
                toastr.success('enjoy your space!!', 'Welcome to Salesb');

            }, 1300);


            var data1 = [
                [0,4],[1,8],[2,5],[3,10],[4,4],[5,16],[6,5],[7,11],[8,6],[9,11],[10,30],[11,10],[12,13],[13,4],[14,3],[15,3],[16,6]
            ];
            var data2 = [
                [0,1],[1,0],[2,2],[3,0],[4,1],[5,3],[6,1],[7,5],[8,2],[9,3],[10,2],[11,1],[12,0],[13,2],[14,8],[15,0],[16,0]
            ];
            $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
                data1, data2
            ],
                    {
                        series: {
                            lines: {
                                show: false,
                                fill: true
                            },
                            splines: {
                                show: true,
                                tension: 0.4,
                                lineWidth: 1,
                                fill: 0.4
                            },
                            points: {
                                radius: 0,
                                show: true
                            },
                            shadowSize: 2
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: "#d5d5d5",
                            borderWidth: 1,
                            color: '#d5d5d5'
                        },
                        colors: ["#1ab394", "#1C84C6"],
                        xaxis:{
                        },
                        yaxis: {
                            ticks: 4
                        },
                        tooltip: false
                    }
            );

            var doughnutData = [
                {
                    value: 300,
                    color: "#a3e1d4",
                    highlight: "#1ab394",
                    label: "App"
                },
                {
                    value: 50,
                    color: "#dedede",
                    highlight: "#1ab394",
                    label: "Software"
                },
                {
                    value: 100,
                    color: "#A4CEE8",
                    highlight: "#1ab394",
                    label: "Laptop"
                }
            ];

            var doughnutOptions = {
                segmentShowStroke: true,
                segmentStrokeColor: "#fff",
                segmentStrokeWidth: 2,
                percentageInnerCutout: 45, // This is 0 for Pie charts
                animationSteps: 100,
                animationEasing: "easeOutBounce",
                animateRotate: true,
                animateScale: false
            };

            var ctx = document.getElementById("doughnutChart").getContext("2d");
            var DoughnutChart = new Chart(ctx).Doughnut(doughnutData, doughnutOptions);

            var polarData = [
                {
                    value: 300,
                    color: "#a3e1d4",
                    highlight: "#1ab394",
                    label: "App"
                },
                {
                    value: 140,
                    color: "#dedede",
                    highlight: "#1ab394",
                    label: "Software"
                },
                {
                    value: 200,
                    color: "#A4CEE8",
                    highlight: "#1ab394",
                    label: "Laptop"
                }
            ];

            var polarOptions = {
                scaleShowLabelBackdrop: true,
                scaleBackdropColor: "rgba(255,255,255,0.75)",
                scaleBeginAtZero: true,
                scaleBackdropPaddingY: 1,
                scaleBackdropPaddingX: 1,
                scaleShowLine: true,
                segmentShowStroke: true,
                segmentStrokeColor: "#fff",
                segmentStrokeWidth: 2,
                animationSteps: 100,
                animationEasing: "easeOutBounce",
                animateRotate: true,
                animateScale: false
            };
            var ctx = document.getElementById("polarChart").getContext("2d");
            var Polarchart = new Chart(ctx).PolarArea(polarData, polarOptions);

        });
    </script>