package swe2013.dao;

import java.util.ArrayList;

import swe2013.location.Room;

/**
 * Room DAO
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 * Interface for storing and retrieval of Room data 
 */

public interface RoomDAO {
	/**
	 * generates and saves a new room
	 * @param hotelID the hotel ID of the hotel
	 * @param roomNumber the number of the new room
	 * @param beds the amount of beds in that room
	 * @param price the price per night for the new room
	 */
	public void saveRoom(long hotelID, long roomNumber, int beds, int price);
	/**
	 * deletes a room
	 * @param hotelID the ID of the hotel
	 * @param roomID the ID/room number of the room to be deleted
	 */
	public void deleteRoom(long hotelID, long roomID);
	/**
	 * retrieves all relevant data for a specific room
	 * @param hotelID the ID of the hotel
	 * @param roomID the ID/room number of the room
	 * @return a Room object with all relevant information
	 */
	public Room getRoomByRoomNumber(long hotelID, long roomID);
	/**
	 * retrieves all room information for all rooms of a specific hotel
	 * @param hotelID the ID of the hotel
	 * @return an ArrayList of Room objects with all relevant information
	 */
	public ArrayList<Room> getRoomsForHotel(long hotelID);

}
