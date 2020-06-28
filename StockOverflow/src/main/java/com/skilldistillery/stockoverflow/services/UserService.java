package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.entities.Stock;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.entities.Webinar;

public interface UserService {
	User findById(int userId);
	User findByUsername(String username);
	List<User> findUsersAttendingWebinar(int webinarId);
	User updateUser(String username, User user);
	boolean disableUser(String username);
	List<User> findUsersUsernameSearch(String keyword);
	List<User> findAllUsers();
	List<Stock> addUserStock(String username, Stock stock);
	public List<Webinar> findWebinarsUserIsGoingTo(String username);
	public List<Post> findPostsByUser(int userId);
}
