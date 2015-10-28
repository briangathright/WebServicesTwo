package com.zewdiemarket.ws.dal;

import org.hibernate.Session;
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
}
