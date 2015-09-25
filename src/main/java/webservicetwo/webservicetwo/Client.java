package webservicetwo.webservicetwo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Client 
{
	public static void run(String[] args)
	{
		System.out.println("Making products!");
		Product p1 = new Product("Meaning of Life");
		System.out.println("Made product with Detail: " + p1.getDetail() + ".");
		Product p2 = new Product("Meaning of Death");
		System.out.println("Made product with Detail: " + p2.getDetail() + ".");
		
		System.out.println("Making customers!");
		Customer c1 = new Customer("Robert Yacobellis");
		System.out.println("Made customer with Name: " + c1.getName() + ".");
		Customer c2 = new Customer("George Thiruvathukal");
		System.out.println("Made customer with Name: " + c2.getName() + ".");
		
		
		System.out.println("Storing products in Database!");
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(c1);
		session.save(c2);
		session.save(p1);
		session.save(p2);
		session.getTransaction().commit();
		
		System.out.println("Stored products and customers");
		System.out.println("Adding products to the customers' productlists!");
		c1.addProduct(p1);
		c1.addProduct(p2);
		System.out.println("Added products " + c1.getProductList() + " to Customer " + c1.getName() + ".");
		session.beginTransaction();
		session.save(c1);
		session.save(c2);
		session.getTransaction().commit();
		
		session.beginTransaction();
		Product retP1 = (Product)session.get(p1.getClass(), (long)1);
		System.out.println("retrieved product is: " + retP1.getID() + " " + retP1.getDetail());
		Customer retC1 = (Customer)session.get(c1.getClass(), (long)1);
		System.out.println("retreived customer is: " + retC1.getID() + " " + retC1.getName());
		session.close();
		sf.close();
		System.out.println("Retrieved products: ");
		for(Product p : retC1.getProductList())
		{
			System.out.println(p.getDetail());
		}
	}
}