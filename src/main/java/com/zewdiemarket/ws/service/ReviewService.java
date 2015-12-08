package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.jws.WebService;
import com.zewdiemarket.ws.service.representation.ReviewRepresentation;
import com.zewdiemarket.ws.service.representation.ReviewRequest;

/*
 * Interface for ReviewService
 */
@WebService
public interface ReviewService {
		   
		public Set<ReviewRepresentation> getReviews();
		public ReviewRepresentation getReview(String reviewId);
	   	
}
