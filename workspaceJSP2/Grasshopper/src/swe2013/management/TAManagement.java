package swe2013.management;

import swe2013.dao.LocationDAO;
import swe2013.location.City;
import swe2013.location.Review;
import swe2013.location.Statistic;
import swe2013.user.TourismAssociation;

public class TAManagement extends UserManagement{
	LocationDAO locationDAO;
	//UserDAO userDAO;
	
	public void reviewAssignedCity(int stars, String reviewText){
		Review.reviewCity(this.session.getUserID(), reviewText, stars);
	}
	
	public Review getReviewForAssignedCity(){
		City city = ((TourismAssociation) this.session).getAssignedCity();
		Review review = Review.getReviewsForCity(city.getName(), city.getCountry());
		return review;
	}
	
	public void editAssignedCityReview(int stars, String reviewText){
		Review.reviewCity(this.session.getUserID(), reviewText, stars);
	}
	
	public Statistic statisticForAssignedCity(){
		return null;
	}	
}