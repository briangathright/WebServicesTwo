package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Request class for Product
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRequest {
	
	private String productDetail;
	
	private double productPrice;
	
	private String productSellerId;
	
	public ProductRequest() {}
	
	public ProductRequest(String detail, String price, String sellerId) {
		this.productDetail = detail;
		this.productPrice = Double.parseDouble(price);
		this.productSellerId = sellerId;
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
	
	public String getSellerId(){
		return this.productSellerId;
	}
	
	public void setSellerId(String id){
		this.productSellerId = id;
	}
}
