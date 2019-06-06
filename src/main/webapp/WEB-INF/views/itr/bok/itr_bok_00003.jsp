<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    
    <jsp:include page="../../common/head.jsp" />
    <%
		String PATH = request.getContextPath();
	%>
    <script>
function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
</script>
    <script>
    	$(function() {
			$("#updateBtn").click(function() {
				var title = $("#title").val();
				var url = $("#url").val();
				var text = $("#text").val();
				var file = $("#file").val();
				
				if(title == ""){
					return alert("제목을 입력해주세요.");
				} else if(url == "") {
					return alert("연결할 주소를 입력해주세요.");
				} else if(text == ""){
					return alert("내용을 입력해주세요.");
				} else if(file == "") {
					return alert("파일을 선택해주세요.");
				} else {
					return $("#updateForm").submit();
				}
			})
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
                    <h1>출판도서/특허 수정</h1>
                </div>
            </div>
        </div>
    </section>
	<!-- Banner Area End -->
	
	<!-- Content Area Start -->
	<section class="content content-center">
	<div class="container">
	    <form id="updateForm" action="update.ib" method="post" enctype="multipart/form-data">
			<section>
			<input type="hidden" name="id" value="${ibd.id} ">
			<span class="input input--hoshi">
				<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${ibd.title }">
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
					<span class="input__label-content input__label-content--hoshi">출판도서 / 특허명</span>
				</label>
			</span>
			<br>
			<select class="cs-select cs-skin-underline" id="category" name="category">
				<c:choose>
					<c:when test="${ibd.category eq '1'}">
						<option value="1" selected>도서</option>
						<option value="2">특허</option>
					</c:when>
					<c:otherwise>
						<option value="1">도서</option>
						<option value="2" selected>특허</option>
					</c:otherwise>
				</c:choose>
			</select>
			</section>
			<section class="related">
			<span class="input input--hoshi">
				<input class="input__field input__field--hoshi" type="text" id="url" name="url" value="${ibd.url }">
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="url">
					<span class="input__label-content input__label-content--hoshi">연결할 URL (www.abcde.com)</span>
				</label>
			</span>
			<br>
			<span class="input input--hoshi">
				<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text">${ibd.text}</textarea>
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
					<span class="input__label-content input__label-content--hoshi">간단한 설명</span>
				</label>
			</span>
			<br>
			<span class="input input--hoshi input--filled">
		    	<input class="input__field input__field--hoshi" type="file" id="file" name="file" accept=".jpg, .jpeg, .png">
		    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="file">
		    		<span class="input__label-content input__label-content--hoshi">사진 첨부</span>
		    	</label>
			</span>
			<br>
			<div align="center">
				<input type="button" id="updateBtn" value="수정">
			</div>
			</section>
		</form>
	</div>
	</section>
	<!-- Content Area End -->
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
    
</body>
