<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<script>   	
function updateBtnClickEvent(id) {
   	location.href='updateView.ib?id=' + id;
}
</script>
<script>
function deleteBtnClickEvent(id) {
	var sign = confirm("정말로 삭제하시겠습니까?");
	
	if(sign == true){
		location.href="delete.ib?id=" + id;
	}
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
	<c:if test="${!empty loginUser }">
	<br>
	<br>
		<div style="text-align: center;">
			<ul class="actions special">
				<li><a href="insertView.ib" class="button alt">추가하기</a></li>
			</ul>
		</div>
		<br>
		<br>
	</c:if>
	<section id="two" class="wrapper style2 alt">
		<div style="text-align: center;">
			<h2>[ 출판 도서 ]</h2>
			<br>
		</div>
		<div class="inner">
			<c:forEach var="ibd" items="${ibds}">
				<a href="redirect.ib?url=${ibd.url }">
				<c:if test="${ibd.category eq 1 }">
					<div class="spotlight">
						<div class="image">
							<img src="http://tbsko.cafe24.com/resources/uploadFiles/itrbok_upload_file/${ibd.image}" alt="" />
						</div>
						<div class="ibd">
							<h3>${ibd.title}</h3>
							<p>${ibd.text}</p>
							<c:if test="${!empty loginUser }">
								<ul class="actions">
									<li><a onclick="updateBtnClickEvent(${ibd.id})" class="button alt">수정</a><a onclick="deleteBtnClickEvent(${ibd.id})" class="button alt">삭제</a></li>
								</ul>
							</c:if>
						</div>
					</div>
				</c:if>
				</a>
			</c:forEach>
		</div>
	</section>
	<section id="two" class="wrapper style2 alt">
		<div style="text-align: center;">
			<h2>[ 특허 ]</h2>
			<br>
		</div>
		<div class="inner">
			<c:forEach var="ibd" items="${ibds}">
				<c:if test="${ibd.category eq 2 }">
					<div class="spotlight">
						<div class="image">
							<img src="http://tbsko.cafe24.com/resources/uploadFiles/itrbok_upload_file/${ibd.image}" alt="" />
							<!-- <img src="../../../../resources/uploadFiles/itrbok_upload_file/${ibd.image}" alt="" /> -->
						</div>
						<div class="ibd">
							<h3>${ibd.title}</h3>
							<p>${ibd.text}</p>
							<c:if test="${!empty loginUser }">
								<ul class="actions">
									<li><a onclick="updateBtnClickEvent(${ibd.id})" class="button alt">수정</a><a onclick="deleteBtnClickEvent(${ibd.id})" class="button alt">삭제</a></li>
								</ul>
							</c:if>
						</div>
						<br>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</section>

	<jsp:include page="../../common/footer.jsp" />

</body>