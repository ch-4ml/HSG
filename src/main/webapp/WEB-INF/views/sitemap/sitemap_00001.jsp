<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    
    <%
		String PATH = request.getContextPath();
	%>
	
    <jsp:include page="../common/head.jsp" />
    <script type="text/javascript">
    	$(function() {
    		
    		// siteMapForward(서비스 ID) 
    		// 사이트맵 페이지 이동 처리 함수
    		function siteMapForward(serviceId) {
				return window.location.assign('<%=PATH %>/'+serviceId);
			}
		});
    </script>
</head>
<body>
<jsp:include page="../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
        	<div> - 회사 소개</div>
            <div onclick="siteMapForward('view.ig')">CEO인사말</div>
            <div onclick="siteMapForward('view.ih')">일반현황 및 연혁</div>
            <div onclick="siteMapForward('view.io')">조직 및 사업 분야</div>
            <div onclick="siteMapForward('view.ib')">출판도서/특허</div>
            <div onclick="siteMapForward('view.il')">회사 위치</div>
            <div> - 인재 채용</div>
            <div onclick="siteMapForward('view.el')">강사채용</div>
            <div> - 교욱 솔루션</div>
            <div onclick="siteMapForward('view.eh')">HW</div>
            <div onclick="siteMapForward('view.es')">SW</div>
            <div onclick="siteMapForward('view.ee')">MOOC</div>
            <div onclick="siteMapForward('view.ec')">교육 컨설팅</div>
            <div onclick="siteMapForward('view.er')">교육 의뢰</div>
        </div>
    </section>
	<section class="sub_banner" role="banner">
		<div class="container">
            
        </div>
    </section><!-- banner -->
	
	<jsp:include page="../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../common/script.jsp" />
    <!-- script -->
</body>