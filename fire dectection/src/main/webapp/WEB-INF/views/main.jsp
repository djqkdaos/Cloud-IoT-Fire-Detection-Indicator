<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/bootstrap.css">
<style type="text/css">
	@media ( max-width : 768px) {
	.btn-lg {
		padding: 1px 5px;
		font-size: 12px;
		line-height: 1.5;
		border-radius: 3px;
		margin-top: 0px;
		margin-bottom: 16.5px;
		padding-left: 2px;
		padding-right: 2px;
		
		}
	}
</style>
<script src="js/mainJs.js"></script>
</head>
<body>
	<jsp:include page="userMenu.jsp" />
	<jsp:include page="navibar.jsp" />
	<div class="container-wrap">

		<div id="myCarousel" class="carousel slide">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li class="item1 active"></li>
				<li class="item2"></li>
				<li class="item3"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">

				<div class="item active">
					<img src="images/main01.png" alt="main01">
					<div class="carousel-caption carousel-caption-title">
						<h1>
							<strong>불끄는 구름 새로운 화재예방 솔루션</strong>
						</h1>
						<h3>
							<strong>당신의 스마트폰으로 빠르게 화재를 알 수 있습니다.</strong>
						</h3>
					</div>
					<div class="carousel-caption carousel-caption-btn">
						<c:choose>
							<c:when test="${login.type eq 'mentor'}">
								<button class="btn btn-primary btn-lg btn-free">수신기관리</button>
								<button class="btn btn-primary btn-lg btn-free">도면관리</button>
							</c:when>
							<c:when test="${login.type eq 'mentee'}">
								<button class="btn btn-primary btn-lg btn-free">수신기관리</button>
								<button class="btn btn-primary btn-lg btn-free">도면관리</button>
							</c:when>
							<c:otherwise>
								<div class="col-xs-6">
									<button class="btn btn-lg btn-signup">회원가입</button>
								</div>
								<div class="col-xs-6">
									<button class="btn btn-primary btn-lg btn-login">로그인</button>	
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="item">
					<img src="images/main02.png" alt="main02">
					<div class="carousel-caption carousel-caption-title">
						<h1>
							<strong>불끄는 구름 새로운 화재예방 솔루션</strong>
						</h1>
						<h3>
							<strong>시각화된 화재 데이터로 빠르게 화재를 진압하십시오.</strong>
						</h3>
					</div>
					<div class="carousel-caption carousel-caption-btn">
						<c:choose>
							<c:when test="${login.type eq 'mentor'}">
								<button class="btn btn-primary btn-lg btn-free">수신기관리</button>
								<button class="btn btn-primary btn-lg btn-free">도면관리</button>
							</c:when>
							<c:when test="${login.type eq 'mentee'}">
								<button class="btn btn-primary btn-lg btn-free">수신기관리</button>
								<button class="btn btn-primary btn-lg btn-free">도면관리</button>
							</c:when>
							<c:otherwise>
								<div class="col-xs-6">
									<button class="btn btn-primary btn-lg btn-signup">회원가입</button>
								</div>
								<div class="col-xs-6">
									<button class="btn btn-primary btn-lg btn-login">로그인</button>	
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="item">
					<img src="images/main03.png" alt="main03">
					<div class="carousel-caption carousel-caption-title">
						<h1>
							<strong>불끄는 구름 새로운 화재예방 솔루션</strong>
						</h1>
						<h3>
							<strong>실시간 모니터링으로 건물화재상황을 실시간으로 감시하세요</strong>
						</h3>
					</div>
					<div class="carousel-caption carousel-caption-btn">
						<c:choose>
							<c:when test="${login.type eq 'mentor'}">
								<button class="btn btn-primary btn-lg btn-free">수신기관리</button>
								<button class="btn btn-primary btn-lg btn-free">도면관리</button>
							</c:when>
							<c:when test="${login.type eq 'mentee'}">
								<button class="btn btn-primary btn-lg btn-free">수신기관리</button>
								<button class="btn btn-primary btn-lg btn-free">도면관리</button>
							</c:when>
							<c:otherwise>
								<div class="col-xs-6">
									<button class="btn btn-primary btn-lg btn-signup">회원가입</button>
								</div>
								<div class="col-xs-6">
									<button class="btn btn-primary btn-lg btn-login">로그인</button>	
								</div>	
							</c:otherwise>
						</c:choose>
					</div>
				</div>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>

	</div>
	<!-- END container-wrap -->
</body>
</html>