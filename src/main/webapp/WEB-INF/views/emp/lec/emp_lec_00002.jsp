<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>
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
</script>
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>강사 채용 등록</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <form action="insert.el" method="post">
		<input type="text" name="title" placeholder="제목">
		<textarea id="text" name="text" placeholder="내용을 추가하세요."></textarea>
		<input type="submit" value="추가">
	</form>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>