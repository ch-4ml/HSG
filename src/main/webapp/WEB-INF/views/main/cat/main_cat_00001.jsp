<!-- MOOC 추가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
    <script>
    	$(function() {
			$("#updateBtn").click(function() {
				var title = $("#title").val();
				var category = $("#category").val();
				
				if(title == ""){
					return alert("제목을 입력해주세요.");
				} else if(text == "") {
					return alert("내용을 입력해주세요.");
				} else {
					return $("#updateForm").submit();
				}
			});
			$("#text").keydown();
		});
    </script>
</head>
<body>
	<jsp:include page="../../common/header-content.jsp" />
	<!-- Banner Area Starts -->
	<section class="banner-area other-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>메인 수정</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->

	<!-- About Area Starts -->
	<section class="content content-center">
		<div class="container">
			<form id="updateForm" action="updateCat.ma" method="post">		
				<section class="related">
					<span class="input__textarea input--hoshi">
				    	<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${c.title }">
				    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
				    		<span class="input__label-content input__label-content--hoshi">제목</span>
				    	</label>
					</span>
					<span class="input__textarea input--hoshi">
						<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text">${c.text }</textarea>
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
							<span class="input__label-content input__label-content--hoshi">내용</span>
						</label>
					</span>
					<br>
					<input type="hidden" name="id" value="${c.id }">
					<div style="text-align: center;">
						<input type="button" id="updateBtn" value="수정">
					</div>
				</section>
			</form>
		</div>
	</section>
	
	<!-- About Area End -->

	<jsp:include page="../../common/footer.jsp" />
	<!-- footer -->
</body>
</html>