package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Order;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.dal.OrderDAO;
import com.zewdiemarket.ws.dal.ReviewDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.Link;
import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ReviewRepresentation;
import com.zewdiemarket.ws.service.representation.ReviewRequest;

/*
 * Activity for Review - we get, create, and delete Review representations
 */
public class ReviewActivity {

	public Set<ReviewRepresentation> getReviews() {
		// TODO Auto-generated method stub
		Set<Review> reviews = new TreeSet<Review>();
		Set<ReviewRepresentation> reviewReps = new TreeSet<ReviewRepresentation>();
		reviews = ReviewDAO.getAllReviews();
		if(reviews == null){
			return null;
		}
		for(Review r : reviews){
			if(r != null){
				ReviewRepresentation reviewRep = new ReviewRepresentation();
				reviewRep.setID(r.getID());
				reviewRep.setReviewDetail(r.getReviewDetail());
				reviewRep.setRating(r.getRating());
				Customer currentCustomer = r.getCustomer();
				String currentCustomerName = currentCustomer.getName();
				reviewRep.setCustomerName(currentCustomerName);
				reviewReps.add(reviewRep);
			}
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

	public Set<ReviewRepresentation> getSellerReviews(String sellerId){
		Set<Review> reviews = new TreeSet<Review>();
		Set<ReviewRepresentation> reviewReps = new TreeSet<ReviewRepresentation>();
		reviews = ReviewDAO.getSellerReviews(Long.parseLong(sellerId));
		for(Review r : reviews){
			ReviewRepresentation reviewRep = new ReviewRepresentation();
			reviewRep.setID(r.getID());
			reviewRep.setCustomerName(r.getCustomer().getName());
			reviewRep.setRating(r.getRating());
			reviewRep.setReviewDetail(r.getReviewDetail());
			reviewReps.add(reviewRep);
		}
		return reviewReps;
	}
	
	public Set<ReviewRepresentation> getProductReviews(String productId){
		Set<Review> reviews = new TreeSet<Review>();
		Set<ReviewRepresentation> reviewReps = new TreeSet<ReviewRepresentation>();
		reviews = ReviewDAO.getProductReviews(Long.parseLong(productId));
		for(Review r : reviews){
			ReviewRepresentation reviewRep = new ReviewRepresentation();
			reviewRep.setID(r.getID());
			reviewRep.setCustomerName(r.getCustomer().getName());
			reviewRep.setRating(r.getRating());
			reviewRep.setReviewDetail(r.getReviewDetail());
			reviewReps.add(reviewRep);
		}
		return reviewReps;
	}

	public String deleteReview(String id) {
		ReviewDAO.deleteReview(ReviewDAO.retrieveReview(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(ReviewRepresentation reviewRep){

	}

	public String createSellerReview(ReviewRequest reviewRequest) {
		Review r = ReviewDAO.addNewSellerReview(reviewRequest.getSellerId(), reviewRequest.getReviewDetail(), reviewRequest.getRating(), reviewRequest.getCustomerId());
		return "OK";
	}
	public String createProductReview(ReviewRequest reviewRequest) {
		Review r = ReviewDAO.addNewProductReview(reviewRequest.getProductId(), reviewRequest.getReviewDetail(), reviewRequest.getRating(), reviewRequest.getCustomerId());
		return "OK";
	}
}
