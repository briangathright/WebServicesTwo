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

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.ReviewRepresentation;
import com.zewdiemarket.ws.service.representation.ReviewRequest;
import com.zewdiemarket.ws.service.workflow.OrderActivity;
import com.zewdiemarket.ws.service.workflow.ReviewActivity;

/*
 * Sets up web service with CXF annotations for ReviewService
 */
@CrossOriginResourceSharing(
        allowOrigins = {
           "http://127.0.0.1:8000"
        }, 
        allowCredentials = true, 
        maxAge = 1, 
        allowHeaders = {
           "X-custom-1", "X-custom-2"
        }, 
        exposeHeaders = {
           "X-custom-3", "X-custom-4"
        }
)
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
	@Path("/review/{reviewId}")
	public ReviewRepresentation getReview(@PathParam("reviewId") String reviewId) {
		ReviewActivity reviewAct = new ReviewActivity();
		return reviewAct.getReview(reviewId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/review/sellerreviews/{sellerId}")
	public Set<ReviewRepresentation> getSellerReviews(@PathParam("sellerId") String sellerId) {
		ReviewActivity reviewAct = new ReviewActivity();
		return reviewAct.getSellerReviews(sellerId);
	}

	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/review/seller/{sellerId}/customer/{customerId}/detail/{detail}/rating/{rating}")
	public Response createSellerReview(@PathParam("sellerId") String sellerId, @PathParam("customerId") String customerId,
			@PathParam("detail") String detail, @PathParam("rating") String rating) {
		ReviewActivity reviewAct = new ReviewActivity();
		ReviewRequest reviewRequest = new ReviewRequest(true, sellerId, detail, rating, customerId);
		String res = reviewAct.createSellerReview(reviewRequest);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/review/product/{productId}/customer/{customerId}/detail/{detail}/rating/{rating}")
	public Response createProductReview(@PathParam("productId") String productId, @PathParam("customerId") String customerId,
			@PathParam("detail") String detail, @PathParam("rating") String rating) {
		ReviewActivity reviewAct = new ReviewActivity();
		ReviewRequest reviewRequest = new ReviewRequest(productId, detail, rating, customerId);
		String res = reviewAct.createProductReview(reviewRequest);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/review/{reviewId}")
	public Response deleteReview(@PathParam("reviewId") String reviewId) {
		ReviewActivity reviewAct = new ReviewActivity();
		String res = reviewAct.deleteReview(reviewId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
		
	}
}
