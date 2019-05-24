<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el test</title>
</head>
<body>
	<c:set var="user" value="${user }" />
	아이디 : ${user.userId } <br>
	암호 : ${user.userPwd } <br>
	전화번호 : ${user.phone } <br>
</body>
</html>