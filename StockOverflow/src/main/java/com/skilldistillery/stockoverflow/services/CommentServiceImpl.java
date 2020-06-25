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
	public Comment createComment(int postId, String username, Comment comment) {
		Optional<Post> postOpt = postRepo.findById(postId);
		Post post = postOpt.get();
		comment.setPost(post);
		comment.setEnabled(true);
		User user = userRepo.findByUsername(username);
		if (user != null) {
			comment.setUser(user);
			commentRepo.saveAndFlush(comment);
			return comment;
		}
		return null;
	}

	@Override
	public Comment updateComment(int commentId, int postId, String username, Comment comment) {
		Optional<Post> postOpt = postRepo.findById(postId);
		Post post = postOpt.get();
		
		User user = userRepo.findByUsername(username);
		
		
		Comment managedComment = commentRepo.findByIdAndUserUsername(commentId, username);
		if(managedComment != null) {
			managedComment.setContent(comment.getContent());
			managedComment.setEnabled(true);
			managedComment.setPost(post);
			managedComment.setUser(user);
			commentRepo.saveAndFlush(managedComment);
			return managedComment;
		}
		return null;
	}

//	@Override
//	public void disableComment(int commentId) {
//		Optional<Comment> commentOpt = commentRepo.findById(commentId);
//		if(commentOpt.isPresent()) {
//			Comment toDisable = commentOpt.get();
//			toDisable.setEnabled(false);
//			commentRepo.saveAndFlush(toDisable);
//		}
//	}
	public Boolean disableComment(String username, int commentId) {
		boolean disableDeletedComment = false;
		Comment comment = commentRepo.findByIdAndUserUsername(commentId, username);
		if (comment != null) {
			comment.setEnabled(false);
			commentRepo.saveAndFlush(comment);
			disableDeletedComment = true;
		}
		return disableDeletedComment;
	}

}
