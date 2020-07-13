<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 삭제 여부 확인</title>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Roboto&display=swap" rel="stylesheet">

	<!-- Bootstrap css file -->
	<link rel="stylesheet" type="text/css" href="/board/view/css/bootstrap.min.css">

	<!-- font awesome icons -->
	<link rel="stylesheet" type="text/css" href="/board/view/css/all.min.css">

	<!-- custom css file -->
	<link rel="stylesheet" type="text/css" href="/board/view/css/style.css">
</head>
<body>
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
							<a class="nav-link" href="https://github.com/d2vts/board_web_site_jsp">GIT HUB</a>
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

	<h3 style="text-align:center; margin-top:3rem; padding: 1%">게시물을 삭제 하시려면 비밀번호를 입력해주세요</h3>
	
	<form id ="login-form" action="delete.do" method="POST">
	<input type="hidden" type="text" name="pid" value="${pid}">
	
  <div class="form-group">
    <label>비밀번호</label>
    <input type="password" name="password" class="form-control" id="exampleInputPassword1" required>
    <c:if test="${errors.NotMatchPW}"><span style="color:red;">비밀번호가 일치하지 않습니다.</span></c:if>
		<c:if test="${errors.NFExcep}"><span style="color:red;">비밀번호가 일치하지 않습니다.</span></c:if>
  </div>
  
  <button type="submit" class="btn btn-primary">삭제</button>
  
</form>
	
	
	<h3 style="text-align:center; padding: 1%;"><a class="delete-confirm-back" href="/board/read.do?pid=${pid}">게시물로 돌아가기</a></h3>
	
	
	<!-- Jquery js file -->
	<script src="/board/view/jquery.3.5.1.js"></script>
	<!-- Bootstrap js file -->
	<script src="/board/view/bootstrap.min.js"></script>
	
</body>
</html>