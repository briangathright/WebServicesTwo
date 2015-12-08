package com.zewdiemarket.ws.service.representation;

/*
 * Request for Review
 */
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
	private double rating;
	private String customerId;
	private String sellerId;
	private String productId;


	public ReviewRequest() {}

	public ReviewRequest(String productId, String reviewDetail, String rating, String customerId){
		this.productId = productId;
		this.reviewDetail = reviewDetail;
		this.rating = Double.parseDouble(rating);
		this.customerId = customerId;
	}

	public ReviewRequest(boolean x, String sellerId, String reviewDetail, String rating, String customerId){
		this.sellerId = sellerId;
		this.reviewDetail = reviewDetail;
		this.rating = Double.parseDouble(rating);
		this.customerId = customerId;
	}

	public String getReviewDetail(){
		return reviewDetail;
	}

	public void setReviewDetail(String reviewDetail){
		this.reviewDetail = reviewDetail;
	}

	public double getRating(){
		return rating;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}
	
	public String getSellerId(){
		return sellerId;
	}

	public void setSellerId(String sellerId){
		this.sellerId = sellerId;
	}
	
	public String getProductId(){
		return productId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

}
