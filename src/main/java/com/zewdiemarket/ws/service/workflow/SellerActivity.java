package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.Seller;
import com.zewdiemarket.ws.dal.SellerDAO;
import com.zewdiemarket.ws.service.representation.SellerRepresentation;

/*
 * Activity for Seller - we get, create, and delete Seller representations
 */
public class SellerActivity {

	public Set<SellerRepresentation> getSellers() {
		Set<Seller> sellers = new HashSet<Seller>();
		Set<SellerRepresentation> sellerReps = new HashSet<SellerRepresentation>();
		sellers = SellerDAO.getAllSellers();
		for(Seller s : sellers){
			SellerRepresentation sellerRep = new SellerRepresentation();
			sellerRep.setID(s.getID());
			sellerRep.setSellerName(s.getName());
			sellerReps.add(sellerRep);
		}
		return sellerReps;
	}

	public SellerRepresentation getSeller(String id) {
		Seller s = SellerDAO.retrieveSeller(Long.parseLong(id));
		SellerRepresentation sellerRep = new SellerRepresentation();
		sellerRep.setID(s.getID());
		sellerRep.setSellerName(s.getName());
		return sellerRep;
	}

	public SellerRepresentation createSeller(String sellerName) {
		Seller s = SellerDAO.addNewSeller(sellerName);
		SellerRepresentation sellerRep = new SellerRepresentation();
		sellerRep.setID(s.getID());
		sellerRep.setSellerName(s.getName());
		return sellerRep;
	}

	public String deleteSeller(String id) {
		SellerDAO.deleteSeller(SellerDAO.retrieveSeller(Long.parseLong(id)));
		return "OK";
	}	
}