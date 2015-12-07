package com.zewdiemarket.ws;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Seller: Class that represents sellers, who create accounts on our marketplace and sell products
 */
@Entity
@Table(name = "seller")
@XmlRootElement
public class Seller implements Serializable, IReviewable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private long seller_id;

	@Column(name = "seller_name")
	private String name;
	
	@Column(name = "seller_password")
	private String password;

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Set<Product> productList = new HashSet<Product>();

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="review_id")
	private Set<Review> reviewList = new HashSet<Review>();

	/**
	 * Orderlist is the list of orders that have been placed to the seller from customers
	 */
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="order_id")
	private Set<Order> orderList = new HashSet<Order>();

	public Seller() {
	}

	public Seller(String name, String password) {
		this.name=name;
		this.password = password;
	}

	public long getID() {
		return seller_id;
	}

	public void setID(long id) {
		this.seller_id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password=password;
	}

	/**
	 * Adds a product to the seller's product list
	 */
	public void addProduct(Product p) {
		productList.add(p);
		p.setSeller(this);
	}

	/**
	 * Removes product from seller's product list
	 */
	public void removeProduct(Product p) {
		productList.remove(p);
	}

	public void setProductList(HashSet<Product> pl) {
		this.productList = pl;
	}

	public Set<Product> getProductList() {
		return productList;
	}


	public void addReview(Review r) {
		reviewList.add(r);
	}

	public void removeReview(Review r){
		reviewList.remove(r);
	}

	public Set<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(HashSet<Review> rl) {
		this.reviewList = rl;
	}


	public Set<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}

	/**
	 * Allows sellers to fulfill orders by shipping placed orders
	 */
	public void fulfillOrder(Order o) {
		if(o.getStatus().equals("PLACED")){
			o.ship();
		}
	}

	/**
	 * Calculates the average ratings of a seller based on reviews
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
		return "Seller:" +
				"\nName: " + name + 
				"\nRating: " + calcAverageRating() + 
				"\nNumber of Reviews: " + reviewList.size() + 
				"\nProduct List:\n" + productList;
	}
}