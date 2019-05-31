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
	
		function deleteBtnClickEvent(id) {
			var sign = confirm("정말로 삭제하시겠습니까?");
			
			if(sign == true){
				window.location.assign("<%=PATH %>/delete.ib?id=" + id);
			}
		}
	
	</script>
    
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>출판도서/특허 수정</h1>
                </div>
            </div>
        </div>
    </section>

<!-- banner text -->
    <form action="update.ib" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${content.id} ">
		<span class="input input--hoshi">
			<input class="input__field input__field--hoshi" type="text" id="title" name="title">
			<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="title">
				<span class="input__label-content input__label-content--hoshi">출판도서 / 특허명</span>
			</label>
		</span>
		<br>					
		<c:choose>
			<c:when test="${content.category eq '1'}">
			<tr>
				<td>*종류</td>
				<td>
					<input type="radio" id="bok" name="category" value="1" checked="checked">
					<label for="bok">서적</label>
				</td>
				<td>
					<input type="radio" id="patent" name="category" value="2">
					<label for="patent">특허</label>
				</td>
			<tr>
			</c:when>
			<c:otherwise>
			<tr>
				<td>*종류</td>
				<td>
					<input type="radio" id="bok" name="category" value="1">
					<label for="bok">서적</label>
				</td>
				<td>
					<input type="radio" id="patent" name="category" value="2" checked="checked">
					<label for="patent">특허</label>
				</td>
			<tr>
			</c:otherwise>
		</c:choose>
					</tr>
						<td>*내용</td>
						<td>
							<input type="text" name="text" value="${content.text} ">
						</td>
					</tr>
					<tr>
						<td>*사진</td>
						<td>
							<input type="file" id="file" name="file" accept=".jpg, .jpeg, .png">
							<input type="text" id="bfleText" value="${content.image} " disabled="disabled">
							<input type="button" id="fileBtn" value="Chose file">
						</td>
					</tr>		
				</table>
				<br>
				<div align="center">
					<input type="button" value="삭제하기" onclick="deleteBtnClickEvent(${content.id})"/>
					<input type="submit" value="수정하기"/>
				</div>
			</form>

	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../../common/script.jsp" />
    <!-- script -->
    
    
    
</body>
