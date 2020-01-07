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
					최근 알림 목록</div>
				<br>
				<div class="row">
					<table class="table table-striped">
				<thead>
					<tr>
						<th id="table">번호</th>
						<th id="table">일시</th>
						<th id="table">감지기번호</th>
						<th id="table">주소</th>
						<th id="table">상태</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="index" value="${limit}"></c:set>
					<c:forEach var="list" items="${firehisotry}">
						<c:set var="index" value="${index +1}" />
						<tr>
							<td id="table">${list.idx}</td>
							<td id="table">${list.firedate}</td>
							<td id="table">${list.s_uuid}</td>
							<td id="table">${list.address }</td>
							<td id="table">${list.status }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>