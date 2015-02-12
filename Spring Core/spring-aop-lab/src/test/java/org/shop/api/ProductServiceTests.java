package org.shop.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:context.xml",
		"classpath:data-initializer.xml" })
public class ProductServiceTests {

	@Autowired
	ProductService productService;
	
	@Test
	public void testCreateProduct(){
		Product product = new Product();
		product.setId(1l);
		product.setName("1");
		product.setDescription("1");
		
		productService.createProduct(product);
	}
	
	@Test
	public void testgetProductById(){
		Product product = new Product();
		product.setId(2l);
		product.setName("2");
		product.setDescription("2");
		
		productService.createProduct(product);
		
		Product product2 = productService.getProductById(product.getId());
		
		assertEquals(product.getId(), product2.getId());
		assertEquals(product.getName(), product2.getName());
		assertEquals(product.getDescription(), product2.getDescription());
	}
	
	@Test
	public void testGetProducts(){	
		List<Product> products = productService.getProducts();	
		assertNotNull(products);
		assertTrue(products.size() != 0);
	}
	
	@Test
	public void testGetProductsByName(){
		Product product1 = new Product();
		product1.setName("Name");
		Product product2 = new Product();
		product2.setName("Name");
		
		productService.createProduct(product1);
		productService.createProduct(product2);
		
		List<Product> products = productService.getProductsByName("Name");	
		assertNotNull(products);
		assertTrue(products.size() != 0);
		
		for(Product product : products){
			assertEquals("Name", product.getName());
		}
	}
}
