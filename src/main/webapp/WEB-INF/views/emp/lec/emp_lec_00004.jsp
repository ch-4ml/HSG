<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
<%@ include file="../../common/tinymce.jsp" %>
<script>
	$(function() {		
		/* validation 처리 */
		$("#updateBtn").click(function() {
			
			var title = $("#title").val();
			var text = $("#text").val();
			
			if(title == ""){
				return alert("제목을 입력해주세요.");
			} else if(text == ""){
				return alert("내용을 입력해주세요.");
			}
			return $("#updateForm").submit();
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
                    <h1>강사 채용 수정</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <section class="content">
	    <div class="container">
		    <form id="updateForm" action="update.el" method="post">
			    <span class="input input--hoshi">
					<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${content.title }">
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
						<span class="input__label-content input__label-content--hoshi">채용 공고 제목</span>
					</label>
				</span>
				<textarea id="text" name="text" placeholder="내용을 추가하세요.">${content.text }</textarea>
				<br>
				<div style="text-align:center;">
					<input type="button" id="updateBtn" value="수정">
				</div>	
			</form>
		</div>
    </section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>