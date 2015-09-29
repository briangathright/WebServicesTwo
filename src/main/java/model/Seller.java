package model;

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

@Entity
@Table(name = "seller")
public class Seller implements Serializable, IReviewable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private long seller_id;

	@Column(name = "seller_name")
	private String name;

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Set<Product> productList = new HashSet<Product>();
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="review_id")
	private Set<Review> reviewList = new HashSet<Review>();
	
	public Seller() {
	}

	public Seller(String name) {
		this.name=name;
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

	public void addProduct(Product p) {
		productList.add(p);
		p.setSeller(this);
	}
	
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

	public void setReviewList(HashSet<Review> rl) {
		this.reviewList = rl;
	}

	public Set<Review> getReviewList() {
		return reviewList;
	}

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

	@Override
	public String toString() {
		return "Seller:" +
				"\nName: " + name + 
				"\nRating: " + calcAverageRating() + 
				"\nNumber of Reviews: " + reviewList.size() + 
				"\nProduct List:\n" + productList;
	}
}