<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="newcss.css" rel="stylesheet" type="text/css"/>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />

    <fmt:message bundle="${loc}" key="local.registration.login.name" var="loginNameLbl" />
    <fmt:message bundle="${loc}" key="local.registration.password.name" var="passwordName" />
    <fmt:message bundle="${loc}" key="local.registration.firstName.name" var="firstName" />
    <fmt:message bundle="${loc}" key="local.registration.LastName.name" var="lastName" />
    <fmt:message bundle="${loc}" key="local.registration.email.name" var="emailName" />
    <fmt:message bundle="${loc}" key="local.registration.phone.name" var="phoneName" />
    <fmt:message bundle="${loc}" key="local.registration.address.name" var="addressName" />
    <fmt:message bundle="${loc}" key="local.registration.dateBirth.name" var="dateBirth" />
    <fmt:message bundle="${loc}" key="local.registration.btnSubmit.name" var="btnSubmitName" />
    <fmt:message bundle="${loc}" key="local.registration.fail.message" var="registrationFailMessage" />
    <fmt:message bundle="${loc}" key="local.registration.fail.unique.message" var="registrationFailUnique" />



</head>
<body>

<div id="wrap">

	<header>
        <jsp:include page="/WEB-INF/jsp/_header.jsp" />
    </header>


    <main>
        <br />

        <jsp:include page="/WEB-INF/jsp/_menuTop.jsp" />

		<br />

        <c:if test="${param.register == 'error'}">
            <h2 class="login_fail_message">${registrationFailMessage}</h2>
        </c:if>
        <c:if test="${param.error == 'unique'}">
            <h2 class="login_fail_message">${registrationFailUnique}</h2>
        </c:if>

		<div class="registration_container">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="registration" />

				<TABLE class="tbl_cnt">
					<tr>
						<td>
							<label for="login">${loginNameLbl}</label>
						</td>
						<td>
							<input type="text" id="login" name="login" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="password">${passwordName}</label>
						</td>
						<td>
							<input type="password" id="psw" name="password" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="username">${firstName}</label>
						</td>
						<td>
							<input type="text" id="username" name="username" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="surname">${lastName}</label>
						</td>
						<td>
							<input type="text" id="surname" name="surname" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="email">${emailName}</label>
						</td>
						<td>
							<input type="email" id="email" name="email" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="phone">${phoneName}</label>
						</td>
						<td>
							<input type="text" id="phone" name="phone" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="address">${addressName}</label>
						</td>
						<td>
							<input type="text" id="address" name="address" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="dateOfBirth">${dateBirth}</label>
						</td>
						<td>
							<input type="date" id="dateOfBirth" name="dateOfBirth" >
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