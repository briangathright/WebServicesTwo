package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.dal.ProductDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;

/*
 * Activity for Product - we get, create, and delete Product representations
 */
public class ProductActivity {

	public Response getProducts() {
		// TODO Auto-generated method stub
		Set<Product> products = new HashSet<Product>();
		Set<ProductRepresentation> prodReps = new HashSet<ProductRepresentation>();
		products = ProductDAO.getAllProducts();
		for(Product p : products){
			ProductRepresentation prodRep= new ProductRepresentation();
			prodRep.setID(p.getID());
			prodRep.setProductDetail(p.getDetail());
			prodRep.setProductPrice(p.getPrice());
			prodReps.add(prodRep);
		}
		return Response.ok(prodReps).header("Access-Control-Allow-Origin", "*").build();
	}

	public Response getProduct(String id) {
		System.out.println("Retrieving product with id " + id);
		Product p =  ProductDAO.retrieveProduct(Long.parseLong(id));
		ProductRepresentation prodRep = new ProductRepresentation();
		prodRep.setID(p.getID());
		prodRep.setProductDetail(p.getDetail());
		prodRep.setProductPrice(p.getPrice());
		return Response.ok(prodRep).header("Access-Control-Allow-Origin", "*").build();
	}

	public Response createProduct(String productDetail, double productPrice) {
		Product p = ProductDAO.addNewProduct(productDetail, productPrice);
		ProductRepresentation prodRep = new ProductRepresentation();
		prodRep.setID(p.getID());
		prodRep.setProductDetail(p.getDetail());
		prodRep.setProductPrice(p.getPrice());
		return Response.ok(prodRep).header("Access-Control-Allow-Origin", "*").build();

	}

	public String deleteProduct(String id) {
		ProductDAO.deleteProduct(ProductDAO.retrieveProduct(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(ProductRepresentation prodRep){

	}
}
