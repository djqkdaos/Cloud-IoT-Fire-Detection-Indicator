<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/css/board/insertCsBoard.css?ver=1">
	<script src="https://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/board/insertCsBoard.js?ver=1"></script>
	<script type="text/javascript" src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
 	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
 	<div class="container-wrap">
 		<div class="row">
 			<div class="col-sm-2"></div>
 			<div class="col-sm-10">
			    <form action="/insertCsBoard.do" method="post" id="insertBoardFrm" enctype="multipart/form-data">
			    	<div class="row">&nbsp;</div>
			    	<div class="row">
			    		<div class="form-group">
				    		제목 : <input id="title" type="text" class="form-control" placeholder="글 제목" required="required">
					    	<div class="row" id="secret">
					    		 비밀글 <input id="secret_chk" type="checkbox" class="form-control" > 
					    		<input id="secret_pw" type="password" class="form-control" placeholder="비밀번호" disabled>
					    	</div>
			    		</div>
			    		<input type="hidden" name="id" value="${login.id}">
			    	</div>
			    	<div class="row">&nbsp;</div>
			    	<div class="row">
				        <textarea name="editor" id="editor"></textarea>
			    	</div>
			    	<div class="row">&nbsp;</div>
			        <div class="row" id="btns">
				        <input type="button" id="insertBoard" class="btn btn-info" value="등록" />
				       	<input type="button" id="cancelBtn" class="btn btn-danger" value="취소" />
	 				</div>
			    </form>
 			</div>
 		</div>
 	</div>
</body>
</html>