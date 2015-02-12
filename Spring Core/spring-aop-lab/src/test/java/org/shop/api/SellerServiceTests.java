package org.shop.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:context.xml",
		"classpath:data-initializer.xml" })
public class SellerServiceTests {

	@Autowired
	ProposalService proposalService;

	@Autowired
	SellerService sellerService;

	@Autowired
	ProductService productService;

	@Test
	public void testGetSellerById() {
		Seller seller1 = sellerService.getSellerById(1L);

		assertNotNull(seller1);
		assertEquals(new Long(1L), seller1.getId());
		assertEquals("Amazon", seller1.getName());

		Seller seller2 = sellerService.getSellerById(2L);

		assertNotNull(seller2);
		assertEquals(new Long(2L), seller2.getId());
		assertEquals("Samsung", seller2.getName());

		Seller seller3 = sellerService.getSellerById(3L);

		assertNotNull(seller3);
		assertEquals(new Long(3L), seller3.getId());
		assertEquals("Apple", seller3.getName());

		Seller seller4 = sellerService.getSellerById(1500L);
		assertNull(seller4);
	}

	@Test
	public void testGetSellers() {
		List<Seller> sellers = sellerService.getSellers();
		assertNotNull(sellers);
		assertTrue(sellers.size() >= 3);

		for (Seller seller : sellers) {
			if(seller.getId() == 1){
				assertEquals("Amazon", seller.getName());
			}else if(seller.getId() == 2){
				assertEquals("Samsung", seller.getName());
			}else if(seller.getId() == 3){
				assertEquals("Apple", seller.getName());
			}
		}
	}

	
	  @Test public void testImportSellers(){
		  Seller seller1 = new Seller();
		  seller1.setName("Seller1");
		  
		  Seller seller2 = new Seller();
		  seller2.setName("Seller2");
		  
		  Seller seller3 = new Seller();
		  seller3.setName("Seller3");
		  
		  List<Seller> sellers = new ArrayList<Seller>();
		  sellers.add(seller1);
		  sellers.add(seller2);
		  sellers.add(seller3);
		  sellerService.importSellers(sellers); 
		  
		  List<Seller> actualSellers = sellerService.getSellers();
		  
		  assertNotNull(actualSellers);
		  assertTrue(actualSellers.size() >= 3);
		  
		  assertTrue(sellers.contains(seller1));
		  assertTrue(sellers.contains(seller2));
		  assertTrue(sellers.contains(seller3));
	  }
	 

}
