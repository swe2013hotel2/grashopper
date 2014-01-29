package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.SqlUserDAO;
import swe2013.dao.UserDAO;
import swe2013.user.User;

/**
 * Servlet implementation class MyAccount
 */
@WebServlet("/MyAccount")
public class MyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAccount() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			System.out.println("get");
			
			Long userID = (Long) session.getAttribute("UserID");
			
			if(userID==null){
				response.sendRedirect("errorPage.jsp?error=No%20User%20Logged%20in");
				return;
			}
			
			
			SqlUserDAO userDAO = new SqlUserDAO();
	
			User user = userDAO.getUserbyID(userID);
	
			request.setAttribute("username", user.getUsername());
			request.setAttribute("vorname", user.getFirstName());
			request.setAttribute("nachname", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("telephone", user.getTelephoneNumber());
			request.setAttribute("sex", user.getSex());
			request.setAttribute("street", user.getStreet());
			request.setAttribute("zip", user.getZipCode());
			request.setAttribute("city", user.getCity());
			request.setAttribute("country", user.getCountry());
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MyAccount.jsp");
			dispatcher.forward(request, response);
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
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
			//System.out.println(hotellierID);
			
			
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

			
			UserDAO udao = new SqlUserDAO();
			
			User user = udao.getUserbyID(hotellierID);
			
			System.out.println(newPW.equals(""));
			
			if(user.checkPassword(oldPW))
			{
				if( newPW.equals(repeatPW) && !repeatPW.equals("") &&  !newPW.equals(""))
				{

						udao.updateUser(hotellierID, username, vorname,
								nachname, email, phone, zipCode,
								street, city, country, sex,
								user.encodePassword(newPW));
				}
				else if(newPW.equals("") && repeatPW.equals("") ){
					udao.updateUser(hotellierID, username, vorname,
							nachname, email, phone, zipCode,
							street, city, country, sex,
							user.getPassword());
				}
			}
			else
			{
				response.sendRedirect("MyAccount?message=Passwoerter%20nicht%20gleich");
				return;
			}
		
			response.sendRedirect("MyAccount");	
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException);
			response.sendRedirect("errorPage.jsp?message=Unbekannter%20Fehler");
		}
	}
}
