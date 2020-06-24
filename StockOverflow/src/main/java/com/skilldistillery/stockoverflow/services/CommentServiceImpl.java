package com.skilldistillery.stockoverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.Comment;
import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.repositories.CommentRepository;
import com.skilldistillery.stockoverflow.repositories.PostRepository;
import com.skilldistillery.stockoverflow.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired UserRepository userRepo;
	
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
		Optional<Post> postOpt = postRepo.findById(postId);
		Post post = postOpt.get();
		comment.setPost(post);
		
		Optional<User> userOpt = userRepo.findById(userId);
		User user = userOpt.get();
		comment.setUser(user);
		return commentRepo.saveAndFlush(comment);
	}

	@Override
	public Comment updateComment(int commentId, int postId, int userId, Comment comment) {
		Optional<Post> postOpt = postRepo.findById(postId);
		Post post = postOpt.get();
		
		Optional<User> userOpt = userRepo.findById(userId);
		User user = userOpt.get();
		
		Comment managedComment = null;
		Optional <Comment> commentOpt = commentRepo.findById(commentId);
		if(commentOpt.isPresent()) {
			managedComment = commentOpt.get();
//			managedComment.setCommentRatings(comment.getCommentRatings());
			managedComment.setContent(comment.getContent());
			managedComment.setEnabled(comment.isEnabled());
			managedComment.setPost(post);
			managedComment.setUser(user);
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
