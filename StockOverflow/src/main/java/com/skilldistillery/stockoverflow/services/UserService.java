package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.User;

public interface UserService {
	User findById(int userId);
	User findByUsername(String username);
	List<User> findUsersAttendingWebinar(int webinarId);
	User updateUser(int userId, User user);
	void disableUser(int userId);
	List<User> findUsersUsernameSearch(String keyword);

}
