<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SPT: Portfolio Sell Stock</title>
    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.min.css">
</head>
<body>
    <%-- Header --%>
    <jsp:include page="../../static/header.jsp" />
    <%-- Main --%>
    <main>
        <div>
            <p><a href="<c:out value="/portfolio/${portfolio.id}"/>">Back to portfolio</a></p>
            <form:form method="post" modelAttribute="transaction">
                <label>Stock name: </label>
                <form:select path="stock">
                    <c:forEach items="${balanceList}" var="balance">
                        <form:option value="${balance.stock}">${balance.stock}</form:option>
                    </c:forEach>
                </form:select><form:errors path="stock"/><br/>
                <label>Quantity: </label>
                <form:input type="number" value="1" min="1" step="1" path="quantity"/><form:errors path="quantity"/><br/>
                <label>Date: </label>
                <form:input type="date" path="date"/><form:errors path="date"/><br/>
                <label>Price: </label>
                <form:input path="price"/><form:errors path="price"/><br/>
                <input type="submit" value="Sell stock">
            </form:form>
        </div>
    </main>

    <%-- Footer --%>
    <jsp:include page="../../static/footer.jsp" />
</body>
</html>
