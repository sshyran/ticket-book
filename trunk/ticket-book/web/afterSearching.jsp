<%-- 
    Document   : afterSearching
    Created on : May 17, 2014, 11:50:47 PM
    Author     : Jessie
--%>

<%@page import="DAL.DAO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search trips</title>
        <link href="front-end/css/bootstrap.css" rel="stylesheet">
        <link href="front-end/css/style.css" rel="stylesheet">
        <link href="front-end/css/fontello.css" type="text/css" rel="stylesheet"
              <link href="${pageContext.request.contextPath}/cssPhuongDN/bootstrap-datetimepicker.min.css" rel="stylesheet">
        <script type="${pageContext.request.contextPath}/cssPhuongDN/jquery.min.js"></script>
        <script type="${pageContext.request.contextPath}/cssPhuongDN/bootstrap.min.js"></script>
        <script type="${pageContext.request.contextPath}/cssPhuongDN/bootstrap-datetimepicker.min.js"></script>
        <script type="${pageContext.request.contextPath}/cssPhuongDN/bootstrap-datetimepicker.pt-BR.js"></script>
        <script type="${pageContext.request.contextPath}/cssPhuongDN/index.js"></script>
        <script type="${pageContext.request.contextPath}/cssPhuongDN/layout.css"></script>
        <script type="${pageContext.request.contextPath}/cssPhuongDN/respond.min.js"></script>
        <style>
            body {
                padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
            }
        </style>
        <link href="front-end/css/bootstrap-responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="front-end/img/favicon.ico">
        <!-- JQuery -->
        <script type="text/javascript" src="front-end/js/jquery.js"></script>
        <!-- Load ScrollTo -->
        <script type="text/javascript" src="front-end/js/jquery.scrollTo-1.4.2-min.js"></script>
        <!-- Load LocalScroll -->
        <script type="text/javascript" src="front-end/js/jquery.localscroll-1.2.7-min.js"></script>
    </head>
    <body>
        <div class="navbar-wrapper">
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">
                        <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
                        <h1 class="brand"><a href="#top">BUS TICKET BOOKER</a></h1>
                        <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
                        <nav class="pull-right nav-collapse collapse">
                            <ul id="menu-main" class="nav">
                                <li><a title="contact" href="WelcomeServlet">Home</a></li>
                                <li><a title="contact" href="#contact">Admin login</a></li>
                            </ul>
                        </nav>
                    </div>
                    <!-- /.container -->
                </div>
                <!-- /.navbar-inner -->
            </div>
            <!-- /.navbar -->
        </div>
        <!-- /.navbar-wrapper -->
        <div id="top"></div>
        <!-- ******************** HeaderWrap ********************-->
        <div id="headerwrap">
            <div class="paddingSearch">
                <form action="SearchServlet">
                    <ul class="inline">
                        <li>Departure :</li>
                        <li>
                            <select class="selectpicker" name="txtDeparture">
                                <c:forEach var="stations" items="${sessionScope.stations}">
                                    <option value="${stations.id}">${stations.province}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li>Terminate :</li>
                        <li>
                            <select class="selectpicker" name="txtTerminate">
                                <c:forEach var="stations" items="${sessionScope.stations}">
                                    <option value="${stations.id}">${stations.province}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li>Date :</li>
                        <li>
                            <input type="text" name="txtDate" placeholder="dd-mm-yyyy"/>
                        </li>
                        <li>
                            <input type="submit" value="Search" name="btAction" />
                        </li>
                    </ul>
                </form>
            </div>

            <div class="container">
                <div class="row">
                    <div class="span12">
                        <table class="table table-bordered table-hover tablesorter">
                            <tr>
                                <th class="header">No.</th>
                                <th class="header">Trip</th>
                                <th class="header">Departure Station</th>
                                <th class="header">Terminate Station</th>
                                <th class="header">Departure time</th>
                                <th class="header">Termination time</th>
                                <th class="header">Available seat</th>
                                <th class="header">Price (VNĐ)</th>
                                <th class="header">Book ticket</th>
                            </tr>

                            <c:if test="${requestScope.check eq 'check'}">
                                <tr>
                                    <td colspan="9">
                                        <h2>Sorry. No trip is available for your choose.</h2>
                                    </td>
                                </tr>
                            </c:if>
                            <c:set var="depS" value="${requestScope.depS}" />
                            <c:set var="terS" value="${requestScope.terS}" />
                            <c:forEach var="trip" items="${requestScope.trips}" varStatus="counter">
                                <tr class="active">
                                <form action="BookingServlet">
                                    <td>${counter.count}</td>
                                    <td>${depS.province} - ${terS.province}</td>
                                    <td>${depS.sname} - ${depS.address}</td>
                                    <td>${terS.sname} - ${terS.address}</td>
                                    <td>${trip.depTime}</td>
                                    <td>${trip.terTime}</td>
                                    <td>${trip.availableSeat}</td>
                                    <td>${trip.price}</td>
                                    <td>

                                        <input type="hidden" name="price" value="${trip.price}"/>
                                        <input type="hidden" name="availableSeat" value="${trip.availableSeat}"/>
                                        <input type="hidden" name="tripID" value="${trip.id}"/>
                                        <input type="submit" value="Book" name="action" />
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="span12">
                        <ul class="icon">
                            <li><a href="#"><i class="icon-pinterest-circled"></i></a></li>
                            <li><a href="#"><i class="icon-facebook-circled"></i></a></li>
                            <li><a href="#"><i class="icon-twitter-circled"></i></a></li>
                            <li><a href="#"><i class="icon-gplus-circled"></i></a></li>
                            <li><a href="#"><i class="icon-skype-circled"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
    </div>
    <div class="scrollblock">
        <section id="feature">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <article>
                            <p>We work to make web a beautiful place.</p>
                            <p>We craft beautiful designs and convert them into</p>
                            <p>fully functional and user-friendy web app.</p>
                        </article>
                    </div>
                    <!-- ./span12 -->
                </div>
                <!-- .row -->
            </div>
            <!-- ./container -->
        </section>
    </div>
</body>
</html>
