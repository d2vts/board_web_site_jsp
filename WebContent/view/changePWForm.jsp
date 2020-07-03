<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
<form action="changePW.do" method="POST">
<p>
현재 비밀번호 : <input type="password" name="curPW">
<c:if test="${errors.curPW }">현재 비밀번호를 입력하세요.</c:if>
<c:if test="${errors.notMatchCurPW }">현재 비밀번호가 일치하지 않습니다.</c:if>
</p>
<p>
새 비밀번호 : <input type="password" name="newPW">
<c:if test="${errors.newPW }">새 비밀번호를 입력하세요.</c:if>
</p>
<input type="submit" value="비밀번호 변경">
</form>
</body>
</html>