<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    <jsp:include page="../../common/head.jsp" />
	<%@ include file="../../common/tinymce.jsp" %>
    
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
                <h1>회사 위치</h1><br>
            </div><!-- banner text -->
        </div>
    </div>
</section><!-- banner -->
	
	<div id="map" style="width:500px; height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2687d605ba78d562665a2a13fe47e09e&libraries=services,clusterer,drawing"></script>
    <script>
    var container = document.getElementById('map');
    var options = {
    		center: new daum.maps.LatLng(37.208569, 127.098032),
    		level: 3
    };
    var map = new daum.maps.Map(container, options);
 	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    var mapTypeControl = new daum.maps.MapTypeControl();

    // 지도 타입 컨트롤을 지도에 표시합니다
    map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
    
 // 마커가 표시될 위치입니다 
    var markerPosition  = new daum.maps.LatLng(37.208569, 127.098032); 

    // 마커를 생성합니다
    var marker = new daum.maps.Marker({
        position: markerPosition
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

    var iwContent = '<div style="padding:5px;">(주)에이치에스글로벌<br>경기도 화성시 동탄순환대로 823, 709호&nbsp<br><a href="http://map.daum.net/link/map/(주)에이치에스글로벌,37.208569, 127.098032" style="color:blue" target="_blank">큰지도보기</a> <a href="http://map.daum.net/link/to/(주)에이치에스글로벌 ,37.208569, 127.098032" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new daum.maps.LatLng(37.208569, 127.098032); //인포윈도우 표시 위치입니다

    // 인포윈도우를 생성합니다
    var infowindow = new daum.maps.InfoWindow({
        position : iwPosition, 
        content : iwContent 
    });
      
    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowindow.open(map, marker); 
    </script>

	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->

    <jsp:include page="../../common/script.jsp" />
    <!-- script -->
</body>
