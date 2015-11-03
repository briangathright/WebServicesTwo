package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;

import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.dal.ReviewDAO;
import com.zewdiemarket.ws.service.representation.ReviewRepresentation;

public class ReviewActivity {

	public Set<ReviewRepresentation> getReviews() {
		// TODO Auto-generated method stub
		Set<Review> reviews = new HashSet<Review>();
		Set<ReviewRepresentation> reviewReps = new HashSet<ReviewRepresentation>();
		reviews = ReviewDAO.getAllReviews();
		for(Review r : reviews){
			ReviewRepresentation reviewRep = new ReviewRepresentation();
			reviewRep.setID(r.getID());
			reviewRep.setReviewDetail(r.getReviewDetail());
			reviewRep.setRating(r.getRating());
			Customer currentCustomer = r.getCustomer();
			String currentCustomerName = currentCustomer.getName();
			reviewRep.setCustomerName(currentCustomerName);
			reviewReps.add(reviewRep);
		}
		return reviewReps;
	}

	public ReviewRepresentation getReview(String reviewId) {
		Review r =  ReviewDAO.retrieveReview(Long.parseLong(reviewId));
		ReviewRepresentation reviewRep = new ReviewRepresentation();
		reviewRep.setID(r.getID());
		reviewRep.setReviewDetail(r.getReviewDetail());
		Customer currentCustomer = r.getCustomer();
		reviewRep.setRating(r.getRating());
		String currentCustomerName = currentCustomer.getName();
		reviewRep.setCustomerName(currentCustomerName);
		return reviewRep;
	}

	public ReviewRepresentation createReview(String reviewDetail, float rating, Customer customer) {
		Review r = ReviewDAO.addNewReview(reviewDetail, rating, customer);
		ReviewRepresentation reviewRep = new ReviewRepresentation();
		reviewRep.setID(r.getID());
		reviewRep.setReviewDetail(r.getReviewDetail());
		String currentCustomerName = customer.getName();
		reviewRep.setCustomerName(currentCustomerName);
		return reviewRep;
	}

	public String deleteReview(String id) {
		ReviewDAO.deleteReview(ReviewDAO.retrieveReview(Long.parseLong(id)));
		return "OK";
	}
}