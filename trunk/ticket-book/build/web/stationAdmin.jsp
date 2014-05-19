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
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Manage Station</title>
        
    </head>

    <body>
        <h2 style="text-align: center; color: blue">STATION</h2>
        <hr>
        <h3 style="text-align: center; color: red;">${errors}</h3>
        <form id="frmStation" name="frmStation" method="post" action="StationCRUD">
            <table border="0" align="center" cellpadding="5" cellspacing="0">
                <tr>
                    <td>ID</td>
                    <td><input readonly size="3" type="text" name="txtId" value="${st.id}"/></td>
                </tr>
                <tr>
                    <td>Sname</td>
                    <td><input size="44" type="text" name="txtSName" value="${st.sname}" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><textarea rows="2" cols="34" name="txtAddress" >${st.address}</textarea></td>
                </tr>
                <tr>
                    <td>Province</td>
                    <td><input size="44" type="text" name="txtProvince" value="${st.province}"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="submit" name="btnInsert" id="btnInsert" value="Insert" />
                        <input type="submit" name="btnUpdate" id="btnUpdate" value="Update" />
                        <input type="submit" name="btnDelete" id="btnDelete" value="Delete" />
                        <a href="routeAdmin.jsp"><input type="button" value="Route"/></a>
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
                    <th>Sname</th>
                    <th>Address</th>
                    <th>Province</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach var="s" items="${sts.rows}">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.sname}</td>
                        <td>${s.address}</td>
                        <td>${s.province}</td>
                        <td><a href="?lnkEdit&txtId=${s.id}">Edit</a></td>
                    </tr>
                </c:forEach>
            </table>

        </form>
    </body>
</html>
