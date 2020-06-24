package com.skilldistillery.stockoverflow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
