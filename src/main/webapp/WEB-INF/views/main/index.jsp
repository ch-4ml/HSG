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
					<a href="" class="template-btn mt-3">자세히보기</a>
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
			<div class="row wow fadeIn" data-wow-delay="500ms">
				<div class="col-lg-5 align-self-center">
					<div class="welcome-img">
						<img src="./resources/images/main_gre.jpg" alt="">
					</div>
				</div>
				<div class="col-lg-7">
					<div class="welcome-text mt-5 mt-lg-0" id="mainGre">
						<h2>HS글로벌에 오신 것을 환영합니다.</h2>
						<p class="pt-3">HS글로벌은 하드웨어와 소프트웨어를 융합하는 혁신적인 시스템 개발의 목적을 가지고 설립되었습니다. 현업에서 풍부한 노하우를 가지고 있는 훌륭한 엔지니어들이 모여 융합 시스템 연구 및 개발을 적극적으로 진행하고 있으며 산업 현장에 실무 노하우를 전달하는 역할에도 최선을 다하고 있습니다.</p>
						<p>HS글로벌은 하드웨어/소프트웨어 융합 시스템 솔루션 전문 기업으로 시작하여 미래 글로벌 IT 융합 기업을 목표로 힘차게 나아가겠습니다. 고객, 엔지니어, 직원이 모두 행복한 회사가 될 것을 약속드리며 앞으로도 저희 HS글로벌에 변함없는 관심과 성원을 부탁드립니다.</p>
					</div>
				</div>
			</div>
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
			<div class="row">
				<div class="col-lg-5 align-self-center wow fadeInUp" data-wow-delay="250ms">
					<div style="position: relative; max-width: 100%; padding-bottom: 75%; height: 0;">
						<iframe width="320" height="240" src="https://www.youtube.com/embed/jXarjmelLrw" frameborder="0" allowfullscreen="" style="position: absolute; position: absolute; top: 0; left: 0; width: 100%; height: 100%;"></iframe>
					</div>
				</div>
				<div class="col-lg-7 wow fadeInUp" data-wow-delay="500ms">
					<div class="youtube-text mt-5 mt-lg-0">
						<p class="pt-1">SW교육 솔루션 '스마트메이커‘ 를 문제해결능력 교육 관점에서 설명을 해 놓았습니다. 강력한 휴대용컴퓨터라고 할 수 있는 스마트폰을, 모든 사람이 한 대씩 항시 소지하고 다니면서, 사용하는 모바일시대가 도래하면서, 지구촌의 거의 모든 나라에서 소프트웨어를 의무교육으로 가르치고 있습니다. 오늘날, 모든 학생을 대상으로 하는 소프트웨어 보편교육의 목적은, 이전에 소수 전문 프로그래머 양성을 위해서, 실시해온 코딩 교육이 아닙니다. 코딩은, 더 이상 적합한 교육의 대상도, 방법도 아니라고 할 수 있습니다. 소프트웨어 보편교육 목적은, 우리 일상생활의 문제를 해결하는 능력을 배양하는 것 입니다. 하지만, 프로그래밍 코딩 기술을 배우지 않고는, 문제해결능력을 배양하는 수업을 할 수 있는 교육용 솔루션이 없었다는 것이, 사실입니다. 그러나 A. I. 기술로 만들어진 '스마트메이커'를 활용하면, 더 이상 코딩이나 알고리즘과 같이, 세상 문제와는 아무런 상관도 없고, 컴퓨팅 장치 밖에서는 전혀 써먹지도 못할 교육을 학생들에게 시킬 필요가 없습니다. 앞으로 학생들이 실제로 풀어야 할 사회 문제나, 현실 문제에 집중해서 조사 및 분석하는 과정을 통해, 생각하는 힘을 기르고 그런 문제를 해결할 도구로서, 소프트웨어를 만드는 지식과 경험을 체득할 수 있게 됩니다</p>
					</div>
				</div>
			</div>
	</section>

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
						<c:forEach var="content" items="${contents }" varStatus="status">
							<div class="single-slide">
								<div class="slide-img">
									<img src="${content.image }" alt="" class="img-fluid">
									<div class="hover-state">
										<a href="#"><i class="fa fa-plus"></i></a>
									</div>
								</div>
								<div class="single-department item-padding text-center">
									<h3>${content.title }</h3>
									<p>${content.text }</p>
									<c:if test="${!empty loginUser }">
										<input type="button" value="수정" onclick="location.href='updateDevView.ma?id=${content.id}'">
										<input type="button" value="삭제" onclick="deleteBtnClickEvent(${content.id})">
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

	<!-- 
		Contact Area 는 메인에서 사용하기 적절하지 않을 것 같아서 뺐습니다.
		 소스는 common 아래에 따로 저장해뒀습니다. -박찬형-
	-->

	<!-- 
	<div class="col-md-6 wow fadeInUp" data-wow-delay="500ms">
		<div id="overlay" class="map">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3177.6398989608747!2d127.0971884264644!3d37.20878170574413!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x18b26f1c5d97b083!2zKOyjvCnsnIjsnbjthY0!5e0!3m2!1sko!2skr!4v1559184596739!5m2!1sko!2skr" width="600" height="450" style="border:0"></iframe>
		</div>
	</div>
	 -->

	<jsp:include page="../common/footer.jsp" />
	<!-- footer -->

</body>
</html>
