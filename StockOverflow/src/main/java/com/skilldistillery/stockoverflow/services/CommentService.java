package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.Comment;

public interface CommentService {
	Comment findCommentById(int commentId);
	List<Comment> findAllPostComments(int postId);
	Comment createComment(int postId, String username, Comment comment);
	Comment updateComment(int commentId, int postId, String username, Comment comment);
	Boolean disableComment(String username, int commentId);
}
