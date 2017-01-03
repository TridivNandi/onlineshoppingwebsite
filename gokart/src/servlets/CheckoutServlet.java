package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Checkout the entire cart.
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		String email = (String)customer.getUserDetails().get(2);
		Map<String, ArrayList<Object>> cart = customer.getCart();
		float grandTotal = 0.0f;
		ArrayList<Object> order = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> orderSummary = new ArrayList<ArrayList<Object>>();
		for(String pname : cart.keySet()) {
			//checkout each item with every loop iteration
			//generate order id for each product
			
			String orderId = email + "#" + pname + "#" + new Date().getTime();
			order.add(orderId);
			order.add(pname);
			order.add(email);
			long time = new Date().getTime();
			order.add(time);
			ArrayList<Object> product = cart.get(pname);
			
			int noOfItems = (Integer)product.get(3);
			order.add(noOfItems);
					
			float basePrice = (Float) product.get(1);
			float discount = (Float) product.get(2);
			float totalPrice = (basePrice - basePrice * discount / 100) * noOfItems;
			grandTotal = grandTotal + totalPrice;
			
			order.add(basePrice);
			order.add(discount);
			order.add(totalPrice);
			
			customer.placeOrder(order);
			orderSummary.add(order);
			cart.remove(pname);
			
			order = new ArrayList<Object>();
		}
		request.setAttribute("Order_Summary", orderSummary);
		request.setAttribute("Grand_Total", grandTotal);

		RequestDispatcher rd = request.getRequestDispatcher("invoice.jsp");
		rd.forward(request, response);

	}

}
