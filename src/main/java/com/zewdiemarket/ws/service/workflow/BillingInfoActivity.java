package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.BillingInfo;
import com.zewdiemarket.ws.dal.BillingInfoDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
/*
 * Activity for BillingInfo - we get, create, and delete BillingInfo representations
 */
public class BillingInfoActivity {

	public Set<BillingInfoRepresentation> getBillingInfos() {
		Set<BillingInfo> billingInfos = new HashSet<BillingInfo>();
		Set<BillingInfoRepresentation> billInfoReps = new HashSet<BillingInfoRepresentation>();
		billingInfos = BillingInfoDAO.getAllBillingInfos();
		for(BillingInfo bi : billingInfos){
			BillingInfoRepresentation billInfoRep = new BillingInfoRepresentation();
			billInfoRep.setID(bi.getID());
			billInfoRep.setBillingName(bi.getBillingName());
			billInfoRep.setCardNumber(bi.getCardNumber());
			billInfoReps.add(billInfoRep);
		}
		return billInfoReps;
	}

	public BillingInfoRepresentation getBillingInfo(String id) {
		BillingInfo bi =  BillingInfoDAO.retrieveBillingInfo(Long.parseLong(id));
		BillingInfoRepresentation billInfoRep = new BillingInfoRepresentation();
		billInfoRep.setID(bi.getID());
		billInfoRep.setBillingName(bi.getBillingName());
		billInfoRep.setCardNumber(bi.getCardNumber());
		return billInfoRep;
	}

	public BillingInfoRepresentation createBillingInfo(String billingName, String cardNumber) {
		BillingInfo bi = BillingInfoDAO.addNewBillingInfo(billingName, cardNumber);
		BillingInfoRepresentation billInfoRep = new BillingInfoRepresentation();
		billInfoRep.setID(bi.getID());
		billInfoRep.setBillingName(bi.getBillingName());
		billInfoRep.setCardNumber(bi.getCardNumber());
		return billInfoRep;
	}

	public String deleteBillingInfo(String id) {
		BillingInfoDAO.deleteBillingInfo(BillingInfoDAO.retrieveBillingInfo(Long.parseLong(id)));
		return "OK";
	}
}
