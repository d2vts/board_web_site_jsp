<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 페이지</title>
</head>
<body>

	<form action="withdrawal.do" method="post">
		<span>비밀번호를 입력하세요 .</span>
		<span><input type="password" name="password"></span>
		<span><input type="submit" value="회원 탈퇴"></span>
		<span>한 번 탈퇴된 회원 정보는 복구되지 않습니다.</span>
		<c:if test="${errors.NotMatchPW}"><span style="color:red"> 비밀번호가 일치하지 않습니다. </span></c:if> 
	</form>
</body>
</html>