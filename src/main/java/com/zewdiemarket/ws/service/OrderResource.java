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
import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.OrderRequest;
import com.zewdiemarket.ws.service.workflow.OrderActivity;

/*
 * Sets up web service with CXF annotations for OrderService
 */
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

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/order")
	public OrderRepresentation createOrder(OrderRequest orderRequest) {
		OrderActivity orderAct = new OrderActivity();
		return orderAct.createOrder(orderRequest.getStatus());
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/order/{orderId}")
	public Response deleteOrder(@PathParam("oderId") String orderId) {
		OrderActivity orderAct = new OrderActivity();
		String res = orderAct.deleteOrder(orderId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;

	}
}
