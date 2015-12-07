package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
		Set<Order> orders = new TreeSet<Order>();
		Set<OrderRepresentation> orderReps = new TreeSet<OrderRepresentation>();
		orders = OrderDAO.getAllOrders();
		for(Order o : orders){
			OrderRepresentation orderRep = new OrderRepresentation();
			orderRep.setID(o.getID());
			orderRep.setStatus(o.getStatus());
			orderRep.setProductID(o.getOrderedProduct().getID());
			orderRep.setCustomerID(o.getCustomer().getID());
			orderRep.setProductName(o.getProductName());

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
		orderRep.setProductName(o.getProductName());
		setLinks(orderRep);
		return orderRep;
	}
	
	public Set<OrderRepresentation> getCustomerOrders(String customerId){
		Set<Order> orders = new TreeSet<Order>();
		Set<OrderRepresentation> orderReps = new TreeSet<OrderRepresentation>();
		orders = OrderDAO.getCustomersOrders(Long.parseLong(customerId));
		for(Order o : orders){
			OrderRepresentation orderRep = new OrderRepresentation();
			orderRep.setID(o.getID());
			orderRep.setStatus(o.getStatus());
			orderRep.setProductID(o.getOrderedProduct().getID());
			orderRep.setCustomerID(o.getCustomer().getID());
			orderRep.setProductName(o.getProductName());
			setLinks(orderRep);
			orderReps.add(orderRep);
		}
		return orderReps;
	}
	
	public Set<OrderRepresentation> getSellerOrders(String sellerId){
		Set<Order> orders = new TreeSet<Order>();
		Set<OrderRepresentation> orderReps = new TreeSet<OrderRepresentation>();
		orders = OrderDAO.getSellerOrders(Long.parseLong(sellerId));
		for(Order o : orders){
			OrderRepresentation orderRep = new OrderRepresentation();
			orderRep.setID(o.getID());
			orderRep.setStatus(o.getStatus());
			orderRep.setProductID(o.getOrderedProduct().getID());
			orderRep.setCustomerID(o.getCustomer().getID());
			orderRep.setProductName(o.getProductName());
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
		orderRep.setProductName(o.getProductName());
		return orderRep;
	}

	public String deleteOrder(String id) {
		OrderDAO.deleteOrder(OrderDAO.retrieveOrder(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(OrderRepresentation orderRep){
		Link[] links = new Link[3];
		Link cancel_order = new Link("cancel_order", System.getenv("ORDERSERVICE_URL") + "cancel/" + orderRep.getID());
		Link view_product = new Link("view_product", System.getenv("PRODUCTSERVICE_URL") + orderRep.getProductID());
		Link fulfill_order = new Link("fulfill_order", System.getenv("ORDERSERVICE_URL") + "fulfill/" + orderRep.getID());
		links[0] = cancel_order;
		links[1] = view_product;
		links[2] = fulfill_order;
		orderRep.setLinks(links);
	}

	public OrderRepresentation cancelCustomerOrder(String orderId) {
		Order o = OrderDAO.retrieveOrder(Long.parseLong(orderId));
		o.setStatus("CANCELLED");
		OrderDAO.addOrder(o);
		return this.getOrder(orderId);
	}
	
	public OrderRepresentation fulfillCustomerOrder(String orderId) {
		Order o = OrderDAO.retrieveOrder(Long.parseLong(orderId));
		o.setStatus("FULFILLED");
		OrderDAO.addOrder(o);
		return this.getOrder(orderId);
	}
}