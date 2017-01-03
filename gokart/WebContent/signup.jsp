<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en" xml:lang="en" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GoKart</title>
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
function validateForm() {
	//window.alert("listened");
	/**var f=document.getElementsByName("myform");
	for(var i=0;i<f.length;i++)
		{
		if(f[i]==null || f[i]=="")
			{
			window.alert("Missing fields");
			return false;
			}
		}**/
		var f=document.myform.First_Name.value;
		   if(f==null || f=="")
			  {
			  window.alert("Missing Fields");
			  return false;
			  }
		   
		   f=document.myform.Last_Name.value;
		   if(f==null || f=="")
			  {
			  window.alert("Missing Fields");
			  return false;
			  }
		   f=document.myform.Shipping_Address.value;
		   if(f==null || f=="")
			  {
			  window.alert("Missing Fields ");
			  return false;
			  }
		   f=document.myform.Pin_Code.value;
		   if(f==null || f=="")
			  {
			  window.alert("Missing Fields");
			  return false;
			  }
		   		   
	f=document.myform.Email.value;
		  var atpos=f.indexOf("@");
		  var dotpos=f.indexOf(".");
		  if(f==null || f=="" || atpos<1 || dotpos<atpos+2 || dotpos+2>=f.length)
			  {
			  window.alert("Not A Valid Email Address");
			  return false;
			  }
		
	f=document.myform.Registered_Phone_No.value;
	if(f==null || f=="" || f.length <8 || f.length >10)
			{
			window.alert("Invalid Phone Number");
			return false;
			}
		
	f= document.myform.Password.value ;
			
	if(f==null || f=="" || f.length < 6 || f.length > 12)
		{
		window.alert("Password must b 6-12 characters long");
		return false;
		}
	

}
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
           &gt; <a>Basket</a>
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
  

    <div class="grid_16">

      <form id="create" name="myform" class="clearfix" action="./RegistrationServlet" method="post" onsubmit = "return validateForm()">
        <h2 class="s_title_1"><span class="s_secondary_color">Sign Up Form</span></h2>
        <div class="clear"></div>

        <div class="s_row_2 clearfix">
          <label class="required"><strong>First Name</strong></label>
          <input type="text" size="20" name = "First_Name" />
        </div>
        
         <div class="s_row_2 clearfix">
          <label class="required"><strong>Last Name</strong></label>
          <input type="text" size="20" name = "Last_Name" />
        </div>
        
         <div class="s_row_2 clearfix">
          <label class="required"><strong>Email</strong></label>
          <input type="text" size="20" name = "Email" />
        </div>

         <div class="s_row_2 clearfix">
          <label class="required"><strong>Password</strong></label>
          <input type="password" size="20" name = "Password" />
        </div>

         <div class="s_row_2 clearfix">
          <label class="required"><strong>Shipping Address</strong></label>
          <input type="text" size="50" name = "Shipping_Address" />
        </div>
        
         <div class="s_row_2 clearfix">
          <label class="required"><strong>Pin Code</strong></label>
          <input type="text" size="10" name = "Pin_Code" />
        </div>
        
         <div class="s_row_2 clearfix">
          <label class="required"><strong>Phone Number</strong></label>
          <input type="text" size="20" name = "Registered_Phone_No" />
        </div>
    
     

        <input type="submit" class="s_button_1 s_main_color_bgr left" value="Submit" />
        
        
      </form>
      
      <br />
      <br />
  
    </div>
    
  </div>
  <!-- end of content --> 
  
  
  
  
  <!-- ********************** --> 
  <!--      F O O T E R       --> 
  <!-- ********************** --> 
  <div id="footer" class="container_12">
    <div id="footer_categories" class="clearfix">
      <div class="grid_2">
      
	<p id="copy">&copy; Pending Copyright 2012. Powered by WayOverGod <br /> <span class="s_main_color" >Made by RR, SB, SC, TN</span></p>  </div>
  </div>
  </div>
  <!-- end of FOOTER --> 
  
</div>

<div id="fb-root"></div>
<!-- <script type="text/javascript">
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
</script> -->

</body>
</html>
