<%-- 
    Document   : routeAdmin
    Created on : May 17, 2014, 11:11:06 AM
    Author     : Dell
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
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/back-end/js/confirms/confirms.js"></script>
        <title>Manage Page</title>
        <link href="${pageContext.request.contextPath}/back-end/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/back-end/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/back-end/font-awesome/css/font-awesome.min.css">
        <script src="${pageContext.request.contextPath}/back-end/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/tablesorter/jquery.tablesorter.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/tablesorter/tables.js"></script>
    </head>

    <body>
        <h2 style="text-align: center">ROUTE</h2>
        <hr>
        <h3 style="text-align: center; color: red;">${errors}</h3>
        <div id="wrapper">
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div>
                    <a class="navbar-brand" href="index.html">SB Admin</a>
                </div>
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i> Manage Trip <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Add trip</a></li>
                                <li><a href="#">Another Item</a></li>
                                <li><a href="#">Third Item</a></li>
                                <li><a href="#">Last Item</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i> Manage Route <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="routeAdmin.jsp">Route</a></li>
                                <li><a href="stationAdmin.jsp">Station</a></li>

                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right navbar-user">
                       
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${sessionScope.ADMIN.fullName} <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i> Inbox <span class="badge">7</span></a></li>
                                <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="LogoutServlet"><i class="fa fa-power-off"></i> Log Out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container">

            </div>
            <div class="col-lg-6">
                <CENTER>
                    <form class="form-horizontal" id="frmRoute" name="frmRoute" method="post" action="RouteCRUD">
                        <div class="control-group">
                            <label class="control-label" for="inputDeparture">Departure</label>
                            <select class="selectpicker" name="txtDeparture">
                                <!--load các route name còn lại-->
                                <%
                                            String conStr2 = new DAO().getConnectionString();
                                            String driver2 = new DAO().getDriverString();
                                %>
                                <sql:setDataSource var="sqlDS" url="<%=conStr2%>" driver="<%=driver2%>" />
                                <sql:query dataSource="${sqlDS}" var="StationSNameList">
                                    select * from [Station]
                                </sql:query>
                                <c:forEach var="row" items="${StationSNameList.rowsByIndex}">
                                    
                                    <option value="${row[0]}">${row[1]}</option>

                                </c:forEach>
                            </select>

                            <div class="control-group">
                                <label class="control-label" for="inputTerminate">Terminate</label>
                                <select class="selectpicker" name="txtTerminate">
                                    <!--load các route name còn lại-->
                                    <sql:setDataSource var="sqlDS" url="<%=conStr2%>" driver="<%=driver2%>" />
                                    <sql:query dataSource="${sqlDS}" var="StationSNameList">
                                        select * from [Station]
                                    </sql:query>
                                    <c:forEach var="row" items="${StationSNameList.rowsByIndex}">
                                         <option value="${row[0]}">${row[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="control-group">
                                <div class="controls">

                                    <button type="submit" class="btn" name="btnInsert" id="btnInsert">Insert</button>
                                </div>
                            </div>
                        </div>
                    </form>

                </CENTER>
                <hr/>
                <CENTER>

                    <form action="RouteCRUD" method="get" class="form-search">
                        <input type="text" class="input-medium search-query"  placeholder="Input To Search" name="txtSearch" value="${search}"/>
                        <input type="submit" class="btn" name="btnSearch"  value="Search"/>
                    </form>
                </CENTER>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>
							ID
                                    </th>
                                    <th>
							Departure
                                    </th>
                                    <th>
							Terminate
                                    </th>
                                    <th>
							Name
                                    </th>
                                    <th>
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <c:forEach var="r" items="${rts.rows}">
                                    <tr class="success">
                                        <td>${r.id}</td>
                                        <td>${r.departure}</td>
                                        <td>${r.terminate}</td>
                                        <td>${r.name}</td>
                                        <td><a href="?lnkDelete&txtId=${r.id}">Delete</a></td>
                                    </tr>

                                </c:forEach>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>


            </div>
        </div>
    </body>
</html>