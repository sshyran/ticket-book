<%-- 
    Document   : stationAdmin
    Created on : May 18, 2014, 9:20:40 PM
    Author     : Dell
--%>

<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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
        <h2 style="text-align: center">STATION</h2>
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
                <center>
                    <form class="form-horizontal" role="form" method="post" action="StationCRUD">
                        <div class="form-group">
                            <label for="ID" class="col-sm-2 control-label">ID</label>
                            <div class="col-sm-10">
                                <input type="text" readonly name="txtId" class="form-control" id="ID" placeholder="ID read only" value="${st.id}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="SName" class="col-sm-2 control-label">SName</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtSName" class="form-control" id="SName" placeholder="Input SName" value="${st.sname}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Address" class="col-sm-2 control-label">Address</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtAddress" class="form-control" id="Address" placeholder="Input Address" value="${st.address}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Province" class="col-sm-2 control-label">Province</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtProvince" class="form-control" id="inputPassword3" placeholder="Input Province" value="${st.province}">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn success" name="btnInsert" id="btnInsert">Insert</button>
                                <button type="submit" class="btn warning" name="btnUpdate" id="btnUpdate">Update</button>
                            </div>
                        </div>
                    </form>

                </center>

                <hr/>
                <CENTER>
                    <form action="StationCRUD" method="get" class="form-search">
                        <input type="text" class="input-medium search-query" placeholder="Input To Search" name="txtSearch" value="${search}"/>
                        <button type="submit" class="btn" name="btnSearch">Search</button>
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
							SName
                                    </th>
                                    <th>
							Address
                                    </th>
                                    <th>
							Province
                                    </th>
                                    <th>
                                        &nbsp;
                                    </th>
                                    <th>
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <c:forEach var="s" items="${sts.rows}">
                                    <tr class="success">
                                        <td>${s.id}</td>
                                        <td>${s.sname}</td>
                                        <td>${s.address}</td>
                                        <td>${s.province}</td>
                                        <td><u><a href="?lnkEdit&txtId=${s.id}">Edit</a></u></td>
                                        <td><u><a href="?lnkDelete&txtId=${s.id}">Delete</a></u></td>
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
