package swe2013.management;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;
import swe2013.location.Bookings;
import swe2013.location.City;
import swe2013.location.Hotel;
import swe2013.location.Statistic;
import swe2013.user.Hotellier;

import java.util.ArrayList;

public class HotellierManagement extends UserManagement{
	LocationDAO locationDAO;
	
	public HotellierManagement(Hotellier user ){
		locationDAO = new SqlLocationDAO();
		this.session = user;
	}
	
	public ArrayList<Bookings> bookingsForAssignedHotel(){
		return null;
	}
	
	public void createHotel(String name, int[] rooms, double[] costPerNight, String cityname, String countryname){
		Hotel hotel = new Hotel(name,  rooms, costPerNight);
		City city = new City(cityname, countryname);
		locationDAO.saveHotel(hotel, city, this.session.getUserID());
	
		((Hotellier)this.session).setAssignedHotel(hotel);
	}
	
	public void editOwnHotel(String name, int[] rooms, double[] costPerNight, long hotelID){
		Hotel hotel = new Hotel(name,  rooms, costPerNight);
		locationDAO.updateHotel(hotel, hotelID);
	}
	
	public Statistic getStatisticForOwnHotel(){
		return null;
	}
}
