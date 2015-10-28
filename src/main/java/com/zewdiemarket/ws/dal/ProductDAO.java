package com.zewdiemarket.ws.dal;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;

import com.zewdiemarket.ws.Product;

public class ProductDAO {
		
	/**
	 * Creates a session and adds the object to the database
	 */
	public static void addProduct(Product p) {
		try {
			System.err.println("%% Adding "+p.getClass().getSimpleName()+" in DB: " + p.toString());
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(p);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Product addNewProduct(String detail, double price){
		Product p = new Product();
		p.setDetail(detail);
		p.setPrice(price);
		addProduct(p);
		return p;
	}
	
	/**
	 * Deletes the object from the database
	 */
	public static void deleteProduct(Product p) {
		System.err.println("%% Deleting " + p.getClass().getSimpleName() + "in DB: " + p.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(p);
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
	
	public static Set<Product> getAllProducts(){
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