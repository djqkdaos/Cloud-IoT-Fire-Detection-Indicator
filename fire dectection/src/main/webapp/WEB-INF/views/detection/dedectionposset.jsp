<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> F.C.S </title>

<link rel="stylesheet" href="/css/user/profileEdu.css">
<link rel="stylesheet" href="/css/user/profileCareer.css">
<link rel="stylesheet" href="css/user/certificate.css">
<script type="text/javascript" src="js/profile.js"></script>
<script type="text/javascript" src="js/detection.js"></script>


<style>
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
</style>
<!-- <script type="text/javascript" src="js/user/certificate.js"></script> -->
</head>
<body>
	<!-- 우측 최상단 메뉴 -->
	<jsp:include page="../userMenu.jsp" />

	<!-- 상단 내비게이션 -->
	<jsp:include page="../navibar.jsp" />
	<!-- 상단 내비 div END -->

	<!-- 프로필 -->
	<div class="container-wrap" style="background-color: #e8e8e8;">
		<!-- 블로그레이아웃 -->
		<div class="row blogLayout animate-box">
			<!-- 좌측 (프로필작성란) -->
			<div class="leftcolumn" id="leftcolumn">
				<!-- 인적사항 -->
				<div id="personal" class="card">
					<div class="row">
						<h3>감지기 등록</h3>
					</div>
					<div id="box_personal">
							<div class="row rere" id="mapdiv">
								<img src="images/sample.png" id="mapimg">
							</div>
							<div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></div>
					</div>
				</div>


			</div>

			<!-- 우측(사이드 네비게이션) -->
			<div class="rightcolumn">
				<div class="card sidenav" id="sidenav">
					<h4>감지기 목록</h4>
					<div>
						<label>선택하세요</label>
						<%-- select id="s_uuidlist" class="selectlist" items="${selectlist }"></select> --%>
						<form:select path="selectlist" items="${selectlist}"
							cssClass="form-control" id="gamzilist" />
						<button class="btn btn-primary " id="gamziadd">추가하기</button>
					</div>
					<br>
					<br>
					<h4>도면 목록</h4>
					<label>선택하세요</label>
					<%-- select id="s_uuidlist" class="selectlist" items="${selectlist }"></select> --%>
					<form:select path="filelist" items="${filelist}"
						cssClass="form-control" id="maplist" />
					<br>
				</div>

			</div>
			<!-- 사이드네비 div END -->
		</div>
		<!-- 블로그레이아웃 div END -->
	</div>
	<!-- 프로필(container-wrap) div END -->
</body>
</html>