package com.zewdiemarket.ws.service.workflow;

import java.util.HashSet;
import java.util.Set;

import com.zewdiemarket.ws.Customer;
import com.zewdiemarket.ws.Seller;
import com.zewdiemarket.ws.dal.CustomerDAO;
import com.zewdiemarket.ws.dal.SellerDAO;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.CustomerRepresentation;
import com.zewdiemarket.ws.service.representation.Link;
import com.zewdiemarket.ws.service.representation.SellerRepresentation;
import com.zewdiemarket.ws.service.representation.SellerRequest;

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
			setLinks(sellerRep);

		}
		return sellerReps;
	}

	public SellerRepresentation getSeller(String id) {
		Seller s = SellerDAO.retrieveSeller(Long.parseLong(id));
		SellerRepresentation sellerRep = new SellerRepresentation();
		sellerRep.setID(s.getID());
		sellerRep.setSellerName(s.getName());
		setLinks(sellerRep);

		return sellerRep;
	}
	
	public SellerRepresentation login(String sellerName, String sellerPassword) {
		Set<Seller> sellers = new HashSet<Seller>();
		sellers = SellerDAO.getAllSellers();
		for(Seller s : sellers){
			if(s.getName().equals(sellerName) && s.getPassword().equals(sellerPassword)){
				return getSeller(String.valueOf(s.getID()));
			}
		}
		return null;
	}

	public String createSeller(SellerRequest sellerRequest) {
		Seller s = SellerDAO.addNewSeller(sellerRequest.getSellerName(), sellerRequest.getSellerPass());
		return "OK";
	}

	public String deleteSeller(String id) {
		SellerDAO.deleteSeller(SellerDAO.retrieveSeller(Long.parseLong(id)));
		return "OK";
	}

	public void setLinks(SellerRepresentation sellerRep){
		Link[] links = new Link[4];
		Link add_product = new Link("add product", System.getenv("PRODUCTSERVICE_URL") + "seller/" + sellerRep.getID());
		Link updatePass = new Link("update_password", System.getenv("SELLERSERVICE_URL") + sellerRep.getID() + "/password/");
		Link view_reviews = new Link("view reviews", System.getenv("REVIEWSERVICE_URL") + "sellerreviews/" + sellerRep.getID());
		Link view_orders = new Link("view orders", System.getenv("ORDERSERVICE_URL") + "sellerorders/" + sellerRep.getID());
		links[0] = add_product;
		links[1] = updatePass;
		links[2] = view_reviews;
		links[3] = view_orders;
		sellerRep.setLinks(links);
	}

	public SellerRepresentation updateSellerPassword(String sellerId, String pass) {
		Seller s = SellerDAO.retrieveSeller(Long.parseLong(sellerId));
		s.setPassword(pass);
		SellerDAO.addSeller(s);
		return this.getSeller(sellerId);
	}
}