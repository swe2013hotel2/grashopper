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
		HttpSession session = request.getSession();
		System.out.println("get");
		
		Long userID = (Long) session.getAttribute("UserID");
		
		if(userID==null){
			response.sendRedirect("invalidLogin.jsp?error=No%20User%20Logged%20in");
			return;
		}
		
		
		SqlUserDAO userDAO = new SqlUserDAO();

		User user = userDAO.getUserbyID(userID);

		
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
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MyAccount.jsp");
		dispatcher.forward(request, response);
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
			
			if(!user.checkPassword(oldPW))
			{
				response.sendRedirect("MyAccount?status=error&message=Wrong%20Password");
				return;
			}
			
			if(newPW==null && repeatPW==null)
			{
				udao.updateUser(hotellierID, username, vorname,
						nachname, email, phone, zipCode,
						street, city, country, sex,
						user.getPassword());
			}
			
			else if(newPW.equals(repeatPW))
			{
				String encodedPassword = user.encodePassword(newPW);
				udao.updateUser(hotellierID, username, vorname,
						nachname, email, phone, zipCode,
						street, city, country, sex,
						encodedPassword);
			}
			else{
				response.sendRedirect("MyAccount?status=error&message=Passwords%20dont%20match");
				return;
			}
			
			response.sendRedirect("MyAccount");	
		}
		catch (Throwable theException) 	    
			{
				System.out.println(theException); 
			}
	}
}
