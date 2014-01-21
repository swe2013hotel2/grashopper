package swe2013.dao;

import java.util.ArrayList;
import java.util.Date;

import swe2013.location.*;


/**
 * SQL Location DAO
 * Class for storing and retrieving location (city and hotel) related information from a sql database
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class SqlLocationDAO extends SqlDAO implements LocationDAO {

	//--------------------------------------------------------------------------------
	static String insert_City = "INSERT INTO a1201759.City"
									+ " VALUES(?,?,?,?,?)";
	static String query_City_begin ="SELECT * FROM a1201759.City ";
	static String city_for_country_city = "SELECT * FROM a1201759.City WHERE cityname=? AND countryname=? ";
	static String city_for_TA = "SELECT * FROM a1201759.City WHERE AssignedTA=?";
	
	static String delete_City_Query = "DELETE FROM a1201759.City WHERE cityname=? AND countryname=?";
	static String assign_TA_Query = "UPDATE a1201759.City SET AssignedTA=? WHERE Cityname=? AND countryname=?";
	
	static String cityName = "cityname";
	static String cityCountry = "countryname";
	static String cityReviewText = "ReviewText";
	static String cityStars = "Stars";
	static String cityTAID = "AssignedTA";
		
	static String[] cityOrder = {cityName,cityCountry,cityReviewText,cityStars, cityTAID};
	//--------------------------------------------------------------------------------
	static String insert_Hotel_SQL = "INSERT INTO a1201759.Hotel "
									+ " VALUES(?,?,?,?,?)";
	static String delete_hotel = "DELETE FROM a1201759.Hotel WHERE HID=?";
	
	static String hotel_for_hotelID_query ="SELECT * FROM a1201759.Hotel WHERE HID=?";
	static String hotel_for_owner_query ="SELECT * FROM a1201759.Hotel WHERE Owner=?";
	static String hotel_for_location_query ="SELECT * FROM a1201759.Hotel WHERE cityname=? AND countryname=?";
	
	static String update_hotelname = "UPDATE a1201759.Hotel SET HotelName=? WHERE HID=?";
	
	static String hotelID = "HID";
	static String hotelName = "HotelName";
	static String hotelCityName = "CityName";
	static String hotelCountryName = "CountryName";
	static String hotelOwner = "Owner";
		
	static String queryFreeRooms = "SELECT r.RID, r.Hotel FROM a1201759.Room r "
			+ "WHERE r.Hotel=? "
			+ "AND (r.Beds >= ?) "
			+ "AND (r.Price <= ?) "
			+ "AND r.RID "
			+ "NOT IN ( SELECT b.RoomID FROM a1201759.Booking b "
			+ "WHERE NOT (b.EndDate < ?"
			+ "OR b.BeginDate > ?)) ORDER BY Price ASC";
	
	static String queryFreeHotels = "SELECT DISTINCT r.HOTEL FROM a1201759.Room r "
			+ "WHERE (r.Beds >= ?) AND (r.Price <= ?) AND r.RID "
			+ "NOT IN ( SELECT b.RoomID FROM a1201759.Booking b "
			+ "WHERE NOT (b.EndDate < ?"
			+ "OR b.BeginDate > ?))";
	
	static String queryFreeHotelsSummary_begin = "SELECT Hotel AS hotelid,HotelName AS hotelname ,RID AS roomid, cityname, countryname, Price AS roomcost, beds AS roomsize "
			+"FROM (a1201759.Room "
			+"INNER JOIN a1201759.Hotel ON Room.Hotel=Hotel.Hid) "
			+"WHERE ";
			
	static String searchBeds = "(Beds >= ?) AND ";
	static String searchPrice = "(Price <= ?) AND ";
	static String searchCity = "(cityname= ?) AND ";
	static String searchCountry = "(countryname= ?) AND ";
	static String queryFreeHotelsSummary_end = "RID NOT IN ( SELECT b.RoomID FROM a1201759.Booking b "
												+"WHERE NOT (b.EndDate < ? "
												+"OR b.BeginDate > ?)) "
												+"GROUP BY HOTEL "
												+"HAVING MIN(Price)";
										
	 
	
	
	static String[] summaryOrder = {"hotelid","hotelname","roomid","cityname","countryname","roomcost","roomsize"};
	static String[] hotelOrder = {hotelID, hotelName, hotelCityName ,hotelCountryName,hotelOwner};
	//--------------------------------------------------------------------------------

	public long saveHotel(Hotel hotel, City city, long ownerID) {
		saveCity(city);

		String queryString = insert_Hotel_SQL;
		Object[] values = new Object[5];
		values[0]=null;
		values[1]=hotel.getName();
		values[2]=city.getName();
		values[3]=city.getCountry();
		values[4]=ownerID;
		
		SqlDAO.executeQuery(queryString, values);
		values = new Object[0];
		String[] order = {"HID"};
				
		queryString = "SELECT MAX(HID) AS HID FROM a1201759.Hotel";
		Object[] result = SqlDAO.selectRecordsFromTable(queryString, values, order).get(0);
		Long hotelID = (Long)result[0];
		
		RoomDAO roomDAO = new SqlRoomDAO();
		
		for(int i=0; i<hotel.getRooms().size();i++){
			Room room = hotel.getRooms().get(i);
			roomDAO.saveRoom(hotelID, room.getRoomNumber(), room.getSize(), (int)room.getCostPerNight());
		}
		
		hotel.setHotelID(hotelID);
		
		return hotelID;
	}

	
	public void deleteHotel(long hotelID){
		Object[] values ={hotelID};
		
		SqlDAO.executeQuery(delete_hotel, values);
	}
	
	public void updateHotel(Hotel hotel, long hotelID){
		Object[] values = {hotel.getName(),hotelID};
		
		SqlDAO.executeQuery(update_hotelname, values);
	}
	
	public void updateHotel(String newname, long hotelID){
		String queryString = update_hotelname;
		Object[] values = {newname, hotelID};
		
		SqlDAO.executeQuery(queryString, values);
	}

	public void saveCity(City city) {
			//Check if exists
			//if not create
			String queryString = query_City_begin + "WHERE CityName=? AND CountryName=?";
			Object[] values = new Object[2];
			values[0]=city.getName();
			values[1]=city.getCountry();
			
			ArrayList<Object[]>results = SqlDAO.selectRecordsFromTable(queryString, values, cityOrder);
			if(results.size()==0)	//doesnt exist
			{
				queryString = insert_City;
				values = new Object[5];
				values[0]=city.getName();
				values[1]=city.getCountry();
				values[2]=null;
				values[3]=null;
				values[4]=null;
				
				SqlDAO.executeQuery(queryString, values);
			}
	}
	
	public void deleteCity(City city){
		Object[] values ={city.getName(),city.getCountry()};
		
		SqlDAO.executeQuery(delete_City_Query, values);
	}
	
	public Hotel getHotelByID(long hotelID){

		Object[] values = {hotelID};

		
		Object[] hotel = SqlDAO.selectRecordsFromTable(hotel_for_hotelID_query, values, hotelOrder).get(0);
		
		RoomDAO roomDAO = new SqlRoomDAO();
		
		ArrayList<Room> rooms = roomDAO.getRoomsForHotel(hotelID);
		
		return new Hotel((String)hotel[1], rooms, hotelID);
	}

	@Override
	public Hotel getHotelbyOwner(long ownerID) {
		Object[] values = {ownerID};
		
		ArrayList<Object[]> hotels = SqlDAO.selectRecordsFromTable(hotel_for_owner_query, values, hotelOrder);
		if(hotels.size()==0)
			return null;
		
		Object[] hotel = hotels.get(0);
		
		RoomDAO roomDAO = new SqlRoomDAO();
		long hotelID = (Long)hotel[0];
		
		ArrayList<Room> rooms = roomDAO.getRoomsForHotel(hotelID);
		
		return new Hotel((String)hotel[1], rooms, hotelID);
	}
	
	public ArrayList<Hotel> getHotelsFromCity(String cityname, String countryname){
		
		System.out.println("gethotelsfromcity");
		Object[] values = {cityname, countryname};
		
		ArrayList<Hotel> result = new ArrayList<Hotel>();		
		
		ArrayList<Object[]> hotels = SqlDAO.selectRecordsFromTable(hotel_for_location_query, values, hotelOrder);
		RoomDAO roomDAO = new SqlRoomDAO();
		
		for(int i=0; i<hotels.size();i++){
			Object[] hotel = hotels.get(i);
			
			long hotelID = (Long)hotel[0];
			String hotelName = (String)hotel[1]; 
		
			ArrayList<Room> rooms = roomDAO.getRoomsForHotel(hotelID);
		
		result.add(new Hotel(hotelName, rooms, hotelID));
		}
		return result;
	}
	
	public City getCityByAssignedTA(long taID){
		Object[] values = {taID};
		
		Object[] city = SqlDAO.selectRecordsFromTable(city_for_TA, values, cityOrder).get(0);
		
		String cityname = (String)city[0];
		String countryname = (String)city[1];
		
		City result = new City(cityname, countryname);
		
		ArrayList<Hotel> hotels = getHotelsFromCity(cityname, countryname);
		result.setHotels(hotels);
		
		return result;
	}
	
	public Long getTAForCity(String cityname, String countryname){
		Object[] values = {cityname,countryname};
		String queryString = "SELECT AssignedTA FROM a1201759.City WHERE cityname=? AND countryname=?";
		
		
		ArrayList<Object[]> cities = SqlDAO.selectRecordsFromTable(queryString, values, cityOrder);
		
		if(cities == null || cities.size()==0) return null;
		
		Object[] city = cities.get(0);
		
		Long taId = (Long)city[4];
		
		System.out.println("TAID="+taId);
		
		return taId;
	}



	@Override
	public void assignTA(String cityname, String countryname, long taID) {
		if(getTAForCity(cityname, countryname)==null)
		{
			Object[] values = {taID, cityname,countryname};
			SqlDAO.executeQuery(assign_TA_Query, values);	
		}
		else
		{
			//return errormessage
		}
	}


	@Override
	public long[] freeRoomsForTimeFrame(long hotelID, Date beginDate,
			Date endDate , int beds, int price) {
		//hotlid, beds, price, begindate, enddate
		Object[] values = {hotelID, beds, price, toSQLDate(beginDate), toSQLDate(endDate)};
		String[] roomsOrder = {"RID"};
		ArrayList<Object[]> rooms = SqlDAO.selectRecordsFromTable(queryFreeRooms, values, roomsOrder);
		long[] roomIDS = new long[rooms.size()];
		
		for(int i=0;i<rooms.size();i++){
			roomIDS[i]= (Long)rooms.get(i)[0];
		}
		
		return roomIDS;
	}

	private static java.sql.Date toSQLDate(java.util.Date date){
		return new java.sql.Date( date.getTime());
	}



	@Override
	public long[] hotelsWithFreeRooms(Date beginDate, Date endDate, int beds,
			int price) {
		
		Object[] values = { beds, price, toSQLDate(beginDate), toSQLDate(endDate)};
		String[] roomsOrder = {"HOTEL"};
		
		ArrayList<Object[]> rooms = SqlDAO.selectRecordsFromTable(queryFreeHotels, values, roomsOrder);
		long[] hotelIDS = new long[rooms.size()];
		
		for(int i=0;i<rooms.size();i++){
			hotelIDS[i]= (Long)rooms.get(i)[0];
		}
		
		return hotelIDS;
	}
	
	public String[][] freeHotelsSummary(String cityname, String countryname, Date beginDate, Date endDate, Integer beds,
			Integer price)
	{
		
		ArrayList<Object> values = new ArrayList<Object>();
		//Object[] values = {beds,price,cityname,countryname, toSQLDate(beginDate), toSQLDate(endDate)};
		String query = queryFreeHotelsSummary_begin;
		if(beds!=null){
			query+=searchBeds;
			values.add(beds);
		}
		if(price!=null)
		{
			query+=searchPrice;
			values.add(price);
		}
		if(cityname!=null){
			query+=searchCity;
			values.add(cityname);
		}
		if(countryname!=null){
			query+=searchCountry;
			values.add(countryname);
		}
		values.add(toSQLDate(beginDate));
		values.add(toSQLDate(endDate));
		
		query+=queryFreeHotelsSummary_end;
		
		
		
		ArrayList<Object[]> results = SqlDAO.selectRecordsFromTable(query, values.toArray(), summaryOrder);
		
		
		String[][] summaries = new String[results.size()][summaryOrder.length];
		
		for(int i=0; i<results.size();i++)
		{
			Object[] result = results.get(i);
			
			for(int j=0;j<result.length;j++)
			{
				summaries[i][j]=(result[j]==null?"":result[j].toString());
			}
		}
		return summaries;
	}
	
}
