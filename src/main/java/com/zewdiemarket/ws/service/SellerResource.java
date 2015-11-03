package com.zewdiemarket.ws.service;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.zewdiemarket.ws.service.representation.SellerRepresentation;
import com.zewdiemarket.ws.service.representation.SellerRequest;
import com.zewdiemarket.ws.service.workflow.SellerActivity;

/*
 * Sets up web service with CXF annotations for SellerService
 */
@Path("/sellerservice/")
public class SellerResource implements SellerService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/seller")
	public Set<SellerRepresentation> getSellers() {
		SellerActivity sellerAct = new SellerActivity();
		return sellerAct.getSellers();
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/seller/{sellerId}")
	public SellerRepresentation getSeller(@PathParam("sellerId") String sellerId) {
		SellerActivity sellerAct = new SellerActivity();
		return sellerAct.getSeller(sellerId);
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/seller")
	public SellerRepresentation createSeller(SellerRequest sellerRequest) {
		SellerActivity sellerAct = new SellerActivity();
		return sellerAct.createSeller(sellerRequest.getSellerName());
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/seller/{sellerId}")
	public Response deleteSeller(@PathParam("sellerId") String sellerId) {
		SellerActivity sellerAct = new SellerActivity();
		String res = sellerAct.deleteSeller(sellerId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;

	}
}
