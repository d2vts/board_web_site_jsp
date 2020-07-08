<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<form action="update.do" method="POST">
		<table>
			<tr>
				<td>No. ${updateReq.postId} <input type="hidden" name="pid" value="${updateReq.postId}"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" value="${updateReq.title}"><c:if test="${errors.title_empty}"><span>제목을 입력하세요</span></c:if></td>
				
			</tr>
			<tr>
				<td><textarea name="content" rows="5" cols="30">${updateReq.content}</textarea><c:if test="${errors.content_empty}">내용을 입력하세요</c:if></td>
			</tr>
			<tr>
				<td><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
</body>
</html>