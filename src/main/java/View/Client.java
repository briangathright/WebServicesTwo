package View;

import java.io.PrintWriter;

import Data.HibernateDao;
import Model.Customer;
import Model.Product;
import Model.Review;
import Model.Seller;

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
		Product p1 = new Product("Lemon Shandy - 12pack");
		writer.println("Created Product: \n" + p1 + "\n");
		Product p2 = new Product("Grapefruit Shandy - 12pack");
		writer.println("Created Product: \n" + p2 + "\n");
		Product p3 = new Product("Merlot - 1 bottle");
		writer.println("Created Product: \n" + p3 + "\n");
		Product p4 = new Product("White Zinfandel - 1 bottle");
		writer.println("Created Product: \n" + p4 + "\n");
		Product p5 = new Product("Moonshine - 1 jar");
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

		writer.println("Making Reviews");
		writer.println("--------------");
		Review r1 = new Review(5, c1, "Just phenomenal!");
		Review r2 = new Review(2, c1, "It's pretty bad.");
		Review r3 = new Review(-5, c2, "Meh.");
		Review r4 = new Review(12, c2, "Outstanding!");

		writer.println("Assigning Reviews");
		writer.println("-----------------");
		p1.addReview(r1);
		p5.addReview(r2);
		p1.addReview(r3);
		s1.addReview(r4);

		writer.println("Storing Everything in Database");
		writer.println("------------------------------");

		HibernateDao.add(s1);
		HibernateDao.add(s2);
		HibernateDao.add(s3);
		HibernateDao.add(p1);
		HibernateDao.add(p2);
		HibernateDao.add(p3);
		HibernateDao.add(p4);
		HibernateDao.add(p5);
		HibernateDao.add(c1);
		HibernateDao.add(c2);
		HibernateDao.add(r1);
		HibernateDao.add(r2);
		HibernateDao.add(r3);
		HibernateDao.add(r4);

		writer.println("Retrieving Some Items");
		writer.println("---------------------");
		writer.println("Retrieving Product with ID: 1");
		Product retP1 = HibernateDao.retrieveProduct((long)1);
		writer.println("Retrieved Product is: \n" + retP1 + "\n");
		writer.println("Retrieving Seller with ID: 1");
		Seller retS1 = HibernateDao.retrieveSeller((long)1);
		writer.println("Retrieved Seller is: \n" + retS1);

		writer.println("Retrieved Product list for Retrieved Seller contains: ");
		for(Product p : retS1.getProductList()) {
			writer.println(p);
			for(Review r : p.getReviewList()) {
				writer.println(r);
			}
		}
	}
}