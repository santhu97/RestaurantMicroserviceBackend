package com.mindtree.user.dao;

import java.util.Optional;

import com.mindtree.user.entity.User;

public interface UserDao {

	Optional<User> getUserByEmail(String email);

	void persistUser(User user);

}
