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
	/* validation 처리 */
	var company = $("#company").val();
	var zip_code = $("#zip_code").val();
	var address = $("#address").val();
	var d_address = $("#d_address").val();
	var name = $("#name").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var files = $("#file").val();
	
	if(company == ""){
		return alert("회사명을 입력해주세요.");
	} else if(zip_code == ""){
		return alert("주소를 입력해주세요.");
	} else if(name == ""){
		return alert("담당자 성함을 입력해주세요.");
	} else if(phone == ""){
		return alert("연락처를 입력해주세요.");
	} else if(email == ""){
		return alert("이메일을 입력해주세요.");
	} else if(files == ""){
		return alert("첨부파일을 입력해주세요.");
	}
	// 사이즈체크
	var maxSize  = 25 * 1024 * 1024
	var fileSize = 0;
	for(var i=0; i<file.files.length; i++) {
		fileSize = fileSize + file.files[i].size;
	}
	if(fileSize > maxSize) {
		alert("첨부파일 사이즈는 25MB 이내로 전송 가능합니다.");
		return;
	}
	document.form.submit();
}
</script>
<script>
function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
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
	<section class="content content-center">
		<div class="container">
			<form action="send.er" name="form" method="post" enctype="multipart/form-data">
				<section>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="company" name="company">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="company">
							<span class="input__label-content input__label-content--hoshi">회사명</span>
						</label>
					</span>
					<select class="cs-select cs-skin-underline double" id="category" name="category">
							<option value="" disabled selected>카테고리 선택</option>
							<option value="1">H/W</option>
							<option value="2">S/W</option>
							<option value="3">해외파견</option>
					</select>
				</section>
				<section>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="zip_code" name="zip_code" value="검색" onclick="execDaumPostcode()" readonly onfocus="this.blur();">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="zip_code">
							<span class="input__label-content input__label-content--hoshi">우편번호</span>
						</label>
					</span>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi input--filled" type="text" id="address" name="address" value="검색 시 자동으로 입력됩니다." readonly onfocus="this.blur();">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="address">
							<span class="input__label-content input__label-content--hoshi">회사주소</span>
						</label>
					</span>
					<br>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="d_address" name="d_address">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="d_address">
							<span class="input__label-content input__label-content--hoshi">상세주소</span>
						</label>
					</span>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi input--filled" type="text" id="e_address" name="e_address" value="검색 시 자동으로 입력됩니다." readonly onfocus="this.blur();">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="e_address">
							<span class="input__label-content input__label-content--hoshi">참고주소</span>
						</label>
					</span>
					<br>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="name" name="name">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="name">
							<span class="input__label-content input__label-content--hoshi">담당자 성함</span>
						</label>
					</span>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="text" id="phone" name="phone">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="phone">
							<span class="input__label-content input__label-content--hoshi">연락처</span>
						</label>
					</span>
					<br>
					<span class="input input--hoshi">
						<input class="input__field input__field--hoshi" type="email" id="email" name="email">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="email">
							<span class="input__label-content input__label-content--hoshi">E-mail</span>
						</label>
					</span>
					<span class="input input--hoshi input--filled">
						<input class="input__field input__field--hoshi" type="file" id="file" name="file" multiple="multiple">
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="name">
							<span class="input__label-content input__label-content--hoshi">첨부파일</span>
						</label>
					</span>
					<br>	
					<span class="input__textarea input--hoshi">
						<textarea class="autosize, input__field input__field--hoshi" onkeydown="resize(this)" onkeyup="resize(this)" id="message" name="message"></textarea>
						<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="message">
							<span class="input__label-content input__label-content--hoshi">요구사항</span>
						</label>
					</span>
				</section>
				<br>
				<input type="button" onclick="fileCheck(this.form.file)" value="전송">
			</form>
		</div>
	</section>
	<!-- Contact Area End -->

	<jsp:include page="../../common/footer.jsp" />
	<!-- footer -->
</body>
</html>