<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<%
	String PATH = request.getContextPath();
%>

<jsp:include page="../../common/head.jsp" />

<script type="text/javascript">
    	function updateBtnClickEvent(id) {
	    	window.location.assign('<%=PATH%>/updateView.ib?id='+id);
		}
</script>
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
	<section id="two" class="wrapper style2 alt">
		<div style="text-align:center;">
			<h1>
				[ 출판 도서 ]
			</h1>
			<br>
		</div>
		<div class="inner">
			<c:forEach var="content" items="${contents}">
				<c:if test="${content.category eq 1 }">

					<div class="spotlight">
						<div class="image">
							<img
								src="http://tbsko.cafe24.com/resources/uploadFiles/itrbok_upload_file/${content.image}"
								alt="" />
						</div>
						<div class="content">
							<h3>${content.title}</h3>
							<p>${content.text}</p>
							<c:if test="${!empty loginUser }">
								<ul class="actions">
									<li><a onclick="updateBtnClickEvent(${content.id})"
										class="button alt">수정</a></li>
								</ul>
							</c:if>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${!empty loginUser }">
				<ul class="actions special">
					<li><a href="<%=PATH%>/insertView.ib" class="button alt">추가하기</a></li>
				</ul>
			</c:if>
		</div>
	</section>
	<section id="two" class="wrapper style2 alt">
		<div style="text-align:center;">
			<h1>
				[ 특허 ]
			</h1>
			<br>
		</div>
		<div class="inner">
			<c:forEach var="content" items="${contents}">
				<c:if test="${content.category eq 2 }">

					<div class="spotlight">
						<div class="image">
							<img
								src="http://tbsko.cafe24.com/resources/uploadFiles/itrbok_upload_file/${content.image}"
								alt="" />
							<!-- <img src="../../../../resources/uploadFiles/itrbok_upload_file/${content.image}" alt="" /> -->
						</div>
						<div class="content">
							<h3>${content.title}</h3>
							<p>${content.text}</p>
							<c:if test="${!empty loginUser }">
								<ul class="actions">
									<li><a onclick="updateBtnClickEvent(${content.id})"
										class="button alt">수정</a></li>
								</ul>
							</c:if>
						</div>
						<br>
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${!empty loginUser }">
				<ul class="actions special">
					<li><a href="<%=PATH%>/insertView.ib" class="button alt">추가하기</a></li>
				</ul>
			</c:if>
		</div>
	</section>

	<jsp:include page="../../common/footer.jsp" />

</body>