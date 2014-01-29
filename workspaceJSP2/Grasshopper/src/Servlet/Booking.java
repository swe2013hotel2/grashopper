package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.BookingDAO;
import swe2013.dao.SqlBookingDAO;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
        super();
        // TODO Auto-generated constructor stub
    }


/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String von = request.getParameter("begindate");
			String bis = request.getParameter("enddate");
			String hotelid = request.getParameter("hotelid");
			String roomid = request.getParameter("roomnumber");

			HttpSession session = request.getSession();
			Long userid = (Long) session.getAttribute("UserID");
			
			System.out.println("Userid: "+userid);
			System.out.println("von: "+von);
			System.out.println("bis: "+bis);
			System.out.println("hotelid: "+hotelid);
			System.out.println("roomid: "+roomid);
			
			if(userid==null|| von==null || bis==null||hotelid==null||roomid==null)
			{	
				response.sendRedirect("errorPage.jsp?message=Buchung%20Fehlgeschlagen");
				return;
			}
			
			BookingDAO bookingDAO = new SqlBookingDAO();
			//RoomDAO roomDAO = new SqlRoomDAO();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			
			Date bd;
			Date ed;
			
			bd = formatter.parse(von);
			ed = formatter.parse(bis);
			
			bookingDAO.saveBooking(Long.parseLong(hotelid), Long.parseLong(roomid), userid, bd, ed);
			
			response.sendRedirect("succesPage.jsp?message=Buchung%20erfolgreich");
			
		}
		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
			response.sendRedirect("errorPage.jsp?message=Buchung%20Fehlgeschlagen");
		}
	}

}
