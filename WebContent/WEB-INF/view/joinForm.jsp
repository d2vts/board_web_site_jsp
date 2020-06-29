<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JoinForm</title>
</head>
<body>
<form action="join.do" method="post">
<p>
아이디 : <input type="text" name="id" value="${param.id}">
<c:if test="${errors.id}">이름을 입력하세요</c:if>
<c:if test="${errors.dulicatedId}">이미 사용중인 아이디입니다.</c:if>
</p>
<p>
이름 : <input type="text" name="name" value="${param.name}">
<c:if test="${errors.name}">이름을 입력하세요</c:if>
</p>
<p>
비밀번호 : <input type="password" name="password" value="${param.password}">
<c:if test="${errors.password}">비밀번호을 입력하세요</c:if>
</p>
<p>
비밀번호 확인 : <input type="password" name="confirmPassword" value="${param.confirmPassword}">
<c:if test="${errors.confirmPassword}">이름을 입력하세요</c:if>
<c:if test="${errors.notMatch}">암호와 확인이 일치하지 않습니다.</c:if>
</p>
<input type="submit" value="가입">
</form>
</body>
</html>