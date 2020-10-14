<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Roboto&display=swap"
	rel="stylesheet">

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
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNav" aria-controls="navbarNav"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item active"><a class="nav-link"
							href="/board/index.do">Home <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="/board/list.do">Board</a></li>
						<li class="nav-item"><a class="nav-link"
							href="https://github.com/d2vts/board_web_site_jsp">GIT HUB</a></li>
					</ul>
				</div>
				<div class="mr-auto nav-item active .navbar-expand-lg">


					<!-- =====================로그인 상태===================== -->
					<c:if test="${! empty loginUser}">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link nav-link2"
								href="/board/mypage.do">마이페이지</a></li>
							<li class="nav-item"><a class="nav-link nav-link2"
								href="/board/logout.do">로그아웃</a></li>
						</ul>
					</c:if>
					<!-- =====================로그인 상태===================== -->



					<!-- =====================비로그인 상태===================== -->

					<c:if test="${empty loginUser}">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link nav-link2"
								href="/board/join.do">회원가입</a></li>
							<li class="nav-item"><a class="nav-link nav-link2"
								href="/board/login.do">로그인</a></li>
						</ul>

					</c:if>
					<!-- =====================비로그인 상태===================== -->

				</div>
			</nav>

		</div>
	</header>

	<table class="table">
		<thead>

			<tr>
				<th scope="col">No</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${postPaging.hasNoPosts()}">
				<tr>
					<td colspan="4">작성된 게시글이 없습니다.</td>
				</tr>
			</c:if>

			<c:forEach var="posts" items="${postPaging.content}">
				<tr>
					<th scope="row">${posts.postId}</th>
					<td><a id="board-table-list"
						href="read.do?pid=${posts.postId}&pageNo=${postPaging.currentPage}"><c:out
								value="${posts.postTitle}" /></a></td>
					<td>${posts.writerName}</td>
					<td>${posts.postView}</td>
				</tr>
			</c:forEach>

			<tr>
			<td colspan="4"><a class="btn btn-primary" id="list-write-btn" href="write.do" role="button">게시글 작성</a></td>
			</tr>
			
		</tbody>
	</table>
	<c:if test="${postPaging.hasPosts()}">
	<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
				<c:if test="${postPaging.startPage > postPaging.pagecount}">
					<li class="page-item"><a class="page-link" href="list.do?pageNo=${postPaging.startPage - postPaging.pagecount}"
					tabindex="-1" aria-disabled="true">Previous</a></li>
					</c:if>
					<c:forEach var="pageNums" begin="${postPaging.startPage}" end="${postPaging.endPage}">
					<li class="page-item"><a class="page-link" href="list.do?pageNo=${pageNums}">${pageNums}</a></li>
					</c:forEach>
					<c:if test="${postPaging.endPage < postPaging.totalPage}">
					<li class="page-item"><a class="page-link" href="list.do?pageNo=${postPaging.startPage + postPaging.pagecount}">Next</a></li>
					</c:if>
				</ul>
			</nav>
			</c:if>
			<!-- Jquery js file -->
	<script src="/board/view/jquery.3.5.1.js"></script>
	<!-- Bootstrap js file -->
	<script src="/board/view/bootstrap.min.js"></script>
</body>
</html>