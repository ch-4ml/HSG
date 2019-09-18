<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    <%@ include file="../../common/head.jsp" %>
<script>
function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
</script>
<script>
   	$(function() {
		$("#update").click(function() {
			var title = $("#title").val();
			var url = $("#url").val();
			var origin = $("#origin").val();
			var text = $("#content").val();
			var file = $("#file").val();
			
			if(title == ""){
				return alert("제목을 입력해주세요.");
			} else if(url == "") {
				return alert("연결할 주소를 입력해주세요.");
			} else if(text == ""){
				return alert("내용을 입력해주세요.");
			} else {
				return $("#updateForm").submit();
			}
		});
	});
</script>
<script>
$(function() {
	$("#delete").click(function() {
		var id = $("#id").val();
		if(!confirm("삭제하시겠습니까?")) return false;
		else location.href="delete.ib?id=" + id;
	});
});
</script>
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>
tinymce.init({
    selector: 'textarea#content',
    menubar: false,
    plugins: ['autolink autosave code link textcolor autoresize hr fullpage'],
    toolbar: "undo redo | forecolor bold underline italic code | link hr",
    fullpage_default_font_family: "NanumSquareRound"
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
                    <h1 class="text-shadow-banner">출판도서/특허 수정</h1>
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
			<input type="hidden" name="id" id="id" value="${c.id} ">
			<span class="input input--hoshi">
				<input class="input__field input__field--hoshi" type="text" id="title" name="title" value="${c.title }">
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
					<span class="input__label-content input__label-content--hoshi">출판도서 / 특허명</span>
				</label>
			</span>
			<select class="cs-select cs-skin-underline double" id="category" name="category">
				<c:choose>
					<c:when test="${c.category eq '1'}">
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
			<section>
			<span class="input input--hoshi">
				<input class="input__field input__field--hoshi" type="text" id="origin" name="origin" value="${c.origin }">
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="origin">
					<span class="input__label-content input__label-content--hoshi">출판사, 출판년도</span>
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
				<input class="input__field input__field--hoshi" type="text" id="url" name="url" value="${c.url }">
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="url">
					<span class="input__label-content input__label-content--hoshi">연결할 URL (www.abcde.com)</span>
				</label>
			</span>
			<br>
			<textarea id="content" name="text">${c.text }</textarea>
			<!-- 
			<span class="input__textarea input--hoshi">
				<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text">${c.text}</textarea>
				<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
					<span class="input__label-content input__label-content--hoshi">간단한 설명</span>
				</label>
			</span>
			 -->
			<br>
			<div align="center">
				<input type="button" id="update" value="수정">
				<input type="button" id="delete" value="삭제">
			</div>
			</section>
		</form>
	</div>
	</section>
	<!-- Content Area End -->
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
    
</body>
