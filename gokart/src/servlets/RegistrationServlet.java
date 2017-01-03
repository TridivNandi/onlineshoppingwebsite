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
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> parameterMap = request.getParameterMap();
		ArrayList<Object> userDetails = new ArrayList<Object>();
		
		userDetails.add(parameterMap.get("First_Name")[0]);
		userDetails.add(parameterMap.get("Last_Name")[0]);
		userDetails.add(parameterMap.get("Email")[0]);
		userDetails.add(parameterMap.get("Password")[0]);
		userDetails.add(parameterMap.get("Shipping_Address")[0]);
		userDetails.add(parameterMap.get("Pin_Code")[0]);
		userDetails.add(parameterMap.get("Registered_Phone_No")[0]);
				
		HttpSession session = request.getSession();
		Customer customer = new Customer();
		if(session.isNew())//new user registration
			customer.create(userDetails);
		else//account editing
			customer.updateUserDetails(userDetails);
		System.out.println(customer);
		customer.setUserDetails(parameterMap.get("Email")[0]);
		session.setAttribute("Customer", customer);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

}
