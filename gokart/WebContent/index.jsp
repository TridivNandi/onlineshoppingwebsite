<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "database.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "util.*" %>

<% ArrayList<ArrayList<Object>> selectedProducts = new ProductSelector().getSelectedProducts();
	pageContext.setAttribute("selectedProducts", selectedProducts);
 %>

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

</head>

<body class="s_layout_fixed">

<div id="wrapper"> 
  
  <!-- ********************** --> 
  <!--      H E A D E R       --> 
  <!-- ********************** -->
  
  <%@ include file="header.jsp" %>
  
  <!-- ********************** --> 
  <!--     I N T R O          --> 
  <!-- ********************** --> 
  <div id="intro">
    <div id="intro_wrap">
    
      <div id="product_intro" class="container_12">
        <div id="product_intro_info" class="grid_5">
          <div style="position: relative;">
            <h2><a href="./ProductDisplayServlet?Pname=${selectedProducts[0][0]}">${selectedProducts[0][0]}</a></h2>
            <div class="s_rating_holder">
              
              <span class="s_average">${selectedProducts[0][2]} out of 5</span> </div>
            <p class="s_desc">BASE PRICE: Rs. ${selectedProducts[0][3]}</p>
            <p class="s_desc">DISCOUNT:${selectedProducts[0][4]}%</p>
            <p class="s_desc">STATUS:${selectedProducts[0][5]}</p>
                         
          </div>
          <div style="position: relative; display: none;">
            <h2><a href="./ProductDisplayServlet?Pname=${selectedProducts[1][0]}">${selectedProducts[1][0]}</a></h2>
            <div class="s_rating_holder">
              
              <span class="s_average">${selectedProducts[1][2]} out of 5</span> </div>
            <p class="s_desc">BASE PRICE: Rs. ${selectedProducts[1][3]}</p>
            <p class="s_desc">DISCOUNT:${selectedProducts[1][4]}%</p>
            <p class="s_desc">STATUS:${selectedProducts[1][5]}</p>
          </div>
          
          <div style="position: relative; display: none;">
            <h2><a href="./ProductDisplayServlet?Pname=${selectedProducts[2][0]}">${selectedProducts[2][0]}</a></h2>
            <div class="s_rating_holder">
              
              <span class="s_average">${selectedProducts[2][2]} out of 5</span> </div>
            <p class="s_desc">BASE PRICE: Rs. ${selectedProducts[2][3]}</p>
            <p class="s_desc">DISCOUNT:${selectedProducts[2][4]}%</p>
            <p class="s_desc">STATUS:${selectedProducts[2][5]}</p>
            
          </div>
          <div style="position: relative; display: none;">
            <h2><a href="./ProductDisplayServlet?Pname=${selectedProducts[3][0]}">${selectedProducts[3][0]}</a></h2>
            <div class="s_rating_holder">
              
              <span class="s_average">${selectedProducts[3][2]} out of 5</span> </div>
            <p class="s_desc">BASE PRICE: Rs. ${selectedProducts[3][3]}</p>
            <p class="s_desc">DISCOUNT:${selectedProducts[3][4]}%</p>
            <p class="s_desc">STATUS:${selectedProducts[3][5]}</p>
            
          </div>
          <div style="position: relative; display: none;">
            <h2><a href="./ProductDisplayServlet?Pname=${selectedProducts[4][0]}">${selectedProducts[4][0]}</a></h2>
            <div class="s_rating_holder">
              
              <span class="s_average">${selectedProducts[4][2]} out of 5</span> </div>
            <p class="s_desc">BASE PRICE: Rs. ${selectedProducts[4][3]}</p>
            <p class="s_desc">DISCOUNT:${selectedProducts[4][4]}%</p>
            <p class="s_desc">STATUS:${selectedProducts[4][5]}</p>
           
          </div>
        </div>
        <div id="product_intro_preview">
          <div class="slides_container">
            <div class="slideItem" style="display: none"><img src="${selectedProducts[0][1]}" width="280" height="280" alt="" /></div>
            <div class="slideItem" style="display: none"><img src="${selectedProducts[1][1]}" width="280" height="280" alt="" /></div>
			<div class="slideItem" style="display: none"><img src="${selectedProducts[2][1]}" width="280" height="280" alt="" /></div>
            <div class="slideItem" style="display: none"><img src="${selectedProducts[3][1]}" width="280" height="280" alt="" /></div>
            <div class="slideItem" style="display: none"><img src="${selectedProducts[4][1]}" width="280" height="280" alt="" /></div>
          </div>
          <a class="s_button_prev" href="javascript:;"></a>
          <a class="s_button_next" href="javascript:;"></a>
        </div>
      </div>
    </div>
  </div>
	<script type="text/javascript" src="js/jquery/jquery.slides.js"></script> 
  <script type="text/javascript" src="js/shoppica.products_slide.js"></script>
  <!-- end of intro --> 
  
  
  <!-- ********************** --> 
  <!--      C O N T E N T     --> 
  <!-- ********************** --> 
  <div id="content" class="container_12">
  
    <div id="welcome" class="grid_12">
      <h2>Welcome to GoKart store</h2>
      <p> <a href="">GoKart</a> is a stylish premium shopping website bringing forth to you the best deals you can only dream of coupled with efficient and excellent cash on delivery options.</p>
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
