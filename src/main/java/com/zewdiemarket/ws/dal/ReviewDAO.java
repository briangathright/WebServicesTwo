package com.zewdiemarket.ws.dal;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;

import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Order;
import com.zewdiemarket.ws.Product;
import com.zewdiemarket.ws.Review;
import com.zewdiemarket.ws.Seller;

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

	/*
	 * Get all reviews in a LinkedHashSet
	 */
	public static Set<Review> getAllReviews(){
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			
			session.beginTransaction();
			
			Set<Review> allReviews = new TreeSet<Review>(session.createCriteria(Review.class).list());
			
			session.getTransaction().commit();
			return allReviews;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Add a new review to the database
	 */
	public static Review addNewReview(String reviewDetail, float rating, Customer customer) {
		Review r = new Review();
		r.setReviewDetail(reviewDetail);
		r.setRating(rating);
		r.setCustomer(customer);
		addReview(r);
		return r;
	}

	public static Set<Review> getSellerReviews(long id) {
		return SellerDAO.getSellerReviews(id);
	}

	public static Review addNewSellerReview(String sellerId, String reviewDetail, double rating, String customerId) {
		Review r = new Review();
		r.setCustomer(CustomerDAO.retrieveCustomer(Long.parseLong(customerId)));
		r.setRating(rating);
		r.setReviewDetail(reviewDetail);
		Seller s = SellerDAO.retrieveSeller(Long.parseLong(sellerId));
		s.addReview(r);
		addReview(r);
		SellerDAO.addSeller(s);
		return null;
	}

	public static Review addNewProductReview(String productId, String reviewDetail, double rating, String customerId) {
		Review r = new Review();
		r.setCustomer(CustomerDAO.retrieveCustomer(Long.parseLong(customerId)));
		r.setRating(rating);
		r.setReviewDetail(reviewDetail);
		Product p = ProductDAO.retrieveProduct(Long.parseLong(productId));
		p.addReview(r);
		addReview(r);
		//ProductDAO.addProduct(p);
		return null;
	}

	public static Set<Review> getProductReviews(long id) {
		return ProductDAO.getProductReviews(id);
	}
}


