<header style="height: 50px; line-height: 1; padding-bottom: 0">
    <nav style="margin-top: 0; padding-top: 5; padding-bottom: 2">
        <b style="text-align: left">Logged user: <mark>${username}</mark> &emsp;&emsp;&emsp;</b>
        <c:when test="${isAdmin}">
            <a href="/admin">Admin</a>
        </c:when>
        <a type="button" href="/home">Home</a>
        <a type="button" href="/logout">Logout</a>
    </nav>
</header>
