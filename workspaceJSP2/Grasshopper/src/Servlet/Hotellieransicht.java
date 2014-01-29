package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;
import swe2013.dao.SqlUserDAO;
import swe2013.dao.UserDAO;
import swe2013.location.Hotel;
import swe2013.location.Review;
import swe2013.user.User;

/**
 * Servlet implementation class Hotellieransicht
 */
@WebServlet("/Hotellieransicht")
public class Hotellieransicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hotellieransicht() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		try
		{	
			System.out.println("Post");
			HttpSession session = request.getSession();
			long hotellierID = (Long) session.getAttribute("UserID");
			System.out.println(hotellierID);
			
			
			String 	hotelname 		= request.getParameter("hotelname");
			
			SqlLocationDAO ldao = new SqlLocationDAO();
			
			Hotel hotel = ldao.getHotelbyOwner(hotellierID);
			
			ldao.updateHotel(hotelname, hotel.getHotelID());

			

			response.sendRedirect("Hotellieransicht");

			
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			System.out.println("get");
			
			if(session.getAttribute("UserID")==null){
				response.sendRedirect("errorPage.jsp?message=Nicht%20eingeloggt");
				return;
			}
			
			int userclass=0;
			if(session.getAttribute("UserClass")==null){
				response.sendRedirect("errorPage.jsp?message=Nicht%20eingeloggt");
				return;
			}
			else
			{
				userclass = (Integer) session.getAttribute("UserClass");
			}
			
			if(userclass!=2){
				response.sendRedirect("errorPage.jsp?message=Sie%20sind%20kein%20Hotellier");
				return;
			}
			
			else
			{
				SqlLocationDAO locationDAO = new SqlLocationDAO();
				
				long hotellierID = (Long) session.getAttribute("UserID");
				Hotel hotel = locationDAO.getHotelbyOwner(hotellierID);
				
				ArrayList<Review> reviews = Review.getReviewsForHotel(hotel.getHotelID());
				
				int[] rooms = hotel.getNumberOfRooms();
				int[] cost = hotel.getPricesOfRooms();
				
				request.setAttribute("hotelname", hotel.getName());
				request.setAttribute("oneBedRoom", rooms[0]);
				request.setAttribute("twoBedRoom", rooms[1]);
				request.setAttribute("priceOneBedRoom", cost[0]);
				request.setAttribute("priceTwoBedRoom", cost[1]);
				request.setAttribute("reviews", reviews);
				
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Hotellieransicht.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}
}
