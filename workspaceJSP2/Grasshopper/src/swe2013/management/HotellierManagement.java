package swe2013.management;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;
import swe2013.location.Bookings;
import swe2013.location.City;
import swe2013.location.Hotel;
import swe2013.location.Statistic;
import swe2013.user.Hotellier;

import java.util.ArrayList;

/**
 * Hotellier Management
 * Class for managing hotellier related activities
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class HotellierManagement extends UserManagement{
	LocationDAO locationDAO;
	
	public HotellierManagement(Hotellier user ){
		locationDAO = new SqlLocationDAO();
		this.session = user;
	}
	
	
	//TODO - ist die funktion noch aktuell?
	/**
	 * retrieves all bookings for the assigned hotel
	 * @return null obviously?
	 */
	public ArrayList<Bookings> bookingsForAssignedHotel(){
		return null;
	}
	
	/**
	 * Creates a new hotel
	 * @param name the name of the hotel
	 * @param rooms the amount of rooms
	 * @param costPerNight the cost per night for each room category
	 * @param cityname the name of the city the hotel is in
	 * @param countryname the name of the country the city is in
	 */
	public void createHotel(String name, int[] rooms, double[] costPerNight, String cityname, String countryname){
		Hotel hotel = new Hotel(name,  rooms, costPerNight);
		City city = new City(cityname, countryname);
		locationDAO.saveHotel(hotel, city, this.session.getUserID());
	
		((Hotellier)this.session).setAssignedHotel(hotel);
	}
	
	/**
	 * edits the hotel data of the assigned hotel
	 * @param name the name of the hotel
	 * @param rooms the amount of rooms per category
	 * @param costPerNight the cost per night per category
	 * @param hotelID the hotel ID of the assigned hotel
	 */
	public void editOwnHotel(String name, int[] rooms, double[] costPerNight, long hotelID){
		Hotel hotel = new Hotel(name,  rooms, costPerNight);
		locationDAO.updateHotel(hotel, hotelID);
	}
	
	//TODO - ist die Funktion noch aktuell?
	
	/**
	 * returns the statistics for the assigned hotel
	 * @return null obviously?
	 */
	public Statistic getStatisticForOwnHotel(){
		return null;
	}
}
