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
	let flag = true; // 스크롤 이벤트가 연속으로 발생하는 것을 방지하기 위한 플래그 
	let currentPage = 1;

	$(function() {
		let getList = function(currentPage) {
			if(isEnd) return;
			
			$.ajax({
				url: "get.ot",
				data: {"currentPage": currentPage},
				dataType: "json",
				success: function(data) {
					let length = data.cs.length;
					if(length < data.pi.limit) {
						isEnd = true;
					}
					$.each(data.cs, function(index, c) {
						index = data.cs.length - (data.pi.limit*(currentPage-1) + index);
						renderList(index, c);
					});
					flag = true;
				}
			});
		}
		
		let renderList = function(index, c) {
			let html = "<a href=detail.ot?id=" + c.id + "><table class='simple'><tr class='simpleboard'>" +
					   "<td class='simpleboard-index'>" + index + "</td>" +
					   "<td class='simpleboard-contents'>" + c.title + "</td>" +
					   "<td class='simpleboard-date'>" + c.postDate + "</td>" +
					   "</tr></table></a>";
			let old = $('.inner').html();
			$('.inner').html(old + html);
		}
		
		$(window).scroll(function() {
			let $window = $(this);
			let scrollTop = $window.scrollTop(); // 요소의 스크롤바 수직 위치
			let documentHeight = $('section#two').height();
			
			if(scrollTop > documentHeight && flag) {
				currentPage += 1;
				getList(currentPage);
				flag = false;
			}
		});
		getList(currentPage);
		
	});
	
	
</script>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>기술노트</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
    <!-- About Area Starts -->
    <section id="two" class="wrapper style2 alt content">
		<div style="text-align: center;">
			<h2>기술노트</h2><br><br>
		</div>
		<div style="text-align: center;" class="inner">
			<c:if test="${!empty loginUser }">
					<ul class="actions special">
						<li><input type="button" id="insert" onclick="location.href='insertView.ot'" value="추가"></li>
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