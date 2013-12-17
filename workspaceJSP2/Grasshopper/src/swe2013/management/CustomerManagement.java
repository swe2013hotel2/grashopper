package swe2013.management;

import java.util.ArrayList;
import java.util.Date;

import swe2013.dao.SqlBookingDAO;
import swe2013.dao.SqlRoomDAO;
import swe2013.location.*;

public class CustomerManagement extends UserManagement{	
	Hotel chosenHotel;
	Room chosenRoom;
	Date chosenBeginDate;
	Date chosenEndDate;
	
	public void reviewHotel(Hotel hotel, int stars, String reviewText){
		Review.reviewHotel(this.session.getUserID(), hotel.getHotelID(), reviewText, stars);
	}
	
	
	public Long bookRoomForTimeFrame(Hotel hotel, Date beginDate, Date endDate){
		SqlRoomDAO roomDAO = new SqlRoomDAO();
		ArrayList<Room> rooms = roomDAO.getRoomsForHotel(hotel.getHotelID());
		
		Room chosenRoom;
		int i=0;
		
		while(i<rooms.size()){
			chosenRoom = rooms.get(i++);
			if(chosenRoom.getBookings().freeForTimeFrame(beginDate, endDate)){
				SqlBookingDAO bookingDAO = new SqlBookingDAO();
				bookingDAO.saveBooking(hotel.getHotelID(), chosenRoom.getRoomNumber(), this.session.getUserID(), beginDate, endDate);
				return chosenRoom.getRoomNumber();
			}
		}
		return null;
	}
}
