<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<%@ include file="../../common/head.jsp"%>
</head>
<body>
	<jsp:include page="../../common/header-content.jsp" />
	<!-- Banner Area Starts -->
	<section class="banner-area other-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>출판 도서/특허</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->
	<section id="one" class="wrapper style2 alt">
		<div style="text-align: center;">
			<h2>출판 도서</h2>
		</div>
		<br> <br>
		<div class="inner">
			<c:if test="${!empty loginUser }">
				<div style="text-align: center;">
					<ul class="actions special">
						<li><input type="button" id="insert" onclick="location.href='insertView.ib'" value="추가"></li>
					</ul>
				</div>
				<br>
				<br>
			</c:if>
			<c:forEach var="c" items="${cs}">
				<c:if test="${c.category eq 1 }">
					<table class="simple">
						<tr class="portrait">
							<td class="portrait-image">
							<c:choose>
							<c:when test="${!empty loginUser }">
							<a href="updateView.ib?id=${c.id }">
							</c:when>
							<c:otherwise>
							<a href="redirect.ib?url=${c.url }" target="_blank">
							</c:otherwise>
							</c:choose>
							<img src="<%= uploadPath %>${c.contents}" alt="" />
							</a>
							</td>
							<td class="portrait-contents">
							<c:choose>
							<c:when test="${!empty loginUser }">
							<a href="updateView.ib?id=${c.id }">
							</c:when>
							<c:otherwise>
							<a href="redirect.ib?url=${c.url }" target="_blank">
							</c:otherwise>
							</c:choose>
									<h3>${c.title}</h3>
									<p>${c.text}</p>
							</a></td>
						</tr>
					</table>
				</c:if>
			</c:forEach>
		</div>
	</section>
	<section id="two" class="wrapper style2 alt">
		<div style="text-align: center;">
			<h2>특허</h2>
		</div>
		<br> <br>
		<div class="inner">
			<c:forEach var="c" items="${cs}">
				<c:if test="${c.category eq 2 }">
					<table class="simple">
						<tr class="portrait">
							<td class="portrait-image">
							<c:choose>
							<c:when test="${!empty loginUser }">
							<a href="updateView.ib?id=${c.id }">
							</c:when>
							<c:otherwise>
							<a href="redirect.ib?url=${c.url }" target="_blank">
							</c:otherwise>
							</c:choose>
							<img src="<%= uploadPath %>${c.contents}" alt="" />
							</a></td>
							<td class="portrait-contents">
							<c:choose>
							<c:when test="${!empty loginUser }">
							<a href="updateView.ib?id=${c.id }">
							</c:when>
							<c:otherwise>
							<a href="redirect.ib?url=${c.url }" target="_blank">
							</c:otherwise>
							</c:choose>
									<h3>${c.title}</h3>
									<p>${c.text}</p>
							</a></td>
						</tr>
					</table>
				</c:if>
			</c:forEach>
		</div>
	</section>
	<jsp:include page="../../common/footer.jsp" />
</body>