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
			<fmt:message bundle="${loc}" key="local.item.cart.addToCartBtn" var="addToCartBtn" />

			<fmt:message bundle="${loc}" key="local.review.addReview.caption" var="addReviewCaption" />
			<fmt:message bundle="${loc}" key="local.review.addButton.text" var="addReviewBtn" />
			<fmt:message bundle="${loc}" key="local.review.error.text" var="reviewErrorText" />
			<fmt:message bundle="${loc}" key="local.review.rating.text" var="ratingText" />
			<fmt:message bundle="${loc}" key="local.review.lastReviews.caption" var="lastReviewsCaption" />



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


                            <div>

                                <table>
                                    <tr>
                                        <td WIDTH=20% VALIGN=TOP>
                                            <div class="item_img_big"></div>
                                        </td>

                                        <td WIDTH=60% VALIGN=TOP>
                                            <div class="item_desc">
                                                <h2>${requestScope.item.nameShort}</h2>
                                                <p>${requestScope.item.nameFull}</p>
                                                <p>${requestScope.item.manufacturer}</p>
                                                <p>${requestScope.item.description}</p>
                                                <div>
                                                    ${ratingLabel} ${requestScope.item.rating}

                                                    <div class="rating-mini">
                                                        <span <c:if test="${requestScope.item.rating >= 1}">class="active"</c:if>></span>
                                                        <span <c:if test="${requestScope.item.rating >= 2}">class="active"</c:if>></span>
                                                        <span <c:if test="${requestScope.item.rating >= 3}">class="active"</c:if>></span>
                                                        <span <c:if test="${requestScope.item.rating >= 4}">class="active"</c:if>></span>
                                                        <span <c:if test="${requestScope.item.rating >= 5}">class="active"</c:if>></span>
                                                    </div>
                                                </div>

                                            </div>
                                        </td>

                                        <td WIDTH=20% VALIGN=TOP>

                                            <p>${priceLabel}</p>
                                            <div class="item_desc_price">
                                                ${requestScope.item.price} ${priceCurrency}
                                            </div>

                                            <br />


                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="add_to_cart" />
                                                <input type="hidden" name="itemId" value="${requestScope.item.itemId}" />
                                                <input type="number" size="3" name="count" min="1" max="${requestScope.item.count}" value="1">
                                                <input type="submit" value="${addToCartBtn}" />
                                                <br />
                                            </form>


                                        </td>


                                    </rt>

                                </table>

                            </div>

                            <hr>


                            <table width=100% valign=top>
                            <tr>

                                <td width=50% valign=top>

                                    <p><b>${lastReviewsCaption}</b></p>

                                    <c:forEach items="${requestScope.reviewList}" var="element">

                                        <table class="reviewList">
                                            <tr>
                                                <td>
                                                    <b>${element.userName}</b>
                                                    <br />
                                                    ${element.reviewDate}
                                                </td>
                                                <td>
                                                    <div class="rating-mini">
                                                        <span <c:if test="${element.rate >= 1}">class="active"</c:if>></span>
                                                        <span <c:if test="${element.rate >= 2}">class="active"</c:if>></span>
                                                        <span <c:if test="${element.rate >= 3}">class="active"</c:if>></span>
                                                        <span <c:if test="${element.rate >= 4}">class="active"</c:if>></span>
                                                        <span <c:if test="${element.rate >= 5}">class="active"</c:if>></span>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <p>${element.comment}</p>
                                                </td>
                                            </tr>
                                        </table>

                                    </c:forEach>

                                </td>

                                <td>
                                    <form action="Controller">
                                      <p><b>${addReviewCaption}</b></p>

                                      <div class="rating-area">
                                      	<input type="radio" id="star-5" name="rating" value="5">
                                      	<label for="star-5" title="${ratingText} «5»"></label>
                                      	<input type="radio" id="star-4" name="rating" value="4">
                                      	<label for="star-4" title="${ratingText} «4»"></label>
                                      	<input type="radio" id="star-3" name="rating" value="3">
                                      	<label for="star-3" title="${ratingText} «3»"></label>
                                      	<input type="radio" id="star-2" name="rating" value="2">
                                      	<label for="star-2" title="${ratingText} «2»"></label>
                                      	<input type="radio" id="star-1" name="rating" value="1" required>
                                      	<label for="star-1" title="${ratingText} «1»"></label>
                                      </div>

                                      <input type="hidden" name="command" value="add_review" />
                                      <input type="hidden" name="itemId" value="${requestScope.item.itemId}" />
                                      <p><textarea name="comment" class="comment_text"></textarea></p>
                                      <p><input type="submit" value="${addReviewBtn}"></p>
                                    </form>

                                    <c:if test="${param.add_review == 'false'}">
                                        <p style="color: red">${reviewErrorText}</p>
                                    </c:if>

                                </td>

                            </tr>
                            </table>



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