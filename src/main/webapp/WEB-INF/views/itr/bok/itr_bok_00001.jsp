<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    
    <%
		String PATH = request.getContextPath();
	%>
    
    <jsp:include page="../../common/head.jsp" />
    
    <script type="text/javascript">
    	function updateBtnClickEvent(bid) {
	    	window.location.assign('<%=PATH %>/updateView.ib?bid='+bid);
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
               	<c:if test="${!empty loginUser }">
                <div>
		    		<button>
            		    <a href='<%=PATH %>/insertView.ib'>추가하기</a>
                	</button>
                </div>
		    	</c:if>
                <table id="itrbokListTable">
				<c:forEach var="itrBok" items="${itrBokList}">
					<tr>
						<td>${itrBok.btitle}</td>
						<td>${itrBok.bcontent}</td>
						<td><img alt="" src="${itrBok.bimg}"></td>
						<c:if test="${!empty loginUser }">
		                <td><input type="button" onclick="updateBtnClickEvent(${itrBok.bid})" value="수정하기"></td>
				    	</c:if>
					</tr>
				</c:forEach>
                </table>
            </div>
        </div>
    </section><!-- banner -->
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../../common/script.jsp" />
    <!-- script -->
</body>
