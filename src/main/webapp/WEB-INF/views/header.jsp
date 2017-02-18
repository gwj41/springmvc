<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<spring:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css"/>
<link rel="stylesheet" href="<spring:url value="/resources/bootstrap/css/bootstrap-theme.css"/>" type="text/css"/>
<script type="text/javascript" src="<spring:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
<script type="text/javascript" language="JavaScript" src="<spring:url value="/resources/jslib/jquery-3.1.1.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#logout").click(function(e){
            e.preventDefault();
            $("#logout-form").submit();
        });
    });
</script>
	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<spring:url value="/"/>" class="navbar-brand">Kevin's Auto Service Center</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="<spring:url value="/mvc/robbie/home/"/>">Home</a></li>
				<li><a href="<spring:url value="/mvc/robbie/services/"/>">Services</a></li>
				<li><a href="<spring:url value="/mvc/robbie/appointment/"/>">Appointment</a></li>
				<sec:authorize access="authenticated" var="authenticated"/>
				<c:choose>
					<c:when test="${authenticated}">
						<li>
							<p class="navbar-text">
								Welcome
								<sec:authentication property="name"/>
								<a id="logout" href="#">Logout</a>
							</p>
							<form id="logout-form" action="<c:url value="/logout"/>" method="post">
								<sec:csrfInput/>
							</form>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="<spring:url value="/mvc/robbie/login/"/>">Sign In</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>