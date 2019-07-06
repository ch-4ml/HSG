<!-- MOOC 추가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<%@ include file="../../common/head.jsp" %>
<script>
	$(function() {
		
		/* validation 처리 */
		$("#insertBtn").click(function() {
			
			var title = $("#title").val();
			var category = $("#category").val();
			
			if(title == ""){
				return alert("제목을 입력해주세요.");
			} else if(!category){
				return alert("카테고리를 선택해주세요.");
			}
			
			return $("#insertForm").submit();
			
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
                    <h1>E-Learning 등록</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
	<section class="content content-center">
	    <div class="container">
		    <form id="insertForm" action="insert.ee" method="post" enctype="multipart/form-data">
				<section>	
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="title" name="title">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
							<span class="input__label-content input__label-content--hoshi">MOOC 제목</span>
						</label>
					</span>
					<select class="cs-select cs-skin-underline double" id="category" name="category">
							<option value="" disabled selected>카테고리 선택</option>
							<option value="1">빅데이터</option>
							<option value="2">인공지능</option>
							<option value="3">기타</option>
					</select>
				</section>
				<section>
				<span class="input input--hoshi input--filled">
			    	<input class="input__field input__field--hoshi" type="file" id="file" name="file" accept=".jpg, .jpeg, .png .gif">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="file">
			    		<span class="input__label-content input__label-content--hoshi">썸네일</span>
			    	</label>
				</span>
				<span class="input input--hoshi">
					<input class="input__field input__field--hoshi" type="text" id="url" name="url">
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="url">
						<span class="input__label-content input__label-content--hoshi">동영상 URL</span>
					</label>
				</span>
				<br>
				<span class="input__textarea input--hoshi">
					<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text"></textarea>
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
						<span class="input__label-content input__label-content--hoshi">설명</span>
					</label>
				</span>
				<div style="text-align:center;">
					<input type="button" id="insertBtn" value="추가">
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