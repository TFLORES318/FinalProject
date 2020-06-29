package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.Post;

public interface PostService {
	
	public List <Post> index();
	
	public List <Post> userPosts(String username);
	
	public Post show(String username, int postId);
	
	public Post create(String username, Post post);
	
	public Post update(String username, int postId, Post post);
	
	public Boolean disable(String username, int postId);
	
	public List<Post> showByTitle(String title);

}
