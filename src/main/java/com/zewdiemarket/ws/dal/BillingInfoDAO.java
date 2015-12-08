package com.zewdiemarket.ws.dal;

import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.Session;
import com.zewdiemarket.ws.BillingInfo;

public class BillingInfoDAO {

	/**
	 * Creates a session and adds the object to the database
	 */
	public static void addBillingInfo(BillingInfo b) {
		try {
			System.err.println("%% Adding "+b.getClass().getSimpleName()+" in DB: " + b.toString());
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(b);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the object from the database
	 */
	public static void deleteBillingInfo(BillingInfo b) {
		System.err.println("%% Deleting " + b.getClass().getSimpleName() + "in DB: " + b.toString());
		Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
	}

	/**
	 * Retrieve a BillingInfo object from the database
	 */

	public static BillingInfo retrieveBillingInfo(long id) {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			BillingInfo bi = session.get(BillingInfo.class, id);

			session.getTransaction().commit();
			return bi;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Gets all Billing infos in a LinkedHashSet
	 */
	public static Set<BillingInfo> getAllBillingInfos() {
		try {
			Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();

			session.beginTransaction();

			Set<BillingInfo> allBillingInfos = new LinkedHashSet<BillingInfo>(session.createCriteria(BillingInfo.class).list());

			session.getTransaction().commit();
			return allBillingInfos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BillingInfo createBillingInfo(){
		BillingInfo bi = new BillingInfo();
		addBillingInfo(bi);
		return bi;
	}

	/*
	 * Adds new Billing Info to the database
	 */
	public static BillingInfo updateBillingInfo(Long billingId,
			String cardType, String billingName, 
			String billingAddress, String cardNumber,
			String expDate, String cvcNumber) {
		BillingInfo bi = retrieveBillingInfo(billingId);
		bi.setCardType(cardType);
		bi.setBillingName(billingName);
		bi.setBillingAddress(billingAddress);
		bi.setCardNumber(cardNumber);
		bi.setExpDate(expDate);
		bi.setCvcNumber(cvcNumber);
		addBillingInfo(bi);
		return bi;
	}
}
