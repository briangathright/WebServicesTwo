package com.zewdiemarket.ws.dal;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Order;
import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.Seller;
import com.zewdiemarket.ws.BillingInfo;

/**
 * This is our Hibernate Data Access Object. 
 * This is our wrapper for interaction with the database
 */
public class HibernateDao {

	/**
	 * Creates a session and adds the object to the database
	 */
	public static void add(Object o) {
		try {
			System.err.println("%% Adding "+o.getClass().getSimpleName()+" in DB: " + o.toString());
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the object from the database
	 */
	public static void delete(Object o) {
		System.err.println("%% Deleting " + o.getClass().getSimpleName() + "in DB: " + o.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
	}

	/**
	 *Retrieves a product from the database.
	 */
	public static Product retrieveProduct(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Product prod = session.get(Product.class, id);

			session.getTransaction().commit();
			return prod;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 *Retrieves a customer from the database
	 */
	public static Customer retrieveCustomer(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Customer cust = session.get(Customer.class, id);

			session.getTransaction().commit();
			return cust;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Retrieve a review from the database
	 **/

	public static Review retrieveReview(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Review review = session.get(Review.class, id);

			session.getTransaction().commit();
			return review;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Retrieves a seller from the database
	 */
	
	public static Seller retrieveSeller(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Seller seller = session.get(Seller.class, id);

			session.getTransaction().commit();
			return seller;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Retrieve a BillingInfo object from the database
	 */
	
	public static BillingInfo retrieveBillingInfo(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			BillingInfo bi = session.get(BillingInfo.class, id);

			session.getTransaction().commit();
			return bi;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Retrieve an order from the database 
	 */
	public static Order retrieveOrder(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Order o = session.get(Order.class, id);

			session.getTransaction().commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Set<Product> retrieveProducts(){
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			
			session.beginTransaction();
			
			Set<Product> allProducts = new LinkedHashSet<Product>(session.createCriteria(Product.class).list());
			
			session.getTransaction().commit();
			return allProducts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}