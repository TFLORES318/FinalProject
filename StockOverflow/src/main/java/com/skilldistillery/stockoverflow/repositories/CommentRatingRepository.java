package com.skilldistillery.stockoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.CommentRating;

public interface CommentRatingRepository extends JpaRepository<CommentRating, Integer> {

}
