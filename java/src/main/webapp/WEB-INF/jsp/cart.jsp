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
			<fmt:message bundle="${loc}" key="local.item.label.rating" var="ratingLabel" />

			<fmt:message bundle="${loc}" key="local.item.cart.cartname" var="CartName" />

			<fmt:message bundle="${loc}" key="local.order.confirm.comment.text" var="confirmComment" />
			<fmt:message bundle="${loc}" key="local.order.confirm.address.text" var="confirmAddress" />
			<fmt:message bundle="${loc}" key="local.order.confirm.paymentType.text" var="confirmPaymentType" />
			<fmt:message bundle="${loc}" key="local.order.confirm.submit.button" var="confirmSubmitBtn" />




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

						<TD VALIGN=TOP>

                            <h2>${CartName}</h2>


                            <c:if test="${empty requestScope.order.itemMap}">
                                В корзине пусто!
                            </c:if>

                            <c:forEach items="${requestScope.order.itemMap}" var="entry">


                                <div class = "cart_item">

                                    <table BORDER=0 WIDTH=100%>
                                        <tr>
                                            <td width=55%><a href="Controller?command=show_item&id=${entry.key.itemId}">${entry.key.nameFull}</a></td>
                                            <td width=10%>Цена: ${entry.key.price}</td>
                                            <td width=15%>Количество: ${entry.value}</td>
                                            <td width=10%>Сумма: ${entry.key.price * entry.value}</td>
                                            <td>
                                                <form action="Controller" method="post">
                                                    <input type="hidden" name="command" value="del_from_cart" />
                                                    <input type="hidden" name="itemId" value="${entry.key.itemId}" />
                                                    <input type="submit" value="Удалить" />
                                                </form>
                                            </td>
                                        </tr>
                                    </table>

                                </div>


                            </c:forEach>

                            <c:if test="${not empty requestScope.order.itemMap}">
                                <div class="order_confirm_block">
                                    <br />
                                    <hr>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="confirm_order" />

                                        <table class="confirm_tbl" width=100% valign=top>
                                            <tr>
                                                <td>
                                                    <label for="comment">${confirmComment}</label>
                                                    <br />
                                                    <textarea name="comment" id="comment" class="confirm_comment"></textarea>
                                                </td>
                                                <td>
                                                    <label for="address">${confirmAddress}</label>
                                                    <br />
                                                    <textarea name="address" id="address" class="confirm_comment"></textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="paymentType">${confirmPaymentType}</label>
                                                    <br /><br />

                                                    <c:forEach items="${applicationScope.paymentTypesMap}" var="entry">
                                                        <input type="radio" name="paymentType" value="${entry.value}" checked>${entry.value}<Br>
                                                    </c:forEach>

                                                </td>
                                                <td>

                                                    <c:if test="${param.confirm == 'error'}">
                                                        <p style="color: red">Ошибка добавления заказа!</p>
                                                    </c:if>

                                                    <input type="submit" value="${confirmSubmitBtn}" />
                                                </td>
                                            </tr>
                                        </table>



                                    </form>
                                </div>
                            </c:if>


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