<header style="height: 100px">
    <label style="text-align: right">
        <c:when test="${isAdmin}">
            <a type="button" href="/admin">Admin</a>
        </c:when>
        <a type="button" href="/home">Home</a>
        <a type="button" href="/logout">Logout</a>
    </label>
    <h4 style="text-align: left; margin-top: 0">${username}</h4>
    <hr>
</header>
