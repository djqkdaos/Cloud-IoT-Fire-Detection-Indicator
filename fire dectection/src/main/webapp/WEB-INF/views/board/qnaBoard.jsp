<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/board/freeBoard.css">
<script type="text/javascript" src="js/board/freeBoard.js"></script>
<title>Free Board</title>
</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
	<div class="container-wrap">
		<div id="fh5co-blog">
			<div class="row">
				<div
					style="text-align: center; vertical-align: middle; font-size: x-large; color: black; font-weight: bold;">
					Q n A</div>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th id="table">번호</th>
						<th id="table">제목</th>
						<th id="table">작성일</th>
						<th id="table">조회수</th>
						<th id="table">짝썽짜</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="index" value="${limit}"></c:set>
					<c:forEach var="freeBoard" items="${list}">
						<c:set var="index" value="${index - 1}" />
						<tr>
							<td id="table">${index}</td>
							<td id="table"><a
								href="viewFreeBoard.do?fb_num=${freeBoard.fb_num}&thisPage=${thisPage}">${freeBoard.title}</a>
							</td>
							<td id="table">${freeBoard.regDate}</td>
							<td id="table">${freeBoard.hit}</td>
							<td id="table">${freeBoard.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row" style="text-align: center;">
			<ul class="pagination">
				<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a></li>
				<c:forEach var="i" begin="1" end="${page}" step="1">
					<c:if test="${thisPage eq i}">
						<li class="page-item active"><a class="page-link"
							href="freeBoard.do?page_num=${i}">${i}</a></li>
					</c:if>
					<c:if test="${thisPage ne i}">
						<li class="page-item"><a class="page-link"
							href="freeBoard.do?page_num=${i}">${i}</a></li>
					</c:if>
				</c:forEach>
				<li class="page-item disabled"><a class="page-link" href="#">&raquo;</a></li>
			</ul>
		</div>
		<div class="row">
			<form action="searchFreeBoard.do" method="post">
				<div id="searchSelect" class="form-group col-sm-4">
					<select name="condition" class="custom-select">
						<option value="title">글 제목</option>
						<option value="id">작성자</option>
					</select>
				</div>
				<div id="search" class="form-group col-sm-4">
					<input id="searchContent" type="text" name="search"
						class="form-control" placeholder="검색">
				</div>
				<div class="form-group col-sm-1">
					<button id="searchBoardBtn" type="submit" class="btn btn-success">검색</button>
				</div>
				<input type="hidden" name="page_num" value="${thisPage}">
			</form>
			<div class="form-group col-sm-3" style="text-align: left;">
				<button id="insertBoardBtn" class="btn btn-info">글쓰기</button>
			</div>
		</div>
	</div>
</body>
</html>