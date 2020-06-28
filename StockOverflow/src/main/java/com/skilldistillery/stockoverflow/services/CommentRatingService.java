package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.CommentRating;

public interface CommentRatingService {
	
	CommentRating findById(int userId, int commentId);
	List<CommentRating> findAll();
	List<CommentRating> findByUser_Id(int userId);

}
