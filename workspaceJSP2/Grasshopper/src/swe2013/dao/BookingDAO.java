package swe2013.dao;

import java.util.ArrayList;

import swe2013.location.Bookings;

/**
 * Booking DAO
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 * Interface for storing booking data
 */
public interface BookingDAO {
	
	/**
	 * saves a new booking to the SQL DB
	 * @param hotelID the ID of the hotel
	 * @param roomID the ID of the specific room that is booked
	 * @param customerID the ID of the customer booking the room
	 * @param beginDate the date of arrival
	 * @param endDate the date of departure
	 */
	public void saveBooking(long hotelID, long roomID, long customerID, java.util.Date beginDate, java.util.Date endDate);
	
	
	/**
	 * returns all bookings for a room
	 * @param hotelID The ID of the hotel
	 * @param roomID The ID of the room
	 * @return a Bookings Object consisting of all bookings for a specific room
	 */
	public Bookings getBookingsForRoom(long hotelID, long roomID);
	
	/**
	 * Checks if a specific user booked a certain hotel
	 * @param userID the ID of the user
	 * @param hotelID the ID of the hotel
	 * @return true if bookings are available in the db for a specific user, false if not
	 */
	public boolean userBookedHotel(long userID, long hotelID);
	
	public  ArrayList<String[]> bookingsForUser(long userID);
}
