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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="offers")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User offerer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private Item itemWithOffers;
	
	@NotNull(message="Please enter a offer amount")
	@Min(value=1, message="Offer amount must be at least 1")
	private Double amount;
	
	@NotEmpty(message="Please add offer remarks")
	@Size(min=10, message="Offer remarks must be 10 or more characters.")
	private String offerRemarks;
	
	public Offer() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getOfferer() {
		return offerer;
	}
	public void setOfferer(User offerer) {
		this.offerer = offerer;
	}
	public Item getItemWithOffers() {
		return itemWithOffers;
	}
	public void setItemWithOffers(Item itemWithOffers) {
		this.itemWithOffers = itemWithOffers;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getOfferRemarks() {
		return offerRemarks;
	}
	public void setOfferRemarks(String offerRemarks) {
		this.offerRemarks = offerRemarks;
	}
}

