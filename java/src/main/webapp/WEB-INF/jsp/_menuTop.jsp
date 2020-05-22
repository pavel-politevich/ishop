<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.menu.line.login" var="loginName" />
            <fmt:message bundle="${loc}" key="local.menu.line.logout" var="logoutName" />
            <fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />
            <fmt:message bundle="${loc}" key="local.menu.line.main" var="MainName" />
            <fmt:message bundle="${loc}" key="local.menu.line.catalog" var="CatalogName" />
            <fmt:message bundle="${loc}" key="local.menu.line.news" var="NewsName" />
            <fmt:message bundle="${loc}" key="local.menu.line.delivery" var="DeliveryName" />

            <fmt:message bundle="${loc}" key="local.item.cart.cartname" var="CartName" />
    </head>

    <body>


        <div class="menu_line">
            <table border="0" width="100%" id="menu_line">
                <tr>
                    <td width="8%">
                        <a href="Controller?command=go_to_main">${MainName}</a>
                    </td>
                    <td width="8%">
                        <a href="Controller?command=go_to_catalog">${CatalogName}</a>
                    </td>
                    <td width="8%">
                        ${NewsName}
                    </td>
                    <td width="10%">
                        ${DeliveryName}
                    </td>
                    <td class="right_list">


                        <a href="Controller?command=get_cart">${CartName}</a> |

                        <c:if test = "${sessionScope.user.name != null}">
                            <c:out value = "${sessionScope.user.name}"/> |
                            <a href="Controller?command=signout">${logoutName}</a>
                        </c:if>

                        <c:if test = "${sessionScope.user.name == null}">
                            <a href="Controller?command=go_to_login">${loginName}</a>
                        </c:if>
                    </td>

                </tr>
            </table>
        </div>

    </body>
</html>