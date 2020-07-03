<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>

	<form action="write.do" method="POST">
		<p>
			제목 : <input type="text" name="title" value="${param.title}">
			<c:if test="${errors.title}">제목을 입력하세요</c:if>
		</p>
		<p>
			<textarea rows="5" cols="50" name="content" placeholder="내용을 입력하세요">${param.content}</textarea>
		</p>
		<input type="submit" value="등록">

	</form>
</body>
</html>