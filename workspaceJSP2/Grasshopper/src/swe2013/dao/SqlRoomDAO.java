package swe2013.dao;

import java.util.ArrayList;

import swe2013.location.Bookings;
import swe2013.location.Room;

public class SqlRoomDAO implements RoomDAO{
	static String insertRoom = "INSERT INTO a1201759.Room VALUES(?,?,?,?)";
	static String queryRoom = "SELECT * FROM a1201759.Room WHERE Hotel=? AND RID=?";
	static String queryHotelRooms =  "SELECT * FROM a1201759.Room WHERE Hotel=? ORDER BY price ASC";
	static String deleteRoom = "DELETE FROM a1201759.Room WHERE HOTEL=? AND RID=?";
	
	static String room_ID = "RID";
	static String room_HotelID = "Hotel";
	static String room_Beds = "beds";
	static String room_Price = "price";
	
	static String[] order = {room_ID, room_HotelID, room_Beds, room_Price};
	
	
	public void saveRoom(long hotelID, long roomNumber, int beds, int price){
		Object[] values = {roomNumber, hotelID, beds, price};
		SqlDAO.executeQuery(insertRoom, values);
	}

	public void deleteRoom(long hotelID, long roomID){
		Object[] values = {hotelID, roomID};
		SqlDAO.executeQuery(deleteRoom, values);
	}
	
	public Room getRoomByRoomNumber(long hotelID, long roomID){
		BookingDAO bookingDAO = new SqlBookingDAO();
		Bookings bookings = bookingDAO.getBookingsForRoom(hotelID, roomID);
		
		Object[] values = {hotelID, roomID};
		Object[] result = SqlDAO.selectRecordsFromTable(queryRoom, values, order).get(0);
		Room room = new Room((Long)result[0], (double)(Float)result[3], (Integer)result[2], bookings);
		return room;
	}
	
	public ArrayList<Room> getRoomsForHotel(long hotelID){
		
		Object[] values = {hotelID};
		
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(queryHotelRooms, values, order);
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		for(int i=0; i<results.size();i++){
			Object[] result = results.get(i);
			long roomID = (Long)result[0];
			BookingDAO bookingDAO = new SqlBookingDAO();
			Bookings bookings = bookingDAO.getBookingsForRoom(hotelID, roomID);
			
			Room room = new Room((Long)result[0], (double)(Float)result[3], (Integer)result[2], bookings);
			rooms.add(room);
		}
		return rooms;
	}
}
