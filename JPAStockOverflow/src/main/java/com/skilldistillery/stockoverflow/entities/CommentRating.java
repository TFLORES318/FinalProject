package com.skilldistillery.stockoverflow.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="comment_rating")
public class CommentRating {
	
	@EmbeddedId
	private CommentRatingId id;
	
	//fields
	
	private int rating;
	
	private String note;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	//mapping to Comment Rating
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="comment_id")
	@MapsId(value="commentId")
	private Comment comment;

	

	public CommentRating() {
		super();
	}






	public CommentRating(CommentRatingId id, int rating, String note, LocalDateTime createdAt, User user,
			Comment comment) {
		super();
		this.id = id;
		this.rating = rating;
		this.note = note;
		this.createdAt = createdAt;
		this.user = user;
		this.comment = comment;
	}






	public CommentRatingId getId() {
		return id;
	}



	public void setId(CommentRatingId id) {
		this.id = id;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Comment getComment() {
		return comment;
	}



	public void setComment(Comment comment) {
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "CommentRating [id=" + id + ", rating=" + rating + ", note=" + note + ", createdAt=" + createdAt + "]";
	}
	
	
	
}

