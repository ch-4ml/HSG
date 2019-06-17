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
                    <a href="view.ma"><img src="./resources/images/logo/HSG_LOGO.png" alt="" title="" width="100%" height="auto"/></a>
                </div>
                <nav id="nav-menu-container">
                    <ul class="nav-menu">
                        <li class="menu-active"><a class="sf-main" href="view.ma">Home</a></li>
                        <li class="menu-has-children"><a href="view.ig">회사소개</a>
                            <ul>
                                <li><a href="view.ig">CEO 인사말</a></li>
                                <li><a href="view.ih">일반 현황 및 연혁</a></li>
								<li><a href="view.io">조직 및 사업 분야</a></li>
								<li><a href="view.ib">출판 도서/특허</a></li>
								<li><a href="view.ic">컨설팅 및 주요 실적</a></li>
								<li><a href="view.ip">Partner & Customer</a></li>
								<li><a href="view.il">회사위치</a></li>
                            </ul>
                        </li>
                        <li class="menu-has-children"><a href="view.ol">오픈소스</a>
                            <ul>
                                <li><a href="view.ol">최신기술</a></li>
                                <li><a href="view.ot">기술노트</a></li>
                                <li><a href="view.os">오픈소스</a></li>
                            </ul>
                        </li>
                        <li class="menu-has-children"><a href="view.ee">교육솔루션</a>
                            <ul>
                                <li><a href="view.ee">MOOC</a></li>
                                <li><a href="view.eh">하드웨어</a></li>
								<li><a href="view.es">소프트웨어</a></li>
								<li><a href="view.ec">교육 컨설팅</a></li>
								<li><a href="view.er">교육 의뢰</a></li>
                            </ul>
                        </li>
						<li class="menu-has-children"><a href="view.en">인재 채용</a>
                            <ul>
                            	<li><a href="view.en">공지사항</a></li>
                                <li><a href="view.el">분야별 채용 공고</a></li>
                            </ul>
                        </li>
                        <c:choose>
							<c:when test="${empty loginUser }">
								<li><a class="sf-main" href="signIn.me">LOGIN</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="sf-main" href="logout.me">LOGOUT</a>
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