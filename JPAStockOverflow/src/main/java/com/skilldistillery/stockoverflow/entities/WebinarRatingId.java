package com.skilldistillery.stockoverflow.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WebinarRatingId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "webinar_id")
	private int webinarId;
	
	@Column(name = "user_id")
	private int userId;
	
	// Constructors
	public WebinarRatingId() {}
	
	public WebinarRatingId(int webinarId, int userId) {
		super();
		this.webinarId = webinarId;
		this.userId = userId;
	}
	
	// Methods
	public int getWebinarId() {
		return webinarId;
	}

	public void setWebinarId(int webinarId) {
		this.webinarId = webinarId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		result = prime * result + webinarId;
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
		WebinarRatingId other = (WebinarRatingId) obj;
		if (userId != other.userId)
			return false;
		if (webinarId != other.webinarId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WebinarRatingId [webinarId=" + webinarId + ", userId=" + userId + "]";
	}
	
	
	
}
