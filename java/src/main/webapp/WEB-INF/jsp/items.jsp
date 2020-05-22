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
			<fmt:message bundle="${loc}" key="local.catalog.label.price" var="priceLabel" />
			<fmt:message bundle="${loc}" key="local.catalog.label.currency" var="priceCurrency" />
			<fmt:message bundle="${loc}" key="local.item.cart.addToCartBtn" var="addToCartBtn" />



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

                            <h2>${requestScope.itemList[0].categoryName}</h2>

                            <c:forEach items="${requestScope.itemList}" var="element">
                                <div class="item_block">

                                    <table style="width:100%;">
                                        <tr>
                                            <td style="width:75%"><b><a href="Controller?command=show_item&id=${element.itemId}">${element.nameFull}</a></b></td>
                                            <td style="width:25%">
                                                ${priceLabel} ${element.price} ${priceCurrency}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>${element.manufacturer}</td>
                                            <td>
                                                <div class="rating-mini">
                                                    <span <c:if test="${element.rating >= 1}">class="active"</c:if>></span>
                                                    <span <c:if test="${element.rating >= 2}">class="active"</c:if>></span>
                                                    <span <c:if test="${element.rating >= 3}">class="active"</c:if>></span>
                                                    <span <c:if test="${element.rating >= 4}">class="active"</c:if>></span>
                                                    <span <c:if test="${element.rating >= 5}">class="active"</c:if>></span>
                                                </div>
                                            </td>
                                        </rt>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <br />
                                                <form action="Controller" method="post">
                                                    <input type="hidden" name="command" value="add_to_cart" />
                                                    <input type="hidden" name="itemId" value="${element.itemId}" />
                                                    <input type="number" size="2" name="count" min="1" max="${element.count}" value="1">
                                                    <input type="submit" value="${addToCartBtn}" />
                                                </form>

                                            </td>

                                        </tr>

                                    </table>

                                </div>
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