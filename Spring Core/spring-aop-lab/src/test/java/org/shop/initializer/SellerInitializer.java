package org.shop.initializer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.shop.api.SellerService;
import org.shop.data.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Seller Initializer util class.
 */
@Component
public class SellerInitializer {

	private Map<Long, String> sellerNames;

	@Autowired
	private SellerService sellerService;

	public SellerInitializer() {
		sellerNames = new HashMap<Long, String>();

		sellerNames.put(1l, org.shop.common.Sellers.AMAZON);
		sellerNames.put(2l, org.shop.common.Sellers.SAMSUNG);
		sellerNames.put(3l, "Apple");
	}

	public Map<Long, String> getSellerNames() {
		return sellerNames;
	}

	public void setSellerNames(Map<Long, String> sellerNames) {
		this.sellerNames = sellerNames;
	}

	public void initSellers() {
		List<Seller> sellers = new LinkedList<Seller>();
		
		for (Map.Entry<Long, String> entry : sellerNames.entrySet()) {
			Seller seller = new Seller();
			seller.setId(entry.getKey());
			seller.setName(entry.getValue());
			sellers.add(seller);
		}
		sellerService.importSellers(sellers);
	}
}
