package com.zewdiemarket.ws;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Product: class that represents products that sellers sell and customers place orders for
 */
@Entity
@Table(name = "product")
@XmlRootElement
public class Product implements Serializable, IReviewable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long product_id;

	@Column(name = "detail")
	private String detail;

	@Column(name = "price")
	private double price;

	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="seller_id")
	private Seller seller;

	/**
	 * A list of reviews that customers write for products
	 */
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="review_id")
	private Set<Review> reviewList = new HashSet<Review>();

	/**
	 * Hibernate requires an empty constructor
	 */
	public Product() {

	}
	
	/**
	 * Constructs a product with detail and price
	 * @param detail
	 * @param price
	 */
	public Product(String detail, double price) {
		this.detail = detail;
		this.price = price;
	}

	public long getID() {
		return product_id;
	}

	public void setID(long id) {
		this.product_id=id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail=detail;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double p) {
		this.price = p;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller s) {
		this.seller = s;
	}

	public void addReview(Review r) {
		reviewList.add(r);
	}

	public void removeReview(Review r){
		reviewList.remove(r);
	}

	public void setReviewList(HashSet<Review> rl)  {
		this.reviewList = rl;
	}

	public Set<Review> getReviewList() {
		return reviewList;
	}

	/**
	 * Calculates the average rating that the product has, based on reviews.
	 */
	public double calcAverageRating() {
		if(reviewList.isEmpty()) {
			return 0;
		}
		double total = 0;
		for(Review r : reviewList) {
			total += r.getRating();
		}
		return total/reviewList.size();
	}
	
	/**
	 *toString method that allows us to show that our application works when testing its output in the client
	 */
	@Override
	public String toString() {
		return "Product: " +
				"\nDetail: " + detail + 
				"\nPrice: " + price +
				"\nRating: " + calcAverageRating() + 
				"\nNumber of Reviews: " + reviewList.size();
	}
}