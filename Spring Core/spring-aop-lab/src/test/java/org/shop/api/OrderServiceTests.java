package org.shop.api;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.shop.data.Seller;
import org.shop.data.State;
import org.shop.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml", "classpath:data-initializer.xml"})
public class OrderServiceTests {

	@Autowired
    OrderService orderService;
    
    @Test
    public void testCreateOrderUserItemArray() {
    	User user = new User();
    	user.setId(1l);
    	user.setUsername("1");
    	
    	Item item1 = new Item();
    	item1.setId(1l);
    	item1.setPrice(100d);	
    	Order order1 = new Order();
    	order1.setId(1l);
    	order1.setCreatedDate(new Date());
    	order1.setUser(user);
    	item1.setOrder(order1); 	
    	Product product1 = new Product();
    	product1.setId(1l);
    	product1.setName("1");
    	product1.setDescription("1");	
    	item1.setProduct(product1);
    	
    	Item item2 = new Item();
    	item1.setId(2l);
    	item1.setPrice(200d);	
    	Order order2 = new Order();
    	order2.setId(2l);
    	order2.setCreatedDate(new Date());
    	order2.setUser(user);
    	item2.setOrder(order2); 	
    	Product product2 = new Product();
    	product2.setId(1l);
    	product2.setName("2");
    	product2.setDescription("2");	
    	item2.setProduct(product2);
    	
    	assertNotNull( "Service is Null", orderService);
        orderService.createOrder(user, item1, item2);
    }

    @Test
    public void testCreateOrderUserProposalArray() {
    	User user = new User();
    	
    	user.setId(2l);
    	user.setUsername("2");
    	
    	Proposal proposal1 = new Proposal();
    	proposal1.setId(1l);
    	proposal1.setPrice(10d);
    	
    	Product product1 = new Product();
    	product1.setId(3l);
    	product1.setDescription("3");
    	product1.setName("3");	
    	proposal1.setProduct(product1);
    	
    	Seller seller1 = new Seller();
    	seller1.setId(2l);
    	seller1.setName("2");
    	
    	proposal1.setSeller(seller1);
    	proposal1.setState(State.ACTIVE_PROPOSAL);
    	
    	Proposal proposal2 = new Proposal();
    	proposal1.setId(2l);
    	proposal1.setPrice(20d);
    	
    	Product product2 = new Product();
    	product2.setId(4l);
    	product2.setDescription("4");
    	product2.setName("4");	
    	proposal2.setProduct(product2);
    	
    	Seller seller2 = new Seller();
    	seller2.setId(3l);
    	seller2.setName("3");
    	
    	proposal2.setSeller(seller2);
    	proposal2.setState(State.ACTIVE_PROPOSAL);
    	
        orderService.createOrder(user, proposal1, proposal2);
    }

    @Test
    public void testGetOrderById() {
        List<Order> order = orderService.getOrdersByUserId(1l);
        assertTrue(order != null);
        assertTrue(order.size() != 0);
        
        Order order1 = order.get(0);
        Order order2 = orderService.getOrderById(order1.getId());
        
        assertEquals(order1, order2);
    }

    @Test
    public void testGetOrdersByUser() {
    	User user = new User();
    	user.setId(1l);
    	 List<Order> order = orderService.getOrdersByUser(user);
         assertTrue(order != null);
         assertTrue(order.size() != 0);
    }

    @Test
    public void testGetOrdersByUserId() {
    	User user = new User();
    	
    	user.setId(22l);
    	user.setUsername("2");
    	
    	Proposal proposal1 = new Proposal();
    	proposal1.setId(1l);
    	proposal1.setPrice(10d);
    	
    	Product product1 = new Product();
    	product1.setId(3l);
    	product1.setDescription("3");
    	product1.setName("3");	
    	proposal1.setProduct(product1);
    	
    	Seller seller1 = new Seller();
    	seller1.setId(2l);
    	seller1.setName("2");
    	
    	proposal1.setSeller(seller1);
    	proposal1.setState(State.ACTIVE_PROPOSAL);
    	  	
        orderService.createOrder(user, proposal1);
    	
    	
    	 List<Order> order = orderService.getOrdersByUserId(22l);
         assertTrue(order != null);
         assertTrue(order.size() != 0);
    }
}
