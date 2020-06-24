package com.skilldistillery.stockoverflow.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	private boolean enabled;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	
//	@JsonIgnoreProperties({"comments"})
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@JsonIgnoreProperties({"comments"})
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
	@OneToMany(mappedBy="comment")
	private List<CommentRating> commentRatings;

	public Comment() {}

	public Comment(int id, String content, LocalDateTime createDate) {
		super();
		this.id = id;
		this.content = content;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<CommentRating> getCommentRatings() {
		return commentRatings;
	}

	public void setCommentRatings(List<CommentRating> commentRatings) {
		this.commentRatings = commentRatings;
	}

	public void addCommentRating(CommentRating commentRating) {
		if (commentRatings == null) {
			commentRatings = new ArrayList<>();
		}
		if(!commentRatings.contains(commentRating)) {
			commentRatings.add(commentRating);
			if(commentRating.getComment() != null) {
				commentRating.getComment().getCommentRatings().remove(commentRating);
			}
		}
	}
	
	public void removeCommentRating(CommentRating commentRating) {
		commentRating.setComment(null);
		if (commentRatings != null) {
			commentRatings.remove(commentRating);
		}
	}

	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", content=" + content + ", createDate=" + createDate + "]";
	}
	
	
	
	
	

}
