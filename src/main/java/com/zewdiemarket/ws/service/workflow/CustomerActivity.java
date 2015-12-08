package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Seller;
import com.zewdiemarket.ws.dal.CustomerDAO;
import com.zewdiemarket.ws.dal.SellerDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRequest;
import com.zewdiemarket.ws.service.representation.Link;
import com.zewdiemarket.ws.service.representation.SellerRequest;

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
			customerRep.setCustomerAddress(c.getAddress());
			customerRep.setCustomerBillingInfoID(c.getBillingInfo().getID());
			customerReps.add(customerRep);
			setLinks(customerRep);
		}
		return customerReps;
	}

	public CustomerRepresentation getCustomer(String id) {
		Customer c =  CustomerDAO.retrieveCustomer(Long.parseLong(id));
		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setID(c.getID());
		customerRep.setCustomerName(c.getName());
		customerRep.setCustomerAddress(c.getAddress());
		customerRep.setCustomerBillingInfoID(c.getBillingInfo().getID());
		setLinks(customerRep);
		return customerRep;
	}

	public CustomerRepresentation createCustomer(String customerName) {
		Customer c = CustomerDAO.addNewCustomer(customerName);
		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setID(c.getID());
		customerRep.setCustomerName(c.getName());
		setLinks(customerRep);
		return customerRep;
	}
	
	public CustomerRepresentation login(String customerName, String customerPassword) {
		Set<Customer> customers = new HashSet<Customer>();
		customers = CustomerDAO.getAllCustomers();
		for(Customer c : customers){
			if(c.getName().equals(customerName) && c.getPassword().equals(customerPassword)){
				return getCustomer(String.valueOf(c.getID()));
			}
		}
		return null;
	}

	public String createCustomer(CustomerRequest customerRequest) {
		Customer c = CustomerDAO.addNewCustomer(customerRequest.getCustomerName(), customerRequest.getCustomerPass());
		return "OK";
	}
	
	public String deleteCustomer(String id) {
		CustomerDAO.deleteCustomer(CustomerDAO.retrieveCustomer(Long.parseLong(id)));
		return "OK";
	}

	
	public void setLinks(CustomerRepresentation customerRep){
		Link[] links = new Link[5];
		Link updatePass = new Link("update_password", System.getenv("CUSTOMERSERVICE_URL") + customerRep.getID() + "/password/");
		Link updateAddr = new Link("update_address", System.getenv("CUSTOMERSERVICE_URL") + customerRep.getID() + "/address/");
		Link billingInfo = new Link("view_billingInfo", System.getenv("BILLINGINFOSERVICE_URL") + customerRep.getBillingInfoID());
		Link orders = new Link("view_orders", System.getenv("ORDERSERVICE_URL")+ "customerorders/" + customerRep.getID());
		Link search = new Link("search", System.getenv("PRODUCTSERVICE_URL"));
		links[0] = orders;
		links[1] = updatePass;
		links[2] = updateAddr;
		links[3] = search;
		links[4] = billingInfo;
		customerRep.setLinks(links);
	}

	public CustomerRepresentation updateCustomerPassword(String customerId, String pass) {
		Customer c = CustomerDAO.retrieveCustomer(Long.parseLong(customerId));
		c.setPassword(pass);
		CustomerDAO.addCustomer(c);
		return this.getCustomer(customerId);
	}
	
	public CustomerRepresentation updateCustomerAddress(String customerId, String addr) {
		Customer c = CustomerDAO.retrieveCustomer(Long.parseLong(customerId));
		c.setAddress(addr);
		CustomerDAO.addCustomer(c);
		return this.getCustomer(customerId);
	}
}
