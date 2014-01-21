package swe2013.user;

/**
 * Customer
 * Class representing all attirbutes and methods for customers
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class Customer extends User {

	/**
	 * @param username the username
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email address
	 * @param telephoneNumber the telephone number
	 * @param zipCode the zip code of the user
	 * @param street the street
	 * @param city the city
	 * @param country the country
	 * @param sex the sex of the person (0 female, 1 male)
	 * @param password the password 
	 */
	public Customer(String username, String firstName, String lastName, String email, String telephoneNumber, int zipCode, String street, String city, String country, boolean sex, String password) {
		super(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * returns the userclass of the person
	 * @return 1 for customer
	 */
	public Integer getUserClass(){
		return 1;	
	}
	
	/**
	 * @return the assigned location ID - will always be null since a customer has no assigned locations
	 */
	public Integer getassignedLocationID() {
		return null;
	}
	


	
}
