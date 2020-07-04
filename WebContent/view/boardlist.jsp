<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>

		<c:if test="${postPaging.hasNoPosts()}">
			<tr>
				<td colspan="4">작성된 게시글이 없습니다.</td>
			</tr>
		</c:if>

		<c:forEach var="posts" items="${postPaging.content}">
			<tr>
				<td>${posts.postId}</td>
				<td><a
					href="read.do?no=${posts.postId}&pageNo=${postPaging.currentPage}"><c:out
							value="${posts.postTitle}" /></a></td>
				<td>${posts.writerName}</td>
				<td>${posts.postView}</td>
			</tr>
		</c:forEach>


		<!-- --------------------------------------------------------- -->

		<c:if test="${postPaging.hasPosts()}">
			<tr>
				<td colspan="4"><c:if
						test="${postPaging.startPage > postPaging.pagecount}">
						<a href="list.do?pageNo=${postPaging.startPage - postPaging.pagecount}"> [ 이전 ] </a>
					</c:if>
					
					<c:forEach var="pageNums" begin="${postPaging.startPage}" end="${postPaging.endPage}">
						<a href="list.do?pageNo=${pageNums}">[ ${pageNums} ]</a>
					</c:forEach>
					
					<c:if test="${postPaging.endPage < postPaging.totalPage}">
						<a href="list.do?pageNo=${postPaging.startPage + postPaging.pagecount}"> [ 다음 ] </a>
					</c:if></td>
			</tr>
		</c:if>

		<!-- --------------------------------------------------------- -->



		<tr>
			<td colspan="4"><a href="write.do"> [ 게시글 쓰기 ] </a></td>
		</tr>
	</table>
</body>
</html>