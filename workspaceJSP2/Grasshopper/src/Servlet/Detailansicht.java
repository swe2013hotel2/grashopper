package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2013.location.Review;

/**
 * Servlet implementation class Detailansicht
 */
@WebServlet("/Detailansicht")
public class Detailansicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Detailansicht() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Long hotelid = Long.parseLong(request.getParameter("hotelid"));
	
			
			ArrayList<Review> reviews = Review.getReviewsForHotel(hotelid);
			request.setAttribute("reviews", reviews);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Detailansicht.jsp");
			dispatcher.include(request, response);
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}


}
