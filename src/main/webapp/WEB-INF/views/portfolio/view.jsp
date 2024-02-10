<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SPT: Portfolio Delete</title>
    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.min.css">
</head>
<body>
    <%-- Header --%>
    <jsp:include page="../../static/header.jsp" />
    <%-- Main --%>
    <main>
        <div>
            <p><a href="<c:out value="/portfolio"/>">Back to portfolio selection</a></p>
            <h3>Portfolio: <mark>${portfolio.name}</mark></h3>
            <h4>Summary</h4>
            <c:choose>
                <c:when test="${balanceList.size() > 0}">
                    <table>
                        <tbody>
                        <tr>
                            <th>Invested</th>
                            <td><c:out value="${summary.invested}"/></td>
                        </tr>
                        <tr>
                            <th>Withdrawn</th>
                            <td><c:out value="${summary.withdrawn}"/></td>
                        </tr>
                        <tr>
                            <th>Realized profit</th>
                            <td><c:out value="${summary.realizedProfit}"/></td>
                        </tr>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>
                        No portfolio summary available.
                    </p>
                </c:otherwise>
            </c:choose>

            <h4>Stock balance</h4>
            <c:choose>
                <c:when test="${balanceList.size() > 0}">
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Stock</th>
                            <th>Quantity</th>
                            <th>Avg. buy price</th>
                            <th>Invested</th>
                            <th>Withdrawn</th>
                            <th>Realized profit</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${balanceList}" var="item" varStatus="ind">
                            <tr>
                                <td><c:out value="${ind.index+1}"/></td>
                                <td><c:out value="${item.stock}"/></td>
                                <td><c:out value="${item.quantity}"/></td>
                                <td><c:out value="${item.averagePrice}"/></td>
                                <td><c:out value="${item.invested}"/></td>
                                <td><c:out value="${item.withdrawn}"/></td>
                                <td><c:out value="${item.realizedProfit}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>
                        No stock balance of the portfolio available.
                    </p>
                </c:otherwise>
            </c:choose>
            <h4>Transaction history</h4>
            <a class="button" href="/portfolio/${portfolio.id}/buy">Buy stock</a>
            <a class="button" href="/portfolio/${portfolio.id}/sell">Sell stock</a>
            <c:choose>
                <c:when test="${portfolio.transactions.size() > 0}">
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Date</th>
                            <th>Type</th>
                            <th>Stock</th>
                            <th>Quantity</th>
                            <th>Price/share</th>
                            <th>Total value</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${portfolio.transactions}" var="transaction" varStatus="ind">
                            <tr>
                                <td><c:out value="${ind.index+1}"/></td>
                                <td><c:out value="${transaction.date}"/></td>
                                <td><c:out value="${transaction.type}"/></td>
                                <td><c:out value="${transaction.stock}"/></td>
                                <td><c:out value="${transaction.quantity}"/></td>
                                <td><c:out value="${transaction.price}"/></td>
                                <td><c:out value="${transaction.price * transaction.quantity}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>
                        No transactions in the portfolio available. Please buy a selected stock.
                    </p>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <%-- Footer --%>
    <jsp:include page="../../static/footer.jsp" />
</body>
</html>
