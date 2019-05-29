<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- Contact Area Starts -->
	<section id="contact" class="section contacts no-padding">
		<div class="container-fluid">
			<div class="row no-gutter">
				<div class="col-md-6 wow fadeInUp" data-wow-delay="250ms">
					<div class="card" style="width: 100%; height: 100%">
						<div class="card-header bg-primary text-white">
							<i class="fa fa-envelope"></i> 교육 의뢰
						</div>
						<div class="card-body">
							<form action="send.er" method="post" enctype="multipart/form-data">
								<div class="row">
									<div class="col-12 col-lg-6">
										<div class="form-group">
											<input type="text" class="form-control" id="name"
												aria-describedby="emailHelp" placeholder="회사명 *" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-lg-6">
										<div class="input-group  mb-3">
											<input type="text" class="form-control" id="zip_code"
												name="zip_code" aria-describedby="emailHelp"
												placeholder="우편번호 *" required>
											<div class="input-group-append">
												<button class="btn btn-outline-secondary" type="button"
													onclick="sample2_execDaumPostcode()" value="우편번호 검색">검색</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-lg-7 mr-1">
										<div class="form-group">
											<input type="text" class="form-control" id="b_address"
												aria-describedby="emailHelp" placeholder="주소 *" required>
										</div>
									</div>
									<div class="col-12 col-lg-3">
										<div class="form-group">
											<input type="text" class="form-control" id="d_address"
												aria-describedby="emailHelp" placeholder="상세주소 *" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-lg-4 mr-1">
										<div class="form-group">
											<input type="text" class="form-control" id="s_name"
												aria-describedby="emailHelp" placeholder="담당자 *" required>
										</div>
									</div>
									<div class="col-12 col-lg-4">
										<div class="form-group">
											<input type="email" class="form-control" id="email"
												aria-describedby="emailHelp" placeholder="E-mail *" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-lg-10">
										<div class="form-group">
											<textarea class="form-control" id="message" rows="6"
												placeholder="요구사항 *" required></textarea>
										</div>
									</div>
								</div>
								<div class="mx-auto">
									<button type="submit" class="btn btn-primary text-right">보내기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</section>
	<!-- Contact Area End -->
</body>
</html>