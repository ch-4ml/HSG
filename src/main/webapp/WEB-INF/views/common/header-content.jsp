<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./common.jsp" %>
<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>	
		<script type="text/javascript">
		</script>
	</head>
	<body>
    <!-- Preloader Starts -->
    <div class="preloader">
        <div class="spinner"></div>
    </div>
    <!-- Preloader End -->

    <!-- Header Area Starts -->
    <header class="header-area">
        <div id="header" id="home">
            <div class="container">
                <div class="row align-items-center justify-content-between d-flex">
                <div id="logo">
                    <a href="index.me"><img src="./resources/images/logo/logo.png" alt="" title="" /></a>
                </div>
                <nav id="nav-menu-container">
                    <ul class="nav-menu">
                        <li class="menu-active"><a href="index.me">Home</a></li>
                        <li class="menu-has-children"><a href="view.ig">회사소개</a>
                            <ul>
                                <li><a href="view.ig">CEO인사말</a></li>
                                <li><a href="view.ih">일반현황 및 연혁</a></li>
								<li><a href="view.io">조직 및 사업분야</a></li>
								<li><a href="view.ib">출판도서/특허</a></li>
								<li><a href="view.il">회사위치</a></li>
                            </ul>
                        </li>
                        <li class="menu-has-children"><a href="">교육솔루션</a>
                            <ul>
                                <li><a href="view.ee">E-러닝</a></li>
                                <li><a href="#">하드웨어</a></li>
								<li><a href="#">소프트웨어</a></li>
								<li><a href="#">교육 컨설팅</a></li>
								<li><a href="#">교육 의뢰</a></li>
                            </ul>
                        </li>
						<li class="menu-has-children"><a href="view.el">강사채용</a>
                            <ul>
                                <li><a href="view.el">채용 공고</a></li>
                            </ul>
                        </li>
                        <c:choose>
							<c:when test="${empty loginUser }">
								<li><a href="signIn.me">LOGIN</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="logout.me">LOGOUT</a>
							</c:otherwise>
						</c:choose>			          				          
                    </ul>
                </nav><!-- #nav-menu-container -->
                </div>
            </div>
        </div>
    </header>
    <!-- Header Area End -->
	</body>
</html>