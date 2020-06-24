package com.skilldistillery.stockoverflow.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Webinar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	@Column(name="date_time")
	private LocalDateTime dateTime;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
	private String picture;
	
	@Column(name="meeting_link")
	private String meetingLink;
	
	@Column(name="max_attendees")
	private int maxAttendees;
	
	private Boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="creator_user_id")
	private User userCreator;
	
	@ManyToMany
	@JoinTable(name="user_webinar",
	joinColumns=@JoinColumn(name="webinar_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private List <User> usersAttending;
	
	@OneToMany(mappedBy="webinar")
	private List <WebinarRating> webinarRatings;
	

	public Webinar() {}

	public Webinar(int id, String title, String description, LocalDateTime dateTime, LocalDateTime createdAt,
			LocalDateTime updateDate, String picture, String meetingLink, int maxAttendees, Boolean enabled) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateTime = dateTime;
		this.createdAt = createdAt;
		this.updateDate = updateDate;
		this.picture = picture;
		this.meetingLink = meetingLink;
		this.maxAttendees = maxAttendees;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	public int getMaxAttendees() {
		return maxAttendees;
	}

	public void setMaxAttendees(int maxAttendees) {
		this.maxAttendees = maxAttendees;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}
	

	public List<User> getUsersAttending() {
		return usersAttending;
	}

	public void setUsersAttending(List<User> usersAttending) {
		this.usersAttending = usersAttending;
	}

	public List<WebinarRating> getWebinarRatings() {
		return webinarRatings;
	}

	public void setWebinarRatings(List<WebinarRating> webinarRatings) {
		this.webinarRatings = webinarRatings;
	}

	@Override
	public String toString() {
		return "Webinar [id=" + id + ", title=" + title + ", description=" + description + ", dateTime=" + dateTime
				+ ", createdAt=" + createdAt + ", updateDate=" + updateDate + ", picture=" + picture + ", meetingLink="
				+ meetingLink + ", maxAttendees=" + maxAttendees + ", enabled=" + enabled + "]";
	}
	
	
	

}
