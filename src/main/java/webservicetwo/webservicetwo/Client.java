package webservicetwo.webservicetwo;

import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Client 
{
	PrintWriter writer;
	
	public Client(PrintWriter writer) {
		this.writer = writer;
	}
	public void run(String[] args)
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
		
		
		writer.println("Storing products in Database!");
		HibernateDao dao = new HibernateDao();

		dao.add(c1);
		dao.add(c2);
		dao.add(p1);
		dao.add(p2);
		
		writer.println("Stored products and customers");
		writer.println("Adding products to the customers' productlists!");
		c1.addProduct(p1);
		c1.addProduct(p2);
		writer.println("Added products " + c1.getProductList() + " to Customer " + c1.getName() + ".");
		dao.add(c1);
		dao.add(c2);
		
//		Product retP1 = (Product)session.get(p1.getClass(), (long)1);
//		writer.println("retrieved product is: " + retP1.getID() + " " + retP1.getDetail());
//		Customer retC1 = (Customer)session.get(c1.getClass(), (long)1);
//		writer.println("retreived customer is: " + retC1.getID() + " " + retC1.getName());
////		writer.println("Retrieved products: ");
//		for(Product p : retC1.getProductList())
//		{
//			System.out.println(p.getDetail());
//		}
	}
}