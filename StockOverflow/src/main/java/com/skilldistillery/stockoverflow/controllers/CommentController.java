package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.stockoverflow.entities.Comment;
import com.skilldistillery.stockoverflow.entities.CommentRating;
import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.services.CommentRatingService;
import com.skilldistillery.stockoverflow.services.CommentService;
import com.skilldistillery.stockoverflow.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class CommentController {

	@Autowired
	private CommentService commSvc;
	@Autowired
	private UserService userSvc;
	@Autowired
	private CommentRatingService commentRatingServ;

	@GetMapping("posts/{pId}/comments")
	public List<Comment> index(@PathVariable int pId) {
		return commSvc.findAllPostComments(pId);
	}
//	
//	@GetMapping("posts/{pId}/post/comments")
//	public List<Comment> index(@PathVariable int pId, Principal principal) {
//		try {
//			User user = userSvc.findByUsername(principal.getName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return commSvc.findAllPostComments(pId);
//	}

	@PostMapping("posts/{postId}/comments")
	public Comment createComment(HttpServletRequest req, HttpServletResponse res, @PathVariable int postId,
			@RequestBody Comment comment, Principal principal) {
		try {
			comment = commSvc.createComment(postId, principal.getName(), comment);
			if (comment == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(comment.getId());
				res.addHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;

	}

	@PutMapping("posts/{postId}/comments/{commentId}")
	public Comment updateComment(HttpServletRequest req, HttpServletResponse res, @PathVariable int postId,
			@PathVariable int commentId, @RequestBody Comment comment, Principal principal) {
		try {
			comment = commSvc.updateComment(commentId, postId, principal.getName(), comment);
			if (comment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			comment = null;
		}
		return comment;
	}
	
	@PutMapping("posts/{postId}/delete/{commentId}") 
	public void disableComment(@PathVariable int postId, @PathVariable int commentId, Principal principal,
			HttpServletResponse res) {
			Post post = new Post();
			post.setId(postId);
		try {
			if (commSvc.disableComment(principal.getName(), commentId)) {
				res.setStatus(204);
			
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@GetMapping("comments/ratings/{userId}")
	public List<CommentRating> findRatingsForUser(@PathVariable int userId, HttpServletResponse res){
		List<CommentRating> ratings = commentRatingServ.findByUser_Id(userId);
		if (ratings == null) {
			res.setStatus(404);
		}
		return ratings;
	}
	
	@PostMapping("comments/ratings")
	public CommentRating createNewCommentRating(@RequestBody CommentRating commentRating, HttpServletRequest req, HttpServletResponse res) {
		try {
			commentRating = commentRatingServ.createCommentRating(commentRating);
			if (commentRating == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(commentRating.getId().getCommentId()).append(commentRating.getId().getUserId());
				res.addHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			commentRating = null;
		}
		return commentRating;
	}
	
}