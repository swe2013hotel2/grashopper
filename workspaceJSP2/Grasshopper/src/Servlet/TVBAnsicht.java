package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.location.City;
import swe2013.location.Hotel;
import swe2013.location.Room;
import swe2013.location.Statistic;
import swe2013.dao.LocationDAO;
import swe2013.dao.RoomDAO;
import swe2013.dao.SqlLocationDAO;
import swe2013.dao.SqlRoomDAO;
import swe2013.dao.SqlUserDAO;
import swe2013.location.Review;
import swe2013.user.TourismAssociation;
import swe2013.user.User;

/**
 * Servlet implementation class TVBAnsicht
 */
@SuppressWarnings("unused")
@WebServlet("/TVBAnsicht")
public class TVBAnsicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TVBAnsicht() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			
			if(session.getAttribute("UserID")==null){
				response.sendRedirect("errorPage.jsp?message=Nicht%20eingeloggt");
				return;
			}
			
			if((Integer)session.getAttribute("UserClass")!=3){
				response.sendRedirect("errorPage.jsp?message=Kein%20Tourismusverband");
				return;
			}
			
			long taID = (Long) session.getAttribute("UserID");
			System.out.println(taID);
				
			LocationDAO dao = new SqlLocationDAO();
			
			City city = dao.getCityByAssignedTA(taID);
			
			String cityname = city.getName();
			String countryname = city.getCountry();
			int hotelanzahl = city.getHotels().size();
			
			Review review = Review.getReviewsForCity(cityname, countryname);
			String reviewtext = review.getReviewText();
	
			String beginDateString = request.getParameter("von");
			String endDateString = request.getParameter("bis");
			
			Date beginDate=null;
			Date endDate=null;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			
			if(beginDateString==null || beginDateString.equals("") || endDateString==null || endDateString.equals(""))
			{
				//If no Date specified last month is presented
				endDate = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.MONTH, -1);
				beginDate = cal.getTime();
			}
			else
			{
				try {
					beginDate = formatter.parse(beginDateString);
					endDate = formatter.parse(endDateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			Statistic statistic = new Statistic(city, beginDate, endDate);
			
			request.setAttribute("cityname", cityname);
			request.setAttribute("countryname", countryname);
			request.setAttribute("reviewtext", reviewtext);
			request.setAttribute("hotelanzahl", hotelanzahl);
			request.setAttribute("von", formatter.format(beginDate) );
			request.setAttribute("bis", formatter.format(endDate));
			request.setAttribute("statistic", statistic);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/TVBansichtdetail.jsp");
			dispatcher.forward(request, response);
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}
	
}