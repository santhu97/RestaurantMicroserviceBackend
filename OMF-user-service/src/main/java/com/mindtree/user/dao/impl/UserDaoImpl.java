package com.mindtree.user.dao.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.user.dao.UserDao;
import com.mindtree.user.entity.User;
import com.mindtree.user.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserByEmail(String email) {
		logger.info("Fetching an optional user from EMAIL ....in Dao!");
		return userRepository.findByEmail(email);
	}

	@Override
	public void persistUser(User user) {
		logger.info("persisting user into DB....In Dao!");
		userRepository.saveAndFlush(user);
		
	}

}
