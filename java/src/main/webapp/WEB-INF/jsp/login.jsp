<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<link href="/newcss.css" rel="stylesheet" type="text/css"/>

			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
			<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

			<fmt:message bundle="${loc}" key="local.header.name" var="headerName" />
			<fmt:message bundle="${loc}" key="local.signin.login.name" var="LabelLoginName" />
			<fmt:message bundle="${loc}" key="local.signin.password.name" var="LabelPasswordName" />
			<fmt:message bundle="${loc}" key="loacl.signin.submitBtn.name" var="BtnSubmitName" />
			<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />
			<fmt:message bundle="${loc}" key="loacl.signin.fail.message" var="loginFailMessage" />

			<fmt:message bundle="${loc}" key="local.menu.line.login" var="loginName" />
            <fmt:message bundle="${loc}" key="local.menu.line.logout" var="logoutName" />
            <fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />
            <fmt:message bundle="${loc}" key="local.menu.line.main" var="MainName" />
            <fmt:message bundle="${loc}" key="local.menu.line.catalog" var="CatalogName" />
            <fmt:message bundle="${loc}" key="local.menu.line.news" var="NewsName" />
            <fmt:message bundle="${loc}" key="local.menu.line.delivery" var="DeliveryName" />
            <fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />


	</head>


	<body>

		<div id="wrap">

			<header>
${headerName}
				<div class="languages">
					<table>
						<tr>
							<td>
								<form action="Controller" method="post">
									<input type="hidden" name="command" value="changeLocale" />
									<input type="hidden" name="local" value="ru" />
									<input type="submit"
						value="${ru_button}" />
									<br />
								</form>
							</td>
							<td>
								<form action="Controller" method="post">
									<input type="hidden" name="command" value="changeLocale" />
									<input type="hidden" name="local" value="en" />
									<input type="submit"
						value="${en_button}" />
									<br />
								</form>
							</td>
						</tr>
					</table>
				</div>
			</header>

			<main>

				<br />

				<div class="menu_line">
                    <table border="0" width="100%" id="menu_line">
                        <tr>
                            <td width="8%">
                                <a href="Controller?command=go_to_main">${MainName}</a>
                            </td>
                            <td width="8%">
                                ${CatalogName}
                            </td>
                            <td width="8%">
                                ${NewsName}
                            </td>
                            <td width="10%">
                                ${DeliveryName}
                            </td>
                            <td class="right_list">

                                <jsp:useBean id="user" class = "by.lifetech.ishop.bean.AuthorizedUser" type="java.lang.Object" scope="session"/>

                                <c:if test = "${user.name != null}">
                                    <c:out value = "${user.name}"/> |
                                    <a href="Controller?command=signout">${logoutName}</a>
                                </c:if>

                                <c:if test = "${user.name == null}">
                                    <a href="Controller?command=go_to_login">${loginName}</a>
                                </c:if>
                            </td>

                        </tr>
                    </table>
                </div>

                <br /><br />

				<c:if test="${param.login == 'fail'}">
                    <h2 class="login_fail_message">${loginFailMessage}</h2>
                </c:if>

				<div class="login_container">
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="signIn" />
						<label for="username">${LabelLoginName}</label>
						<input type="text" id="username" name="username" required>

						<label for="password">${LabelPasswordName}</label>
						<input type="password" id="password" name="password" required>

						<br />
						<br />

						<input class="submitLogin" type="submit" value="${BtnSubmitName}">


						<div class="register_block">
							<a href="Controller?command=go_to_register">${RegistrationName}</a>
						</div>
					</form>
				</div>


			</main>
		</div>

	<footer>
		${copyright}
	</footer>


	</body>
</html>