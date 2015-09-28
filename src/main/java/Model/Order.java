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
@Table(name = "order")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long order_id;
	
	@Column(name = "order_status")
	private String orderStatus = "PLACED";
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	Set<Product> orderProductList = new HashSet<Product>();
	
	public Order() {
		
	}
	
	public Order(Set<Product> productList, Customer c) {
		this.orderProductList = productList;
		this.customer = c;
	}
	
	public long getID() {
		return order_id;
	}
	
	public void setID(long id) {
		this.order_id = id;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String status) {
		this.orderStatus = status;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer c) {
		this.customer = c;
	}
	
	public void addOrderProduct(Product p) {
		orderProductList.add(p);
	}
	
	public void removeOrderProduct(Product p) {
		orderProductList.remove(p);
	}

	public void setOrderProductList(HashSet<Product> pl) {
		this.orderProductList = pl;
	}

	public Set<Product> getOrderProductList() {
		return orderProductList;
	}
	
	public double calculateCost() {
		double total = 0.0;
		for(Product p : orderProductList) {
			total += p.getPrice();
		}
		return total;
	}

	public void place() {
		orderStatus = "PLACED";
	}
	
	public void ship() {
		orderStatus = "SHIPPED";
	}
	
	public void deliver() {
		orderStatus = "DELIVERED";
	}
	
	public void cancel() {
		orderStatus = "CANCELLED";
	}
	
}