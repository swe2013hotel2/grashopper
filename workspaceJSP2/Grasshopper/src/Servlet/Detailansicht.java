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
			
			String hotelid = request.getParameter("hotelid");
			String hotelname = request.getParameter("hotelname");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String roomnumber = request.getParameter("roomnumber");
			String roomsize = request.getParameter("roomsize");
			String roomcost = request.getParameter("roomcost");
			String begindate = request.getParameter("begindate");
			String enddate = request.getParameter("enddate");
			
			ArrayList<Review> reviews = Review.getReviewsForHotel(Long.parseLong(hotelid));
			request.setAttribute("reviews", reviews);
			
			request.setAttribute("hotelid", hotelid);
			request.setAttribute("hotelname", hotelname);
			request.setAttribute("city", city);
			request.setAttribute("country", country);
			request.setAttribute("roomnumber", roomnumber);
			request.setAttribute("roomsize", roomsize);
			request.setAttribute("roomcost", roomcost);
			request.setAttribute("begindate", begindate);
			request.setAttribute("enddate", enddate);
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Detailansicht.jsp");
			
			dispatcher.forward(request, response);
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}


}
