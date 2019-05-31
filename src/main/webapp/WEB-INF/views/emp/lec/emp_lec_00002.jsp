<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
<%@ include file="../../common/tinymce.jsp" %>

<script type="text/javascript">
	$(function() {
		
		/* validation 처리 */
		$("#insertBtn").click(function() {
			
			var title = $("#title").val();
			var content = $("#content").val();
			
			if(name == ""){
				return alert("제목을 입력해주세요.");
			} else if(content == ""){
				return alert("내용을 입력해주세요.");
			}
			
			return $("#insertForm").submit();
			
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
                    <h1>강사 채용 등록</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <form id="insertForm" action="insert.el" method="post">
		<input type="text" id="title" name="title" placeholder="제목">
		<textarea id="text" id= "content" name="text" placeholder="내용을 추가하세요."></textarea>
		<input type="button" id="insertBtn" value="추가">
	</form>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>