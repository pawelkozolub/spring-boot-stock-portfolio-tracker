<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header style="height: 50px; line-height: 1; padding-bottom: 0">
    <nav style="margin-top: 0; padding-top: 5px; padding-bottom: 2px">
        <b style="text-align: left">Logged user: <mark>${username}</mark> &emsp;&emsp;&emsp;</b>
        <c:choose>
            <c:when test="${isAdmin}">
                <a href="/admin">Admin</a>
            </c:when>
        </c:choose>
        <a type="button" href="/home">Home</a>
        <a type="button" href="/logout">Logout</a>
    </nav>
</header>
