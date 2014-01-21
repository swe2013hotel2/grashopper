package swe2013.user;

import swe2013.location.City;

/**
 * TourismAssociation
 * Class representing all attirbutes and methods for TAs
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class TourismAssociation extends User{

	City assignedCity=null;
	
	/**
	 * generates a new TA
	 * @param username the username
	 * @param firstName the first name
	 * @param lastName the last name 
	 * @param email the email address 
	 * @param telephoneNumber the telephone number
	 * @param zipCode the ZIP code
	 * @param street the street
	 * @param city the city
	 * @param country the country
	 * @param sex the sex
	 * @param password the password
	 */
	public TourismAssociation(String username, String firstName, String lastName, String email, String telephoneNumber, int zipCode, String street, String city, String country, boolean sex, String password) {
		super(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
		
		setAssignedCity(new City(city,country));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the assigned city
	 */
	public final City getAssignedCity() {
		return assignedCity;
	}

	/**
	 * @param assignedCity the assigned city to set
	 */
	public final void setAssignedCity(City assignedCity) {
		this.assignedCity = assignedCity;
	}	
	
	/**
	 * returns the user class for the TA (3)
	 */
	public Integer getUserClass(){
		return 3;	
	}
	
}
