<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="jdbc.connection.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>DB Connection Test JSP</title>
</head>
<body>
<%

try(Connection conn = ConnectionProvider.getConnection()){ // try-with-resource구문 씀으로 close()안써줘도 됨
	out.println("DB Connection is Successed!");// DB 연결 성공시 출력문
}catch(SQLException e){
	out.println("DB Connection is Failed");// DB 연결 실패시 출력문
	application.log("Connection failed",e); // DB 연겨 실패시 출력로그
}

//mysql 버전따라서 타임존 설정관련 에러뜨는것 주의 

%>
</body>
</html>