package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * BillingInfo: Class that represents billing information for customers
 */
@Entity
@Table(name = "billinginfo")
public class BillingInfo implements Serializable { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billingInfo_id")
	private long billingInfo_id;

	@Column(name = "cardType")
	private String cardType;

	@Column(name = "billingName") 
	private String billingName;

	@Column(name = "billingAddress")
	private String billingAddress;

	@Column(name = "cardNumber")
	private String cardNumber;

	@Column(name = "expDate")
	private String expDate;
	
	/**
	 * Hibernate requires an empty constructor
	 */
	@Column(name = "cvcNumber")
	private String cvcNumber;
	public BillingInfo() {

	}
	/**
	 * 
	 * @param cardType - the customer's cardtype (MasterCards, VISA, etc.)
	 * @param billingName - the name associated with the customer's billing info
	 * @param billingAddress - customer's billing address
	 * @param cardNumber - customer's payment card number
	 * @param expDate - customer's payment expiration date
	 * @param cvcNumber - customer's payment card CVC number
	 */
	public BillingInfo(String cardType, String billingName, String billingAddress, String cardNumber, String expDate, String cvcNumber) {
		this.cardType = cardType;
		this.billingName = billingName;
		this.billingAddress = billingAddress;
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.cvcNumber = cvcNumber;
	}

	public long getID() {
		return billingInfo_id;
	}

	public void setID(long id) {
		this.billingInfo_id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCvcNumber() {
		return cvcNumber;
	}

	public void setCvcNumber(String cvcNumber) {
		this.cvcNumber = cvcNumber;
	}
	/**
	 * toString method that allows us to show that our application works when testing its output in the client
	 */
	@Override
	public String toString() {
		return "\nCardType: " + cardType +
				"\nBilling Name: " + billingName + 
				"\nBilling Address: " + billingAddress +
				"\nCard Number: " + cardNumber +
				"\nExpiration Date: " + expDate +
				"\nCVC Number: " + cvcNumber;
	}
}
