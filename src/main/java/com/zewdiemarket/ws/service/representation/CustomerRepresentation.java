package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Class for Customer Representations, including id and customerName
 */
@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRepresentation extends AbstractRepresentation{

	private long id;
	private String customerName;
	private String customerAddr;
	private long billingId;
	
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
	
	public long getBillingInfoID(){
		return this.billingId;
	}
	
	public void setCustomerBillingInfoID(long billingInfoId){
		this.billingId = billingInfoId;
	}
	
	public String getCustomerAddress(){
		return this.customerAddr;
	}
	
	public void setCustomerAddress(String customerAddr){
		this.customerAddr = customerAddr;
	}
}
