package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Post findByIdAndUserUsername(int postId, String username);
	
	List <Post> findByUserUsername(String username);

}
