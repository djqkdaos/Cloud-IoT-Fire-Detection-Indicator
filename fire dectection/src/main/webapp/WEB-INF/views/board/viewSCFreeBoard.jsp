<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/board/viewFreeBoard.css">
	<script type="text/javascript" src="js/board/viewSCFreeBoard.js"></script>
	<title>View Search Board</title>
</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
	<div class="container-wrap">
	<input type="hidden" id="thisPage" value="${thisPage}">
	<input type="hidden" id="prevfb_num" value="${prevfb_num}">
	<input type="hidden" id="nextfb_num" value="${nextfb_num}">
	<input type="hidden" id="fb_num" value="${board.fb_num}">
	<input type="hidden" id="condition" value="${condition}">
	<input type="hidden" id="search" value="${search}">
		<div class="row">
			<div class="col-sm-4" style="text-align: left;">
				<button id="prevBtn" class="btn btn-primary">이전 글</button>
				<button id="nextBtn" class="btn btn-warning">다음 글</button>
			</div>
			<div class="col-sm-4"></div>
			<div class="col-sm-4" style="text-align: right;">
				<button id="listBtn" class="btn btn-success">목록</button>
				<c:if test="${board.id eq login.id}">
					<button id="modifyBtn" class="btn btn-info">수정</button>
					<button id="deleteBtn" class="btn btn-danger">삭제</button>
				</c:if>
			</div>
		</div>
		<div class="row">
			<br>
			<div class="col-sm-7" style="text-align: left;">
				제목 : ${board.title}
			</div>
			<div class="col-sm-5" style="text-align: right;">
				작성일 : ${board.regDate} |
				작성자 : ${board.id} |
				조회수 : ${board.hit}
			</div>
		</div>
		<div class="row">
			<br>
			<div id="boardContent" class="col-sm-12">
				내용 : ${board.content}
			</div>
		</div>
	</div>
	<jsp:include page="../reply/reply.jsp" />
</body>
</html>