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
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>

    $(function() {
    	$("#update").click(function() {
    		var oldTitle = $('#title').html();
    		var oldText = $('#text').html();
    		var oldCategory = $('#cat').val();
    		$("#content").html(
    				"<input type='text' name='title' placeholder='제목' value='" + oldTitle + "'>" + 
    				"<select name='category'>" + 
    				"<option value='1'>빅데이터</option>" + 
    				"<option value='2'>인공지능</option>" + 
    				"<option value='3'>기타</option>" + 
    				"</select>" + 
    				"<textarea id='text' name='text'>" + oldText + "</textarea><input type='submit' value='수정'>");
    		tinymce.init({
    	        selector: 'textarea',
    	        menubar: false,
    	        language_url: 'tinymce/ko_KR.js',
    	        plugins: ['autolink autosave code link media table textcolor autoresize hr image imagetools powerpaste fullpage'],
    	        toolbar: "undo redo | styleselect fontselect fontsizeselect | forecolor bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table link media code hr",
    	        fullpage_default_font_family: "NanumSquareRound",
    	        font_formats: "나눔스퀘어라운드=NanumSquareRound;",
    	        fontsize_formats: "11px 12px 14px 16px 18px 24px 36px 48px"
    	    });
    		$("#updateButton").html("");
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
                    <h1>MOOC 등록</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <form action="update.ee", method="post">
	    <span id="content">
		    ${eln.postDate }
		   	<span id="title">${eln.title }</span>
		    <span id="text">${eln.text }</span>
			<c:if test="${!empty loginUser }">
				<input type="button" id="update" value="수정">
			</c:if>
			<input type="hidden" id="cat" name="cat" value="${eln.category }">
		</span>
	    <input type="hidden" name="id" value="${eln.id }">
    </form>
    	
	
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>