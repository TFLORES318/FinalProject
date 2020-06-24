package com.skilldistillery.stockoverflow.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentRatingId implements Serializable {
	
	private static final long serialVersionUID =1L;
	
	@Column(name="comment_id")
	private int commentId;
	
	@Column(name="user_id")
	private int userId;

	
	
	public CommentRatingId() {
		super();
	}

	public CommentRatingId(int commentId, int userId) {
		super();
		this.commentId = commentId;
		this.userId = userId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CommentRatingId [commentId=" + commentId + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentRatingId other = (CommentRatingId) obj;
		if (commentId != other.commentId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
