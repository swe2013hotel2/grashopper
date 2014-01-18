package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;

/**
 * Servlet implementation class Buchen
 */
@WebServlet("/Buchen")
public class Buchen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buchen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		
		try{
			String hotelname = request.getParameter("hotelname");
			String von = request.getParameter("von");
			String bis = request.getParameter("bis");
			//String kosten = request.getParameter("maxkosten");
			//	String personen = request.getParameter("personen");
			
			@SuppressWarnings("unused")
			LocationDAO locationDao = new SqlLocationDAO();
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			
			@SuppressWarnings("unused")
			Date bd;
			@SuppressWarnings("unused")
			Date ed;
			
			bd = formatter.parse(von);
			ed = formatter.parse(bis);

			Object personen2 = "2";
			Object kosten2 = "100,00";
			Object sterne = "4";
			Object zweibett = "1";
			Object telefon = "07242-12355";
			Object email = "verena@test.com";
			Object strasse = "Strasse 1";
			Object ort = "Linz";
			Object land = "Austria";
			Object plz = "4623";
			
			request.setAttribute("hotelname", hotelname);
			request.setAttribute("von", von);	
			request.setAttribute("bis", bis);
			request.setAttribute("personen2", personen2);
			request.setAttribute("kosten2", kosten2);
			request.setAttribute("sterne", sterne);
			request.setAttribute("zweibett", zweibett);
			request.setAttribute("telefon", telefon);
			request.setAttribute("email", email);
			request.setAttribute("strasse", strasse);
			request.setAttribute("plz", plz);
			request.setAttribute("ort", ort);
			request.setAttribute("land", land);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Buchungsansicht.jsp");
			dispatcher.forward(request, response);
			
			
		}	
		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
		



	    
	    
	    

	    
		
		
		
	}

}
