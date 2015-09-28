package Data;

import org.hibernate.Session;

import Model.Customer;
import Model.Product;
import Model.Review;
import Model.Seller;

public class HibernateDao {

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

	public static void delete(Object o) {
		System.err.println("%% Deleting " + o.getClass().getSimpleName() + "in DB: " + o.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
	}

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