package swe2013.location;


import java.util.ArrayList;

import swe2013.dao.SqlBookingDAO;
import swe2013.dao.SqlDAO;
import swe2013.dao.SqlUserDAO;
import swe2013.user.User;

public class Review {
	static String insert_Review_Hotel = "INSERT INTO a1201759.Review VALUES(?,?,?,?)";
	static String review_City = "UPDATE a1201759.City SET ReviewText=?, Stars=?  WHERE AssignedTA=?";
	static String query_Hotel = "SELECT * FROM a1201759.Review WHERE HOTEL=?";
	static String query_City = "SELECT * FROM a1201759.City WHERE Cityname=? AND Countryname=?";
	
	static String sql_hotelreviewText ="Text";
	static String sql_city_reviewText = "ReviewText";
	static String sql_stars = "Stars";
	static String sql_cityReviewCreator = "AssignedTA";
	static String sql_hotelReviewCreator = "Author";
	
	static String[] hotelReviewOrder = {sql_hotelreviewText, sql_stars, sql_hotelReviewCreator};
	static String[] cityReviewOrder = {sql_city_reviewText, sql_stars, sql_cityReviewCreator};
	
	int stars;
	String reviewText;
	
	
	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}


	String creatorUsername;

	
	public static void reviewHotel(long userID, long hotelID, String reviewText, int stars){
		SqlBookingDAO bookingDAO = new SqlBookingDAO(); 
		if(bookingDAO.userBookedHotel(userID, hotelID)){
			Object[] values = {};
			SqlDAO.executeQuery(insert_Review_Hotel, values);
		}
	}
	
	public static void reviewCity(long userID, String reviewText, int stars){
		Object[] values = {reviewText, stars, userID};
		SqlDAO.executeQuery(review_City, values);
	}
	
	public static ArrayList<Review> getReviewsForHotel(long hotelID){
		Object[] values= {hotelID};
		ArrayList<Review> result = new ArrayList<Review>();
		ArrayList<Object[]> reviews = SqlDAO.selectRecordsFromTable(query_Hotel, values, hotelReviewOrder );
	
		for(int i=0;i<reviews.size();i++)
			result.add(reviewFromResultSet(reviews.get(i)));
		
		return result;
	}
	
	public static Review getReviewsForCity(String cityname, String countryname){
		Object[] values= {cityname, countryname};
		
		Object[] reviewData = SqlDAO.selectRecordsFromTable(query_City, values, cityReviewOrder ).get(0);
	
		Review review=null;
		if(reviewData!=null)
			review=reviewFromResultSet(reviewData);
		return review;
	}

	
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
}
