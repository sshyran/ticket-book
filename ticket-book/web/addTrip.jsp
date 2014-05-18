<%--
    Document   : admin
    Created on : May 16, 2014, 2:35:23 PM
    Author     : khoatnd
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
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div>
                    <a class="navbar-brand" href="index.html">SB Admin</a>
                </div>
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <!--<li><a href="index.html"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                        <li><a href="charts.html"><i class="fa fa-bar-chart-o"></i> Charts</a></li>
                        <li><a href="tables.html"><i class="fa fa-table"></i> Tables</a></li>
                        <li><a href="forms.html"><i class="fa fa-edit"></i> Forms</a></li>
                        <li><a href="typography.html"><i class="fa fa-font"></i> Typography</a></li>
                        <li><a href="bootstrap-elements.html"><i class="fa fa-desktop"></i> Bootstrap Elements</a></li>
                        <li><a href="bootstrap-grid.html"><i class="fa fa-wrench"></i> Bootstrap Grid</a></li>
                        <li class="active"><a href="blank-page.html"><i class="fa fa-file"></i> Blank Page</a></li>-->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i> Manage Trip <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Add trip</a></li>
                                <li><a href="#">Another Item</a></li>
                                <li><a href="#">Third Item</a></li>
                                <li><a href="#">Last Item</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right navbar-user">
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i> Inbox <span class="badge">7</span></a></li>
                                <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="col-lg-6">
                <h2>Add a trip</h2>
                <form name="addTrip" method="POST" action="tripServlet">
                    <input type="hidden" name="action" value="add"/>
                    <table>
                        <tbody>
                            <tr>
                                <td align="right">Start at: </td>
                                <td>
                                    <input type="text" name="dTime" class="input-block-level" placeholder="yyyy-mm-dd hh:mm:ss">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Arrival at: </td>
                                <td>
                                    <input type="text" name="tTime" class="input-block-level" placeholder="yyyy-mm-dd hh:mm:ss">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Price: </td>
                                <td>
                                    <input type="text" name="price" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Total Seats: </td>
                                <td>
                                    <input type="text" name="tSeat" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Available Seats: </td>
                                <td>
                                    <input type="text" name="aSeat" class="input-block-level">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Route ID: </td>
                                <td>
                                    <select class="selectpicker" name="routeId">
                                        <!--load các route name còn lại-->
                                        <%
                                                    String conStr2 = new DAO().getConnectionString();
                                                    String driver2 = new DAO().getDriverString();
                                        %>
                                        <sql:setDataSource var="sqlDS" url="<%=conStr2%>" driver="<%=driver2%>" />
                                        <sql:query dataSource="${sqlDS}" var="routeIdList">
                                            select id from [Route]
                                        </sql:query>
                                        <c:forEach var="row" items="${routeIdList.rowsByIndex}">
                                            <option>${row[0]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"></td>
                                <td>
                                    <input type="submit" value="Add" class="btn-primary btn">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>