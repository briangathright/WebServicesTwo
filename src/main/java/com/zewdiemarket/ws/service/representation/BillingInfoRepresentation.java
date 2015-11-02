package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BillingInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BillingInfoRepresentation {
	
	private long id;
	private String billingName;
	private String cardNumber;
	
	public BillingInfoRepresentation() {}
	
	public long getID(){
		return this.id;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public String getBillingName(){
		return this.billingName;
	}
	
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}
	
	public String getCardNumber(){
		return this.cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
