<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
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
	<form id="join-form" action="update.do" method="POST">
	<span class="badge badge-secondary">No. ${updateReq.postId}</span><input type="hidden" name="pid" value="${updateReq.postId}">
	<span class="badge badge-success"> 글을 수정 중입니다.</span>
  <div class="form-group">
    <input type="text" class="form-control" id="exampleFormControlInput1" name="title" value="${updateReq.title}">
    <c:if test="${errors.title_empty}"><span style="color:red;">제목을 입력하세요</span></c:if>
  </div>
  
  <div class="form-group">
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="30" cols="100"name="content">${updateReq.content}</textarea>
  </div>
  
  <button type="submit" class="btn btn-primary btn-lg btn-block">수정</button>
</form>
	
	<!-- Jquery js file -->
	<script src="/board/view/jquery.3.5.1.js"></script>
	<!-- Bootstrap js file -->
	<script src="/board/view/bootstrap.min.js"></script>
	
</body>
</html>