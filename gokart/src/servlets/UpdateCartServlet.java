package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		Map<String,String[]> parameterMap = request.getParameterMap();
		System.out.println("\n"+parameterMap.keySet());
		Map<String, ArrayList<Object>> cart = customer.getCart();
		
		String skipFlag = "";
		
		for(String key: parameterMap.keySet()) {
			String temp = parameterMap.get(key)[0];
			System.out.println("Temp = "+ temp);
			
			
			//checkbox name must be Pname,quantity field name should be Quantity-<Pname>
			if(key.startsWith("Quantity")){//it's a quantity field
				int quantity = Integer.parseInt(temp.trim());
				String pname = key.substring(9);
				if(skipFlag.equals(pname))
					continue;
				ArrayList<Object> orderDetails = cart.get(pname);
				orderDetails.set(3, quantity);//resetting quantity
				
				float basePrice = (Float) orderDetails.get(1);
				float discount = (Float) orderDetails.get(2);
				
				float totalPrice = (basePrice - basePrice * discount / 100) * quantity;
				orderDetails.set(4, totalPrice);//recalculating and resetting the total price
				
			}
			else if(!key.startsWith("Quantity")) {
				if(temp.equals("on")) {//checkbox checked
					skipFlag = key;
					customer.removeFromCart(key);
				}
			}
			
			
			
		}
		customer.setCart(cart);
		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
	}

}
