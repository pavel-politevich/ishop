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
			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"	var="ru_button" />
			<fmt:message bundle="${loc}" key="local.locbutton.name.en"	var="en_button" />

			<fmt:message bundle="${loc}" key="local.header.name" var="headerName" />
			<fmt:message bundle="${loc}" key="local.menu.line.login" var="loginName" />
			<fmt:message bundle="${loc}" key="local.menu.line.logout" var="logoutName" />
			<fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />
			<fmt:message bundle="${loc}" key="local.menu.line.main" var="MainName" />
			<fmt:message bundle="${loc}" key="local.menu.line.catalog" var="CatalogName" />
			<fmt:message bundle="${loc}" key="local.menu.line.news" var="NewsName" />
			<fmt:message bundle="${loc}" key="local.menu.line.delivery" var="DeliveryName" />
			<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />
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


				<TABLE BORDER=0 WIDTH=100%>
					<TR>
						<TD WIDTH=20% VALIGN=TOP>
							<div class="menu_left">


							</div>
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

							<!--	iframe		-->

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