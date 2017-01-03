package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SearchEngine;

/**
 * Servlet implementation class ProductDisplayServlet
 */
@WebServlet("/ProductDisplayServlet")
public class ProductDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Search the database for a particular product and display that product.
		
		String pname = request.getParameter("Pname");
		ArrayList<Object> product = new SearchEngine().searchProduct(pname);
		request.setAttribute("Product", product);
		RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
		rd.forward(request, response);
				
	}

}
