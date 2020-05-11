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

    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

    <fmt:message bundle="${loc}" key="local.header.name" var="headerName" />
    <fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />

    <fmt:message bundle="${loc}" key="local.registration.login.name" var="loginName" />
    <fmt:message bundle="${loc}" key="local.registration.password.name" var="passwordName" />
    <fmt:message bundle="${loc}" key="local.registration.firstName.name" var="firstName" />
    <fmt:message bundle="${loc}" key="local.registration.LastName.name" var="lastName" />
    <fmt:message bundle="${loc}" key="local.registration.email.name" var="emailName" />
    <fmt:message bundle="${loc}" key="local.registration.phone.name" var="phoneName" />
    <fmt:message bundle="${loc}" key="local.registration.address.name" var="addressName" />
    <fmt:message bundle="${loc}" key="local.registration.dateBirth.name" var="dateBirth" />
    <fmt:message bundle="${loc}" key="local.registration.btnSubmit.name" var="btnSubmitName" />

    <fmt:message bundle="${loc}" key="local.menu.line.login" var="loginName" />
    <fmt:message bundle="${loc}" key="local.menu.line.logout" var="logoutName" />
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

		<br />

		<div class="registration_container">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="registration" />

				<TABLE class="tbl_cnt">
					<tr>
						<td>
							<label for="login">${loginName}</label>
						</td>
						<td>
							<input type="text" id="login" name="login" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="password">${passwordName}</label>
						</td>
						<td>
							<input type="password" id="psw" name="password" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="username">${firstName}</label>
						</td>
						<td>
							<input type="text" id="username" name="username" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="surname">${lastName}</label>
						</td>
						<td>
							<input type="text" id="surname" name="surname" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="email">${emailName}</label>
						</td>
						<td>
							<input type="email" id="email" name="email" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="phone">${phoneName}</label>
						</td>
						<td>
							<input type="text" id="phone" name="phone" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="address">${addressName}</label>
						</td>
						<td>
							<input type="text" id="address" name="address" required>
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="dateOfBirth">${dateBirth}</label>
						</td>
						<td>
							<input type="date" id="dateOfBirth" name="dateOfBirth" required>
								<br />
								<br />
						</td>
					</tr>
														</table>


				<input type="submit" value="${btnSubmitName}">
			</form>
		</div>


    </main>
    </div>

    <footer>
		${copyright}
    </footer>

</body>
</html>