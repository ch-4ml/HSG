<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<%@ include file="../../common/head.jsp"%>

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

	<section id="one" class="wrapper style2 alt">
		<div style="text-align: center;">
			<h2>출판 도서</h2>
		</div>
		<br>
		<br>
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
					<div class="spotlight">
						<div class="image">
							<img src="<%= uploadPath %>${c.contents}" alt="" />
						</div>
						<div class="list">
							<a href="redirect.ib?url=${c.url }" target="_blank">
								<div class="list-content">
									<h3>${c.title}</h3>
									<br>
									<p>${c.text}</p>
								</div>
							</a>
							<div class="list-button">
								<c:if test="${!empty loginUser }">
									<ul class="actions">
										<li><input type="button" onclick="updateBtnClickEvent(${c.id})" value="수정"></li>
										<li><input type="button" onclick="deleteBtnClickEvent(${c.id})" value="삭제"></li>
									</ul>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</section>
	<section id="two" class="wrapper style2 alt">
		<div style="text-align: center;">
			<h2>특허</h2>
		</div>
		<br>
		<br>
		<div class="inner">
			<c:forEach var="c" items="${cs}">
				<c:if test="${c.category eq 2 }">
					<div class="spotlight">
						<div class="image">
							<img src="<%= uploadPath %>${c.contents}" alt="" />
						</div>
						<div class="list">
							<div class="list-content">
								<h3>${c.title}</h3>
								<p>${c.text}</p>
							</div>
							<div class="list-button">
								<c:if test="${!empty loginUser }">
									<ul class="actions">
										<li><input type="button" onclick="updateBtnClickEvent(${c.id})" value="수정"></li>
										<li><input type="button" onclick="deleteBtnClickEvent(${c.id})" value="삭제"></li>
									</ul>
								</c:if>
							</div>
						</div>
						<br>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</section>
	<jsp:include page="../../common/footer.jsp" />
</body>