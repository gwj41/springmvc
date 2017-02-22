<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <c:url value="/login" var="loginVar"/>

    <form id="loginForm" action="${loginVar}" method="post">
        <div class="form-group">
            <label for="username">User Name</label>
            <input name="username" type="text" id="username"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input name="password" type="password" id="password"/>
        </div>
        <div class="form-group">
            <label for="age">Age</label>
            <input name="age" type="text" id="age"/>
        </div>
        <div class="form-group">
            <label for="springRocks">Remember Me?</label>
            <input type="checkbox" name="springRocks" id="springRocks"/>
        </div>
        <sec:csrfInput/>
        <input type="submit" value="Login">Login</input>
    </form>

</body>
</html>
