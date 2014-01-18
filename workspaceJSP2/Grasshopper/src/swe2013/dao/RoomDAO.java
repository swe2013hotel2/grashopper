package swe2013.dao;

import java.util.ArrayList;

import swe2013.location.Bookings;
import swe2013.location.Room;

public interface RoomDAO {
	
	public void saveRoom(long hotelID, long roomNumber, int beds, int price);
	public void deleteRoom(long hotelID, long roomID);
	public Room getRoomByRoomNumber(long hotelID, long roomID);
	public ArrayList<Room> getRoomsForHotel(long hotelID);

}
