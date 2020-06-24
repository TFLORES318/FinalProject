package com.skilldistillery.stockoverflow.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private String flair;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name="profile_picture")
	private String profilePicture;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	private Boolean enabled;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userCreator")
	private List <Webinar> webinarsUserIsHosting;
	@JsonIgnore
	@ManyToMany(mappedBy="usersAttending")
	private List <Webinar> webinarsAttending;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <Comment> comments;
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy="user")
	private List <Post> posts;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <CommentRating> commentRatings;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <WebinarRating> webinarRatings;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <UserStockJournal> journalEntries;
	@JsonIgnore
	@ManyToMany(mappedBy="users")
	private List <Stock> stocks;
	
	public User() {}

	public User(int id, String username, String password, String flair, String firstName, String lastName, String email,
			Role role, String profilePicture, LocalDateTime createDate, Boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.flair = flair;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.profilePicture = profilePicture;
		this.createDate = createDate;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFlair() {
		return flair;
	}

	public void setFlair(String flair) {
		this.flair = flair;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	public List<Webinar> getWebinarsUserIsHosting() {
		return webinarsUserIsHosting;
	}

	public void setWebinarsUserIsHosting(List<Webinar> webinarsUserIsHosting) {
		this.webinarsUserIsHosting = webinarsUserIsHosting;
	}
	
	// Webinar that user is hosting
	public void addWebinar(Webinar webinar) {
		if (webinarsUserIsHosting == null) {
			webinarsUserIsHosting = new ArrayList<>();
		}
		if (!webinarsUserIsHosting.contains(webinar)) {
			webinarsUserIsHosting.add(webinar);
			if (webinar.getUserCreator() != null) {
				webinar.getUserCreator().getWebinarsUserIsHosting().remove(webinar);
			}
		}
	}
	
	public void removeWebinar(Webinar webinar) {
		webinar.setUserCreator(null);
		if (webinarsUserIsHosting != null) {
			webinarsUserIsHosting.remove(webinar);
		}
	}
	
	

	public List<Webinar> getWebinarsAttending() {
		return webinarsAttending;
	}

	public void setWebinarsAttending(List<Webinar> webinarsAttending) {
		this.webinarsAttending = webinarsAttending;
	}
	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		
		if (!comments.contains(comment)) {
			comments.add(comment);
			if(comment.getUser() != null) {
				comment.getUser().getComments().remove(comment);
			}
			comment.setUser(this);
		}
	}

	public void removeComment(Comment comment) {
		comment.setUser(null);
		if (comments != null) {
			comments.remove(comment);
		}
	}
	

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post) {
		if (posts == null) {
			posts = new ArrayList<>();
		}
		
		if (!posts.contains(post)) {
			posts.add(post);
			if(post.getUser() != null) {
				post.getUser().getPosts().remove(post);
			}
			post.setUser(this);
		}
	}
	
	public void removePost(Post post) {
		post.setUser(null);
		if (posts != null) {
			posts.remove(post);
		}
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
			if(commentRating.getUser() != null) {
				commentRating.getUser().getCommentRatings().remove(commentRating);
			}
		}
	}
	
	public void removeCommentRating(CommentRating commentRating) {
		commentRating.setUser(null);
		if (commentRatings != null) {
			commentRatings.remove(commentRating);
		}
	}
	
	
	
	public List<WebinarRating> getWebinarRatings() {
		return webinarRatings;
	}

	public void setWebinarRatings(List<WebinarRating> webinarRatings) {
		this.webinarRatings = webinarRatings;
	}
	
	public void addWebinarRating(WebinarRating webinarRating) {
		if (webinarRatings == null) {
			webinarRatings = new ArrayList<>();
		}
		if(!webinarRatings.contains(webinarRating)) {
			webinarRatings.add(webinarRating);
			if(webinarRating.getUser() != null) {
				webinarRating.getUser().getWebinarRatings().remove(webinarRating);
			}
		}
	}
	
	public void removeWebinarRating(WebinarRating webinarRating) {
		webinarRating.setUser(null);
		if (webinarRatings != null) {
			webinarRatings.remove(webinarRating);
		}
	}
	

	public List<UserStockJournal> getJournalEntries() {
		return journalEntries;
	}

	public void setJournalEntries(List<UserStockJournal> journalEntries) {
		this.journalEntries = journalEntries;
	}
	
	public void addJournalEntry(UserStockJournal journalEntry) {
		if (journalEntries == null) {
			journalEntries = new ArrayList<>();
		}
		
		if (!journalEntries.contains(journalEntry)) {
			journalEntries.add(journalEntry);
			if(journalEntry.getUser() != null) {
				journalEntry.getUser().getJournalEntries().remove(journalEntry);
			}
			journalEntry.setUser(this);
		}
	}

	public void removeJournalEntry(UserStockJournal journalEntry) {
		journalEntry.setUser(null);
		if (journalEntries != null) {
			journalEntries.remove(journalEntry);
		}
	}
	

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", flair=" + flair
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role
				+ ", profilePicture=" + profilePicture + ", createDate=" + createDate + ", enabled=" + enabled + "]";
	}
	
	
	
	


}
