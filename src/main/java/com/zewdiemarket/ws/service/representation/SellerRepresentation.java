package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Seller")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class SellerRepresentation {

	private long id;
	private String sellerName;

	public SellerRepresentation(){}

	public long getID(){
		return this.id;
	}

	public void setID(long id){
		this.id = id;
	}

	public String getSellerName(){
		return this.sellerName;
	}

	public void setSellerName(String sellerName){
		this.sellerName = sellerName;
	}
}