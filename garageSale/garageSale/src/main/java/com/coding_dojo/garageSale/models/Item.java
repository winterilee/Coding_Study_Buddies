package com.coding_dojo.garageSale.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "items")
public class Item {

//	table values
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Item must have a title")
	private String title;
	
	@NotEmpty(message = "Price must be listed")
	private int price;
	
	@NotEmpty(message = "A brief description must be included")
	private String description;
	
	private boolean status;
	
	private boolean isBidAccepted;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
//	default constructor
	public Item () {}
	
//	constructor
	public Item(String title, int price, String description, boolean status, boolean isBidAccepted, User user) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.status = status;
		this.isBidAccepted = isBidAccepted;
		this.user = user;
	}
	
//	getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getIsBidAccepted() {
		return isBidAccepted;
	}
	public void setIsBidAccepted(boolean isBidAccepted) {
		this.isBidAccepted = isBidAccepted;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
//	auto-generating + updating 'created_at' and 'updated_at'
	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}
	
}
