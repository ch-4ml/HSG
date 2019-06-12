<!-- MOOC 수정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<%@ include file="../../common/head.jsp" %>
<script>
	$(function() {
		
		/* validation 처리 */
		$("#updateBtn").click(function() {
			
			var title = $("#title").val();
			var category = $("#category").val();
			
			if(title == ""){
				return alert("제목을 입력해주세요.");
			} else if(!category){
				return alert("카테고리를 선택해주세요.");
			}
			
			return $("#updateForm").submit();
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
                    <h1>MOOC 수정</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
	<section class="content content-center">
	    <div class="container">
		    <form id="updateForm" action="update.ee" method="post" enctype="multipart/form-data">
		    	<section>
				<span class="input input--hoshi">
					<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${c.title }">
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
						<span class="input__label-content input__label-content--hoshi">MOOC 제목</span>
					</label>
				</span>
				<select class="cs-select cs-skin-underline double" id="category" name="category">
					<c:choose>
						<c:when test="${c.category eq '1' }">
							<option value="1" selected>빅데이터</option>
							<option value="2">인공지능</option>
							<option value="3">기타</option>
						</c:when>
						<c:when test="${c.category eq '2' }">
							<option value="1">빅데이터</option>
							<option value="2" selected>인공지능</option>
							<option value="3">기타</option>
						</c:when>
						<c:otherwise>
							<option value="1">빅데이터</option>
							<option value="2">인공지능</option>
							<option value="3" selected>기타</option>
						</c:otherwise>
					</c:choose>
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
					<input class="input__field input__field--hoshi" type="text" id="url" name="url" value="${c.url }">
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="url">
						<span class="input__label-content input__label-content--hoshi">동영상 URL</span>
					</label>
				</span>
				<br>
				<span class="input__textarea input--hoshi">
					<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text">${c.text }</textarea>
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
						<span class="input__label-content input__label-content--hoshi">설명</span>
					</label>
				</span>
				</section>
				<input type="hidden" id="id" name="id" value="${c.id }">
				<div style="text-align:center;">
					<input type="button" id="updateBtn" value="수정">
				</div>
				<br>
			</form>
		</div>
	</section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>