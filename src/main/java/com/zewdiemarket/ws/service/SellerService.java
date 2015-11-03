package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.jws.WebService;
import com.zewdiemarket.ws.service.representation.SellerRepresentation;
import com.zewdiemarket.ws.service.representation.SellerRequest;

/*
 * Interface for SellerService
 */
@WebService
public interface SellerService {

	public Set<SellerRepresentation> getSellers();
	public SellerRepresentation getSeller(String sellerId);
	public SellerRepresentation createSeller(SellerRequest sellerRequest);

}