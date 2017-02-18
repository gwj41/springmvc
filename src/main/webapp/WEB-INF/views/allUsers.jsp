<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show All Users</title>
</head>
<body>
    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>First name: <input type="text" name="firstName" value="${user.firstName}" /></td>>
                <td>Last name: <input type="text" name="lastName" value="${user.lastName}" /></td>
                <td>Birthday: <input type="text" name="birthday" value="${user.birthday}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
