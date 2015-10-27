package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRequest {
	
	private String productDetail;
	
	private double productPrice;
	
	public ProductRequest() {}
	
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
