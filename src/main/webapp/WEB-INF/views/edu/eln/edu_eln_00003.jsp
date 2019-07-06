<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
<link href="./resources/css/detail-default.css" rel="stylesheet" type="text/css" media="all" />
<title>HS글로벌</title>
<%@ include file="../../common/head.jsp" %>
<script>
    $(function() {
    	$("#delete").click(function() {
    		var id = $("#id").val();
    		if(!confirm("삭제하시겠습니까?")) return false;
    		else location.href="delete.ee?id=" + id;
    	});
    });
    
    $(function() {
    	$("#update").click(function() {
    		var id = $("#id").val();
    		location.href="updateView.ee?id=" + id;
    	});
    });
    
    $(function() {
    	$("#list").click(function() {
    		location.href="view.ee";
    	});
    });
 
    $(function() {
		var con = $('iframe').parent().attr('style');
		if(con.includes("text-align: center;")) {
			$('iframe').parent('p').attr('align', 'middle');
			$('iframe').parent('p').removeAttr('style');
		}// your code here
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
                    <h1>E-Learning 상세</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <section id="two" class="wrapper style2 alt content-center">
	    <div><h2>${c.title }</h2></div>
   		<div class="inner">
    		<form action="update.ee" method="post">
				<div class="contents">
					<span id="contents">
					<br><br>
						<a href="redirect.ee?url=${c.url }" target="_blank">
							<img src="<%= uploadPath %>${c.contents }" alt="">
						</a>
						<br><br>
						${c.text }
						<br><br>
					</span>
				</div>
				<div style="text-align: center;">
					<input type="button" id="list" value="목록">
					<c:if test="${!empty loginUser }">
						<input type="button" id="update" value="수정">
						<input type="button" id="delete" value="삭제">
					</c:if>
				</div>
				<input type="hidden" id="category" name="category" value="${c.category }">
				<input type="hidden" id="id" name="id" value="${c.id }">
    		</form>
   		</div>
    </section>	    
	
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>