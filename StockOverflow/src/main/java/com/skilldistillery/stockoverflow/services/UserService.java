package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.User;

public interface UserService {
	User findById(int userId);
	User findByUsername(String username);
	List<User> findUsersAttendingWebinar(int webinarId);
	User updateUser(String username, User user);
	boolean disableUser(String username);
	List<User> findUsersUsernameSearch(String keyword);

}
