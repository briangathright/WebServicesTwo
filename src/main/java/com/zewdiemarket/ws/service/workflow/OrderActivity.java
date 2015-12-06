package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.Order;
import com.zewdiemarket.ws.dal.OrderDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.Link;
import com.zewdiemarket.ws.service.representation.OrderRepresentation;

/*
 * Activity for Order - we get, create, and delete Order representations
 */
public class OrderActivity {
	public Set<OrderRepresentation> getOrders() {
		Set<Order> orders = new HashSet<Order>();
		Set<OrderRepresentation> orderReps = new HashSet<OrderRepresentation>();
		orders = OrderDAO.getAllOrders();
		for(Order o : orders){
			OrderRepresentation orderRep = new OrderRepresentation();
			orderRep.setID(o.getID());
			orderRep.setStatus(o.getStatus());
			orderReps.add(orderRep);
		}
		return orderReps;
	}

	public OrderRepresentation getOrder(String id) {
		Order o = OrderDAO.retrieveOrder(Long.parseLong(id));
		OrderRepresentation orderRep = new OrderRepresentation();
		orderRep.setID(o.getID());
		orderRep.setStatus(o.getStatus());
		return orderRep;
	}

	public OrderRepresentation createOrder(String status) {
		Order o = OrderDAO.addNewOrder(status);
		OrderRepresentation orderRep = new OrderRepresentation();
		orderRep.setID(o.getID());
		orderRep.setStatus(o.getStatus());
		return orderRep;
	}

	public String deleteOrder(String id) {
		OrderDAO.deleteOrder(OrderDAO.retrieveOrder(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(OrderRepresentation orderRep){
		Link[] links = new Link[2];
		Link buy = new Link("buy", System.getenv("PRODUCTSERVICE_URL"));
		Link review = new Link("review", System.getenv("PRODUCTSERVICE_URL"));
		links[0] = buy;
		links[1] = review;
		orderRep.setLinks(links);
	}
}