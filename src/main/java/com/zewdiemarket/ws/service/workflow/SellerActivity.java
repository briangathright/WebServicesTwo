package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;
import com.zewdiemarket.ws.Seller;
import com.zewdiemarket.ws.dal.SellerDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.Link;
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

	public void setLinks(SellerRepresentation sellerRep){
		Link[] links = new Link[3];
		Link add_product = new Link("add product", System.getenv("PRODUCTSERVICE_URL"));
		Link view_reviews = new Link("view reviews", System.getenv("PRODUCTSERVICE_URL"));
		Link fulfill_order = new Link("fulfill order", System.getenv("PRODUCTSERVICE_URL"));
		links[0] = add_product;
		links[1] = view_reviews;
		links[3] = fulfill_order;
		sellerRep.setLinks(links);
	}
}