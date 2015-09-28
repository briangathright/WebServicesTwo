package model;

import java.util.HashSet;
import java.util.Set;

public interface IReviewable {

	public void addReview(Review r);

	public void removeReview(Review r);

	public void setReviewList(HashSet<Review> rl);

	public Set<Review> getReviewList();

	public double calcAverageRating();
}
