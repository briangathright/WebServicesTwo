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
public class ReviewRepresentation extends AbstractRepresentation implements Comparable<ReviewRepresentation>{
	
	private long id;
	private String reviewDetail;
	private double rating;
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

	public double getRating(){
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public int compareTo(ReviewRepresentation rr) {
		return (int) (id - rr.getID());
	}
}
