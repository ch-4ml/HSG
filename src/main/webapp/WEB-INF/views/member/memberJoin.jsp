<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">회원가입</h1>
	
	<form action="insert.me" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>*아이디</td>
				<td><input type="text" id="userId" name="userId"></td>
				<td><input type="button" onclick="duplicationCheck();" value="중복확인"></td>
			</tr>
			<tr>
				<td>*비밀번호</td>
				<td>
					<input type="password" name="userPwd">
				</td>
			</tr>
			<tr>
				<td>*비밀번호 확인</td>
				<td>
					<input type="password" name="userPwd2">
				</td>
			</tr>
			<tr>
				<td>*이름</td>
				<td>
					<input type="text" name="userName">
				</td>
			</tr>
			<tr>
				<td>*이메일</td>
				<td>
					<input type="text" name="email">
				</td>
			</tr>
			<tr>
				<td>주민등록번호</td>
				<td>
					<input type="text" name="birthDay" size="6">-
					<input type="text" name="gender" size="1">******
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="tel" name="phone">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="address">
				</td>
			</tr>		
			<tr>
				<td>프로필 사진</td>
				<td>
					<input type="file" name="photo">
				</td>
			</tr>		
							
		</table>
		<br>
		<div align="center">
			<button type="reset">다시쓰기</button>
			<button type="submit">가입하기</button>
		</div>
	</form>
	<script type="text/javascript">
		function duplicationCheck() {
			var userId = $("#userId").val();
			
			$.ajax({
				url:"duplicationCheck.me",
				type:"post",
				data:{userId:userId},
				success:function(data){
					//alert(data);
					console.log(data);
					console.log(data.member.userId);
				}
			});
		}
	</script>
</body>
</html>