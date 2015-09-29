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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customer_id;

	@Column(name = "customer_name")
	private String name;

	@Column(name = "customer_shippingAddress")
	private String shippingAddress = "N/A";

	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="billingInfo_id")
	private BillingInfo billingInfo;

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Set<Product> shoppingCart = new HashSet<Product>();

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="order_id")
	private Set<Order> orderList = new HashSet<Order>();

	public Customer() {
	}

	public Customer(String name) {
		this.name=name;
	}

	public long getID() {
		return customer_id;
	}

	public void setID(long id) {
		this.customer_id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getAddress() {
		return this.shippingAddress;
	}

	public void setAddress(String address) {
		this.shippingAddress = address;
	}

	public BillingInfo getBillingInfo() {
		return this.billingInfo;
	}

	public void setBillingInfo(BillingInfo bi) {
		this.billingInfo = bi;
	}

	public void addToShoppingCart(Product p) {
		shoppingCart.add(p);
	}

	public void removeFromShoppingCart(Product p) {
		shoppingCart.remove(p);
	}

	public void clearShoppingCart() {
		shoppingCart = new HashSet<Product>();
	}

	public Set<Product> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(Set<Product> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void placeOrder() {
		Order o = new Order(this, shoppingCart);
		orderList.add(o);
		o.place();
		clearShoppingCart();
	}

	public void cancelOrder(Order o) {
		orderList.remove(o);
		o.cancel();
	}

	public Set<Order> getOrderList(){
		return orderList;
	}

	public void setOrderList(Set<Order> orderList){
		this.orderList = orderList;
	}

	public void addOrder(Order o){
		orderList.add(o);
	}

	public void removeOrder(Order o){
		orderList.remove(o);
	}
	@Override
	public String toString() {
		return "Customer:" +
				"\nName: " + name + 
				"\nAddress: " + shippingAddress +
				"\nOrders: " + orderList;
	}
}