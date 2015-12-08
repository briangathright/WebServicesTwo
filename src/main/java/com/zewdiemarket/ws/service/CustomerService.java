package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.jws.WebService;
import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRequest;

/*
 * Interface for BillingInfoService
 */

@WebService
public interface CustomerService {

	public Set<CustomerRepresentation> getCustomers();
	public CustomerRepresentation getCustomer(String customerId);
	public CustomerRepresentation login(String customerName, String customerPassword);
	
}
