package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Request class for BillingInfo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BillingInfoRequest {

	private String billingName;

	private String cardNumber;
	
	private String cardType;
	
	private String billingAddress;
		
	private String expDate;
	
	private String cvcNumber;


	public BillingInfoRequest() {}

	public String getBillingName(){
		return billingName;
	}

	public void setBillingName(String billingName){
		this.billingName = billingName;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}
	
	public String getCardType(){
		return cardType;
	}

	public void setCardType(String cardType){
		this.cardType = cardType;
	}
	
	public String getBillingAddress(){
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress){
		this.billingAddress = billingAddress;
	}
	
	public String getExpDate(){
		return expDate;
	}

	public void setExpDate(String expDate){
		this.expDate = expDate;
	}
	
	public String getCvcNumber(){
		return cvcNumber;
	}

	public void setCvcNumber(String cvcNumber){
		this.cvcNumber = cvcNumber;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}