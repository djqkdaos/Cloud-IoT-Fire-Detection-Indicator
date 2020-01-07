<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/mymap.js"></script>

<title>Free Board</title>
<style type="text/css">
.inline {
	display: inline;
}

.wi {
	width: 120px;
}

.easypiechart-panel {
	text-align: center;
	padding: 1px 0;
	margin-bottom: 20px;
}

.easypiechart {
	position: relative;
	text-align: center;
	width: 120px;
	height: 120px;
	margin: 20px auto 10px auto;
}

.easypiechart .percent {
	display: block;
	position: absolute;
	font-size: 26px;
	top: 38px;
	width: 120px;
}

#easypiechart-blue .percent {
	color: #30a5ff;
}

#easypiechart-teal .percent {
	color: #1ebfae;
}

#easypiechart-orange .percent {
	color: #ffb53e;
}

#easypiechart-red1 .percent {
	color: #ef4040;
}

#easypiechart-red2 .percent {
	color: #ef4040;
}

#easypiechart-red3 .percent {
	color: #ef4040;
}

@media screen and (max-width: 800px) {
	/*모바일 적용 css 768px이하면 적용 (가로가)ㅇㅋ?가로 몇으로 하까*/
	#map {
		position: relative;
		top: 0;
		left: 0;
		width: 100%;
		height: 430px;
	}
}

@media ( min-width : 801px) {
	#map {
		position: relative;
		top: 0;
		left: 0;
		width: 100%;
		height: 245px;
	}
}

.rere {
	position: relative;
}

.container-wrap {
	max-width: 1920px;
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

.alert-minimalist>[data-notify="icon"] {
	height: 25px;
	margin-right: 0px;
}

.alert-minimalist>[data-notify="title"] {
	color: rgb(51, 51, 51);
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

.alert-minimalist>[data-notify="message"] {
	font-size: 80%;
}
</style>
<!-- <script type="text/javascript" src="js/chart-data.js"></script> -->
<script type="text/javascript" src="js/chart.min.js"></script>

<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.css" />
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.js"></script>

<script
	src="https://rawgit.com/mapshakers/leaflet-icon-pulse/master/src/L.Icon.Pulse.js"></script>
<link rel="stylesheet"
	href="https://rawgit.com/mapshakers/leaflet-icon-pulse/master/src/L.Icon.Pulse.css" />
<script src="js/bootstrap-notify.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>

</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
	<div class="container-wrap">
		<div id="fh5co-blog">
			<div
				style="text-align: center; vertical-align: middle; font-size: x-large; color: black; font-weight: bold;">
				단말기 모니터링</div>
			<br>
			<div class="row">
				<div class="col-xs-3 col-md-2">
					<div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>No.1</h4>
							<div class="easypiechart" id="easypiechart-red1" data-percent="1">
								<span class="percent" id="n1">18.4v</span>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-3 col-md-2">
					<div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>No.2</h4>
							<div class="easypiechart" id="easypiechart-red2" data-percent="1">
								<span class="percent" id="n2">19.1v</span>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-3 col-md-2">
					<div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>No.3</h4>
							<div class="easypiechart" id="easypiechart-red3" data-percent="1">
								<span class="percent" id="n3">8.2v</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-3 col-md-2">
					<div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>Status</h4>
							<div class="easypiechart">
								<br> <span class="test">Status</span><br> <span
									id="n4">Fire!</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-3 col-md-2">
					<div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>CPU</h4>
							<div class="easypiechart" id="easypiechart-orange"
								data-percent="99">
								<span class="percent" id="cpus">2342MB</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-3 col-md-2">
					<div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>Memory</h4>
							<div class="easypiechart" id="easypiechart-teal"
								data-percent="22">
								<span class="percent" id="memo">2342MB</span>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-lg-7">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-heading">도면</div>
							<div class="panel-body rere">
								<div class="mapdiv" id="mapdiv">
									<img src="images/sample.png" id="mapimga"
										style="width: 100%; height: 100%">
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="col-lg-5">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-heading">전압 통계</div>
							<div class="panel-body">
								<div class="canvas-wrapper">
									<canvas class="main-chart" id="line-chart" height="200"
										width="600"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-heading">지도</div>
							<div class="panel-body">
								<div class="canvas-wrapper">
									<div id="map"></div>
									<%-- <canvas class="main-chart" id="line-chart" height="200"
											width="600"></canvas> --%>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>


			<!--/.row-->

		</div>
	</div>

	<script type="text/javascript" src="js/servermonitor.js"></script>
	.
	<script src="js/mobileview2.js"></script>
</body>
</html>