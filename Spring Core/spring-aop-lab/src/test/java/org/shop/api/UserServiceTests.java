package org.shop.api;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml", "classpath:listeners.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTests {

    @Autowired
    UserService userService;
    
    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("Ivan Ivanov");
        Assert.assertTrue(userService.registerUser(user) > 0);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUsername("Ivan Ivanov"); 
        Long userId = userService.registerUser(user);  
        User savedUser = userService.getUserById(userId);   
        Assert.assertNotNull(savedUser);
        Assert.assertEquals("Ivan Ivanov", savedUser.getUsername());
    }

    @Test
    public void testUpdateUserProfile() {
        User user = new User();
        user.setUsername("Ivan Ivanov");       
        Long userId = userService.registerUser(user);       
        user = userService.getUserById(userId);
        user.setUsername("Ivan Petrov");       
        userService.updateUserProfile(user);       
        user = userService.getUserById(userId);      
        Assert.assertEquals("Ivan Petrov", user.getUsername());
    }

    @Test
    public void testGetUsers() {
        Assert.assertTrue(userService.getUsers().size() == 2);
    }
}
