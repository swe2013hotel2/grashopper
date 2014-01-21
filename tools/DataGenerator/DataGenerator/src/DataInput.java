
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * SWE Projekt Hotel 2 - Datengenerator
 * @author Simon Anreiter, Andreas Kocman, Vicky Dorothy Moser
 *
 */
public class DataInput {

	private static final int NUMBER_OF_PERSONS 		= 1000;
	private static final int PERCENTAGE_HOTELLIER 	= 30;
	private static final int PERCENTAGE_TVB			= 5;
	private static final int NUMBER_OF_ROOMS 		= 2000;
	private static final int NUMBER_OF_REVIEWS 		= 500;
	private static final int NUMBER_OF_BOOKINGS		= 10000;
	
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://a1201759.mysql.univie.ac.at/a1201759";
	private static final String DB_USER = "a1201759";
	private static final String DB_PASSWORD = "Eins2345";

	Connection dbConnection = null;
	private DataGenerator gen = null;
	
	public static void main(String[] args) {
		DataInput singleton = new DataInput();
		singleton.run();
	}
	
	public DataInput(){
		gen = new DataGenerator();
	}
	
	public void run(){
		int amountOfHotelliers 	= NUMBER_OF_PERSONS * PERCENTAGE_HOTELLIER/100;
		System.out.println("Amount Hotellier: " + amountOfHotelliers);
		int amountOfTVB			= NUMBER_OF_PERSONS * PERCENTAGE_TVB/100;
		System.out.println("Amount TVB: " + amountOfTVB);
		int amountOfClients 		= NUMBER_OF_PERSONS-amountOfHotelliers-amountOfTVB;
		System.out.println("Amount Clients: " + amountOfClients);
		
		System.out.println("Opening Connection to DB " + DB_CONNECTION);
		dbConnection = this.getConnection();
		
		gen.generatePersons(amountOfClients, amountOfHotelliers, amountOfTVB);
		
		this.insertEntry("User", gen.generatePersons(amountOfClients, amountOfHotelliers, amountOfTVB));
		this.insertEntry("City", gen.generateCities(amountOfTVB, amountOfClients+amountOfHotelliers+1, amountOfTVB));
		this.insertEntry("Hotel", gen.generateHotels(amountOfHotelliers, amountOfClients+1, amountOfHotelliers));
		this.insertEntry("Room", gen.generateRooms(NUMBER_OF_ROOMS, amountOfHotelliers));
		this.insertEntry("Review", gen.generateReviews(NUMBER_OF_REVIEWS, amountOfHotelliers, amountOfClients));
		this.insertEntry("Booking", gen.generateBookings(NUMBER_OF_BOOKINGS, amountOfHotelliers, NUMBER_OF_ROOMS, amountOfClients));
		
		System.out.println("Closing Connection.");
		this.closeConnection();
		
		System.out.println("Done.");
	}
	
	private void insertEntry (String table, ArrayList<Object[]> entryList){
		int size=entryList.get(0).length;
		
		String preparedStatement="INSERT INTO " + table + " VALUES (";
		for (int i=0; i<size; i++)
			preparedStatement += "?,";
		preparedStatement = preparedStatement.substring(0, preparedStatement.length() - 1) + ")";
		
		int i=0;
		for (Object[] entry : entryList){
			this.executeQuery(preparedStatement, entry);
			i++;
			if (i%10==0)
				System.out.println("Inserted " + i + " entries to " + table + " (Total: " + entryList.size() +")");
		}
		System.out.println("Data in table " + table +" generated!");
	}
	
	private int executeQuery(String insertString, Object[] values )
	{
		PreparedStatement preparedStatement = null;
		int errorCode=0;
		
		try {
			preparedStatement = dbConnection.prepareStatement(insertString);
 
			for(int i= 0; i<values.length;i++ )
			{
				preparedStatement.setObject(i+1, (values[i]==null? Types.NULL : values[i]));
			}
 
			int rowsModified = preparedStatement.executeUpdate();
			errorCode = (rowsModified !=0 ? 0:1);
			
			if(errorCode!=0)
				System.out.println("Query not executed");
 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return errorCode; 
	}

	private Connection getConnection() {
		 
		Connection connection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(
                           DB_CONNECTION, DB_USER,DB_PASSWORD);
			return connection; 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}	
	
	private void closeConnection(){
		if (dbConnection != null) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
