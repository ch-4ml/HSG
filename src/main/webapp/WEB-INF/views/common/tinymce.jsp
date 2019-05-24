<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

</body>
</html>