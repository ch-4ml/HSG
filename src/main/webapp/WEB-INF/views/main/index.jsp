<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<head>
<%@ include file="../common/head.jsp"%>
<title>HS글로벌</title>
<script>
function deleteBtnClickEvent(id) {
	var sign = confirm("정말로 삭제하시겠습니까?");
	
	if(sign == true){
		location.href="deleteDev.ma?id=" + id;
	}
}
</script>
<script src="https://cloud.tinymce.com/5/tinymce.min.js?apiKey=iiyaoh4ovlz6z3aafb6vdpqtllt555a3g3loxoh2dwetyw3e"></script>
<script>
$(function() {
	$("#updateItrButton").click(function() {
		var oldImage = $('#contentsItrImage').html();
		var oldContent = $('#contentsItr').html();
		$("#updateItrSection").html("<textarea id='contents' name='contents'>" + oldImage + oldContent + "</textarea><br><div style='text-align:center;'><input type='submit' value='수정'></div>");
		tinymce.init({
		    selector: 'textarea#contents',
		    menubar: false,
		    language_url: 'tinymce/ko_KR.js',
		    plugins: ['autolink autosave code link media table textcolor autoresize hr fullpage'],
		    toolbar: "undo redo | fontsizeselect | forecolor bold underline italic code | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table link media code hr",
		    fullpage_default_font_family: "NanumSquareRound",
		    font_formats: "나눔스퀘어라운드=NanumSquareRound;",
		    fontsize_formats: "11px 12px 14px 16px 18px 24px 36px 48px",
		});
		$("#updateItrButton").html("");
	});
});
</script>
</head>
<body>
	<jsp:include page="../common/header-content.jsp" />
	<!-- Banner Area Starts -->
	<section class="banner-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
						<h1 class="text-shadow-banner">HW/SW 융합 시스템 개발<br>HW/SW 분야 기술 교육</h1>
				</div>
				<p class="text-shadow-p">${ban.text }</p>
				<c:if test="${!empty loginUser }">
					<a href="updateBanView.ma" class="template-btn mt-3">수정</a>
				</c:if>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->
	<!-- Feature Area Starts -->
	<section class="feature-area section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<c:if test="${!empty loginUser }"><a href="updateCatView.ma?id=${cat[0].id }"></c:if>
					<div class="single-feature text-center item-padding wow fadeInUp" data-wow-delay="250ms">
						<img src="./resources/images/feature3.png" alt="">
						<h3>${cat[0].title }</h3>
						<p class="pt-3">${cat[0].text }</p>
					</div>
					<c:if test="${!empty loginUser }"></a></c:if>
				</div>
				
				<div class="col-lg-4 col-md-6">
					<c:if test="${!empty loginUser }">
						<a href="updateCatView.ma?id=${cat[1].id }">
					</c:if>
					<div class="single-feature text-center item-padding mt-4 mt-md-0 wow fadeInUp" data-wow-delay="500ms">
						<img src="./resources/images/feature2.png" alt="">
						<h3>${cat[1].title }</h3>
						<p class="pt-3">${cat[1].text }</p>
					</div>
					<c:if test="${!empty loginUser }">
						</a>
					</c:if>
				</div>
				
				<div class="col-lg-4 col-md-6">
				<c:if test="${!empty loginUser }">
					<a href="updateCatView.ma?id=${cat[2].id }">
				</c:if>
					<div class="single-feature text-center item-padding mt-4 mt-lg-0 wow fadeInUp" data-wow-delay="750ms">
						<img src="./resources/images/feature1.png" alt="">
						<h3>${cat[2].title }</h3>
						<p class="pt-3">${cat[2].text }</p>
					</div>
				<c:if test="${!empty loginUser }">
					</a>
				</c:if>
				</div>
			</div>
		</div>
	</section>
	<!-- Feature Area End -->
	<section class="mooc-area section-padding3">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<div class="section-top text-center ">
						<h2>Introduce</h2>
					</div>
				</div>
			</div>
			<form id="updateItrForm" action="updateItr.ma" method="post">
				<span id="updateItrSection">
					<div class="row">
						<div class="col-lg-5 align-self-center wow fadeInUp" data-wow-delay="250ms">
							<div style="position: relative; max-width: 100%; padding-bottom: 75%; height: 0;">
								<span id="contentsItrImage">${itr.contents }</span>
							</div>
						</div>
						<div class="col-lg-7 wow fadeInUp" data-wow-delay="500ms">
							<div class="youtube-text mt-5 mt-lg-0">
								<span id="contentsItr">${itr.text }</span>
								<c:if test="${!empty loginUser }">
									<div style="text-align: center;">
										<input type="button" id="updateItrButton" value="수정">
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</span> <input type="hidden" name="id" value="${itr.id }">
			</form>
		</div>
	</section>
	<span id="updateItrSection"></span>
	<!-- Department Area Starts -->
	<section class="department-area section-padding4">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<div class="section-top text-center">
						<h2>Products</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 wow fadeInUp" data-wow-delay="500ms">
					<div class="department-slider owl-carousel">
						<c:if test="${!empty loginUser }">
							<div class="single-slide">
								<div class="slide-img">
									<img src="" alt="" class="img-fluid">
									<div class="hover-state">
										<a href="insertDevView.ma"><i class="fa fa-plus"></i></a>
									</div>
								</div>
								<div class="single-department item-padding text-center">
									<h3>개발 추가하기</h3>
									<p></p>
								</div>
							</div>
						</c:if>
						<c:forEach var="d" items="${dev }" varStatus="status">
							<div class="single-slide">
								<div class="slide-img">
									<img src="<%= uploadPath %>${d.contents }" alt="" class="img-fluid">
									<div class="hover-state">
										<i class="fa fa-plus"></i>
									</div>
								</div>
								<div class="single-department item-padding text-center">
									<h3>${d.title }</h3> <br>
									<p style="text-align: center;">${d.text }</p>
									<c:if test="${!empty loginUser }">
										<input type="button" value="수정" onclick="location.href='updateDevView.ma?id=${d.id}'">
										<input type="button" value="삭제" onclick="deleteBtnClickEvent(${d.id})">
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Department Area Starts -->
	<jsp:include page="../common/footer.jsp" />
	<!-- footer -->
</body>
</html>
