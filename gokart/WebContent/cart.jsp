 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>

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

<script type="text/javascript">
function OnSubmitForm(a)
{
  if( a == 2)
  {
   document.myform.action ="<c:url value='./CheckoutServlet' />";
  }
  else
  if( a== 3)
  { 
	  var f= document.getElementById("Quantity").value;
	  
	  if(f%1 != 0 || f>10 || f<1 )
	  	{
	  	window.alert("Quantity must be an integer value between 0 and 10");
	  	return false;
	  	}

    document.myform.action ="<c:url value='./UpdateCartServlet' />";
  }
  else
	  if( a == 1)
	  {
	    document.myform.action ="<c:url value='index.jsp' />";
	  }
  return true;
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
           &gt; <a href="cart.jsp">Basket</a>
        </div>
        <h1>Shopping Cart</h1>
      </div>
    </div>
  </div>
  <!-- end of intro -->
  
  
  <!-- ********************** --> 
  <!--      C O N T E N T     --> 
  <!-- ********************** --> 
  <div id="content" class="container_12">
  
    <div id="shopping_cart" class="grid_12">

      <form id="cart" class="clearfix" name="myform" onsubmit="return OnSubmitForm();" method="post">
        <table class="s_table_1" width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <th width="65">Remove</th>
            <th width="320">Name</th>
            <th width="60">Image</th>
            <th>Base Price</th>
            <th>Discount</th>
            <th>Quantity</th>
            <th>Total</th>
          </tr>
          
		 	
		 	 <%	Set<String> keySet = ((Customer)session.getAttribute("Customer")).getCart().keySet();
		 		String[] keys = new String[keySet.size()];
		 		int i = 0;
		 		for(String pn : keySet)
		 			keys[i++] = pn;
		 		System.out.println(((Customer)session.getAttribute("Customer")).getCart());
		 		pageContext.setAttribute("Keys", keys);
			 	pageContext.setAttribute("Cart", ((Customer)session.getAttribute("Customer")).getCart());
			  %>	
		  	
		  		
		  		 <c:forEach var="pname" items="${Keys}" >
          			<c:set var="product" value="${pname}" scope="page" />

         			<tr class="even">
           				<td valign="middle"><input type="checkbox" name="${product}"/></td>
            			<td valign="middle"><a href="product.jsp"><strong>${product}</strong></a></td>
            			<td valign="middle"><a href="product.jsp"><img src="${Cart[product][0]}" width="60" height="60"  /></a></td>
            			<td valign="middle">Rs. ${Cart[product][1]}<span class="s_currency s_after"></span></td>
            			<td valign="middle">${Cart[product][2]}<span class="s_currency s_after">%</span></td>
            			<td valign="middle"><input id="Quantity" type="text" size="3" name="Quantity-${product}" value=" ${Cart[product][3]}"/></td>
            			<td valign="middle">Rs. ${Cart[product][4]}<span class="s_currency s_after"></span></td>
          			</tr>
          		</c:forEach>
          
        </table>
        <br />
                                
        <div class="clear"></div>
        <br />

        <button class="s_button_1 s_ddd_bgr left" type="submit" name="operation" onclick="OnSubmitForm(1);" value="Continue Shopping" ><span class="s_text">Continue Shopping</span></button>
        <button class="s_button_1 s_main_color_bgr" type="submit" name="operation" onclick="OnSubmitForm(2);" value="Checkout" ><span class="s_text">Checkout</span></button>
        <button class="s_button_1 s_main_color_bgr" type="submit" name="operation" onclick="OnSubmitForm(3);" value="Update" ><span class="s_text">Update</span></button>
      </form>

    </div>

    <div class="clear"></div>
    <br />
    <br />
    
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
