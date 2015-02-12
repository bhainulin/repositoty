package org.shop.api.impl;

import java.util.List;

import org.shop.api.ProductService;
import org.shop.data.Product;
import org.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.repository = productRepository;
    }
    
    public Product getProductById(Long id) {
        return repository.getProductById(id);
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }

  
    public List<Product> getProductsByName(String name) {
        return repository.getProductsByName(name);
    }

    public Long createProduct(Product product) {
        return repository.createProduct(product);
    }

    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    public void deleteProduct(Long productId) {
        repository.deleteProduct(productId);
    }
}
