package org.shop.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:context.xml",
		"classpath:data-initializer.xml" })
public class ItemServiceTests {

	@Autowired
	ItemService itemService;

	@Test
	public void testCreateItem() {
		Item item = new Item();
		item.setId(1l);
		item.setPrice(10d);

		Product product = new Product();
		product = new Product();
		product.setId(1l);
		product.setName("1");
		product.setDescription("1");
		item.setProduct(product);

		Order order = new Order();
		order.setId(1l);
		order.setCreatedDate(new Date());

		User user = new User();
		user.setId(1l);
		user.setUsername("1");
		order.setUser(user);
		item.setOrder(order);

		itemService.createItem(item);
	}

	@Test
	public void testGetItemsByOrderId() {
		Item item = new Item();
		item.setId(1l);
		item.setPrice(10d);

		Product product = new Product();
		product = new Product();
		product.setId(1l);
		product.setName("1");
		product.setDescription("1");
		item.setProduct(product);

		Order order = new Order();
		order.setId(2l);
		order.setCreatedDate(new Date());

		User user = new User();
		user.setId(1l);
		user.setUsername("1");
		order.setUser(user);
		item.setOrder(order);

		itemService.createItem(item);

		List<Item> items = itemService.getItemsByOrderId(2l);

		assertNotNull(items);
		assertTrue(items.size() != 0);

		for (Item i : items) {
			assertEquals(new Long(2L), i.getOrder().getId());
		}
	}

}
