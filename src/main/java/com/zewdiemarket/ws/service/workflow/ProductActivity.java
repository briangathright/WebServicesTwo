package com.zewdiemarket.ws.service.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.zewdiemarket.ws.Order;
import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.dal.OrderDAO;
import com.zewdiemarket.ws.dal.ProductDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.Link;
import com.zewdiemarket.ws.service.representation.OrderRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;
import com.zewdiemarket.ws.service.representation.ProductRequest;

/*
 * Activity for Product - we get, create, and delete Product representations
 */
public class ProductActivity {

	public Set<ProductRepresentation> getProducts() {
		// TODO Auto-generated method stub
		Set<Product> products = new TreeSet<Product>();
		Set<ProductRepresentation> prodReps = new TreeSet<ProductRepresentation>();
		products = ProductDAO.getAllProducts();
		for(Product p : products){
			ProductRepresentation prodRep= new ProductRepresentation();
			prodRep.setID(p.getID());
			prodRep.setProductDetail(p.getDetail());
			prodRep.setProductPrice(p.getPrice());
			setLinks(prodRep);
			prodReps.add(prodRep);
		}
		return prodReps;
	}

	public ProductRepresentation getProduct(String id) {
		System.out.println("Retrieving product with id " + id);
		Product p =  ProductDAO.retrieveProduct(Long.parseLong(id));
		ProductRepresentation prodRep = new ProductRepresentation();
		prodRep.setID(p.getID());
		prodRep.setProductDetail(p.getDetail());
		prodRep.setProductPrice(p.getPrice());
		setLinks(prodRep);
		return prodRep;
	}

	public String createProduct(ProductRequest prodRequest) {
		Product p = ProductDAO.addNewProduct(prodRequest.getProductDetail(), prodRequest.getProductPrice(), prodRequest.getSellerId());
		
		return "OK";
	}

	public String deleteProduct(String id) {
		ProductDAO.deleteProduct(ProductDAO.retrieveProduct(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(ProductRepresentation prodRep){
		Link[] links = new Link[2];
		Link buy = new Link("buy", System.getenv("ORDERSERVICE_URL") + "?product_id=" + prodRep.getID() + "&customer_id=");
		Link review = new Link("review", System.getenv("REVIEWSERVICE_URL") + "product/" + prodRep.getID());
		links[0] = buy;
		links[1] = review;
		prodRep.setLinks(links);
	}

	public Set<ProductRepresentation> getSellerProducts(String sellerId) {
		Set<Product> products = new TreeSet<Product>();
		Set<ProductRepresentation> prodReps = new TreeSet<ProductRepresentation>();
		products = ProductDAO.getSellerProducts(Long.parseLong(sellerId));
		for(Product p : products){
			ProductRepresentation prodRep = getProduct(String.valueOf(p.getID()));
			prodReps.add(prodRep);
		}
		return prodReps;
	}
}
