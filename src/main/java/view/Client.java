package view;

import java.io.PrintWriter;
import data.HibernateDao;
import model.BillingInfo;
import model.Customer;
import model.Product;
import model.Review;
import model.Seller;

/**
 * Client: Tests functions and classes, and database functionality. Output can be read on Heroku
 */
public class Client {
	PrintWriter writer;

	public Client(PrintWriter writer) {
		this.writer = writer;
	}

	public void run() {
		writer.println("Making Sellers (Partners)");
		writer.println("-------------------------");
		Seller s1 = new Seller("LuTech Inc.");
		writer.println("Created Seller: \n" + s1 + "\n");
		Seller s2 = new Seller("Zewdie and Co.");
		writer.println("Created Seller: \n" + s2 + "\n");
		Seller s3 = new Seller("Nema Nemati");
		writer.println("Created Seller: \n" + s3 + "\n");

		writer.println("Making Products");
		writer.println("---------------");
		Product p1 = new Product("Lemon Shandy - 12pack", 7.99);
		writer.println("Created Product: \n" + p1 + "\n");
		Product p2 = new Product("Grapefruit Shandy - 12pack", 6.99);
		writer.println("Created Product: \n" + p2 + "\n");
		Product p3 = new Product("Merlot - 1 bottle", 10.99);
		writer.println("Created Product: \n" + p3 + "\n");
		Product p4 = new Product("White Zinfandel - 1 bottle", 9.99);
		writer.println("Created Product: \n" + p4 + "\n");
		Product p5 = new Product("Moonshine - 1 jar", 12.99);
		writer.println("Created Product: \n" + p5 + "\n");

		writer.println("Adding Products to Sellers");
		writer.println("--------------------------");
		s1.addProduct(p1);
		s1.addProduct(p2);
		writer.println(s1 + "\n");
		s2.addProduct(p3);
		s2.addProduct(p4);
		writer.println(s2 + "\n");
		s3.addProduct(p5);
		writer.println(s3 + "\n");

		writer.println("Making Customers");
		writer.println("----------------");
		Customer c1 = new Customer("Thor Odinson");
		writer.println("Created Customer: \n" + c1 + "\n");
		Customer c2 = new Customer("Odin Allfather");
		writer.println("Created Customer: \n" + c2 + "\n");

		writer.println("Adding Billing and Shipping Info to Customers");
		writer.println("---------------------------------------------");
		c1.setAddress("2244 Main Street, Asgard City, Asgard, 65101");
		BillingInfo b1 = new BillingInfo("Visa", "Thor Odinson", "2244 Main Street, Asgard City, Asgard, 65101", "1234-4321-5343-1233", "03/16", "256");
		c1.setBillingInfo(b1);
		c2.setAddress("1212 Side Street, Asgard City, Asgard, 65101");
		BillingInfo b2 = new BillingInfo("Visa", "Odin Allfather", "1212 Side Street, Asgard City, Asgard, 65101", "2313-4343-6444-1221", "04/17", "126");
		c2.setBillingInfo(b2);
		writer.println(c1.getName() + "'s address set to " + c1.getAddress());
		writer.println(c1.getName() + "'s billing info set to " + c1.getBillingInfo() + "\n");
		writer.println(c2.getName() + "'s address set to " + c2.getAddress());
		writer.println(c2.getName() + "'s billing info set to " + c2.getBillingInfo());


		writer.println("\nMaking Reviews for Products and Sellers");
		writer.println("-----------------------------------------");
		Review r1 = new Review(5, c1, "Just phenomenal!");
		Review r2 = new Review(2, c1, "It's pretty bad.");
		Review r3 = new Review(-5, c2, "Meh.");
		Review r4 = new Review(12, c2, "Outstanding!");

		p1.addReview(r1);
		writer.println("Added review to " + p1.getDetail() + "\n" + r1 + "\n");
		p5.addReview(r2);
		writer.println("Added review to " + p5.getDetail() + "\n" + r2 + "\n");
		p1.addReview(r3);
		writer.println("Added review to " + p1.getDetail() + "\n" + r3 + "\n");
		s1.addReview(r4);
		writer.println("Added review to " + s1.getName() + "\n" + r4 + "\n");

		writer.println("\nPlacing Orders");
		writer.println("-------------");
		c1.addToShoppingCart(p1);
		c1.addToShoppingCart(p2);
		c1.placeOrder();
		writer.println("Made order:\n" + c1.getOrderList());

		writer.println("\nStoring Everything in Database");
		writer.println("------------------------------");

		/**
		 * Uses our Hibernate Data Access Object to add objects to be stored in our database
		 */
		HibernateDao.add(s1);
		HibernateDao.add(s2);
		HibernateDao.add(s3);
		HibernateDao.add(p1);
		HibernateDao.add(p2);
		HibernateDao.add(p3);
		HibernateDao.add(p4);
		HibernateDao.add(p5);
		HibernateDao.add(b1);
		HibernateDao.add(b2);
		HibernateDao.add(c1);
		HibernateDao.add(c2);
		HibernateDao.add(r1);
		HibernateDao.add(r2);
		HibernateDao.add(r3);
		HibernateDao.add(r4);

		writer.println("\nRetrieving Some Entries from Database");
		writer.println("---------------------------------------");

		writer.println("\nRetrieving Product with ID: 1");
		Product retP1 = HibernateDao.retrieveProduct((long)1);
		writer.println("Retrieved Product is: \n" + retP1 + "\n");

		writer.println("\nRetrieving Seller with ID: 1");
		Seller retS1 = HibernateDao.retrieveSeller((long)1);
		writer.println("Retrieved Seller is: \n" + retS1);

		/**
		 * Prints all of the Seller's Products and reviews
		 */
		writer.println("\nRetrieved Product list for Retrieved Seller contains: ");
		for(Product p : retS1.getProductList()) {
			writer.println("\n" + p);
			for(Review r : p.getReviewList()) {
				writer.println("\n" + r);
			}
		}
		
		writer.println("\nRetrieving Customer with ID: 1");
		Customer retC1 = HibernateDao.retrieveCustomer((long)1);
		writer.println("Retrieved Customer is: \n" + retC1 + "\n");

		writer.println("\nRetrieving Customer with ID: 2");
		Customer retC2 = HibernateDao.retrieveCustomer((long)2);
		writer.println("Retrieved Customer is: \n" + retC2 + "\n");
	}
}