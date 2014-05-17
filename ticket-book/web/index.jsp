<%-- 
    Document   : index
    Created on : May 15, 2014, 9:41:09 PM
    Author     : bc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                        <h1 class="brand"><a href="#top">Bus ticket booker</a></h1>
                        <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
                        <nav class="pull-right nav-collapse collapse">
                            <ul id="menu-main" class="nav">
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
                            <table>
                                <tr>
                                    <td>
                                        <h2>Đi từ: </h2>
                                    </td>
                                    <td><select class="selectpicker">
                                            <option>1</option>
                                            <option>2</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td><h2>Đến: </h2></td>
                                    <td><select class="selectpicker">
                                            <option>1</option>
                                            <option>2</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td><h2>Ngày: </h2></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Tìm" class="cform-submit"></td>
                                    <td></td>
                                </tr>
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