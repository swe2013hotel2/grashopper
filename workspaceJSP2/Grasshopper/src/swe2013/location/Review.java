
package swe2013.location;
  

import java.util.ArrayList;

import swe2013.dao.SqlBookingDAO;
import swe2013.dao.SqlDAO;

/**
 * Review
 * Class for managing reviews
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class Review {
	static String insert_Review_Hotel = "INSERT INTO a1201759.Review VALUES(?,?,?,?,?)";
	static String review_City = "UPDATE a1201759.City SET ReviewText=?, Stars=?  WHERE AssignedTA=?";
	static String query_Hotel = "SELECT * FROM a1201759.Review WHERE HOTEL=?";
	static String query_City = "SELECT * FROM a1201759.City WHERE Cityname=? AND Countryname=?";
	
	static String reviews_for_Hotel = "SELECT Text AS reviewtext, stars, username "
			+ "FROM a1201759.Review "
			+ "INNER JOIN a1201759.User ON Author=UID "
			+"WHERE Hotel=?";
	
	static String update_hotel_review = "UPDATE a1201759.Review SET Text=?, Stars=? WHERE ReviewID=?";
	
	static String sql_hotelreviewText ="Text";
	static String sql_city_reviewText = "ReviewText";
	static String sql_stars = "Stars";
	static String sql_cityReviewCreator = "AssignedTA";
	static String sql_hotelReviewCreator = "Author";
	
	static String[] hotelReviewOrder = {sql_hotelreviewText, sql_stars, sql_hotelReviewCreator};
	static String[] cityReviewOrder = {sql_city_reviewText, sql_stars, sql_cityReviewCreator};
	
	static String booked_hotels_and_reviews_for_customer = "SELECT hotelid , hotelname, ReviewID, Stars, Text "
			+"FROM ((SELECT DISTINCT Hotel.HID AS hotelid, hotelName AS hotelname "
			+"FROM (a1201759.Hotel INNER JOIN a1201759.Booking ON Booking.HotelID =Hotel.HID) "
			+"WHERE CLIENT=?) AS bookedHotels "
			+"LEFT JOIN a1201759.Review ON bookedHotels.hotelid=Review.Hotel)";
	
	static String[] reviewOrder = {"hotelid","hotelname","ReviewID", "Stars","Text"};
	static String[] review_order = {"reviewtext","stars","username"};
	
	
	int stars;
	String reviewText;
	String creatorUsername;
	
	/**
	 * generates a new review
	 * @param text the text of the review
	 * @param stars the amount of stars (1-5)
	 * @param username the username of the author
	 */
	public Review(String text, int stars, String username)
	{
		this.reviewText=text;
		this.stars = stars;
		this.creatorUsername=username;
	}

	/**
	 * returns the amount of stars
	 * @return amount of stars (1-5)
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * sets the amount of stars for the review
	 * @param stars the amount of stars (1-5)
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	/**
	 * returns the name of the autor
	 * @return the username of the autor
	 */
	public String getUsername() {
		return this.creatorUsername;
	}

	/**
	 * sets the name of the autor
	 * @param username the username of the autor
	 */
	public void setUsername(String username) {
		this.creatorUsername = username;
	}
	
	/**
	 * returns the text of the review
	 * @return the text of the review
	 */
	public String getReviewText() {
		return reviewText;
	}

	/**
	 * sets the text of the review
	 * @param reviewText the text of the review
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	/**
	 * adds a review for a specific hotel
	 * @param userID the ID of the autor
	 * @param hotelID the ID of the hotel to be reviewed
	 * @param reviewText the text of the review
	 * @param stars the amount of stars for the review (1-5)
	 */
	public static void reviewHotel(long userID, long hotelID, String reviewText, int stars){
		SqlBookingDAO bookingDAO = new SqlBookingDAO(); 
		if(bookingDAO.userBookedHotel(userID, hotelID)){
			Object[] values = {null, stars, reviewText, userID, hotelID};
			SqlDAO.executeQuery(insert_Review_Hotel, values);
		}
	}
	
	/**
	 * allows TA to review their assigned city
	 * @param userID the user ID of the TA
	 * @param reviewText the review text
	 * @param stars the amount of stars (1-5)
	 */
	public static void reviewCity(long userID, String reviewText, int stars){
		Object[] values = {reviewText, stars, userID};
		SqlDAO.executeQuery(review_City, values);
	}
	
	/**
	 * returns all reviews for a specific hotel
	 * @param hotelID the ID of the hotel
	 * @return an ArrayList of all reviews available for that hotel
	 */
	public static ArrayList<Review> getReviewsForHotel(long hotelID){
		Object[] values= {hotelID};
		ArrayList<Review> result = new ArrayList<Review>();
		
		ArrayList<Object[]> reviews = SqlDAO.selectRecordsFromTable(reviews_for_Hotel, values, review_order );
	
		for(int i=0;i<reviews.size();i++){
			Object[] object = reviews.get(i);
			String text = (String)object[0];
			int stars = (Integer)object[1];
			String username = (String)object[2];
		
			result.add(new Review(text, stars, username));
		}
		
		return result;
	}
	
	/**
	 * returns the review for a specific city
	 * @param cityname the name of the city
	 * @param countryname the name of the country the city is in
	 * @return the review of the city
	 */
	public static Review getReviewsForCity(String cityname, String countryname){
		Object[] values= {cityname, countryname};
		
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(query_City, values, cityReviewOrder );
	
		if(results.size()==0)
			return null;
		
		Object[] result = results.get(0);
		
		return new Review((String)result[0], (Integer)result[1],"fjdds");
	}

	/*
	private static Review reviewFromResultSet(Object[] reviewData){
		Review review = new Review();
		review.reviewText = (String)reviewData[0];
		review.stars = (Integer)reviewData[1];
		long userID = (Long)reviewData[2];
		SqlUserDAO userDAO = new SqlUserDAO();
		User user = userDAO.getUserbyID(userID);
		review.creatorUsername = user.getUsername();
		
		return review;
	}
	*/
	
	
	//TODO - ist das wirklich was die funktion macht?
	/**
	 * returns all bookings for a specific user
	 * @param userid the user ID of the user
	 * @return an array of strings describing all bookings
	 */
	public static String[][] usersBookings(long userid)
	{
		Object[] values = {userid};
		
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(booked_hotels_and_reviews_for_customer, values, reviewOrder);
		
		String[][] summaries = new String[results.size()][reviewOrder.length];
		
		for(int i=0; i<results.size();i++)
		{
			Object[] result = results.get(i);
			
			for(int j=0;j<result.length;j++)
			{
				if(result[j]!=null)
					summaries[i][j]=result[j].toString();
				else
					summaries[i][j]="";
			}
		}
		return summaries;
	}
	
	
	/**
	 * Updates a specific review
	 * @param reviewid the ID of the review
	 * @param reviewText the text of the review
	 * @param stars the amount of stars for the review (1-5)
	 */
	public static void updateReview(long reviewid, String reviewText, int stars)
	{
		Object[] values = {reviewText, stars, reviewid};
		
		SqlDAO.executeQuery(update_hotel_review, values);
	}
}
