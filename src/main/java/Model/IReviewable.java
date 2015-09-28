package Model;

import java.util.HashSet;
import java.util.Set;

public interface IReviewable {

	public void addReview(Review r);

	public void deleteReview(Review r);

	public void setReviewList(HashSet<Review> rl);

	public Set<Review> getReviewList();

	public double calcAverageRating();
}
