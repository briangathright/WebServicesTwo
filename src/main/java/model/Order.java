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
@Table(name="order")
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
	
	
	public Order() {
		
	}
	
	public Order(Set<Product> productList) {
		this.orderedProducts=productList;
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
	
	@Override
	public String toString()
	{
		return "Order:" + 
				"\nProductList:\n" + orderedProducts +
				"\nStatus: " + status;
	}
	
	
}