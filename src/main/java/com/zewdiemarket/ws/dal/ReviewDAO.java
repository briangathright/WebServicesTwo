package com.zewdiemarket.ws.dal;

import org.hibernate.Session;
import com.zewdiemarket.ws.Review;

public class ReviewDAO {

	/**
	 * Creates a session and adds the object to the database
	 */
	public static void addReview(Review r) {
		try {
			System.err.println("%% Adding "+r.getClass().getSimpleName()+" in DB: " + r.toString());
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(r);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the object from the database
	 */
	public static void deleteReview(Review r) {
		System.err.println("%% Deleting " + r.getClass().getSimpleName() + "in DB: " + r.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(r);
		session.getTransaction().commit();
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
}
