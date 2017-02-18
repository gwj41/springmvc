document.write("<script language='javascript' src='../jslib/jquery-3.1.1.js'></script>");
    var script=document.createElement("script");
    script.type="text/javascript";
    script.src="../jslib/jquery-3.1.1.js";
    document.getElementsByTagName('head')[0].appendChild(script);
    setTimeout(function() {
    $("#viewUsers").click(function (e) {
        e.preventDefault();
        $.getJSON("<spring:url value=" / robbie / getAllUsers
        "/>", function (data) {
//                    alert(JSON.stringify(data));
            $("#searchResults").text(JSON.stringify(data));
        }
        )
        ;
        return false;
    });
    $("#viewSingleUser").click(function (e) {
        e.preventDefault();
        $.getJSON("<spring:url value="/robbie/findByLastName/Gu"/>", function (data) {
            $("#searchResults").text(JSON.stringify(data));
        }
        )
        ;
        return false;
    });
    $("#findBy").click(function (e) {
        e.preventDefault();
        $.get("<spring:url value="/robbie/findByDealershipName/Dong Chang"/>", function (data) {
            $("#searchResults").text(JSON.stringify(data));
        }, "json"
        )
        ;
        return false;
    });
    $("#viewSingleUserXML").click(function (e) {
        e.preventDefault();
        $.get("<spring:url value="/robbie/findOne/1"/>", function (data) {
            $("#searchResults").text(JSON.stringify(data));
        }, "xml"
        )
        ;
        return false;
    });
    $("#deleteSingleUser").click(function (e) {
        e.preventDefault();
        var id = $("#userId").val();
        <spring:url value="/robbie/deleteSingleUser/" htmlEscape="true" javaScriptEscape="true" var="urltemp"/>;
        var deleteUrl = "${urltemp}";
        var url = deleteUrl + id;
        $.get(url, function (data) {
            $("#searchResults").text(data);
        });
        return false;
    });
}