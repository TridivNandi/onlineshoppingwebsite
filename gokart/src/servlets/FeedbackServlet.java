package servlets;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

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
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		//Customer must be logged in to submit feedback
		if(session.getAttribute("Customer") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

		String pname = request.getParameter("Pname");
		Customer customer = (Customer)session.getAttribute("Customer");
		String review = request.getParameter("Review");
		String fname = (String)(customer.getUserDetails().get(0));
		String lname = (String)(customer.getUserDetails().get(1));
		if(!review.equals(""))
			review = "<p><font color='BLUE'>" + fname + " " + lname + "</p></font><p>" + review + "</p><br />";
		//make a Clob somehow
		/*Clob reviewClob = null;
		try {
			reviewClob = customer.getConnection().createClob();
			reviewClob.setString(1, review);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int rating = 0;
		if(request.getParameter("Rating") != "")
			rating = Integer.parseInt(request.getParameter("Rating"));
		customer.submitFeedback(pname, review, rating);
		request.setAttribute("Product", new SearchEngine().searchProduct(pname));
		RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
		rd.forward(request, response);
	}

}
