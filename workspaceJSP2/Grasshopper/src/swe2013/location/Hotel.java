package swe2013.location;
 
import java.util.ArrayList;
import java.util.Date;

/**
 * Hotel
 * Class for managing hotel information
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class Hotel extends Location{
	
	ArrayList<Room> rooms=null;
	String name;
	long hotelID;
	
	/**
	 * Creates a new empty hotel based only on the name of the hotel
	 * @param name
	 */
	public Hotel(String name) {
		this.setName(name);
		this.rooms=new ArrayList<Room>();
	}
	
	/**
	 * Creates a hotel object by input of the amount of rooms of each category and the prize of each category
	 * @param name the name of the hotel
	 * @param rooms[] the amount of rooms as an array - room[0]=5 would mean 5 one person rooms, room[1]=7 seven two person rooms and so on.
	 * @param costPerNight[] the cost per hotel category, has to be equal to the amount of room categories as defined by the room array
	 * @param hotelID the hotel ID. If left blank, ID will be assigned during SQL save
	 * @throws IllegalArgumentException will check input and throws IllegalArgument exception on wrong arguments. Check exception text for more detail.
	 */
	public Hotel(String name, int[] rooms, double[] costPerNight) throws IllegalArgumentException	{
		//set hotel name
		this.setName(name);
		this.rooms=new ArrayList<Room>();
		
		//check input
		for(int i=0; i<rooms.length; i++) //do we only have positive input for all room sizes?
			if(rooms[i]<0)
				throw new IllegalArgumentException("Number of rooms for each category must be 0 or above.");
		
		for(int i=0; i<costPerNight.length; i++) // do we only have positive costs per room category?
			if(costPerNight[i]<0)
				throw new IllegalArgumentException("Price of eacht room category must be 0 or above.");
		
		if(rooms.length!=costPerNight.length) // do we have the same amount of room categories and prize categories?
			throw new IllegalArgumentException("Number of room categories does not match number of price categories.");
		
		for (int i=0; i<rooms.length; i++)
		{
			for(int j= 0; j<rooms[i]; j++)
				this.rooms.add(new Room((j*i+j), costPerNight[i], i+1));
		}
	}
	
	/**
	 * Creates a hotel object by input of the amount of rooms of each category and the prize of each category
	 * @param name the name of the hotel
	 * @param rooms[] the amount of rooms as an array - room[0]=5 would mean 5 one person rooms, room[1]=7 seven two person rooms and so on.
	 * @param costPerNight[] the cost per hotel category, has to be equal to the amount of room categories as defined by the room array
	 * @param hotelID the hotel ID. If left blank, ID will be assigned during SQL save
	 * @throws IllegalArgumentException will check input and throws IllegalArgument exception on wrong arguments. Check exception text for more detail.
	 */
	public Hotel(String name, int[] rooms, double[] costPerNight, long hotelID) throws IllegalArgumentException	{
		//set hotel name
		this.setName(name);
		this.setHotelID(hotelID);
		this.rooms=new ArrayList<Room>();
		
		//check input
		for(int i=0; i<rooms.length; i++) //do we only have positive input for all room sizes?
			if(rooms[i]<0)
				throw new IllegalArgumentException("Number of rooms for each category must be 0 or above.");
		
		for(int i=0; i<costPerNight.length; i++) // do we only have positive costs per room category?
			if(costPerNight[i]<0)
				throw new IllegalArgumentException("Price of eacht room category must be 0 or above.");
		
		if(rooms.length!=costPerNight.length) // do we have the same amount of room categories and prize categories?
			throw new IllegalArgumentException("Number of room categories does not match number of price categories.");
		
		for (int i=0; i<rooms.length; i++)
		{
			for(int j= 0; j<rooms[i]; j++)
				this.rooms.add(new Room((j*i+j), costPerNight[i], i+1));
		}
	}

	/**
	 * Construct hotel, when Array of rooms is already known
	 * @param name the name of the hotel
	 * @param roomList the ArrayList<room> of all rooms of the hotel
	 * @param hotelID the hotelID of the room
	 * @throws IllegalArgumentException will check arguments and throw exception if arguments are not correct. See exception messages for more details.
	 */
	public Hotel(String name, ArrayList<Room> roomList, long hotelID) throws IllegalArgumentException {
		this.setName(name);
		this.setHotelID(hotelID);
		this.setRooms(roomList);
	}
		
	/**
	 * returns an array list of all rooms in the hotel
	 * @return an ArrayList of all rooms in the hotel
	 */
	public ArrayList<Room> getRooms(){
		return rooms;
	}
	
	/**
	 * books a room in the hotel for a specific timeframe
	 * @param customerID the customer ID of the user booking
	 * @param persons the amount of persons the user is booking for
	 * @param beginDate the arrival date
	 * @param endDate the departure date
	 */
	public void bookRoomForTimeFrame(long customerID, int persons, Date beginDate, Date endDate){
		for(Room room : this.rooms){
			//System.out.println("Trying to book for" + persons + ": " +room.getSize() + ", available: " + room.bookings.freeForTimeFrame(beginDate, endDate) );
			if (room.getSize()==persons && room.getBookings().freeForTimeFrame(beginDate, endDate)){
				room.bookings.bookForTimeFrame(customerID, beginDate, endDate);
				//System.out.println("booked.");
				return;
			}
		}
		throw new IllegalStateException("No rooms available in the hotel for that time frame and amount of persons.");
	}

	/**
	 * returns the number of rooms for each amount of beds
	 * @return an int array consisting of all rooms divided into beds per room categories
	 */
	public int[] getNumberOfRooms(){
		ArrayList<Room> roomList=this.getRooms();
		int maxSize=0;
		for(Room room : roomList)
		{
			if (room.getSize()>maxSize)
				maxSize=room.getSize();
		}
		int[] amountRooms=new int[maxSize];
		for (int i=0; i<maxSize; i++)
			amountRooms[i]=0;
		for (Room room : roomList){
			amountRooms[room.getSize()-1]++;
		}
		return amountRooms;
	}
	
	/**
	 * returns the Prices for each room category
	 * @return an int array of the prices for each room category
	 */
	public int[] getPricesOfRooms(){
		 ArrayList<Room> roomList=this.getRooms();
		 int maxSize=0;
		 for(Room room : roomList){
			 if (room.getSize()>maxSize)
				 maxSize=room.getSize();
		 }
		 int[] amountRooms=new int[maxSize];
		 for (int i=0; i<maxSize; i++)
			 amountRooms[i]=0;
		 for (Room room : roomList){
			 amountRooms[room.getSize()-1]=(int) room.getCostPerNight();
		 }
		 return amountRooms;
	 }
	
	/**
	 * returns the name of the hotel
	 * @return the name of the hotel
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name of the Hotel
	 * @param name the name to set
	 */
	public final void setName(String name) {
		Location.checkInput(name);
		this.name = name;
	}

	/**
	 * Returns the name of the hotel
	 * @return the hotelID
	 */
	public final long getHotelID() {
		return hotelID;
	}

	/**
	 * Sets the ID of the hotel
	 * @param hotelID the hotelID to set
	 */
	public final void setHotelID(long hotelID) {
		this.hotelID = hotelID;
	}

	/**
	 * Sets the rooms of the hotel
	 * @param ArrayList<Room> rooms the rooms to set
	 */
	public final void setRooms(ArrayList<Room> rooms) {
		System.out.println("blaaaaaa");System.out.println("blaaaaaa");System.out.println("blaaaaaa");System.out.println("blaaaaaa");System.out.println("blaaaaaa");
		this.rooms = rooms;
	}
	
	/**
	 * adds a given room to the hotel
	 * @param room the room to add
	 */
	public final void addRoom(Room room){
		rooms.add(room);
	}
	
}
