package com.zewdiemarket.ws.dal;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;

import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.Seller;

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

	/*
	 * Add a new product to teh database
	 */
	public static Product addNewProduct(String detail, double price, String sellerId){
		Product p = new Product();
		Seller s = SellerDAO.retrieveSeller(Long.parseLong(sellerId));
		p.setDetail(detail);
		p.setPrice(price);
		p.setSeller(s);
		p.setReviewList(new HashSet<Review>());
		s.addProduct(p);
		addProduct(p);
		SellerDAO.addSeller(s);
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
	/*
	 * Get all Products in a LinkedHashSet
	 */
	public static Set<Product> getAllProducts(){
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			
			session.beginTransaction();
			
			Set<Product> allProducts = new TreeSet<Product>(session.createCriteria(Product.class).list());
			
			session.getTransaction().commit();
			return allProducts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}