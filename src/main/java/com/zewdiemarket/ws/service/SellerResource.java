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

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.SellerRepresentation;
import com.zewdiemarket.ws.service.representation.SellerRequest;
import com.zewdiemarket.ws.service.workflow.CustomerActivity;
import com.zewdiemarket.ws.service.workflow.SellerActivity;

/*
 * Sets up web service with CXF annotations for SellerService
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
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/sellerlogin/{sellerName}/{sellerPassword}")
	public SellerRepresentation login(@PathParam("sellerName") String sellerName, @PathParam("sellerPassword")String sellerPassword){
		SellerActivity sellerAct = new SellerActivity();
		return sellerAct.login(sellerName, sellerPassword);
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/seller")
	public SellerRepresentation createSeller(SellerRequest sellerRequest) {
		SellerActivity sellerAct = new SellerActivity();
		return sellerAct.createSeller(sellerRequest.getSellerName());
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/seller/{sellerId}/password/{password}")
	public SellerRepresentation updateSellerPassword(@PathParam("sellerId") String sellerId,
															@PathParam("password") String pass) {
		SellerActivity sellerAct = new SellerActivity();
		return sellerAct.updateSellerPassword(sellerId, pass);
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
