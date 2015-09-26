package webservicetwo.webservicetwo;

import java.io.PrintWriter;

public class Client 
{
	PrintWriter writer;
	
	public Client(PrintWriter writer) {
		this.writer = writer;
	}
	public void run()
	{
		writer.println("Making Sellers (Partners)");
		writer.println("-------------------------");
		Seller s1 = new Seller("LuTech Inc.");
		writer.println("Created Seller: " + s1);
		Seller s2 = new Seller("Zewdie and Co.");
		writer.println("Created Seller: " + s2);
		Seller s3 = new Seller("Nema Nemati");
		writer.println("Created Seller: " + s3);
		
		writer.println("Making Products");
		writer.println("---------------");
		Product p1 = new Product("Lemon Shandy - 12pack");
		writer.println("Created Product: " + p1);
		Product p2 = new Product("Grapefruit Shandy - 12pack");
		writer.println("Created Product: " + p2);
		Product p3 = new Product("Merlot - 1 bottle");
		writer.println("Created Product: " + p3);
		Product p4 = new Product("White Zinfandel - 1 bottle");
		writer.println("Created Product: " + p4);
		Product p5 = new Product("Moonshine - 1 jar");
		writer.println("Created Product: " + p5);
		
		writer.println("Adding Products to Sellers");
		writer.println("--------------------------");
		s1.addProduct(p1);
		s1.addProduct(p2);
		writer.println(s1 + " sells " + p1 + " and " + p2);
		s2.addProduct(p3);
		s2.addProduct(p4);
		writer.println(s2 + " sells " + p3 + " and " + p4);
		s3.addProduct(p5);
		writer.println(s3 + " sells " + p5);
		
		writer.println("Making Customers");
		writer.println("----------------");
		Customer c1 = new Customer("Thor Odinson");
		writer.println("Created Customer: " + c1);
		Customer c2 = new Customer("Odin Allfather");
		writer.println("Created Customer: " + c2);
		
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
		HibernateDao dao = new HibernateDao();

		dao.add(s1);
		dao.add(s2);
		dao.add(s3);
		dao.add(p1);
		dao.add(p2);
		dao.add(p3);
		dao.add(p4);
		dao.add(p5);
		dao.add(c1);
		dao.add(c2);
		dao.add(r1);
		dao.add(r2);
		dao.add(r3);
		dao.add(r4);
		
		writer.println("Retrieving Some Items");
		writer.println("---------------------");
		writer.println("Retrieving Product with ID: 1");
		Product retP1 = dao.retrieveProduct((long)1);
		writer.println("Retrieved Product is: " + retP1);
		writer.println("Retrieving Seller with ID: 1");
		Seller retS1 = dao.retrieveSeller((long)1);
		writer.println("Retrieved Seller is: " + retS1);
		
	 	writer.println("Retrieved Product list for Retrieved Seller contains:  ");
		for(Product p : retS1.getProductList())
		{
			writer.println(p);
			for(Review r : p.getReviewList()) {
				writer.println(r);
			}
		}
	}
}