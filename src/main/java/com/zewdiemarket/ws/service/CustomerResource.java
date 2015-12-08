package com.zewdiemarket.ws.service;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRequest;
import com.zewdiemarket.ws.service.workflow.CustomerActivity;

/*
 * Sets up web service with CXF annotations for CustomerService
 */
//@CrossOriginResourceSharing(
//allowOrigins = {
// "http://127.0.0.1:8000"
//}, 
//allowCredentials = true, 
//maxAge = 1, 
//allowHeaders = {
// "X-custom-1", "X-custom-2"
//}, 
//exposeHeaders = {
// "X-custom-3", "X-custom-4"
//}
//)
@CrossOriginResourceSharing(allowAllOrigins = true)
@Path("/customerservice/")
public class CustomerResource implements CustomerService {

		@GET
		@Produces({"application/xml" , "application/json"})
		@Path("/customer")
		public Set<CustomerRepresentation> getCustomers() {
			CustomerActivity customerAct = new CustomerActivity();
			return customerAct.getCustomers();
		}

		@GET
		@Produces({"application/xml" , "application/json"})
		@Path("/customer/{customerId}")
		public CustomerRepresentation getCustomer(@PathParam("customerId") String customerId) {
			CustomerActivity customerAct = new CustomerActivity();
			return customerAct.getCustomer(customerId);
		}

		@POST
		@Produces({"application/xml" , "application/json"})
		@Path("/customer")
		public CustomerRepresentation createCustomer(CustomerRequest customerRequest) {
			CustomerActivity customerAct = new CustomerActivity();
			return customerAct.createCustomer(customerRequest.getCustomerName());
		}
		
		@POST
		@Produces({"application/xml" , "application/json"})
		@Path("/customer/{customerId}/password/{password}")
		public CustomerRepresentation updateCustomerPassword(@PathParam("customerId") String customerId,
																@PathParam("password") String pass) {
			CustomerActivity customerAct = new CustomerActivity();
			return customerAct.updateCustomerPassword(customerId, pass);
		}
		
		@POST
		@Produces({"application/xml" , "application/json"})
		@Path("/customer/{customerId}/address/{address}")
		public CustomerRepresentation updateCustomerAddress(@PathParam("customerId") String customerId,
																@PathParam("address") String address) {
			CustomerActivity customerAct = new CustomerActivity();
			return customerAct.updateCustomerAddress(customerId, address);
		}
		
		@GET
		@Produces({"application/xml" , "application/json"})
		@Path("/customerlogin/{customerName}/{customerPassword}")
		public CustomerRepresentation login(@PathParam("customerName") String customerName, @PathParam("customerPassword")String customerPassword){
			CustomerActivity customerAct = new CustomerActivity();
			return customerAct.login(customerName, customerPassword);
		}

		@DELETE
		@Produces({"application/xml" , "application/json"})
		@Path("/customer/{customerId}")
		public Response deleteCustomer(@PathParam("customerId") String customerId) {
			CustomerActivity customerAct = new CustomerActivity();
			String res = customerAct.deleteCustomer(customerId);
			if (res.equals("OK")) {
				return Response.status(Status.OK).build();
			}
			return null;

		}
}
