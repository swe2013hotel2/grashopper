package swe2013.management;

import java.util.ArrayList;
import java.util.Date;

import swe2013.dao.SqlUserDAO;
import swe2013.dao.UserDAO;
import swe2013.location.Hotel;
import swe2013.location.Review;
import swe2013.location.Room;
import swe2013.user.User;

/**
 * User Management
 * Class for managing activities relevant for all user groups
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class UserManagement {
	User session;
	UserDAO userDAO;
	
	UserManagement(){
		this.userDAO = new SqlUserDAO();
	}

	/**
	 * returns hotels with free rooms based on certain parameters
	 * @param country the name of the country the hotel should be in
	 * @param city the name of the city the hotel should be in
	 * @param size the size of the room necessary
	 * @param maxPrice the highest possible price
	 * @param beginDate date of arrival
	 * @param endDate date of departure
	 * @return an ArrayList of all hotels within these parameters with free rooms
	 */
	ArrayList<Hotel>getHotelsWithFreeRoomsWithParameters(String country, String city, int size, float maxPrice, Date beginDate, Date endDate){
		return null;
	}
	
	/**
	 * returns a free room in a hotel within the given parameters
	 * @param hotel the hotel the room should be in
	 * @param size the size of the room necessary
	 * @param maxPrice the highest possible price
	 * @param beginDate date of arrival
	 * @param endDate date of departure
	 * @return the room
	 */
	Room getFreeRoomWithParameters(Hotel hotel, int size, float maxPrice, Date beginDate, Date endDate){
		return null;
	}
	
	/**
	 * returns the amount of free rooms in a hotel within certain given parameters
	 * @param hotel the hotel the room should be in
	 * @param size the size of the room necessary
	 * @param maxPrice the highest possible price
	 * @param beginDate date of arrival
	 * @param endDate date of departure
	 * @return amount of free rooms within these parameters
	 */
	int getNumberOfFreeRoomsWithParameters(Hotel hotel, int size, float maxPrice, Date beginDate, Date endDate){
		return 0;
	}
	
	
	//TODO - ist die Funktion noch aktuell?
	/**
	 * returns the reviews for the hotel
	 * @param hotel the hotel
	 * @return null obviously?
	 */
	Review getReviewsForHotel(Hotel hotel){
		return null;
	}
	
	public void editOwnAccount(/*params*/){}
	
	public void login(){}
	
	public void logout(){}
	
	public void deleteOwnAccount(){}
	
	public void registerUser(User user){}
}
