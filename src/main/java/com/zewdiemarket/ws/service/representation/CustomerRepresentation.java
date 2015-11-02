package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRepresentation {

	private long id;
	private String customerName;
	
	public CustomerRepresentation(){}
	
	public long getID(){
		return this.id;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public String getCustomerName(){
		return this.customerName;
	}
	
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
}
