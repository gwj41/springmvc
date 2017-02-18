<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <c:url value="/login" var="loginVar"/>
    <form id="loginForm" action="${loginVar}" method="post">
        <p>
            <label for="username">User Name</label>
            <input name="username" type="text" id="username" class="form-control"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input name="password" type="password" id="password" class="form-control" />
        </p>
        <p>
            <label for="age">Age</label>
            <input name="age" type="text" id="age" class="form-control" />
        </p>
        <sec:csrfInput/>
        <button type="submit">Login</button>
    </form>

</body>
</html>
