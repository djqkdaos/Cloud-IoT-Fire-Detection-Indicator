<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/board/freeBoard.css">
<script type="text/javascript" src="js/fileupload.js"></script>

<title>Free Board</title>
<style type="text/css">
.inline {
	display: inline;
}
.wi{
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
					도면 업로드</div>
				<br>
				<form id="file" action="/uploadFile" method="post"
					enctype="multipart/form-data">
					<div class="col-md-4 col-md-push-4 animate-box">
						<div class="row">
							<!-- <div id="iconDiv" class="form-group text-center">
							<img alt="" src="images/profile.png" id="" width="200px" height="200px">
							<input type="file" name="icon" accept="image/*">
							</div> -->
							<div id="idDiv" class="form-group form-inline">
								<input id="file" name="file" type="file" class="form-control"
									placeholder="아이디" requierd="requierd" style="width: 65%;">
								<input type="submit" class="bnt btn-lg btn-primary" value="업로드">
								<input type="hidden" id="id" name="id" value="${id}">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="container-wrap">
		<div id="fh5co-blog">
			<div class="row">
				<div
					style="text-align: center; vertical-align: middle; font-size: x-large; color: black; font-weight: bold;">
					등록된 도면 목록</div>
				<br>
				<div class="row">
					<table class="table table-striped">
				<thead>
					<tr>
						<th id="table">고유번호</th>
						<th id="table">파일크기</th>
						<th id="table">등록일</th>
						<th id="table">명칭</th>
						<th id="table">수정</th>
						<th id="table">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="index" value="${limit}"></c:set>
					<c:forEach var="list" items="${filelist}">
						<c:set var="index" value="${index +1}" />
						<tr>
							<td id="table">${list.hash}</td>
							<td id="table">${list.filesize}</td>
							<td id="table">${list.regdate}</td>
							<td id="table"><input type="text" name="new_name" id="new_name${index}" class="form-control wi" value="${list.new_name}"></td>
							<td id="table"><button id="updatename_bnt" class="btn btn-info  btn-ms" onClick="updatename('${list.hash }','${index}','${list.old_name}')">수정</button>
							</td>
							<td id="table"><button id="delete_map_btn" class="btn btn-danger btn-ms" onClick="deletemap('${list.hash }')">삭제</button>
							</td>
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