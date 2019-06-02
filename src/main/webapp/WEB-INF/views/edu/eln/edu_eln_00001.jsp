<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>MOOC</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    
    <section id="two" class="wrapper style2 alt">
	<div style="text-align:center;"><h2>강의 목록</h2></div><br><br>
	<div class="inner">
		<c:if test="${!empty loginUser }">
			<div style="text-align:center;">
				<ul class="actions special">
					<li><a href="insertView.ee" class="button alt">추가하기</a></li>
				</ul>
			</div>
			<br>
			<br>
		</c:if>
		<c:forEach var="content" items="${contents }" varStatus="status">
			<a href="viewDetail.ee?id=${content.id }">
			<span class="spotlight">
				<h3>&nbsp;&nbsp; ${status.count } &nbsp;&nbsp; </h3>
				<img src="https://img.youtube.com/vi/${content.image }/hqdefault.jpg" width="25%">&nbsp &nbsp &nbsp &nbsp
				<span>
					<h3>${content.title }</h3><BR>
					<h4>본문</h4><BR>
					${content.postDate }
				</span>
			</span>
			</a>
		</c:forEach>
	</div>
	</section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>