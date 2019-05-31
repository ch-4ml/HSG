<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>
<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="./resources/css/main2.css" />

<%
		String PATH = request.getContextPath();
	%>

<jsp:include page="../../common/head.jsp" />

<script type="text/javascript">
    	function updateBtnClickEvent(id) {
	    	window.location.assign('<%=PATH %>/updateView.ib?id='+id);
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
	
		<!-- Two -->
		
			<section id="two" class="wrapper style2 alt">
			<div><h1><center>[ 출판 도서 ]</center></h1><br></div>
				<div class="inner">
					<c:forEach var="content" items="${contents}">
					<c:if test="${content.category eq 1 }">
					
					<div class="spotlight">
						<div class="image">
							<img src="../../../../resources/uploadFiles/itrbok_upload_file/${content.image}" alt="" />
						</div>
						<div class="content">
							<h3>${content.title}</h3>
							<p>${content.text}</p>
							<c:if test="${!empty loginUser }">
							<ul class="actions">
								<li><a onclick="updateBtnClickEvent(${content.id})"  class="button alt">수정</a></li><p>
							</ul>
							</c:if>
						</div>
					</div>
					</c:if>
					</c:forEach>

				<c:if test="${!empty loginUser }">
					<ul class="actions special">
						<li><a href="<%=PATH %>/insertView.ib" class="button alt">추가하기</a></li>
					</ul>
				</c:if>
				</div>
			</section>
			<p>
			<section id="two" class="wrapper style2 alt">
			<div><h1><center>[ 특허 ]</center></h1><br></div>
				<div class="inner">
					<c:forEach var="content" items="${contents}">
					<c:if test="${content.category eq 2 }">
					
					<div class="spotlight">
						<div class="image">
						<img src="../../../../resources/uploadFiles/itrbok_upload_file/test.jpg" alt="" />
							<!-- <img src="../../../../resources/uploadFiles/itrbok_upload_file/${content.image}" alt="" /> -->
						</div>
						<div class="content">
							<h3>${content.title}</h3>
							<p>${content.text}</p>
							<c:if test="${!empty loginUser }">
							<ul class="actions">
								<li><a onclick="updateBtnClickEvent(${content.id})" class="button alt">수정</a></li><p>
							</ul>
							</c:if>
						</div>
					</div>
					</c:if>
					</c:forEach>
				<c:if test="${!empty loginUser }">
					<ul class="actions special">
						<li><a href="<%=PATH %>/insertView.ib" class="button alt">추가하기</a></li>
					</ul>
				</c:if>
				</div>
			</section>

	<jsp:include page="../../common/footer.jsp" />

	</body>