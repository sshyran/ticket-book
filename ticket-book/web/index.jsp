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
                        <h1 class="brand"><a href="#top">Legend!</a></h1>
                        <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
                        <nav class="pull-right nav-collapse collapse">
                            <ul id="menu-main" class="nav">
                                <li><a title="portfolio" href="#portfolio">Portfolio</a></li>
                                <li><a title="services" href="#services">Services</a></li>
                                <li><a title="news" href="#news">News</a></li>
                                <li><a title="team" href="#team">Team</a></li>
                                <li><a title="contact" href="#contact">Contact</a></li>
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
            <header class="clearfix">
                <h1><span>Legend!</span> We make web a beautiful place.</h1>
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <h2>Signup for our Newsletter to be updated</h2>
                            <input type="text" name="your-email" placeholder="you@yourmail.com" class="cform-text" size="40" title="your email">
                            <input type="submit" value="Notify me" class="cform-submit">
                        </div>
                    </div>
                    <div class="row">
                        <div class="span12">
                            <ul class="icon">
                                <li><a href="#" target="_blank"><i class="icon-pinterest-circled"></i></a></li>
                                <li><a href="#" target="_blank"><i class="icon-facebook-circled"></i></a></li>
                                <li><a href="#" target="_blank"><i class="icon-twitter-circled"></i></a></li>
                                <li><a href="#" target="_blank"><i class="icon-gplus-circled"></i></a></li>
                                <li><a href="#" target="_blank"><i class="icon-skype-circled"></i></a></li>
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
