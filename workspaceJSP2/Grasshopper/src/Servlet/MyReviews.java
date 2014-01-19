package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.location.Review;

/**
 * Servlet implementation class MyReviews
 */
@WebServlet("/MyReviews")
public class MyReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviews() {
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
		
		String[][] summaries = Review.usersBookings(userID);
		
		for(int i=0; i<summaries.length;i++){
			for(int j=0; j<summaries[i].length;j++)
				System.out.print(summaries[i][j]);
			System.out.println("");
		}
		request.setAttribute("summaries", summaries);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MyReviews.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("post");
		
		Long userID = (Long)session.getAttribute("UserID");
		Long hotelid = Long.parseLong(request.getParameter("hotelid"));
		String reviewText = request.getParameter("text");
		String reviewIDString = request.getParameter("reviewid");
		int stars = Integer.parseInt(request.getParameter("stars"));
		
		System.out.println("ReviewText "+ reviewText);
		System.out.println("Reviewid "+ reviewIDString);
		System.out.println("hotelid "+ request.getParameter("hotelid"));
		System.out.println("Stars "+ request.getParameter("stars"));
		
		if(reviewIDString.equals(""))
		{
			if(!reviewText.equals("")){
				System.out.println(request.getParameter("kjhgfeiewgfiekhf"));
				Review.reviewHotel(userID, hotelid, reviewText, stars);
			}
		}
		else
		{
			if(!reviewText.equals("")){
				//updaten
				System.out.println(request.getParameter("ksdjkhfiuwshdfhweihiuwehfewekhf"));
				Long reviewid = Long.parseLong(request.getParameter("reviewid"));
				Review.updateReview(reviewid, reviewText, stars);
			}
		}
		response.sendRedirect("MyReviews");
	}

}
