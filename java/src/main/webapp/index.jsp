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

			<fmt:message bundle="${loc}" key="local.message" var="message" />
			<fmt:message bundle="${loc}" key="local.registration.success" var="successRegistrationMessage" />
			<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />



	</head>
	<body>
		<div id="wrap">

			<header>
				<jsp:include page="/WEB-INF/jsp/header.jsp" />
			</header>

			<main>

				<br />

                <jsp:include page="/WEB-INF/jsp/menuTop.jsp" />

				<br />


				<TABLE BORDER=0 WIDTH=100%>
					<TR>
						<TD WIDTH=20% VALIGN=TOP>

							<jsp:include page="/WEB-INF/jsp/sidebar.jsp" />

							<br />
						</TD>

						<TD WIDTH=60% VALIGN=TOP>



							<c:if test="${param.register == 'success'}">
								<h2>${successRegistrationMessage}</h2>
							</c:if>

							<c:if test="${param.login == 'success'}">
								<c:if test = "${user.name != null}">
									<h2>${message},  <c:out value = "${user.name}"/>
									</h2>
								</c:if>
							</c:if>



						</TD>
						<TD WIDTH=20% VALIGN=TOP>
							<iframe src="" frameborder=0 height=200px width=200px>
							</iframe>
						</TD>

					</TR>
				</TABLE>
				<br />

			</main>
		</div>



		<footer>
			${copyright}
		</footer>


	</body>
</html>