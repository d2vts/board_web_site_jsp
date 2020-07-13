<%@ tag body-content="empty" pageEncoding="utf-8"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%
	value = value.replaceAll("\n", "\n<br>");
	value = value.replaceAll("&", "&amp");
	value = value.replaceAll("<", "&lt;");
	value = value.replaceAll(" ", "&nbsp;");
%>

<%= value %>
	
