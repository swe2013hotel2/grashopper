package swe2013.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User
 * Class representing all attirbutes and methods for users
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class User {
	String username;
	String firstName;
	String lastName;	
	String email;
	String telephoneNumber;
	int zipCode;
	String street;
	String city;
	String country;
	boolean sex;
	String password;
	long UserID;
	
	/**
	 * generates a new User
	 * @param username the user name
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
	public User(String username, String firstName,	String lastName, String email, String telephoneNumber, int zipCode, String street,	String city, String country, boolean sex, String password){
		
		this.setUsername(username);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setTelephoneNumber(telephoneNumber);
		this.setStreet(street);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setCountry(country);
		this.setSex(sex);
		this.setPassword(password);
	}
	
	/**
	 * sets the password directly without using the MD5 hashing first
	 * @param password
	 */
	public void setPasswordDirect(String password){
		this.password=password;
	}
	
	/**
	 * Transforms relevant User Data into a String
	 */
	public String toString(){
		String output=	"Username: " + this.getUsername() +
						"Password: " + this.getPassword() +
						"First Name: " + this.getFirstName() +
						"Last Name: " + this.getLastName() +
						"Sex: " + this.getSex() +
						"eMail: " + this.getEmail() + 
						"Telephone Number: " +this.getTelephoneNumber()+
						"Street: " + this.getStreet() +
						"ZIP Code: " + this.getZipCode() +
						"City: " + this.getCity() + 
						"Country: " + this.getCountry();
		return output;
	}

	/**
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}
	
	/**
	 * @return the UserClass
	 */
	public Integer getUserClass() {
		return null;
	}
	
	/**
	 * @return the assignedLocationID
	 */
	public Integer getassignedLocationID() {
		return null;
	}
	
	
	

	/**
	 * @return the first name
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @return the last name
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @return the email address
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @return the telephone number
	 */
	public final String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @return the zip code
	 */
	public final int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the street
	 */
	public final String getStreet() {
		return street;
	}

	/**
	 * @return the city
	 */
	public final String getCity() {
		return city;
	}

	/**
	 * @return the country
	 */
	public final String getCountry() {
		return country;
	}
	
	/**
	 * @return the sex
	 */
	public final boolean getSex() {
		return sex;
	}
	
	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	public long getUserID() {
		return UserID;
	}

	/**
	 * @param username the username to set
	 */
	public final void setUsername(String username) throws IllegalArgumentException {
		try{
			User.checkInput(username);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		this.username=username;
	}

	/**
	 * @param firstName the first name to set
	 */
	public final void setFirstName(String firstName)  throws IllegalArgumentException {
		try{
			User.checkInput(firstName);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		this.firstName=firstName;
	}

	/**
	 * @param lastName the last name to set
	 */
	public final void setLastName(String lastName) throws IllegalArgumentException {
		try{
			User.checkInput(lastName);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		this.lastName=lastName;
	}

	/**
	 * @param email the email address to set
	 */
	public final void setEmail(String email) throws IllegalArgumentException {
		try{
			User.checkInput(email);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		if(!email.contains("@"))
			throw new IllegalArgumentException("eMail Adress seems to be invalid.");
		this.email=email;
	}

	/**
	 * @param telephoneNumber the telephone number to set
	 */
	public final void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @param zipCode the zip code to set
	 */
	public final void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param street the street to set
	 */
	public final void setStreet(String street) throws IllegalArgumentException {
		try{
			User.checkInput(email);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		this.street=street;
	}

	/**
	 * @param city the city to set
	 */
	public final void setCity(String city) throws IllegalArgumentException {
		try{
			User.checkInput(email);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		this.city = city;
	}

	/**
	 * @param country the country to set
	 */
	public final void setCountry(String country) throws IllegalArgumentException {
		try{
			User.checkInput(email);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		this.country = country;
	}

	/**
	 * @param sex the sex to set
	 */
	public final void setSex(boolean sex) {
		this.sex = sex;
	}

	public void setUserID(long userID) {
		UserID = userID;
	}

	/**
	 * Set Password
	 * Will check for Password length and set the MD5 hash of the password
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		//Check for possible SQL insertion and password length
		try{
			User.checkInput(email);
		}catch (IllegalArgumentException exception){
			throw exception;
		}
		if (password.length() < 6)
			throw new IllegalArgumentException("Password must be longer than 6 characters");
		//Convert String to MD5 Hash and save password
		MessageDigest digest=null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {}
		digest.update(password.getBytes(),0,password.length());
		password = new BigInteger(1,digest.digest()).toString(16).toString();
		this.password = password;
	}
	
	
	
	/**
	 * Checks if the argument equals the password
	 * @param password the password to compare with the stored password hash
	 * @return true if password is correct, false if incorrect
	 */
	public final boolean checkPassword(String password){
		MessageDigest digest=null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {}
		digest.update(password.getBytes(),0,password.length());
		password = new BigInteger(1,digest.digest()).toString(16).toString();
		if (password.equals(this.password))
			return true;
		return false;
	}
	
	public String encodePassword(String password){
		MessageDigest digest=null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {}
		digest.update(password.getBytes(),0,password.length());
		password = new BigInteger(1,digest.digest()).toString(16).toString();
		return password;
	}

	public final static void checkInput(String input) throws IllegalArgumentException{
		if (!input.equals(input.replaceAll(";", "")))
		throw new IllegalArgumentException("Input contained illegal characters - possible SQL Insertion?");
	}
	
}
