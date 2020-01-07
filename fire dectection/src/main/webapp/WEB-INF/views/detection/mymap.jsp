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
</style>
</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
	<div class="container-wrap">
		<div id="fh5co-blog">
			<div class="row">
				<div
					style="text-align: center; vertical-align: middle; font-size: x-large; color: black; font-weight: bold;">
					등록된 도면 보기</div>
				<br>
				<h4>도면 목록</h4>
					<label>선택하세요</label>
					<form:select path="filelist" items="${filelist}"
						cssClass="form-control" id="maplist" />
				<div id="box_personal">
					<div class="row rere" id="mapdiv">
						<img src="images/sample.png" id="mapimg">
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>