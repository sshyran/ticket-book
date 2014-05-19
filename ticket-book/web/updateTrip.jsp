<%-- 
    Document   : updateTrip
    Created on : May 18, 2014, 3:04:03 PM
    Author     : bc
--%>
<%@page import="DAL.DAO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <c:if test="${sessionScope.ADMIN==null}">
        <c:redirect url="errorPage.jsp"/>
    </c:if>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/back-end/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/back-end/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/back-end/font-awesome/css/font-awesome.min.css">
        <title>Update Trip</title>
    </head>
    <body>
        <div id="wrapper">
            <%@include file="HTML/AdminNavigation.html" %>
            <div class="col-lg-6">
                <h2>Update Trip</h2>
                <!--<form name="updateTrip" method="POST" action="tripServlet" accept-charset="ISO-8859-1">-->
                <form name="updateTrip" method="POST" action="tripServlet">
                    <input type="hidden" name="action" value="confirmUpdate"/>
                    <table>
                        <tbody>
                            <tr>
                                <td align="right">ID: </td>
                                <td>
                                    <input type="text" value="${requestScope.updateTrip.id}" name="id" class="input-block-level" readonly>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Start at: </td>
                                <td>
                                    <input type="text" value="${requestScope.updateTrip.depTime}" name="dTime" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Arrival at: </td>
                                <td>
                                    <input type="text" value="${requestScope.updateTrip.terTime}" name="tTime" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Price: </td>
                                <td>
                                    <input type="text" value="${requestScope.updateTrip.price}" name="price" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Total Seats: </td>
                                <td>
                                    <input type="text" value="${requestScope.updateTrip.totalSeat}" name="tSeat" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Available Seats: </td>
                                <td>
                                    <input type="text" value="${requestScope.updateTrip.availableSeat}" name="aSeat" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Route ID | Route Name: </td>
                                <td>
                                    <c:set var="current" value="${requestScope.updateTrip.routeId}"/>
                                    <%
                                                String conStr2 = new DAO().getConnectionString();
                                                String driver2 = new DAO().getDriverString();
                                    %>
                                    <sql:setDataSource var="sqlDS" url="<%=conStr2%>" driver="<%=driver2%>" />
                                    <sql:query dataSource="${sqlDS}" var="routeNameList">
                                        select name from [Route] where id = ${current}
                                    </sql:query>
                                    <c:forEach var="row" items="${routeNameList.rowsByIndex}">
                                        <input type="text" value="${current} | ${row[0]}" name="id" class="input-block-level" disabled>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"></td>
                                <td>
                                    <input type="submit" value="Update" class="btn-primary btn">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
