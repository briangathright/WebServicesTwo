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

import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.BillingInfoRequest;
import com.zewdiemarket.ws.service.workflow.BillingInfoActivity;

@Path("/billininfoservice/")
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

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/billininfo/{billinginfoId}")
	public Response deleteProduct(@PathParam("billinginfoId") String billingInfoId) {
		BillingInfoActivity billingInfoAct = new BillingInfoActivity();
		String res = billingInfoAct.deleteBillingInfo(billingInfoId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;

	}
}