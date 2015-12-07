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
			orderRep.setProductID(o.getOrderedProduct().getID());
			orderRep.setCustomerID(o.getCustomer().getID());
			setLinks(orderRep);
			orderReps.add(orderRep);
		}
		return orderReps;
	}

	public OrderRepresentation getOrder(String id) {
		Order o = OrderDAO.retrieveOrder(Long.parseLong(id));
		OrderRepresentation orderRep = new OrderRepresentation();
		orderRep.setID(o.getID());
		orderRep.setStatus(o.getStatus());
		orderRep.setProductID(o.getOrderedProduct().getID());
		orderRep.setCustomerID(o.getCustomer().getID());
		setLinks(orderRep);
		return orderRep;
	}
	
	public Set<OrderRepresentation> getCustomerOrders(String customerId){
		Set<Order> orders = new HashSet<Order>();
		Set<OrderRepresentation> orderReps = new HashSet<OrderRepresentation>();
		orders = OrderDAO.getCustomersOrders(Long.parseLong(customerId));
		for(Order o : orders){
			OrderRepresentation orderRep = new OrderRepresentation();
			orderRep.setID(o.getID());
			orderRep.setStatus(o.getStatus());
			orderRep.setProductID(o.getOrderedProduct().getID());
			orderRep.setCustomerID(o.getCustomer().getID());
			setLinks(orderRep);
			orderReps.add(orderRep);
		}
		return orderReps;
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
		Link cancel_order = new Link("cancel_order", System.getenv("ORDERSERVICE_URL") + "cancel/" + orderRep.getID());
		Link view_product = new Link("view_product", System.getenv("PRODUCTSERVICE_URL") + orderRep.getProductID());
		links[0] = cancel_order;
		links[1] = view_product;
		orderRep.setLinks(links);
	}

	public OrderRepresentation cancelCustomerOrders(String orderId) {
		Order o = OrderDAO.retrieveOrder(Long.parseLong(orderId));
		o.setStatus("CANCELLED");
		OrderDAO.addOrder(o);
		return this.getOrder(orderId);
	}
}