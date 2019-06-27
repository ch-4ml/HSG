<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>HS글로벌</title>
    <jsp:include page="../../common/head.jsp" />
	<%@ include file="../../common/tinymce-one-page.jsp" %>
     <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #maps {
      	max-width: 70em;
      	width: 100%;
        height: 100%;
        padding: 10%;
        border-style: outset;
        border-width: 5px;
        border-color: hsl(200, 100%, 50%);
        margin-left: auto;
    	margin-right: auto;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
</head>
<body>
<jsp:include page="../../common/header-content.jsp" />
    <!-- Banner Area Starts -->
    <section class="banner-area other-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>회사 위치</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->
	<section id="two" class="wrapper style2 alt">
		<div style="text-align:center;"><h2>오시는 길</h2></div><br>
	</section>
	<br><br>
	<div id="maps"></div><br><br>

    <script>
    var marker;
    var infowindow;
	// Initialize and add the map
	function initMap() {
	  // The location of hsgbiz
	  var hsgbiz = {lat: 37.208455, lng: 127.097964};
	  
	  var contentString = '<div style=\"text-align:center\"><h3>HS글로벌</h3><br>경기도 화성시 동탄순환대로 823 709호<br>(영천동, 에이팩시티 지식산업센터)</div>';
	  
	  infowindow = new google.maps.InfoWindow({
		  content: contentString
		});
	  
	  // The map, centered at hsgbiz
	  var map = new google.maps.Map(
	      document.getElementById('maps'), {zoom: 17, center: hsgbiz});
	  // The marker, positioned at hsgbiz
	  marker = new google.maps.Marker({
		  position: hsgbiz, 
		  map: map,
		  title: "HS글로벌"});
	  marker.addListener('click', toggleBounce);
	  marker.addListener('click', function () {
		  infowindow.open(map, marker);
	  });
	  infowindow.open(map, marker);
	}

  	function toggleBounce() {
	  if (marker.getAnimation() !== null) {
	    marker.setAnimation(null);
	  } else {
	    marker.setAnimation(google.maps.Animation.BOUNCE);
	  }
	}
	</script>
    <section id="two" class="wrapper style2 alt">
	    <div style="text-align:center;"><h2>대중교통</h2></div><br><br>
	    <div class="inner">
			<form id="content_form" method="post" action="update.il">
				<div class="contents">
					<span id="contents"> ${c.contents } </span>
				</div>
				<br>
				<span id="updateButton">
					<c:if test="${!empty loginUser }">
						<div style="text-align:center;">
							<input type="button" id="update" value="수정">
						</div>
						<br>
					</c:if>
				</span>
				<input type="hidden" name="id" value="${c.id }">
			</form>
		</div>
	</section>
    <!--Load the API from the specified URL
    * The async attribute allows the browser to render the page while the API loads
    * The key parameter will contain your own API key (which is not needed for this tutorial)
    * The callback parameter executes the initMap() function
    -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB4LR6QGoBBw_VJChqlK6mAs5l4mB33YSM&callback=initMap" async defer></script>
	
	<jsp:include page="../../common/footer.jsp" />
    <!-- footer -->
</body>
