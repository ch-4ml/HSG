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
	$("#delete").click(function() {
  		var id = $("#id").val();
  		if(!confirm("삭제하시겠습니까?")) return false;
  		else location.href="delete.el?id=" + id;
  	});
});

$(function() {
	$("#list").click(function() {
  		location.href="view.el";
  	});	
});
  	
  	
$(function() {
   	$("#update").click(function() {
   		var id = $("#id").val();
   		location.href="updateView.el?id=" + id;
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
                    <h1>강사 채용 상세</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <section class="content">
    <div class="container">
	<form action="update.el", method="post">
	    <span id="content">
		    ${content.postDate }
		   	<span id="title">${content.title }</span>
		    <span id="text">${content.text }</span>
		    <div class="content-center">
			    <a href="sendForm.el"><input type="button" value="지원하기"></a>
	    		<input type="button" id="list" value="목록 보기">
				<c:if test="${!empty loginUser }">
					<input type="button" id="update" value="수정">
					<input type="button" id="delete" value="삭제">
				</c:if>
			</div>
		</span>
	    <input type="hidden" id="id" name="id" value="${content.id }">
    </form>
    </div>
    </section>


    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>