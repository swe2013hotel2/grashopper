package swe2013.dao;
 
import java.util.ArrayList;

import swe2013.user.User;

/**
 * User DAO
 * Interface for storing and retrieval of user data 
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public interface UserDAO {

	
	ArrayList<User> getUserList() throws IllegalArgumentException;
	
	User getUserbyEmail(String email);
	User getUserbyID(long userID);

	int saveUser(User user) throws IllegalArgumentException;
	

	void deleteUser(User user) throws IllegalArgumentException;
	

	void updateUser(long userid, String username, String firstName,
			String lastName, String email, String telephoneNumber, int zipCode,
			String street, String city, String country, boolean sex,
			String password) throws IllegalArgumentException;

}
