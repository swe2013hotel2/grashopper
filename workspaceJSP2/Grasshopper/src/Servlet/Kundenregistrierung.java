package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.user.*;
import swe2013.dao.SqlUserDAO;

/**
 * Servlet implementation class Kundenregistrierung
 */
@WebServlet("/Kundenregistrierung")
public class Kundenregistrierung extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kundenregistrierung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String username = request.getParameter("username");
			String anrede = request.getParameter("anrede");
			String vorname = request.getParameter("vorname");
			String nachname = request.getParameter("nachname");
			String email = request.getParameter("email");
			String telefon = request.getParameter("telefon");
			String strasse = request.getParameter("strasse");
			String plz = request.getParameter("plz");
			String ort = request.getParameter("ort");
			String land = request.getParameter("land");
			String password = request.getParameter("passwort");
			String password2 = request.getParameter("passwort2");
			
			if(!password.equals(password2)){
				response.sendRedirect("Kundenregistrierung.jsp?message=Passwoerter%20stimmen%20nicht%20Ÿberein");
			}
			else{
		
				SqlUserDAO UserDAO = new SqlUserDAO();
				
				
				User customer = UserDAO.getUserbyEmail(email);
				if(customer!=null)
				{
					response.sendRedirect("Kundenregistrierung.jsp?message=Registrierung%20fehlgeschlagen:%20Email%20schon%20vergeben"); //error page 
					return;
				}
				
				customer = new Customer(username, vorname, nachname, email, telefon, Integer.parseInt(plz), strasse, ort, land, (anrede.equals("2")?true:false), password);
				int status = UserDAO.saveUser(customer);
				if(status!=0)
				{
					response.sendRedirect("Kundenregistrierung.jsp?message=Registrierung%20fehlgeschlagen"); //error page 
					return;
				}
			    HttpSession session = request.getSession();	    
			    session.setAttribute("username", customer.getFirstName());
			    session.setAttribute("UserId", customer.getUserID());
			    session.setAttribute("UserClass", customer.getUserClass());
			    
			    response.sendRedirect("userLogged.jsp"); //logged-in page      		
			}	
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}

}
