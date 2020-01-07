<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>navibar</title>
</head>
<body>
	<nav class="fh5co-nav" role="navigation">
	<div class="container-wrap">
		<div class="top-menu">
			<div class="row">
				<div class="col-xs-3">
					<div id="fh5co-logo">
						<a href="main.do">F.C.S</a>
					</div>
				</div>
				<div class="col-xs-9 text-right menu-1">
						<c:set var="dataa" value="${sessionScope.login.id} "/>
					<ul>
						<li class=""><a href="main.do">홈</a></li>
						
						<!-- <li class="has-dropdown"><a href="intro1.do">소개</a>
							<ul class="dropdown">
								<li><a href="intro1.do">사업소개</a></li>
								<li><a href="intro2.do">서비스소개</a></li>
							</ul></li> -->
						<li class="has-dropdown"><a href="setposition.do">수신기설정</a></li>
						<li class="has-dropdown"><a href="viewmap.do">도면관리</a>
							<ul class="dropdown">
								<li><a href="viewmap.do">건물도면 관리</a></li>
								<li><a href="mymap.do">건물도면 보기</a></li>
							</ul></li>
						<li class="has-dropdown"><a href="freeBoard.do?page_num=1">고객센터</a>
							<ul class="dropdown">
								<li><a href="freeBoard.do?page_num=1">공지사항</a></li>
								<li><a href="qnaBoard.do?page_num=1">Q&A</a></li>
							</ul></li>
						<!-- <li class="has-dropdown"><a href="#">이용안내</a></li> -->
						<li class="has-dropdown"><a href="fireMYhistory.do">알림센터</a>
							<ul class="dropdown">
								<li><a href="fireMYhistory.do">알림내역</a></li>
								<li><a href="firemonitor.do">화재 모니터링</a></li>
								<li><a href="firehistorry.do">화재 기록</a></li>
								<li><a href="firemapview.do">화재 도면 보기</a></li>
							</ul></li>
						<li class="has-dropdown"><a href="monitor.do">화재모니터링</a></li>
						<c:if test="${dataa eq 'test '}">
							<li class="has-dropdown"><a href="freeBoard.do?page_num=1">관리자메뉴</a>
								<ul class="dropdown">
								<li><a href="userManager.do">사용자관리</a></li>
								<li><a href="serverStatus.do">서버상태</a></li>
								<li><a href="mapManger.do">도면관리</a></li>
								<li><a href="detectorManager.do">수신기관리</a></li>
							</ul></li>
							</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</nav>
</body>
</html>