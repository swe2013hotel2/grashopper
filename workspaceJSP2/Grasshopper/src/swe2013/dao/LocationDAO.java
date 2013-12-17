package swe2013.dao;

import java.util.ArrayList;
import java.util.Date;

import swe2013.location.City;
import swe2013.location.Hotel;


public interface LocationDAO {


	public long saveHotel(Hotel hotel, City city, long ownerID);
	public Hotel getHotelbyOwner(long ownerID);
	public Hotel getHotelByID(long hotelID);
	
	public City getCityByAssignedTA(long taID);
	public void assignTA(String cityname, String countryname, long taID);
	
	public void deleteHotel(long hotelID);
	public void updateHotel(Hotel hotel, long hotelID);
	
	public void saveCity(City city) ;
	public void deleteCity(City city);
	public Long getTAForCity(String cityname, String countryname);
	
	public long[] freeRoomsForTimeFrame(long hotelID, java.util.Date beginDate, java.util.Date endDate, int beds, int price );
	public long[] hotelsWithFreeRooms( java.util.Date beginDate, java.util.Date endDate, int beds, int price);
	public String[][] freeHotelsSummary(String cityname, String countryname, Date beginDate, Date endDate, int beds,
			int price);
}
