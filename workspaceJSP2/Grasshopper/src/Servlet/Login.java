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
import swe2013.user.User;

/**
 * Servlet implementation class Login
 */
@SuppressWarnings("unused")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
			String email = request.getParameter("username");
			
			String password = request.getParameter("password");
			
			SqlUserDAO UserDAO = new SqlUserDAO();

			User user = UserDAO.getUserbyEmail(email);
			
			if(user==null)
			{
				System.out.print("dsoifhidshgfds");
				response.sendRedirect("errorPage.jsp?message=User%20existiert%20nicht");
			}
			
			if (user != null && user.checkPassword(password))
			{
				HttpSession session = request.getSession();
				session.setAttribute("UserID",user.getUserID());
				session.setAttribute("UserClass",user.getUserClass());
				session.setAttribute("username", user.getFirstName());

				response.sendRedirect("userLogged.jsp");    		
			}
			        
			else 
				response.sendRedirect("errorPage.jsp?error=Falsches%20Passwort"); //error page 
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
		

	}
}

