<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    <jsp:include page="../../common/head.jsp" />
	<%@ include file="../../common/tinymce.jsp" %>
    
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>강사 채용</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
	
	<div id="overlay" class="map">
		<iframe id="map"
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3177.6398989608747!2d127.0971884264644!3d37.20878170574413!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x18b26f1c5d97b083!2zKOyjvCnsnIjsnbjthY0!5e0!3m2!1sko!2skr!4v1559184596739!5m2!1sko!2skr"width="100%" height="200"></iframe>
	</div>
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../../common/script.jsp" />
    <!-- script -->
</body>
