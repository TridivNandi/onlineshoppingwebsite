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
          &gt;
          <a href="">${param.Category}</a></div>
        <h1>${param.Category}</h1>
      </div>
    </div>
  </div>
  <!-- end of intro -->

  
  <!-- ********************** --> 
  <!--      C O N T E N T     -->
  <!-- ********************** --> 
  <div id="content" class="container_12">

    
    <div id="category" class="grid_9">
      <div id="listing_options">
        <div id="view_mode" class="s_nav">
          <ul class="clearfix">
            <li id="view_grid" class="s_selected"><span class="s_icon"></span>Grid</li>
          </ul>
        </div>
      </div>

      <div class="clear"></div>
      
      <div class="s_listing s_grid_view clearfix">
      
      <c:forEach var="product" items="${SearchResult}" varStatus="status" >
        <c:if test="${status.count % 4 == 0}">
        	<div class="clear"></div>
        </c:if>
        
        <div class="s_item grid_3"> 
                    
          <h3><a href="./ProductDisplayServlet?Pname=${product[0]}">${product[0]}</a></h3>
          <h4>Base Price Rs.${product[3]}</h4>
          <img src="${product[1]}" width="200" height="200" />
          <p class="s_price"><span class="s_currency s_before">Discount</span>${product[4]} %</p>
          
        </div>
		
      </c:forEach>  

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
      
	<p id="copy">&copy; Pending Copyright 2012. Powered by WayOverGod <br /> <span class="s_main_color" >Made by RR, SB, SC, TN</span></p>  </div>
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
