package com.zewdiemarket.ws.dal;

import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.Session;
import com.zewdiemarket.ws.Customer;

public class CustomerDAO {
	/**
	 * Creates a session and adds the object to the database
	 */
	public static void addCustomer(Customer c) {
		try {
			System.err.println("%% Adding "+c.getClass().getSimpleName()+" in DB: " + c.toString());
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(c);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the object from the database
	 */
	public static void deleteCustomer(Customer c) {
		System.err.println("%% Deleting " + c.getClass().getSimpleName() + "in DB: " + c.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
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

	public static Set<Customer> getAllCustomers() {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();

			session.beginTransaction();

			Set<Customer> allCustomers = new LinkedHashSet<Customer>(session.createCriteria(Customer.class).list());

			session.getTransaction().commit();
			return allCustomers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Customer addNewCustomer(String customerName) {
		Customer c = new Customer();
		c.setName(customerName);
		addCustomer(c);
		return c;
	}
}
