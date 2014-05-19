<%-- 
    Document   : routeAdmin
    Created on : May 17, 2014, 11:11:06 AM
    Author     : Dell
--%>

<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Manage Route</title>
        
    </head>

    <body>
        <h2 style="text-align: center; color: blue"> ROUTE </h2>
        <hr>
        <h3 style="text-align: center; color: red;">${errors}</h3>

        <form id="frmRoute" name="frmRoute" method="post" action="RouteCRUD">
            <table border="0" align="center" cellpadding="5" cellspacing="0">

                <tr>
                    <td>Departure</td>
                    <td><input size="3" type="text" name="txtDeparture" value="${rt.departure}" /></td>
                </tr>
                <tr>
                    <td>Terminate</td>
                    <td><input size="3" type="text" name="txtTerminate" value="${rt.terminate}" /></td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="submit" name="btnInsert" id="btnInsert" value="Insert" /></td>
                </tr>
                <tr>
                    <td>ID</td>
                    <td><input size="3" type="text" name="txtId" value="${rt.id}"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="submit" name="btnDelete" id="btnDelete" value="Delete" />
                        <a href="stationAdmin.jsp"><input type="button" value="Station"/></a>
                        <a href="admin.jsp"><input type="button" value="SBAdmin"/></a>
                    </td>
                </tr>
            </table>
            <hr/>
            <div style="text-align: center; margin: 10px;">
  	Search: <input type="text" name="txtSearch" value="${search}"/>
                <input type="submit" name="btnSearch"value="Search" />
            </div>

            <table width="100%" border="1" align="center" cellpadding="5" cellspacing="0">
                <tr bgcolor="lightgray">
                    <th>Id</th>
                    <th>Departure</th>
                    <th>Terminate</th>
                    <th>Name</th>
                </tr>
                <c:forEach var="r" items="${rts.rows}">
                    <tr>
                        <td>${r.id}</td>
                        <td>${r.departure}</td>
                        <td>${r.terminate}</td>
                        <td>${r.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </body>
</html>