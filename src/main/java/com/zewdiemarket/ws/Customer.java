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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Customer: Class that represents customer objects
 */
@Entity
@Table(name = "customer")
@XmlRootElement
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
	
	/**
	 * Hibernate requires an empty constructor
	 */
	public Customer() {
	}
	
	/**
	 * @param name - constructs a customer with a name
	 */
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
	
	/**
	 * Adds a product to the customer's Shopping Cart, which is then changed into
	 * an order, shared with sellers.
	 */
	public void addToShoppingCart(Product p) {
		shoppingCart.add(p);
	}

	/**
	 *Removes an item from the customer's shopping cart if they don't want it to be included 
	 *in the compeleted order
	 */
	public void removeFromShoppingCart(Product p) {
		shoppingCart.remove(p);
	}
	/**
	 * Clears the shopping cart, creating a new empty shopping cart
	 */
	public void clearShoppingCart() {
		shoppingCart = new HashSet<Product>();
	}

	public Set<Product> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(Set<Product> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	/**
	 * Places an order, filled with the items from the customer's shopping cart
	 * Calls Order's place method, marking the order as placed, then clears the shopping cart
	 */
	public void placeOrder() {
		Order o = new Order(this, shoppingCart);
		orderList.add(o);
		o.place();
		clearShoppingCart();
	}

	/**
	 * Cancels an order, and tells the order to be marked as cancelled
	 */
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
	/**
	 * Adds an order to the orderList
	 */
	public void addOrder(Order o){
		orderList.add(o);
	}
	/**
	 * Removes an order to the orderList
	 */
	public void removeOrder(Order o){
		orderList.remove(o);
	}
	/**
	 *toString method that allows us to show that our application works when testing its output in the client
	 */
	@Override
	public String toString() {
		return "Customer:" +
				"\nName: " + name + 
				"\nAddress: " + shippingAddress +
				"\nOrders: " + orderList;
	}
}