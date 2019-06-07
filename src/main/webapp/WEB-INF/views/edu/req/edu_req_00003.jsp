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
<script>
    $(function() {
    	$("#list").click(function() {
    		location.href="view.er";
    	});
    });
    
    $(function() {
    	$("#apply").click(function() {
    		var title = $("#title").val();
    		location.href="sendForm.er?title= " + title;
    	});
    });
    
    $(function() {
    	$("#update").click(function() {
    		var id = $("#id").val();
    		location.href="updateView.er?id=" + id;
    	});
    });
    
    $(function() {
    	$("#delete").click(function() {
    		var id = $("#id").val();
    		if(!confirm("삭제하시겠습니까?")) return false;
    		else location.href="delete.er?id=" + id;
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
                    <h1>교육 상세보기</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <section id="two" class="wrapper style2 alt">
    	<div style="text-align:center;"><h2>${content.title }</h2></div><br><br>
    	<div class="inner">
		    <form id="content_form" action="update.er" method="post">
			    <div class="contents">
			    	<span id="contents">${content.text }</span>
			    </div>
			    <br>
			    <div style="text-align: center;">
				    <input type="button" id="list" value="목록">
				    <input type="button" id="apply" value="신청">
					<c:if test="${!empty loginUser }">
						<input type="button" id="update" value="수정">
						<input type="button" id="delete" value="삭제">
					</c:if>
				</div>
				<input type="hidden" id="id" name="id" value="${content.id }">
				<input type="hidden" id="title" name="title" value="${content.title }">
		    </form>
	    </div>
	</section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>