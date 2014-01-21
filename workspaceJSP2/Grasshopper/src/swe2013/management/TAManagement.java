package swe2013.management;

import swe2013.dao.LocationDAO;
import swe2013.location.City;
import swe2013.location.Review;
import swe2013.location.Statistic;
import swe2013.user.TourismAssociation;
 
/**
 * Tourism Association Management
 * Class for managing TA related activities
 * @author Anreiter Simon, Moser Victoria Dorothy, Kocman Andreas
 */
public class TAManagement extends UserManagement{
	LocationDAO locationDAO;
	//UserDAO userDAO;
	
	/**
	 * reviews the assigned city
	 * @param stars number of Stars (1-5)
	 * @param reviewText the text of the review
	 */
	public void reviewAssignedCity(int stars, String reviewText){
		Review.reviewCity(this.session.getUserID(), reviewText, stars);
	}
	
	/**
	 * retrieve the review for the assigned city
	 * @return the review for the assigned city
	 */
	public Review getReviewForAssignedCity(){
		City city = ((TourismAssociation) this.session).getAssignedCity();
		Review review = Review.getReviewsForCity(city.getName(), city.getCountry());
		return review;
	}
	
	/**
	 * edits the review of the assigned city
	 * @param stars amount of stars (1-5)
	 * @param reviewText the text of the review
	 */
	public void editAssignedCityReview(int stars, String reviewText){
		Review.reviewCity(this.session.getUserID(), reviewText, stars);
	}
	
	
	//TODO - ist die Funktion noch aktuell?
	/**
	 * returns the statistics for the assigned city
	 * @return null obviously?
	 */
	public Statistic statisticForAssignedCity(){
		return null;
	}	
}