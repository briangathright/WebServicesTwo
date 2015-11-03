package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Representation for Product, including id, productDetail, and productPrice
 */
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation {
	
	private long id;
	private String productDetail;
	private double productPrice;
	
	public ProductRepresentation() {}
	
	public long getID(){
		return id;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public String getProductDetail(){
		return productDetail;
	}
	
	public void setProductDetail(String productDetail){
		this.productDetail = productDetail;
	}
	
	public double getProductPrice(){
		return productPrice;
	}
	
	public void setProductPrice(double productPrice){
		this.productPrice = productPrice;
	}
}
