 
package swe2013.location;
 
/**
 * Location
 * Class for managing location (City and Hotel) information
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public abstract class Location {
	//private ArrayList<Review> reviews;

	public Location(){}

	/**
	 * checks input for possible sql insertions
	 * @param input
	 * @throws IllegalArgumentException
	 */
	public final static void checkInput(String input) throws IllegalArgumentException{
		if (!input.equals(input.replaceAll(";", "")))
		throw new IllegalArgumentException("Input contained illegal characters - possible SQL Insertion?");
	}
	
	public abstract String getName();
}
