<%-- 
    Document   : admin
    Created on : May 16, 2014, 2:35:23 PM
    Author     : bc
--%>
<%@page import="java.sql.Date"%>
<%@page import="DAL.Trip"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="DAL.DAO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:if test="${sessionScope.ADMIN==null}">
        <c:redirect url="errorPage.jsp"/>
    </c:if>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/back-end/js/confirms/confirms.js"></script>
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/back-end/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/back-end/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/back-end/font-awesome/css/font-awesome.min.css">
        <script src="${pageContext.request.contextPath}/back-end/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/tablesorter/jquery.tablesorter.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/tablesorter/tables.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <%@include file="HTML/AdminNavigation.html" %>

            <div class="col-lg-6">
                <c:if test="${requestScope.tripList ne null}">
                    <h2>View Trips</h2>
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                            <tr>
                                <th class="header">ID <i class="fa fa-sort"></i></th>
                                <th class="header">Start at <i class="fa fa-sort"></i></th>
                                <th class="header">Arrival at <i class="fa fa-sort"></i></th>
                                <th class="header">Price <i class="fa fa-sort"></i></th>
                                <th class="header">Total Seats <i class="fa fa-sort"></i></th>
                                <th class="header">Available Seats <i class="fa fa-sort"></i></th>
                                <th class="header">Route ID <i class="fa fa-sort"></i></th>
                                <th class="header">From <i class="fa fa-sort"></i></th>
                                <th class="header">To <i class="fa fa-sort"></i></th>
                                <th class="header">Update <i class="fa fa-sort"></i></th>
                                <th class="header">Delete <i class="fa fa-sort"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="trip" items="${requestScope.tripList}">
                                <tr>
                                    <td><c:out value="${trip.id}"/></td>
                                    <td><c:out value="${trip.depTime}"/></td>
                                    <td><c:out value="${trip.terTime}"/></td>
                                    <td><c:out value="${trip.price}"/></td>
                                    <td><c:out value="${trip.totalSeat}"/></td>
                                    <td><c:out value="${trip.availableSeat}"/></td>
                                    <td><c:out value="${trip.routeId}"/></td>
                                    <td><c:out value="${trip.departure}"/></td>
                                    <td><c:out value="${trip.terminate}"/></td>
                                    <td>
                                        <a href="ActionServlet?btAction=Update Trip&action=update&id=${trip.id}" class="btn btn-success">
                                            Update
                                        </a>
                                    </td>
                                    <td>
                                        <a href="ActionServlet?btAction=Delete Trip&action=delete&id=${trip.id}" class="btn btn-danger">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--<a href="addTrip.jsp" class="btn btn-primary">Add trip</a>-->
                </c:if>
            </div>
        </div>
    </body>
</html>