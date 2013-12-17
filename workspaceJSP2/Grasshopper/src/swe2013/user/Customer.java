package swe2013.user;

public class Customer extends User {

	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephoneNumber
	 * @param zipCode
	 * @param street
	 * @param city
	 * @param country
	 * @param sex
	 * @param password
	 */
	public Customer(String username, String firstName, String lastName, String email, String telephoneNumber, int zipCode, String street, String city, String country, boolean sex, String password) {
		super(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
		// TODO Auto-generated constructor stub
	}
	
	public Integer getUserClass(){
		return 1;	
	}
	
	/**
	 * @return the assignedLocationID
	 */
	public Integer getassignedLocationID() {
		return null;
	}
	


	
}
