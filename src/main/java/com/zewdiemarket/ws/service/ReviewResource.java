package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRequest;
import com.zewdiemarket.ws.service.representation.ReviewRepresentation;
import com.zewdiemarket.ws.service.representation.ReviewRequest;
import com.zewdiemarket.ws.service.workflow.ProductActivity;
import com.zewdiemarket.ws.service.workflow.ReviewActivity;


@Path("/reviewservice/")
public class ReviewResource implements ReviewService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/review")
	public Set<ReviewRepresentation> getReviews() {
		ReviewActivity reviewAct = new ReviewActivity();
		return reviewAct.getReviews();
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{ReviewId}")
	public ReviewRepresentation getReview(@PathParam("reviewId") String reviewId) {
		ReviewActivity reviewAct = new ReviewActivity();
		return reviewAct.getReview(reviewId);
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/review")
	public ReviewRepresentation createReview(ReviewRequest reviewRequest) {
		ReviewActivity reviewAct = new ReviewActivity();
		return reviewAct.createReview(reviewRequest.getReviewDetail(), reviewRequest.getRating(), reviewRequest.getCustomer());
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{productId}")
	public Response deleteProduct(@PathParam("productId") String productId) {
		ProductActivity prodAct = new ProductActivity();
		String res = prodAct.deleteProduct(productId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
		
	}
}
