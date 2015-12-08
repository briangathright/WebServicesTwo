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


	public static Review addNewSellerReview(String sellerId, String reviewDetail, double rating, String customerId) {
		Review r = new Review();
		r.setCustomer(CustomerDAO.retrieveCustomer(Long.parseLong(customerId)));
		r.setRating(rating);
		r.setReviewDetail(reviewDetail);
		Seller s = SellerDAO.retrieveSeller(Long.parseLong(sellerId));
		s.addReview(r);
		r.setSeller(s);
		addReview(r);
		SellerDAO.addSeller(s);
		return r;
	}

	public static Review addNewProductReview(String productId, String reviewDetail, double rating, String customerId) {
		Review r = new Review();
		Customer c = CustomerDAO.retrieveCustomer(Long.parseLong(customerId));
		r.setCustomer(c);
		r.setRating(rating);
		r.setReviewDetail(reviewDetail);
		Product p = ProductDAO.retrieveProduct(Long.parseLong(productId));
		p.addReview(r);
		addReview(r);
		r.setProduct(p);
		ProductDAO.addProduct(p);
		CustomerDAO.addCustomer(c);
		return r;
	}

	public static Set<Review> getProductReviews(long id) {
		Set<Review> allReviews = getAllReviews();
		Set<Review> productReviews = new LinkedHashSet<Review>();
		for(Review r : allReviews){
			if(r.getProduct().getID() == id){
				productReviews.add(r);
			}
		}
		return productReviews;	
	}

	public static Set<Review> getSellerReviews(long id) {
		Set<Review> allReviews = getAllReviews();
		Set<Review> sellerReviews = new LinkedHashSet<Review>();
		for(Review r : allReviews){
			if(r.getSeller().getID() == id){
				sellerReviews.add(r);
			}
		}
		return sellerReviews;	
	}

}


