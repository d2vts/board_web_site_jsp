<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 삭제 여부 확인</title>
</head>
<body>

	<h3>게시물을 삭제 하시려면 비밀번호를 입력해주세요</h3>
	<form action="delete.do" method="POST">
	<input type="hidden" type="text" name="pid" value="${pid}">
		<p> 비밀번호 <input type="password" name="password">
		<c:if test="${errors.NotMatchPW}">비밀번호가 일치하지 않습니다.</c:if>
		<c:if test="${errors.NFExcep}">비밀번호가 일치하지 않습니다.</c:if>
		<p> <input type="submit" value="삭제">
		</form>&nbsp;&nbsp; <a href="/board/read.do?pid=${pid}">돌아가기</a>
</body>
</html>