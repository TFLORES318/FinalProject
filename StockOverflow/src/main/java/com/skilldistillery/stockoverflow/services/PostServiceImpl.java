package com.skilldistillery.stockoverflow.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.repositories.PostRepository;
import com.skilldistillery.stockoverflow.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Post> index() {
		List<Post> allPosts = postRepo.findAll();
		List<Post> activePosts = new ArrayList<>();
		for (Post post : allPosts) {
			if(post.isEnabled()) {
				activePosts.add(post);
			}
			else activePosts.remove(post);
		}
		return activePosts;
	}

	@Override
	public List<Post> userPosts(String username) {
		return postRepo.findByUserUsername(username);
	}

	@Override
	public Post show(String username, int postId) {
		Post post = postRepo.findByIdAndUserUsername(postId, username);
		return post;
	}

	@Override
	public Post create(String username, Post post) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			post.setEnabled(true);
			post.setUser(user);
			postRepo.saveAndFlush(post);
			return post;
		}
		return null;
	}

	@Override
	public Post update(String username, int postId, Post post) {
		Post updatedPost = postRepo.findByIdAndUserUsername(postId, username);
		if (updatedPost != null) {
			updatedPost.setTitle(post.getTitle());
			updatedPost.setDescription(post.getDescription());
			postRepo.saveAndFlush(updatedPost);
			return updatedPost;
			
		}
		return null;
	}

	@Override
	public Boolean disable(String username, int postId) {
		boolean deletedDisable = false;
		Post post = postRepo.findByIdAndUserUsername(postId, username);
		if (post != null) {
			post.setEnabled(false);
			postRepo.saveAndFlush(post);
			deletedDisable = true;
		}
		return deletedDisable;
	}

}
