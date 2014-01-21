package swe2013.dao;

import java.util.Date;

import swe2013.location.City;
import swe2013.location.Hotel;

/**
 * Location DAO
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 * Interface for storing and retrieval of location data (hotels and cities)
 */
public interface LocationDAO {

	/**
	 * saves a new hotel
	 * @param hotel the hotel object to be saved
	 * @param city the city the hotel is in
	 * @param ownerID the ID of the account of the owner of the hotel
	 * @return returns the ID of the saved hotel
	 */
	public long saveHotel(Hotel hotel, City city, long ownerID);
	
	/**
	 * retrieves the hotel of a certain hotellier
	 * @param ownerID the user ID of the hotellier
	 * @return a Hotel object representing the hotel data
	 */
	public Hotel getHotelbyOwner(long ownerID);
	/**
	 * retrieves a hotel by its ID
	 * @param hotelID the ID of the hotel
	 * @return a Hotel object representing the hotel data
	 */
	public Hotel getHotelByID(long hotelID);
	
	/**
	 * retrieves a city by the ID of the responsible tourist association (TA)
	 * @param taID the ID of the TA
	 * @return a City object representing the city data
	 */
	public City getCityByAssignedTA(long taID);
	/**
	 * assigns a registered TA to a city
	 * @param cityname the name of the city
	 * @param countryname the name of the country the city is in
	 * @param taID the ID of the TA
	 */
	public void assignTA(String cityname, String countryname, long taID);
	
	/**
	 * deletes a hotel
	 * @param hotelID the ID of a hotel
	 */
	public void deleteHotel(long hotelID);
	/**
	 * updates a hotel identified by its ID
	 * @param hotel a Hotel object containing the updated hotel information
	 * @param hotelID the ID of the hotel
	 */
	public void updateHotel(Hotel hotel, long hotelID);
	/**
	 * updates a hotel name
	 * @param newname the new name of the hotel
	 * @param hotelID the ID of the hotel
	 */
	public void updateHotel(String newname, long hotelID);
	
	/**
	 * saves a new city
	 * @param city a City object containing all relevant data of the city
	 */
	public void saveCity(City city) ;
	/**
	 * deletes a city
	 * @param city the City object of the city to be deleted
	 */
	public void deleteCity(City city);
	/**
	 * retrieves the ID of the TA responsible for a certain city
	 * @param cityname the name of the city
	 * @param countryname the name of the country the city is in
	 * @return the ID of the TA responsible for the city
	 */
	public Long getTAForCity(String cityname, String countryname);
	
	/**
	 * returns all free rooms within a certain time frame in a hotel
	 * @param hotelID the ID of the hotel
	 * @param beginDate the begin of the timeframe/date of arrival
	 * @param endDate the end of the timeframe/date of departure
	 * @param beds number of beds necessary
	 * @param price the highest price
	 * @return array of room IDs free in a certain hotel
	 */
	public long[] freeRoomsForTimeFrame(long hotelID, java.util.Date beginDate, java.util.Date endDate, int beds, int price );
	/**
	 * returns all hotels with free rooms for a certain time frame
	 * @param beginDate the begin of the timeframe/date of arrival
	 * @param endDate the end of the timeframe/date of departure
	 * @param beds number of beds necessary
	 * @param price the highest price
	 * @return array of hotel IDs of hotels with free rooms
	 */
	public long[] hotelsWithFreeRooms( java.util.Date beginDate, java.util.Date endDate, int beds, int price);
	/**
	 * returns a summary of all hotels and rooms within a specific city
	 * @param cityname the name of the city
	 * @param countyname the name of the country the city is in
	 * @param beginDate the begin of the timeframe/date of arrival
	 * @param endDate the end of the timeframe/date of departure
	 * @param beds number of beds necessary
	 * @param price the highest price
	 * @return array of all hotel IDs with free rooms and the room IDs of these rooms 
	 */
	public String[][] freeHotelsSummary(String cityname, String countryname, Date beginDate, Date endDate, Integer beds,
			Integer price);
}
