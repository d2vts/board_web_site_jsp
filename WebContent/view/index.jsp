<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP INDEX</title>
</head>
<body>

<!-- 로그인상태-->
<c:if test="${! empty loginUser}">
${loginUser.name}님, 환영합니다.
<p><a href="/board/logout.do"> 로그아웃 </a></p>
<p><a href="/board/mypage.do"> 마이페이지 </a></p>
</c:if>
<!-- 로그인상태 -->

<!-- 비로그인 상태-->
<c:if test="${empty loginUser}">
<p><a href="/board/join.do"> 회원가입 </a></p>
<p><a href="/board/login.do"> 로그인 </a></p>
</c:if>
<!-- 비로그인 상태-->

</body>
</html>