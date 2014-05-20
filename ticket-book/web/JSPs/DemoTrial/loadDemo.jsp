<%-- 
    Document   : adminIndex
    Created on : May 16, 2014, 11:17:00 PM
    Author     : phuongdn
--%>
<%@page import="DAL.DAO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator's index</title>
        <link href="${pageContext.request.contextPath}/back-end/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/back-end/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/back-end/font-awesome/css/font-awesome.min.css">
        <script src="${pageContext.request.contextPath}/back-end/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/back-end/js/bootstrap.js"></script>
    </head>
    <body>
        <%
                    String conStr = new DAO().getConnectionString();
                    String driver = new DAO().getDriverString();
        %>
        <sql:setDataSource var="sqlDS" url="<%=conStr%>" driver="<%=driver%>" />
        <sql:query dataSource="${sqlDS}" var="stationList">
            SELECT [id] ,[sname] ,[address] ,[province]  FROM [TripBooking].[dbo].[Station]
        </sql:query>
        <!--Present data-->
    <center>
        <table border="1">
            <!--header-->
            <tr>
                <c:forEach var="colName" items="${stationList.columnNames}">
                    <th><c:out value="${colName}" /></th>
                </c:forEach>
            </tr>
            <!--rows-->
            <c:forEach var="row" items="${stationList.rowsByIndex}">
                <tr>
                    <c:forEach var="col" items="${row}">
                        <td> <c:out value="${col}" /> </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
