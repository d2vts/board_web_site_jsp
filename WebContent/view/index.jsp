<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>JSP MAIN</title>
	<!-- fonts file file -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Roboto&display=swap" rel="stylesheet">

	<!-- Bootstrap css file -->
	<link rel="stylesheet" type="text/css" href="/board/view/css/bootstrap.min.css">

	<!-- font awesome icons -->
	<link rel="stylesheet" type="text/css" href="/board/view/css/all.min.css">

	<!-- custom css file -->
	<link rel="stylesheet" type="text/css" href="/board/view/css/style.css">
</head>

<body>
	<!-- =====================TOP BAR===================== -->
	<div class="top-bar"> <a href="#" class="top-bar-a">GO TOP</a></div>
	<!-- =====================TOP BAR===================== -->


	<!-- =====================Start Header Area===================== -->

	<header class="header_area">
		<div class="main-menu">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="/board/index.do">D2VTS</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item active">
							<a class="nav-link" href="/board/index.do">Home <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/board/list.do">Board</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="https://github.com/d2vts/board_web_site_jsp">GITHUB</a>
						</li>
					</ul>
				</div>
				<div class="mr-auto nav-item active .navbar-expand-lg">


					<!-- =====================로그인 상태===================== -->
					<c:if test="${! empty loginUser}">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link nav-link2" href="/board/mypage.do">마이페이지</a></li>
							<li class="nav-item"><a class="nav-link nav-link2" href="/board/logout.do">로그아웃</a></li>
						</ul>
					</c:if>
					<!-- =====================로그인 상태===================== -->



					<!-- =====================비로그인 상태===================== -->

					<c:if test="${empty loginUser}">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link nav-link2" href="/board/join.do">회원가입</a></li>
							<li class="nav-item"><a class="nav-link nav-link2" href="/board/login.do">로그인</a></li>
						</ul>

					</c:if>
					<!-- =====================비로그인 상태===================== -->

				</div>
			</nav>

		</div>
	</header>

	<!-- =====================End Header Area===================== -->

	


	<!-- =====================Start Main Area===================== -->

	<main class="site-main">


		<!-- =====================Start Banner Area===================== -->
		<section class="site-banner">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12 site-title">

						<!-- =====================로그인 했을시===================== -->
						<c:if test="${! empty loginUser}">
							<h3 class="title-text title-text1">${loginUser.name}님, 환영합니다.</h3>
						</c:if>
						<!-- =====================로그인 했을시===================== -->

						<!-- =====================비로그인시===================== -->
						<c:if test="${empty loginUser}">
							<h3 class="title-text title-text1">방문해주셔서 감사합니다.</h3>
						</c:if>
						<!-- =====================비로그인시===================== -->
						<h1 class="title-text text-uppercase title-text2">게시판 사이트 BY JSP</h1>
						<h4 class="title-text text-uppercase title-text3">Web Developer_LEE TAE SEUNG</h4>
						<div class="site-buttons">
							<div class="d-flex flex-row flex-wrap">
								<button class="btn button primary-button mr-4 text-uppercase">github</button>
								<button class="btn button secondary-button mr-4 text-uppercase">Contact</button>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-12"><img src="/board/view/img/banner/index_banner.jpg" alt="banner-img"
							class="img-fluid"></div>
				</div>
			</div>
		</section>

		<!-- =====================End Banner Area===================== -->
	</main>
	<!-- =====================End Main Area===================== -->
	<footer class="footer-area">
		<div class="container">
			<div class="">
				<div class="site-logo text-center py-4">
					<a href=""><img src="" alt=""></a>
				</div>
				<div class="social text-center">
					<h5 class="text-uppercase">Contact Me</h5>
					<a href="#"><i class="fab fa-github"></i></a>
					<a href="#"><i class="fab fa-instagram"></i></a>
					<a href="#"><i class="fab fa-youtube"></i></a>
					<a href="#"><i class="fab fa-twitter"></i></a>
				</div>
				<div class="copyrights text-center">
					<p class="para">
						copyright ©2020 All rights reserved | This site is made by
						<a href="#"><span style="color: var(--primary-color); font-size: 1.5rem;">D2VTS</span></a>
					</p>
				</div>
			</div>
		</div>

	</footer>

<!-- Jquery js file -->
	<script src="/board/view/jquery.3.5.1.js"></script>
	<!-- Bootstrap js file -->
	<script src="/board/view/bootstrap.min.js"></script>
</body>

</html>