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

			<fmt:message bundle="${loc}" key="local.header.name" var="headerName" />
			<fmt:message bundle="${loc}" key="local.signin.login.name" var="LabelLoginName" />
			<fmt:message bundle="${loc}" key="local.signin.password.name" var="LabelPasswordName" />
			<fmt:message bundle="${loc}" key="loacl.signin.submitBtn.name" var="BtnSubmitName" />
			<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />
			<fmt:message bundle="${loc}" key="loacl.signin.fail.message" var="loginFailMessage" />
			<fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />



	</head>


	<body>

		<div id="wrap">

			<header>
                <jsp:include page="/WEB-INF/jsp/_header.jsp" />
			</header>

			<main>

				<br />

				<jsp:include page="/WEB-INF/jsp/_menuTop.jsp" />

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