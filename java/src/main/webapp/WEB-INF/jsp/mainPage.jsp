<%@ page language="java" import="by.lifetech.ishop.bean.AuthorizedUser" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h2>Main Page</h2>
<h1>Hello,

<%
	AuthorizedUser user = (AuthorizedUser)request.getAttribute("user");
	out.println(user.getName());
%>

</h1>
</body>
</html>
