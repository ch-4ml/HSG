<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
<script>
	$(function() {		
		/* validation 처리 */
		$("#updateBtn").click(function() {
			
			var title = $("#title").val();
			var text = $("#text").val();
			
			if(title == ""){
				return alert("제목을 입력해주세요.");
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
                    <h1>강사 채용 수정</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <section class="content content-center">
	    <div class="container">
		    <form id="updateForm" action="update.el" method="post" enctype="multipart/form-data">
			    <span class="input input--hoshi">
					<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${c.title }">
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
						<span class="input__label-content input__label-content--hoshi">채용 공고 제목</span>
					</label>
				</span>
				<span class="input input--hoshi input--filled">
			    	<input class="input__field input__field--hoshi" type="file" id="file" name="file" accept=".jpg, .jpeg, .png .gif">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="file">
			    		<span class="input__label-content input__label-content--hoshi">사진 첨부</span>
			    	</label>
				</span>
				<br>
				<span class="input__textarea input--hoshi">
					<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text">${c.text }</textarea>
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
						<span class="input__label-content input__label-content--hoshi">내용</span>
					</label>
				</span>
				<br>
				<div style="text-align:center;">
					<input type="button" id="updateBtn" value="수정">
				</div>
				<input type="hidden" id="id" name="id" value="${c.id }">
			</form>
		</div>
    </section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>