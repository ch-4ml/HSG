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
<jsp:include page="../common/head.jsp" />
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
	$("#updateGreButton").click(function() {
		var oldImage = $('#contentsGreImage').html();
		var oldContent = $('#contentsGre').html();
		$("#updateGreSection").html("<textarea id='text' name='text'>" + oldImage + oldContent + "</textarea><br><div style='text-align:center;'><input type='submit' value='수정'></div>");
		tinymce.init({
		    selector: 'textarea',
		    menubar: false,
		    language_url: 'tinymce/ko_KR.js',
		    plugins: ['autolink autosave code link media table textcolor autoresize hr image imagetools fullpage'],
		    toolbar: "undo redo | fontsizeselect | forecolor bold underline italic code | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table link media image code hr",
		    fullpage_default_font_family: "NanumSquareRound",
		    font_formats: "나눔스퀘어라운드=NanumSquareRound;",
		    fontsize_formats: "11px 12px 14px 16px 18px 24px 36px 48px",
		    	file_picker_types: 'image',
			    file_picker_callback: function (cb, value, meta) {
			        var input = document.createElement('input');
			        input.setAttribute('type', 'file');
			        input.setAttribute('accept', 'image/*');

			        /*
			          Note: In modern browsers input[type="file"] is functional without
			          even adding it to the DOM, but that might not be the case in some older
			          or quirky browsers like IE, so you might want to add it to the DOM
			          just in case, and visually hide it. And do not forget do remove it
			          once you do not need it anymore.
			        */

			        input.onchange = function () {
			          var file = this.files[0];

			          var reader = new FileReader();
			          reader.onload = function () {
			            /*
			              Note: Now we need to register the blob in TinyMCEs image blob
			              registry. In the next release this part hopefully won't be
			              necessary, as we are looking to handle it internally.
			            */
			            var id = 'blobid' + (new Date()).getTime();
			            var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
			            var base64 = reader.result.split(',')[1];
			            var blobInfo = blobCache.create(id, file, base64);
			            blobCache.add(blobInfo);

			            /* call the callback and populate the Title field with the file name */
			            cb(blobInfo.blobUri(), { title: file.name });
			          };
			          reader.readAsDataURL(file);
			        };

			        input.click();
			      }
		});
		$("#updateGreButton").html("");
	});
});

$(function() {
	$("#updateMoocButton").click(function() {
		var oldImage = $('#contentsMoocImage').html();
		var oldContent = $('#contentsMooc').html();
		$("#updateMoocSection").html("<textarea id='text' name='text'>" + oldImage + oldContent + "</textarea><br><div style='text-align:center;'><input type='submit' value='수정'></div>");
		tinymce.init({
		    selector: 'textarea',
		    menubar: false,
		    language_url: 'tinymce/ko_KR.js',
		    plugins: ['autolink autosave code link media table textcolor autoresize hr image imagetools fullpage'],
		    toolbar: "undo redo | fontsizeselect | forecolor bold underline italic code | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table link media image code hr",
		    fullpage_default_font_family: "NanumSquareRound",
		    font_formats: "나눔스퀘어라운드=NanumSquareRound;",
		    fontsize_formats: "11px 12px 14px 16px 18px 24px 36px 48px",
		    	file_picker_types: 'image',
			    file_picker_callback: function (cb, value, meta) {
			        var input = document.createElement('input');
			        input.setAttribute('type', 'file');
			        input.setAttribute('accept', 'image/*');

			        /*
			          Note: In modern browsers input[type="file"] is functional without
			          even adding it to the DOM, but that might not be the case in some older
			          or quirky browsers like IE, so you might want to add it to the DOM
			          just in case, and visually hide it. And do not forget do remove it
			          once you do not need it anymore.
			        */

			        input.onchange = function () {
			          var file = this.files[0];

			          var reader = new FileReader();
			          reader.onload = function () {
			            /*
			              Note: Now we need to register the blob in TinyMCEs image blob
			              registry. In the next release this part hopefully won't be
			              necessary, as we are looking to handle it internally.
			            */
			            var id = 'blobid' + (new Date()).getTime();
			            var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
			            var base64 = reader.result.split(',')[1];
			            var blobInfo = blobCache.create(id, file, base64);
			            blobCache.add(blobInfo);

			            /* call the callback and populate the Title field with the file name */
			            cb(blobInfo.blobUri(), { title: file.name });
			          };
			          reader.readAsDataURL(file);
			        };

			        input.click();
			      }
		});
		$("#updateMoocButton").html("");
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
				<div class="col-lg-8">
					<div class="col-lg-10">
						<h1>HW/SW 시스템 융합 솔루션 기업</h1>
					</div>
					<p>
						HS글로벌은 하드웨어/소프트웨어 융합 시스템 솔루션 전문 기업으로 시작하여 <BR>미래 글로벌 IT 융합 기업을 목표로 힘차게 나아가겠습니다.
					</p>
					<!-- <a href="" class="template-btn mt-3">자세히보기</a>  -->
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->

	<!-- Feature Area Starts -->
	<section class="feature-area section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="single-feature text-center item-padding wow fadeInUp" data-wow-delay="250ms">
						<img src="./resources/images/feature1.png" alt="">
						<h3>교육 솔루션</h3>
						<p class="pt-3">기업에 근무하는 R&D 인력을 대상으로 신규 프로젝트 진행이나, 개발자의 직무향상을 위하여 기업이 컨설팅 및 교육을 위탁하여 진행되는 서비스입니다.</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="single-feature text-center item-padding mt-4 mt-md-0 wow fadeInUp" data-wow-delay="500ms">
						<img src="./resources/images/feature3.png" alt="">
						<h3>개발</h3>
						<p class="pt-3">
							산업용 pc 기반의 사용자 인터페이스 프로그램과 교육용 Kocoafab 지니어스키트 스타터 팩 및 보드 등을 개발합니다.<br>
						</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="single-feature text-center item-padding mt-4 mt-lg-0 wow fadeInUp" data-wow-delay="750ms">
						<img src="./resources/images/feature2.png" alt="">
						<h3>컨설팅</h3>
						<p class="pt-3">다양한 사업 분야에서 최적의 솔루션을 제공합니다.</p>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Feature Area End -->

	<!-- Welcome Area Starts -->
	<section class="welcome-area section-padding3">
		<div class="container">
			<form id="updateGreForm" action="updateGre.ma" method="post">
				<span id="updateGreSection">
					<div class="row wow fadeIn" data-wow-delay="500ms">
						<div class="col-lg-5 align-self-center">
							<div class="welcome-img">
								<span id="contentsGreImage">${gre.image }</span>
							</div>
						</div>
						<div class="col-lg-7">
							<div class="welcome-text mt-5 mt-lg-0" id="mainGre">
								<span id="contentsGre">${gre.text }</span>
								<c:if test="${!empty loginUser }">
									<div style="text-align: center;">
										<input type="button" id="updateGreButton" value="수정">
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</span> <input type="hidden" name="id" value="${gre.id }">
			</form>
		</div>
	</section>

	<!-- Welcome Area End -->

	<section class="mooc-area section-padding3">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<div class="section-top text-center ">
						<h2>MOOC</h2>
					</div>
				</div>
			</div>
			<form id="updateMoocForm" action="updateMooc.ma" method="post">
			<span id="updateMoocSection">
			<div class="row">
				<div class="col-lg-5 align-self-center wow fadeInUp" data-wow-delay="250ms">
					<div style="position: relative; max-width: 100%; padding-bottom: 75%; height: 0;">
						<span id="contentsMoocImage">${mooc.image }</span>
					</div>
				</div>
				<div class="col-lg-7 wow fadeInUp" data-wow-delay="500ms">
					<div class="youtube-text mt-5 mt-lg-0">
						<span id="contentsMooc">${mooc.text }</span>
						<c:if test="${!empty loginUser }">
							<div style="text-align: center;">
								<input type="button" id="updateMoocButton" value="수정">
							</div>
						</c:if>
					</div>
				</div>
			</div>
			</span> <input type="hidden" name="id" value="${mooc.id }">
			</form>
	</section>

	<span id="updateMoocSection"></span>
	<!-- Department Area Starts -->
	<section class="department-area section-padding4">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<div class="section-top text-center">
						<h2>교육용/산업용 개발</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 wow fadeInUp" data-wow-delay="500ms">
					<div class="department-slider owl-carousel">
						<c:if test="${!empty loginUser }">
							<div class="single-slide">
								<div class="slide-img">
									<img src="http://placehold.it/362x273" alt="" class="img-fluid">
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
									<img src="${d.image }" alt="" class="img-fluid">
									<div class="hover-state">
										<a href="#"><i class="fa fa-plus"></i></a>
									</div>
								</div>
								<div class="single-department item-padding text-center">
									<h3>${d.title }</h3>
									<p>${d.text }</p>
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
