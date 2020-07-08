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
</head>
<body>
	<a href="/board/index.do">메인화면으로 가기</a>
	<table border="1" style="border: 1px solid gray; width: 95%">

		<tr>
			<td>게시 번호</td>
			<td>${postinfo.postId}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${postinfo.writerName}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value='${postinfo.postTitle}' /></td>
		</tr>
		<tr>
			<td>내용</td>
			<%-- <td>${postinfo.postContent}</td> --%>
			<td><u:pre value='${postinfo.postContent}' /></td>
		</tr>
		<tr>
			<td colspan="2"><c:set var="pageNo"
					value="${empty param.pageNo ? '1' : param.pageNo}" /> <a
				href="list.do?pageNo=${pageNo}">목록</a> <c:if
					test="${loginUser.id == postinfo.writerId}">
					<a href="update.do?pid=${postinfo.postId}"> [ 수정 ] </a>
					<a href="delete.do?pid=${postinfo.postId}"> [ 삭제 ] </a>
				</c:if></td>
		</tr>
	</table>
	<h2>댓글</h2>
<c:if test="${! empty loginUser}">
	<form action="reply.do" method="POST">
		<table border="1">

			<tr>
				<td><textarea rows="5" cols="50" name="content"
						placeholder="댓글을 입력하세요"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="댓글 작성"></td>
			</tr>
			
		</table>
		<input type="hidden" name="pid" value="${postinfo.postId}">
	</form>
	</c:if>
	
	
	




	<c:forEach var="rp" items="${reply}">
		<table border="1">
			<tr>
				<td>글쓴이</td>
				  <td>${rp.writerId}</td> 
			</tr>
			<tr>
			<td colspan="2"><fmt:formatDate value="${rp.regDate}" pattern="yyyy-MM-dd"/></td> 
			</tr>
			<tr>
				<td colspan="2">${rp.content}</td>
			</tr>
		</table>
	</c:forEach>


</body>
</html>