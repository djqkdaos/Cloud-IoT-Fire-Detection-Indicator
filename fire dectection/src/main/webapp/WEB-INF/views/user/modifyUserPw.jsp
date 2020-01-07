<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/user/user.css">
<link rel="stylesheet" href="/css/user/modifyUserPw.css">
<script type="text/javascript" src="/js/user/modifyUserPw.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/sha256.js"></script>	

</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />

<div class="container-wrap">
<div class="page-modifyUserPw">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-6 box-modifyUserPw">
	<h2 class="text-center"><strong>비밀번호 변경</strong></h2>
	<input type="hidden" id="id" class="form-control" value="${login.id}">
	<input type="hidden" id="chkModPw" value="${chkModPw}">
	<input type="password" id="pw" class="form-control" placeholder="현재 비밀번호">
	<div id="feedbackPw" class="feedback"></div>
	<input type="password" id="newPw" class="form-control" placeholder="새 비밀번호">
	<div id="feedbackNewPw" class="feedback"></div>
	<input type="password" id="rePw" class="form-control" placeholder="새 비밀번호 확인">
	<div id="feedbackRePw" class="feedback"></div>
	<div class="feedback"></div>
	<input type="button" id="btn_modifyUserPW" class="btn btn-primary" value="비밀번호변경">
</div>
<div class="col-sm-3"></div>

</div>
</div>
</div>
</body>
</html>