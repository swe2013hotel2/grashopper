/**
 * 
 */
package swe2013.location;

public class Room {
	long roomNumber=0;
	double costPerNight=0;
	int size=0;
	Bookings bookings=null;
	
	public Room(long roomNumber, double costPerNight, int size){
		this.setRoomNumber(roomNumber);
		this.setCostPerNight(costPerNight);
		this.setSize(size);	
		this.bookings=new Bookings();
	}

	public Room(long roomNumber, double costPerNight, int size, Bookings bookings){
		this.setRoomNumber(roomNumber);
		this.setCostPerNight(costPerNight);
		this.setSize(size);	
		this.setBookings(bookings);
	}
	
	/**
	 * @return the roomNumber
	 */
	public final long getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public final void setRoomNumber(long roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the costPerNight
	 */
	public final double getCostPerNight() {
		return costPerNight;
	}

	/**
	 * @param costPerNight the costPerNight to set
	 */
	public final void setCostPerNight(double costPerNight) {
		this.costPerNight = costPerNight;
	}

	/**
	 * @return the size
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public final void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the bookings
	 */
	public final Bookings getBookings() {
		return bookings;
	}

	/**
	 * @param bookings the bookings to set
	 */
	public final void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}
}