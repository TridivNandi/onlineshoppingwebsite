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
 * Servlet implementation class SearcherServlet
 */
@WebServlet("/SearcherServlet")
public class SearcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Search by category. The URL for the category links have the embedded request parameter-category.
		
		String category = request.getParameter("Category");
		ArrayList<ArrayList<Object>> result = new SearchEngine().searchByCategory(category);
		System.out.println(result);
		request.setAttribute("SearchResult", result);
		RequestDispatcher rd = request.getRequestDispatcher("ResultsDisplay.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// For search with/without search refinement wrt price.
		String searchWord = request.getParameter("SearchWord");
		String startPrice = request.getParameter("StartPrice");
		String endPrice = request.getParameter("EndPrice");
		System.out.println(searchWord);
		SearchEngine engine = new SearchEngine();
		ArrayList<ArrayList<Object>> result;
		//if(startPrice == null && endPrice == null)
			result = engine.search(searchWord);
		/*else {
			float sPrice = Float.parseFloat(startPrice);
			float ePrice = Float.parseFloat(endPrice);
			result = engine.search(searchWord, sPrice, ePrice);
		}*/
			System.out.println(result);
		request.setAttribute("SearchResult", result);
		RequestDispatcher rd = request.getRequestDispatcher("ResultsDisplay.jsp");
		rd.forward(request, response);
		
		
		
	}

}
