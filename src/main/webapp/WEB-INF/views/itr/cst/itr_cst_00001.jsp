<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
<%@ include file="../../common/tinymce-one-page.jsp" %>
</head>
<body>
	<jsp:include page="../../common/header-content.jsp" />
	<!-- Banner Area Starts -->
	<section class="banner-area other-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>컨설팅 및 검증 실적</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->

	<!-- About Area Starts -->
	<section class="content">
	    <div class="container">
			<form id="content_form" method="post" action="update.ic">
				<span id="contents"> ${contents[0].text } </span>
				<span id="updateButton">
					<c:if test="${!empty loginUser }">
						<div style="text-align:center;">
							<input type="button" id="update" value="수정">
						</div>
						<br>
					</c:if>
				</span>
				<input type="hidden" name="id" value="${contents[0].id }">
			</form>
		</div>
	</section>

	<!-- About Area End -->

	<jsp:include page="../../common/footer.jsp" />
	<!-- footer -->
</body>
