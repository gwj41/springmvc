<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <script type="text/javascript" language="JavaScript" src="<spring:url value="/resources/jslib/jquery-3.1.1.js"/>"></script>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
    <%--<script type="text/javascript" language="JavaScript" src="<spring:url value="/resources/jslib/jquery-3.1.1.js"/>"></script>--%>
    <%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#viewUsers").click(function (e) {
                e.preventDefault();
                $.getJSON("<spring:url value="/mvc/robbie/getAllUsers"/>",function (data) {
                    $("#searchResults").text(JSON.stringify(data));
                });
                return false;
            });
            $("#viewSingleUser").click(function (e) {
                e.preventDefault();
                $.getJSON("<spring:url value="/mvc/robbie/findByLastName/Gu"/>",function (data) {
                    $("#searchResults").text(JSON.stringify(data));
                });
                return false;
            });
            $("#findBy").click(function (e) {
                e.preventDefault();
                $.get("<spring:url value="/mvc/robbie/findByDealershipName/Dong Chang"/>",function (data) {
                    $("#searchResults").text(JSON.stringify(data));
                },"json");
                return false;
            });
            $("#viewSingleUserXML").click(function (e) {
                e.preventDefault();
                $.get("<spring:url value="/mvc/robbie/findOne/1"/>",function (data) {
                    $("#searchResults").text(JSON.stringify(data));
                },"xml");
                return false;
            });
            $("#updateAge").click(function (e) {
                e.preventDefault();
                var url = "<spring:url value="/mvc/robbie/users/lastName/"/>" + $("#lastName").val() + "/age/" + $("#age").val();
                $.get(encodeURI(url),function (data) {
                    $("#searchResults").html(data);
                },"text");
                return false;
            });
            $("#deleteSingleUser").click(function (e) {
                e.preventDefault();
                var id = $("#userId").val();
                <spring:url value="/mvc/robbie/deleteSingleUser/" htmlEscape="true" javaScriptEscape="true" var="urltemp"/>;
                var deleteUrl = "${urltemp}";
                var url = deleteUrl + id;
                $.get(url,function (data) {
                    $("#searchResults").text(data);
                });
                return false;
            });
            $("#query").click(function (e) {
                var pageNo = $("#pageNo").val();
                var pageSize = $("#pageSize").val();
//                queryUserAction(e,pageNo,pageSize,'');
                queryUserSort(e,pageNo,pageSize,"age");
            });
            $("#findByAge").click(function (e) {
                var pageNo = $("#pageNo").val();
                var pageSize = $("#pageSize").val();
                queryUserSort(e,pageNo,pageSize,'/' + $("#userAge").val());
            });
            $("#queryIds").click(function (e) {
                var id1 = $("#id1").val();
                var id2 = $("#id2").val();
                var id3 = $("#id3").val();
                var id4 = $("#id4").val();
                var urlSegment = "/" + id1 + "/" + id2 + "/" + id3 + "/" + id4;
                var urlTemp = "<spring:url value="/mvc/robbie/users"/>";
                $.getJSON(urlTemp + urlSegment,function (data) {
                    $("#searchResults").text(JSON.stringify(data));
                });
//                queryUserAction(e,0,10,urlSegment);
            });
        })
        function navigateToFirstPage(e,sortField,pageNo) {
            var pageSize = $("#selectedPageSize").val();
//            queryUserAction(e,pageNo,pageSize,'');
            queryUserSort(e,pageNo,pageSize,sortField);
        }
        function navigateToLastPage(e,sortField,pageNo) {
            var pageSize = $("#selectedPageSize").val();
            var totalPages = $("#totalPages").val();
//            queryUserAction(e,totalPages,pageSize,'');
            queryUserSort(e,pageNo,pageSize,sortField);
        }
        function navigateToPage(e,sortField,pageNo) {
            var pageSize = $("#selectedPageSize").val();
//            queryUserAction(e,pageNo,pageSize,'');
            queryUserSort(e,pageNo,pageSize,sortField);
        }
        function sortId(e,pageNo) {
            e.preventDefault();
            var pageSize = $("#selectedPageSize").val();
            queryUserSort(e,pageNo,pageSize,"id");
        }
        function sortFirstName(e,pageNo) {
            e.preventDefault();
            var pageSize = $("#selectedPageSize").val();
            queryUserSort(e,pageNo,pageSize,"firstName");
        }
        function sortLastName(e,pageNo) {
            e.preventDefault();
            var pageSize = $("#selectedPageSize").val();
            queryUserSort(e,pageNo,pageSize,"lastName");
        }
        function sortBirthday(e,pageNo) {
            e.preventDefault();
            var pageSize = $("#selectedPageSize").val();
            queryUserSort(e,pageNo,pageSize,"birthday");
        }
        function sortAge(e,pageNo) {
            e.preventDefault();
            var pageSize = $("#selectedPageSize").val();
            queryUserSort(e,pageNo,pageSize,"age");
        }
        function sort(event,sortField,pageNo) {
//            alert(sortField);
            event.preventDefault();
            var pageSize = $("#selectedPageSize").val();
            queryUserSort(event,pageNo,pageSize,sortField);
        }
        function queryUserSort(e,pageNo,pageSize,field) {
//            e.preventDefault();
            <spring:url value="/mvc/robbie/users?page=" htmlEscape="true" javaScriptEscape="true" var="urlSegment"/>;
            pageNo = pageNo == '' ? "0" : pageNo;
            pageSize = pageSize == '' ? "6" : pageSize;
            var userPageUrl = "${urlSegment}" + pageNo + "&size=" + pageSize + "&sort=" + field;
//            alert(userPageUrl);
            $.getJSON(encodeURI(userPageUrl),function (data) {
                /*                    var jsonData = $.parseJSON(data);
                 var resultList = jsonData.result;
                 var result = eval("(" + JSON.stringify(data) + ")");*/
                var options = ['1','2','3','4','5','6','7','8','9','10','11','12'];
                var optionsHtml = "";
                var selected = "";
                for (var i = 1; i < 12; i++) {
                    /*                    selected = options[i] == pageSize ? "selected" : "";
                     optionsHtml += "<option" + selected  + "value=" + options[i] + ">" + options[i] + "</option>";*/
                    if(i == pageSize) {
                        optionsHtml += "<option selected value='" + i + "'>" + i + "</option>";
                    } else {
                        optionsHtml += "<option value='" + i + "'>" + i + "</option>";
                    }
                }
                var length = data.items.length;
                var results = data.items;
                var previousPage = data.currentPage - 1;
                var nextPage = data.currentPage + 1;
                var hasNext = data.hasNextPage;
                var hasPrevious = data.hasPrePage;
                if (!hasNext) {
                    nextPage = data.currentPage;
                }
                if (!hasPrevious) {
                    previousPage = data.currentPage;
                }
                var id = 'id';
                var firstName = 'firstName';
                var lastName = 'lastName';
                var birthday = 'birthday';
                var age = 'age';
                var text;
                var head = "<table border='5px'><tr><td><a href='javascript:void(0);' onclick=\"sort(event,'id'," + data.currentPage + ")\">ID</a></td>" +
                    "<td><a href='javascript:void(0);' onclick=\"sort(event,'firstName'," + data.currentPage + ")\">First Name</a></td>" +
                    "<td><a href='javascript:void(0);' onclick=\"sort(event,'lastName'," + data.currentPage + ")\">Last Name</a></td>" +
                    "<td><a href='javascript:void(0);' onclick=\"sort(event,'birthday'," + data.currentPage + ")\">Birthday</a></td>" +
                    "<td><a href='javascript:void(0);' onclick=\"sort(event,'age'," + data.currentPage + ")\">Age</a></td></tr>";
/*                var head = "<table border='5px'><tr><td><a href='/robbie/users' onclick='sortId(event," + data.currentPage + ")'>ID</a></td>" +
                    "<td><a href='/robbie/users' onclick='sortFirstName(event," + data.currentPage + ")'>First Name</a></td>" +
                    "<td><a href='/robbie/users' onclick='sortLastName(event," + data.currentPage + ")'>Last Name</a></td>" +
                    "<td><a href='/robbie/users' onclick='sortBirthday(event," + data.currentPage + ")'>Birthday</a></td>" +
                    "<td><a href='/robbie/users' onclick='sortAge(event," + data.currentPage + ")'>Age</a></td></tr>";*/
                var tail = "<tr>"+
                    "<td><input id='first' type='button' onclick=\"navigateToFirstPage(event,'" + data.sort + "','0')\" value='First Page'></td>"+
                    "<td><input id='previous' type='button' onclick=\"navigateToPage(event,'" + data.sort + "','" + previousPage + "')\" value='Previous Page'></td>"+
                    "<td><select id='selectedPageSize'>" + optionsHtml + "</select></td>"+
                    "<td><input id='next' type='button' onclick=\"navigateToPage(event,'" + data.sort + "','" + nextPage + "')\" value='Next Page'></td>" +
                    "<td><input id='last' type='button' onclick=\"navigateToLastPage(event,'" + data.sort + "','" + data.totalPages + "')\" value='Last Page'></td>"+
                    "</tr>" + "</table>";
                var body = "";
                for (var i = 0;i < length;i++) {
                    body += "<tr><td>" + results[i].id + "</td>" +
                        "<td>" + results[i].firstName + "</td>" +
                        "<td>" + results[i].lastName + "</td>" +
                        "<td>" + results[i].birthday + "</td>" +
                        "<td>" + results[i].age + "</td></tr>";
                }

                text = head + body + tail + "<input id='totalPages' hidden value=" + data.totalPages + "/>" ;
                $("#searchResults").html(text);
            })
        }
        function queryUserAction(e,pageNo,pageSize,urlSeg) {
//            e.preventDefault();
            <spring:url value="/robbie/users/" htmlEscape="true" javaScriptEscape="true" var="urlSegment"/>;
            pageNo = pageNo == '' ? "0" : pageNo;
            pageSize = pageSize == '' ? "6" : pageSize;
            var userPageUrl = "${urlSegment}" + pageNo + "/" + pageSize + urlSeg;
//            alert(userPageUrl);
            $.getJSON(userPageUrl,function (data) {
                /*                    var jsonData = $.parseJSON(data);
                 var resultList = jsonData.result;
                 var result = eval("(" + JSON.stringify(data) + ")");*/
                var options = ['1','2','3','4','5','6','7','8','9','10','11','12'];
                var optionsHtml = "";
                var selected = "";
                for (var i = 1; i < 12; i++) {
/*                    selected = options[i] == pageSize ? "selected" : "";
                    optionsHtml += "<option" + selected  + "value=" + options[i] + ">" + options[i] + "</option>";*/
                    if(i == pageSize) {
                        optionsHtml += "<option selected value='" + i + "'>" + i + "</option>";
                    } else {
                        optionsHtml += "<option value='" + i + "'>" + i + "</option>";
                    }
                }
                var length = data.items.length;
                var results = data.items;
                var previousPage = data.currentPage - 1;
                var nextPage = data.currentPage + 1;
                var hasNext = data.hasNextPage;
                var hasPrevious = data.hasPrePage;
                if (!hasNext) {
                    nextPage = data.currentPage;
                }
                if (!hasPrevious) {
                    previousPage = data.currentPage;
                }
                var text;
                var head = "<table border='5px'><tr><td>ID</td><td>First Name</td><td>Last Name</td><td>Birthday</td><td>Age</td></tr>";
                var tail = "<tr>"+
                    "<td><input id='first' type='button' onclick='navigateToFirstPage(event,0)' value='First Page'></td>"+
                    "<td><input id='previous' type='button' onclick='navigateToPage(event," + previousPage + ")' value='Previous Page'></td>"+
                    "<td><select id='selectedPageSize'>" + optionsHtml + "</select></td>"+
                    "<td><input id='next' type='button' onclick='navigateToPage(event," + nextPage + ")' value='Next Page'></td>" +
                    "<td><input id='last' type='button' onclick='navigateToLastPage(event," + data.totalPages + ")' value='Last Page'></td>"+
                    "</tr>" + "</table>";
                var body = "";
                for (var i = 0;i < length;i++) {
                    body += "<tr><td>" + results[i].id + "</td>" +
                        "<td>" + results[i].firstName + "</td>" +
                        "<td>" + results[i].lastName + "</td>" +
                        "<td>" + results[i].birthday + "</td>" +
                        "<td>" + results[i].age + "</td></tr>";
                }
                text = head + body + tail + "<input id='totalPages' hidden value=" + data.totalPages + "/>" + "<input id='totalPages' hidden value=" + data.totalPages + "/>";
                $("#searchResults").html(text);
            })
        }
    </script>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    ${currentDate}
    <sec:authorize access="isAuthenticated()" var="authenticated"/>
    <c:choose>
        <c:when test="${not authenticated}">
            <spring:url var="formUrl" value="/mvc/robbie/review" />
            <form:form action="${formUrl}" method="post" modelAttribute="user">
                <label for="username" rel="User Name" >User Name</label>
                <form:input id="username" type="text" path="username" /><br/>
                <label for="password" rel="Password" >Password</label>
                <form:input id="password" type="password" path="password" /><br/>
                <label for="firstName" rel="First Name" >First Name</label>
                <form:input id="firstName" type="text" path="firstName" /><br/>
                <label for="lastName" rel="Last name">Last Name</label>
                <form:input id="lastName" type="text" path="lastName" />
                <form:errors path="lastName"/><br/>
                <label for="birthday" rel="Birthday">Birthday</label>
                <form:input id="birthday" type="text" path="birthday" />
                <form:errors path="birthday"/><br/>
                <label for="age" rel="Age">Age</label>
                <form:input id="age" type="text" path="age" /><br/>
                <label for="role" rel="Role">Role</label>
                <form:select id="role" path="role">
                    <form:options items="${roles}"></form:options>
                </form:select><br/>
                <input type="submit" value="Submit" />
            </form:form>
        </c:when>
        <c:otherwise>
            <div>
            <a id="dealership" href="<spring:url value="/mvc/dealer/dealerships"/>">View all dealerships</a>
            </div>
        </c:otherwise>
    </c:choose>

    <div>上传文件</div>
    <spring:url value="/robbie/upload" var="uploadUrl"/>
    <form:form action="${uploadUrl}" method="post" enctype="multipart/form-data">
        Select file <input name="file" type="file"/><br/>
        <input type="submit" value="Upload">
    </form:form>
    <a id="viewUsers" href="<spring:url value="/mvc/robbie/getAllUsers"/>">View all users(json)</a><br/>
    <a id="viewSingleUser" href="<spring:url value="/mvc/robbie/findByLastName"/>">View single user(json)</a><br/>
    <a id="findBy" href="<spring:url value="/mvc/robbie/findByAddressCity"/>">FindBy</a><br/>
    <a id="viewSingleUserXML" href="<spring:url value="/mvc/robbie/findOne"/>">View single user(xml)</a><br/>
    <input id="userId" name="userId" type="text"/><input id="deleteSingleUser" type="button" value="Delete Single User"/><br/>
    <input id="id1" type="text" width="3" /><br/>
    <input id="id2" type="text" width="3" /><br/>
    <input id="id3" type="text" width="3" /><br/>
    <input id="id4" type="text" width="3" /><br/>
    <input id="queryIds" type="button" value="FindByIDs"/><br/>
    <input id="pageNo" type="text" maxlength="3" width="4" /><input id="pageSize" type="text" maxlength="3" width="4"/><br/>
    <input id="query" type="button" value="Query All Users"/><br/>
    <div>Find By Age and paging</div>
    <input id="userAge" type="text" width="3px" maxlength="3"/>
    <input id="findByAge" type="button" value="Query Users By Age"/><br/>
    <a id="updateAge" href="<spring:url value="/mvc/robbie/users/lastName/Gu/age/101"/>">Update Age by last name</a><br/>
    <span id="searchResults"></span>
</body>
</html>
