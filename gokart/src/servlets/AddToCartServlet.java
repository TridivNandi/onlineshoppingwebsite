package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.SearchEngine;

import model.Customer;

/**
 * Servlet implementation class CartHandlerServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Add-to-cart button is pressed for a certain product. Button embeds a request parameter-Pname.
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		if(customer == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);		
		}
		else {
			ArrayList<Object> orderDetails = new ArrayList<Object>();
			String pname = request.getParameter("Pname");
			System.out.println(pname);
			request.setAttribute("Pname", pname);
			orderDetails = new SearchEngine().searchForMatchingProduct(pname);
			
			orderDetails.add(1);//adds quantity=1 of the item to the cart
			
			float discount = (Float) orderDetails.get(2);
			float basePrice = (Float) orderDetails.get(1);
			float totalPrice = basePrice - basePrice*discount/100;
			
			orderDetails.add(totalPrice);//adding total price
			customer.addToCart(pname, orderDetails);
			RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);		
		}
	}
}
