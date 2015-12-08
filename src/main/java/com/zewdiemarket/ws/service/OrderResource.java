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

import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.OrderRequest;
import com.zewdiemarket.ws.service.workflow.OrderActivity;

/*
 * Sets up web service with CXF annotations for OrderService
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
@Path("/orderservice/")
public class OrderResource implements OrderService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order")
	public Set<OrderRepresentation> getOrders() {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.getOrders();
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/{orderId}")
	public OrderRepresentation getOrder(@PathParam("orderId") String orderId) {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.getOrder(orderId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/customerorders/{customerId}")
	public Set<OrderRepresentation> getCustomerOrders(@PathParam("customerId") String customerId) {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.getCustomerOrders(customerId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/sellerorders/{sellerId}")
	public Set<OrderRepresentation> getSellerOrders(@PathParam("sellerId") String sellerId) {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.getSellerOrders(sellerId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/cancel/{orderId}")
	public OrderRepresentation cancelCustomerOrder(@PathParam("orderId") String orderId) {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.cancelCustomerOrder(orderId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/fulfill/{orderId}")
	public OrderRepresentation fulfillCustomerOrder(@PathParam("orderId") String orderId) {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.fulfillCustomerOrder(orderId);
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/order/product/{productId}/customer/{customerId}")
	public String createOrder(@PathParam("productId") String productId, @PathParam("customerId") String customerId) {
		OrderActivity orderAct = new OrderActivity();
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setProductId(productId);
		orderRequest.setCustomerId(customerId);
		return orderAct.createOrder(orderRequest);
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/order/{orderId}")
	public Response deleteOrder(@PathParam("orderId") String orderId) {
		OrderActivity orderAct = new OrderActivity();
		String res = orderAct.deleteOrder(orderId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;

	}
}
