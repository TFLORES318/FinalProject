package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// Get all Posts by Logged-In User:
	@GetMapping("users/posts")
	public List<Post> displayPostsByUser(Principal principal){
		return postSvc.userPosts(principal.getName());
	}
	
	// Get one Post by ID
	@GetMapping("posts/{pId}")
	public Post showPost(
			@PathVariable int pId,
			HttpServletResponse res,
			Principal principle
	){
		Post post = postSvc.show(principle.getName(), pId);
		if (post == null) {
			res.setStatus(404); // if wrong Id, 404 no post with that id
		}
		return post;
	}
	
	// Create new Post
	@PostMapping("posts")
	public Post create(
			@RequestBody Post post,
			HttpServletResponse res,
			HttpServletRequest req,
			Principal principle
	){
			try {
				post = postSvc.create(principle.getName(), post);
				if (post == null) {
					res.setStatus(404);
				}
				else {
					res.setStatus(201); // 201 successfully created
					StringBuffer url = req.getRequestURL(); // gets the resource created with the same url path and Post id
					res.setHeader("Location", url.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				post = null;
			}
			return post;
	}
	
	//Update existing Post by ID
	@PutMapping("posts/{pId}")
	public Post update(
			HttpServletRequest req,
			HttpServletResponse res,
			Principal principle,
			@PathVariable int pId,
			@RequestBody Post post
	){
		try {
			post = postSvc.update(principle.getName(), pId, post);
			if (post == null) {
				res.setStatus(404); // 404 not found, not a valid post id
			}
			return post;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400); // bad request due to poor data, 400 error
			post = null;
		}
		return post;
	}
	
	//Disable existing Post by ID
	@PutMapping("posts/disable/{pId}")
	public void disable(
			@PathVariable Integer pId,
			HttpServletResponse response,
			Principal principle
	) {
		try {
			if(postSvc.disable(principle.getName(), pId)) {
				response.setStatus(204); // ID was valid / 204 no content
			}
			else {
				response.setStatus(404); // invalid ID, run not found
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(409); // conflict, trying to delete a run with tied child relationships
		}
	}
	

}
