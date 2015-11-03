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
}