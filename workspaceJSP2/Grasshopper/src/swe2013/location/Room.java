package swe2013.location;
 
/**
 * Room
 * Class for managing room information
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class Room {
	long roomNumber=0;
	double costPerNight=0;
	int size=0;
	Bookings bookings=null;
	
	/**
	 * generates a room based on room number, cost per night and number of beds available in that room
	 * @param roomNumber the room number
	 * @param costPerNight the cost per night
	 * @param size the size of the room (amount of beds)
	 */
	public Room(long roomNumber, double costPerNight, int size){
		this.setRoomNumber(roomNumber);
		this.setCostPerNight(costPerNight);
		this.setSize(size);	
		this.bookings=new Bookings();
	}

	/**
	 * generates a room based on room number, cost per night and number of beds available in that room
	 * furthermore adds existing bookings to that room
	 * @param roomNumber the room number
	 * @param costPerNight the cost per night
	 * @param size the size of the room (amount of beds)
	 * @param bookings bookings for that room
	 */
	public Room(long roomNumber, double costPerNight, int size, Bookings bookings){
		this.setRoomNumber(roomNumber);
		this.setCostPerNight(costPerNight);
		this.setSize(size);	
		this.setBookings(bookings);
	}
	
	/**
	 * @return the room number
	 */
	public final long getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the room number to set
	 */
	public final void setRoomNumber(long roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the cost per night
	 */
	public final double getCostPerNight() {
		return costPerNight;
	}

	/**
	 * @param costPerNight the cost per night to set
	 */
	public final void setCostPerNight(double costPerNight) {
		this.costPerNight = costPerNight;
	}

	/**
	 * @return the size (amount of beds)
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * @param size the size (amount of beds) to set
	 */
	public final void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the bookings for the room
	 */
	public final Bookings getBookings() {
		return bookings;
	}

	/**
	 * @param bookings sets bookings for the room
	 */
	public final void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}
}