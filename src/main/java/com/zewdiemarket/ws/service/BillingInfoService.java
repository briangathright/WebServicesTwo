package com.zewdiemarket.ws.service;

import java.util.Set;
import javax.jws.WebService;
import com.zewdiemarket.ws.service.representation.BillingInfoRepresentation;
import com.zewdiemarket.ws.service.representation.BillingInfoRequest;

/*
 * Interface for BillingInfoService
 */

@WebService
public interface BillingInfoService {

	public Set<BillingInfoRepresentation> getBillingInfos();
	public BillingInfoRepresentation getBillingInfo(String billingInfoId);
	public BillingInfoRepresentation createBillingInfo(BillingInfoRequest billingInfoRequest);

}
