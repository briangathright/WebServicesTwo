package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.dal.ProductDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.Link;
import com.zewdiemarket.ws.service.representation.ProductRepresentation;

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
		return prodRep;
	}

	public ProductRepresentation createProduct(String productDetail, double productPrice) {
		Product p = ProductDAO.addNewProduct(productDetail, productPrice);
		ProductRepresentation prodRep = new ProductRepresentation();
		prodRep.setID(p.getID());
		prodRep.setProductDetail(p.getDetail());
		prodRep.setProductPrice(p.getPrice());
		return prodRep;
	}

	public String deleteProduct(String id) {
		ProductDAO.deleteProduct(ProductDAO.retrieveProduct(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(ProductRepresentation prodRep){
		Link buy = new Link("buy", System.getenv("PRODUCTSERVICE_URL"));
		Link review = new Link("review", System.getenv("PRODUCTSERVICE_URL"));
		prodRep.setLinks(buy);
		prodRep.setLinks(review);
	}
}
