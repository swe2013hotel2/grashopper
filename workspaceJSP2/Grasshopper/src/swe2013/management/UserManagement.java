package swe2013.management;

import java.util.ArrayList;
import java.util.Date;

import swe2013.dao.SqlUserDAO;
import swe2013.dao.UserDAO;
import swe2013.location.Hotel;
import swe2013.location.Review;
import swe2013.location.Room;
import swe2013.user.User;

public class UserManagement {
	User session;
	UserDAO userDAO;
	
	UserManagement(){
		this.userDAO = new SqlUserDAO();
	}

	
	ArrayList<Hotel>getHotelsWithFreeRoomsWithParameters(String country, String city, int size, float maxPrice, Date beginDate, Date endDate){
		return null;
	}
	
	Room getFreeRoomWithParameters(Hotel hotel, int size, float maxPrice, Date beginDate, Date endDate){
		return null;
	}
	
	int getNumberOfFreeRoomsWithParameters(Hotel hotel, int size, float maxPrice, Date beginDate, Date endDate){
		return 0;
	}
	
	Review getReviewsForHotel(Hotel hotel){
		return null;
	}
	
	public void editOwnAccount(/*params*/){}
	
	public void login(){}
	
	public void logout(){}
	
	public void deleteOwnAccount(){}
	
	public void registerUser(User user){}
}
