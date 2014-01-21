package swe2013.dao;
 
import java.util.ArrayList;

import swe2013.user.*;

/**
 * SQL User DAO
 * Class for storing and retrieving user related information from a sql database
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class SqlUserDAO implements UserDAO {

	static String insert_User_SQL = "INSERT INTO a1201759.User "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	static String query_User_begin ="SELECT * FROM a1201759.User";
	
	static String delete_Query = "DELETE FROM a1201759.User WHERE email=?";
	static String update_User ="UPDATE a1201759.User SET Username=?, firstname=?, lastname=?, email=?, phone=?,zipCode=?,Street=?,City=?,Country=?,Sex=?,password=? WHERE UID=?";

	static String userID = "UID";
	static String username = "Username";
	static String password = "Password";
	static String email = "Email";
	static String firstName = "FirstName";
	static String lastName = "LastName";
	static String street = "Street";
	static String zipCode = "ZIPCode";
	static String city = "City";
	static String country = "Country";
	static String sex = "Sex";
	static String phone = "Phone";
	static String userGroup = "UserGroup";
	
	static String[] standardOrder = {userGroup, username, firstName, lastName, email
										, phone, zipCode, street, city,country
										, sex, password, userID};
	
	public Object[] getUserDataArrayForSQL(User user){
		Object userData[] = new Object[13];

		if (user.getUserClass()==0)
			throw new IllegalArgumentException("Unknown User Type.");
		
		userData[0] = null;
		userData[1] = user.getUsername();
		userData[2] = user.getPassword();
		userData[3] = user.getEmail();
		userData[4] = user.getFirstName();
		userData[5] = user.getLastName();
		userData[6] = user.getStreet();
		userData[7] = user.getZipCode();
		userData[8] = user.getCity();
		userData[9] = user.getCountry() ;
		userData[10] = (Integer)(user.getSex()?1:0) ;
		userData[11] = user.getTelephoneNumber() ;
		userData[12] = user.getUserClass();
		
		return userData;
	}
	
	
	
	@Override
	public void deleteUser(User user) throws IllegalArgumentException {
		Object[] values = new Object[1];
		values[0]=user.getEmail();
		SqlDAO.executeQuery(delete_Query, values);
	}



	@Override
	public ArrayList<User> getUserList() throws IllegalArgumentException {
		String queryString = query_User_begin;
		Object values[] = new Object[0];
		
		ArrayList<User> users = new ArrayList<User>();
		//System.out.print("khksdisdhu");
		
		 ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(queryString, values, standardOrder);
		
		//System.out.print(rs);
		//System.out.print("khksdhisuehfisdhu");

		for(int i=0;i<results.size();i++){		
			users.add(this.userFromResultSet(results.get(i)));
		}
		return users;
	}

	@Override
	public User getUserbyEmail(String email) {
		String queryString = query_User_begin;
		queryString = queryString + " WHERE Email=?";
		Object values[] = new Object[1];
		values[0]=email;
		
		 ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(queryString, values, standardOrder);
		if(results.size()==0)
			return null;

		User user = this.userFromResultSet(results.get(0));

		return user;
	}
	
	public User getUserbyID(long userID) {
		String queryString = query_User_begin;
		queryString = queryString + " WHERE UID=?";
		Object values[] = new Object[1];
		values[0]=userID;
		
		 ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(queryString, values, standardOrder);
		if(results.size()==0) return null;

		User user = null;
		user = this.userFromResultSet(results.get(0));

		return user;
	}
	
	User userFromResultSet(Object[] objects) {
		User user = null;
		Integer userGroup = (Integer)objects[0];
		String username = (String)objects[1];
		String firstName = (String)objects[2];
		String lastName = (String)objects[3];
		String email = (String)objects[4];
		String telephoneNumber = (String) objects[5];
		String zipCodeString = (String)objects[6];//
		String street = (String)objects[7];
		String city = (String)objects[8];
		String country = (String)objects[9];
		boolean sex = ((Integer)objects[10]==1?true:false);;//
		String encryptedPassword = (String)objects[11];
		Long userID = (Long)objects[12];
		
		
		int zipCode = Integer.parseInt(zipCodeString);

			if(userGroup==1){
				user = new Customer(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
			}
			else if(userGroup==2)
			{
				user = new Hotellier(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
			}
			else if(userGroup==3)
			{
				user = new TourismAssociation(username, firstName, lastName, email, telephoneNumber, zipCode, street, city, country, sex, password);
			}
			
			user.setUserID(userID);
			user.setPasswordDirect(encryptedPassword);
		
		return user;
	}

	@Override
	public long saveUser(User user) throws IllegalArgumentException {
		SqlDAO.executeQuery(insert_User_SQL, this.getUserDataArrayForSQL(user));
		String queryString = "SELECT MAX(UID) AS UID FROM a1201759.User";
		
		String[] order = {"UID"};
		Object[] values = {};
		Object[] result = SqlDAO.selectRecordsFromTable(queryString, values, order).get(0);
		long userID = (Long)result[0];
		user.setUserID(userID);
		
		/*
		if(user.getUserClass()==3){
			SqlLocationDAO locationDAO = new SqlLocationDAO();
	
			City city = ((TourismAssociation)user).getAssignedCity();
			if(locationDAO.getTAForCity(city.getName(), city.getCountry())!=null) return 0;
			
			locationDAO.saveCity(city);
			locationDAO.assignTA(city.getName(), city.getCountry(), userID);
		}
		
		else if(user.getUserClass()==2){
			SqlLocationDAO locationDAO = new SqlLocationDAO();
			Hotel hotel = ((Hotellier)user).getAssignedHotel();
			City city = new City(user.getCity(),user.getCountry());
			locationDAO.saveHotel(hotel, city, userID);
		}
		*/
		
		return userID;
	}



	@Override
	public void updateUser(long userid, String username, String firstName,
			String lastName, String email, String telephoneNumber, int zipCode,
			String street, String city, String country, boolean sex,
			String password) throws IllegalArgumentException {
		
	Object[]values = {username, firstName, lastName, email, telephoneNumber,zipCode,street,city,country,(sex==true?1:0),password, userid};
		SqlDAO.executeQuery(update_User, values);
	}

}
