<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<%@ include file="../../common/head.jsp" %>
</head>
<body>
<script>
	let isEnd = false;
	let currentPage = 1;

	$(function() {
		$(window).scroll(function() {
			let $window = $(this);
			let scrollTop = $window.scrollTop();
			let windowHeight = $window.height();
			let documentHeight = $('body').height();
			
			if(scrollTop + windowHeight + 30 > documentHeight) {
				currentPage += 1;
				getList();
			}
		});
		getList();
	});
	
	let getList = function() {
		if(isEnd) return;
		
		$.ajax({
			url: "get.ol",
			data: {"currentPage": currentPage},
			dataType: "json",
			success: function(data) {
				let length = data.cs.length;
				if(length < 5) {
					isEnd = true;
				}
				$.each(data.cs, function(index, c) {
					renderList(index, c);
				});
			}
		});
	}
	
	let renderList = function(index, c) {
		let html = "<table><tr class='simpleboard'>" +
				   "<td class='simpleboard-index'>" + index + "</td>" +
				   "<td class='simpleboard-contents'>" + c.title + "</td>" +
				   "<td class='simpleboard-date'>" + c.postDate + "</td>" +
				   "</tr></table>";
		let old = $('.inner').html();
		$('.inner').html(old + html);
	}
	
	
</script>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>최신 기술</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
    <!-- About Area Starts -->
    <section id="two" class="wrapper style2 alt content-center">
		<h2>최신 기술</h2><br><br>
		<div class="inner">
			<c:if test="${!empty loginUser }">
					<ul class="actions special">
						<li><input type="button" id="insert" onclick="location.href='insertView.ol'" value="추가"></li>
					</ul>
				<br>
				<br>
			</c:if>
		</div>
	</section>

    <!-- About Area End -->
    
    <jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
</html>