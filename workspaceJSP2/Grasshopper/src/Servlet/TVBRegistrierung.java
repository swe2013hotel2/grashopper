package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.SqlLocationDAO;
import swe2013.dao.SqlUserDAO;
import swe2013.location.City;
import swe2013.location.Review;
import swe2013.user.TourismAssociation;
import swe2013.user.User;

/**
 * Servlet implementation class TVBRegistrierung
 */
@WebServlet("/TVBRegistrierung")
public class TVBRegistrierung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TVBRegistrierung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			String firstname = request.getParameter("vorname");
			String lastname = request.getParameter("nachname");
			String email = request.getParameter("email");
			String phone = request.getParameter("telefon");
			String street = request.getParameter("strasse");
			String zipCode = request.getParameter("plz");
			String city = request.getParameter("ort");
			String country = request.getParameter("land");
			String citydescription = request.getParameter("ortbeschreibung");
			String cityRating = request.getParameter("rating");
			String password = request.getParameter("passwort");
			String passwordconfirmation = request.getParameter("passwort2");

			if(!password.equals(passwordconfirmation)){
				response.sendRedirect("TVBRegistrierung.jsp?message=Passwoerter%20stimmen%20nicht%20ueberein");
				return;
			}
			
			SqlLocationDAO dao = new SqlLocationDAO();
			Long taID = dao.getTAForCity(city, country);
			
			if(taID!=null){
				response.sendRedirect("TVBRegistrierung.jsp?message=Stadt%20hat%20bereits%20Tourismusverband");
				return;
				
			}
			else{
				SqlUserDAO userDAO = new SqlUserDAO();
				SqlLocationDAO locationDAO = new SqlLocationDAO();
				
				User ta = userDAO.getUserbyEmail(email);
				if(ta!=null)
				{
					response.sendRedirect("TVBRegistrierung.jsp?message=Registrierung%20fehlgeschlagen:%20Email%20schon%20vergeben"); //error page 
					return;
				}
				
				ta = new TourismAssociation( username, firstname, lastname, email, phone, Integer.parseInt(zipCode),street, city, country, (sex.equals("2")?true:false), password);
				int status = userDAO.saveUser(ta);
				if(status!=0)
				{
					response.sendRedirect("TVBRegistrierung.jsp?message=Registrierung%20fehlgeschlagen"); //error page 
					return;
				}
				
				City taCity = new City(city, country);
				locationDAO.saveCity(taCity);
				locationDAO.assignTA(city, country, ta.getUserID());
				Review.reviewCity(ta.getUserID(), citydescription, Integer.parseInt(cityRating));
				
				HttpSession session = request.getSession();
				session.setAttribute("username", ta.getFirstName());
				session.setAttribute("UserID", ta.getUserID());
				session.setAttribute("UserClass", ta.getUserClass());
				
				response.sendRedirect("userLogged.jsp");
			}	    

		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
}
}
