<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/user/user.js"></script>

</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
	<div class="container-wrap">
		<div id="fh5co-blog">
			<div class="row">
				<div
					style="text-align: center; vertical-align: middle; font-size: x-large; color: black; font-weight: bold;">
					자유게시판</div>
					
			</div>
		</div>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
					<!-- vo.getId(),vo.getName(),vo.getPhone(),vo.getEmail(),vo.getIdx(),UserService.getUserRasUuid("nouser"),UserService.getNotUserRasUuid("djqkdaos") -->
						<th id="table">IDX</th>
						<th id="table">ID</th>
						<th id="table">이름</th>
						<th id="table">전화번호</th>
						<th id="table">등록된 단말기</th>
						<th id="table">미등록 단말기</th>
						<th id="table">사용자 삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="alllist" items="${userlist}">
						<tr>
							<td id="table">${alllist.idx }</td>
							<td id="table">${alllist.id }</td>
							<td id="table">${alllist.name }</td>
							<td id="table">${alllist.phone }</td>
							<td id="table"><form:select path="userlist" items="${alllist.uuid}" cssClass="form-control"/></td>
							<td id="table"><form:select id="nouuidSelect${alllist.idx }" path="userlist" onchange="nouuidSelect('${alllist.idx}','${alllist.id }')" items="${alllist.nouuid}" cssClass="form-control"/></td>
							<td id="table"><button id="delete_user_btn" class="btn btn-info" onClick="deleteuser('${alllist.id }')">삭제</button></td>
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