package Servlet;

import java.io.IOException;

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
import swe2013.user.Hotellier;
import swe2013.user.User;

/**
 * Servlet implementation class Hotelupdate
 */
@WebServlet("/Hotelupdate")
public class Hotelupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hotelupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{	
			System.out.println("Hotelupdate");
			HttpSession session = request.getSession();
			long hotellierID = (Long) session.getAttribute("UserID");
			System.out.println(hotellierID);
			
			int[] rooms 		= new int[2];
			double[] cost 		= new double[2];
			//System.out.println("arrayspassn");
			//String 	password	= "";
			
			String 	hotelname 		= request.getParameter("hotelname");
			
			String 	password= "";
			
			String 	username= request.getParameter("username");
			String 	vorname	= request.getParameter("vorname");
			String 	nachname= request.getParameter("nachname");
			String 	email 	= request.getParameter("email");
			String 	phone 	= request.getParameter("telephone");
			boolean	sex		= (Integer.valueOf(request.getParameter("sex"))==1? true:false);
			String 	street	= request.getParameter("street");
			int 	zipCode	= Integer.valueOf(request.getParameter("zip"));
			String 	city	= request.getParameter("city");
			String 	country	= request.getParameter("country");
			
			String oldPW = request.getParameter("oldPW");
			String newPW = request.getParameter("newPW");
			String repeatPW = request.getParameter("repeatPW");
			
			
			if (!newPW.equals(repeatPW))
				throw new IllegalArgumentException("Passwords do not match!");
			
			System.out.println("passt");
			
			LocationDAO ldao = new SqlLocationDAO();
			UserDAO udao = new SqlUserDAO();
			
			User user = udao.getUserbyID(hotellierID);
			
			if(!user.checkPassword(oldPW))
			{
				//anzeigen + errormessage
			}			
			
			udao.updateUser(hotellierID, username, vorname,
					nachname, email, phone, zipCode,
					street, city, country, sex,
					newPW);
			
			Hotel hotel = ldao.getHotelbyOwner(hotellierID);
			
			ldao.updateHotel(hotelname, hotel.getHotelID());
			
			response.sendRedirect("/Hotellieransicht");
			
		}
		catch (Throwable theException) 	    
			{
				System.out.println(theException); 
			}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SqlUserDAO userDAO = new SqlUserDAO();
		SqlLocationDAO locationDAO = new SqlLocationDAO();
		
		HttpSession session = request.getSession();
		long hotellierID = (Long) session.getAttribute("UserID");
		
		User user = userDAO.getUserbyID(hotellierID);
		Hotel hotel = locationDAO.getHotelbyOwner(hotellierID);
		
		request.setAttribute("username", user.getUsername());
		request.setAttribute("vorname", user.getFirstName());
		request.setAttribute("nachname", user.getLastName());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("telephone", user.getEmail());
		request.setAttribute("sex", user.getSex());
		request.setAttribute("street", user.getStreet());
		request.setAttribute("zip", user.getZipCode());
		request.setAttribute("city", user.getCity());
		request.setAttribute("country", user.getCountry());
		
		int[] rooms = hotel.getNumberOfRooms();
		int[] cost = hotel.getPricesOfRooms();
		
		request.setAttribute("hotelname", hotel.getName());
		request.setAttribute("rooms[0]", rooms[0]);
		request.setAttribute("rooms[1]", rooms[1]);
		request.setAttribute("cost[0]", cost[0]);
		request.setAttribute("cost[1]", cost[1]);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Hotellieransicht.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
