<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    <jsp:include page="../../common/head.jsp" />
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
 	<!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>CEO 인사말</h1>
                    <a href="index.me">Home</a> <span>|</span> <a href="view.ig">CEO인사말</a>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
	
	<form action="updateImg.ig" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>이미지</td>
				<td>
					<input type="file" name="gfile">
					<input type="hidden" name="id" value="">
					<!-- <input type="hidden" name="pageId" value="itr_gre_00001">  -->
				</td>
			</tr>		
		</table>
		<div align="center">
			<input id="submit" type="submit" value="변경하기">
		</div>
	</form>
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../../common/script.jsp" />
    <!-- script -->
</body>
