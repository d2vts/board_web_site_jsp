<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 보기</title>
</head>
<body>

<table border="1" style="border:1px solid gray; width:95%">

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
	<td colspan="2">
	<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
	<a href="list.do?pageNo=${pageNo}">목록</a>
	<c:if test="${loginUser.id == postinfo.writerId}">
	<a href="update.do"> [ 수정 ] </a>
	<a href="delete.do"> [ 삭제 ] </a>
	</c:if>
	</td>
</tr>

</table>

</body>
</html>