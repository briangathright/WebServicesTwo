package com.zewdiemarket.ws.dal;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.Session;

import com.zewdiemarket.ws.Order;
import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.Seller;

public class SellerDAO {
	
	/**
	 * Creates a session and adds the object to the database
	 */
	public static void addSeller(Seller s) {
		try {
			System.err.println("%% Adding "+s.getClass().getSimpleName()+" in DB: " + s.toString());
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(s);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the object from the database
	 */
	public static void deleteSeller(Seller s) {
		System.err.println("%% Deleting " + s.getClass().getSimpleName() + "in DB: " + s.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(s);
		session.getTransaction().commit();
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

	/*
	 * Get all sellsers in a linked hash set
	 */
	public static Set<Seller> getAllSellers() {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();

			session.beginTransaction();

			Set<Seller> allSellers = new LinkedHashSet<Seller>(session.createCriteria(Seller.class).list());

			session.getTransaction().commit();
			return allSellers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Set<Order> getSellerOrders(long id) {
		Seller s = retrieveSeller(id);
		Set<Order> sellerOrders = s.getOrderList();
		return sellerOrders;
	}

	public static Seller addNewSeller(String sellerName, String sellerPassword) {
		Seller s = new Seller();
		s.setName(sellerName);
		s.setPassword(sellerPassword);
		s.setOrderList(new HashSet<Order>());
		s.setProductList(new HashSet<Product>());
		s.setReviewList(new HashSet<Review>());
		addSeller(s);
		return s;
	}
}
