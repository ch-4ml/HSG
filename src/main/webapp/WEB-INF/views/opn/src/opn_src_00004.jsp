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
<%@ include file="../../common/tinymce.jsp" %>
<script>
	$(function() {
		
		/* validation 처리 */
		$("#updateBtn").click(function() {
			
			var title = $("#title").val();
			var category = $("#category").val();
			
			if(title == ""){
				return alert("제목을 입력해주세요.");
			} 			
			return $("#content_form").submit();
			
		});
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
                    <h1>오픈소스</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
	<section class="content content-center">
	    <div class="container">
		    <form id="content_form" action="update.os" method="post" enctype="multipart/form-data">
				<section>	
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${c.title }">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
							<span class="input__label-content input__label-content--hoshi">제목</span>
						</label>
					</span>
					 
					<span class="input input--hoshi input--filled">
						<input class="input__field input__field--hoshi" type="file" id="file" name="file">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="name">
							<span class="input__label-content input__label-content--hoshi">첨부파일</span>
						</label>
					</span>
					 
					<textarea id="content" name="contents">${c.contents}</textarea>
					<input type="hidden" id="id" name="id" value="${c.id }">
					<input type="button" id="updateBtn" value="추가">
				</section>
			</form>
		</div>
	</section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>