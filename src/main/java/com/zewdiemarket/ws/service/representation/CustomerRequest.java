package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Request class for Customer
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRequest {
	String customerName;
	
	public CustomerRequest(){}
	
	public String getCustomerName(){
		return customerName;
	}
	
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
}
