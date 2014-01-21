package swe2013.user;

import swe2013.location.Hotel;

/**
 * Hotellier
 * Class representing all attirbutes and methods for hotelliers
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class Hotellier extends User{
	Hotel assignedHotel=null;

	/**
	 * generates a new hotellier
	 * @param username the username
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email address
	 * @param telephoneNumber the telephone number 
	 * @param zipCode the zip code
	 * @param street the street
	 * @param city the city
	 * @param country the country
	 * @param sex the sex (0 female, 1 male)
	 * @param password the password
	 */
	public Hotellier(String username, String firstName, String lastName, String email, String telephoneNumber, int zipCode, String street, String city, String country, boolean sex, String password) {
		super(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
	}

	/**
	 * generates a new hotellier
	 * @param username the username
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email address
	 * @param telephoneNumber the telephone number 
	 * @param zipCode the zip code
	 * @param street the street
	 * @param city the city
	 * @param country the country
	 * @param sex the sex (0 female, 1 male)
	 * @param password the password
	 * @param assignedHotel the assigned hotel
	 */
	public Hotellier(String username, String firstName, String lastName, String email, String telephoneNumber, int zipCode, String street, String city, String country, boolean sex, String password, Hotel assignedHotel) {
		super(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
		this.assignedHotel = assignedHotel;
	}

	
	/**
	 * @return the assigned hotel
	 */
	public final Hotel getAssignedHotel() {
		return assignedHotel;
	}


	/**
	 * @param assignedHotel set the assigned hotel
	 */
	public final void setAssignedHotel(Hotel assignedHotel) {
		this.assignedHotel = assignedHotel;
	}
	
	/**
	 * returns the user class of the hotellier (2)
	 */
	public Integer getUserClass(){
		return 2;	
	}


}
