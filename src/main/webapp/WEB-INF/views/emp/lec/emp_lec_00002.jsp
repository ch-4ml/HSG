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
                    <h1>이메일 지원</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
    
    <!-- About Area Starts -->
    <form action="send.el" method="post">
	    <input type="text" name="name" placeholder="지원자 성함">
	    <input type="text" name="field" placeholder="분야">
	    <input type="text" name="phone" placeholder="연락처">
	    <input type="text" name="email" placeholder="이메일">
	    <textarea name="career" placeholder="경력사항을 자유롭게 기재해주세요."></textarea>
	    <input type="submit" value="메일 보내기">
    </form>
    
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>