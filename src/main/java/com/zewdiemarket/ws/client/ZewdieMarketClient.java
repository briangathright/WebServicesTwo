package com.zewdiemarket.ws.client;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.ws.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Response;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.Seller;
import com.zewdiemarket.ws.dal.BillingInfoDAO;
import com.zewdiemarket.ws.dal.CustomerDAO;
import com.zewdiemarket.ws.dal.ProductDAO;
import com.zewdiemarket.ws.dal.ReviewDAO;
import com.zewdiemarket.ws.dal.SellerDAO;
import com.zewdiemarket.ws.BillingInfo;

/**
 * Client: Tests functions and classes, and database functionality. Output can be read on Heroku
 */
public class ZewdieMarketClient {
	
	public ZewdieMarketClient() {}
	
	public void run2() {
		 List<Object> providers = new ArrayList<Object>();
         JacksonJsonProvider provider = new JacksonJsonProvider();
         provider.addUntouchable(Response.class);
         providers.add(provider);
         
         /*****************************************************************************************
          * GET METHOD invoke
          *****************************************************************************************/
         System.out.println("GET METHOD .........................................................");
         WebClient getClient = WebClient.create("/services", providers);
         
         //Configuring the CXF logging interceptor for the outgoing message
         WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
       //Configuring the CXF logging interceptor for the incoming response
         WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());
         
         // change application/xml  to get in xml format
         getClient = getClient.accept("application/json").type("application/json").path("/productservice/product/1");
         
         //The following lines are to show how to log messages without the CXF interceptors
         String getRequestURI = getClient.getCurrentURI().toString();
         System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
         String getRequestHeaders = getClient.getHeaders().toString();
         System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);
         
         //to see as raw XML/json
         String response = getClient.get(String.class);
         System.out.println("GET METHOD Response: ...." + response);
	}

	public void run() {
		System.out.println("Making Sellers (Partners)");
		System.out.println("-------------------------");
		Seller s1 = new Seller("LuTech Inc.");
		System.out.println("Created Seller: \n" + s1 + "\n");
		Seller s2 = new Seller("Zewdie and Co.");
		System.out.println("Created Seller: \n" + s2 + "\n");
		Seller s3 = new Seller("Nema Nemati");
		System.out.println("Created Seller: \n" + s3 + "\n");

		System.out.println("Making Products");
		System.out.println("---------------");
		Product p1 = new Product("Lemon Shandy - 12pack", 7.99);
		System.out.println("Created Product: \n" + p1 + "\n");
		Product p2 = new Product("Grapefruit Shandy - 12pack", 6.99);
		System.out.println("Created Product: \n" + p2 + "\n");
		Product p3 = new Product("Merlot - 1 bottle", 10.99);
		System.out.println("Created Product: \n" + p3 + "\n");
		Product p4 = new Product("White Zinfandel - 1 bottle", 9.99);
		System.out.println("Created Product: \n" + p4 + "\n");
		Product p5 = new Product("Moonshine - 1 jar", 12.99);
		System.out.println("Created Product: \n" + p5 + "\n");

		System.out.println("Adding Products to Sellers");
		System.out.println("--------------------------");
		s1.addProduct(p1);
		s1.addProduct(p2);
		System.out.println(s1 + "\n");
		s2.addProduct(p3);
		s2.addProduct(p4);
		System.out.println(s2 + "\n");
		s3.addProduct(p5);
		System.out.println(s3 + "\n");

		System.out.println("Making Customers");
		System.out.println("----------------");
		Customer c1 = new Customer("Thor Odinson");
		System.out.println("Created Customer: \n" + c1 + "\n");
		Customer c2 = new Customer("Odin Allfather");
		System.out.println("Created Customer: \n" + c2 + "\n");

		System.out.println("Adding Billing and Shipping Info to Customers");
		System.out.println("---------------------------------------------");
		c1.setAddress("2244 Main Street, Asgard City, Asgard, 65101");
		BillingInfo b1 = new BillingInfo("Visa", "Thor Odinson", "2244 Main Street, Asgard City, Asgard, 65101", "1234-4321-5343-1233", "03/16", "256");
		c1.setBillingInfo(b1);
		c2.setAddress("1212 Side Street, Asgard City, Asgard, 65101");
		BillingInfo b2 = new BillingInfo("Visa", "Odin Allfather", "1212 Side Street, Asgard City, Asgard, 65101", "2313-4343-6444-1221", "04/17", "126");
		c2.setBillingInfo(b2);
		System.out.println(c1.getName() + "'s address set to " + c1.getAddress());
		System.out.println(c1.getName() + "'s billing info set to " + c1.getBillingInfo() + "\n");
		System.out.println(c2.getName() + "'s address set to " + c2.getAddress());
		System.out.println(c2.getName() + "'s billing info set to " + c2.getBillingInfo());


		System.out.println("\nMaking Reviews for Products and Sellers");
		System.out.println("-----------------------------------------");
		Review r1 = new Review(5, c1, "Just phenomenal!");
		Review r2 = new Review(2, c1, "It's pretty bad.");
		Review r3 = new Review(-5, c2, "Meh.");
		Review r4 = new Review(12, c2, "Outstanding!");

		p1.addReview(r1);
		System.out.println("Added review to " + p1.getDetail() + "\n" + r1 + "\n");
		p5.addReview(r2);
		System.out.println("Added review to " + p5.getDetail() + "\n" + r2 + "\n");
		p1.addReview(r3);
		System.out.println("Added review to " + p1.getDetail() + "\n" + r3 + "\n");
		s1.addReview(r4);
		System.out.println("Added review to " + s1.getName() + "\n" + r4 + "\n");

		System.out.println("\nPlacing Orders");
		System.out.println("-------------");
		c1.addToShoppingCart(p1);
		c1.addToShoppingCart(p2);
		c1.placeOrder();
		System.out.println("Made order:\n" + c1.getOrderList());

		System.out.println("\nStoring Everything in Database");
		System.out.println("------------------------------");

		/**
		 * Uses our Hibernate Data Access Object to add objects to be stored in our database
		 */
		SellerDAO.addSeller(s1);
		SellerDAO.addSeller(s2);
		SellerDAO.addSeller(s3);
		ProductDAO.addProduct(p1);
		ProductDAO.addProduct(p2);
		ProductDAO.addProduct(p3);
		ProductDAO.addProduct(p4);
		ProductDAO.addProduct(p5);
		BillingInfoDAO.addBillingInfo(b1);
		BillingInfoDAO.addBillingInfo(b2);
		CustomerDAO.addCustomer(c1);
		CustomerDAO.addCustomer(c2);
		ReviewDAO.addReview(r1);
		ReviewDAO.addReview(r2);
		ReviewDAO.addReview(r3);
		ReviewDAO.addReview(r4);

		System.out.println("\nRetrieving Some Entries from Database");
		System.out.println("---------------------------------------");

		System.out.println("\nRetrieving Product with ID: 1");
		Product retP1 = ProductDAO.retrieveProduct((long)1);
		System.out.println("Retrieved Product is: \n" + retP1 + "\n");

		System.out.println("\nRetrieving Seller with ID: 1");
		Seller retS1 = SellerDAO.retrieveSeller((long)1);
		System.out.println("Retrieved Seller is: \n" + retS1);

		/**
		 * Prints all of the Seller's Products and reviews
		 */
		System.out.println("\nRetrieved Product list for Retrieved Seller contains: ");
		for(Product p : retS1.getProductList()) {
			System.out.println("\n" + p);
			for(Review r : p.getReviewList()) {
				System.out.println("\n" + r);
			}
		}
		
		System.out.println("\nRetrieving Customer with ID: 1");
		Customer retC1 = CustomerDAO.retrieveCustomer((long)1);
		System.out.println("Retrieved Customer is: \n" + retC1 + "\n");

		System.out.println("\nRetrieving Customer with ID: 2");
		Customer retC2 = CustomerDAO.retrieveCustomer((long)2);
		System.out.println("Retrieved Customer is: \n" + retC2 + "\n");
		
		
		System.out.println("Retrieve all Products: \n");
		
		Set<Product> rp = ProductDAO.getAllProducts();
		for(Product p : rp){
			System.out.println(p.getID());
		}
		run2();
	}
}