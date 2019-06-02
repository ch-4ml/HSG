<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
<link href="./resources/css/detail-default.css" rel="stylesheet" type="text/css" media="all" />
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
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
    </script>
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>MOOC 상세</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- About Area Starts -->
    <form action="update.ee" method="post">
    <div class="wrapper">
	<div id="banner" class="container">${content.text }</div>
	<div id="welcome" class="container">
    	
	<div class="title">
	  <h2>${content.title }</h2> <h4>${content.postDate}</h4>
		</div>
		<p> 이 부분에 내용을 넣으면 될 것 같습니다. <BR> 영상과 내용을 따로 올릴 수 있도록 만들어주시면 감사하겠습니다.</p>
	</div>
	</div>
	<input type="hidden" id="category" name="category" value="${content.category }">
	<input type="hidden" id="id" name="id" value="${content.id }">
    </form>
    <div style="text-align:center;">
	    <!-- 
		    <span id="content"><br>
			   	<span id="title">${content.title }</span>
			   	${content.postDate }
			    <span id="text">${content.text }</span>
				<input type="hidden" id="category" name="category" value="${content.category }">
			</span>
		    <input type="hidden" id="id" name="id" value="${content.id }">
	    </form> -->
	    <c:if test="${!empty loginUser }">
			<input type="button" id="update" value="수정">
			<input type="button" id="delete" value="삭제">
		</c:if>
	    <input type="button" id="list" value="목록 보기">
    </div>
    	
	
    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>