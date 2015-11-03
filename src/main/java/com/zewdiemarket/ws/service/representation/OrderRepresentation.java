package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Representation for Order Representation, including id and status
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {

	private long id;
	private String status;

	public OrderRepresentation() {}

	public long getID() {
		return this.id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
