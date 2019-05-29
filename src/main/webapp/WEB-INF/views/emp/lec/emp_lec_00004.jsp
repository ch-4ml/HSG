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
function fileCheck(file)
{
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
    <form action="send.el" name="form" method="post" enctype="multipart/form-data">
	    <input type="text" name="name" placeholder="지원자 성함" required><br>
	    <input type="text" name="field" placeholder="분야" required><br>
	    <input type="text" name="phone" placeholder="연락처" required><br>
	    <input type="text" name="email" placeholder="이메일" required><br>
	    <textarea name="career" placeholder="경력사항을 자유롭게 기재해주세요."></textarea><br>
	    <input type="file" name="file" multiple="multiple"><br>
	    <input type="button" value="메일 보내기" onclick="fileCheck(this.form.file)">
    </form>
    
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>