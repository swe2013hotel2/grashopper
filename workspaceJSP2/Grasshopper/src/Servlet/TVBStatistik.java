package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;
import swe2013.location.City;
import swe2013.location.Review;
import swe2013.location.Statistic;

/**
 * Servlet implementation class TVBAnsicht2
 */
@WebServlet("/TVBStatistik")
public class TVBStatistik extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TVBStatistik() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String von = request.getParameter("von");
		System.out.println("von" + von);
		String bis = request.getParameter("bis");
		System.out.println("bis" + bis);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserID")==null){
			response.sendRedirect("notvb.jsp");
			return;
		}
		
		int userclass=0;
		if(session.getAttribute("UserClass")==null){
			response.sendRedirect("notvb.jsp");
			return;
		}
		else
		{
			userclass = (Integer) session.getAttribute("UserClass");
		}
		
		if(userclass!=3){
			response.sendRedirect("notvb.jsp");
			return;
		}
		else
		{
		System.out.println(userclass);
		
		long taID = (Long) session.getAttribute("UserID");
		System.out.println(taID);
			
		LocationDAO dao = new SqlLocationDAO();
		
		City city = dao.getCityByAssignedTA(taID);
		System.out.println(city);
		String cityname = city.getName();
		System.out.println(cityname);
		String countryname = city.getCountry();
		System.out.println(countryname);
		
		
		
		Review review = Review.getReviewsForCity(cityname, countryname);
		//System.out.println(review);
		String reviewtext = review.getReviewText();
		System.out.println(reviewtext);
		
		
		int hotelanzahl = city.getHotels().size();
	
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		Date bd = null;
		Date ed = null;
		
		try {
			bd = formatter.parse(von);
			ed = formatter.parse(bis);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("bd" + bd);
		System.out.println("ed" + ed);
		//Date anfang = null;
		//Date ende = null;
		
		//try {
		//	anfang = formatter.parse("13.12.2013");
		//	ende = formatter.parse("25.12.2013");
		//} catch (ParseException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		Statistic statistic2 = new Statistic(city, bd, ed);
		System.out.println(statistic2);
	
		String statistic = statistic2.toString2();
		
		int bookings = statistic2.bookingscounter;
		System.out.println(bookings);
		
		request.setAttribute("cityname", cityname);
		request.setAttribute("countryname", countryname);
		request.setAttribute("reviewtext", reviewtext);
		request.setAttribute("hotelanzahl", hotelanzahl);
		request.setAttribute("bookings", bookings);
		request.setAttribute("statistic", statistic);
		request.setAttribute("bd", von);
		request.setAttribute("ed", bis);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/TVBansichtdetail.jsp");
		dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
