<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SPT: Portfolio Edit</title>
    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.min.css">
</head>
<body>
    <%-- Header --%>
    <jsp:include page="../../static/header.jsp" />
    <%-- Main --%>
    <main>
        <div>
            <p><a href="<c:out value="/portfolio/home"/>">Back to portfolio selection</a></p>
            <h3>Update portfolio information</h3>
            <form:form method="post" modelAttribute="portfolio">
                <p>
                    <form:hidden path="id"/>
                    <label>Portfolio name: </label>
                    <form:input path="name"/><form:errors path="name"/><br/>
                    <label>Portfolio description: </label>
                    <form:textarea rows="4" path="description"/><form:errors path="description"/><br/>
                </p>
                <input type="submit" value="Edit portfolio">
            </form:form>
        </div>
    </main>

    <%-- Footer --%>
    <jsp:include page="../../static/footer.jsp" />
</body>
</html>
