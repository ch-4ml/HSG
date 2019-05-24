<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<form action="file" id="fileUpload" name="fileUpload" method="post" enctype="multipart/form-data">
        이름 : <input type="text" name="name" id="cmd" value="nameval"><br>
        파일 : <input type="file" name="file"><br> <input
            type="submit" name="업로드" value="제출"><br>
    </form>
</body>
</html>