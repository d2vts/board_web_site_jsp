<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="reply.command.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 보기</title>
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
	
	<div class="blog-post outer-post">
        <h2 class="blog-post-title"><c:out value='${postinfo.postTitle}' /></h2>
        <p class="blog-post-meta"><span class="badge badge-info">${postinfo.postId}</span>&nbsp;<strong>${postinfo.writerName}</strong></p>
		<div class="blog-post inner-post">
		<textarea class="form-control" id="exampleFormControlTextarea1" rows="30" cols="100"name="content" readonly>${postinfo.postContent}</textarea>
		</div>
        
		<c:set var="pageNo"	value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}" class="badge badge-secondary">목록으로</a>
				<c:if
					test="${loginUser.id == postinfo.writerId}">
					<a href="update.do?pid=${postinfo.postId}" class="badge badge-warning">수정하기</a>
					<a href="delete.do?pid=${postinfo.postId}" class="badge badge-danger">삭제하기</a>
				</c:if>
		</div>
	
	<h3 id="readpost-comment">Comment</h3>
<c:if test="${! empty loginUser}">
	<form class="reply-form" action="reply.do" method="POST"> <input type="hidden" name="pid" value="${postinfo.postId}">
		

			<textarea class="form-control comment-textarea"rows="5" cols="50" name="content"
						placeholder="댓글을 입력하세요"></textarea>
		
		<button type="submit" class="btn btn-primary btn-lg btn-block">댓글 작성</button>
			
		
		
	</form>
	</c:if>
	
	
	




	<c:forEach var="rp" items="${reply}">
	<div class="comment-container">
	<div class="c-wrtier-date"><h4>${rp.writerId}</h4><span class="badge badge-dark"><fmt:formatDate value="${rp.regDate}" pattern="yyyy년 MM월 dd일"/></span></div>
	<div class= "c-content">${rp.content}</div>
	</div>
	</c:forEach>
<!-- Jquery js file -->
	<script src="/board/view/jquery.3.5.1.js"></script>
	<!-- Bootstrap js file -->
	<script src="/board/view/bootstrap.min.js"></script>

</body>
</html>