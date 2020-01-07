<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/user/user.css">
<link rel="stylesheet" href="/css/user/findID.css">
<script type="text/javascript" src="/js/user/findID.js"></script>
</head>
<body>
	<jsp:include page="../userMenu.jsp" />
	<jsp:include page="../navibar.jsp" />
	
<div class="container-wrap">
<div class="page-findID">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-6 box-findID">
	<h2 class="text-center"><strong>아이디찾기</strong></h2>
	<input type="text" id="name" class="form-control" placeholder="이름">
	<input type="email" id="email" class="form-control" placeholder="이메일">
	<input type="button" id="btn_findID" class="btn btn-primary" value="아이디찾기">
</div>
<div class="col-sm-3"></div>

</div>
	<!-- 회원가입, ID 및 비밀번호 찾기 -->
	<jsp:include page="loginFooter.jsp" />
</div>
</div>
</body>
</html>