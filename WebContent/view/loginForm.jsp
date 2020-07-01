<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginPage</title>
</head>
<body>
	<form action="login.do" method="post">
		<c:if test="${errors.discord}">아이디 나 비밀번호가 일치하지 않습니다.</c:if>
		<p>
			아이디 : <input type="text" name="id" value="${param.id}">
		</p>
		<p>
			비밀번호 : <input type="password" name="password" value="${param.password}">
		</p>
		<p>
			<input type="submit" value="로그인">
		</p>
	</form>
</body>
</html>