package net.javaguides.springboot.admin.products.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "sku")
	private String sku;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "user_id")
	private long userId;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "price")
	private int price;

	@Column(name = "cost")
	private int cost;

	public Product() {
	}

	public Product(long id, String sku, String name, String description, long userId, Date createdAt, Date updatedAt, int price, int cost) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.price = price;
		this.cost = cost;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
