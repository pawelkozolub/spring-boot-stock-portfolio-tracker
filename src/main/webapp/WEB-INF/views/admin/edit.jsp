<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SPT: User Edit</title>
    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.min.css">
</head>
<body>
    <%-- Header --%>
    <jsp:include page="../../static/header.jsp" />
    <%-- Main --%>
    <main>
        <div>
            <h3>User information</h3>
            <c:choose>
                <c:when test="${user != null}">
                    <table>
                        <thead>
                        <tr>
                            <th>User ID</th>
                            <th>User name</th>
                            <th>User role</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.username}"/></td>
                            <td><c:out value="${user.authorities}"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <br/>
                    <br/>
                    <h3>Edit User information</h3>
                    <form:form method="post" modelAttribute="user">
                        <p>
                            <form:hidden path="id"/>
                            <label>Name: </label>
                            <form:input path="username"/><form:errors path="username"/><br/>
                            <label>Password: </label>
                            <form:password path="password"/><form:errors path="password"/><br/>
                            <label>Role: </label>
                            <form:select path="authorities" size="2">
                                <form:options items="${roleList}" itemLabel="authority" />
                            </form:select>
                        </p>
                        <p>Please confirm or <a href="<c:out value="/admin"/>">Cancel</a><p>
                        <input type="submit" value="Edit User">
                    </form:form>
                </c:when>
                <c:otherwise>
                    <p>
                        Selected User is unavailable.
                    </p>
                </c:otherwise>
            </c:choose>
        </div>
        <p><a href="<c:out value="/admin"/>">Back</a><p>
    </main>
    <%-- Footer --%>
    <jsp:include page="../../static/footer.jsp" />
</body>
</html>
