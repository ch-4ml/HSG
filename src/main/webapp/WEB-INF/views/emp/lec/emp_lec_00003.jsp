<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<%@ include file="../../common/head.jsp" %>
<script>
$(function() {
	$("#delete").click(function() {
  		var id = $("#id").val();
  		if(!confirm("삭제하시겠습니까?")) return false;
  		else location.href="delete.el?id=" + id;
  	});
});

$(function() {
	$("#apply").click(function() {
  		location.href="sendForm.el";
  	});	
});
  	
  	
$(function() {
   	$("#update").click(function() {
   		var id = $("#id").val();
   		location.href="updateView.el?id=" + id;
   	});
});

$(function() {
	$("#list").click(function() {
		location.href="view.el";
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
    <section id="two" class="wrapper style2 alt content-center">
	    <div><h2>${c.title }</h2></div>
	    <div class="inner">
    		<div class="contents">
   				<br><br>
   				<img src="<%= uploadPath %>${c.contents }" alt="">
				<br><br>
				${c.text }
				<br><br>
    		</div>
    		<br>
    		<input type="button" id="list" value="목록">
    		<input type="button" id="apply" value="지원">
    		<c:if test="${!empty loginUser }">
    			<input type="button" id="update" value="수정">
    			<input type="button" id="delete" value="삭제">
    		</c:if>
    		<input type="hidden" id="id" name="id" value="${c.id }">
	    </div>
	</section>
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>