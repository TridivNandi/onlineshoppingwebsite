<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en" xml:lang="en" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GoKart</title>
<meta property="og:image" content="images/dummy/pic_1.jpg" />
<meta name="description" content="My Store" />

<link rel="stylesheet" type="text/css" href="stylesheet/960.css" media="all" />
<link rel="stylesheet" type="text/css" href="stylesheet/screen.css" media="screen" />
<link rel="stylesheet" type="text/css" href="stylesheet/color.css" media="screen" />
<link rel="stylesheet" type="text/css" href="stylesheet/prettyPhoto.css" media="all" />
<!--[if lt IE 9]>
<link rel="stylesheet" type="text/css" href="stylesheet/ie.css" media="screen" />
<![endif]-->

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="js/shoppica.js"></script>
<script type="text/javascript">

$(document).ready(function() {

  $(".s_tabs").tabs({ fx: { opacity: 'toggle', duration: 300 } });

  $("#product_images a[rel^='prettyPhoto'], #product_gallery a[rel^='prettyPhoto']").prettyPhoto({
    theme: 'light_square',
    opacity: 0.5
  });

});

function validate()
{
var f= document.myform.Rating.value;
if(f%1 != 0 || f>5 || f<1 )
	{
	window.alert("Rating must be an integer value between 0 and 5");
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
          <a href="">Home</a>
          &gt;
          <a href="">${param.Category}</a>
        </div>
        <h1>${param.Category}</h1>
      </div>
    </div>
  </div>
  <!-- end of intro -->

  
  <!-- ********************** --> 
  <!--      C O N T E N T     -->
  <!-- ********************** --> 
  <div id="content" class="product_view container_12">

    <div id="product" class="grid_12">
    <form action="./AddToCartServlet?Pname=${Product[0]}" method="post">
      <div id="product_images" class="grid_4 alpha"><a id="product_image_preview" rel="prettyPhoto[gallery]" href=""><img id="image" src="${Product[2]}" width="300" height="300" /></a>
      </div>
      
       
      <div id="product_info" class="grid_4">
        <dl class="clearfix">
          <dt>Availability</dt>
          <dd>${Product[9]}</dd>
          <dt>Price</dt>
          <dd>Rs. ${Product[7]}</dd>
          <dt>Discount</dt>
          <dd>${Product[8]} %</dd>
          <dt>Average Rating</dt>
          <dd>
            <p>${Product[4]} out of 5</p>
          </dd>
        </dl>
      	<p class="s_short_desc">${Product[3]}</p>
      	<button class="s_button_1 s_main_color_bgr" type="submit"><span class="s_text">Add to cart</span></button> <span class="clear"></span> </div>   
        </form>
          <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=xa-4de0eff004042e7a"></script>
          <!-- AddThis Button END -->
        
       
       </div>
       
      </div>
      <div class="clear"></div>
            
       <div class="s_tab_box">
        <h2 class="s_title_1"><span class="s_main_color">Reviews</span></h2>
       <br> </br>
       <br> </br>
      <div class="cpt_product_description ">
      <strong>${Product[5]}</strong>
      </div>
       
      
        
        <form name="myform" action="<c:url value='./FeedbackServlet?Pname=${Product[0]}' />" method="post"  onsubmit="return validate()">
          <div id="product_reviews" class="s_listing">
          <c:if test="${sessionScope.Customer != null}">
            <h2 class="s_title_1"><span class="s_main_color">Write</span> Review</h2>
            <div id="review_title" class="clear"></div>
            <div class="s_row_3 clearfix">
              <label><strong>Your Review:</strong></label>
              <textarea name="Review" style="width: 98%;" rows="8"></textarea>
              
            </div>
            <div class="s_row_3 clearfix">
              <label><strong>Rating</strong></label>
              <input type="text" name="Rating"  size="5"/> <strong> out of 5</strong>
            </div>
            <span class="clear border_ddd"></span>
          <button class="s_button_1 s_main_color_bgr" type="submit"><span class="s_text">SUBMIT</span></button>  <span class="clear"></span>
          
          </c:if>
          </div>
          </form>
          
          
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
<script>
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
