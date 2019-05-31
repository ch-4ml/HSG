<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>

    $(function() {
    	$("#update").click(function() {
    		var oldContent = $('#content').html();
    		$("#content").html("<textarea id='text' name='text'>" + oldContent + "</textarea><input type='submit' value='수정'>");
    		tinymce.init({
    		    selector: 'textarea',
    		    menubar: false,
    		    language_url: 'tinymce/ko_KR.js',
    		    plugins: ['autolink autosave code link media table textcolor autoresize hr image imagetools powerpaste fullpage'],
    		    toolbar: "undo redo | fontselect fontsizeselect | forecolor bold underline italic code | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table link media code hr",
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
					<h1>조직 및 사업분야</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->

	<!-- About Area Starts -->

	<form id="content_form" method="post" action="update.io">
		<span id="content"> ${contents[0].text } </span>
		<span id="updateButton">
			<c:if test="${!empty loginUser }">
				<div style="text-align:center;">
					<input type="button" id="update" value="수정">
				</div>
			</c:if>
		</span>
		<input type="hidden" name="id" value="${contents[0].id }">
	</form>

	<!-- About Area End -->

	<jsp:include page="../../common/footer.jsp" />
	<!-- footer -->
</body>
