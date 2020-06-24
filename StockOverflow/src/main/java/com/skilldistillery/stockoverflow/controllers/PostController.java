package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.services.PostService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class PostController {
	
	@Autowired
	PostService postSvc;
	
	// Get all Posts in general:
	@GetMapping("posts")
	public List<Post> index(){
		return postSvc.index();
	}
	
	// Get all Posts by User ID:
	@GetMapping("users/posts")
	public List<Post> displayPostsByUser(Principal principal){
		return postSvc.userPosts(principal.getName());
	}

}
