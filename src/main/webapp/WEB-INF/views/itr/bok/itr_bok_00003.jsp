<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    
    <jsp:include page="../../common/head.jsp" />
    <%
		String PATH = request.getContextPath();
	%>
	<script type="text/javascript">
		$(function() {
			$("#file").hide();
			
			$("#fileBtn").on('click', function(){
				$('#file').click();
			});
			
			$("#file").on('change', function(){  // 값이 변경되면
				$("#bfleText").val($("#file").val());
				
			});
		});
	
		function deleteBtnClickEvent(bid) {
			
			var sign = confirm("정말로 삭제하시겠습니까?");
			
			if(sign == true){
				window.location.assign('<%=PATH %>/delete.ib?bid='+bid);
			}
		}
	
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
                <form action="update.ib" method="post" enctype="multipart/form-data">
				<input type="hidden" name="bid" value="${itrBok.bid} ">
				<table>
					<tr>
						<td>*출판도서/특허명</td>
						<td><input type="text" name="btitle" value="${itrBok.btitle} "></td>
					</tr>
					<c:choose>
						<c:when test="${itrBok.btype eq '1'}">
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
						<tr>
						</c:when>
						<c:otherwise>
						<tr>
							<td>*종류</td>
							<td>
								<input type="radio" id="bok" name="btype" value="1">
								<label for="bok">서적</label>
							</td>
							<td>
								<input type="radio" id="patent" name="btype" value="2" checked="checked">
								<label for="patent">특허</label>
							</td>
						<tr>
						</c:otherwise>
					</c:choose>
					</tr>
						<td>*내용</td>
						<td>
							<input type="text" name="bcontent" value="${itrBok.bcontent} ">
						</td>
					</tr>
					<tr>
						<td>*사진</td>
						<td>
							<input type="file" id="file" name="bfile" accept=".jpg, .jpeg, .png">
							<input type="text" id="bfleText" value="${itrBok.bimg} " disabled="disabled">
							<input type="button" id="fileBtn" value="Chose file">
						</td>
					</tr>		
				</table>
				<br>
				<div align="center">
					<input type="button" value="삭제하기" onclick="deleteBtnClickEvent(${itrBok.bid})"/>
					<input type="submit" value="수정하기"/>
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
