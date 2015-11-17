package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.dal.CustomerDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRepresentation;

/*
 * Activity for Customer - we get, create, and delete Customer representations
 */
public class CustomerActivity {

	public Set<CustomerRepresentation> getCustomers() {
		Set<Customer> customers = new HashSet<Customer>();
		Set<CustomerRepresentation> customerReps = new HashSet<CustomerRepresentation>();
		customers = CustomerDAO.getAllCustomers();
		for(Customer c : customers){
			CustomerRepresentation customerRep = new CustomerRepresentation();
			customerRep.setID(c.getID());
			customerRep.setCustomerName(c.getName());
			customerReps.add(customerRep);
		}
		return customerReps;
	}

	public CustomerRepresentation getCustomer(String id) {
		Customer c =  CustomerDAO.retrieveCustomer(Long.parseLong(id));
		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setID(c.getID());
		customerRep.setCustomerName(c.getName());
		return customerRep;
	}

	public CustomerRepresentation createCustomer(String customerName) {
		Customer c = CustomerDAO.addNewCustomer(customerName);
		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setID(c.getID());
		customerRep.setCustomerName(c.getName());
		return customerRep;
	}

	public String deleteCustomer(String id) {
		CustomerDAO.deleteCustomer(CustomerDAO.retrieveCustomer(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(CustomerRepresentation customerRep){

	}
}
