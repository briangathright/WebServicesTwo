package com.zewdiemarket.ws;

import java.util.HashSet;
import java.util.Set;
/**
 * Interface that requires methods for every object that is reviewable
 */
public interface IReviewable {
	/**
	 * Function to add a review to a product
	 */
	public void addReview(Review r);
	
	/**
	 * Function to remove a review from a product
	 */
	public void removeReview(Review r);

	public void setReviewList(HashSet<Review> rl);

	public Set<Review> getReviewList();

	/**
	 * returns a double calculated average rating, to improve user experience
	 */
	public double calcAverageRating();
}
