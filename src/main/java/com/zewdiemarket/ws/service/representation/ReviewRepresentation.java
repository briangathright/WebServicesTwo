package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.zewdiemarket.ws.Customer;

/*
 * Representation for Review, including id, reviewDetail, rating, customerName
 */
@XmlRootElement(name = "Review")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ReviewRepresentation extends AbstractRepresentation{
	
	private long id;
	private String reviewDetail;
	private float rating;
	private String customerName;
	
	public ReviewRepresentation() {}
	
	public long getID(){
		return id;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public String getReviewDetail(){
		return reviewDetail;
	}
	
	public void setReviewDetail(String productDetail){
		this.reviewDetail = productDetail;
	}
	
	public String getCustomerName(){
		return customerName;
	}
	
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public float getRating(){
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
}
