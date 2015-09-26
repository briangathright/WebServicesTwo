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
		writer.println("Making products!");
		Product p1 = new Product("Meaning of Life");
		writer.println("Made product with Detail: " + p1.getDetail() + ".");
		Product p2 = new Product("Meaning of Death");
		writer.println("Made product with Detail: " + p2.getDetail() + ".");
		
		writer.println("Making customers!");
		Customer c1 = new Customer("Robert Yacobellis");
		writer.println("Made customer with Name: " + c1.getName() + ".");
		Customer c2 = new Customer("George Thiruvathukal");
		writer.println("Made customer with Name: " + c2.getName() + ".");
		
		writer.println("Making some Reviews!");
		Review r1 = new Review(5, c2, "Just phenomenal!");
		
		writer.println("Storing products in Database!");
		HibernateDao dao = new HibernateDao();

		dao.add(c1);
		dao.add(c2);
		dao.add(p1);
		dao.add(p2);
		dao.add(r1);
		
		writer.println("Stored products and customers");
		writer.println("Adding products to the customers' productlists!");
		c1.addProduct(p1);
		c1.addProduct(p2);

		writer.println("Adding review to a product");
		
		p1.addReview(r1);
		
		dao.add(p1);
		dao.add(c1);
		dao.add(c2);
		
		writer.println("Retrieving Items");
		Product retP1 = dao.retrieveProduct((long)1);
		writer.println("retrieved product is: " + retP1.getID() + " " + retP1.getDetail());
		Customer retC1 = dao.retrieveCustomer((long)1);
		
		writer.println("retreived customer is: " + retC1.getID() + " " + retC1.getName());
 		writer.println("Retrieved products: ");
		for(Product p : retC1.getProductList())
		{
			writer.println(p);
			for(Review r : p.getReviewList()) {
				writer.println(r);
			}
		}
	}
}