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
public class Order implements Comparable<Order>, Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	long order_id;

	@Column(name="status")
	String status;

	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	Product orderedProduct;
	
	@Column(name="product_name")
	String productName;

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
	public Order(Customer customer, Product product) {
		this.orderedProduct=product;
		this.productName = orderedProduct.getDetail();
		this.customer=customer;
	}

	public long getID(){
		return order_id;
	}

	public void setID(long order_id){
		this.order_id=order_id;
	}


	public Product getOrderedProduct(){
		return orderedProduct;
	}

	public void setOrderProduct(Product product){
		this.orderedProduct=product;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductName(String name){
		this.productName = name;
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
		if(!this.getStatus().equals("DELIEVERED"))
		{
			return false;
		}

		return true;
	}
	/**
	 * 
	 * @return - total cost of an order
	 */
	public double calculateCost() {
		double total = 0.0;
		total += orderedProduct.getPrice();
		return total;
	}
	/**
	 * Places an order by setting it's status as PLACED, and creates the seller's suborders, setting their status to PLACED.
	 */
	public void place() {
		status = "PLACED";
		orderedProduct.getSeller().getOrderList().add(this);
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
				"\nOrderedProduct:\n" + orderedProduct +
				"\nStatus: " + status;
	}
	
	public int compareTo(Order o) {
		return (int) (order_id - o.getID());
	}


}