<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en" xml:lang="en" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Your Store</title>
<meta name="description" content="My Store" />
<link rel="stylesheet" type="text/css" href="stylesheet/960.css" media="all" />
<link rel="stylesheet" type="text/css" href="stylesheet/screen.css" media="screen" />
<link rel="stylesheet" type="text/css" href="stylesheet/color.css" media="screen" />
<!--[if lt IE 9]>
<link rel="stylesheet" type="text/css" href="stylesheet/ie.css" media="screen" />
<![endif]-->

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/shoppica.js"></script>

<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {

	jQuery.validator.setDefaults({
			errorElement: "p",
			errorClass: "s_error_msg",
			errorPlacement: function(error, element) {
					error.insertAfter(element);
			},
			highlight: function(element, errorClass, validClass) {
					$(element).addClass("error_element").removeClass(validClass);
					$(element).parent("div").addClass("s_error_row");
			},
			unhighlight: function(element, errorClass, validClass) {
					$(element).removeClass("error_element").addClass(validClass);
					$(element).parent("div").removeClass("s_error_row");
			}
	});
	$("#login").validate();

});
</script>


</head>

<body class="s_layout_fixed">

<div id="wrapper"> 
  
    <!-- ********************** --> 
  <!--      H E A D E R       --> 
  <!-- ********************** -->
  
  <%@ include file="header.jsp" %>
  
  <!-- end of header --> 

  <!-- ********************** --> 
  <!--     I N T R O          -->
  <!-- ********************** --> 
  <div id="intro">
    <div id="intro_wrap">
      <div class="container_12">
        <div id="breadcrumbs" class="grid_12">
          <a href="index.jsp">Home</a>
           &gt; <a href="">Basket</a>
        </div>
        <h1>My account</h1>
      </div>
    </div>
  </div>
  <!-- end of intro -->
  
  
  <!-- ********************** --> 
  <!--      C O N T E N T     --> 
  <!-- ********************** --> 
  <div id="content" class="container_16">
  

    <div id="login_page" class="grid_16">
            
      <div class="grid_8 alpha">
        <h2 class="s_title_1"><span class="s_secondary_color">I am a new</span> customer.</h2>
        <div class="clear"></div>
        <form id="account" action="signup.jsp">
          <div class="s_row_3 clearfix">
            
            <br />
            <p>By creating an account you will be able to shop faster, be up to date on an order's status, and keep track of the orders you have previously made.</p>

          </div>
          <br />
          <button class="s_button_1 s_main_color_bgr" type="submit"><span class="s_text">Register</span></button>
        </form>
      </div>

      <div class="grid_8 omega">
        <h2 class="s_title_1"><span class="s_secondary_color">Returning</span> Customer</h2>

        <div class="clear"></div>
        <form id="login" action="<c:url value='./AuthenticatorServlet'/>" method="post">
          <div class="s_row_3 clearfix">
            I am a returning customer.<br />
            <br />
            <label><strong>E-Mail Address:</strong></label>
            <input type="text" size="35" name="Email" class="required email" />
            <br />
            <br />
            <label><strong>Password:</strong></label>
            <input type="password" size="35" name="Password" class="required" />
            <br />
          </div>
          <c:if test="${requestScope.Error != null}">
         <label><font color="RED">Username or password do not match</font></label>
          </c:if>
          <span class="clear border_ddd"></span>
          <br />
          <button class="s_button_1 s_main_color_bgr" type="submit"><span class="s_text">Login</span></button>
        </form>
      </div>

     </div>
    
  </div>
  <!-- end of content --> 
  
 
  
  
  
 <!-- ********************** --> 
  <!--      F O O T E R       --> 
  <!-- ********************** --> 
  <div id="footer" class="container_12">
    <div id="footer_categories" class="clearfix">
      <div class="grid_2">
      
	<p id="copy">&copy; Pending Copyright 2012. Powered by WayOverGod <br /> <span class="s_main_color" >Made by RR, SB, SC, TN</span></p>
  </div>
  </div>
  </div>
  <!-- end of FOOTER --> 
  
</div>

<div id="fb-root"></div>
<script type="text/javascript">
  window.fbAsyncInit = function() {
    FB.init({appId: '0c18007de6f00f7ecda8c040fb76cd90', status: true, cookie: true,
     xfbml: true});
  };
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
    '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());
</script>

</body>
</html>
