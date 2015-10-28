package com.zewdiemarket.ws;

import java.io.Serializable;
import java.util.Arrays;
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
 * Orders: Class that represents orders that customers place to order products
 */
@Entity
@Table(name="orders")
@XmlRootElement
public class Order implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	long order_id;

	@Column(name="status")
	String status;

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	Set <Product> orderedProducts = new HashSet<Product>();

	/**
	 * Suborders is a set of orders, where the main order is split into 
	 * orders for each seller in the order.
	 */
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="order_id")
	Set <Order> subOrders = new HashSet<Order>();

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	Customer customer;

	/**
	 * Hibernate requires an empty constructor
	 */
	public Order() {

	}
	/**
	 * Constructs an Order object with product list and customer
	 */
	public Order(Customer customer, Set<Product> productList) {
		this.orderedProducts=productList;
		this.customer=customer;
	}

	public long getID(){
		return order_id;
	}

	public void setID(long order_id){
		this.order_id=order_id;
	}


	public Set<Product> getProductList(){
		return orderedProducts;
	}

	public void setProductList(Set<Product> productList){
		this.orderedProducts=productList;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public Customer getCustomer(){
		return customer;
	}

	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	
	/**
	 * 
	 * @return - Checks fulfillment of order by checking that each suborder (described above) is completely fulfilled, and returns
	 * true or false
	 */
	public boolean checkFulfillment() {
		for(Order o : subOrders){
			if(!o.getStatus().equals("DELIEVERED"))
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @return - total cost of an order
	 */
	public double calculateCost() {
		double total = 0.0;
		for(Product p : orderedProducts) {
			total += p.getPrice();
		}
		return total;
	}
	/**
	 * Places an order by setting it's status as PLACED, and creates the seller's suborders, setting their status to PLACED.
	 */
	public void place() {
		status = "PLACED";

		for(Product p : orderedProducts){
			Order o = new Order(customer, new HashSet<Product>(Arrays.asList(p)));
			o.setStatus("PLACED");
			subOrders.add(o);
			p.getSeller().getOrderList().add(o);
		}
	}

	public void ship() {
		status = "SHIPPED";
	}

	public void deliver() {
		status = "DELIVERED";
	}

	public void cancel() {
		status = "CANCELLED";
	}
	
	/**
	 *toString method that allows us to show that our application works when testing its output in the client
	 */
	@Override
	public String toString()
	{
		return "Order:" + 
				"\nProductList:\n" + orderedProducts +
				"\nStatus: " + status;
	}


}