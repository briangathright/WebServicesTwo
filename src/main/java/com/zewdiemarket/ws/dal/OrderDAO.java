package com.zewdiemarket.ws.dal;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import com.zewdiemarket.ws.Order;

public class OrderDAO {
	
	/**
	 * Creates a session and adds the object to the database
	 */
	public static void addOrder(Order o) {
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
	public static void deleteOrder(Order o) {
		System.err.println("%% Deleting " + o.getClass().getSimpleName() + "in DB: " + o.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
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

	public static Set<Order> getAllOrders() {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();

			session.beginTransaction();

			Set<Order> allOrders = new LinkedHashSet<Order>(session.createCriteria(Order.class).list());

			session.getTransaction().commit();
			return allOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Order addNewOrder(String status) {
		Order o = new Order();
		o.setStatus(status);
		addOrder(o);
		return o;
	}
}
