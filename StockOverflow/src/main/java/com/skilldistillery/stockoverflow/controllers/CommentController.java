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
import com.skilldistillery.stockoverflow.entities.Post;
import com.skilldistillery.stockoverflow.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class CommentController {

	@Autowired
	private CommentService commSvc;

	@GetMapping("posts/{pId}/comments")
	public List<Comment> index(@PathVariable int pId) {
		return commSvc.findAllPostComments(pId);
	}

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
	
	
}