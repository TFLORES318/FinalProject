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
	
	//find for a different user (not the logged in user)
	@Override
	public List<CommentRating> findByUser_Id(int userId) {
		List<CommentRating> ratings = repo.queryForRatingsForUser(userId);
		return ratings;
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

	@Override
	public CommentRating createCommentRating(CommentRating commentRating) {
		repo.saveAndFlush(commentRating);
		return commentRating;
	}

	@Override
	public List<CommentRating> findForLoggedInuser(int userId) {
		return repo.findByUser_id(userId);
	}
	
	
	
}
