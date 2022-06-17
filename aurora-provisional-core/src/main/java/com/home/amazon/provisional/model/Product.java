package com.home.amazon.provisional.model;

import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("productId")
	private Long productId;

	@SerializedName("productName")
	private String productName;

	@SerializedName("passCategory")
	private String passCategory;

	public Product(Long productId) {
		super();
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPassCategory() {
		return passCategory;
	}

	public void setPassCategory(String passCategory) {
		this.passCategory = passCategory;
	}

}
