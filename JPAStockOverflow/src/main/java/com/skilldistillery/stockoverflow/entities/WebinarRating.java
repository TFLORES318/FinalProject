package com.skilldistillery.stockoverflow.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "webinar_rating")
public class WebinarRating {
	
	@EmbeddedId
	private WebinarRatingId id;
	
	private int rating;
	
	@Column(name="rating_note")
	private String ratingNote;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	// Constructors
	public WebinarRating() {}
	
	public WebinarRating(WebinarRatingId id, int rating, String ratingNote, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.rating = rating;
		this.ratingNote = ratingNote;
		this.createdAt = createdAt;
	}

	public WebinarRatingId getId() {
		return id;
	}

	public void setId(WebinarRatingId id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getRatingNote() {
		return ratingNote;
	}

	public void setRatingNote(String ratingNote) {
		this.ratingNote = ratingNote;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		WebinarRating other = (WebinarRating) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WebinarRating [id=" + id + ", rating=" + rating + ", ratingNote=" + ratingNote + ", createdAt="
				+ createdAt + "]";
	}
	
	
	
	
	
	
}
