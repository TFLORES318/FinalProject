package com.skilldistillery.stockoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
