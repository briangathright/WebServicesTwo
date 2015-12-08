package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Request class for Order
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {

	private String productId;
	private String sellerId;
	private String customerId;

	public OrderRequest() {}

	public String getProductId(){
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getCustomerId(){
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getSellerId(){
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
}
