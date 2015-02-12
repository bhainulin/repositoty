package org.shop.api;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.shop.data.Seller;
import org.shop.data.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:context.xml",
		"classpath:data-initializer.xml" })
public class ProposalServiceTest {
	@Autowired
	ProposalService proposalService;

	@Autowired
	SellerService sellerService;

	@Autowired
	ProductService productService;

	@Test
	public void testCreateProposal() {
		Seller seller = sellerService.getSellerById(1l);
		assertNotNull(seller);

		Product product = new Product();
		product.setName("Product1");
		product.setDescription("Description");

		productService.createProduct(product);

		Long proposalId = proposalService.createProposal(seller.getId(),
				product.getId(), 100d);

		assertNotNull(proposalId);

		List<Proposal> proposals = proposalService
				.getProposalsByProductId(product.getId());

		assertNotNull(proposals);
		assertEquals(1, proposals.size());

		Proposal proposal = proposals.get(0);

		assertEquals(proposalId, proposal.getId());
		assertEquals(seller.getId(), proposal.getSeller().getId());
		assertEquals(State.NOT_ACTIVE_PROPOSAL, proposal.getState());
	}

	@Test
	public void testAtivateProposal() {
		Seller seller = sellerService.getSellerById(1l);
		assertNotNull(seller);

		Product product = new Product();
		product.setName("Product2");
		product.setDescription("Description");

		productService.createProduct(product);

		Long proposalId = proposalService.createProposal(seller.getId(),
				product.getId(), 100d);

		assertNotNull(proposalId);

		List<Proposal> proposals = proposalService
				.getProposalsByProductId(product.getId());

		assertNotNull(proposals);
		assertEquals(1, proposals.size());

		Proposal proposal = proposals.get(0);

		assertEquals(proposalId, proposal.getId());
		assertEquals(seller.getId(), proposal.getSeller().getId());
		assertEquals(State.NOT_ACTIVE_PROPOSAL, proposal.getState());

		proposalService.activateProposal(proposalId);

		proposals = proposalService.getProposalsByProductId(product.getId());

		assertNotNull(proposals);
		assertEquals(1, proposals.size());

		proposal = proposals.get(0);

		assertEquals(State.ACTIVE_PROPOSAL, proposal.getState());
	}

	@Test
	public void testDeactivateProposal() {
		Seller seller = sellerService.getSellerById(1l);
		assertNotNull(seller);

		Product product = new Product();
		product.setName("Product3");
		product.setDescription("Description");

		productService.createProduct(product);

		Long proposalId = proposalService.createProposal(seller.getId(),
				product.getId(), 100d);

		assertNotNull(proposalId);

		List<Proposal> proposals = proposalService
				.getProposalsByProductId(product.getId());

		assertNotNull(proposals);
		assertEquals(1, proposals.size());

		Proposal proposal = proposals.get(0);

		assertEquals(proposalId, proposal.getId());
		assertEquals(seller.getId(), proposal.getSeller().getId());
		assertEquals(State.NOT_ACTIVE_PROPOSAL, proposal.getState());

		proposalService.activateProposal(proposalId);

		proposals = proposalService.getProposalsByProductId(product.getId());

		assertNotNull(proposals);
		assertEquals(1, proposals.size());

		proposal = proposals.get(0);

		assertEquals(State.ACTIVE_PROPOSAL, proposal.getState());

		proposalService.deactivateProposal(proposalId);

		assertEquals(State.NOT_ACTIVE_PROPOSAL, proposal.getState());
	}

	@Test
	public void testGetProposalByProduct() {
		Seller seller1 = sellerService.getSellerById(1l);
		assertNotNull(seller1);

		Seller seller2 = sellerService.getSellerById(1l);
		assertNotNull(seller2);

		Product product = new Product();
		product.setName("Product4");
		product.setDescription("Description");

		productService.createProduct(product);

		Long proposalId1 = proposalService.createProposal(seller1.getId(),
				product.getId(), 100d);

		Long proposalId2 = proposalService.createProposal(seller2.getId(),
				product.getId(), 200d);

		assertNotNull(proposalId1);
		assertNotNull(proposalId2);

		List<Proposal> proposals = proposalService
				.getProposalsByProduct(product);

		assertNotNull(proposals);
		assertEquals(2, proposals.size());

		for (Proposal proposal : proposals) {
			assertEquals(proposal.getProduct(), product);
			Long id = proposal.getId();

			if (proposalId1.equals(id)) {
				assertEquals(seller1, proposal.getSeller());
				assertEquals(new Double(100d), proposal.getPrice());
			} else if (proposalId2.equals(id)) {
				assertEquals(seller2, proposal.getSeller());
				assertEquals(new Double(200d), proposal.getPrice());
			}
		}
	}

	@Test
	public void testGetProposalByProductId() {
		Seller seller1 = sellerService.getSellerById(1l);
		assertNotNull(seller1);

		Seller seller2 = sellerService.getSellerById(1l);
		assertNotNull(seller2);

		Product product = new Product();
		product.setName("Product5");
		product.setDescription("Description");

		productService.createProduct(product);

		Long proposalId1 = proposalService.createProposal(seller1.getId(),
				product.getId(), 100d);

		Long proposalId2 = proposalService.createProposal(seller2.getId(),
				product.getId(), 200d);

		assertNotNull(proposalId1);
		assertNotNull(proposalId2);

		List<Proposal> proposals = proposalService
				.getProposalsByProductId(product.getId());

		assertNotNull(proposals);
		assertEquals(2, proposals.size());

		for (Proposal proposal : proposals) {
			assertEquals(proposal.getProduct(), product);
			Long id = proposal.getId();

			if (proposalId1.equals(id)) {
				assertEquals(seller1, proposal.getSeller());
				assertEquals(new Double(100d), proposal.getPrice());
			} else if (proposalId2.equals(id)) {
				assertEquals(seller2, proposal.getSeller());
				assertEquals(new Double(200d), proposal.getPrice());
			}
		}
	}

	@Test
	public void testGetProposalBySeller() {
		Seller seller = sellerService.getSellerById(1l);
		assertNotNull(seller);

		Product product1 = new Product();
		product1.setName("Product6");
		product1.setDescription("Description");

		Product product2 = new Product();
		product1.setName("Product7");
		product1.setDescription("Description");

		productService.createProduct(product1);
		productService.createProduct(product2);

		Long proposalId1 = proposalService.createProposal(seller.getId(),
				product1.getId(), 100d);

		Long proposalId2 = proposalService.createProposal(seller.getId(),
				product2.getId(), 200d);

		assertNotNull(proposalId1);
		assertNotNull(proposalId2);

		List<Proposal> proposals = proposalService.getProposalsBySeller(seller);

		assertNotNull(proposals);
		assertTrue(proposals.size() >= 2);

		for (Proposal proposal : proposals) {
			assertEquals(proposal.getSeller(), seller);

			Long id = proposal.getId();
			if (proposalId1.equals(id)) {
				assertEquals(product1, proposal.getProduct());
				assertEquals(new Double(100d), proposal.getPrice());
			} else if (proposalId2.equals(id)) {
				assertEquals(product2, proposal.getProduct());
				assertEquals(new Double(200d), proposal.getPrice());
			}
		}
	}

	
	  @Test public void testGetProposalBySellerId(){
		  Seller seller = sellerService.getSellerById(1l);
			assertNotNull(seller);

			Product product1 = new Product();
			product1.setName("Product6");
			product1.setDescription("Description");

			Product product2 = new Product();
			product1.setName("Product7");
			product1.setDescription("Description");

			productService.createProduct(product1);
			productService.createProduct(product2);

			Long proposalId1 = proposalService.createProposal(seller.getId(),
					product1.getId(), 100d);

			Long proposalId2 = proposalService.createProposal(seller.getId(),
					product2.getId(), 200d);

			assertNotNull(proposalId1);
			assertNotNull(proposalId2);

			List<Proposal> proposals = proposalService.getProposalsBySellerId(seller.getId());

			assertNotNull(proposals);
			assertTrue(proposals.size() >= 2);

			for (Proposal proposal : proposals) {
				assertEquals(proposal.getSeller(), seller);

				Long id = proposal.getId();
				if (proposalId1.equals(id)) {
					assertEquals(product1, proposal.getProduct());
					assertEquals(new Double(100d), proposal.getPrice());
				} else if (proposalId2.equals(id)) {
					assertEquals(product2, proposal.getProduct());
					assertEquals(new Double(200d), proposal.getPrice());
				}
			}
	  }
	 
}
