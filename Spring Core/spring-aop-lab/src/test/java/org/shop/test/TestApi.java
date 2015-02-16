package org.shop.test;

import java.util.List;

import org.shop.api.UserService;
import org.shop.data.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApi {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/context.xml");

		UserService userServiceByName = (UserService) applicationContext
				.getBean("userService");
		System.out.println("Get By Name: " + userServiceByName.toString());

		testBean(userServiceByName);

		UserService userServiceByType = applicationContext
				.getBean(UserService.class);
		System.out.println("Get By Type: " + userServiceByType.toString());

		testBean(userServiceByType);

		UserService userServiceByTypeAndName = applicationContext.getBean(
				"userService", UserService.class);
		System.out.println("Get By Type And Name: "
				+ userServiceByTypeAndName.toString());

		testBean(userServiceByTypeAndName);

		UserService userServiceByAlias = (UserService) applicationContext
				.getBean("clientService");
		System.out.println("Get By Alias: " + userServiceByAlias.toString());

		testBean(userServiceByAlias);
	}

	private static void testBean(UserService bean) {
		User user1 = new User();
		user1.setUsername("user1");
		bean.registerUser(user1);

		List<User> users = bean.getUsers();

		System.out
				.println("Collection contains user: " + users.contains(user1));

		User user = bean.getUserById(user1.getId());

		System.out.println("Expected Id: " + user1.getId() + " ActualId: "
				+ user.getId());
	}

}
