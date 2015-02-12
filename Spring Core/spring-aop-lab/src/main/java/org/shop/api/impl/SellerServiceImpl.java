package org.shop.api.impl;

import java.util.List;

import org.shop.api.SellerService;
import org.shop.data.Seller;
import org.shop.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    
	@Autowired
    private SellerRepository repository;

    public List<Seller> getSellers() {
        return repository.getSellers();
    }

    public Seller getSellerById(Long sellerId) {
        return repository.getSellerById(sellerId);
    }

    public void importSellers(List<Seller> sellers) {
        for (Seller seller : sellers) {
            repository.createOrUpdateSeller(seller);
        }
    }
}
