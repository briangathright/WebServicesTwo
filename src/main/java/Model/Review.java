package Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private long review_id;

	@Column(name = "rating")
	private float rating;

	@Column(name = "review_detail")
	private String review_detail;

	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="customer_id")
	private Customer customer;	

	public Review() {

	}

	public Review(float rating, Customer customer) {
		this.rating = boundRating(rating);
		this.customer = customer;
	}

	public Review(float rating, Customer customer, String review_detail) {
		this.rating = boundRating(rating);
		this.customer = customer;
		this.review_detail = review_detail;
	}

	public void setID(long id) {
		this.review_id = id;
	}

	public long getID() {
		return review_id;
	}

	public void setRating(float rating) {
		boundRating(rating);
	}

	public float getRating() {
		return rating;
	}

	public void setReviewDetail(String review_detail) {
		this.review_detail = review_detail;
	}

	public String getReviewDetail() {
		return review_detail;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public float boundRating(float rating) {
		if(rating<0) {
			return 0;
		}
		else if(rating>5) {
			return 5;
		}
		return rating;
	}

	@Override
	public String toString() {
		return "Review " + review_id + ":" + 
				"\nCustomer: " + customer.getName() +
				"\nRating: " + rating + 
				"\nDetail: " + review_detail;
	}
}