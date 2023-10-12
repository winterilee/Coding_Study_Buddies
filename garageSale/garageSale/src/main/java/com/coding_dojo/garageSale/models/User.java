package com.coding_dojo.garageSale.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
//	table values
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Email is required")
	@Email
	private String email;
	
	@NotEmpty(message = "Password is required")
	@Size(min = 8, max = 200, message = "Password must be at least 8 characters")
	private String password;
	
	@Transient
	@NotEmpty(message = "Password confirmation is required")
	@Size(min = 8, max = 200, message = "Password must be at least 8 characters")
	private String confirm;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated_at;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Item> items;
	
//	default constructor
	public User () {}
	
//	constructor
	public User(String name, String email, String password, List<Item> items) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.items = items;
	}
	
//	getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
//	auto-generating and updating 'created_at' and 'updated_at'
	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}
	
}
