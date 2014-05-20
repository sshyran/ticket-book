<%-- 
    Document   : index
    Created on : May 15, 2014, 9:41:09 PM
    Author     : bc
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
        <title>Đặt vé</title>
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
            #headerwrap input[type=text] {
                background: #ffffff; 
                font-family: 'Open Sans', sans serif;
                font-size: 1.3125em;
                border: 1px solid #cccccc;
                text-align: center;
                vertical-align: middle;
                margin-bottom: 0 !important;
            }
            #headerwrap input[type=text]:focus {
                background: #ffffff; 
                border-color: rgba(82, 168, 236, 0.8);
                outline: 0;
                outline: thin dotted \9;
                color:black;
                /* IE6-9 */

                -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
                -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
                box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
            }
            h4 {
                font-size: 1em;
                line-height: 1.5;
                margin-top: 1em;
                margin-bottom: 1em;
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
                                <li><a title="contact" href="ActionServlet?btAction=Home">Home</a></li>
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
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <center id="center">
                            <form action="ActionServlet">
                                <table border="0" style="background-color: grey">
                                    <tr>
                                        <td>
                                            <h4>Name</h4>
                                        </td>
                                        <td>
                                            <input type="text" name="txtName" required="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>Email</h4></td>
                                        <td>
                                            <input type="email" name="txtEmail" required="true"  />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>Phone</h4></td>
                                        <td>
                                            <input type="text" name="txtPhone" required="true" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>Number of Ticket</h4></td>
                                        <td>
                                            <select name="NumOfTicket">
                                                <c:forEach var="i" begin="1" end="${requestScope.count}" step="1">
                                                    <option value="${i}">${i}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>Payment method</h4></td>
                                        <td>
                                            <select name="Method">
                                                <option value="Cash">Cash</option>
                                                <option value="Bank">Bank</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="hidden" name="tripID" value="${requestScope.tripID}"/>
                                            <input type="hidden" name="price" value="${requestScope.price}"/>
                                            <input type="submit" value="Booking" name="action"/></td>
                                    <input type="hidden" value="Book ticket" name="btAction"/>
                                    </tr>
                                </table>
                            </form>

                        </center>
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
