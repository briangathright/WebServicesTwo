package com.zewdiemarket.ws.service;

import java.util.Set;

import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRequest;

public interface CustomerService {

	public Set<CustomerRepresentation> getCustomers();
	public CustomerRepresentation getCustomer(String customerId);
	public CustomerRepresentation createCustomer(CustomerRequest customerRequest);
	
}
