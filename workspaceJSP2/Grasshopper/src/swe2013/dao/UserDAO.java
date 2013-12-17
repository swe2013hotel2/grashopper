package swe2013.dao;

import java.util.ArrayList;

import swe2013.user.User;

public interface UserDAO {

	
	ArrayList<User> getUserList() throws IllegalArgumentException;
	
	User getUserbyEmail(String email);
	User getUserbyID(long userID);

	long saveUser(User user) throws IllegalArgumentException;
	

	void deleteUser(User user) throws IllegalArgumentException;
	

	void updateUser(User user) throws IllegalArgumentException;

}
