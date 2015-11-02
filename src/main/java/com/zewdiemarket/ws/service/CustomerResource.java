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
import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRequest;
import com.zewdiemarket.ws.service.workflow.CustomerActivity;

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
