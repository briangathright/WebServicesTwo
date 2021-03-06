package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Request for Seller
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class SellerRequest {
	String sellerName;
	String sellerPass;

	public SellerRequest(){}

	public String getSellerName(){
		return sellerName;
	}

	public void setSellerName(String sellerName){
		this.sellerName = sellerName;
	}
	
	public String getSellerPass(){
		return sellerPass;
	}

	public void setSellerPass(String sellerPass){
		this.sellerPass = sellerPass;
	}
}
