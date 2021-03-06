<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html> 
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->

<%
	String PATH = request.getContextPath();
%>

<head>
	<jsp:include page="../common/head.jsp" />
    <title>HS글로벌 - 로그인</title>
    <script>
    $(function(){
		$("#loginBtn").click(function() {
				
			var userId = $("#userId").val();
			var userPw = $("#userPw").val();
			
			if(userId == "" || userPw == ""){
				alert('아이디나 비밀번호를 입력하지 않았습니다.');
			} else {
				var loginUser = {
					"userId" : userId,
					"userPw" : userPw
				};
	    		$.ajax({
	    			method: "post",
	    			url: "<%=PATH %>/login.me",
	    			data: loginUser,
	    			success: function(data){  	
						if(data.result == "T"){
							window.location.assign("<%=PATH %>/loginSessionStore.me?userId="+userId);
						} else {
							alert("아이디나 비밀번호가 맞지 않습니다.");
						}
	   		    	},
	    			error: function(e){
	   		    		alert(e.message);
	   		    	}
	   			});
	    		alert("관리자 모드입니다.");	
			}
		});
	});
    
    $(function() {
    	$("#userId").keypress(function (e) {
    		if(e.which == 13) {
    			var loginBtn = document.getElementById("loginBtn");
    	    	loginBtn.click();
    		}
    	});
    	
    	$("#userPw").keypress(function (e) {
    		if(e.which == 13) {
    			var loginBtn = document.getElementById("loginBtn");
    	    	loginBtn.click();
    		}
    	});
    });
    </script>
</head>
<body>
<jsp:include page="../common/header-content.jsp" />
	 <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="text-shadow-banner">LOGIN</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
	
	<!-- Login Area Starts -->
    <section class="login-area section-padding">
	  <div class="container">
		<div class="row">
		  <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
			<div class="card card-signin my-5">
			  <div class="card-body">
				<h2 class="card-title text-center">로그인</h2>
				<form>
				  <div class="form-label-group">
				  	<span class="input input--hoshi">
				  		<input class="input__field input__field--hoshi" type="text" id="userId" name="userId" required autofocus>
				  		<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="userId">
				  			<span class="input__label-content input__label-content--hoshi">ID</span>
				  		</label>
				  	</span>
				  </div>
				  <div class="form-label-group">
				  	<span class="input input--hoshi">
				  		<input class="input__field input__field--hoshi" type="password" id="userPw" name="userPw" required autofocus>
				  		<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="userPw">
				  			<span class="input__label-content input__label-content--hoshi">비밀번호</span>
				  		</label>
				  	</span>
				  </div>
				  <br>
				  <div style="text-align:center;">
					<input type="button" id="loginBtn" value="로그인">
				  </div>
				</form>
			  </div>
			</div>
		  </div>
		</div>
	  </div>
    </section>
    <!-- Login Area End -->
	<jsp:include page="../common/footer.jsp" />
	<!-- footer -->
</body>
