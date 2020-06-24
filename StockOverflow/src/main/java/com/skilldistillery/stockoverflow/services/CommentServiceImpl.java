package com.skilldistillery.stockoverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.Comment;
import com.skilldistillery.stockoverflow.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public Comment findCommentById(int commentId) {
		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		Comment comment = null;
		if(commentOpt.isPresent()) {
			comment = commentOpt.get();
		}
		return comment;
	}

	@Override
	public List<Comment> findAllPostComments(int postId) {
		List<Comment> comments = commentRepo.findByPost_Id(postId);
		return comments;
	}

	@Override
	public Comment createComment(int postId, int userId, Comment comment) {
		//Post post = postRepo.findById(postId);
		//comment.setPost(post);
		//User user = userRepo.findById(userId);
		//comment.setUser(user);
		return commentRepo.saveAndFlush(comment);
	}

	@Override
	public Comment updateComment(int commentId, int postId, int userId, Comment comment) {
		//Post post = postRepo.findById(postId);
		//comment.setPost(post);
		//User user = userRepo.findById(userId);
		//comment.setUser(user);
		Comment managedComment = null;
		Optional <Comment> commentOpt = commentRepo.findById(commentId);
		if(commentOpt.isPresent()) {
			managedComment = commentOpt.get();
//			managedComment.setCommentRatings(comment.getCommentRatings());
			managedComment.setContent(comment.getContent());
			managedComment.setEnabled(comment.isEnabled());
		}
		return managedComment;
	}

	@Override
	public void disableComment(int commentId) {
		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		if(commentOpt.isPresent()) {
			Comment toDisable = commentOpt.get();
			toDisable.setEnabled(false);
			commentRepo.saveAndFlush(toDisable);
		}
	}

}
