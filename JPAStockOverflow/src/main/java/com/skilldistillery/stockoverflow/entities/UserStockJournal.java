package com.skilldistillery.stockoverflow.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="user_stock_journal")
public class UserStockJournal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;

	public UserStockJournal() {}

	public UserStockJournal(int id, String content, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserStockJournal [id=" + id + ", content=" + content + ", createdAt=" + createdAt + "]";
	}
	
	
	
	

}
