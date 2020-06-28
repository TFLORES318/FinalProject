package com.skilldistillery.stockoverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.CommentRating;
import com.skilldistillery.stockoverflow.entities.CommentRatingId;
import com.skilldistillery.stockoverflow.repositories.CommentRatingRepository;

@Service
public class CommentRatingServiceImpl implements CommentRatingService {

	@Autowired
	private CommentRatingRepository repo;

	@Override
	public List<CommentRating> findAll() {
		return repo.findAll();
	}

	@Override
	public List<CommentRating> findByUser_Id(int userId) {
		return null;
	}

	@Override
	public CommentRating findById(int userId, int commentId) {
		CommentRatingId id = new CommentRatingId(commentId, userId);
		Optional<CommentRating> commentRatingOpt = repo.findById(id);
		CommentRating commentRating = null;
		if(commentRatingOpt.isPresent()) {
			commentRating = commentRatingOpt.get();
		}
		return commentRating;
	}
	
	
}
