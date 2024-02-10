<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SPT: User Admin Panel</title>
    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.min.css">
</head>
<body>
    <%-- Header --%>
    <jsp:include page="../../static/header.jsp" />
    <%-- Main --%>
    <main>
        <div>
            <h3>User list</h3>
            <c:choose>
                <c:when test="${userList.size() > 0}">
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="user" varStatus="ind">
                            <tr>
                                <td><c:out value="${ind.index+1}"/></td>
                                <td><c:out value="${user.username}"/></td>
                                <td><c:out value="${user.authorities}"/></td>
                                <td>
                                    <a href="<c:out value="/admin/${user.id}"/>">View</a>
                                    <a href="<c:out value="/admin/edit?id=${user.id}"/>">Edit</a>
                                    <a href="<c:out value="/admin/delete?id=${user.id}"/>">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>
                        No Users in database.
                    </p>
                </c:otherwise>
            </c:choose>

            <%--    <hr/>--%>
            <br/>
            <br/>
            <h3>Add User</h3>
            <form:form method="post" modelAttribute="user">
                <p>
                    <label>Name: </label>
                    <form:input path="username"/><form:errors path="username"/><br/>
                    <label>Password: </label>
                    <form:password path="password"/><form:errors path="password"/><br/>
                    <label>Role: </label>
                    <form:select path="authorities" size="2">
                        <form:options items="${roleList}" itemLabel="authority" />
                    </form:select>
                </p>
                <input type="submit" value="Add User">
            </form:form>
        </div>

    </main>
    <%-- Footer --%>
    <jsp:include page="../../static/footer.jsp" />
</body>
</html>
