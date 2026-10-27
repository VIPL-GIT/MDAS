
<%@ page import="nl.captcha.Captcha" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>MDMS</title>
        <link rel="shortcut icon" href="assets/dist/img/favicon.png" type="image/x-icon">
        <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.26/webfont.js"></script>
        <script>
            WebFont.load({
                google: {families: ['Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i']},
                active: function () {
                    sessionStorage.fonts = true;
                }
            });
        </script>
        <!-- START GLOBAL MANDATORY STYLE -->
        <link href="assets/dist/css/base.css" rel="stylesheet" type="text/css">
        <!-- START THEME LAYOUT STYLE -->
        <link href="assets/dist/css/style.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
  .login_footer{
  background: url(img/footer__bg.gif) repeat 5% bottom;
    bottom:0px;
    color: #ffffff;
    left: 0;
    bottom: 0 !important;
    height: 135px;
    background-color: transparent;
    z-index: 90;
  }
  
  .usernameinner{
/*   margin-left: 45px; */
border-left: 1px solid #ddd;
background: #fff;
  }
  </style>
    </head>
    <body class="hold-transition fixed sidebar-mini">
        
        <!-- Content Wrapper -->
        <div class="login-wrapper" style="background:#2e92b2;">
            <div class="container-center" style="padding-bottom:0px !important;">
                <div class="panel panel-bd" style="background:#FFF">
                    <div class="panel-heading">
                        <div class="view-header">
                            <div class="header-icon">
                                <i class="pe-7s-unlock"></i>
                            </div>
                            <div class="header-title">
                                <h3>Login</h3> <br>
                                <small><strong>Please enter your credentials to login.</strong></small>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <form action="login.epx" id="loginForm" novalidate method="post" enctype="application/x-www-form-urlencoded" autoComplete="off">
                            
                            <div class="form-group">
                                <label class="control-label">Username</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                    <input id="username" type="text" class="form-control" name="userId" placeholder="Username">
                                </div>
<!--                                 <span class="help-block small">Your unique username to app</span> -->
                            </div>
                            <div class="form-group">
                                <label class="control-label">Password</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-key"></i></span>
                                    <input id="pass" type="password" class="form-control" name="password" placeholder="******">
                                </div>
<!--                                 <span class="help-block small">Your unique username to app</span> -->
                            </div>
                           <div class="form-group capta">
			                    <div class="usernameinner">
			                    <img src="<c:url value="simpleCaptcha.png" />">
			                    <input name="captcha" style="width:310px;height:30px;"/>
			                    </div>
			                </div>
                            <div>
                                <button class="btn btn-base pull-right">Login</button>
<!--                                 <div class="checkbox checkbox-success"> -->
<!--                                     <input id="checkbox3" type="checkbox"> -->
<!--                                     <label for="checkbox3">Keep me signed in</label> -->
<!--                                 </div> -->
<c:set var="val" value="${errormessage}"/>
<c:choose> 
  <c:when test="${val == 'true'}">
  <a style="color:blue;font-size:15px;"href='forgetpassword.epx'>Forget password?</a>
  </c:when>
  <c:otherwise>
  <p style="color:red;font-size:15px;">${errormessage}</p>
  </c:otherwise>
</c:choose>
<p style="color:blue;font-size:15px;">${SentInfo}</p>

                            </div>
                        </form>
                    </div>
                </div>
<!--                 <div id="bottom_text"> -->
<!--                     Don't have an account? <a href="register.html">Sign Up</a><br> -->
<!--                     Remind <a href="forget_password.html">Password</a> -->
<!--                 </div> -->

            </div>
        </div>  <!-- /.content-wrapper -->

        <div class="login_footer">
    <span class="pull-right" style="margin-top:105px;padding-right:25px;">© 2020  Verticross India Pvt.Ltd. All Rights Reserved.</span>
    </div>
        <!-- START CORE PLUGINS -->
        <script src="assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>