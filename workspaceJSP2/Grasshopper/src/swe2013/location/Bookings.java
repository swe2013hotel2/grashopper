package swe2013.location;

import java.util.ArrayList;
import java.util.Date;

/**
 * Bookings
 * Class for managing booking information - each room stores bookings in a booking object
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class Bookings {
	ArrayList<Date> beginDates=null;
	ArrayList<Date> endDates=null;
	ArrayList<Long> customer=null;
	
	/** 
	 * Constructor, creates the necessary ArrayLists for the booking information
	 */
	public Bookings(){
		beginDates=new ArrayList<Date>();
		endDates=new ArrayList<Date>();
		customer=new ArrayList<Long>();
	}
	
	/**
	 * Creates a new booking for the given time frame
	 * @param customer customer related to the booking
	 * @param beginDate begin date of the booking
	 * @param endDate end date of the booking 
	 * @throws IllegalArgumentException will throw IllegalargumentException if end date < begin date or if time frame not available
	 */
	public void bookForTimeFrame(long customerID, Date beginDate, Date endDate) throws IllegalArgumentException {
		// Check if beginDate < endDate
		
		if (endDate.before(beginDate))
			throw new IllegalArgumentException ("End date before Begin date.");
		//do we already have bookings? if not, add current booking
		if (beginDates.size()==0){
			this.beginDates.add(beginDate);
			this.endDates.add(endDate);
			this.customer.add(customerID);
			return;
		}
		//Timeframe still available?
		if (!this.freeForTimeFrame(beginDate, endDate))
			throw new IllegalArgumentException("Date is not available for booking.");
		//add booking in the right order
		
		for(int i=0; i<this.beginDates.size(); i++)
		{
			Date existingBeginDate = this.beginDates.get(i);
			if(beginDate.after(existingBeginDate))
			{
				if(i+1<this.beginDates.size())
				{
					this.beginDates.add(i+1, beginDate);
					this.endDates.add(i+1, endDate);
					this.customer.add(i+1, customerID);
				}
				else
				{
					this.beginDates.add( beginDate);
					this.endDates.add( endDate);
					this.customer.add( customerID);
				}
			}
			
		}
	}
	
	/**
	 * Checks if a certain time frame is available for booking
	 * @param beginDate begin date of the time frame
	 * @param endDate end date of the time frame
	 * @return boolean true, if time frame is available for booking, false if not
	 * @throws IllegalArgumentException if end date is before the begin date
	 */
	public boolean freeForTimeFrame(Date beginDate, Date endDate) throws IllegalArgumentException{
		if(this.beginDates.size()==0)
			return true;
		
		for(int i=0; i< this.beginDates.size(); i++)
		{
			Date existingBeginDate = this.beginDates.get(i);
			Date existingEndDate = this.endDates.get(i);

			if(beginDate.after(existingBeginDate) && beginDate.before(existingEndDate) 
			 || beginDate.equals(existingBeginDate) && beginDate.equals(existingEndDate))
				return false;
			if(endDate.after(existingBeginDate) && endDate.before(existingEndDate)
			 || endDate.equals(existingBeginDate) && endDate.equals(existingEndDate))
				return false;
			
		}
		return true;
	}
	
	/**
	 * generates a string of the booking information
	 * @return string consisting of the most important booking information
	 */
	public String toString()
	{
		String result ="";
		for(int i=0; i<this.beginDates.size();i++)
		{
			result += "von "+ this.beginDates.get(i).toString()+" bis "+this.endDates.get(i).toString()+"\n";
		}
		return result;
	}
}
