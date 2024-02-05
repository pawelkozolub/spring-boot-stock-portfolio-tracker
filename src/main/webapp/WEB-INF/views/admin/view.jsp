<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SPT:Home</title>
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
                            <th>#</th>
                            <th>User name</th>
                            <th>User roles</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><c:out value="${user.id}"/></td>
                                <td><c:out value="${user.username}"/></td>
                                <td><c:out value="${user.authorities}"/></td>
                                <td>
                                    <a href="<c:out value="/admin/${user.id}"/>">View</a>
                                    <a href="<c:out value="/admin/edit?id=${user.id}"/>">Edit</a>
                                    <a href="<c:out value="/admin/delete?id=${user.id}"/>">Delete</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
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
