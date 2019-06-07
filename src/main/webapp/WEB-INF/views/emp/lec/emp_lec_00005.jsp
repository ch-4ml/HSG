<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
</head>
<body>
<script>
function fileCheck(file) {
	
	/* validation 처리 */
	var name = $("#name").val();
	var field = $("#field").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var files = $("#file").val();
	
	if(name == ""){
		return alert("지원자 성함을 입력해주세요.");
	} else if(field == ""){
		return alert("분야를 입력해주세요.");
	} else if(phone == ""){
		return alert("연락처를 입력해주세요.");
	} else if(email == ""){
		return alert("이메일을 입력해주세요.");
	} else if(files == ""){
		return alert("첨부파일을 입력해주세요.");
	}
	
	// 사이즈체크
	var maxSize  = 25 * 1024 * 1024
	var fileSize = 0;
	for(var i=0; i<file.files.length; i++) {
		fileSize = fileSize + file.files[i].size;
	}
	if(fileSize > maxSize) {
		alert("첨부파일 사이즈는 25MB 이내로 전송 가능합니다.");
		return;
	}
	document.form.submit();
}
</script>
<script>
function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
</script>

<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>강사 지원</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
    
    <!-- About Area Starts -->
    <section class="content content-center">
    	<div style="text-align:center;">
			<h2>강사 지원 메일 보내기</h2>
		</div>
		<div class="container">
		    <form action="send.el" name="form" method="post" enctype="multipart/form-data">
			    <span class="input input--hoshi">
			    	<input class="input__field input__field--hoshi" type="text" id="name" name="name">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="name">
			    		<span class="input__label-content input__label-content--hoshi">지원자 성함</span>
			    	</label>
			    </span>
			    <span class="input input--hoshi">
			    	<input class="input__field input__field--hoshi" type="text" id="field" name="field">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="field">
			    		<span class="input__label-content input__label-content--hoshi">분야</span>
			    	</label>
			    </span>
			    <br>
			    <span class="input input--hoshi">
			    	<input class="input__field input__field--hoshi" type="text" id="phone" name="phone">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="phone">
			    		<span class="input__label-content input__label-content--hoshi">연락처</span>
			    	</label>
			    </span>
			    <span class="input input--hoshi">
			    	<input class="input__field input__field--hoshi" type="email" id="email" name="email">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="email">
			    		<span class="input__label-content input__label-content--hoshi">이메일</span>
			    	</label>
			    </span>
			    <br>
			    <span class="input input--hoshi">
					<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="career" name="career"></textarea>
					<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="career">
						<span class="input__label-content input__label-content--hoshi">경력사항</span>
					</label>
				</span>
			    <span class="input input--hoshi input--filled">
			    	<input class="input__field input__field--hoshi" type="file" id="file" name="file" multiple="multiple">
			    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="file">
			    		<span class="input__label-content input__label-content--hoshi">첨부파일</span>
			    	</label>
			    </span>
			    <br>
				<div style="text-align:center;">
					<input type="button" onclick="fileCheck(this.form.file)" value="보내기">
				</div>	
		    </form>
    	</div>
    </section>
    
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>