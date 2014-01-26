package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.SqlLocationDAO;
import swe2013.dao.SqlUserDAO;
import swe2013.location.City;
import swe2013.location.Hotel;
import swe2013.user.Hotellier;
import swe2013.user.User;

/**
 * Servlet implementation class Login
 */
@SuppressWarnings("unused")
@WebServlet("/Hotelregistrierung")
public class Hotelregistrierung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hotelregistrierung() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{				
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
			
			if (request.getParameter("password1").equals(request.getParameter("password2"))){
				password = request.getParameter("password1");
			}
			
			int[] rooms 	= new int[2];
			double[] cost 	= new double[2];
			
			String 	hotelname 	= request.getParameter("hotelname");
			rooms[0]= Integer.valueOf(request.getParameter("bed1"));
			rooms[1]= Integer.valueOf(request.getParameter("bed2"));
			cost[0]	= Double.valueOf(request.getParameter("cost1"));
			cost[1]	= Double.valueOf(request.getParameter("cost2"));
			String 	hotelcity	= request.getParameter("city");
			String 	hotelcountry	= request.getParameter("country");
			
			
			SqlUserDAO UserDAO = new SqlUserDAO();
			SqlLocationDAO locationDAO = new SqlLocationDAO();

			Hotel newHotel	= new Hotel(hotelname, rooms, cost);
			City hotellocation = new City(hotelcity, hotelcountry);
			
			User user = UserDAO.getUserbyEmail(email);
			
			if(user!=null)
			{
				response.sendRedirect("Hotellierregistrierung.jsp?message=Registrierung%20fehlgeschlagen:%20Email%20schon%20vergeben"); //error page 
				return;
			}
			user = new Hotellier(username, vorname, nachname, email, phone, zipCode, street, city, country, sex, password, newHotel);
			
			int status = UserDAO.saveUser(user);
			if(status!=0)
			{
				response.sendRedirect("Hotellierregistrierung.jsp?message=Registrierung%20fehlgeschlagen"); //error page 
				return;
			}
			locationDAO.saveHotel(newHotel, hotellocation, user.getUserID());
			
			
			if (user != null)
			{
				HttpSession session = request.getSession();	    
				session.setAttribute("username", user.getFirstName()); 
				session.setAttribute("UserID", user.getUserID());
				session.setAttribute("UserClass", user.getUserClass());
				response.sendRedirect("userLogged.jsp");    		
			}
			        
			else 
				response.sendRedirect("errorPage.jsp?message=Registrierung%20fehlgeschlagen"); //error page 
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}
}


