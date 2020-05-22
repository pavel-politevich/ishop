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
			<fmt:message bundle="${loc}" key="local.catalog.welcome.message" var="chooseMessage" />




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


				<TABLE BORDER=0 WIDTH=100%>
					<TR>
						<TD WIDTH=20% VALIGN=TOP>

							<jsp:include page="/WEB-INF/jsp/_sidebar.jsp" />

							<br />
						</TD>

						<TD WIDTH=60% VALIGN=TOP>

                            <h2>${chooseMessage}</h2>
                            <c:forEach items="${applicationScope.categoryList}" var="element">
                                <p><a href="Controller?command=get_items&category=${element.categoryId}">${element.name}</a> (${element.description})</p>
                            </c:forEach>


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