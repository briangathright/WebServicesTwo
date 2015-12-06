package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRequest;

/*
 * Interface for ProductService
 */

@WebService
public interface ProductService {
		   
		public Set<ProductRepresentation> getProducts();
		public ProductRepresentation getProduct(String productId);	   	
}
