package com.zewdiemarket.ws.service;

import java.util.Set;

import javax.jws.WebService;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRequest;

@WebService
public interface ProductService {
		   
		public Set<ProductRepresentation> getProducts();
		public ProductRepresentation getProduct(String productId);
		public ProductRepresentation createEmployee(ProductRequest productRequest);
	   
	    //public Response updateEmployee(EmployeeRequest employeeRequest);
	    //public Response deleteEmployee(String employeeId);
		
		

	
}
