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

			<fmt:message bundle="${loc}" key="local.errorPage.message" var="errorText" />
			<fmt:message bundle="${loc}" key="local.errorPage.message.link" var="errorLink" />


	</head>


	<body>

		<div id="wrap">

			<header>
                <jsp:include page="/WEB-INF/jsp/_header.jsp" />
			</header>

			<main>

				<br />

                <h1>${errorText}</h1>

                <br /><br />

                <a href="Controller?command=go_to_main">${errorLink}</a>

			</main>
		</div>

	<footer>
		${copyright}
	</footer>


	</body>
</html>