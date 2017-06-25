<%@page import="sample.user.User"%>
<html>
<body>
<%@page import="org.apache.shiro.authc.UsernamePasswordToken"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%
Subject currentUser = SecurityUtils.getSubject();
%>

<% if (currentUser.isAuthenticated()){ %>
 <h2>Hello <%= currentUser.getPrincipal() %></h2>
 	Admin:<%= currentUser.hasRole("admin") %><br>
 	User: <%= currentUser.hasRole("user") %><br>
<% }else{ %>
	False
<% } %> 
	
</body>
</html>

	