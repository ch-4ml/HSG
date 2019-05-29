<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HS글로벌</title>
<jsp:include page="../../common/head.jsp" />
</head>
<body>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName
										: data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("e_address").value = extraAddr;

						} else {
							document.getElementById("e_address").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById("zip_code").value = data.zonecode;
						document.getElementById("address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("d_address").focus();
					}
				}).open();
	}
</script>
<script>
function fileCheck(file)
{
	// 사이즈체크
	var maxSize  = 25 * 1024 * 1024
	var fileSize = 0;
	// 브라우저 확인
	var browser = navigator.appName;
	
	// 익스플로러일 경우
	if (browser=="Microsoft Internet Explorer") {
		var oas = new ActiveXObject("Scripting.FileSystemObject");
		fileSize = oas.getFile( file.value ).size;
	}
	// 익스플로러가 아닐경우
	else {
//		file.files.forEach(function(f) {
//			fileSize += f.size;
//		});
		fileSize = file.files[0].size;
	}
	alert("파일사이즈 : "+ fileSize +", 최대파일사이즈 : 25MB");
	if(fileSize > maxSize) {
		alert("첨부파일 사이즈는 25MB 이내로 전송 가능합니다.");
		return;
	}
		document.form.submit();
}
</script>


	<jsp:include page="../../common/header-content.jsp" />
	<!-- Banner Area Starts -->
	<section class="banner-area other-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>교육 의뢰</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- Banner Area End -->

	<!-- Contact Area Starts -->

	<form action="send.er" name="form" method="post"
		enctype="multipart/form-data">
		<input type="text" class="form-control" id="program" name="program"
			aria-describedby="emailHelp" placeholder="신청할 프로그램 *"
			value="${content.title }" required readonly> <input
			type="text" class="form-control" id="company" name="company"
			aria-describedby="emailHelp" placeholder="회사명 *" required> <input
			type="text" class="form-control" id="zip_code" name="zip_code"
			aria-describedby="emailHelp" placeholder="우편번호 *" required readonly>
		<div class="input-group-append">
			<button class="btn btn-outline-secondary" type="button"
				onclick="execDaumPostcode()" value="우편번호 검색">검색</button>
		</div>
		<input type="text" class="form-control" id="address" name="address"
			aria-describedby="emailHelp" placeholder="주소 *" required readonly>
		<input type="text" class="form-control" id="d_address"
			name="d_address" aria-describedby="emailHelp" placeholder="상세주소 *"
			required> <input type="text" class="form-control"
			id="e_address" name="e_address" aria-describedby="emailHelp"
			placeholder="참고항목 " readonly> <input type="text"
			class="form-control" id="name" name="name"
			aria-describedby="emailHelp" placeholder="담당자명 *" required> <input
			type="text" class="form-control" id="phone" name="phone"
			aria-describedby="emailHelp" placeholder="연락처 *" required> <input
			type="email" class="form-control" id="email" name="email"
			aria-describedby="emailHelp" placeholder="E-mail *" required>
		<textarea class="form-control" id="message" name="message" rows="6"
			placeholder="요구사항 *" required></textarea>
		<input type="file" class="form-control" id="file" name="file"
			multiple="multiple" aria-describedby="emailHelp" placeholder="첨부 파일">
		<div class="mx-auto">
			<button type="button" class="btn btn-primary text-right"
				onclick="fileCheck(this.form.file)">보내기</button>
		</div>
	</form>
	<!-- Contact Area End -->

	<jsp:include page="../../common/footer.jsp" />
	<!-- footer -->
</body>
</html>