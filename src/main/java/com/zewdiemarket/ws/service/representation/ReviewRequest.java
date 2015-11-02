package com.zewdiemarket.ws.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.zewdiemarket.ws.Customer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ReviewRequest {
	
	private String reviewDetail;
	private float rating;
	private Customer customer;
		
	public ReviewRequest() {}
	
	public String getReviewDetail(){
		return reviewDetail;
	}
	
	public void setReviewDetail(String reviewDetail){
		this.reviewDetail = reviewDetail;
	}
	
	public float getRating(){
		return rating;
	}
	
	public void setRating(float rating){
		this.rating = rating;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
}
