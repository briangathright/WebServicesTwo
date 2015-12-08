package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRequest;
import com.zewdiemarket.ws.service.workflow.OrderActivity;
import com.zewdiemarket.ws.service.workflow.ProductActivity;

/*
 * Sets up web service with CXF annotations for ProductService
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
@Path("/productservice/")
public class ProductResource implements ProductService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product")
	public Set<ProductRepresentation> getProducts() {
		ProductActivity prodAct = new ProductActivity();
		return prodAct.getProducts();
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{productId}")
	public ProductRepresentation getProduct(@PathParam("productId") String productId) {
		ProductActivity prodAct = new ProductActivity();
		return prodAct.getProduct(productId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product/sellerproducts/{sellerId}")
	public Set<ProductRepresentation> getSellerProducts(@PathParam("sellerId") String sellerId) {
		ProductActivity prodAct = new ProductActivity();
		return prodAct.getSellerProducts(sellerId);
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/product/seller/{sellerId}/productdetail/{productDetail}/productprice/{productPrice}")
	public Response createProduct(@PathParam("productDetail") String detail, 
													@PathParam("productPrice") String price, @PathParam("sellerId") String sellerId) {
		ProductActivity prodAct = new ProductActivity();
		ProductRequest productRequest = new ProductRequest(detail, price, sellerId);
		String res = prodAct.createProduct(productRequest);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{productId}")
	public Response deleteProduct(@PathParam("productId") String productId) {
		ProductActivity prodAct = new ProductActivity();
		String res = prodAct.deleteProduct(productId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
		
	}
}
