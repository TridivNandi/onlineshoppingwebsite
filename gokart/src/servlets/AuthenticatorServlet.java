package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.Encrypt;
import security.SystemUnavailableException;

import model.Customer;

/**
 * Servlet implementation class AuthenticatorServlet
 */
@WebServlet("/AuthenticatorServlet")
public class AuthenticatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("Email");
		char[] password = null;
		try {
			password = Encrypt.getInstance().hash("SHA-512", request.getParameter("Password"), true).toCharArray();
		} catch (SystemUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer customer = new Customer();
		if(customer.connect(email, password) == true) {
			customer.setUserDetails(email);
			HttpSession session = request.getSession();
			session.setAttribute("Customer", customer);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		
		else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("Error", 1);
			rd.forward(request, response);
		}
		
	}

}
