<%@ page language="java" import="by.lifetech.ishop.bean.AuthorizedUser" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h1>Main Page</h1>
<h1>

<%

    if (request.getAttribute("operation") != null && request.getAttribute("operation").equals("registration")) {
            out.println("Регистрация прошла успешно!");
    }


    else if (request.getAttribute("user") != null) {
        AuthorizedUser user = (AuthorizedUser)request.getAttribute("user");
        out.println("Добро пожаловать, " + user.getName());
    }
    else {
        out.println("Пользователь не найден!");
    }

%>

</h1>
</body>
</html>
