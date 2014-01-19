/**
 * 
 */
package swe2013.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;

/**
 * The SqlDAO Class handles SQL connection management
 * @author Andreas Kocman (0302840)
 *
 */
public class SqlDAO {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://a1201759.mysql.univie.ac.at/a1201759";
	private static final String DB_USER = "a1201759";
	private static final String DB_PASSWORD = "Eins2345";

	Connection connection=null;
	
	public static int executeQuery(String insertString, Object[] values )
	{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int errorCode=0;
		
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertString);
 
			for(int i= 0; i<values.length;i++ )
			{
				preparedStatement.setObject(i+1, (values[i]==null? Types.NULL : values[i]));
			}
			
			System.out.println(preparedStatement.toString());
 
			int rowsModified = preparedStatement.executeUpdate();
			
			errorCode = (rowsModified !=0 ? 0:1);
			
 
			if(errorCode==0)
				System.out.println("Query Executed");
 
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
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return errorCode; 
	}
	
	public static ArrayList<Object[]> selectRecordsFromTable(String queryString, Object[] values, String[] order) {
		 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		ArrayList<Object[]> resultList = new ArrayList<Object[]>();
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(queryString);
			
			
			for(int i= 0; i<values.length;i++ )
			{
				preparedStatement.setObject(i+1, (values[i]==null? Types.NULL : values[i]));
			}
 
			System.out.println(preparedStatement.toString());
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Object[] result = new Object[order.length];
				for(int i=0; i<order.length;i++){
					result[i] = rs.getObject(order[i]);
				}
				resultList.add(result);
			}

		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultList;
	}
 
	
	
	public SqlDAO()
	{
		// Try to load jdbc Drivers (for Java 1.5 or below)
		try {
		  Class.forName( DB_DRIVER );
		}
		catch ( ClassNotFoundException e ) {
		  e.printStackTrace();
		}
	}
	
	
	/**
	 * Establish a connection to a MySQL database using default parameters
	 */
	private static Connection getDBConnection() {
		 
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
}