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

import swe2013.dao.SqlUserDAO;
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{	
			int[] rooms 	= new int[2];
			double[] cost 	= new double[2];
			String 	password= "";
			String 	name 	= request.getParameter("hotelname");
			String 	username= request.getParameter("username");
			String 	vorname	= request.getParameter("vorname");
			String 	nachname= request.getParameter("nachname");
			String 	email 	= request.getParameter("email");
			String 	phone 	= request.getParameter("telephone");
			rooms[0]= Integer.valueOf(request.getParameter("bed1"));
			rooms[1]= Integer.valueOf(request.getParameter("bed2"));
			cost[0]	= Double.valueOf(request.getParameter("cost1"));
			cost[1]	= Double.valueOf(request.getParameter("cost2"));
			boolean	sex		= (Integer.valueOf(request.getParameter("sex"))==1? true:false);
			String 	street	= request.getParameter("street");
			int 	zipCode	= Integer.valueOf(request.getParameter("zip"));
			String 	city	= request.getParameter("city");
			String 	country	= request.getParameter("country");
			if (request.getParameter("password1").equals(request.getParameter("password2"))){
				password = request.getParameter("password1");
			}
			
			SqlUserDAO UserDAO = new SqlUserDAO();

			Hotel newHotel	= new Hotel(name, rooms, cost);
			Hotellier user	= new Hotellier(username, vorname, nachname, email, phone, zipCode, street, city, country, sex, password, newHotel);
			user.setAssignedHotel(newHotel);
			UserDAO.saveUser(user);
			
			if (user != null)
			{
				HttpSession session = request.getSession();	    
				session.setAttribute("username", user.getFirstName()); 
				session.setAttribute("UserID", user.getUserID());
				session.setAttribute("UserClass", user.getUserClass());
				response.sendRedirect("userLogged.jsp");    		
			}
			        
			else 
				response.sendRedirect("invalidLogin.jsp"); //error page 
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}


