<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

<script src="js/bootstrap-notify.js"></script>
<script src="js/mobileview.js"></script>
<link href="css/animate.css" rel="stylesheet">

<!-- Web socket CDN -->
<script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<link rel="stylesheet" href="/css/user/profileEdu.css">
<link rel="stylesheet" href="/css/user/profileCareer.css">
<link rel="stylesheet" href="css/user/certificate.css">


<title>Free Board</title>
<style type="text/css">
.inline {
	display: inline;
}

.wi {
	width: 120px;
}

#containment-wrapper {
	width: 95%;
	height: 300px;
	border: 2px solid #ccc;
	padding: 10px;
}

h3 {
	clear: left;
}

.rere {
	position: relative;
}

.abab {
	position: absolute;
}

.popover-content {
	font-size: 10px;
}
.alert-minimalist {
	background-color: rgb(255, 242, 240);
	border-color: rgba(149, 149, 149, 0.3);
	border-radius: 3px;
	color: rgb(0, 0, 0);
	padding: 10px;
}
.alert-minimalist > [data-notify="icon"] {
	height: 25px;
	margin-right: 0px;
}
.alert-minimalist > [data-notify="title"] {
	color: rgb(51, 51, 51);
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}
.alert-minimalist > [data-notify="message"] {
	font-size: 80%;
}
</style>

</head>
<body>

	<div class="container-wrap">
		<div id="fh5co-blog">
			<div class="row">
			<div id="box_personal">
							<div class="row rere" id="mapdiv">
								<img src="images/sample.png" id="mapimg">
							</div>
							<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1층 에서 화재가 감지되었습니다.</h4>
							<div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></div>
					</div>
				</div>
				<div></div>
			</div>
		</div>

</body>
</html>