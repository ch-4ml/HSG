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
    <c:if test="${!empty loginUser }">
    	<a href="insertView.ee">추가하기</a>
    	<br>
    </c:if>
	<c:forEach var="content" items="${contents }" varStatus="status">
		<a href="viewDetail.ee?id=${content.id }">
		${status.count }
		<img src="https://img.youtube.com/vi/${content.image }/mqdefault.jpg">
		${content.title }
		${content.postDate }
		</a>
		<br>
		<br>
	</c:forEach>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>