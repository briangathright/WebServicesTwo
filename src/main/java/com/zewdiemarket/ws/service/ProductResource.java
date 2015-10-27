package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRequest;


@Path("/productservice/")
public class ProductResource implements ProductService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product")
	public Set<ProductRepresentation> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{productId}")
	public ProductRepresentation getProduct(@PathParam("productId") String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/product")
	public ProductRepresentation createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@DELETE
//	@Produces({"application/xml" , "application/json"})
//	@Path("/employee/{employeeId}")
//	public Response deleteEmployee(@PathParam("employeeId") String id) {
//		System.out.println("Delete METHOD Request from Client with employeeRequest String ............." + id);
//		EmployeeActivity empActivity = new EmployeeActivity();
//		String res = empActivity.deleteEmployee(id);
//		if (res.equals("OK")) {
//			return Response.status(Status.OK).build();
//		}
//		return null;
//	}
}
