<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
   <%@ include file="../../common/head.jsp" %>
</head>
<body>
<script>
$(function() {
	$('#insertBtn').click(function() {
		let form = $('#insertForm');
		let formData = new FormData(form[0]);
		if($('#file').val()) {
			$.ajax({
				url: 'insert.ip',
				data: formData,
				dataType: 'json',
				processData: false, 
				contentType: false, 
				type: 'post',
				success: function(data) {
					$('.partner-customer').html("");
					$.each(data.fs, function(index, f) {
						let html = "<li id='" + f.contentsId + "'class='partner-customer-li ui-sortable-handle'><a href='javascript:deleteImg(" + f.id + ", " + f.contentsId + ");'><img src='" + $('#uploadPath').val() + f.stored + "'></a></li>"
						let old = $('.partner-customer').html();
						$('.partner-customer').html(old + html);
					});
				},
				error: function(error) {
					alert("실패");
					console.log(error);
				}
			});
		} else alert("파일을 선택하세요.");
	});
});

$(function() {
	$(".partner-customer").sortable({
		update: function(event, ul) {
			$.ajax({
				url: 'updateOrder.ip',
				data: {'order': $(this).sortable('toArray').toString()},
				type: 'post',
				success: function(data) {
					$('.partner-customer').html("");
					$.each(data.fs, function(index, f) {
						let html = "<li id='" + f.contentsId + "'class='partner-customer-li ui-sortable-handle'><a href='javascript:deleteImg(" + f.id + ", " + f.contentsId + ");'><img src='" + $('#uploadPath').val() + f.stored + "'></a></li>"
						let old = $('.partner-customer').html();
						$('.partner-customer').html(old + html);
					});
				},
				error:function(request,status,error) {
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		}
	});
});

function deleteImg(id, contentsId) {
	var user = $('#user').val();
	if(user == 'admin') {
		if(confirm("정말 삭제하시겠습니까?")) {
			$.ajax({
				url: 'delete.ip',
				data: {'id': id},
				dataType: 'json',
				success: function(data) {
					let temp = '#' + contentsId;
					$(temp).remove();
				},
				error: function(error) {
					alert(error);
				}
			});
		}
	}
}
</script>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="text-shadow-banner">Partner & Customer</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
    <section class="content content-center">
    	<div class="container">
		  	<input type="hidden" id="user" value="${loginUser.userId }">
		  	<input type="hidden" id="uploadPath" value="<%= uploadPath %>">
		  	<c:if test="${!empty loginUser }">
			  	<form id="insertForm" enctype="multipart/form-data">
			  		<span class="input input--hoshi input--filled">
				    	<input class="input__field input__field--hoshi" type="file" id="file" name="file" accept=".jpg, .jpeg, .png .gif">
				    	<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="file">
				    		<span class="input__label-content input__label-content--hoshi">로고 추가</span>
				    	</label>
					</span> <input type="button" id="insertBtn" class="insert-in-partner-customer" value="추가">
					<br>
			  	</form>
		  	</c:if>
		  	<c:choose>
		  	<c:when test="${!empty loginUser }">
		  	<ul class="partner-customer">
		  	</c:when>
		  	<c:otherwise>
		  	<ul class="partner-customer-disabled">
		  	</c:otherwise>
		  	</c:choose>
		  	<c:forEach var="f" items="${fs }">
		  		<li id="${f.contentsId }" class="partner-customer-li"><a href="javascript:deleteImg(${f.id }, ${f.contentsId});"><img src="<%= uploadPath %>${f.stored }"></a></li>
		  	</c:forEach>
		  	</ul>
	  	</div>
	</section>	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
