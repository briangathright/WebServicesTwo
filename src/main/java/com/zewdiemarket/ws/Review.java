package com.zewdiemarket.ws;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Review: Class that represents reviews that customers write for sellers and products
 */
@Entity
@Table(name = "reviews")
@XmlRootElement
public class Review implements Serializable, Comparable<Review> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private long review_id;

	@Column(name = "rating")
	private double rating;

	@Column(name = "review_detail")
	private String review_detail;

	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="customer_id")
	private Customer customer;	
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Product product;	
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="seller_id")
	private Seller seller;	

	/**
	 * Hibernate requires an empty constructor
	 */
	public Review() {

	}

	/**
	 * Constructs a Review that has rating and customer who wrote the review
	 * @param rating
	 * @param customer
	 */
	public Review(double rating, Customer customer, String review_detail, IReviewable ir) {
		this.rating = boundRating(rating);
		this.customer = customer;
		if(ir.getClass() == Product.class){
			this.product = (Product) ir;
			this.seller = null;
		}
		else{
			this.seller = (Seller) ir;
			this.product = null;
		}
	}

	/**
	 * Constructs a Review that has rating, customer who wrote the review, and a detailed review
	 * @param rating
	 * @param customer
	 * @param review_detail
	 */
	public Review(double rating, Customer customer, String review_detail) {
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

	public void setRating(double rating) {
		this.rating = boundRating(rating);
	}

	public double getRating() {
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
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Seller getSeller() {
		return seller;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	/**
	 * Ensures that ratings are always between 0 and 5, changing outlies to accepted ratings
	 * @param rating
	 * @return
	 */
	public double boundRating(double rating) {
		if(rating<0) {
			return 0;
		}
		else if(rating>5) {
			return 5;
		}
		return rating;
	}
	
	/**
	 *toString method that allows us to show that our application works when testing its output in the client
	 */
	@Override
	public String toString() {
		return "Review:" + 
				"\nCustomer: " + customer.getName() +
				"\nRating: " + rating + 
				"\nDetail: " + review_detail;
	}

	public int compareTo(Review r) {
		return (int) (review_id - r.getID());
	}
}