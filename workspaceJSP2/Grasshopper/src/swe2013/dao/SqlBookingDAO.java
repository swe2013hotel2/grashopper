package swe2013.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import swe2013.location.Bookings;

/**
 * Sql Booking DAO
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 * Class for storing and retrieving booking related information from a sql database
 */
public class SqlBookingDAO implements BookingDAO {
	
	static String insert_Booking = "INSERT INTO a1201759.Booking VALUES(?,?,?,?,?)";
	static String bookings_for_Room_query = "SELECT * FROM a1201759.Booking WHERE HotelID=? AND RoomID=? ORDER BY BeginDate ASC";
	static String user_Booked_Hotel_query = "SELECT * FROM a1201759.Booking WHERE Client=? AND HotelID=?";
	static String bookings_for_User_query =  "SELECT hotelname, roomid, begindate, enddate "
												 +"FROM a1201759.Booking "
												 +"INNER JOIN a1201759.Hotel "
												 +"ON Hotel.HID=Booking.HotelID "
												 +"WHERE Client=? "
												 +"ORDER BY begindate";

	static String bookingClient = "Client";
	static String bookingHotelID= "HotelID";
	static String bookingRoomID = "RoomID";
	static String bookingBeginDate = "BeginDate";
	static String bookingEndDate = "EndDate";
	
	static String[] booking_order = {"hotelname","roomid","begindate","enddate"};
	
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
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(bookings_for_Room_query, values, order);
		
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
		int size = SqlDAO.selectRecordsFromTable(user_Booked_Hotel_query, values, order).size();
		
		return (size > 0);
	}

	public ArrayList<String[]> bookingsForUser(long userID) {
		Object[] values = {userID};
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(bookings_for_User_query, values, booking_order);
		ArrayList<String[]> objects = new ArrayList<String[]>();
		
		for(int i=0; i<results.size(); i++)
		{
			Object[] object = results.get(i);
			String[] result = {object[0].toString(), object[1].toString(), dateToString((Date) object[2]),dateToString( (Date) object[3])};
			objects.add(result);
		}
		
		return objects;
	}
	
	private String dateToString(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		return formatter.format(date);
	}
	
}
