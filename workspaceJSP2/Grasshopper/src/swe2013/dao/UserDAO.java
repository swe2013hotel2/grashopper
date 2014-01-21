package swe2013.dao;

import java.util.ArrayList;

import swe2013.user.User;

public interface UserDAO {

	/**
	 * returns a list of all registered users
	 * @return an ArrayList of all users registered
	 * @throws IllegalArgumentException if no users are registered
	 */
	ArrayList<User> getUserList() throws IllegalArgumentException;
	
	/**
	 * returns the user registered with the email address
	 * @param email the email address
	 * @return the user registered with the email adress, NULL if no user found
	 */
	User getUserbyEmail(String email);
	/**
	 * returns the user registered with the specified ID
	 * @param userID the user ID of the user
	 * @return the user registered with the specified User ID, NULL if no user found.
	 */
	User getUserbyID(long userID);

	/**
	 * saves a user
	 * @param user the user object of the user to be saved
	 * @return the user ID of the newly saved user
	 * @throws IllegalArgumentException
	 */
	long saveUser(User user) throws IllegalArgumentException;
	
	/**
	 * deletes a user
	 * @param user the user Object of the user to be deleted
	 * @throws IllegalArgumentException
	 */
	void deleteUser(User user) throws IllegalArgumentException;
	
	/**
	 * updates user information, Arguments can be null if no changes are necessary
	 * @param userid the user ID of the user to be updated
	 * @param username the user ID
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param email the email adress of the user
	 * @param telephoneNumber the telephone number of the user
	 * @param zipCode the zip code of the user
	 * @param street the street of the user
	 * @param city the city of the user
	 * @param country the country of the user
	 * @param sex the sex of the user
	 * @param password the password of the user
	 * @throws IllegalArgumentException
	 */
	void updateUser(long userid, String username, String firstName,
			String lastName, String email, String telephoneNumber, int zipCode,
			String street, String city, String country, boolean sex,
			String password) throws IllegalArgumentException;

}
