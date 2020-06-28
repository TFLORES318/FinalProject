package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.entities.Role;
import com.skilldistillery.stockoverflow.entities.Stock;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.entities.Webinar;
import com.skilldistillery.stockoverflow.services.AuthService;
import com.skilldistillery.stockoverflow.services.PostService;
import com.skilldistillery.stockoverflow.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PostService postServ;
	
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
	
	@GetMapping("users/userpro")
	public User findUserProById(HttpServletResponse res, Principal principal) {
		User user = userServ.findByUsername(principal.getName());
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
	
	@PutMapping("users/admin/update")
	public User adminUserUpdate(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) {
//		User updateUser = userServ.findById(user.getId());
		try {
			if (user == null) {
				res.setStatus(404);
			}
			user = userServ.updateUser(user.getUsername(), user);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
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
	
	@GetMapping("users")
	public List<User> findAll(Principal principal, HttpServletResponse res){
		List<User> users = null;
		User user = userServ.findByUsername(principal.getName());
		if(user.getRole() == Role.ADMIN) {
			users = userServ.findAllUsers();
		}
		else {
			res.setStatus(404);
		}
		return users;
	}
	
	@GetMapping("users/webinars")
	public List<Webinar> findAllWebinars(Principal principal) {
		return userServ.findWebinarsUserIsGoingTo(principal.getName());
	}
	
	@GetMapping("users/{userId}/posts")
	public List<Post> displayPostsByUser(@PathVariable int userId){
		return userServ.findPostsByUser(userId);
	}
	
	@GetMapping("users/{userId}/webinars")
	public List<Webinar> displayWebinarsByUser(@PathVariable int userId) {
		User user = userServ.findById(userId);
		return user.getWebinarsAttending();
	}
}
