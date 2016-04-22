<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>INSPINIA - Landing Page</title>

    <!-- Bootstrap core CSS -->
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/bootstrap.min.css" rel="stylesheet">

    <!-- Animation CSS -->
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/animate.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Static_Full_Version/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/Static_Full_Version/css/style.css" rel="stylesheet">
    
    <script>
    
	function goPrivateForm(){

		location.href="<%= request.getContextPath() %>/customerregistform";
		
	}
	
	function goCompanyForm(){

		location.href="<%= request.getContextPath() %>/companyregistform";
		
	}
	
	function goLogin(){
		
		location.href="<%= request.getContextPath() %>/customerloginform";
		
	}
</script>
</head>
<body id="page-top" class="landing-page">

<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#inSlider" data-slide-to="0" class="active"></li>
        <li data-target="#inSlider" data-slide-to="1"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <div class="container">
                <div class="carousel-caption">
                    <h2>환영합니다.<br/>
                                          복잡한 쇼핑몰 구축이나 <br/>
                                          오픈 마켓 입점 없이<br/>
                                          누구나 무료로 온라인 쇼핑몰의<br/>
                                          사장님이 되실 수 있습니다.</h2>
                    <p>http://salesb.net</p>
                    <p>
                        <a class="btn btn-lg btn-primary" href="javascript:goLogin()" role="button">로그인</a>
                        <a class="caption-link" href="javascript:goLogin()" role="button">go login</a>
                    </p>
                    <p>
                        <a class="btn btn-lg btn-primary" href="javascript:goPrivateForm()" role="button">일반회원가입</a>
                        <a class="btn btn-lg btn-primary" href="javascript:goCompanyForm()" role="button">사업자회원가입</a>
                    </p>
                </div>
                <div class="carousel-image wow zoomIn">
                    <img src="<%= request.getContextPath() %>/Static_Full_Version/img/landing/laptop.png" alt="laptop"/>
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back one"></div>

        </div>
        <div class="item">
            <div class="container">
                <div class="carousel-caption blank">
                    <h2>환영합니다.<br/>
                                          복잡한 쇼핑몰 구축이나 <br/>
                                          오픈 마켓 입점 없이<br/>
                                          누구나 무료로 온라인 쇼핑몰의<br/>
                                          사장님이 되실 수 있습니다.</h2>
                    <p>http://salesb.net</p>
                    <p>
                        <a class="btn btn-lg btn-primary" href="javascript:goLogin()" role="button">로그인</a>
                        <a class="caption-link" href="javascript:goLogin()" role="button">go login</a>
                    </p>
                    <p>
                        <a class="btn btn-lg btn-primary" href="javascript:goPrivateForm()" role="button">일반회원가입</a>
                        <a class="btn btn-lg btn-primary" href="javascript:goCompanyForm()" role="button">사업자회원가입</a>
                    </p>
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back two"></div>
        </div>
    </div>
    <a class="left carousel-control" href="#inSlider" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#inSlider" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>


<section id="features" class="container services">
    <div class="row">
        <div class="col-sm-3">
            <h2>SALESB란?</h2>
            <p>누구나 판매자가 되어 상품을 판매 하실 수 있습니다.</p>
            <p><a class="navy-link" href="#" role="button">Details &raquo;</a></p>
        </div>
    </div>

</section>

<section id="contact" class="gray-section contact">
    <div class="container">
        <div class="row m-b-lg">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>Contact Us</h1>
                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.</p>
            </div>
        </div>
        <div class="row m-b-lg">
            <div class="col-lg-3 col-lg-offset-3">
                <address>
                    <strong><span class="navy">Company name, Inc.</span></strong><br/>
                    795 Folsom Ave, Suite 600<br/>
                    San Francisco, CA 94107<br/>
                    <abbr title="Phone">P:</abbr> (123) 456-7890
                </address>
            </div>
            <div class="col-lg-4">
                <p class="text-color">
                    Consectetur adipisicing elit. Aut eaque, totam corporis laboriosam veritatis quis ad perspiciatis, totam corporis laboriosam veritatis, consectetur adipisicing elit quos non quis ad perspiciatis, totam corporis ea,
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <a href="mailto:salesb@salesb.net" class="btn btn-primary">Send us mail</a>
                <p class="m-t-sm">
                    Or follow us on social platform
                </p>
                <ul class="list-inline social-icon">
                    <li><a href="#"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li><a href="#"><i class="fa fa-facebook"></i></a>
                    </li>
                    <li><a href="#"><i class="fa fa-linkedin"></i></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center m-t-lg m-b-lg">
                <p><strong>&copy; 2015 Company Name</strong><br/> consectetur adipisicing elit. Aut eaque, laboriosam veritatis, quos non quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
            </div>
        </div>
    </div>
</section>

<!-- Mainly scripts -->
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/jquery-2.1.1.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/inspinia.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/pace/pace.min.js"></script>
<script src="<%= request.getContextPath() %>/Static_Full_Version/js/plugins/wow/wow.min.js"></script>


<script>

    $(document).ready(function () {

        $('body').scrollspy({
            target: '.navbar-fixed-top',
            offset: 80
        });

        // Page scrolling feature
        $('a.page-scroll').bind('click', function(event) {
            var link = $(this);
            $('html, body').stop().animate({
                scrollTop: $(link.attr('href')).offset().top - 50
            }, 500);
            event.preventDefault();
            $("#navbar").collapse('hide');
        });
    });

    var cbpAnimatedHeader = (function() {
        var docElem = document.documentElement,
                header = document.querySelector( '.navbar-default' ),
                didScroll = false,
                changeHeaderOn = 200;
        function init() {
            window.addEventListener( 'scroll', function( event ) {
                if( !didScroll ) {
                    didScroll = true;
                    setTimeout( scrollPage, 250 );
                }
            }, false );
        }
        function scrollPage() {
            var sy = scrollY();
            if ( sy >= changeHeaderOn ) {
                $(header).addClass('navbar-scroll')
            }
            else {
                $(header).removeClass('navbar-scroll')
            }
            didScroll = false;
        }
        function scrollY() {
            return window.pageYOffset || docElem.scrollTop;
        }
        init();

    })();

    // Activate WOW.js plugin for animation on scrol
    new WOW().init();

</script>

</body>
</html>
<script>


var msg='${ordermessage}';

if(msg!=''){
	
	alert('${ordermessage}');
	
}

</script>