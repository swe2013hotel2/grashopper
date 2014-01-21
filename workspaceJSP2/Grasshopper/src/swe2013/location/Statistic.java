package swe2013.location;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Statistic
 * Class for managing statistic information on hotels and cities
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */ 
public class Statistic {
	String nameOfLocation;
	Date statisticBeginDate;
	Date statisticEndDate;
	Date[] dateBookings;
	public int[]  amountBookings;
	public int bookingscounter=0;
	
	/**
	 * generates statistics for a specific city for a specific timeframe
	 * @param city the city
	 * @param beginDate the begin date for the statistic
	 * @param endDate the end date for the statistic
	 */
	public Statistic (City city, Date beginDate, Date endDate){
		this.setStatisticBeginDate(beginDate);
		this.setStatisticEndDate(endDate);
		this.setNameOfLocation(city.getName());  // <<< - CITY hat noch keinen Namen im Objekt
		
		ArrayList<Hotel> hotelList = city.getHotels();
		if (hotelList==null || hotelList.size()==0)
			throw new IllegalArgumentException("No Hotels in that city");
		
		for (Hotel hotel : hotelList){
			this.mergeWithStatistic(new Statistic(hotel, beginDate, endDate));
		}
	}
	
	/**
	 * generates statistics for a specific city for a specific timeframe
	 * @param hotel the hotel
	 * @param beginDate the begin date for the statistic
	 * @param endDate the end date for the statistic
	 */
	public Statistic(Hotel hotel, Date beginDate, Date endDate)
	{
		this.setStatisticBeginDate(beginDate);
		this.setStatisticEndDate(endDate);
		this.setNameOfLocation(hotel.getName());
		
		ArrayList<Room> roomList=hotel.getRooms();
		if (roomList==null || roomList.size()==0)
			throw new IllegalArgumentException("No Rooms in that hotel");
		
		for (Room room : roomList){
			this.mergeWithStatistic(new Statistic(room, beginDate, endDate));
		}
	}
	
	/**
	 * generates statistics for a specific room
	 * @param room the room
	 * @param beginDate the begin date for the statistic
	 * @param endDate the end date for the statistic
	 */
	public Statistic(Room room, Date beginDate, Date endDate){
		this.setStatisticBeginDate(beginDate);
		this.setStatisticEndDate(endDate);
		this.setNameOfLocation(String.valueOf(room.getRoomNumber()));

		ArrayList<Date[]> dateList= this.getDayList(statisticBeginDate, statisticEndDate);
		int i=0;
		dateBookings	= new Date[dateList.size()];
		amountBookings	= new int[dateList.size()];
		for(Date[] day : dateList){
			dateBookings[i]=day[0];
			if (!room.getBookings().freeForTimeFrame(day[0], day[1]))
				amountBookings[i]=1;
			else
				amountBookings[i]=0;
			i++;
		}
	}
	
	/**
	 * merges this statistic with another statistic of a similar entity and an equal timeframe
	 * @param statistic the statistic to merge with
	 */
	private void mergeWithStatistic(Statistic statistic){
		if (this.getStatisticBeginDate() != statistic.getStatisticBeginDate() 
				|| this.getStatisticEndDate()!= statistic.getStatisticEndDate()){
			throw new IllegalArgumentException("Timeframes do not match");
		}
		if (this.amountBookings==null){
			dateBookings	= new Date[statistic.dateBookings.length];
			amountBookings	= new int[statistic.amountBookings.length];
		}
		for(int i=0; i<this.dateBookings.length; i++){
			this.dateBookings[i]=statistic.dateBookings[i];
			this.amountBookings[i]+=statistic.getAmountBookingsByID(i);
			if(statistic.getAmountBookingsByID(i)>0){
				bookingscounter++;
			}
		}	
	}
	
	/**
	 * returns a list of all dates between two specific dates 
	 * @param beginDate the begin date
	 * @param endDate the end date
	 * @return an ArrayList of dates between the two dates
	 */
	private ArrayList<Date[]> getDayList(Date beginDate, Date endDate){ 
		ArrayList<Date[]> dateList = new ArrayList<Date[]>();
		Date[] curDate = new Date[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		do {
			curDate = new Date[2];
		    cal.add(Calendar.DATE, 1);
	        curDate[0]=cal.getTime();
	        cal.add(Calendar.HOUR_OF_DAY, 23);
			curDate[1]=cal.getTime();
	        cal.add(Calendar.HOUR_OF_DAY, -23);
			dateList.add(curDate);
		}while(curDate[1].before(endDate));

		return dateList;
	}

	/**
	 * returns a string of the statistic
	 */
	public String toString(){
		String outputString="";
	
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		for(int i=0; i<dateBookings.length;i++){
			outputString+= formatter.format(dateBookings[i]) + ": " + amountBookings[i] + "\n";
		}
		return outputString;
	}
	
	/**
	 * returns a html code snipplet of the statistic
	 * @return
	 */
	public String toString2(){

		String output2="";
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		for(int i=0; i<dateBookings.length;i++){
			output2+= formatter.format(dateBookings[i]) + ": " + amountBookings[i] + "<br>";
		}
		return output2;
	}
	
	
	/**
	 * @return the statistic begin date
	 */
	public final Date getStatisticBeginDate() {
		return statisticBeginDate;
	}

	/**
	 * @param statisticBeginDate the statistic begin date to set
	 */
	private final void setStatisticBeginDate(Date statisticBeginDate) {
		this.statisticBeginDate = statisticBeginDate;
	}

	/**
	 * @return the statistic end date
	 */
	public final Date getStatisticEndDate() {
		return statisticEndDate;
	}

	/**
	 * @param statisticEndDate the statistic end date to set
	 */
	private final void setStatisticEndDate(Date statisticEndDate) {
		this.statisticEndDate = statisticEndDate;
	}

	/**
	 * @return the date bookings
	 */
	public final Date[] getDateBookings() {
		return dateBookings;
	}
	
	/**
	 * @return the date bookings
	 */
	public final String getDateBookingsByID(int ID) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		return formatter.format(dateBookings[ID]);
	}
	
	/**
	 * @param dateBookings the date bookings to set
	 */
	@SuppressWarnings("unused")
	private final void setDateBookings(Date[] dateBookings) {
		this.dateBookings = dateBookings;
	}

	/**
	 * @return the amount of bookings
	 */
	public final int[] getAmountBookings() {
		return amountBookings;
	}
	
	public final int getAmountBookingsByID(int ID) {
		return amountBookings[ID];
	}

	/**
	 * @param amountBookings the amount of bookings to set
	 */
	@SuppressWarnings("unused")
	private final void setAmountBookings(int[] amountBookings) {
		this.amountBookings = amountBookings;
	}

	/**
	 * @return the name of the location
	 */
	public final String getNameOfLocation() {
		return nameOfLocation;
	}

	/**
	 * @param nameOfLocation the name of the location to set
	 */
	private final void setNameOfLocation(String nameOfLocation) {
		this.nameOfLocation = nameOfLocation;
	}
}
