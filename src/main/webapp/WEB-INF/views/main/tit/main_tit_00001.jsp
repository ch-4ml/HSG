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
				var text = $("#text").val();
				
				if(text == "") {
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
					<h1>배너 수정</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->
	<div style="text-align: center;">
		한 줄 띄우기를 원하시는 경우 엔터 대신 &lt;br&gt;을 삽입해주세요.
	</div>
	<!-- About Area Starts -->
	<section class="content content-center">
		<div class="container">
			<form id="updateForm" action="updateTit.ma" method="post">		
				<section class="related">
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