package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.BillingInfoRequest;
import com.zewdiemarket.ws.service.workflow.BillingInfoActivity;

/*
 * Sets up web service with CXF annotations for BillingInfoService
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
@Path("/billinginfoservice/")
public class BillingInfoResource implements BillingInfoService {


	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/billinginfo")
	public Set<BillingInfoRepresentation> getBillingInfos() {
		BillingInfoActivity billingInfoAct = new BillingInfoActivity();
		return billingInfoAct.getBillingInfos();
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/billinginfo/{billinginfoId}")
	public BillingInfoRepresentation getBillingInfo(@PathParam("billinginfoId") String billingInfoId) {
		BillingInfoActivity billingInfoAct = new BillingInfoActivity();
		return billingInfoAct.getBillingInfo(billingInfoId);
	}
	

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/billinginfo")
	public BillingInfoRepresentation createBillingInfo(BillingInfoRequest billingInfoRequest) {
		BillingInfoActivity billingInfoAct = new BillingInfoActivity();
		return billingInfoAct.createBillingInfo(billingInfoRequest.getBillingName(), billingInfoRequest.getCardNumber());
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/billinginfo/{billinginfoId}/update/cardtype/{cardType}/billingname/{billingName}/"
			+ "/")
	public Response updateBillingInfo( @PathParam("billinginfoId") String billingInfoId,
			@PathParam("cardType") String cardType,
			@PathParam("billingName") String billingName,
			@PathParam("billingAddress") String billingAddress,
			@PathParam("cardNumber") String cardNumber,
			@PathParam("expDate") String expDate,
			@PathParam("cvcNumber") String cvcNumber
			) {
		BillingInfoActivity billingInfoAct = new BillingInfoActivity();
		BillingInfoRequest billingInfoRequest = new BillingInfoRequest();
		billingInfoRequest.setCardType(cardType);
		billingInfoRequest.setBillingName(billingName);
		billingInfoRequest.setBillingAddress(billingAddress);
		billingInfoRequest.setCardNumber(cardNumber);
		billingInfoRequest.setExpDate(expDate);
		billingInfoRequest.setCvcNumber(cvcNumber);
		String res = billingInfoAct.updateBillingInfo(billingInfoId, billingInfoRequest);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/billinginfo/{billinginfoId}")
	public Response deleteBillingInfo(@PathParam("billinginfoId") String billingInfoId) {
		BillingInfoActivity billingInfoAct = new BillingInfoActivity();
		String res = billingInfoAct.deleteBillingInfo(billingInfoId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;

	}
}