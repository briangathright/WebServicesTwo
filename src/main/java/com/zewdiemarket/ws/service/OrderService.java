package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.jws.WebService;
import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.OrderRequest;

@WebService
public interface OrderService {

	public Set<OrderRepresentation> getOrders();
	public OrderRepresentation getOrder(String orderId);
	public OrderRepresentation createOrder(OrderRequest orderRequest);

}