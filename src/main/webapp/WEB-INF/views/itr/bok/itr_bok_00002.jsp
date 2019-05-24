<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    
    <%
		String PATH = request.getContextPath();
	%>
	
    <jsp:include page="../../common/head.jsp" />
    <script type="text/javascript">
    	$(function() {
			$("#resisterBtn").click(function() {
				var btitle = $("#btitle").val();
				var bcontent = $("#bcontent").val();
				var bfile = $("#bfile").val();
				
				if(btitle == ""){
					return alert("제목을 입력해주세요.");
				} else if(bcontent == ""){
					return alert("내용을 입력해주세요.");
				} else if(bfile == "") {
					return alert("파일을 선택해주세요.");
				} else {
					return $("#resisterForm").submit();
				}
			})
		});
    </script>
</head>
<body>
<section class="sub_banner" role="banner">
        <header id="header">
            <jsp:include page="../../common/header-content.jsp" />
            <!-- header content -->
        </header><!-- header -->
		<div class="container">
            <div class="col-md-10 col-md-offset-1">
                <div class="sub_banner-text text-center">
                    <h1>출판도서/특허 추가</h1><br>
                </div><!-- banner text -->
                <form action="insert.ib" id="resisterForm" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>*출판도서/특허명</td>
						<td><input type="text" id="btitle" name="btitle"></td>
					</tr>
					<tr>
						<td>*종류</td>
						<td>
							<input type="radio" id="bok" name="btype" value="1" checked="checked">
							<label for="bok">서적</label>
						</td>
						<td>
							<input type="radio" id="patent" name="btype" value="2">
							<label for="patent">특허</label>
						</td>
					</tr>
					<tr>
						<td>*내용</td>
						<td>
							<input type="text" id="bcontent" name="bcontent">
						</td>
					</tr>
					<tr>
						<td>*사진</td>
						<td>
							<input type="file" id="bfile" name="bfile">
						</td>
					</tr>		
				</table>
				<br>
				<div align="center">
					<input type="reset" value="다시쓰기">
					<input type="button" id="resisterBtn" value="등록하기">
				</div>
				</form>
            </div>
        </div>
    </section><!-- banner -->
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../../common/script.jsp" />
    <!-- script -->
</body>
