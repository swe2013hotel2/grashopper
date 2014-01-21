package swe2013.location;

import java.util.ArrayList;

/**
 * City
 * Class for managing city information
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class City extends Location{
	String country;
	int CityID;
	ArrayList<Hotel> hotels;
	String name;
	
	/**
	 * Constructor, creates a city based on its name and the name of the country it belongs to
	 * @param name the name of the city
	 * @param country the name of the country the city belongs to
	 */
	public City(String name, String country){
		this.name = name;
		this.country = country;
	}
	
	/**
	 * adds a hotel to the city
	 * @param hotel the hotel to add
	 */
	public void addHotel(Hotel hotel){
		hotels.add(hotel);		
	}
	
	/**
	 * @return the country the city belongs to
	 */
	public final String getCountry() {
		return country;
	}
	
	/**
	 * sets the name of the city
	 * @param name the name of the city
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * returns the name of the city
	 * @return the name of the city
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * sets the country the city belongs to
	 * @param country the country to set
	 */
	public final void setCountry(String country) {
		this.country = country;
	}


	/**
	 * sets the list of hotels in a city
	 * @param hotels the list of hotels to set
	 */
	public final void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

	/**
	 * returns a list of all hotels in the city
	 * @return an ArrayList of hotels
	 */
	public ArrayList<Hotel> getHotels(){
		return hotels;
	}
	
	//public void removeHotel(Hotel hotel){}
}
