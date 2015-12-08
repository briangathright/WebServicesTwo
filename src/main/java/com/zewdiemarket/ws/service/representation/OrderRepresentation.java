package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Representation for Order Representation, including id and status
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation extends AbstractRepresentation implements Comparable<OrderRepresentation>{

	private long id;
	private long productId;
	private long customerId;
	private long sellerId;
	private String sellerName;
	private String productName;
	private String status;

	public OrderRepresentation() {}

	public long getID() {
		return this.id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getProductName(){
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}
	
	public String getSellerName(){
		return sellerName;
	}

	public void setSellerName(String name) {
		this.sellerName = name;
	}
	
	public long getProductID() {
		return this.productId;
	}
	
	public void setProductID(long productId) {
		this.productId = productId;
	}
	
	public long getSellerID() {
		return this.sellerId;
	}
	
	public void setSellerID(long sellerId) {
		this.sellerId = sellerId;
	}
	
	public long getCustomerID() {
		return this.customerId;
	}
	
	public void setCustomerID(long customerId) {
		this.customerId = customerId;
	}

	public int compareTo(OrderRepresentation or) {
		return (int) (id - or.getID());
	}
}
