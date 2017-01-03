<div id="header" class="container_12">
    <div class="grid_12">
    
    	<a id="site_logo" href="index.jsp">GoKart</a> 

      <div id="system_navigation" class="s_nav">
        <ul class="s_list_1 clearfix">
          <li><a href="index.jsp">Home</a></li>
          <c:choose>
          <c:when test="${sessionScope.Customer != null}">
              <li>Welcome ${sessionScope.Customer.userDetails[0]}</li>
          <li><a href="./LogOutServlet">Log Out</a></li>
          <li><a href="update.jsp">Update account</a></li>
          <li><a href="cart.jsp">Shopping Cart</a></li>
          </c:when>
          <c:otherwise>
          <li><a href="login.jsp">Log In</a></li>
          </c:otherwise>
          </c:choose>
        </ul>
      </div>

      <div id="site_search">
      	<a id="show_search" href="javascript:;" title="Search:"></a>
        <div id="search_bar" class="clearfix">
        <form action="./SearcherServlet" method="post">
          <input type="text" id="filter_keyword" name="SearchWord"/>
          
          <button class="s_button_1 s_secondary_color_bgr" type="submit"><span class="s_text">Go</span></button>
          </form>
        </div>
      </div>
      
      <div id="categories" class="s_nav">
        <ul>
          <li id="menu_home"> <a href="index.jsp">Home</a> </li>
         
          <li> <a href="./SearcherServlet?Category=Mobiles">Mobiles</a></li>
              
          <li> <a href="./SearcherServlet?Category=Computers">Computers</a></li>
          
          <li><a href="./SearcherServlet?Category=Clothing">Clothing</a></li>
          
          <li><a href="./SearcherServlet?Category=Shoes">Shoes</a></li>
          
          <li><a href="./SearcherServlet?Category=Perfumes">Perfumes</a></li>
         
          <li><a href="./SearcherServlet?Category=Cameras">Cameras</a></li>
           
          <li><a href="./SearcherServlet?Category=Electronic-Accessories">Electronic Accessories</a></li>
            
          <li><a href="./SearcherServlet?Category=Books">Books</a></li>
            
        </ul>
      </div>
      

      
    </div>
  </div>