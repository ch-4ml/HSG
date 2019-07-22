<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>	
    <%@ include file="../../common/head.jsp" %>
    <script>
    	$(function() {
			$("#insertBtn").click(function() {
				var title = $("#title").val();
				var category = $("#category").val();
				var url = $("#url").val();
				var text = $("#text").val();
				var file = $("#file").val();
				
				if(title == ""){
					return alert("제목을 입력해주세요.");
				} else if(category == "") {
					return alert("카테고리를 선택해주세요.");
				} else if(url == "") {
					return alert("연결할 주소를 입력해주세요.");
				} else if(text == ""){
					return alert("내용을 입력해주세요.");
				} else if(file == "") {
					return alert("파일을 선택해주세요.");
				} else {
					return $("#insertForm").submit();
				}
			});
			$("#text").keydown();
		});
    </script>
    <script>
function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
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
                    <h1 class="text-shadow-banner">출판도서/특허 추가</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
    
    <!-- Content Area Start -->
	<section class="content content-center">
		<div class="container">
		    <form id="insertForm" action="insert.ib" method="post" enctype="multipart/form-data">
				<section>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="title" name="title"">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
							<span class="input__label-content input__label-content--hoshi">출판도서 / 특허명</span>
						</label>
					</span>
					<select class="cs-select cs-skin-underline double" id="category" name="category">
						<option value="" disabled selected>카테고리 선택</option>
						<option value="1">도서</option>
						<option value="2">특허</option>
					</select>
				</section>
				<section>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="url" name="url">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="url">
							<span class="input__label-content input__label-content--hoshi">연결할 URL</span>
						</label>
					</span>
					<span class="input input--hoshi input--filled">
				    	<input class="input__field input__field--hoshi" type="file" id="file" name="file" accept=".jpg, .jpeg, .png .gif">
				    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="file">
				    		<span class="input__label-content input__label-content--hoshi">사진 첨부</span>
				    	</label>
					</span>
					</section>
				<section>
				<br>
				<span class="input__textarea input--hoshi">
					<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="text" name="text"></textarea>
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="text">
						<span class="input__label-content input__label-content--hoshi">간단한 설명</span>
					</label>
				</span>
				<br>
				<div align="center">
					<input type="button" id="insertBtn" value="추가">
				</div>
				</section>
			</form>
		</div>
	</section>
	<!-- Content Area End -->
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>