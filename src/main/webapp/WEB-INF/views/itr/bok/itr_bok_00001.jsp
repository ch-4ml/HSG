<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<title>HS글로벌</title>

<%
		String PATH = request.getContextPath();
	%>

<jsp:include page="../../common/head.jsp" />

<script type="text/javascript">
    	function updateBtnClickEvent(id) {
	    	window.location.assign('<%=PATH %>/updateView.ib?id='+id);
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
                    <h1>MOOC</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

	<c:if test="${!empty loginUser }">
		<div>
			<button>
				<a href='<%=PATH %>/insertView.ib'>추가하기</a>
			</button>
		</div>
	</c:if>
	<table id="itrbokListTable">
		<c:forEach var="content" items="${contents}">
			<tr>
				<td>${content.title}</td>
				<td>${content.text}</td>
				<td><img alt="" src="../../../../resources/uploadFiles/itrbok_upload_file/${content.image}"></td>
				<c:if test="${!empty loginUser }">
				<td>
					<input type="button" onclick="updateBtnClickEvent(${content.id})" value="수정하기">
				</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="../../common/footer.jsp" />
	<!-- footer -->

</body>
