package org.shop.api.impl;

import java.util.List;

import org.shop.api.UserService;
import org.shop.data.User;
import org.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	// @Override
	public Long registerUser(User user) {
		return userRepository.createUser(user);
	}

	// @Override
	public User getUserById(Long userId) {
		return userRepository.getUserById(userId);
	}

	// @Override
	public void updateUserProfile(User user) {
		userRepository.updateUser(user);
	}

	// @Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Autowired
	public void populate(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
