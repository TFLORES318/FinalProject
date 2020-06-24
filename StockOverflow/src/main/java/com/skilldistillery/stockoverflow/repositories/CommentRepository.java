package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByPost_Id(int postId);
	
	Comment findByIdAndUserUsername(int commentId, String username);
}
