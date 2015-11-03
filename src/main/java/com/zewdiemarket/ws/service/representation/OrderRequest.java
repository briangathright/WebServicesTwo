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

	private String status;

	public OrderRequest() {}

	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
