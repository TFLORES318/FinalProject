package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.stockoverflow.entities.Stock;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.services.AuthService;
import com.skilldistillery.stockoverflow.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	@GetMapping("webinar/{webinarId}/users")
	public List<User> findUsersAttendingWebinar(@PathVariable int webinarId){
		return userServ.findUsersAttendingWebinar(webinarId);
	}
	
	@GetMapping("users/{userId}")
	public User findUserById(@PathVariable int userId, HttpServletResponse res) {
		User user = userServ.findById(userId);
		if(user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@GetMapping("users/stocks")
	public List<Stock> findUserStock(Principal principal){
		User user = userServ.findByUsername(principal.getName());
		return user.getStocks();
	}
	
	@PutMapping("users/update")
	public User updateUser(@RequestBody User user, Principal principal, HttpServletRequest req, HttpServletResponse res) {
		
		try {
			user = userServ.updateUser(principal.getName(), user);
			if(user == null) {
				System.out.println("In null");
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			user = null;
			e.printStackTrace();
		}
		return user;
	}
	
	//user facing "delete" but account is disabled in DB
	@PutMapping("users/delete")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			boolean disabled = userServ.disableUser(principal.getName());
				if(disabled) {
					res.setStatus(204);
				}
				else {
					res.setStatus(404);
				}
		} catch (Exception e) {
			res.setStatus(409);
			e.printStackTrace();
		}
	}
	
	@GetMapping("users/search/{keyword}")
	public List<User> searchByUsername(@PathVariable String keyword){
		return userServ.findUsersUsernameSearch(keyword);
	}

}
