package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.SqlBookingDAO;

/**
 * Servlet implementation class MyBookings
 */
@WebServlet("/MyBookings")
public class MyBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBookings() {
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
		
		SqlBookingDAO bdao = new SqlBookingDAO();
		
		ArrayList<String[]> bookings = bdao.bookingsForUser(userID);
		
		request.setAttribute("bookings", bookings);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MyBookings.jsp");
		dispatcher.forward(request, response);
	}

}
