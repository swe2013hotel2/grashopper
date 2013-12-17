package swe2013.dao;

import java.sql.Date;
import java.util.ArrayList;

import swe2013.location.Bookings;

public class SqlBookingDAO implements BookingDAO {
	
	static String insert_Booking = "INSERT INTO a1201759.Booking VALUES(?,?,?,?,?)";
	static String query_Booking_Room = "SELECT * FROM a1201759.Booking WHERE HotelID=? AND RoomID=? ORDER BY BeginDate ASC";
	static String user_Booked_Hotel = "SELECT * FROM a1201759.Booking WHERE Client=? AND HotelID=?";

	static String bookingClient = "Client";
	static String bookingHotelID= "HotelID";
	static String bookingRoomID = "RoomID";
	static String bookingBeginDate = "BeginDate";
	static String bookingEndDate = "EndDate";
	
	static String[] order = {bookingClient, bookingHotelID, bookingRoomID, bookingBeginDate, bookingEndDate};
	
	@Override
	public void saveBooking(long hotelID, long roomID,long customerID , java.util.Date beginDate,
			java.util.Date endDate) {
		
		long beginTime = beginDate.getTime();
		long endTime = endDate.getTime();
		
		Date sqlBeginDate = new Date(beginTime);
		Date sqlEndDate = new Date(endTime);
		Object[] values = {customerID, hotelID, roomID, sqlBeginDate, sqlEndDate};
		
		SqlDAO.executeQuery(insert_Booking, values);
	}

	@Override
	public Bookings getBookingsForRoom(long hotelID, long roomID) {
		Object[] values = {hotelID, roomID};
		Bookings bookings = new Bookings();
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(query_Booking_Room, values, order);
		
		for(int i=0; i<results.size(); i++){
			Object[] result = results.get(i);
			long beginTime = ((Date)result[3]).getTime();
			long endTime = ((Date)result[4]).getTime();
			
			java.util.Date beginDate = new java.util.Date(beginTime);
			java.util.Date endDate = new java.util.Date(endTime);
			
			bookings.bookForTimeFrame((Long)result[0], beginDate, endDate);
		}
		
		return bookings;
	}

	@Override
	public boolean userBookedHotel(long userID, long hotelID) {
		Object[] values = {userID, hotelID};
		int size = SqlDAO.selectRecordsFromTable(user_Booked_Hotel, values, order).size();
		
		
		return (size > 0);
	}

}
