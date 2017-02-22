<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Detail</title>
    <script type="text/javascript" language="JavaScript" src="<spring:url value="/resources/jslib/jquery-3.1.1.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#deleteDealer").click(function(e){
                e.preventDefault();
                $("#deleteForm").submit();
            });
        });
    </script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <c:if test="${not empty user}">
        First Name is ${user.firstName}<br/>
        Last Name is ${user.lastName}<br/>
        Birthday is ${user.birthday}<br/>
    </c:if>

    <sec:authorize access="isAuthenticated()" var="authenticated"/>
    <c:choose>
        <c:when test="${not authenticated}">
            <a href="<spring:url value="/mvc/robbie/save"/>">Confirm</a>
        </c:when>
        <c:otherwise>
            <table class="table-responsive">
                <tr><td>ID</td><td>${dealership.id}</td></tr>
                <tr><td>Name</td><td>${dealership.name}</td></tr>
                <tr><td>Brand</td><td>${dealership.brand}</td></tr>
                <tr><td>Phone Number</td><td>${dealership.phoneNumber}</td></tr>
                <tr><td>City</td><td>${dealership.address.city}</td></tr>
                <tr><td>State</td><td>${dealership.address.state}</td></tr>
                <tr><td>Postal Code</td><td>${dealership.address.zip}</td></tr>
            </table>
            <%--<sec:authorize access="hasAuthority('ROLE_ADMIN')">--%>
            <sec:authorize access="${isAdmin}">
                <div>
                    <a href="#" id="deleteDealer">Delete Dealership</a>
                    <spring:url value="/mvc/dealer/dealership/${dealership.id}" var="daleteUrl"/>
                    <form:form id="deleteForm" action="${daleteUrl}" method="post">
                        <sec:csrfInput/>
                    </form:form>
                </div>
            </sec:authorize>
        </c:otherwise>
    </c:choose>


</body>
</html>
