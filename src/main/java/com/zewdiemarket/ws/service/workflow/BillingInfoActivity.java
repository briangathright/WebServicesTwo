package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.BillingInfo;
import com.zewdiemarket.ws.dal.BillingInfoDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.BillingInfoRequest;
import com.zewdiemarket.ws.service.representation.Link;
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
			setLinks(billInfoRep);
		}
		return billInfoReps;
	}

	public BillingInfoRepresentation getBillingInfo(String id) {
		BillingInfo bi =  BillingInfoDAO.retrieveBillingInfo(Long.parseLong(id));
		BillingInfoRepresentation billInfoRep = new BillingInfoRepresentation();
		billInfoRep.setID(bi.getID());
		billInfoRep.setBillingName(bi.getBillingName());
		billInfoRep.setCardNumber(bi.getCardNumber());
		setLinks(billInfoRep);
		return billInfoRep;
	}

	public String updateBillingInfo(String billingId, BillingInfoRequest billingInfoRequest) {
		BillingInfo bi = BillingInfoDAO.updateBillingInfo(Long.parseLong(billingId), billingInfoRequest.getCardType(), billingInfoRequest.getBillingName(),
				billingInfoRequest.getBillingAddress(), billingInfoRequest.getCardNumber(), billingInfoRequest.getExpDate(), billingInfoRequest.getCvcNumber());
		return "OK";
	}

	public String deleteBillingInfo(String id) {
		BillingInfoDAO.deleteBillingInfo(BillingInfoDAO.retrieveBillingInfo(Long.parseLong(id)));
		return "OK";
	}
	
	public void setLinks(BillingInfoRepresentation billInfoRep){
		Link[] links = new Link[1];
		Link update_info = new Link("update_info", System.getenv("BILLINGINFOSERVICE_URL") + billInfoRep.getID());
		links[0] = update_info;
		billInfoRep.setLinks(links);
	}

	public BillingInfoRepresentation createBillingInfo(String billingName, String cardNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
